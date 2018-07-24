package com.easytech.otc.config.chain;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.easytech.otc.coin.support.CoinOperator;
import com.easytech.otc.enums.CoinTypeEnum;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class CoinConfig {

    private Map<CoinTypeEnum, CoinOperator> coinOperatorMap = Maps.newHashMap();
    private List<CoinOperator>              operators;

    @Bean
    public Object coinOperators(List<CoinOperator> coinOperators) {
        operators.stream().forEach(operator -> {
            CoinTypeEnum coinTypeEnum = operator.coinType();
            if (coinTypeEnum == null) {
                LOGGER.error("there's 'null' coin type fund on class {}.", operator.getClass());
                throw new RuntimeException("no coin type defined!");
            }

            CoinOperator coinOperator = coinOperatorMap.putIfAbsent(coinTypeEnum, operator);
            if (coinOperator != null) {
                LOGGER.error("duplicate coin operator [{}] and [{}] defined of type {}.", operator.getClass(), coinOperator.getClass(), coinTypeEnum);
                throw new RuntimeException("duplicate coin operator defined!");
            }
        });

        Collections.sort(coinOperators, (o1, o2) -> {
            boolean coin1 = o1.coinType().isCoin();
            boolean coin2 = o2.coinType().isCoin();
            return coin1 ? 1 : (coin2 ? 0 : -1);
        });
        operators = coinOperators;

        return null;
    }

    public CoinOperator getCoinOperator(CoinTypeEnum coinTypeEnum) {

        CoinOperator coinOperator = coinOperatorMap.get(coinTypeEnum);
        if (coinOperator == null) {
            throw new RuntimeException("there's no coin operator defined of type" + coinTypeEnum);
        }

        return coinOperator;
    }

    public List<CoinOperator> getCoinOperators() {
        return operators;
    }
}
