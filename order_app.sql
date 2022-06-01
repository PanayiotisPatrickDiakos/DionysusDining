/*
 Navicat Premium Data Transfer

 Source Server         : ulice
 Source Server Type    : MySQL
 Source Server Version : 50631
 Source Host           : localhost:3306
 Source Schema         : order_app

 Target Server Type    : MySQL
 Target Server Version : 50631
 File Encoding         : 65001

 Date: 29/10/2021 13:55:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_food
-- ----------------------------
DROP TABLE IF EXISTS `t_food`;
CREATE TABLE `t_food`  (
  `id` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT '' COMMENT 'primary key',
  `foodname` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT '' COMMENT 'name of food',
  `price` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT '' COMMENT 'price',
  `pic_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT 'picture link',
  `type` int(1) NOT NULL DEFAULT 1 COMMENT 'type of food：1-main food, 2-snack, 3-drink',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT 'remark',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_food
-- ----------------------------
INSERT INTO `t_food` VALUES ('1', 'Hot spot', '100.00', '/static/images/hotspot.jpg', 1, 'good');
INSERT INTO `t_food` VALUES ('12', 'Cray1', '100.00', '/static/images/cray.jpeg', 2, 'test');
INSERT INTO `t_food` VALUES ('13', 'Chips', '200.00', '/static/images/chips.jpeg', 2, 'test');
INSERT INTO `t_food` VALUES ('2', 'Milk-tea', '150.00', '/static/images/milktea.jpeg', 3, 'nice');
INSERT INTO `t_food` VALUES ('3', 'testFood3', '100.00', '/static/images/meat.jpeg', 1, 'test');
INSERT INTO `t_food` VALUES ('4', 'testFood4', '100.00', '/static/images/meat.jpeg', 1, 'test');
INSERT INTO `t_food` VALUES ('5', 'testFood5', '100.00', '/static/images/meat.jpeg', 1, 'test');
INSERT INTO `t_food` VALUES ('6', 'testFood6', '100.00', '/static/images/meat.jpeg', 1, 'test');
INSERT INTO `t_food` VALUES ('7', 'testFood7', '100.00', '/static/images/meat.jpeg', 1, 'test');
INSERT INTO `t_food` VALUES ('8', 'testFood8', '100.00', '/static/images/meat.jpeg', 1, 'test');
INSERT INTO `t_food` VALUES ('9', 'testFood9', '100.00', '/static/images/meat.jpeg', 1, 'test');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'primary key',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'user id',
  `order_json` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'order content json',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create_time',
  `order_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT 'order number',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('9cc5e29295d867af8aa6f3446e9ab550', '1', '[{\"count\":2,\"foodname\":\"Hot spot\",\"id\":\"1\",\"picUrl\":\"/static/images/hotspot.jpg\",\"price\":\"100.00\",\"remark\":\"good\",\"type\":1},{\"count\":2,\"foodname\":\"testFood4\",\"id\":\"4\",\"picUrl\":\"/static/images/meat.jpeg\",\"price\":\"100.00\",\"remark\":\"test\",\"type\":1},{\"count\":2,\"foodname\":\"testFood3\",\"id\":\"3\",\"picUrl\":\"/static/images/meat.jpeg\",\"price\":\"100.00\",\"remark\":\"test\",\"type\":1},{\"count\":2,\"foodname\":\"testFood6\",\"id\":\"6\",\"picUrl\":\"/static/images/meat.jpeg\",\"price\":\"100.00\",\"remark\":\"test\",\"type\":1}]', '2021-10-27 10:07:08', '03A');
INSERT INTO `t_order` VALUES ('a0c355c892d4dd7d208e581f0b3108e0', '1', '[{\"count\":1,\"foodname\":\"Hot spot\",\"id\":\"1\",\"picUrl\":\"/static/images/hotspot.jpg\",\"price\":\"100.00\",\"remark\":\"good\",\"type\":1},{\"count\":1,\"foodname\":\"testFood3\",\"id\":\"3\",\"picUrl\":\"/static/images/meat.jpeg\",\"price\":\"100.00\",\"remark\":\"test\",\"type\":1},{\"count\":1,\"foodname\":\"testFood4\",\"id\":\"4\",\"picUrl\":\"/static/images/meat.jpeg\",\"price\":\"100.00\",\"remark\":\"test\",\"type\":1},{\"count\":1,\"foodname\":\"testFood6\",\"id\":\"6\",\"picUrl\":\"/static/images/meat.jpeg\",\"price\":\"100.00\",\"remark\":\"test\",\"type\":1},{\"count\":1,\"foodname\":\"Cray1\",\"id\":\"12\",\"picUrl\":\"/static/images/cray.jpeg\",\"price\":\"100.00\",\"remark\":\"test\",\"type\":2},{\"count\":1,\"foodname\":\"Chips\",\"id\":\"13\",\"picUrl\":\"/static/images/chips.jpeg\",\"price\":\"200.00\",\"remark\":\"test\",\"type\":2},{\"count\":1,\"foodname\":\"Milk-tea\",\"id\":\"2\",\"picUrl\":\"/static/images/milktea.jpeg\",\"price\":\"150.00\",\"remark\":\"nice\",\"type\":3}]', '2021-10-28 09:26:10', '05B');
INSERT INTO `t_order` VALUES ('f052a4fc23a977d9abc3bce9c4fb7211', '1', '[{\"count\":1,\"foodname\":\"Hot spot\",\"id\":\"1\",\"picUrl\":\"/static/images/hotspot.jpg\",\"price\":\"100.00\",\"remark\":\"good\",\"type\":1},{\"count\":1,\"foodname\":\"testFood4\",\"id\":\"4\",\"picUrl\":\"/static/images/meat.jpeg\",\"price\":\"100.00\",\"remark\":\"test\",\"type\":1},{\"count\":1,\"foodname\":\"Milk-tea\",\"id\":\"2\",\"picUrl\":\"/static/images/milktea.jpeg\",\"price\":\"150.00\",\"remark\":\"nice\",\"type\":3}]', '2021-10-27 10:07:22', '04A');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT '' COMMENT 'primary key',
  `username` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT '' COMMENT 'username',
  `password` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT '' COMMENT 'password',
  `telephone` varchar(25) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT '' COMMENT 'elephone',
  `email` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'email',
  `status` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'status',
  `role_id` int(1) NOT NULL DEFAULT 0 COMMENT 'role ID（0-customer,1-staff）',
  `remark` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL COMMENT 'remark',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `create_by` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'create by a person',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT 'update time',
  `update_by` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'update by a person',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'xiaoming', '123456', NULL, NULL, NULL, 0, 'customer', '2021-09-19 19:18:10', NULL, '2021-10-29 13:55:06', NULL);
INSERT INTO `t_user` VALUES ('2', 'xidada', '123456', '', NULL, NULL, 1, 'clerk role', '2021-10-25 15:03:58', NULL, '2021-10-29 13:55:26', NULL);
INSERT INTO `t_user` VALUES ('3', 'xidada2', '123456', '', NULL, NULL, 1, 'clerk role', '2021-10-29 13:54:49', NULL, '2021-10-29 13:55:22', NULL);

SET FOREIGN_KEY_CHECKS = 1;
