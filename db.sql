/*
Navicat MySQL Data Transfer

Source Server         : 192.168.163.32
Source Server Version : 50505
Source Host           : 192.168.163.32:3306
Source Database       : cifpay_lc_test

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-02-15 17:45:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_bank_expenses
-- ----------------------------
DROP TABLE IF EXISTS `admin_bank_expenses`;
CREATE TABLE `admin_bank_expenses` (
  `ID` varchar(20) NOT NULL DEFAULT '',
  `BANK_NAME` varchar(64) DEFAULT NULL,
  `BANK_ID` varchar(20) DEFAULT NULL,
  `BANK_PROCEDURE` varchar(8) DEFAULT NULL COMMENT '手续规则 ：比率 ,笔数',
  `PROCEDURE_VALUE` float DEFAULT NULL COMMENT '手续规则对应的值 0~100',
  `CON_ID` varchar(20) DEFAULT NULL,
  `SUM_MONEY` float DEFAULT NULL COMMENT '总金额（元)',
  `SUM_COUNT` int(11) DEFAULT NULL,
  `PAYMENT` float DEFAULT NULL COMMENT '应收手续费（元）',
  `ALREADY_PAY` float DEFAULT NULL COMMENT '实收手续费（元）',
  `ARREARS_PAY` float DEFAULT NULL COMMENT '欠费(元)',
  `BILL_TIME` datetime DEFAULT NULL COMMENT '对账时间',
  `CREATE_TIME` datetime DEFAULT NULL,
  `STATE` tinyint(4) DEFAULT '0' COMMENT '状态',
  `IS_PAY` tinyint(4) DEFAULT '0' COMMENT '是否欠费:0欠费，1没有欠费',
  `STATIS_TIME_S` varchar(8) DEFAULT NULL COMMENT '统计年月',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银行手续费';

-- ----------------------------
-- Table structure for admin_bank_exp_statis
-- ----------------------------
DROP TABLE IF EXISTS `admin_bank_exp_statis`;
CREATE TABLE `admin_bank_exp_statis` (
  `ID` varchar(20) NOT NULL DEFAULT '',
  `BANK_EXP_ID` varchar(20) DEFAULT NULL,
  `EXP_STATIS_NAME` varchar(64) DEFAULT NULL COMMENT '缴费名称',
  `MONEY_PAY` double DEFAULT NULL COMMENT '缴费金额',
  `PAY_TIME` datetime DEFAULT NULL COMMENT '缴费时间',
  `USER_ID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银行手续费缴费明细';

-- ----------------------------
-- Table structure for admin_base_config
-- ----------------------------
DROP TABLE IF EXISTS `admin_base_config`;
CREATE TABLE `admin_base_config` (
  `ID` varchar(20) NOT NULL DEFAULT '',
  `CONFIG_NAME` varchar(32) DEFAULT NULL,
  `CONFIG_TYPE` tinyint(4) DEFAULT NULL,
  `CONFIG_VAL` varchar(1024) DEFAULT NULL,
  `CONGIG_STATE` tinyint(4) DEFAULT '0' COMMENT '0:有效，1：无效',
  `CREATE_TIME` datetime DEFAULT NULL,
  `CONFIG_CODE` varchar(8) DEFAULT NULL COMMENT '编码',
  `USER_ID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置表';

-- ----------------------------
-- Table structure for admin_cifpay_lc_bank
-- ----------------------------
DROP TABLE IF EXISTS `admin_cifpay_lc_bank`;
CREATE TABLE `admin_cifpay_lc_bank` (
  `ID` varchar(20) NOT NULL DEFAULT '',
  `BANK_CODE` varchar(32) DEFAULT NULL COMMENT '银行代码',
  `BANK_NAME` varchar(128) DEFAULT NULL COMMENT '银行名称',
  `BANK_TYPE` varchar(32) DEFAULT NULL COMMENT '类型：BANK银行、THIRD_PARTY第三方',
  `IS_VALID` int(11) DEFAULT '0' COMMENT '启用状态：0 待开通，1 正常，2 关闭',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `LANDING_AGENCY` varchar(128) NOT NULL COMMENT '落地机构',
  `BANK_ACCOUNT` varchar(45) NOT NULL COMMENT '银行行号',
  `INTER_BANK` tinyint(1) NOT NULL COMMENT '是否跨行业务 ',
  `ADDRESS` varchar(255) NOT NULL COMMENT '联系地址',
  `CONTACT_NAME` varchar(45) NOT NULL COMMENT '业务联系人',
  `TELEPHONE` varchar(45) DEFAULT NULL COMMENT '联系人电话',
  `FIXED_TELEPHONE` varchar(45) NOT NULL COMMENT '固定电话',
  `EMAIL` varchar(45) NOT NULL COMMENT '联系人邮箱',
  `BANK_STATE` int(11) DEFAULT NULL COMMENT '审核状态 0:未审核，1：已审核，2：审核不通过，3：重新申请',
  `BANK_REVIEW` char(1) DEFAULT NULL COMMENT '银行审核状态：0:未审核，1:审核通过，2：审核不通过',
  `REVIEW_REMARK` varchar(255) DEFAULT NULL COMMENT '审核意见',
  `USER_ID` varchar(20) DEFAULT NULL,
  `BANK_OTHER` varchar(255) DEFAULT NULL COMMENT '其他信息',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNIQUE_BANK_CODE` (`BANK_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银行信息';

-- ----------------------------
-- Table structure for admin_cifpay_lc_bank_log
-- ----------------------------
DROP TABLE IF EXISTS `admin_cifpay_lc_bank_log`;
CREATE TABLE `admin_cifpay_lc_bank_log` (
  `ID` varchar(20) NOT NULL DEFAULT '',
  `BANK_ID` varchar(20) DEFAULT NULL,
  `USER_ID` varchar(20) DEFAULT NULL,
  `USER_NAME` varchar(64) DEFAULT NULL COMMENT '操作人',
  `GROUP_NAME` varchar(64) DEFAULT NULL COMMENT '部门',
  `ACTION_RECORD` varchar(32) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银行操作日志信息';

-- ----------------------------
-- Table structure for admin_cifpay_lc_merchant
-- ----------------------------
DROP TABLE IF EXISTS `admin_cifpay_lc_merchant`;
CREATE TABLE `admin_cifpay_lc_merchant` (
  `MER_ID` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '',
  `MER_CODE` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '商户编码',
  `MERCHANT_NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '商户名称',
  `MER_SITE_NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '商户系统名称',
  `MER_SITE_DOMAIN` varchar(512) CHARACTER SET utf8 DEFAULT NULL COMMENT '商户系统域名',
  `ENCRYPT_TYPE` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '加密方式(MD5/RSA)',
  `ENCRYPT_KEY` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '加密密钥',
  `DECRYPT_KEY` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '解密密钥（RSA方式时存商户的公钥）',
  `DEF_LC_TYPE` varchar(5) COLLATE utf8_bin DEFAULT NULL COMMENT '默认使用的银信证类型',
  `MERCHANT_STATUS` char(1) COLLATE utf8_bin NOT NULL COMMENT '商户状态：0待开通，1正常，2已关闭',
  `CREATED_DT` datetime NOT NULL COMMENT '创建时间',
  `UPDATED_DT` datetime DEFAULT NULL COMMENT '更新时间',
  `MER_TYPE` char(1) CHARACTER SET utf8 NOT NULL DEFAULT '1' COMMENT '商户类型：1-企业类型商户  2-个人类型商户',
  `BUSINESS_LICENSE` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '营业执照注册号',
  `ORGANIZATION_IMG` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '组织机构代码证扫描件,支持jpeg、jpg、png格式',
  `LICENSE_IMG` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '营业执照扫描件,支持jpeg、jpg、png格式',
  `MER_LOGO` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '企业LOGO',
  `BANK_NAME` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '开户银行',
  `ACCOUNT_NAME` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '开户名称',
  `BANK_ACCOUNT` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '银行账户',
  `BANK_CODE` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '银行编码',
  `PERSONAL_NAME` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '个人姓名',
  `IDENTITY_CARD` varchar(19) CHARACTER SET utf8 DEFAULT NULL COMMENT '身份证号',
  `ID_CARD_IMG` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '身份证证扫描件,支持jpeg、jpg、png格式',
  `CONTACT_NAME` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '联系人姓名',
  `TELEPHONE` varchar(11) CHARACTER SET utf8 NOT NULL COMMENT '联系电话',
  `FIXED_TELEPHONE` varchar(16) CHARACTER SET utf8 DEFAULT NULL COMMENT '固定电话',
  `CONTACT_EMAIL` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '联系邮箱',
  `CONTACT_ADDRESS` varchar(256) CHARACTER SET utf8 NOT NULL COMMENT '联系地址',
  `REFEREE` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '推荐人',
  `MER_REVIEW` char(1) CHARACTER SET utf8 NOT NULL COMMENT '商户审核状态：0:未审核，1:审核通过，2：审核不通过',
  `REVIEW_REMARK` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '其他信息',
  `USER_ID` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `REVIEW_DT` datetime DEFAULT NULL COMMENT '激活时间（最新审核通过时间）',
  `TR_ID` varchar(15) CHARACTER SET utf8 DEFAULT NULL COMMENT '标记请求者ID',
  `TOKEN_TYPE` varchar(2) CHARACTER SET utf8 DEFAULT NULL COMMENT '标记类型',
  `MER_OTHER` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '其他信息',
  PRIMARY KEY (`MER_ID`),
  UNIQUE KEY `IDX_LC_MERCHANT_MER_ID` (`MER_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商户信息表';

-- ----------------------------
-- Table structure for admin_cifpay_lc_merchant_log
-- ----------------------------
DROP TABLE IF EXISTS `admin_cifpay_lc_merchant_log`;
CREATE TABLE `admin_cifpay_lc_merchant_log` (
  `ID` varchar(20) NOT NULL DEFAULT '',
  `MER_ID` varchar(20) DEFAULT NULL,
  `USER_ID` varchar(20) DEFAULT NULL,
  `USER_NAME` varchar(64) DEFAULT NULL COMMENT '操作人',
  `GROUP_NAME` varchar(64) DEFAULT NULL COMMENT '部门',
  `ACTION_RECORD` varchar(32) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商户操作日志表';

-- ----------------------------
-- Table structure for admin_cifpay_lc_mer_cre
-- ----------------------------
DROP TABLE IF EXISTS `admin_cifpay_lc_mer_cre`;
CREATE TABLE `admin_cifpay_lc_mer_cre` (
  `ID` varchar(20) NOT NULL DEFAULT '',
  `MER_ID` varchar(20) DEFAULT NULL,
  `MER_CODE` varchar(64) DEFAULT NULL COMMENT '商户code',
  `XN_MER_ID` varchar(64) DEFAULT NULL COMMENT '虚拟商户号',
  `CRE_ID` varchar(20) DEFAULT NULL,
  `XN_TYPE` int(11) DEFAULT NULL COMMENT '0:储蓄卡，1：信用卡',
  PRIMARY KEY (`ID`),
  KEY `IDX_LC_MER_CRE_MER_CODE` (`MER_CODE`,`XN_MER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商户证书关联表';

-- ----------------------------
-- Table structure for admin_cifpay_lc_review_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_cifpay_lc_review_info`;
CREATE TABLE `admin_cifpay_lc_review_info` (
  `ID` varchar(20) NOT NULL DEFAULT '',
  `REVIEW_CODE` varchar(16) DEFAULT NULL COMMENT '审核业务编码',
  `REVIEW_NAME` varchar(64) DEFAULT NULL COMMENT '审核业务名称',
  `REVIEW_REMARK` varchar(1024) DEFAULT NULL COMMENT '审核业务说明',
  `CREATE_TIME` datetime DEFAULT NULL,
  `REVIEW_ID` varchar(20) DEFAULT NULL,
  `REVIEW_RESULT` int(11) unsigned DEFAULT NULL COMMENT '0:未审核，1:审核通过，2：审核不通过',
  `REVIEW_USER_ID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务审核信息表';

-- ----------------------------
-- Table structure for admin_contract
-- ----------------------------
DROP TABLE IF EXISTS `admin_contract`;
CREATE TABLE `admin_contract` (
  `ID` varchar(20) NOT NULL DEFAULT '',
  `CON_NO` varchar(45) NOT NULL COMMENT '合同编号',
  `CON_NAME` varchar(45) NOT NULL COMMENT '合同名称',
  `CON_PROCEDURE` tinyint(1) NOT NULL COMMENT '手续规则 1：比率  2：笔数',
  `PROCEDURE_VALUE` int(4) NOT NULL COMMENT '手续规则对应的值 0~100',
  `MER_ID` varchar(20) DEFAULT NULL,
  `BANK_ID` varchar(20) DEFAULT NULL,
  `BANK_CODE` varchar(16) DEFAULT NULL COMMENT '银行Code',
  `CON_TYPE` tinyint(1) DEFAULT NULL COMMENT '合同类型 1：商户  2：银行',
  `START_DATE` datetime NOT NULL COMMENT '生效时间',
  `END_DATE` datetime NOT NULL COMMENT '失效时间',
  `OTHER` varchar(255) DEFAULT NULL COMMENT '合同其他信息，存储格式JSON',
  `CON_STATUS` tinyint(1) NOT NULL DEFAULT '1' COMMENT '合同状态',
  `USER_ID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合同信息表';

-- ----------------------------
-- Table structure for admin_credentials
-- ----------------------------
DROP TABLE IF EXISTS `admin_credentials`;
CREATE TABLE `admin_credentials` (
  `ID` varchar(20) NOT NULL DEFAULT '',
  `CRE_NAME` varchar(32) DEFAULT NULL COMMENT '证书名称',
  `CRE_PATH` varchar(128) DEFAULT NULL COMMENT '证书路径',
  `CRE_TYPE` tinyint(20) DEFAULT NULL COMMENT '证书类型:0文件存储，1数据库，2文件名称，3MONGODB',
  `CRE_CONENT` longblob COMMENT '证书内容',
  `CREATE_TIME` datetime DEFAULT NULL,
  `USER_ID` varchar(20) DEFAULT NULL,
  `CRE_SUM` int(11) DEFAULT NULL COMMENT '计数',
  `CRE_REMARK` varchar(1024) DEFAULT NULL COMMENT '证书说明',
  `CRE_STATE` tinyint(11) DEFAULT NULL COMMENT '证书状态',
  `CRE_FILE_NAME` varchar(64) DEFAULT NULL,
  `CRE_PASSWORD` varchar(64) DEFAULT NULL COMMENT '证书密码',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='证书信息表';

-- ----------------------------
-- Table structure for admin_group_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_group_info`;
CREATE TABLE `admin_group_info` (
  `ID` varchar(20) NOT NULL DEFAULT '',
  `GROUP_NAME` varchar(64) DEFAULT NULL,
  `GROUP_CODE` varchar(16) DEFAULT NULL,
  `GROUP_REMARK` varchar(1024) DEFAULT NULL COMMENT '部门描述',
  `GROUP_POWER` int(11) DEFAULT NULL COMMENT '1:内部管理，2：商户，3：银行拓展,4:基础部门',
  `CREATE_TIME` datetime DEFAULT NULL,
  `GROUP_STATE` int(11) DEFAULT '0' COMMENT '部门状态0:未审核，1:审核通过，2：审核不通过,3:重新申请',
  `CREATE_USER_ID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门信息表';

-- ----------------------------
-- Table structure for admin_group_resc
-- ----------------------------
DROP TABLE IF EXISTS `admin_group_resc`;
CREATE TABLE `admin_group_resc` (
  `ID` varchar(20) NOT NULL DEFAULT '',
  `GROUP_ID` varchar(20) DEFAULT NULL,
  `RESC_ID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门资源关联表';

-- ----------------------------
-- Table structure for admin_login_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_login_info`;
CREATE TABLE `admin_login_info` (
  `L_ID` varchar(20) NOT NULL DEFAULT '',
  `U_ID` varchar(20) DEFAULT NULL,
  `U_ACCOUNT_NAME` varchar(255) DEFAULT NULL,
  `L_IP` varchar(255) DEFAULT NULL,
  `L_LOGIN_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`L_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';

-- ----------------------------
-- Table structure for admin_log_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_log_info`;
CREATE TABLE `admin_log_info` (
  `L_ID` varchar(20) NOT NULL DEFAULT '',
  `U_ID` varchar(20) DEFAULT NULL,
  `L_ACCOUNT_NAME` varchar(100) DEFAULT NULL,
  `L_OPERATION` varchar(255) DEFAULT NULL COMMENT '用户所做的操作',
  `L_CONTENT` varchar(1000) DEFAULT NULL COMMENT '日志内容',
  `L_CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`L_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- ----------------------------
-- Table structure for admin_mer_expenses
-- ----------------------------
DROP TABLE IF EXISTS `admin_mer_expenses`;
CREATE TABLE `admin_mer_expenses` (
  `ID` varchar(20) NOT NULL DEFAULT '',
  `MER_NAME` varchar(64) DEFAULT NULL,
  `MER_PROCEDURE` varchar(8) DEFAULT NULL COMMENT '手续规则 :比率 ,笔数',
  `PROCEDURE_VALUE` float DEFAULT NULL COMMENT '手续规则对应的值 0~100',
  `MER_ID` varchar(20) DEFAULT NULL,
  `CON_ID` varchar(20) DEFAULT NULL,
  `SUM_MONEY` float DEFAULT NULL COMMENT '总金额（元)',
  `SUM_COUNT` int(11) DEFAULT NULL COMMENT '总笔数',
  `PAYMENT` float DEFAULT NULL COMMENT '应收手续费（元）',
  `ALREADY_PAY` float DEFAULT NULL COMMENT '实收手续费（元）',
  `ARREARS_PAY` float DEFAULT NULL COMMENT '欠费(元)',
  `BILL_TIME` datetime DEFAULT NULL COMMENT '对账时间',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `STATE` tinyint(4) DEFAULT '0' COMMENT '状态',
  `IS_PAY` tinyint(4) DEFAULT '0' COMMENT '是否欠费:0欠费，1没有欠费',
  `STATIS_TIME_S` varchar(8) DEFAULT NULL COMMENT '统计年月',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商户收费信息表';

-- ----------------------------
-- Table structure for admin_mer_exp_statis
-- ----------------------------
DROP TABLE IF EXISTS `admin_mer_exp_statis`;
CREATE TABLE `admin_mer_exp_statis` (
  `ID` varchar(20) NOT NULL DEFAULT '',
  `MER_EXP_ID` varchar(20) DEFAULT NULL,
  `EXP_STATIS_NAME` varchar(64) DEFAULT NULL COMMENT '收费名称',
  `MONEY_PAY` double DEFAULT NULL COMMENT '收费金额',
  `EXP_TIME` datetime DEFAULT NULL COMMENT '收费时间',
  `USER_ID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商户收费明细表';

-- ----------------------------
-- Table structure for admin_resource
-- ----------------------------
DROP TABLE IF EXISTS `admin_resource`;
CREATE TABLE `admin_resource` (
  `S_ID` varchar(20) NOT NULL DEFAULT '',
  `S_PARENT_ID` varchar(20) DEFAULT NULL,
  `S_NAME` varchar(100) NOT NULL COMMENT '资源名称',
  `S_SOURCE_KEY` varchar(100) NOT NULL COMMENT '资源唯一标识',
  `S_TYPE` int(11) NOT NULL COMMENT '资源类型,0:目录;1:菜单;2:按钮',
  `S_SOURCE_URL` varchar(500) DEFAULT NULL COMMENT '资源URL',
  `S_LEVEL` int(11) DEFAULT NULL COMMENT '层级',
  `S_ICON` varchar(100) DEFAULT '' COMMENT '图标',
  `S_IS_HIDE` int(11) DEFAULT '0' COMMENT '是否隐藏',
  `S_DESCRIPTION` varchar(100) DEFAULT NULL COMMENT '描述',
  `S_CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `S_UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `S_BASE_TYPE` int(11) DEFAULT '0' COMMENT '是否默认资源,1:是，0:否',
  `S_REDIRECT_URL` varchar(100) DEFAULT NULL COMMENT '跳转路径',
  PRIMARY KEY (`S_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Table structure for admin_resources_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_resources_role`;
CREATE TABLE `admin_resources_role` (
  `ID` varchar(20) NOT NULL DEFAULT '',
  `S_ID` varchar(20) DEFAULT NULL,
  `R_ID` varchar(20) DEFAULT NULL,
  `T_CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限映射表';

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `R_ID` varchar(20) NOT NULL DEFAULT '',
  `R_NAME` varchar(50) NOT NULL COMMENT '角色名称',
  `R_KEY` varchar(50) NOT NULL COMMENT '角色KEY',
  `R_STATUS` int(11) DEFAULT '0' COMMENT '角色状态,0：正常；1：删除',
  `R_DESCRIPTION` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `R_CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `R_UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `G_ID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`R_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for admin_role_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_user`;
CREATE TABLE `admin_role_user` (
  `ID` varchar(20) NOT NULL DEFAULT '',
  `R_ID` varchar(20) DEFAULT NULL,
  `U_ID` varchar(20) DEFAULT NULL,
  `T_CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色映射表';

-- ----------------------------
-- Table structure for admin_sms_template
-- ----------------------------
DROP TABLE IF EXISTS `admin_sms_template`;
CREATE TABLE `admin_sms_template` (
  `ID` varchar(20) NOT NULL DEFAULT '',
  `SMS_TEM_NAME` varchar(64) DEFAULT NULL COMMENT '模板名称',
  `SMS_TEM_CONTENT` varchar(1024) DEFAULT NULL COMMENT '模板内容',
  `SMS_TEM_TYPE` tinyint(4) DEFAULT NULL COMMENT '模板类型：1:cp200 ,2:cp300,3:cp500,4:开证,5:退款',
  `USER_ID` varchar(20) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `SMS_STATE` tinyint(4) DEFAULT NULL COMMENT '状态',
  `SMS_REMARK` varchar(512) DEFAULT NULL COMMENT '备注',
  `SMS_TEM_PARAM` varchar(256) DEFAULT NULL COMMENT '模板参数',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信模板';

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `U_ID` varchar(20) NOT NULL DEFAULT '',
  `U_NAME` varchar(100) NOT NULL COMMENT '真实姓名',
  `U_ACCOUNT_NAME` varchar(100) NOT NULL COMMENT '账户名称',
  `U_PASSWORD` varchar(100) NOT NULL COMMENT '用户密码',
  `U_DELETE_STATUS` tinyint(4) DEFAULT '0' COMMENT '逻辑删除状态:0：正常；1:已删除；2：冻结',
  `U_LOCKED` tinyint(4) DEFAULT '0' COMMENT '是否锁定',
  `U_DESCRIPTION` varchar(200) DEFAULT NULL COMMENT '用户描述',
  `U_CREDENTIALS_SALT` varchar(500) NOT NULL COMMENT '加密盐',
  `U_CREATOR_NAME` varchar(100) NOT NULL COMMENT '创建者',
  `U_CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `U_UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `U_TYPE` int(11) DEFAULT NULL COMMENT '用户类型 1：部门管理员，2：操作人民，3:其他用户',
  `G_ID` varchar(20) DEFAULT NULL,
  `U_STATE` bigint(20) DEFAULT NULL COMMENT '0:未审核，1：已审核，2：审核不通过，3：重新审核',
  `U_EMAIL` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `U_PHONE` varchar(11) DEFAULT NULL COMMENT '联系手机号',
  `U_EXTENSION` varchar(1024) DEFAULT NULL COMMENT '分机号',
  `U_SESSION` bigint(20) DEFAULT '0' COMMENT '是否修改原始密码0 未修改，1，修改',
  `U_LOGIN_TIME` timestamp NULL DEFAULT NULL COMMENT '登录时间',
  `U_ISABLE` tinyint(4) DEFAULT '0' COMMENT '0:启用,1:禁用',
  PRIMARY KEY (`U_ID`),
  UNIQUE KEY `U_ACCOUNT_NAME_UNIQUE` (`U_ACCOUNT_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账户表';

-- ----------------------------
-- Table structure for admin_user_group
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_group`;
CREATE TABLE `admin_user_group` (
  `ID` varchar(20) NOT NULL DEFAULT '',
  `GROUP_ID` varchar(20) DEFAULT NULL,
  `USER_ID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Table structure for admin_user_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_info`;
CREATE TABLE `admin_user_info` (
  `U_ID` varchar(20) NOT NULL DEFAULT '',
  `U_SEX` int(11) DEFAULT NULL COMMENT '性别',
  `U_BIRTHDAY` date DEFAULT NULL COMMENT '出生日期',
  `U_TELEPHONE` varchar(20) DEFAULT NULL COMMENT '电话',
  `U_EMAIL` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `U_ADDRESS` varchar(200) DEFAULT NULL COMMENT '住址',
  `U_CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `G_ID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`U_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户扩展信息表';

-- ----------------------------
-- Table structure for cifpay_guid_worker_id
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_guid_worker_id`;
CREATE TABLE `cifpay_guid_worker_id` (
  `MACHINE_ID` varchar(32) NOT NULL COMMENT '用于表示该机器节点的唯一ID，例如对机器名的MD5值',
  `APP_INSTANCE_ID` varchar(32) NOT NULL COMMENT '用于表示该应用实例在该机器节点的唯一ID，例如对某个文件路径的MD5值',
  `WORKER_ID` int(10) unsigned NOT NULL COMMENT '该应用被分配到的Snowflake worker id，支持0到1023的整数值',
  `MACHINE_NAME` varchar(256) NOT NULL COMMENT '机器名称',
  `APP_BINARY_PATH` varchar(2048) DEFAULT NULL COMMENT '可选，用于记录该应用实现的二进制文件所在磁盘路径',
  `INITIALIZED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '该应用节点的首次初始化日期',
  `APP_LAST_STARTED_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '该应用节点最近一次启动的日期',
  PRIMARY KEY (`MACHINE_ID`,`APP_INSTANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于分配和登记所有应用节点的Snowflake Worker Id，该Worker Id提供给Snowflake算法用于生成各数据表的主键ID值';

-- ----------------------------
-- Table structure for cifpay_lc
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc`;
CREATE TABLE `cifpay_lc` (
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
  `PAYER_TYPE` varchar(16) DEFAULT NULL COMMENT '开证人类型：个人:1  企业:2',
  `PAYER_BANK_CODE` varchar(32) DEFAULT NULL COMMENT '开证人银行代码：ICBC、ALIPAY',
  `PAYER_BANK_NAME` varchar(128) DEFAULT NULL COMMENT '开证人银行名称',
  `PAYER_MOBILE` varchar(32) DEFAULT NULL,
  `RECV_ID` varchar(32) DEFAULT NULL COMMENT '收证人信息',
  `RECV_ACCNO` varchar(32) DEFAULT NULL COMMENT '银行卡号、支付宝账号',
  `RECV_TYPE` varchar(16) DEFAULT NULL COMMENT '收证人类型：个人:1  企业:2',
  `RECV_BANK_CODE` varchar(32) DEFAULT NULL COMMENT '机构代码：工商银行、平安银行、支付宝',
  `RECV_BANK_NAME` varchar(128) DEFAULT NULL COMMENT '收证人银行名称',
  `RECV_MOBILE` varchar(32) DEFAULT NULL,
  `LC_STATUS` varchar(2) DEFAULT NULL COMMENT '银信证状态：10已开证、20已收证、30已履约、31部分履约、32已展期、40已申请解付、41已刹车、50已执行解付、88已解付完成、90已解冻退回、91预失效',
  `RECV_VALID_TIME` timestamp NULL DEFAULT NULL COMMENT '收证有效期',
  `SEND_VALID_TIME` timestamp NULL DEFAULT NULL COMMENT '履约有效期',
  `CONFIRM_VALID_TIME` timestamp NULL DEFAULT NULL COMMENT '申请解付有效期',
  `PAY_VALID_TIME` timestamp NULL DEFAULT NULL COMMENT '执行解付时间（到期执行解付）',
  `LC_STATE_RETURN_URL` varchar(512) DEFAULT NULL COMMENT '银信证开证回调URL',
  `LC_STATE_NOTIFY_URL` varchar(512) DEFAULT NULL COMMENT '银信证状态变更通知URL',
  `LC_ORDER_DETAIL_URL` varchar(512) DEFAULT NULL COMMENT '银信证商户订单详情URL',
  `THIRD_PARTY_CODE` varchar(64) DEFAULT NULL COMMENT '第三方认证机构代码',
  `PAY_TYPE` varchar(16) DEFAULT '1' COMMENT '解付类型：SINGLE单次解付、MULTIPLE同收款人多次解付、',
  `SMS_CODE` varchar(8) DEFAULT NULL COMMENT '自动发送的短信验证码',
  `IS_VALID` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否有效，1有效，0无效',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `REMARK` varchar(512) DEFAULT '1' COMMENT '备注',
  PRIMARY KEY (`LC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银信证主表';

-- ----------------------------
-- Table structure for cifpay_lc_confirm_pay
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc_confirm_pay`;
CREATE TABLE `cifpay_lc_confirm_pay` (
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

-- ----------------------------
-- Table structure for cifpay_lc_invalid
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc_invalid`;
CREATE TABLE `cifpay_lc_invalid` (
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
  `REMARK` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`LC_INVALID_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银信证失效记录表';

-- ----------------------------
-- Table structure for cifpay_lc_log
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc_log`;
CREATE TABLE `cifpay_lc_log` (
  `LOG_ID` bigint(20) NOT NULL COMMENT '记录ID',
  `LC_ID` bigint(20) NOT NULL COMMENT '银信证ID',
  `STEP_LOG_ID` bigint(20) DEFAULT NULL COMMENT '银信证过程表的id，例如openid，sendid',
  `TRADE_CODE` varchar(32) DEFAULT NULL COMMENT '交易代码，初始化证INIT，开证 OPEN，收证RECV，履约 APPOINTMENT，申请解付 APPLY，解付 TRANSFER，展期 DEFER，刹车 SUSPEND，定时任务 TIMERTASK',
  `FROM_STATUS` varchar(32) DEFAULT NULL COMMENT '变更前银信证状态',
  `TO_STATUS` varchar(32) DEFAULT NULL COMMENT '变更后银信证状态',
  `LC_RESPONSE` varchar(1024) DEFAULT NULL COMMENT '银信证交易结果',
  `REMARK` varchar(256) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银信证交易流水表，每次银信证状态改变时，记录日志';

-- ----------------------------
-- Table structure for cifpay_lc_open
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc_open`;
CREATE TABLE `cifpay_lc_open` (
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

-- ----------------------------
-- Table structure for cifpay_lc_open_batch
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc_open_batch`;
CREATE TABLE `cifpay_lc_open_batch` (
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

-- ----------------------------
-- Table structure for cifpay_lc_page
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc_page`;
CREATE TABLE `cifpay_lc_page` (
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

-- ----------------------------
-- Table structure for cifpay_lc_page_template
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc_page_template`;
CREATE TABLE `cifpay_lc_page_template` (
  `TEMPLATE_CODE` varchar(50) NOT NULL COMMENT '模板编号',
  `VERSION` varchar(20) NOT NULL COMMENT '版本号',
  `TRAN_STATUS` int(1) NOT NULL DEFAULT '0' COMMENT '处理状态，0=无进程占用处理；1=处理中',
  `LOCKED_BY_NODE` varchar(256) DEFAULT NULL COMMENT '当前锁定该记录的应用节点ID',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`TEMPLATE_CODE`,`VERSION`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='证化页面模板表';

-- ----------------------------
-- Table structure for cifpay_lc_page_template_detail
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc_page_template_detail`;
CREATE TABLE `cifpay_lc_page_template_detail` (
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

-- ----------------------------
-- Table structure for cifpay_lc_pay
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc_pay`;
CREATE TABLE `cifpay_lc_pay` (
  `LC_PAY_ID` bigint(20) NOT NULL COMMENT '解付记录ID',
  `LC_CONFIRM_ID` bigint(20) NOT NULL COMMENT '申请解付记录ID',
  `LC_ID` bigint(20) DEFAULT NULL COMMENT '银信证ID',
  `MID` varchar(32) DEFAULT NULL,
  `ORDER_ID` varchar(128) DEFAULT NULL COMMENT '订单号',
  `TOTAL_AMOUNT` decimal(19,2) DEFAULT NULL COMMENT '解付金额（单位分）',
  `TRADE_TIME` timestamp NULL DEFAULT NULL COMMENT '解付处理日期',
  `VALID_TIME` timestamp NULL DEFAULT NULL COMMENT '解付有效期',
  `UNION_TXNTIME` varchar(32) DEFAULT NULL COMMENT '订单发送时间(格式YYYYMMDDhhmmss)',
  `UNION_SERIAL_NO` varchar(64) DEFAULT NULL,
  `REMARK` varchar(256) DEFAULT NULL COMMENT '备注',
  `LC_PAY_STATUS` varchar(32) DEFAULT NULL COMMENT '解付状态：处理中0、成功1、失败2、不确定3',
  `LC_PAY_RESPONSE` varchar(256) DEFAULT NULL COMMENT '解付结果：银行接口返回的错误/成功信息',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`LC_PAY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银信证执行解付记录表';

-- ----------------------------
-- Table structure for cifpay_lc_product
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc_product`;
CREATE TABLE `cifpay_lc_product` (
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
  `AUTO_SEND_SMS` bit(1) DEFAULT NULL COMMENT '自动发送短信',
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

-- ----------------------------
-- Table structure for cifpay_lc_recv
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc_recv`;
CREATE TABLE `cifpay_lc_recv` (
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

-- ----------------------------
-- Table structure for cifpay_lc_recv_batch
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc_recv_batch`;
CREATE TABLE `cifpay_lc_recv_batch` (
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

-- ----------------------------
-- Table structure for cifpay_lc_refund
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc_refund`;
CREATE TABLE `cifpay_lc_refund` (
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

-- ----------------------------
-- Table structure for cifpay_lc_send
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc_send`;
CREATE TABLE `cifpay_lc_send` (
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

-- ----------------------------
-- Table structure for cifpay_lc_send_org
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc_send_org`;
CREATE TABLE `cifpay_lc_send_org` (
  `ORG_ID` varchar(64) NOT NULL COMMENT '履约机构ID',
  `ORG_NAME` varchar(128) DEFAULT NULL COMMENT '履约机构名称',
  `ITF_URL` varchar(1024) DEFAULT NULL COMMENT '接口调用地址',
  `FINISH_REGEX` varchar(256) DEFAULT NULL COMMENT '接口返回内容履约完成正则表达式',
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ORG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='履约机构';

-- ----------------------------
-- Table structure for cifpay_lc_trd_code_desc
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc_trd_code_desc`;
CREATE TABLE `cifpay_lc_trd_code_desc` (
  `PLATFORM_ID` varchar(32) NOT NULL COMMENT '平台ID',
  `SYS_CODE` varchar(20) NOT NULL DEFAULT '' COMMENT '系统响应码',
  `SYS_DESC` varchar(100) DEFAULT NULL COMMENT '系统返回信息描述',
  `RESP_CODE` varchar(20) NOT NULL COMMENT '平台返回码',
  `RESP_DESC` varchar(256) DEFAULT NULL COMMENT '平台返回结果信息',
  PRIMARY KEY (`PLATFORM_ID`,`RESP_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='状态码描述表';

-- ----------------------------
-- Table structure for cifpay_lc_trd_lock
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc_trd_lock`;
CREATE TABLE `cifpay_lc_trd_lock` (
  `LC_ID` bigint(20) NOT NULL COMMENT '银信证ID',
  `INSERT_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '插入时间',
  PRIMARY KEY (`LC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cifpay_lc_trd_unionpay_flow
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc_trd_unionpay_flow`;
CREATE TABLE `cifpay_lc_trd_unionpay_flow` (
  `FLOW_ID` bigint(20) NOT NULL,
  `BUSINESS_ID` bigint(20) DEFAULT NULL COMMENT '业务ID',
  `LC_ID` bigint(20) NOT NULL COMMENT '银信证ID',
  `TXN_TYPE` varchar(2) NOT NULL COMMENT '交易类型01消费 31 消费撤销',
  `TXN_SUB_TYPE` varchar(2) NOT NULL COMMENT ' 交易子类型',
  `BIZ_TYPE` varchar(10) DEFAULT NULL COMMENT '业务类型 001001订购 000902无跳转',
  `MER_ID` varchar(20) DEFAULT NULL COMMENT '商户代码',
  `SUB_MER_ID` varchar(20) DEFAULT NULL COMMENT '二级商户代码',
  `ORDER_ID` varchar(40) DEFAULT NULL COMMENT '商户订单号',
  `ORIG_FLOW_ID` varchar(30) DEFAULT NULL COMMENT '原始交易流水号',
  `TXN_TIME` varchar(20) DEFAULT NULL COMMENT '订单发送时间',
  `TXN_AMT` bigint(20) DEFAULT NULL COMMENT '交易金额,单位为分',
  `CURRENCY_CODE` varchar(10) DEFAULT NULL COMMENT '交易币种',
  `NEW_FLOW_ID` varchar(30) DEFAULT NULL COMMENT '银联交易流水号（返回结果）',
  `TRACE_NO` varchar(50) DEFAULT NULL COMMENT '系统跟踪号',
  `TRACE_TIME` varchar(20) DEFAULT NULL COMMENT '交易传输时间',
  `ASYN_RESP_CODE` varchar(2) DEFAULT NULL COMMENT '异步结果',
  `ASYN_RESP_MSG` varchar(256) DEFAULT NULL COMMENT '交易结果返回信息',
  `SYNC_RESP_CODE` varchar(2) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '同步结果 ',
  `SYNC_RESP_MSG` varchar(256) DEFAULT NULL,
  `SETTLE_AMT` bigint(20) DEFAULT NULL COMMENT '清算金额',
  `SETTLE_CURRENCY_CODE` varchar(10) DEFAULT NULL COMMENT '清算币种',
  `SETTLE_DATE` varchar(20) DEFAULT NULL COMMENT '清算日期',
  `SYNC_TRADE_RESULT` varchar(1) DEFAULT NULL COMMENT '同步交易结果 0成功 1失败 2状态未知',
  `ASYN_TRADE_RESULT` varchar(1) DEFAULT NULL COMMENT '异步交易结果 0成功 1失败 2状态未知',
  `INSERT_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '插入日期',
  `LAST_UPD_TIME` timestamp NULL DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`FLOW_ID`),
  KEY `flowId` (`FLOW_ID`) USING BTREE,
  KEY `order_txn_index` (`ORDER_ID`,`TXN_TIME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银联支付渠道流水表';

-- ----------------------------
-- Table structure for cifpay_lc_trd_unionpay_main
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc_trd_unionpay_main`;
CREATE TABLE `cifpay_lc_trd_unionpay_main` (
  `BUSINESS_ID` bigint(20) NOT NULL COMMENT '表主键',
  `LC_ID` bigint(20) NOT NULL COMMENT '银信证ID',
  `TXN_ID` varchar(15) NOT NULL COMMENT '交易类型（2）+交易子类型（2）+银信证类型（3）+卡别（1）+识别码（2）',
  `TXN_TYPE` varchar(2) NOT NULL COMMENT '交易类型',
  `TXN_SUB_TYPE` varchar(2) NOT NULL COMMENT '交易子类型',
  `BIZ_TYPE` varchar(6) NOT NULL COMMENT '业务类型 001001订购 000902无跳转',
  `MER_ID` varchar(20) DEFAULT NULL COMMENT '商户代码',
  `ORDER_ID` varchar(40) NOT NULL COMMENT '商户订单号',
  `TXN_TIME` varchar(20) NOT NULL COMMENT '订单发送时间',
  `TXN_AMT` bigint(20) DEFAULT NULL COMMENT '交易金额,单位为分',
  `CHANNEL_TYPE` varchar(2) DEFAULT NULL COMMENT ' 渠道类型',
  `SUB_MER_ID` varchar(20) DEFAULT NULL COMMENT '二级商户代码',
  `USER_ID` varchar(40) DEFAULT NULL COMMENT '商户中用户的唯一ID',
  `ACC_TYPE` varchar(2) DEFAULT NULL COMMENT '账号类型',
  `ACC_NO` varchar(40) DEFAULT NULL COMMENT '账号',
  `CURRENCY_CODE` varchar(6) DEFAULT '156' COMMENT '交易币种',
  `PHONE_NO` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `REQ_RESERVED` varchar(1000) DEFAULT NULL COMMENT '请求方保留域',
  `ORDER_DESC` varchar(200) DEFAULT NULL COMMENT '订单描述',
  `SYNC_RESP_CODE` varchar(2) DEFAULT NULL COMMENT '同步应答码',
  `SYNC_RESP_MSG` varchar(1000) DEFAULT NULL COMMENT '同步应答信息',
  `ASYN_RESP_CODE` varchar(2) DEFAULT NULL COMMENT '异步应答码',
  `ASYN_RESP_MSG` varchar(1000) DEFAULT NULL COMMENT '异步应答信息',
  `RTN_QUERY_ID` varchar(40) DEFAULT NULL COMMENT '交易查询流水号',
  `RTN_SETTLE_DATE` varchar(10) DEFAULT NULL COMMENT '清算日期',
  `SYNC_TRADE_RESULT` varchar(1) DEFAULT NULL COMMENT '同步交易结果 0成功 1失败 2状态未知',
  `ASYN_TRADE_RESULT` varchar(1) DEFAULT '9' COMMENT '异步交易结果 0成功 1失败 2状态未知 9默认',
  `FLOW_ID` bigint(20) DEFAULT NULL COMMENT '流水表ID（最后一笔）',
  `INSERT_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '插入时间(即同步交易本地时间)',
  `LAST_UPD_TIME` timestamp NULL DEFAULT NULL COMMENT '最后更新时间(异步通知本地时间)',
  `QUERY_TIMES` int(11) DEFAULT '0' COMMENT '查询交易次数',
  PRIMARY KEY (`BUSINESS_ID`),
  KEY `order_txn_index` (`ORDER_ID`,`TXN_TIME`) USING BTREE,
  KEY `queryId` (`RTN_QUERY_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cifpay_lc_trd_unionpay_token
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc_trd_unionpay_token`;
CREATE TABLE `cifpay_lc_trd_unionpay_token` (
  `MER_ID` varchar(20) NOT NULL COMMENT '商户代码',
  `ACC_NO` varchar(100) NOT NULL COMMENT '商户账号（卡号）',
  `TOKEN` varchar(100) DEFAULT NULL COMMENT '支付标记',
  `TR_ID` varchar(15) DEFAULT NULL COMMENT '标记请求者ID',
  `TOKEN_LEVEL` varchar(2) DEFAULT NULL COMMENT '标记担保级别',
  `TOKEN_BEGIN` varchar(14) DEFAULT NULL COMMENT '标记生效时间 格式为 YYYYMMDDhhmmss',
  `TOKEN_END` varchar(14) DEFAULT NULL COMMENT '标记失效时间 格式为 YYYYMMDDhhmmss',
  `TOKEN_TYPE` varchar(2) DEFAULT NULL COMMENT '标记类型',
  `PHONE_NO` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`MER_ID`,`ACC_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cifpay_lc_type
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_lc_type`;
CREATE TABLE `cifpay_lc_type` (
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

-- ----------------------------
-- Table structure for cifpay_message
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_message`;
CREATE TABLE `cifpay_message` (
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
) ENGINE=InnoDB AUTO_INCREMENT=112508703407607809 DEFAULT CHARSET=utf8 COMMENT='消息表';

-- ----------------------------
-- Table structure for cifpay_message_his
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_message_his`;
CREATE TABLE `cifpay_message_his` (
  `ID` bigint(20) NOT NULL COMMENT '主键',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 消息历史表';

-- ----------------------------
-- Table structure for cifpay_pre_lc
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_pre_lc`;
CREATE TABLE `cifpay_pre_lc` (
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

-- ----------------------------
-- Table structure for cifpay_user_account
-- ----------------------------
DROP TABLE IF EXISTS `cifpay_user_account`;
CREATE TABLE `cifpay_user_account` (
  `P_ID` bigint(20) NOT NULL,
  `MID` varchar(32) NOT NULL COMMENT '用户渠道标识',
  `MER_USERID` varchar(32) NOT NULL DEFAULT '' COMMENT '商户用户标识',
  `USER_MOBILE` varchar(32) DEFAULT NULL COMMENT '用户手机号',
  `PAYER_ACCNO` varchar(32) DEFAULT NULL COMMENT '用户银行卡号',
  `ACCNO_TYPE` int(11) DEFAULT NULL COMMENT '0，储蓄卡；1,信用卡；',
  `CREATE_DATE` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`P_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银联商户用户账号关联表';
