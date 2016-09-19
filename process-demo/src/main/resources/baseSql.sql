/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50616
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2016-09-18 10:22:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(50) NOT NULL COMMENT '菜单编号',
  `menuName` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `menuNameDesc` varchar(255) DEFAULT NULL COMMENT '菜单描述',
  `menuParentId` int(255) DEFAULT NULL COMMENT '父级menu（0为根目录）',
  `menuIcon` varchar(255) DEFAULT 'fa fa-link' COMMENT '小图标',
  `menuUrl` varchar(255) DEFAULT NULL COMMENT '用来路由跳转',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '用户管理', '用户管理', '0', 'fa fa-link', 'javascript:void(0)');
INSERT INTO `t_menu` VALUES ('2', '用户新增', '用户新增', '1', 'fa fa-link', 'javascript:void(0)');
INSERT INTO `t_menu` VALUES ('3', '用户更新', '用户更新', '1', 'fa fa-link', 'javascript:void(0)');
INSERT INTO `t_menu` VALUES ('4', '用户删除', '用户删除', '1', 'fa fa-link', 'javascript:void(0)');
INSERT INTO `t_menu` VALUES ('5', '用户查询', '用户查询', '1', 'fa fa-link', 'javascript:void(0)');
INSERT INTO `t_menu` VALUES ('6', '角色管理', '角色管理', '0', 'fa fa-link', 'javascript:void(0)');
INSERT INTO `t_menu` VALUES ('7', '权限管理', '权限管理', '0', 'fa fa-link', 'javascript:void(0)');
INSERT INTO `t_menu` VALUES ('8', '角色权限', '角色权限', '7', 'fa fa-link', 'javascript:void(0)');
INSERT INTO `t_menu` VALUES ('9', '菜单权限', '菜单权限', '7', 'fa fa-link', 'javascript:void(0)');
INSERT INTO `t_menu` VALUES ('10', '用户权限', '用户权限', '7', 'fa fa-link', 'javascript:void(0)');
INSERT INTO `t_menu` VALUES ('11', '监控', '监控', '0', 'fa fa-link', '#druid');
INSERT INTO `t_menu` VALUES ('12', '我的邮箱', '我的邮箱', '0', 'fa fa-envelope', 'javascript:void(0)');
INSERT INTO `t_menu` VALUES ('13', '发送邮件', '发送邮件', '12', 'fa fa-envelope', '#email');
INSERT INTO `t_menu` VALUES ('14', '收取邮件', '收取邮件', '12', 'fa fa-envelope', '#email');
INSERT INTO `t_menu` VALUES ('15', '我的日历', '我的日历', '0', 'fa fa-calendar', 'javascript:void(0)');
INSERT INTO `t_menu` VALUES ('16', '测试三级目录', '测试三级目录', '2', 'fa fa-link', 'javascript:void(0)');

