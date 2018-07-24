package com.easytech.otc.coin.support.eth;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EthTask {

    @Scheduled(fixedRate = 1000 * 15, initialDelay = 1000 * 10)
    private void process() {
        EthValues.setRecomendedGasPrice(GasPriceStation.fetchRecomendedGasPrice());
    }
}
