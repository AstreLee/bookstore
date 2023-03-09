/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50714 (5.7.14-log)
 Source Host           : localhost:3306
 Source Schema         : bookstore_finalproj

 Target Server Type    : MySQL
 Target Server Version : 50714 (5.7.14-log)
 File Encoding         : 65001

 Date: 30/11/2022 15:50:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
                         `bookId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图书ID',
                         `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图书名称',
                         `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图书描述',
                         `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '图书单价',
                         `num` int(10) NULL DEFAULT NULL COMMENT '图书数量',
                         `publisher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图书出版社',
                         `categoryId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类别ID',
                         `imgUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图书图片存储路径',
                         PRIMARY KEY (`bookId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('385815392615927808', 'Python编程', 'Python编程，从入门到放弃', 68.80, 19, '人民邮电出版社', '385098649354375168', '/static/d5eada87-e034-42d3-a0dc-75e9f4a29aba.png');
INSERT INTO `book` VALUES ('385816302318522368', '云计算', '拥抱云原生', 48.50, 3, '机械工业出版社', '385092164717121536', '/static/8ea51891-fbb3-4ef1-a336-7b28d4772685.png');
INSERT INTO `book` VALUES ('385816508753776640', 'Java', '拥抱Spring', 99.90, 18, '清华大学出版社', '385091898349457408', '/static/6e7b33ff-773f-4be5-ab09-57e14b3223df.png');
INSERT INTO `book` VALUES ('385816766682501120', 'Go语言', '拥抱云原生', 65.80, 18, '人民邮电出版社', '385091966137798656', '/static/03dd0594-23de-41c5-a181-8aa0cb76bc02.png');
INSERT INTO `book` VALUES ('385817202114170880', 'SpringBoot实战', 'Spring系列', 60.70, 18, '人民邮电出版社', '385091898349457408', '/static/27058875-2972-4009-97e4-4411b2c5c304.png');

