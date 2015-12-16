/*
Navicat MySQL Data Transfer

Source Server         : 115.29.140.31
Source Server Version : 50542
Source Host           : 115.29.140.31:3306
Source Database       : xn_idauth

Target Server Type    : MYSQL
Target Server Version : 50542
File Encoding         : 65001

Date: 2015-10-23 10:14:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `gateway_idauth_log`
-- ----------------------------
DROP TABLE IF EXISTS `gateway_idauth_log`;
CREATE TABLE `gateway_idauth_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 PK',
  `userId` varchar(50) NOT NULL COMMENT 'userId',
  `systemId` varchar(20) NOT NULL COMMENT '调用系统Id',
  `realName` varchar(20) NOT NULL COMMENT '用户真实姓名',
  `idKind` varchar(1) NOT NULL COMMENT '证件类型 1:身份证 2：其他',
  `idNo` varchar(20) NOT NULL COMMENT '证件号码',
  `status` bit(1) NOT NULL COMMENT '调用状态:0失败,1成功',
  `verify_state` bit(1) DEFAULT NULL COMMENT '验证结果:0失败,1一致',
  `verify_msg` varchar(500) DEFAULT NULL COMMENT '验证结果错误信息',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `ip` varchar(60) DEFAULT NULL COMMENT 'ip',
  `createtime` timestamp NULL DEFAULT NULL COMMENT '创建时间(yyyy-MM-dd hh:mm:ss)',
  `creator` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modifytime` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `modifier` varchar(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='身份认证调用日志表';

