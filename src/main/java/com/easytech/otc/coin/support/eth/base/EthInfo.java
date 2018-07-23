package com.easytech.otc.coin.support.eth.base;

import java.io.IOException;
import java.math.BigInteger;

import org.web3j.protocol.Web3j;

import com.easytech.otc.coin.support.eth.EthException;

/**
 * 请求区块链的信息
 */
public class EthInfo {

    private static Web3j web3j;

    /**
     * 客户端版本
     * 
     * @return
     */
    public String getClientVersion() {
        try {
            return web3j.web3ClientVersion().send().getWeb3ClientVersion();
        } catch (IOException e) {
            throw new EthException(e);
        }
    }

    /**
     * 区块高度
     *
     * @return
     */
    public BigInteger getBlockNumber() {
        try {
            return web3j.ethBlockNumber().send().getBlockNumber();
        } catch (IOException e) {
            throw new EthException(e);
        }
    }

    /**
     * 挖矿奖励账户
     * 
     * @return
     */
    public String getAddress() {
        try {
            return web3j.ethCoinbase().send().getAddress();
        } catch (IOException e) {
            throw new EthException(e);
        }
    }

    /**
     * 是否在同步区块
     *
     * @return
     */
    public boolean isSyncing() {
        try {
            return web3j.ethSyncing().send().isSyncing();
        } catch (IOException e) {
            throw new EthException(e);
        }
    }

    /**
     * 是否在挖矿
     *
     * @return
     */
    public boolean isMining() {
        try {
            return web3j.ethMining().send().isMining();
        } catch (IOException e) {
            throw new EthException(e);
        }
    }

    /**
     * 当前gas price
     *
     * @return
     */
    public BigInteger getGasPrice() {
        try {
            return web3j.ethGasPrice().send().getGasPrice();
        } catch (IOException e) {
            throw new EthException(e);
        }
    }

    /**
     * 挖矿速度
     *
     * @return
     */
    public BigInteger getHashrate() {
        try {
            return web3j.ethHashrate().send().getHashrate();
        } catch (IOException e) {
            throw new EthException(e);
        }
    }

    /**
     * 协议版本
     *
     * @return
     */
    public String getProtocolVersion() {
        try {
            return web3j.ethProtocolVersion().send().getProtocolVersion();
        } catch (IOException e) {
            throw new EthException(e);
        }
    }

    /**
     * 连接的节点数
     *
     * @return
     */
    public BigInteger getQuantity() {
        try {
            return web3j.netPeerCount().send().getQuantity();
        } catch (IOException e) {
            throw new EthException(e);
        }
    }
}