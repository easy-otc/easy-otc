package com.easytech.otc.coin.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyAddr {

    private String privateKey;
    private String publicKey;
    private String address;
}
