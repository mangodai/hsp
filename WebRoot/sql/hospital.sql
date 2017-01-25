/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : hospital

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-01-22 11:49:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` int(32) NOT NULL AUTO_INCREMENT,
  `cname` varchar(100) NOT NULL,
  `ccid` int(32) DEFAULT NULL COMMENT '父类标签',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '低消费', null);
INSERT INTO `category` VALUES ('2', '高消费', null);

-- ----------------------------
-- Table structure for cure
-- ----------------------------
DROP TABLE IF EXISTS `cure`;
CREATE TABLE `cure` (
  `cure_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cure_name` varchar(255) NOT NULL DEFAULT '其他理疗',
  `cure_doctor` varchar(50) DEFAULT '默认医生',
  `cure_cost` decimal(10,2) NOT NULL,
  `image` varchar(200) DEFAULT NULL,
  `cid` int(32) DEFAULT NULL,
  `cure_extra` text,
  PRIMARY KEY (`cure_id`,`cure_name`),
  KEY `hospital_cure` (`cid`),
  KEY `cure_id` (`cure_id`),
  CONSTRAINT `hospital_cure` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cure
-- ----------------------------
INSERT INTO `cure` VALUES ('1', '针刺', '默认医生', '50.00', 'images/zhenjiu.jpg', '1', null);
INSERT INTO `cure` VALUES ('2', '微波', '默认医生', '110.00', 'images/weibo.jpg', '2', null);
INSERT INTO `cure` VALUES ('3', '拔罐', '默认医生', '50.00', 'images/huoguan.jpg', '1', null);
INSERT INTO `cure` VALUES ('4', '封包', '默认医生', '80.00', 'images/fenbao.jpg', '1', null);
INSERT INTO `cure` VALUES ('5', '推拿', '默认医生', '200.00', 'images/tuina.jpg', '2', null);
INSERT INTO `cure` VALUES ('6', '艾灸', '默认医生', '80.00', 'images/aijiu.jpg', '1', null);
INSERT INTO `cure` VALUES ('7', '电针', '默认医生', '60.00', 'images/dianzhen.jpg', '1', null);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `mid` varchar(32) NOT NULL,
  `mname` varchar(32) NOT NULL,
  `email` varchar(32) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `content` text,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('029F29B3EE9941F1AE6C455E337D3BC2', 'zxc', 'zxc23@qq.com', 'zzz', 'waikjdsalkjdsa');
INSERT INTO `message` VALUES ('57C610C0DD624F888C35BF47B56C749A', 'qwe', '235552@139.com', 'zzz', 'zxcxzcxzc');
INSERT INTO `message` VALUES ('BADEA0BF237D4944939DC15101C42E1D', '嗲超', '965557340@qq.com', '建议', '内容');
INSERT INTO `message` VALUES ('q', 'w', 'e', 'a', 'w');
INSERT INTO `message` VALUES ('q1', 'w1', 'e1', 'a1', 'w1');
INSERT INTO `message` VALUES ('qwqweeqw', 'qwe', '96555734@qq.com', 'title', 'context');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `oid` int(32) NOT NULL AUTO_INCREMENT,
  `ordertime` datetime DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `state` smallint(1) DEFAULT NULL,
  `user_id` int(12) unsigned DEFAULT NULL,
  `tel` varchar(11) DEFAULT NULL,
  `order_uid` varchar(32) DEFAULT NULL,
  `order_reserve` datetime DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `hospital_order` (`user_id`),
  CONSTRAINT `hospital_order` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1010 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1000', '2017-01-18 23:16:24', '110.00', '1', '16', '18270235552', '38FAA2C0D0CD4733939AA6E4485F7DF3', '2017-01-18 23:14:00');
INSERT INTO `orders` VALUES ('1001', '2017-01-19 19:53:31', '110.00', '1', '16', '1802121', 'A73D48627AE94878A0E2A625FB7B108F', '2017-01-19 19:00:00');
INSERT INTO `orders` VALUES ('1003', '2017-01-19 19:54:25', '200.00', '1', '16', '1802121', '6A699BE6CA034E24B969326A2E617A9A', '2017-01-19 23:00:00');
INSERT INTO `orders` VALUES ('1004', '2017-01-19 19:54:35', '80.00', '1', '16', '18270235552', '0C8D633A0B394D8EB7BA3A5217AE2929', '2017-01-06 19:00:00');
INSERT INTO `orders` VALUES ('1005', '2017-01-19 19:54:45', '80.00', '1', '16', '1802121', '436AC0A96514435CBE643CFA4D43CF3E', '2017-01-17 22:00:00');
INSERT INTO `orders` VALUES ('1007', '2017-01-19 19:55:30', '330.00', '1', '16', '18270235552', 'DDC24F7E00D84F3FA718E1E5FC9A2BC9', '2017-01-21 21:00:00');
INSERT INTO `orders` VALUES ('1008', '2017-01-19 19:55:46', '80.00', '1', '16', '1802121', '1996BD40A9C447E48296DAEC764F2D61', '2017-01-05 15:00:00');
INSERT INTO `orders` VALUES ('1009', '2017-01-19 20:03:18', '200.00', '1', '16', '18270235552', '7BE07AEC33EE415DA3B2DA3D374DC79E', '2017-01-19 21:00:00');

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
  KEY `hospital_item_2` (`cure_id`),
  KEY `hospital_item_1` (`oid`),
  CONSTRAINT `hospital_item_1` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `hospital_item_2` FOREIGN KEY (`cure_id`) REFERENCES `cure` (`cure_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES ('25', '1', '110.00', '1000', '2');
