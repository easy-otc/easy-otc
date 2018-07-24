package com.easytech.otc.coin.support.eth;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.web3j.tx.ChainId;
import org.web3j.utils.Convert;

public class EthValues {

    public static final String                        EMPTY_STR         = "";
    public static final byte                          CHAIN_ID          = ChainId.ROPSTEN;

    public static final BigInteger                    DEFAULT_GAS_PRICE = Convert.toWei(BigDecimal.valueOf(3), Convert.Unit.GWEI).toBigInteger();
    public static final BigInteger                    DEFAULT_GAS_LIMIT = BigInteger.valueOf(21000);

    private static GasPriceStation.RecomendedGasPrice recomendedGasPrice;

    public static synchronized void setRecomendedGasPrice(GasPriceStation.RecomendedGasPrice rgs) {
        recomendedGasPrice = rgs;
    }

    public static synchronized GasPriceStation.RecomendedGasPrice getRecomendedGasPrice() {
        return recomendedGasPrice;
    }

    public static synchronized BigInteger getGasPrice() {
        if (recomendedGasPrice != null) {
            return recomendedGasPrice.getFast();
        }
        return DEFAULT_GAS_PRICE;
    }

    public static synchronized BigInteger getGasLimit() {
        return DEFAULT_GAS_LIMIT;
    }
}