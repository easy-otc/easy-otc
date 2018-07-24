package com.easytech.otc.service;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easytech.otc.coin.support.CoinOperator;
import com.easytech.otc.coin.support.KeyAddr;
import com.easytech.otc.common.crypt.AESUtil;
import com.easytech.otc.config.chain.CoinConfig;
import com.easytech.otc.enums.CoinTypeEnum;
import com.easytech.otc.exception.BizException;
import com.easytech.otc.mapper.model.Coin;
import com.easytech.otc.mapper.model.User;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CoinService extends BaseService<Coin> {

    @Autowired
    private UserService userService;
    @Autowired
    private CoinConfig  coinConfig;

    /**
     * 生成所有币和代币公私钥、地址
     *
     * @param uid
     */
    @Transactional
    public void genCoinAndTokenAccount(Integer uid) {
        User user = userService.getById(uid);
        String k = user.getLoginPasswordPublicKey();

        Map<String, Coin> map = Maps.newHashMap();

        for (CoinOperator operator : coinConfig.getCoinOperators()) {

            // TODO liuweizhen 代币要斟酌

            CoinTypeEnum coinTypeEnum = operator.coinType();
            String coinCode = coinTypeEnum.getCode();

            if (coinTypeEnum.isCoin()) {
                KeyAddr keyAddr = operator.genKeyAddr();
                String privateKey = keyAddr.getPrivateKey();
                String publicKey = keyAddr.getPublicKey();
                String address = keyAddr.getAddress();

                String encryptPrivateKey = AESUtil.encrypt(privateKey, StringUtils.substring(k, 40, 56), StringUtils.substring(k, 60, 76));

                Coin coin = new Coin();
                coin.setUid(uid);
                coin.setCoinCode(coinCode);
                coin.setPrivateKey(encryptPrivateKey);
                coin.setPublicKey(publicKey);
                coin.setAddress(address);
                coin.setAmount(BigDecimal.ZERO);
                coin.setLockAmount(BigDecimal.ZERO);

                this.insert(coin);

                map.put(coinCode, coin);

                continue;
            }

            Coin refCoin = map.get(coinCode.substring(0, 1) + "00");
            if (refCoin == null) {
                LOGGER.error("未定义平台币, coinType : {}.", coinTypeEnum);
                throw new BizException("未定义平台币");
            }

            Coin coin = new Coin();
            coin.setUid(uid);
            coin.setCoinCode(coinCode);
            coin.setRefCoin(refCoin.getId());
            coin.setAmount(BigDecimal.ZERO);
            coin.setLockAmount(BigDecimal.ZERO);

            this.insert(coin);
        }
    }
}