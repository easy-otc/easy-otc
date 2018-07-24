package com.easytech.otc.coin.support.eth.contract;

import static org.web3j.utils.Convert.Unit.ETHER;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.web3j.utils.Convert;

import com.easytech.otc.coin.support.eth.EthValues;
import com.easytech.otc.coin.support.eth.base.TransactionClient;
import com.easytech.otc.service.BaseTest;

public class TestTransaction extends BaseTest {

    private String            from            = "0x87C8437a3c1767e13134EF39287f52687e222Fd4";
    private String            to              = "0xa60Cc01168053e97C92cEE32d1241e944c2e09E0";
    private String            privateKey      = "50CB1F53E22E441BAA474FE488781288CACF2F6209257088E98F2EF335426256";

    private String            contractAddress = "0xe70798c9de59701dbc15b3c6167d0d144fe93847";

    @Autowired
    private TransactionClient transactionClient;

    @Test
    public void testContractTransaction() {
        //        String txHash = transactionClient.sendTokenTransaction(contractAddress, from, to, new BigDecimal(9), 8, EthValues.CHAIN_ID, privateKey);
        //        System.out.println(txHash);
    }

    @Test
    public void testSendTransaction() {

        BigInteger balance = transactionClient.getBalance(from);
        System.out.println(balance);
        System.out.println(Convert.fromWei(new BigDecimal(balance), ETHER));

        BigInteger gasPrice = Convert.toWei(BigDecimal.valueOf(3), Convert.Unit.GWEI).toBigInteger();
        BigInteger gasLimit = BigInteger.valueOf(30000);

        String txHash = transactionClient.sendTransaction(from, to, gasPrice, new BigDecimal(28), "", EthValues.CHAIN_ID, privateKey);
        System.out.println(txHash);

        System.out.println();
    }
}
