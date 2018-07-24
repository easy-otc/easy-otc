package com.easytech.otc.coin.support.eth;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;

import com.easytech.otc.service.OrderService;
import com.google.common.collect.Lists;

import rx.Subscription;
import rx.functions.Action1;

/**
 * 监听区块、交易
 * 所有监听都在Web3jRx中
 *
 */
//@Component
public class EthObserver {

    @Autowired
    private Web3j              web3j;

    @Autowired
    private OrderService       orderService;

    private List<Subscription> subscriptions = Lists.newArrayList();

    @PostConstruct
    public void init() {
        // TODO liuweizhen 新块似乎没必要监听
        subscriptions.add(newBlockObserver());

        subscriptions.add(newTransactionObserver());
        subscriptions.add(pendingTransactionObserver());
    }

    @PreDestroy
    public void destroy() {
        subscriptions.stream().forEach(subscription -> {
            subscription.unsubscribe();
        });
    }

    /**
     * 新块监听
     * 
     * @return
     */
    public Subscription newBlockObserver() {
        return web3j.blockObservable(false).subscribe(block -> {

            System.out.println("new block come in");
            System.out.println("block number" + block.getBlock().getNumber());
        });
    }

    /**
     * 打进区块的交易监听
     * 
     * @return
     */
    public Subscription newTransactionObserver() {
        return web3j.transactionObservable().subscribe(transaction -> {
            // TODO liuweizhen 优化点：可以把交易过的hash放到redis上，然后从redis拿出来做一层过滤
            orderService.orderSuccessOnChain(transaction.getHash());
        });
    }

    /**
     * 网络交易监听
     * 
     * @return
     */
    public Subscription pendingTransactionObserver() {
        return web3j.pendingTransactionObservable().subscribe(transaction -> {

            // TODO liuweizhen 优化点：可以把交易过的hash放到redis上，然后从redis拿出来做一层过滤
            orderService.orderSuccessOnNet(transaction.getHash());
        });
    }

    /**
     * 监听ERC20 token 交易
     *
     * @param contractAddress
     */
    public void observContractTransacion(String contractAddress) {

        // TODO liuweizhen 从token表中查出合约地址做监听

        List<String> contractAddresses = Lists.newArrayList();
        contractAddresses.stream().forEach(s -> {

            EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, contractAddress);
            Event event = new Event("Transfer", Arrays.asList(new TypeReference<Address>() {
            }, new TypeReference<Address>() {
            }), Arrays.asList(new TypeReference<Uint256>() {
            }));

            String topicData = EventEncoder.encode(event);
            filter.addSingleTopic(topicData);

            System.out.println(topicData);

            web3j.ethLogObservable(filter).subscribe(new Action1<Log>() {

                @Override
                public void call(Log log) {

                    System.out.println(log.getBlockNumber());
                    System.out.println(log.getTransactionHash());

                    List<String> topics = log.getTopics();
                    for (String topic : topics) {
                        System.out.println(topic);
                    }
                }
            });
        });
    }

}
