package com.easytech.otc.coin.support.eth;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.web3j.tx.ChainId;
import org.web3j.utils.Convert;

public class EthValues {

    public static final String EMPTY_STR = "";

    private BigInteger         gasPrice  = Convert.toWei(BigDecimal.valueOf(3), Convert.Unit.GWEI).toBigInteger();

    private byte               chainId   = ChainId.ROPSTEN;

    // ------------------------------------------------------------------------

    private EthValues() {
    }

    private static class Holder {
        private static final EthValues INSTANCE = new EthValues();
    }

    public static EthValues getInstance() {
        return Holder.INSTANCE;
    }

    // ------------------------------------------------------------------------

    public synchronized void setGasPrice(BigInteger gasPrice) {
        this.gasPrice = gasPrice;
    }

    public synchronized BigInteger getGasPrice() {
        return gasPrice;
    }

    public synchronized byte getChainId() {
        return chainId;
    }
}