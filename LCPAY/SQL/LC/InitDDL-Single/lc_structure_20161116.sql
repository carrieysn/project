CREATE DATABASE  IF NOT EXISTS `cifpay_lc_platform` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cifpay_lc_platform`;
-- MySQL dump 10.13  Distrib 5.7.12, for linux-glibc2.5 (x86_64)
--
-- Host: 192.168.163.2    Database: cifpay_lc_platform
-- ------------------------------------------------------
-- Server version	5.6.28-log

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
-- Table structure for table `CIFPAY_GUID_WORKER_ID`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_GUID_WORKER_ID` (
  `MACHINE_ID` varchar(32) NOT NULL COMMENT '用于表示该机器节点的唯一ID，例如对机器名的MD5值',
  `APP_INSTANCE_ID` varchar(32) NOT NULL COMMENT '用于表示该应用实例在该机器节点的唯一ID，例如对某个文件路径的MD5值',
  `WORKER_ID` int(10) unsigned NOT NULL COMMENT '该应用被分配到的Snowflake worker id，支持0到1023的整数值',
  `MACHINE_NAME` varchar(256) NOT NULL COMMENT '机器名称',
  `APP_BINARY_PATH` varchar(2048) DEFAULT NULL COMMENT '可选，用于记录该应用实现的二进制文件所在磁盘路径',
  `INITIALIZED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '该应用节点的首次初始化日期',
  `APP_LAST_STARTED_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '该应用节点最近一次启动的日期',
  PRIMARY KEY (`MACHINE_ID`,`APP_INSTANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于分配和登记所有应用节点的Snowflake Worker Id，该Worker Id提供给Snowflake算法用于生成各数据表的主键ID值';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CIFPAY_LC`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_LC` (
  `LC_ID` bigint(20) NOT NULL COMMENT '银信证ID',
  `BATCH_NO` varchar(64) DEFAULT NULL COMMENT '批次号，批量开证时使用',
  `PRODUCT_ID` bigint(20) NOT NULL COMMENT '银信证产品ID',
  `PRODUCT_CODE` varchar(32) NOT NULL COMMENT '银信证产品代码',
  `LC_NO` varchar(512) DEFAULT NULL COMMENT '银信证展示编号',
  `LC_TYPE` varchar(16) DEFAULT NULL COMMENT '银信证类型：CP300、CP500、CP700',
  `LC_CURRENCY` varchar(8) DEFAULT NULL COMMENT '币种：CHY',
  `LC_AMOUNT` decimal(19,2) DEFAULT NULL COMMENT '银信证开证总额(单位分)',
  `LC_FREEZING_AMOUNT` decimal(19,2) DEFAULT '0.00' COMMENT '银信证冻结金额(单位分)，系统冻结金额，而非银行的冻结金额，包含已履约和已申请解付的金额',
  `LC_BALANCE` decimal(19,2) DEFAULT NULL COMMENT '银信证可用余额(单位分)',
  `LC_PAY_CHANNEL` varchar(32) DEFAULT NULL COMMENT '支付渠道',
  `MID` varchar(32) DEFAULT NULL COMMENT '商户号',
  `ORDER_ID` varchar(128) DEFAULT NULL COMMENT '订单号',
  `ORDER_CONTENT` varchar(256) DEFAULT NULL,
  `PAYER_ID` varchar(32) DEFAULT NULL COMMENT '开证人标识信息',
  `PAYER_ACCNO` varchar(32) DEFAULT NULL COMMENT '银行卡号、支付宝账号',
  `PAYER_TYPE` varchar(16) DEFAULT NULL COMMENT '开证人类型：个人:PERSONAL  企业:ENTERPRISE',
  `PAYER_BANK_CODE` varchar(32) DEFAULT NULL COMMENT '开证人银行代码：ICBC、ALIPAY',
  `PAYER_BANK_NAME` varchar(128) DEFAULT NULL COMMENT '开证人银行名称',
  `PAYER_MOBILE` varchar(32) DEFAULT NULL,
  `RECV_ID` varchar(32) DEFAULT NULL COMMENT '收证人信息',
  `RECV_ACCNO` varchar(32) DEFAULT NULL COMMENT '银行卡号、支付宝账号',
  `RECV_TYPE` varchar(16) DEFAULT NULL COMMENT '收证人类型：个人:PERSONAL  企业:ENTERPRISE',
  `RECV_BANK_CODE` varchar(32) DEFAULT NULL COMMENT '机构代码：工商银行、平安银行、支付宝',
  `RECV_BANK_NAME` varchar(128) DEFAULT NULL COMMENT '收证人银行名称',
  `RECV_MOBILE` varchar(32) DEFAULT NULL,
  `LC_STATUS` varchar(2) DEFAULT NULL COMMENT '银信证状态：10已开证、20已收证、30已履约、31部分履约、32已展期、40已申请解付、41已刹车、50已执行解付、88已解付完成、90已解冻退回、91预失效',
  `RECV_VALID_TIME` timestamp NULL DEFAULT NULL COMMENT '收证有效期',
  `SEND_VALID_TIME` timestamp NULL DEFAULT NULL COMMENT '履约有效期',
  `CONFIRM_VALID_TIME` timestamp NULL DEFAULT NULL COMMENT '申请解付有效期',
  `PAY_VALID_TIME` timestamp NULL DEFAULT NULL COMMENT '执行解付有效期（到期执行解付）',
  `LC_STATE_RETURN_URL` varchar(512) DEFAULT NULL COMMENT '银信证开证回调URL',
  `LC_STATE_NOTIFY_URL` varchar(512) DEFAULT NULL COMMENT '银信证状态变更通知URL',
  `LC_ORDER_DETAIL_URL` varchar(512) DEFAULT NULL COMMENT '银信证商户订单详情URL',
  `THIRD_PARTY_CODE` varchar(64) DEFAULT NULL COMMENT '第三方认证机构代码',
  `PAY_TYPE` varchar(16) DEFAULT '1' COMMENT '解付类型：SINGLE单次解付、MULTIPLE同收款人多次解付、',
  `IS_VALID` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否有效，1有效，0无效',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `REMARK` varchar(512) DEFAULT '1' COMMENT '备注',
  PRIMARY KEY (`LC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银信证主表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CIFPAY_LC_BANK`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_LC_BANK` (
  `BANK_CODE` varchar(32) NOT NULL COMMENT '机构代码',
  `BANK_NAME` varchar(128) DEFAULT NULL COMMENT '机构名称',
  `BANK_TYPE` varchar(32) DEFAULT NULL COMMENT '类型：BANK银行、THIRD_PARTY第三方',
  `IS_VALID` int(11) NOT NULL DEFAULT '0' COMMENT '启用状态：0 待开通，1 正常，2 关闭',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`BANK_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银信证机构代码定义表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CIFPAY_LC_CONFIRM_PAY`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_LC_CONFIRM_PAY` (
  `LC_CONFIRM_ID` bigint(20) NOT NULL COMMENT '申请解付记录ID',
  `LC_SEND_ID` bigint(20) NOT NULL COMMENT '履约记录ID',
  `LC_ID` bigint(20) DEFAULT NULL COMMENT '银信证ID',
  `BATCH_NO` varchar(64) DEFAULT NULL COMMENT '批次编号，批量提现时使用',
  `LC_BALANCE` decimal(19,2) DEFAULT NULL COMMENT '银信证余额（单位分）',
  `LC_PAY_AMOUNT` decimal(19,2) DEFAULT NULL COMMENT '申请解付金额（单位分）',
  `MID` varchar(32) DEFAULT NULL COMMENT '商户号',
  `ORDER_ID` varchar(128) DEFAULT NULL COMMENT '订单号',
  `VALID_TIME` timestamp NULL DEFAULT NULL COMMENT '申请解付有效期时长（到期执行解付）',
  `SIGN_CODE` varchar(128) DEFAULT NULL COMMENT '申请解付凭证',
  `CONFIRM_STATUS` varchar(32) DEFAULT NULL COMMENT '处理结果：申请解付成功、失败、0处理中',
  `PROCESS_STATUS` int(11) DEFAULT NULL COMMENT '流程状态：0待处理、50已由执行解付处理、41已由刹车处理、90已由失效处理',
  `REMARK` varchar(256) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请解付创建日期',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`LC_CONFIRM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银信证申请解付记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CIFPAY_LC_INVALID`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_LC_INVALID` (
  `LC_INVALID_ID` bigint(20) NOT NULL COMMENT '失效记录ID',
  `LC_ID` bigint(20) DEFAULT NULL COMMENT '银信证ID',
  `INVALID_TYPE` varchar(32) DEFAULT NULL COMMENT '失效类型：1=撤回失效、2=退回失效、3=强制失效（我方客服操作）、4=到期失效',
  `LC_AMOUNT` decimal(19,2) DEFAULT NULL COMMENT '银信证金额（单位分）',
  `LC_BALANCE` decimal(19,2) DEFAULT NULL COMMENT '银信证余额（单位分）',
  `INVALID_AMOUNT` decimal(19,2) DEFAULT NULL COMMENT '失效金额（单位分）',
  `LC_INVALID_STATUS` varchar(32) DEFAULT NULL COMMENT '失效状态：处理中、成功、失败、不确定',
  `LC_INVALID_RESPONSE` varchar(256) DEFAULT NULL COMMENT '失效结果：银行接口返回的错误/成功信息',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`LC_INVALID_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银信证失效记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CIFPAY_LC_LOG`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_LC_LOG` (
  `LOG_ID` bigint(20) NOT NULL COMMENT '记录ID',
  `LC_ID` bigint(20) NOT NULL COMMENT '银信证ID',
  `STEP_LOG_ID` bigint(20) DEFAULT NULL COMMENT '银信证过程表的id，例如openid，sendid',
  `TRADE_CODE` varchar(32) DEFAULT NULL COMMENT '交易代码，初始化证INIT，开证 OPEN，收证RECV，履约 APPOINTMENT，申请解付 APPLY，解付 TRANSFER，展期 DEFER，刹车 SUSPEND，定时任务 TIMERTASK',
  `LC_STATUS` varchar(32) DEFAULT NULL COMMENT '银信证状态，10开证、20已收证、30已履约、31已展期、40已申请解付、41已刹车、50已执行解付、88已解付完成、99已失效',
  `LC_RESPONSE` varchar(256) DEFAULT NULL COMMENT '银信证交易结果',
  `REMARK` varchar(256) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银信证交易流水表，每次银信证状态改变时，记录日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CIFPAY_LC_OPEN`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_LC_OPEN` (
  `LC_OPEN_ID` bigint(20) NOT NULL COMMENT '开证记录ID',
  `LC_ID` bigint(20) NOT NULL COMMENT '银信证ID',
  `LC_BATCH_ID` bigint(20) DEFAULT NULL COMMENT '银信证批次ID',
  `MID` varchar(32) DEFAULT NULL COMMENT '商户号',
  `ORDER_ID` varchar(128) DEFAULT NULL COMMENT '订单号',
  `LC_CURRENCY` varchar(8) DEFAULT NULL COMMENT '银信证币种',
  `LC_AMOUNT` decimal(19,2) DEFAULT NULL COMMENT '银信证开证总额(单位分)',
  `PAYER_ID` varchar(32) DEFAULT NULL COMMENT '开证人标识信息',
  `PAYER_ACCNO` varchar(32) DEFAULT NULL COMMENT '银行卡号、支付宝账号',
  `PAYER_TYPE` varchar(16) DEFAULT NULL COMMENT '收证人类型：1:个人 2:企业',
  `PAYER_BANK_CODE` varchar(32) DEFAULT NULL COMMENT '开证人银行代码：ICBC、ALIPAY',
  `PAYER_BANK_NAME` varchar(128) DEFAULT NULL COMMENT '开证人银行名称',
  `PAYER_MOBILE` varchar(32) DEFAULT NULL,
  `RECV_ID` varchar(32) DEFAULT NULL COMMENT '收证人信息',
  `RECV_ACCNO` varchar(32) DEFAULT NULL COMMENT '银行卡号、支付宝账号',
  `RECV_TYPE` varchar(16) DEFAULT NULL COMMENT '收证人类型：1:个人 2:企业',
  `RECV_BANK_CODE` varchar(32) DEFAULT NULL COMMENT '工商银行、平安银行、支付宝',
  `RECV_BANK_NAME` varchar(128) DEFAULT NULL COMMENT '开证人银行名称',
  `RECV_MOBILE` varchar(32) DEFAULT NULL,
  `VALID_TIME` timestamp NULL DEFAULT NULL COMMENT '开证有效期',
  `UNION_TXNTIME` varchar(32) DEFAULT NULL COMMENT '订单发送时间(格式YYYYMMDDhhmmss)',
  `UNION_SERIAL_NO` varchar(64) DEFAULT NULL,
  `REMARK` varchar(256) DEFAULT NULL COMMENT '备注',
  `TRADE_TIME` timestamp NULL DEFAULT NULL COMMENT '开证处理日期',
  `LC_OPEN_CHANNEL` varchar(32) DEFAULT NULL COMMENT '账务接口渠道类型：银联',
  `LC_OPEN_STATUS` varchar(32) DEFAULT NULL COMMENT '开证状态：0处理中、1成功、2失败、3不确定、4通知商户成功 5通知商户失败',
  `LC_OPEN_RESPONSE` varchar(2560) DEFAULT NULL COMMENT '开证结果：银行接口返回的错误/成功信息',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`LC_OPEN_ID`),
  KEY `idx_lc_pen_lcid` (`LC_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银信证开证记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CIFPAY_LC_OPEN_BATCH`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_LC_OPEN_BATCH` (
  `BATCH_OPEN_ID` bigint(20) NOT NULL COMMENT '批次号',
  `MID` varchar(32) DEFAULT NULL COMMENT '商户号',
  `ORDER_ID` varchar(128) DEFAULT NULL COMMENT '批次订单号',
  `LC_CURRENCY` varchar(50) DEFAULT NULL COMMENT '币种：CHY',
  `LC_BATCH_AMOUNT` decimal(19,2) DEFAULT NULL COMMENT '批次开证总额(单位分)',
  `LC_BATCH_BALANCE` decimal(19,2) DEFAULT NULL COMMENT '批次开证余额',
  `PAYER_ID` varchar(32) DEFAULT NULL COMMENT '开证人标识信息',
  `PAYER_ACCNO` varchar(32) DEFAULT NULL COMMENT '银行卡号、支付宝账号',
  `PAYER_TYPE` varchar(16) DEFAULT NULL COMMENT '收证人类型：1:个人 2:企业',
  `PAYER_BANK_CODE` varchar(32) DEFAULT NULL COMMENT '开证人银行代码：ICBC、ALIPAY',
  `PAYER_BANK_NAME` varchar(128) DEFAULT NULL COMMENT '开证人银行名称',
  `REMARK` varchar(256) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`BATCH_OPEN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='批量开证记录表，保存批量开证时的操作结果';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CIFPAY_LC_PAGE`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_LC_PAGE` (
  `LC_ID` bigint(20) NOT NULL COMMENT '银信证ID',
  `VERSION` varchar(10) DEFAULT NULL COMMENT '版本号',
  `PAGE_URI` varchar(256) DEFAULT NULL COMMENT '银信证静态资源ID',
  `STORE_TYPE` varchar(30) DEFAULT NULL COMMENT '存储方式，fastdfs、阿里云等',
  `STORE_PATH` varchar(256) DEFAULT NULL COMMENT '存储的物理路径',
  `STORE_GROUP` varchar(30) DEFAULT NULL COMMENT 'fastdfs需要有group',
  `TRAN_STATUS` int(1) NOT NULL DEFAULT '0' COMMENT '处理标志，0=待处理；1=处理中；2=处理成功，3=处理失败',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `GEN_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '生成静态资源时间',
  `LOCKED_BY_NODE` varchar(256) DEFAULT NULL COMMENT '当前锁定该记录的应用节点ID',
  PRIMARY KEY (`LC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银信证证化页面资源';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CIFPAY_LC_PAGE_TEMPLATE`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_LC_PAGE_TEMPLATE` (
  `TEMPLATE_CODE` varchar(50) NOT NULL COMMENT '模板编号',
  `VERSION` varchar(20) NOT NULL COMMENT '版本号',
  `TRAN_STATUS` int(1) NOT NULL DEFAULT '0' COMMENT '处理状态，0=无进程占用处理；1=处理中',
  `LOCKED_BY_NODE` varchar(256) DEFAULT NULL COMMENT '当前锁定该记录的应用节点ID',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`TEMPLATE_CODE`,`VERSION`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='证化页面模板表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CIFPAY_LC_PAGE_TEMPLATE_DETAIL`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_LC_PAGE_TEMPLATE_DETAIL` (
  `TEMPLATE_CODE` varchar(50) NOT NULL COMMENT '模板编号',
  `VERSION` varchar(20) NOT NULL COMMENT '版本号',
  `TEMPLATE_FILE_NAME` varchar(50) NOT NULL COMMENT '模板文件名称',
  `TEMPLATE_FILE_PATH` varchar(512) NOT NULL COMMENT '原始模板文件全路径',
  `SYS_FILE_PATH` varchar(512) DEFAULT NULL COMMENT '上传后文件全路径',
  `ITEM_NAME` varchar(64) DEFAULT NULL COMMENT '在模板文件中的唯一标识名称，##name##',
  `ITEM_CATEGORY` varchar(64) DEFAULT NULL COMMENT '分类，非静态引入资源与业务相关。如果此项不为空则ITEM_NAME与字典项相对应',
  `SEQ` int(11) DEFAULT NULL COMMENT '【重要】次序号，后上传的模板文件里面可能引用先上传的文件路径。通常情况下图片资源要先上传，然后css和js，最后才是网页模板',
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`TEMPLATE_CODE`,`VERSION`,`TEMPLATE_FILE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='证化页面模板明细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CIFPAY_LC_PAY`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_LC_PAY` (
  `LC_PAY_ID` bigint(20) NOT NULL COMMENT '解付记录ID',
  `LC_CONFIRM_ID` bigint(20) NOT NULL COMMENT '申请解付记录ID',
  `LC_ID` bigint(20) DEFAULT NULL COMMENT '银信证ID',
  `MID` varchar(32) DEFAULT NULL,
  `ORDER_ID` varchar(128) DEFAULT NULL COMMENT '订单号',
  `TOTAL_AMOUNT` decimal(19,2) DEFAULT NULL COMMENT '解付金额（单位分）',
  `TRADE_TIME` timestamp NULL DEFAULT NULL COMMENT '解付处理日期',
  `VALID_TIME` timestamp NULL DEFAULT NULL COMMENT '解付有效期',
  `REMARK` varchar(256) DEFAULT NULL COMMENT '备注',
  `LC_PAY_STATUS` varchar(32) DEFAULT NULL COMMENT '解付状态：处理中0、成功1、失败2、不确定3',
  `LC_PAY_RESPONSE` varchar(256) DEFAULT NULL COMMENT '解付结果：银行接口返回的错误/成功信息',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`LC_PAY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银信证执行解付记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CIFPAY_LC_PRODUCT`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_LC_PRODUCT` (
  `PRODUCT_ID` bigint(20) NOT NULL COMMENT '银信证产品ID',
  `PRODUCT_CODE` varchar(32) NOT NULL COMMENT '银信证产品代码',
  `PRODUCT_NAME` varchar(32) DEFAULT NULL COMMENT '银信证产品名称',
  `PRODUCT_DESCRIPTION` varchar(256) DEFAULT NULL COMMENT '银信证产品描述',
  `LC_TYPE` varchar(16) DEFAULT NULL COMMENT '银信证类型',
  `LC_CURRENCY` varchar(8) DEFAULT NULL COMMENT '银信证币种',
  `LC_AMOUNT_MIN` decimal(19,2) DEFAULT NULL COMMENT '银信证最小开证总额(单位分)',
  `LC_AMOUNT_MAX` decimal(19,2) DEFAULT NULL COMMENT '银信证最大开证总额(单位分)',
  `LC_STANDARD` bit(1) DEFAULT NULL COMMENT '银信证标准证：标准证不可以修改177的有效日期',
  `PAYER_TYPE` varchar(16) DEFAULT NULL COMMENT '开证人类别：10个人、20企业、30全部',
  `PAYER_ACCNO_TYPE` varchar(16) DEFAULT NULL COMMENT '支付账号类别：借记卡、信用卡、全部',
  `RECV_TYPE` varchar(16) DEFAULT NULL COMMENT '收证人类别：10个人、20企业、30全部',
  `THIRD_PARTY_TYPE` varchar(16) DEFAULT NULL COMMENT '第三方认证机构类别：企业、个人、指定机构',
  `THIRD_PARTY_CODE` varchar(32) DEFAULT NULL COMMENT '第三方认证机构代码',
  `LC_INVALID_HANDLE_CHANNEL` varchar(1) DEFAULT '9' COMMENT '银信证失效处理渠道，1=银行标准；9=其他，（暂时保留，用于区分星意和标准版）',
  `ALLOW_MULTIPLE_OPEN` bit(1) DEFAULT NULL COMMENT '是否支持批量开证',
  `ALLOW_PARTIAL_PAY` bit(1) DEFAULT NULL COMMENT '是否支持部分解付',
  `AUTO_OPEN` bit(1) DEFAULT NULL COMMENT '自动开证',
  `AUTO_RECV` bit(1) DEFAULT NULL COMMENT '自动收证',
  `AUTO_SEND` bit(1) DEFAULT NULL COMMENT '自动履约',
  `AUTO_CONFIRM` bit(1) DEFAULT NULL COMMENT '自动申请解付',
  `AUTO_PAY` bit(1) DEFAULT NULL COMMENT '自动执行解付',
  `SHOW_RECV` bit(1) DEFAULT NULL COMMENT '显示收证节点（UI）',
  `SHOW_SEND` bit(1) DEFAULT NULL COMMENT '显示履约节点（UI）',
  `SHOW_CONFIRM` bit(1) DEFAULT NULL COMMENT '显示申请解付节点（UI）',
  `SHOW_SUCCESS` bit(1) DEFAULT NULL COMMENT '显示资金到账节点（UI）',
  `DISPLAY_NAME_OPEN` varchar(64) DEFAULT NULL COMMENT '开证名称（UI）',
  `DISPLAY_NAME_RECV` varchar(64) DEFAULT NULL COMMENT '收证名称（UI）',
  `DISPLAY_NAME_SEND` varchar(64) DEFAULT NULL COMMENT '履约名称（UI）',
  `DISPLAY_NAME_CONFIRM` varchar(64) DEFAULT NULL COMMENT '申请解付名称（UI）',
  `DISPLAY_NAME_PAY` varchar(64) DEFAULT NULL COMMENT '执行解付名称（UI）',
  `DISPLAY_NAME_SUCCESS` varchar(64) DEFAULT NULL COMMENT '资金到账名称（UI）',
  `VALID_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效日期',
  `PRODUCT_STATUS` varchar(16) NOT NULL DEFAULT '0' COMMENT '0未生效、1已生效、2已停用、3审批中',
  `DEL_FLAG` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标识',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改日期',
  `IS_TEMPLATE` bit(1) DEFAULT NULL COMMENT '是否模板',
  `TEMPLATE_NAME` varchar(32) DEFAULT NULL COMMENT '模板名称',
  `TEMPLATE_DESCRIPTION` varchar(256) DEFAULT NULL COMMENT '模板描述',
  PRIMARY KEY (`PRODUCT_ID`),
  UNIQUE KEY `idx_lc_product_productcode` (`PRODUCT_CODE`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银信证产品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CIFPAY_LC_RECV`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_LC_RECV` (
  `LC_RECV_ID` bigint(20) NOT NULL COMMENT '收证记录ID',
  `LC_ID` bigint(20) DEFAULT NULL COMMENT '银信证ID',
  `MID` varchar(32) DEFAULT NULL COMMENT '商户号',
  `RECV_ID` varchar(32) DEFAULT NULL COMMENT '收证人信息',
  `RECV_ACCNO` varchar(32) DEFAULT NULL COMMENT '银行卡号、支付宝账号',
  `RECV_TYPE` varchar(16) DEFAULT NULL COMMENT '收证人类型：1:个人 2:企业',
  `RECV_BANK_CODE` varchar(32) DEFAULT NULL COMMENT '收证方机构代码：工商银行、平安银行、支付宝',
  `RECV_BANK_NAME` varchar(128) DEFAULT NULL COMMENT '开证人银行名称',
  `RECV_MOBILE` varchar(32) DEFAULT NULL,
  `VALID_TIME` timestamp NULL DEFAULT NULL COMMENT '收证有效期',
  `REMARK` varchar(256) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`LC_RECV_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银信证收证记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CIFPAY_LC_RECV_BATCH`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_LC_RECV_BATCH` (
  `BATCH_RECV_ID` bigint(20) NOT NULL,
  `MID` varchar(32) DEFAULT NULL COMMENT '商户号',
  `RECV_ID` varchar(32) DEFAULT NULL COMMENT '收证人信息',
  `RECV_ACCNO` varchar(32) DEFAULT NULL COMMENT '银行卡号、支付宝账号',
  `RECV_TYPE` varchar(16) DEFAULT NULL COMMENT '收证人类型：1:个人 2:企业',
  `RECV_BANK_CODE` varchar(32) DEFAULT NULL COMMENT '收证方机构代码：工商银行、平安银行、支付宝',
  `RECV_BANK_NAME` varchar(128) DEFAULT NULL COMMENT '开证人银行名称',
  `VALID_TIME` timestamp NULL DEFAULT NULL COMMENT '收证有效期',
  `REMARK` varchar(256) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`BATCH_RECV_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CIFPAY_LC_REFUND`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_LC_REFUND` (
  `LC_REFUND_ID` bigint(20) NOT NULL,
  `LC_ID` bigint(20) NOT NULL COMMENT '银信证ID',
  `MID` varchar(256) NOT NULL COMMENT '商户ID',
  `REFUND_ORDER_ID` varchar(256) NOT NULL COMMENT '商户退款订单号（由商户提供，不可为空，同商户不可重复）',
  `ORDER_ID` varchar(256) NOT NULL COMMENT '订单ID',
  `REFUND_AMOUNT` decimal(19,2) NOT NULL COMMENT '退款金额（分）',
  `REFUND_STATUS` int(11) DEFAULT NULL COMMENT '退款状态0处理中、1成功、2失败、3不确定',
  `REFUND_DATE` timestamp NULL DEFAULT NULL COMMENT '退款处理日期',
  `REMARK` varchar(256) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`LC_REFUND_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银信证退款表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CIFPAY_LC_SEND`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_LC_SEND` (
  `LC_SEND_ID` bigint(20) NOT NULL COMMENT '履约记录ID',
  `LC_ID` bigint(20) NOT NULL COMMENT '银信证ID',
  `MID` varchar(32) DEFAULT NULL COMMENT '商户号',
  `ORDER_ID` varchar(128) DEFAULT NULL COMMENT '订单号',
  `ORDER_AMOUNT` decimal(19,2) DEFAULT NULL COMMENT '订单金额（单位分）',
  `ORDER_CONTENT` varchar(1024) DEFAULT NULL COMMENT '订单内容，例如快递信息等',
  `LC_CONFIRM_AMOUNT` decimal(19,2) DEFAULT NULL COMMENT '履约金额（单位分）',
  `LC_BALANCE` decimal(19,2) DEFAULT NULL COMMENT '银信证当前余额（单位分）',
  `SEND_TYPE` varchar(64) DEFAULT NULL COMMENT '履约方式：CP300、CP500、CP700',
  `SEND_SIGN_CODE` varchar(64) DEFAULT NULL COMMENT '履约验证码',
  `SEND_ORG_ID` varchar(64) DEFAULT NULL COMMENT '履约机构',
  `SEND_ORDER_ID` varchar(64) DEFAULT NULL COMMENT '履约订单号',
  `SEND_STATUS` varchar(32) DEFAULT NULL COMMENT '处理结果：0处理中、1处理成功、2处理失败、3不确定',
  `PROCESS_STATUS` int(11) DEFAULT NULL COMMENT '流程状态：0待处理、32已由展期处理、40已由申请解付处理、90已由失效处理',
  `SEND_TIME` timestamp NULL DEFAULT NULL COMMENT '履约日期',
  `VALID_TIME` timestamp NULL DEFAULT NULL COMMENT '履约有效期',
  `REMARK` varchar(256) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`LC_SEND_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银信证履约记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CIFPAY_LC_SEND_ORG`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_LC_SEND_ORG` (
  `ORG_ID` varchar(64) NOT NULL COMMENT '履约机构ID',
  `ORG_NAME` varchar(128) DEFAULT NULL COMMENT '履约机构名称',
  `ITF_URL` varchar(1024) DEFAULT NULL COMMENT '接口调用地址',
  `FINISH_REGEX` varchar(256) DEFAULT NULL COMMENT '接口返回内容履约完成正则表达式',
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ORG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='履约机构';
/*!40101 SET character_set_client = @saved_cs_client */;

-- Table structure for table `CIFPAY_LC_TYPE`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_LC_TYPE` (
  `LC_TYPE` varchar(16) NOT NULL COMMENT '银信证类型',
  `LC_TYPE_DESC` varchar(512) DEFAULT NULL COMMENT '银信证类型描述',
  `MAX_DAYS_TO_RECEIVE` int(11) DEFAULT NULL COMMENT '默认收证期（天）',
  `MAX_DAYS_TO_SEND` int(11) DEFAULT NULL COMMENT '默认履约期（天）',
  `MAX_DAYS_TO_CONFIRM_PAY` int(11) DEFAULT NULL COMMENT '默认签收期（天）',
  `MAX_DAYS_TO_PAY` int(11) DEFAULT NULL COMMENT '默认解付期（天）',
  `IS_VALID` int(11) NOT NULL DEFAULT '0' COMMENT '启用状态：0 待开通，1 正常，2 已关闭',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  PRIMARY KEY (`LC_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银信证类型定义表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CIFPAY_PRE_LC`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_PRE_LC` (
  `LC_ID` bigint(20) NOT NULL COMMENT '预开证银信证ID（同银信证ID一致）',
  `BATCH_NO` varchar(64) DEFAULT NULL COMMENT '批次号',
  `MID` varchar(32) NOT NULL COMMENT '商户号',
  `ORDER_ID` varchar(128) DEFAULT NULL COMMENT '订单号',
  `ORDER_CONTENT` varchar(256) DEFAULT NULL COMMENT '订单内容',
  `PRODUCT_ID` bigint(20) NOT NULL COMMENT '银信证产品ID',
  `PRODUCT_CODE` varchar(32) NOT NULL COMMENT '银信证产品代码',
  `LC_NO` varchar(512) DEFAULT NULL COMMENT '银信证展示编号',
  `LC_TYPE` varchar(16) DEFAULT NULL COMMENT '银信证类型：CP300、CP500、CP700',
  `LC_CURRENCY` varchar(8) DEFAULT NULL COMMENT '币种',
  `LC_AMOUNT` decimal(19,2) DEFAULT NULL COMMENT '银信证开证总额(单位分)',
  `PAYER_ID` varchar(32) DEFAULT NULL COMMENT '开证人标识信息',
  `PAYER_ACCNO` varchar(32) DEFAULT NULL COMMENT '银行卡号、支付宝账号',
  `PAYER_TYPE` varchar(16) DEFAULT NULL COMMENT '收证人类型：1:个人 2:企业',
  `PAYER_BANK_CODE` varchar(32) DEFAULT NULL COMMENT '开证人银行代码：ICBC、ALIPAY',
  `PAYER_BANK_NAME` varchar(128) DEFAULT NULL COMMENT '开证人银行名称',
  `PAYER_MOBILE` varchar(32) DEFAULT NULL,
  `RECV_ID` varchar(32) DEFAULT NULL COMMENT '收证人信息',
  `RECV_ACCNO` varchar(32) DEFAULT NULL COMMENT '银行卡号、支付宝账号',
  `RECV_TYPE` varchar(16) DEFAULT NULL COMMENT '收证人类型：1:个人 2:企业',
  `RECV_BANK_CODE` varchar(32) DEFAULT NULL COMMENT '机构代码：工商银行、平安银行、支付宝',
  `RECV_BANK_NAME` varchar(128) DEFAULT NULL COMMENT '开证人银行名称',
  `RECV_MOBILE` varchar(32) DEFAULT NULL,
  `LC_STATUS` varchar(32) DEFAULT NULL COMMENT '银信证状态，00新建、88交易成功、99交易失败',
  `OPEN_VALID_TIME` timestamp NULL DEFAULT NULL COMMENT '开证有效期',
  `RECV_VALID_TIME` timestamp NULL DEFAULT NULL COMMENT '收证有效期',
  `SEND_VALID_TIME` timestamp NULL DEFAULT NULL COMMENT '履约有效期',
  `CONFIRM_VALID_TIME` timestamp NULL DEFAULT NULL COMMENT '申请解付有效期',
  `PAY_VALID_TIME` timestamp NULL DEFAULT NULL COMMENT '执行解付有效期',
  `LC_STATE_RETURN_URL` varchar(512) DEFAULT NULL COMMENT 'UI响应式开证回调URL，若为纯API后台开证，则该字段可选',
  `LC_STATE_NOTIFY_URL` varchar(512) DEFAULT NULL COMMENT '银信证状态变更通知URL',
  `LC_ORDER_DETAIL_URL` varchar(512) DEFAULT NULL COMMENT '银信证商户订单详情URL',
  `THIRD_PARTY_CODE` varchar(32) DEFAULT NULL COMMENT '第三方认证机构代码',
  `PAY_TYPE` varchar(16) DEFAULT NULL COMMENT '解付类型：SINGLE单次解付、MULTIPLE同收款人多次解付、',
  `MER_USERID` varchar(32) DEFAULT NULL,
  `IS_VALID` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否有效',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `REMARK` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`LC_ID`),
  KEY `idx_pre_lc_mid_orderid` (`MID`,`ORDER_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银信证预开证表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CIFPAY_USER_ACCOUNT`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_USER_ACCOUNT` (
  `P_ID` bigint(20) NOT NULL,
  `MID` varchar(32) NOT NULL COMMENT '用户渠道标识',
  `MER_USERID` varchar(32) NOT NULL DEFAULT '' COMMENT '商户用户标识',
  `USER_MOBILE` varchar(32) DEFAULT NULL COMMENT '用户手机号',
  `PAYER_ACCNO` varchar(32) DEFAULT NULL COMMENT '用户银行卡号',
  `ACCNO_TYPE` int(11) DEFAULT NULL COMMENT '0，储蓄卡；1,信用卡；',
  `CREATE_DATE` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`P_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银联商户用户账号关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cifpay_message`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_MESSAGE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `MSG_TYPE` int(5) DEFAULT NULL COMMENT '消息分类(1:银信证)',
  `SCENE` varchar(20) NOT NULL COMMENT '场景',
  `STATUS` int(2) NOT NULL COMMENT '状态（0：待处理 2：处理中）',
  `PARAMS` varchar(2000) NOT NULL COMMENT '参数（json格式）',
  `COUNT` int(2) NOT NULL DEFAULT '0' COMMENT '处理次数',
  `PLAN_TIME` bigint(20) NOT NULL COMMENT '计划开始处理时间(如果为0，则表示需要立刻处理)',
  `LAST_TIME` bigint(20) NOT NULL COMMENT '最后一次处理时间',
  `CREATE_TIME` bigint(20) NOT NULL COMMENT '消息创建时间',
  PRIMARY KEY (`ID`),
  KEY `idx_message_msgtype` (`MSG_TYPE`) USING BTREE,
  KEY `idx_message_msgtype_sence` (`MSG_TYPE`,`SCENE`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5283 DEFAULT CHARSET=utf8 COMMENT='消息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cifpay_message_his`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CIFPAY_MESSAGE_HIS` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `MSG_TYPE` int(5) DEFAULT NULL COMMENT '消息分类',
  `SCENE` varchar(20) NOT NULL COMMENT '场景(A001：预开证 ，A002：开证，A003：收证，A004：履约，A005：展期，A006：申请解付，A007：暂停，A008：解付转账，A009：失效，A010：批量开证，A011：批量提现，，A012：退款)',
  `PARAMS` varchar(2000) NOT NULL COMMENT '参数（json格式）',
  `STATUS` int(2) NOT NULL COMMENT '处理结果(0:成功，1：失败)',
  `COUNT` int(5) NOT NULL DEFAULT '0' COMMENT '处理次数',
  `PLAN_TIME` bigint(20) NOT NULL COMMENT '计划开始处理时间(如果为0，则表示需要立刻处理)',
  `LAST_TIME` bigint(20) NOT NULL COMMENT '最后一次处理时间',
  `CREATE_TIME` bigint(20) NOT NULL COMMENT '消息创建时间',
  `REMARK` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5283 DEFAULT CHARSET=utf8 COMMENT=' 消息历史表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-16 15:11:02
