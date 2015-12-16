-- MySQL dump 10.13  Distrib 5.6.24, for osx10.8 (x86_64)
--
-- Host: 121.43.101.148    Database: develop_idauth
-- ------------------------------------------------------
-- Server version	5.5.45

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `gateway_idauth_log`
--

DROP TABLE IF EXISTS `gateway_idauth_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gateway_idauth_log` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键 PK',
  `systemId` varchar(2) NOT NULL COMMENT '系统Id',
  `userId` varchar(32) NOT NULL COMMENT 'userId',
  `idKind` char(1) NOT NULL COMMENT '证件类型 1:身份证 ',
  `idNo` varchar(32) NOT NULL COMMENT '证件号码',
  `realName` varchar(16) NOT NULL COMMENT '真实姓名',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `error_code` varchar(16) NOT NULL COMMENT '三方验证结果',
  `error_msg` varchar(255) NOT NULL COMMENT '三方验证信息',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='身份认证调用日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tjc_idauth`
--

DROP TABLE IF EXISTS `tjc_idauth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tjc_idauth` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键 PK',
  `systemId` varchar(2) NOT NULL COMMENT '系统Id',
  `userId` varchar(32) NOT NULL COMMENT 'userId',
  `idKind` char(1) NOT NULL COMMENT '证件类型 1:身份证 ',
  `idNo` varchar(32) NOT NULL COMMENT '证件号码',
  `realName` varchar(16) NOT NULL COMMENT '真实姓名',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='身份认证结果表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-06 10:44:03
