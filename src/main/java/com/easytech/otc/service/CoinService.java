package com.easytech.otc.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easytech.otc.coin.support.CoinOperator;
import com.easytech.otc.coin.support.KeyAddr;
import com.easytech.otc.common.crypt.AESUtil;
import com.easytech.otc.mapper.model.Coin;
import com.easytech.otc.mapper.model.User;

@Service
public class CoinService extends BaseService<Coin> {

    @Autowired
    private UserService        userService;
    @Autowired
    private List<CoinOperator> operators;

    /**
     * 生成所有币和代币公私钥、地址
     *
     * @param uid
     */
    @Transactional
    public void genCoinAndTokenAccount(Integer uid) {
        User user = userService.getById(uid);
        String k = user.getLoginPasswordPublicKey();

        operators.stream().forEach(coinOperator -> {

            // TODO liuweizhen 代币要斟酌
            KeyAddr keyAddr = coinOperator.genKeyAddr();
            String privateKey = keyAddr.getPrivateKey();
            String publicKey = keyAddr.getPublicKey();
            String address = keyAddr.getAddress();

            String encryptPrivateKey = AESUtil.encrypt(privateKey, StringUtils.substring(k, 40, 56), StringUtils.substring(k, 60, 76));

            Coin coin = new Coin();
            coin.setUid(uid);
            coin.setCoinType(coinOperator.coinType().getCode());
            coin.setPrivateKey(encryptPrivateKey);
            coin.setPublicKey(publicKey);
            coin.setAddress(address);
            coin.setAmount(BigDecimal.ZERO);
            coin.setLockAmount(BigDecimal.ZERO);

            this.insert(coin);
        });
    }
}
