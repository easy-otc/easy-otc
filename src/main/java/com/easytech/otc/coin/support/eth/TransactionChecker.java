package com.easytech.otc.coin.support.eth;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * 定时检查交易是否成功
 *
 * 从数据库拉取然后去线上检查
 */
@Slf4j
@Component
public class TransactionChecker {

    @Autowired
    private Web3j                 web3j;

    private final ExecutorService timeoutCheckeExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,   //
        new PriorityBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat("transaction-check-pool").build());

    public void submit(String transactionHash) {
        this.timeoutCheckeExecutor.submit(new AsyncTransactionCheckRunnable(transactionHash));
    }

    class AsyncTransactionCheckRunnable implements Runnable, Comparable<AsyncTransactionCheckRunnable> {
        private String transactionHash;
        private Date   submitTime;
        private int    reties = 0;

        public AsyncTransactionCheckRunnable(String transactionHash) {
            this.transactionHash = transactionHash;
            this.submitTime = new Date();
        }

        @Override
        public void run() {
            try {
                TransactionReceipt transactionReceipt = web3j.ethGetTransactionReceipt(transactionHash).sendAsync().get().getTransactionReceipt().get();
                if (transactionReceipt.isStatusOK()) {

                }

                reties++;
                timeoutCheckeExecutor.submit(this);
            } catch (Exception e) {
                LOGGER.error(null, e);
            }
        }

        @Override
        public int compareTo(AsyncTransactionCheckRunnable o) {
            return this.submitTime.compareTo(o.submitTime);
        }
    }
}