package com.easytech.otc.config.chain.eth;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.ipc.UnixIpcService;
import org.web3j.protocol.ipc.WindowsIpcService;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Slf4j
@Configuration
public class EthConfig {

    @Value("${eth.web3j.url}")
    private String web3jUrl;

    @Value("${eth.web3j.timeoutSeconds}")
    private long   timeout;

    @Bean
    public Web3j web3j(OkHttpClient httpClient) {
        Web3jService web3jService = buildService(web3jUrl, httpClient);
        LOGGER.info("Building service for endpoint: " + web3jUrl);
        return Web3j.build(web3jService);
    }

    @Bean
    public Admin admin(OkHttpClient httpClient) {
        Web3jService web3jService = buildService(web3jUrl, httpClient);
        LOGGER.info("Building admin service for endpoint: " + web3jUrl);
        return Admin.build(web3jService);
    }

    @Bean
    public OkHttpClient httpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        configureLogging(builder);
        configureTimeouts(builder);
        return builder.build();
    }

    private Web3jService buildService(String clientAddress, OkHttpClient httpClient) {
        Web3jService web3jService;

        if (clientAddress == null || clientAddress.equals("")) {
            web3jService = new HttpService(httpClient);
        } else if (clientAddress.startsWith("http")) {
            web3jService = new HttpService(clientAddress, httpClient, false);
        } else if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            web3jService = new WindowsIpcService(clientAddress);
        } else {
            web3jService = new UnixIpcService(clientAddress);
        }

        return web3jService;
    }

    private void configureTimeouts(OkHttpClient.Builder builder) {
        builder.connectTimeout(timeout, TimeUnit.SECONDS);
        builder.readTimeout(timeout, TimeUnit.SECONDS);
        builder.writeTimeout(timeout, TimeUnit.SECONDS);
    }

    private static void configureLogging(OkHttpClient.Builder builder) {
        if (LOGGER.isDebugEnabled()) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(LOGGER::debug);
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
    }
}
