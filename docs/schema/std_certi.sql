DROP TABLE IF EXISTS `tstd_cpassword`;
CREATE TABLE `tstd_cpassword` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类别',
  `account` varchar(64) DEFAULT NULL COMMENT 'key',
  `password` varchar(64) DEFAULT NULL COMMENT 'value',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置表';

DROP TABLE IF EXISTS `tstd_idauth`;
CREATE TABLE `tstd_idauth` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `id_kind` char(4) NOT NULL COMMENT '证件类型 1:身份证 ',
  `id_no` varchar(32) NOT NULL COMMENT '证件号码',
  `real_name` varchar(16) NOT NULL COMMENT '真实姓名',
  `card_no` varchar(255) DEFAULT NULL COMMENT '银行卡号',
  `bind_mobile` varchar(32) DEFAULT NULL COMMENT '绑定手机号',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='身份认证结果表';


DROP TABLE IF EXISTS `tstd_gateway_idauth_log`;
CREATE TABLE `tstd_gateway_idauth_log` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键 PK',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `id_kind` char(4) NOT NULL COMMENT '证件类型 1:身份证 ',
  `id_no` varchar(32) NOT NULL COMMENT '证件号码',
  `real_name` varchar(16) NOT NULL COMMENT '真实姓名',
  `card_no` varchar(255) DEFAULT NULL COMMENT '银行卡号',
  `bind_mobile` varchar(32) DEFAULT NULL COMMENT '绑定手机号',
  `error_code` varchar(16) NOT NULL COMMENT '三方验证结果',
  `error_msg` varchar(255) NOT NULL COMMENT '三方验证信息',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='三方认证日志表';

DROP TABLE IF EXISTS `tstd_user_picture`;
CREATE TABLE `tstd_user_picture` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键 PK',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `id_kind` char(4) NOT NULL COMMENT '证件类型 1:身份证 ',
  `id_no` varchar(32) NOT NULL COMMENT '证件号码',
  `real_name` varchar(16) NOT NULL COMMENT '真实姓名',
  `id_pic1` varchar(255) NOT NULL COMMENT '正面照',
  `id_pic2` varchar(255) NOT NULL COMMENT '反面照',
  `id_user_pic` varchar(255) NOT NULL COMMENT '手持照',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `verify_user` varchar(32) DEFAULT NULL COMMENT '认证人',
  `verify_datetime` datetime DEFAULT NULL COMMENT '认证时间',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人工认证日志表';
