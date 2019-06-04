/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : gtms

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 04/06/2019 14:08:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_init
-- ----------------------------
DROP TABLE IF EXISTS `t_init`;
CREATE TABLE `t_init`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_num` tinyint(2) NULL DEFAULT NULL COMMENT '学生最多选题数量',
  `teacher_num` tinyint(2) NULL DEFAULT NULL COMMENT '老师最多带学生数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_init
-- ----------------------------
INSERT INTO `t_init` VALUES (1, 1, 5);

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单文本',
  `menu_belong` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用人',
  `menu_status` tinyint(1) NULL DEFAULT NULL COMMENT '状态',
  `menu_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图片',
  `menu_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, '个人信息', '1', 1, 'images/leftico02.png', 'personInfo.html');
INSERT INTO `t_menu` VALUES (2, '修改密码', '1', 1, 'images/leftico03.png', 'modifyPwd.html');
INSERT INTO `t_menu` VALUES (3, '学生选题', '1', 1, 'images/leftico01.png', 'studentSelectThesis.html');
INSERT INTO `t_menu` VALUES (4, '查看信息', '1', 1, 'images/leftico04.png', 'viewSelectThesisInfo.html');
INSERT INTO `t_menu` VALUES (5, '下载任务书', '1', 1, 'images/leftico04.png', 'studentDownloadTask.html');
INSERT INTO `t_menu` VALUES (6, '上传论文', '1', 1, 'images/leftico04.png', 'studentUploadThesis.html');
INSERT INTO `t_menu` VALUES (7, '个人信息', '2', 1, 'images/leftico02.png', 'personInfo.html');
INSERT INTO `t_menu` VALUES (8, '修改密码', '2', 1, 'images/leftico03.png', 'modifyPwd.html');
INSERT INTO `t_menu` VALUES (9, '给定题目', '2', 1, 'images/leftico02.png', 'teacherGiveThesis.html');
INSERT INTO `t_menu` VALUES (10, '选定学生', '2', 1, 'images/leftico02.png', 'teacherSelectStudent.html');
INSERT INTO `t_menu` VALUES (11, '上传任务书', '2', 1, 'images/leftico02.png', 'teacherUploadTask.html');
INSERT INTO `t_menu` VALUES (12, '下载论文', '2', 1, 'images/leftico02.png', 'teacherDownloadThesis.html');
INSERT INTO `t_menu` VALUES (13, '个人信息', '3', 1, 'images/leftico02.png', 'personInfo.html');
INSERT INTO `t_menu` VALUES (14, '添加用户', '3', 1, 'images/leftico02.png', 'manageAddUser.html');
INSERT INTO `t_menu` VALUES (15, '数据分析', '3', 1, 'images/leftico02.png', 'dataAnalysis.html');
INSERT INTO `t_menu` VALUES (16, '设置数据', '3', 1, 'images/leftico02.png', 'manageSetData.html');
INSERT INTO `t_menu` VALUES (17, '选题情况', '3', 1, 'images/leftico02.png', 'selectThesisDetail.html');
INSERT INTO `t_menu` VALUES (18, '控制进程', '3', 1, 'images/leftico02.png', 'controlProcess.html');

-- ----------------------------
-- Table structure for t_no_pass_thesis
-- ----------------------------
DROP TABLE IF EXISTS `t_no_pass_thesis`;
CREATE TABLE `t_no_pass_thesis`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_class` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teacher_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teacher_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `thesis_no` bigint(20) NULL DEFAULT NULL,
  `thesis_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teacher_opinion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_student_info
-- ----------------------------
DROP TABLE IF EXISTS `t_student_info`;
CREATE TABLE `t_student_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `student_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学号',
  `student_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `student_major` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业',
  `student_instructor` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '辅导员',
  `student_class` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级',
  `student_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `student_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_student_info
-- ----------------------------
INSERT INTO `t_student_info` VALUES (18, 27, '201501420251', '黄舒蕾', '软件工程', '刘琨', '15软本2班', '15773123658', '1024516935@qq.com');
INSERT INTO `t_student_info` VALUES (19, 28, '201501420250', '倪叶', '软件工程', '刘琨', '15软本2班', '15111086682', '1821462202@qq.com');
INSERT INTO `t_student_info` VALUES (20, 29, '201501420129', '曹港回', '软件工程', '刘琨', '15软本1班', '18229844513', '16960433820@qq.com');
INSERT INTO `t_student_info` VALUES (21, 31, '201501420253', '朱凌华', '软件工程', '刘琨', '15软件本2班', '17673625186', '525133372@qq.com');

