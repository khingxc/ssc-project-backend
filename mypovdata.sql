-- MySQL dump 10.13  Distrib 8.0.25, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: myPovUserInfo
-- ------------------------------------------------------
-- Server version	5.5.5-10.5.11-MariaDB-1:10.5.11+maria~focal

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) unsigned NOT NULL,
  `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1001,1,9223372036854775806,1,1,1000,0,0);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_diary`
--

DROP TABLE IF EXISTS `tbl_diary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_diary` (
  `diary_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `diary_text` varchar(2000) NOT NULL,
  `date` varchar(255) NOT NULL,
  PRIMARY KEY (`diary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_diary`
--

LOCK TABLES `tbl_diary` WRITE;
/*!40000 ALTER TABLE `tbl_diary` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_diary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_ques_admin`
--

DROP TABLE IF EXISTS `tbl_ques_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_ques_admin` (
  `qid` int(11) NOT NULL AUTO_INCREMENT,
  `qtext` varchar(2000) NOT NULL,
  `date_add` varchar(255) NOT NULL,
  PRIMARY KEY (`qid`),
  UNIQUE KEY `tbl_ques_admin_qid_uindex` (`qid`),
  UNIQUE KEY `tbl_ques_admin_qtext_uindex` (`qtext`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_ques_admin`
--

LOCK TABLES `tbl_ques_admin` WRITE;
/*!40000 ALTER TABLE `tbl_ques_admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_ques_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_ques_user`
--

DROP TABLE IF EXISTS `tbl_ques_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_ques_user` (
  `qaid` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `question` varchar(255) NOT NULL,
  `answer` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  PRIMARY KEY (`qaid`),
  UNIQUE KEY `question_table_question_name_uindex` (`question`),
  UNIQUE KEY `question_table_question_text_uindex` (`answer`),
  UNIQUE KEY `tbl_ques_user_qaid_uindex` (`qaid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='for daily question';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_ques_user`
--

LOCK TABLES `tbl_ques_user` WRITE;
/*!40000 ALTER TABLE `tbl_ques_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_ques_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_tasks`
--

DROP TABLE IF EXISTS `tbl_tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_tasks` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `task_description` varchar(255) NOT NULL,
  `completed` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  PRIMARY KEY (`task_id`),
  UNIQUE KEY `tbl_tasks_task_id_uindex` (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_tasks`
--

LOCK TABLES `tbl_tasks` WRITE;
/*!40000 ALTER TABLE `tbl_tasks` DISABLE KEYS */;
INSERT INTO `tbl_tasks` VALUES (1,'admin@hahaha.com','drink water 8 times a day','false','2021-07-22'),(2,'admin@hahaha.com','drink water 8 times a day','false','2021-07-22');
/*!40000 ALTER TABLE `tbl_tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_user` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `display_name` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tbl_user_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES (6,'admin@hahaha.com','$2a$10$I9pyssylSoNZ.seFNUWEu..WcVaUmt7yGmPNInv7ohpiWLQh43uAO','I am Admin','USER'),(8,'khungkhing@yahoo.com','$2a$10$5c.ji6H3jpxvujhVCRuawORC/Eb9zPs2o7fuG/TS.rmtf5y/ZOOhq','khungkhing','Admin');
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-23  0:57:02
