/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 100422
 Source Host           : localhost:3306
 Source Schema         : srbademodb

 Target Server Type    : MySQL
 Target Server Version : 100422
 File Encoding         : 65001

 Date: 27/07/2023 17:29:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
CREATE DATABASE IF NOT EXISTS srbademoDb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE srbademoDb;
-- ----------------------------
-- Table structure for courses
-- ----------------------------
CREATE TABLE IF NOT EXISTS `courses`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hours` int(11) NOT NULL,
  `max_students` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of courses
-- ----------------------------
INSERT INTO `courses` VALUES (1, 'course-1', 0, 10);
INSERT INTO `courses` VALUES (3, 'course-2', 0, 100);
INSERT INTO `courses` VALUES (4, 'course-3', 0, 30);
INSERT INTO `courses` VALUES (5, 'course-4', 0, 40);
INSERT INTO `courses` VALUES (6, 'course-5', 0, 50);

-- ----------------------------
-- Table structure for lectures
-- ----------------------------
CREATE TABLE IF NOT EXISTS `lectures`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `field_of_study` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lectures
-- ----------------------------
INSERT INTO `lectures` VALUES (1, 'lecture-1', 'spring boot');
INSERT INTO `lectures` VALUES (3, 'lecture-3', 'react js');

-- ----------------------------
-- Table structure for students
-- ----------------------------
CREATE TABLE IF NOT EXISTS `students`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `course_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK6jiqckumc6tm0h9otqbtqhgnr`(`course_id`) USING BTREE,
  CONSTRAINT `FK6jiqckumc6tm0h9otqbtqhgnr` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES (3, 'Jone Dae', 20, 'some1 where', 'some1@email.com', '+123-456-789', 1);
INSERT INTO `students` VALUES (4, 'Nikola Jovanovic', 22, 'some2 where', 'some2@email.com', '+223-456-789', 1);
INSERT INTO `students` VALUES (5, 'Marko Petrovic', 23, 'some3 where', 'some3@email.com', '+323-456-789', 3);
INSERT INTO `students` VALUES (6, 'Aleksandar Nikolic', 24, 'some4 where', 'some4@email.com', '+423-456-789', 3);
INSERT INTO `students` VALUES (7, 'Stefan Ivanovic', 25, 'some5 where', 'some5@email.com', '+523-456-789', 5);

SET FOREIGN_KEY_CHECKS = 1;