-- ----------------------------
-- Table structure for t_menu_resources
-- ----------------------------
DROP TABLE IF EXISTS `t_menu_resources`;
CREATE TABLE `t_menu_resources` (
  `id` int(50) NOT NULL AUTO_INCREMENT COMMENT '菜单权限编号',
  `menuId` int(50) DEFAULT NULL COMMENT '菜单编号',
  `resourcesId` int(50) DEFAULT NULL COMMENT '资源权限编号',
  PRIMARY KEY (`id`),
  KEY `fk_menu_resources_menuId` (`menuId`),
  KEY `fk_menu_resouces_resourcesId` (`resourcesId`),
  CONSTRAINT `fk_menu_resouces_resourcesId` FOREIGN KEY (`resourcesId`) REFERENCES `t_resources` (`resourcesId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_menu_resources_menuId` FOREIGN KEY (`menuId`) REFERENCES `t_menu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu_resources
-- ----------------------------
INSERT INTO `t_menu_resources` VALUES ('1', '5', '12');

-- ----------------------------
-- Table structure for t_resources
-- ----------------------------
DROP TABLE IF EXISTS `t_resources`;
CREATE TABLE `t_resources` (
  `resourcesId` int(50) NOT NULL AUTO_INCREMENT COMMENT '资源编号',
  `resourcesName` varchar(255) DEFAULT NULL COMMENT '资源名称',
  `resourcesType` varchar(50) DEFAULT NULL COMMENT '资源类别（menu和button两种）',
  `resourcesUrl` varchar(255) DEFAULT NULL,
  `parentId` int(50) DEFAULT NULL COMMENT '资源所属（0表示跟节点，与menu对应，相当于menu的一个菜单）',
  `resourcesStatus` int(1) DEFAULT '1' COMMENT '资源是否有效（1表示有效，0表示失效）',
  PRIMARY KEY (`resourcesId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_resources
-- ----------------------------
INSERT INTO `t_resources` VALUES ('12', '查询当前用户', 'button', '/getUser', null, '1');
INSERT INTO `t_resources` VALUES ('13', '查询当前用户的菜单栏', 'button', '/queryMenu', null, '1');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `roleId` int(50) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `roleCode` int(50) DEFAULT NULL COMMENT '角色机构代码',
  `roleName` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `roleDescription` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `orgId` int(50) DEFAULT NULL COMMENT '角色所属公司机构代码',
  `departmentId` int(50) DEFAULT NULL,
  `departmentName` varchar(255) DEFAULT NULL COMMENT '所属部门名称',
  `status` int(1) DEFAULT '1' COMMENT '角色是否有效（1表示有效，0表示失效）',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '34000', 'admin', '超级管理员', null, null, null, '1');
INSERT INTO `t_role` VALUES ('2', '34001', 'user', '普通用户', null, null, null, '1');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `id` int(50) NOT NULL AUTO_INCREMENT COMMENT '角色-菜单编号',
  `roleId` int(50) DEFAULT NULL COMMENT '角色编号',
  `menuId` int(50) DEFAULT NULL COMMENT '菜单编号',
  PRIMARY KEY (`id`),
  KEY `fk_role_menu_roleid` (`roleId`),
  KEY `fk_role_menu_menuId` (`menuId`),
  CONSTRAINT `fk_role_menu_menuId` FOREIGN KEY (`menuId`) REFERENCES `t_menu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_menu_roleid` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`roleid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('1', '1', '1');
INSERT INTO `t_role_menu` VALUES ('2', '1', '2');
INSERT INTO `t_role_menu` VALUES ('3', '1', '3');
INSERT INTO `t_role_menu` VALUES ('4', '1', '4');
INSERT INTO `t_role_menu` VALUES ('5', '1', '5');
INSERT INTO `t_role_menu` VALUES ('6', '1', '6');
INSERT INTO `t_role_menu` VALUES ('7', '1', '7');
INSERT INTO `t_role_menu` VALUES ('8', '1', '8');
INSERT INTO `t_role_menu` VALUES ('9', '1', '9');
INSERT INTO `t_role_menu` VALUES ('10', '1', '10');
INSERT INTO `t_role_menu` VALUES ('11', '1', '11');
INSERT INTO `t_role_menu` VALUES ('12', '1', '12');
INSERT INTO `t_role_menu` VALUES ('13', '1', '13');
INSERT INTO `t_role_menu` VALUES ('14', '1', '14');
INSERT INTO `t_role_menu` VALUES ('15', '1', '15');
INSERT INTO `t_role_menu` VALUES ('16', '1', '16');

-- ----------------------------
-- Table structure for t_role_resources
-- ----------------------------
DROP TABLE IF EXISTS `t_role_resources`;
CREATE TABLE `t_role_resources` (
  `id` int(50) NOT NULL AUTO_INCREMENT COMMENT '用户资源表编号',
  `resourcesId` int(50) DEFAULT NULL COMMENT '资源编号',
  `roleId` int(50) DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`),
  KEY `fk_resources_id` (`resourcesId`),
  KEY `fk_role_id` (`roleId`),
  CONSTRAINT `fk_resources_id` FOREIGN KEY (`resourcesId`) REFERENCES `t_resources` (`resourcesid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_id` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`roleid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_resources
-- ----------------------------
INSERT INTO `t_role_resources` VALUES ('3', '12', '1');
INSERT INTO `t_role_resources` VALUES ('4', '12', '2');
INSERT INTO `t_role_resources` VALUES ('5', '13', '1');
INSERT INTO `t_role_resources` VALUES ('6', '13', '2');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userId` int(50) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `userName` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) DEFAULT NULL COMMENT '加密密码的盐',
  `orgId` int(50) DEFAULT NULL COMMENT '所属公司机构代码',
  `telephone` varchar(50) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机联系方式',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `entryTime` datetime DEFAULT NULL COMMENT '入职时间',
  `departmentId` varchar(255) DEFAULT NULL COMMENT '所属部门机构代码',
  `status` int(1) DEFAULT '1' COMMENT '是否有效（1表示有效，0表示失效）',
  `lastLoginIp` varchar(255) DEFAULT NULL COMMENT '最后一次登录ip',
  `userAgent` varchar(255) DEFAULT NULL COMMENT '最后一次访问工具',
  `userImage` varchar(255) DEFAULT NULL COMMENT '用户头像',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', null, null, null, null, '2016-09-08 09:11:56', null, '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36', null);
INSERT INTO `t_user` VALUES ('2', 'user', '4b2a98dc4d7056295b9d45f8cad89590', '1d0a3eaa34f564472c2d2016d7b51efc', null, null, null, null, null, null, '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0', null);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int(50) NOT NULL AUTO_INCREMENT COMMENT '用户-角色编号',
  `userId` int(50) DEFAULT NULL COMMENT '用户编号',
  `roleId` int(50) DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`),
  KEY `fk_userid` (`userId`),
  KEY `fk_roleid` (`roleId`),
  CONSTRAINT `fk_roleid` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`roleid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_userid` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1', '1');
INSERT INTO `t_user_role` VALUES ('2', '1', '2');
INSERT INTO `t_user_role` VALUES ('3', '2', '1');
