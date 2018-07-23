package com.easytech.otc.coin.support.eth.base;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import org.springframework.stereotype.Component;
import org.web3j.crypto.*;
import org.web3j.protocol.ObjectMapperFactory;

import com.easytech.otc.coin.support.KeyAddr;
import com.easytech.otc.coin.support.eth.EthException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公钥私钥相关接口
 */
@Component
public class WalletClient {

    /**
     * 从keystore导出私钥
     *
     * @param keystorePath 账号的keystore路径
     * @param password     密码
     */
    private String exportPrivateKey(String keystorePath, String password) {
        try {
            Credentials credentials = WalletUtils.loadCredentials(password, keystorePath);
            BigInteger privateKey = credentials.getEcKeyPair().getPrivateKey();
            return privateKey.toString(16);
        } catch (IOException e) {
            throw new EthException(e);
        } catch (CipherException e) {
            throw new EthException("密码错误");
        }
    }

    /**
     * 导入私钥到keystore，返回keystore文件名
     *
     * @param privateKey 私钥
     * @param password   密码
     * @param directory  存储路径 默认测试网络WalletUtils.getTestnetKeyDirectory() 默认主网络 WalletUtils.getMainnetKeyDirectory()
     * @return keystoreName
     */
    private String importPrivateKey(BigInteger privateKey, String password, String directory) {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        ECKeyPair ecKeyPair = ECKeyPair.create(privateKey);
        try {
            return WalletUtils.generateWalletFile(password, ecKeyPair, dir, true);
        } catch (CipherException e) {
            throw new EthException("密码错误");
        } catch (IOException e) {
            throw new EthException(e);
        }
    }

    /**
     * 生成带助记词的账号
     * 账号包括两部分内容：keystore文件名、助记词
     *
     * @param keystorePath
     * @param password
     */
    private Bip39Wallet exportBip39Wallet(String keystorePath, String password) {
        try {
            return WalletUtils.generateBip39Wallet(password, new File(keystorePath));
        } catch (CipherException e) {
            throw new EthException("密码错误");
        } catch (IOException e) {
            throw new EthException(e);
        }
    }

    /**
     * 从助记词导出公私钥、地址
     * 
     * @param password 密码
     * @param mnemonic 助记词
     */
    private KeyAddr loadBip39Credentials(String password, String mnemonic) {
        Credentials credentials = WalletUtils.loadBip39Credentials(password, mnemonic);

        String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
        String publicKey = credentials.getEcKeyPair().getPublicKey().toString(16);
        String address = credentials.getAddress();

        return new KeyAddr(privateKey, publicKey, address);

    }

    /**
     * 创建钱包
     *
     * @param password 密码
     */
    public KeyStoreAddr createWallet(String password) {
        try {
            ECKeyPair ecKeyPair = Keys.createEcKeyPair();
            WalletFile walletFile = Wallet.createStandard(password, ecKeyPair);
            ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
            String keystore = objectMapper.writeValueAsString(walletFile);

            return new KeyStoreAddr(keystore, walletFile.getAddress());
        } catch (Exception e) {
            throw new EthException(e);
        }
    }

    /**
     * 创建账户
     *
     * @return
     */
    public KeyAddr createKeyAddr() {
        try {
            ECKeyPair keyPair = Keys.createEcKeyPair();

            String privateKey = keyPair.getPrivateKey().toString(16);
            String publicKey = keyPair.getPublicKey().toString(16);
            String address = Keys.getAddress(keyPair);

            return new KeyAddr(privateKey, publicKey, address);
        } catch (Exception e) {
            throw new EthException(e);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KeyStoreAddr {

        private String keystore;
        private String address;
    }
}
