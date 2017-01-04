

DROP TABLE IF EXISTS `gateway_idauth_log`;
CREATE TABLE `gateway_idauth_log` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键 PK',
  `systemId` varchar(2) NOT NULL COMMENT '系统Id',
  `userId` varchar(32) NOT NULL COMMENT 'userId',
  `idKind` char(1) NOT NULL COMMENT '证件类型 1:身份证 ',
  `idNo` varchar(32) NOT NULL COMMENT '证件号码',
  `realName` varchar(16) NOT NULL COMMENT '真实姓名',
  `card_no` varchar(255) DEFAULT NULL COMMENT '银行卡号',
  `bind_mobile` varchar(32) DEFAULT NULL COMMENT '绑定手机号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `error_code` varchar(16) NOT NULL COMMENT '三方验证结果',
  `error_msg` varchar(255) NOT NULL COMMENT '三方验证信息',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='身份认证调用日志表';

DROP TABLE IF EXISTS `tjc_idauth`;
CREATE TABLE `tjc_idauth` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键 PK',
  `systemId` varchar(2) NOT NULL COMMENT '系统Id',
  `userId` varchar(32) NOT NULL COMMENT 'userId',
  `idKind` char(1) NOT NULL COMMENT '证件类型 1:身份证 ',
  `idNo` varchar(32) NOT NULL COMMENT '证件号码',
  `realName` varchar(16) NOT NULL COMMENT '真实姓名',
  `card_no` varchar(255) DEFAULT NULL COMMENT '银行卡号',
  `bind_mobile` varchar(32) DEFAULT NULL COMMENT '绑定手机号',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='身份认证结果表';

DROP TABLE IF EXISTS `tstd_user_picture`;
CREATE TABLE `tstd_user_picture` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL,
  `status` varchar(1) DEFAULT NULL,
  `id_kind` varchar(1) NOT NULL,
  `id_no` varchar(32) NOT NULL,
  `real_name` varchar(16) NOT NULL,
  `id_pic1` varchar(255) NOT NULL,
  `id_pic2` varchar(255) NOT NULL,
  `id_user_pic` varchar(255) NOT NULL,
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `verify_user` varchar(32) DEFAULT NULL,
  `verify_datetime` datetime DEFAULT NULL COMMENT '认证时间',
  `remark` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