INSERT INTO `order_item` VALUES ('26', '1', '110.00', '1001', '2');
INSERT INTO `order_item` VALUES ('30', '1', '80.00', '1004', '6');
INSERT INTO `order_item` VALUES ('31', '1', '80.00', '1005', '6');
INSERT INTO `order_item` VALUES ('34', '1', '80.00', '1008', '6');
INSERT INTO `order_item` VALUES ('35', '1', '200.00', '1009', '5');
INSERT INTO `order_item` VALUES ('36', '1', '200.00', '1003', '5');
INSERT INTO `order_item` VALUES ('37', '1', '80.00', '1007', '4');
INSERT INTO `order_item` VALUES ('38', '1', '60.00', '1007', '7');
INSERT INTO `order_item` VALUES ('39', '1', '80.00', '1007', '6');
INSERT INTO `order_item` VALUES ('40', '1', '110.00', '1007', '2');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `uid` int(12) NOT NULL AUTO_INCREMENT,
  `utime` datetime NOT NULL,
  `passw` varchar(32) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1', '2017-01-11 00:00:00', '');
INSERT INTO `test` VALUES ('2', '2017-01-11 00:00:00', '*B12289EEF8752AD620294A64A37CD58');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_name` varchar(50) NOT NULL,
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_tel` int(10) DEFAULT NULL,
  `user_privilege` varchar(50) NOT NULL DEFAULT 'custom',
  `user_password` varchar(255) DEFAULT '',
  `user_regist_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `email` varchar(50) NOT NULL,
  `code` varchar(64) NOT NULL,
  `state` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('dai', '15', '0', 'custom', '*BBAE266E0E1E92B3A857E20260D41B7BC259297F', '2017-01-17 15:50:57', '965557340@qq.com', '3B1C5D6AB864446782FC34ADE1AE1DB441F1DF3A7F96488F9C9BBF9A46F5E633', '1');
INSERT INTO `t_user` VALUES ('deri', '16', '0', 'admin', '*23AE809DDACAF96AF0FD78ED04B6A265E05AA257', '2017-01-17 15:52:39', '18270235552@139.com', 'F246958E3A7440839DA31F8AB164474A85CA54D57BDE41FF86C920660407C70D', '1');

-- ----------------------------
-- View structure for item_details
-- ----------------------------
DROP VIEW IF EXISTS `item_details`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `item_details` AS SELECT
order_item.item_id,
order_item.item_count,
order_item.item_total,
order_item.oid,
cure.cure_name
FROM
order_item
INNER JOIN cure ON order_item.cure_id = cure.cure_id ;

-- ----------------------------
-- View structure for orderplan
-- ----------------------------
DROP VIEW IF EXISTS `orderplan`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `orderplan` AS SELECT
orders.oid,
orders.order_reserve,
orders.tel,
orders.total,
t_user.user_name
FROM
orders
INNER JOIN t_user ON orders.user_id = t_user.user_id ;
DROP TRIGGER IF EXISTS `toPassword`;
DELIMITER ;;
CREATE TRIGGER `toPassword` BEFORE INSERT ON `t_user` FOR EACH ROW BEGIN
	set NEW.user_password = PASSWORD(NEW.user_password);
    END
;;
DELIMITER ;
