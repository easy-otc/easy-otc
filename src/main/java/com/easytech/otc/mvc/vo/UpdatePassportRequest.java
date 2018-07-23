package com.easytech.otc.mvc.vo;

import lombok.Data;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/22 19:47
 */
@Data
public class UpdatePassportRequest {
    private String oldPassword;
    private String newPassword;
}
