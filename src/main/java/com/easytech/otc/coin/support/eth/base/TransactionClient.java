package com.easytech.otc.coin.support.eth.base;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import com.easytech.otc.coin.support.eth.EthException;

/**
 * eth转账相关接口
 * 
 */
@Component
public class TransactionClient {

    @Autowired
    private Web3j web3j;

    /**
     * 获取余额
     *
     * @param address 钱包地址
     * @return 余额
     */
    public BigInteger getBalance(String address) {
        try {
            EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
            return ethGetBalance.getBalance();
        } catch (IOException e) {
            throw new EthException(e);
        }
    }

    /**
     * 发送交易
     * 
     * @param from
     * @param to
     * @param amount
     * @param gasPrice
     * @param gasLimit
     * @param supplement
     * @param chainId
     * @param privateKey
     * @return
     */
    public String sendTransaction(String from, String to, BigDecimal amount, BigInteger gasPrice, BigInteger gasLimit, String supplement, byte chainId, String privateKey) {
        try {
            BigInteger nonce = web3j.ethGetTransactionCount(from, DefaultBlockParameterName.PENDING).send().getTransactionCount();
            BigInteger value = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger();

            String signedData = signTransaction(nonce, gasPrice, gasLimit, to, value, supplement, chainId, privateKey);
            if (signedData != null) {
                EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(signedData).send();
                return ethSendTransaction.getTransactionHash();
            }

            return null;
        } catch (Exception e) {
            throw new EthException(e);
        }
    }

    /**
     * 发送代币交易
     *
     * @param contractAddress
     * @param from
     * @param to
     * @param amount
     * @param decimals
     * @param gasPrice
     * @param gasLimit
     * @param chainId
     * @param privateKey
     * @return
     */
    public String sendTokenTransaction(String contractAddress, String from, String to, BigDecimal amount, int decimals, BigInteger gasPrice, BigInteger gasLimit, byte chainId, String privateKey) {

        try {
            String methodName = "transfer";

            BigInteger nonce = web3j.ethGetTransactionCount(from, DefaultBlockParameterName.PENDING).send().getTransactionCount();

            List<Type> inputParameters = new ArrayList<>();
            inputParameters.add(new Address(to));
            inputParameters.add(new Uint256(amount.multiply(BigDecimal.TEN.pow(decimals)).toBigInteger()));

            List<TypeReference<?>> outputParameters = new ArrayList<>();
            outputParameters.add(new TypeReference<Bool>() {
            });

            Function function = new Function(methodName, inputParameters, outputParameters);
            String data = FunctionEncoder.encode(function);

            String signedData = signTransaction(nonce, gasPrice, gasLimit, contractAddress, BigInteger.ZERO, data, chainId, privateKey);
            if (signedData != null) {
                EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(signedData).send();
                String txHash = ethSendTransaction.getTransactionHash();
                return txHash;
            }

            return null;
        } catch (IOException e) {
            throw new EthException(e);
        }
    }

    /**
     * 获取普通交易的gas上限
     *
     * @param transaction 交易对象
     * @return gas 上限
     */
    private BigInteger getTransactionGasLimit(Transaction transaction) {
        try {
            return web3j.ethEstimateGas(transaction).send().getAmountUsed();
        } catch (IOException e) {
            throw new EthException(e);
        }
    }

    /**
     * 签名交易
     *
     * @param nonce
     * @param gasPrice
     * @param gasLimit
     * @param to
     * @param value
     * @param data
     * @param chainId
     * @param privateKey
     * @return
     */
    public static String signTransaction(BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, String to, BigInteger value, String data, byte chainId, String privateKey) {
        RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, to, value, data);

        if (privateKey.startsWith("0x")) {
            privateKey = privateKey.substring(2);
        }
        ECKeyPair ecKeyPair = ECKeyPair.create(new BigInteger(privateKey, 16));
        Credentials credentials = Credentials.create(ecKeyPair);

        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, chainId, credentials);
        return Numeric.toHexString(signedMessage);
    }

    /**
     * 生成一个普通交易对象
     *
     * @param from 放款方
     * @param to   收款方
     * @param nonce       交易序号
     * @param gasPrice    gas 价格
     * @param gasLimit    gas 数量
     * @param value       金额
     * @return 交易对象
     */
    private static Transaction makeTransaction(String from, String to, BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, BigInteger value) {
        return Transaction.createEtherTransaction(from, nonce, gasPrice, gasLimit, to, value);
    }

    /**
     * 提交前 计算交易hash
     *
     * @param signedData
     * @return
     */
    public static String caculateTransactionHash(String signedData) {
        return Hash.sha3(signedData);
    }
}