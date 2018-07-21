/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.7.20-log : Database - easy-otc
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE = '' */;

/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS */`easy-otc` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `easy-otc`;

/*Table structure for table `ad` */

DROP TABLE IF EXISTS `ad`;

CREATE TABLE `ad` (
  `id`                         int(10)        NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  `uid`                        int(10)        NOT NULL
  COMMENT '用户id',
  `ad_type`                    tinyint(1)     NOT NULL
  COMMENT '广告类型',
  `coin_type`                  tinyint(1)     NOT NULL
  COMMENT '币种',
  `amount`                     decimal(18, 8) NOT NULL DEFAULT '0.00000000'
  COMMENT '币数量',
  `legal_price`                decimal(10, 2) NOT NULL DEFAULT '0.00'
  COMMENT '法币单价，单位：元',
  `Transaction_term`           varchar(256)            DEFAULT NULL
  COMMENT '交易条款',
  `only_real_name_authed_user` tinyint(1)     NOT NULL DEFAULT '0'
  COMMENT '是否仅限于已实名认证用户,0-否，1-是',
  `status`                     tinyint(1)     NOT NULL DEFAULT '0'
  COMMENT '广告状态,0-上单，1-撤单，2-删除',
  `create_time`                timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '记录创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_uid_coin_type_ad_type` (`uid`, `coin_type`, `ad_type`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `ad` */

/*Table structure for table `coin` */

DROP TABLE IF EXISTS `coin`;

CREATE TABLE `coin` (
  `id`          int(4)         NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  `uid`         int(10)        NOT NULL
  COMMENT '用户id',
  `coin_type`   tinyint(3)     NOT NULL
  COMMENT '币种',
  `private_key` varchar(128)   NOT NULL
  COMMENT '私钥',
  `public_key`  varchar(128)   NOT NULL
  COMMENT '公钥',
  `address`     varchar(128)   NOT NULL
  COMMENT '地址',
  `amount`      decimal(18, 8) NOT NULL DEFAULT '0.00000000'
  COMMENT '币数量',
  `lock_amount` decimal(18, 8) NOT NULL DEFAULT '0.00000000'
  COMMENT '锁定币数量，可用token数量 = amount - lock_amount',
  `create_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '记录创建时间',
  `update_time` timestamp      NULL     DEFAULT CURRENT_TIMESTAMP
  COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_uid_token_type` (`uid`, `coin_type`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `coin` */

/*Table structure for table `coin_flow` */

DROP TABLE IF EXISTS `coin_flow`;

CREATE TABLE `coin_flow` (
  `id`                int(4)         NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  `uid`               int(10)        NOT NULL
  COMMENT '用户id',
  `coin_type`         tinyint(1)     NOT NULL
  COMMENT '币种',
  `op_type`           tinyint(1)     NOT NULL
  COMMENT '操作类型，0-充，1-提，2-买，3-卖，充提是主动流水，买卖是自动流水',
  `address`           varchar(128)   NOT NULL
  COMMENT '地址',
  `amount`            decimal(18, 8) NOT NULL DEFAULT '0.00000000'
  COMMENT 'token数量',
  `before_amount`     decimal(18, 8) NOT NULL DEFAULT '0.00000000'
  COMMENT '发生之前数量',
  `chain_cost_amount` decimal(18, 8) NOT NULL DEFAULT '0.00000000'
  COMMENT '交易消耗数量',
  `after_amount`      decimal(18, 8) NOT NULL DEFAULT '0.00000000'
  COMMENT '发生之后数量',
  `create_time`       timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '记录创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_uid_coin_type` (`uid`, `coin_type`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `coin_flow` */

/*Table structure for table `legal_account` */

DROP TABLE IF EXISTS `legal_account`;

CREATE TABLE `legal_account` (
  `id`          int(8)      NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  `uid`         int(10)     NOT NULL
  COMMENT '用户id',
  `mode`        tinyint(1)  NOT NULL
  COMMENT '收款方式，0-支付宝，1-微信，2-银行卡',
  `real_name`   varchar(16) NOT NULL
  COMMENT '姓名',
  `account`     varchar(32) NOT NULL
  COMMENT '账号',
  `qr_code_url` varchar(128)         DEFAULT NULL
  COMMENT '收款二维码url',
  `memo`        varchar(128)         DEFAULT NULL
  COMMENT '个人说明',
  `create_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '记录创建时间',
  `update_time` timestamp   NULL     DEFAULT CURRENT_TIMESTAMP
  COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_uid` (`uid`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `legal_account` */

/*Table structure for table `legal_flow` */

DROP TABLE IF EXISTS `legal_flow`;

CREATE TABLE `legal_flow` (
  `id`            int(4)         NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  `uid`           int(10)        NOT NULL
  COMMENT '用户id',
  `op_type`       tinyint(1)     NOT NULL
  COMMENT '流水类型，0-充，1-提，2-手续费，3-手续费返还，充提属于主动流水，手续费、手续费返还属于自动流水',
  `amount`        decimal(10, 2) NOT NULL DEFAULT '0.00'
  COMMENT '发生金额，单位：元',
  `before_amount` decimal(10, 2) NOT NULL DEFAULT '0.00'
  COMMENT '发生之前金额',
  `after_amount`  decimal(10, 2) NOT NULL DEFAULT '0.00'
  COMMENT '发生之后金额',
  `create_time`   timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '记录创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_uid_op_type` (`uid`, `op_type`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `legal_flow` */

/*Table structure for table `news` */

DROP TABLE IF EXISTS `news`;

CREATE TABLE `news` (
  `id`          int(4)      NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  `news_type`   tinyint(1)  NOT NULL DEFAULT '0'
  COMMENT '讯息类型，0-公告，1-新闻',
  `title`       varchar(32) NOT NULL
  COMMENT '标题',
  `content`     text        NOT NULL
  COMMENT '内容。以HTML的形式存储，前端拿到后直接渲染',
  `author`      varchar(32) NOT NULL
  COMMENT '作者',
  `status`      tinyint(1)  NOT NULL DEFAULT '0'
  COMMENT '讯息状态,0-发布，1-撤销',
  `create_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '记录创建时间',
  `update_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '修改时间',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `news` */

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `id`                int(4)         NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  `order_id`          varchar(15)    NOT NULL
  COMMENT '订单号',
  `uid`               int(10)        NOT NULL
  COMMENT '用户id',
  `memo_code`         varchar(6)     NOT NULL
  COMMENT '订单备注号',
  `order_type`        tinyint(1)     NOT NULL
  COMMENT '订单类型，0-买单，1-卖单',
  `ad_id`             int(10)        NOT NULL
  COMMENT '广告id',
  `coin_type`         tinyint(1)     NOT NULL
  COMMENT '币种',
  `amount`            decimal(18, 8) NOT NULL DEFAULT '0.00000000'
  COMMENT '交易数量',
  `legal_price`       decimal(10, 2) NOT NULL DEFAULT '0.00'
  COMMENT '法币单价，单位：元',
  `total_legal_price` decimal(10, 2) NOT NULL DEFAULT '0.00'
  COMMENT '法币总价',
  `payment_status`    tinyint(1)     NOT NULL DEFAULT '0'
  COMMENT '支付状态，0-未支付，1-已支付',
  `order_status`      tinyint(1)     NOT NULL DEFAULT '0'
  COMMENT '订单状态，0-进行中，1-已完成，2-已取消',
  `payment_time`      timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '支付时间',
  `coin_Release_time` timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '确认放币时间',
  `create_time`       timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '记录创建时间',
  `update_time`       timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_uid_status` (`uid`, `order_status`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `order` */

/*Table structure for table `real_name_info` */

DROP TABLE IF EXISTS `real_name_info`;

CREATE TABLE `real_name_info` (
  `id`                    int(8)       NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  `real_name`             varchar(32)  NOT NULL
  COMMENT '真实姓名',
  `id_card_no`            varchar(18)  NOT NULL
  COMMENT '身份证号',
  `id_card_front_pic_url` varchar(128) NOT NULL
  COMMENT '正面照片url',
  `id_card_back_pic_url`  varchar(128) NOT NULL
  COMMENT '背面照片url',
  `id_card_held_pic_url`  varchar(128) NOT NULL
  COMMENT '手持照片url',
  `status`                tinyint(1)   NOT NULL DEFAULT '0'
  COMMENT '实名认证状态，0-已提交，2-审核中，3-实名通过，4-实名认证失败',
  `fail_times`            int(2)       NOT NULL DEFAULT '0'
  COMMENT '审核失败次数',
  `create_time`           timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '记录创建时间',
  `update_time`           timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_id_card_no` (`id_card_no`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `real_name_info` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id`                         int(10)        NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  `NAME`                       varchar(64)    NOT NULL
  COMMENT '用户名',
  `email`                      varchar(128)   NOT NULL
  COMMENT '邮箱',
  `mobile`                     char(11)                DEFAULT NULL
  COMMENT '手机号码',
  `login_password`             varchar(128)   NOT NULL
  COMMENT '登录密码',
  `login_password_private_key` varchar(128)   NOT NULL
  COMMENT '登录密码私钥',
  `login_password_public_key`  varchar(128)   NOT NULL
  COMMENT '登录密码公钥',
  `fund_password`              varchar(128)   NOT NULL
  COMMENT '资金密码',
  `fund_password_private_key`  varchar(128)   NOT NULL
  COMMENT '资金密码私钥',
  `fund_password_public_key`   varchar(128)   NOT NULL
  COMMENT '资金密码公钥',
  `default_legal_account`      int(8)                  DEFAULT NULL
  COMMENT '默认法币账号',
  `legal_amount`               decimal(10, 2) NOT NULL DEFAULT '0.00'
  COMMENT '法币余额',
  `lock_legal_amount`          decimal(10, 2) NOT NULL DEFAULT '0.00'
  COMMENT '锁定法币',
  `invition_code`              varchar(8)     NOT NULL
  COMMENT '邀请码',
  `invited_by`                 int(10)                 DEFAULT NULL
  COMMENT '邀请者',
  `trade_times`                int(5)         NOT NULL DEFAULT '0'
  COMMENT '交易次数',
  `trade_success_times`        int(5)         NOT NULL DEFAULT '0'
  COMMENT '成功交易次数',
  `praise_rate`                decimal(4, 2)           DEFAULT NULL
  COMMENT '好评率',
  `login_ip`                   varchar(160)            DEFAULT NULL
  COMMENT '最近10次登录ip，以竖线分隔',
  `last_login_time`            timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '最近一次登录时间',
  `is_real_name_authed`        tinyint(1)     NOT NULL DEFAULT '0'
  COMMENT '是否实名认证,0-否,1-是',
  `is_mobile_verified`         tinyint(1)     NOT NULL DEFAULT '0'
  COMMENT '手机号是否验证,0-否,1-是',
  `is_email_verified`          tinyint(1)     NOT NULL DEFAULT '0'
  COMMENT '邮箱是否验证,0-否,1-是',
  `create_time`                timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '记录创建时间',
  `update_time`                timestamp      NULL     DEFAULT CURRENT_TIMESTAMP
  COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_name` (`NAME`),
  UNIQUE KEY `unq_email` (`email`),
  UNIQUE KEY `unq_mobile` (`mobile`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

/*Data for the table `user` */

insert into `user` (`id`, `NAME`, `email`, `mobile`, `login_password`, `login_password_private_key`, `login_password_public_key`, `fund_password`, `fund_password_private_key`, `fund_password_public_key`, `default_legal_account`, `legal_amount`, `lock_legal_amount`, `invition_code`, `invited_by`, `trade_times`, `trade_success_times`, `praise_rate`, `login_ip`, `last_login_time`, `is_real_name_authed`, `is_mobile_verified`, `is_email_verified`, `create_time`, `update_time`)
values
  (1, 'name', '737093270@qq.com', NULL, 'setLoginPassword', 'setLoginPasswordPrivateKey', 'setLoginPasswordPublicKey',
      'setFundPassword', 'setFundPasswordPrivateKey', 'setFundPasswordPublicKey', NULL, '1.00', '0.00', 'setInvit',
                                                                                        NULL, 13, 11, NULL, NULL,
                                                                                        '2018-07-19 13:03:41', 0, 1, 0,
   '2018-07-19 13:03:41', NULL);

/*Table structure for table `work_order` */

DROP TABLE IF EXISTS `work_order`;

CREATE TABLE `work_order` (
  `id`                 int(4)      NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  `uid`                int(10)     NOT NULL
  COMMENT '用户id',
  `work_order_type`    tinyint(1)  NOT NULL
  COMMENT '工单类型，0-交易，1-账户，2-投诉，3-建议',
  `theme`              varchar(32) NOT NULL
  COMMENT '主题',
  `content`            text        NOT NULL
  COMMENT '内容',
  `attach_url`         varchar(128)         DEFAULT NULL
  COMMENT '附件图片url',
  `communicate_record` text COMMENT '交流记录。以HTML的形式存储，前端拿到后直接渲染',
  `status`             tinyint(1)  NOT NULL DEFAULT '0'
  COMMENT '工单状态，0-提交，2-处理中，3-处理完成',
  `create_time`        timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '记录创建时间',
  `update_time`        timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_uid_type` (`uid`, `work_order_type`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `work_order` */

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;
