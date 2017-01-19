/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : hospital

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-01-15 11:40:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` int(32) NOT NULL AUTO_INCREMENT,
  `cname` varchar(100) NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cure
-- ----------------------------
DROP TABLE IF EXISTS `cure`;
CREATE TABLE `cure` (
  `cure_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cure_name` varchar(255) NOT NULL DEFAULT '其他理疗',
  `cure_cost` decimal(10,2) NOT NULL,
  `image` varchar(200) DEFAULT NULL,
  `cid` int(32) DEFAULT NULL,
  `cure_extra` text,
  PRIMARY KEY (`cure_id`,`cure_name`),
  KEY `hospital_cure` (`cid`),
  KEY `cure_id` (`cure_id`),
  CONSTRAINT `hospital_cure` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `oid` int(32) NOT NULL AUTO_INCREMENT,
  `ordertime` datetime DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `state` smallint(1) NOT NULL,
  `uid` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `hospital_order` (`uid`),
  CONSTRAINT `hospital_order` FOREIGN KEY (`uid`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `item_id` int(32) NOT NULL AUTO_INCREMENT,
  `item_count` int(8) NOT NULL DEFAULT '1',
  `item_total` decimal(10,2) NOT NULL DEFAULT '0.00',
  `oid` int(32) NOT NULL,
  `cure_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`item_id`),
  KEY `hospital_item_1` (`oid`),
  KEY `hospital_item_2` (`cure_id`),
  CONSTRAINT `hospital_item_1` FOREIGN KEY (`oid`) REFERENCES `order` (`oid`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `hospital_item_2` FOREIGN KEY (`cure_id`) REFERENCES `cure` (`cure_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_name` varchar(50) NOT NULL,
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_tel` int(10) NOT NULL,
  `user_privilege` varchar(50) NOT NULL DEFAULT 'custom',
  `user_password` varchar(255) DEFAULT '',
  `user_regist_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `email` varchar(50) NOT NULL,
  `code` varchar(64) NOT NULL,
  `state` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