-- ----------------------------
-- Table structure for cart_item
-- ----------------------------
DROP TABLE IF EXISTS `cart_item`;
CREATE TABLE `cart_item`  (
                              `id` int(4) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                              `cartId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '购物车ID',
                              `bookId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图书ID',
                              `num` int(10) NULL DEFAULT NULL COMMENT '图书总量',
                              `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '图书总价',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart_item
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
                             `categoryId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类别ID',
                             `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类别名称',
                             `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类别描述',
                             PRIMARY KEY (`categoryId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('385091898349457408', 'Java', 'Spring春天');
INSERT INTO `category` VALUES ('385091966137798656', 'Go', '一起Go');
INSERT INTO `category` VALUES ('385092164717121536', '云计算', '拥抱云原生');
INSERT INTO `category` VALUES ('385098649354375168', 'Python', '从入门到放弃');

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
                               `id` int(4) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                               `orderId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单ID(订单项)',
                               `bookId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图书ID',
                               `num` int(10) NULL DEFAULT NULL COMMENT '图书数量',
                               `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '图书总价格',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES (14, '385817736300728320', '385816302318522400', 2, 97.00);
INSERT INTO `order_item` VALUES (15, '385817787739672576', '385815392615927800', 1, 68.80);
INSERT INTO `order_item` VALUES (16, '385817787739672576', '385816508753776640', 1, 99.90);
INSERT INTO `order_item` VALUES (17, '385817787739672576', '385816302318522400', 1, 48.50);
INSERT INTO `order_item` VALUES (18, '385817787739672576', '385816766682501100', 1, 65.80);
INSERT INTO `order_item` VALUES (19, '385817787739672576', '385817202114170900', 2, 121.40);
INSERT INTO `order_item` VALUES (20, '385817846107607040', '385816508753776640', 1, 99.90);
INSERT INTO `order_item` VALUES (21, '385817846107607040', '385816302318522400', 4, 194.00);
INSERT INTO `order_item` VALUES (22, '385819435488776192', '385816766682501100', 1, 65.80);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
                             `menuId` int(4) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '权限ID',
                             `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
                             `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
                             PRIMARY KEY (`menuId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2003 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (2001, '访问前台界面', 'sys:user:info');
INSERT INTO `sys_menu` VALUES (2002, '后台管理', 'sys:admin:info');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                             `roleId` int(4) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                             `roleName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
                             PRIMARY KEY (`roleId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1004 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1001, 'USER');
INSERT INTO `sys_role` VALUES (1002, 'ADMIN');
INSERT INTO `sys_role` VALUES (1003, 'SUPERADMIN');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
                                  `id` int(4) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '标识ID',
                                  `roleId` int(4) NOT NULL COMMENT '角色ID',
                                  `menuId` int(4) NULL DEFAULT NULL COMMENT '权限ID',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3003 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (3001, 1001, 2001);
INSERT INTO `sys_role_menu` VALUES (3002, 1002, 2002);

-- ----------------------------
-- Table structure for sys_user_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_admin`;
CREATE TABLE `sys_user_admin`  (
                                   `userId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
                                   `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
                                   `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
                                   `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户手机',
                                   `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
                                   `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户状态',
                                   PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_admin
-- ----------------------------
INSERT INTO `sys_user_admin` VALUES ('385048925230338048', 'admin2', '$2a$10$Ss7Tl/l//0OFkXffCL2Zx.kwPgarCRJIrN3DOHH55t9QCqg2C8iTy', '13872025579', '2684492032@qq.com', 'NORMAL');
INSERT INTO `sys_user_admin` VALUES ('385089194432663552', 'root', '$2a$10$Vz.uPc6pSSOpzc/uUsypzOZq2wJRvPWvINUlVl5x2sAVV/jf0PYWa', '13872025579', 'root@163.com', 'NORMAL');

-- ----------------------------
-- Table structure for sys_user_regular
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_regular`;
CREATE TABLE `sys_user_regular`  (
                                     `userId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
                                     `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
                                     `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
                                     `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户电话号码',
                                     `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
                                     `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户地址/省',
                                     `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户地址/市',
                                     `region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户地址/区',
                                     `details` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户地址/详细地址',
                                     `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户状态',
                                     PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_regular
-- ----------------------------
INSERT INTO `sys_user_regular` VALUES ('385817676737417216', 'ruofei', '$2a$10$04.KjsXmgJpqgX5XxMkgw.LA8SnOJqwjCsH86ZCZWCM8TjkHwHfaa', '13872025579', '2684492032@qq.com', '湖北省', '武汉市', '武昌区', '三期A5005', 'NORMAL');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
                                  `id` int(4) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '标识ID',
                                  `userId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户ID',
                                  `roleId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色ID',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1011 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1001, '385048925230338048', '1002');
INSERT INTO `sys_user_role` VALUES (1002, '385089194432663552', '1003');
INSERT INTO `sys_user_role` VALUES (1010, '385817676737417216', '1001');

-- ----------------------------
-- Table structure for user_cart
-- ----------------------------
DROP TABLE IF EXISTS `user_cart`;
CREATE TABLE `user_cart`  (
                              `id` int(4) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                              `cartId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '购物车ID',
                              `userId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '购物车所属用户ID',
                              `num` int(10) NULL DEFAULT NULL COMMENT '购物车商品总数',
                              `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '购物车商品总额',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_cart
-- ----------------------------
INSERT INTO `user_cart` VALUES (9, '385817676989075456', '385817676737417216', 0, 0.00);

-- ----------------------------
-- Table structure for user_order
-- ----------------------------
DROP TABLE IF EXISTS `user_order`;
CREATE TABLE `user_order`  (
                               `id` int(4) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                               `orderId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单ID',
                               `userId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单所属用户ID',
                               `num` int(10) NULL DEFAULT NULL COMMENT '订单中所含商品数量',
                               `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '订单中所含商品总价值',
                               `state` int(1) NULL DEFAULT NULL COMMENT '订单状态',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_order
-- ----------------------------
INSERT INTO `user_order` VALUES (8, '385817736300728320', '385817676737417216', 2, 97.00, 1);
INSERT INTO `user_order` VALUES (9, '385817787739672576', '385817676737417216', 6, 404.40, 2);
INSERT INTO `user_order` VALUES (10, '385817846107607040', '385817676737417216', 5, 293.90, -1);
INSERT INTO `user_order` VALUES (11, '385819435488776192', '385817676737417216', 1, 65.80, 1);

SET FOREIGN_KEY_CHECKS = 1;