-- ----------------------------
-- Table structure for t_student_teacher_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_student_teacher_relation`;
CREATE TABLE `t_student_teacher_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学号',
  `student_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `student_class` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级',
  `teacher_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '老师编号',
  `teacher_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '老师名字',
  `thesis_no` bigint(20) NULL DEFAULT NULL COMMENT '论文编号',
  `thesis_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '论文题目',
  `teacher_opinion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '老师意见',
  `opinion_flag` int(2) NULL DEFAULT NULL COMMENT '意见标识（1：同意，2：不同意）',
  `task_status` int(1) NULL DEFAULT 2 COMMENT '是否提供了任务书（1：已提供，2：未提供）',
  `task_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务书路径',
  `thesis_status` int(1) NULL DEFAULT 2 COMMENT '论文上传状态（1：已上传，2：未上传）',
  `thesis_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '论文路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_student_teacher_relation
-- ----------------------------
INSERT INTO `t_student_teacher_relation` VALUES (15, '201501420251', '黄舒蕾', '15软本2班', '20150101', '陈老师', 24, '高校毕业论文管理系统', '', 1, 1, '24.docx', 1, '24.docx');
INSERT INTO `t_student_teacher_relation` VALUES (16, '201501420250', '倪叶', '15软本2班', '20150101', '陈老师', 26, '校园二手物品交易市场', '', 1, 1, '26.docx', 1, '26.docx');

-- ----------------------------
-- Table structure for t_teacher_info
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher_info`;
CREATE TABLE `t_teacher_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `teacher_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '老师编号',
  `teacher_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '老师姓名',
  `teacher_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称',
  `teacher_education` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历',
  `teacher_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `teacher_email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_teacher_info
-- ----------------------------
INSERT INTO `t_teacher_info` VALUES (7, 7, '960407', '陈敏', '导师', '博士', '13574814856', '2481662421@qq.com');
INSERT INTO `t_teacher_info` VALUES (9, 30, '20150101', '陈老师', '导师', '博士', '13574814856', '2481662421@qq.com');
INSERT INTO `t_teacher_info` VALUES (10, 32, '20150102', '陈亮', '讲师', '博士', '15773123659', '1024516935@qq.com');

-- ----------------------------
-- Table structure for t_thesis_info
-- ----------------------------
DROP TABLE IF EXISTS `t_thesis_info`;
CREATE TABLE `t_thesis_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `thesis_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '论文题目',
  `teacher_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '老师编号',
  `teacher_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '老师姓名',
  `notice_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `select_num` int(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '被选次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_thesis_info
-- ----------------------------
INSERT INTO `t_thesis_info` VALUES (24, '高校毕业论文管理系统', '20150101', '陈老师', '要求实现基础功能，界面简洁美观', 1);
INSERT INTO `t_thesis_info` VALUES (25, '社交平台app', '20150101', '陈老师', '界面美观', 0);
INSERT INTO `t_thesis_info` VALUES (26, '校园二手物品交易市场', '20150101', '陈老师', '要求用java语言', 1);
INSERT INTO `t_thesis_info` VALUES (27, 'B/S架构下的进销存系统', '20150101', '陈老师', '采用B/S架构', 0);
INSERT INTO `t_thesis_info` VALUES (28, 'B/S架构下的进销存系统', '20150101', '陈老师', '淡粉色的发', 0);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户',
  `user_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `user_type` tinyint(1) NULL DEFAULT NULL COMMENT '用户类型（1学生，2老师，3管理员）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (7, '960407', '960407', 3);
INSERT INTO `t_user` VALUES (27, '201501420251', '201501420251', 1);
INSERT INTO `t_user` VALUES (28, '201501420250', '201501420250', 1);
INSERT INTO `t_user` VALUES (29, '201501420129', '201501420129', 1);
INSERT INTO `t_user` VALUES (30, '20150101', '20150101', 2);
INSERT INTO `t_user` VALUES (31, '201501420253', '201501420253', 1);
INSERT INTO `t_user` VALUES (32, '20150102', '20150102', 2);

SET FOREIGN_KEY_CHECKS = 1;
