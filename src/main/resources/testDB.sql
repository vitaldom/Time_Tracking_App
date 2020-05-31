-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: time_tracking_app_db
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(500) NOT NULL,
  `time_tracked` int DEFAULT NULL,
  `report` varchar(500) DEFAULT NULL,
  `status` enum('IN_PROCESS','DELETION_REQUESTED','DONE') NOT NULL,
  `admin_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `admin_id_idx` (`admin_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `admin_id` FOREIGN KEY (`admin_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (2,'Test','Test',14,NULL,'DELETION_REQUESTED',18,19),(3,'Test2','Test2',7,NULL,'DELETION_REQUESTED',18,19),(4,'Clean house','Clean clean',NULL,NULL,'IN_PROCESS',18,20),(5,'Walk','walk',NULL,NULL,'IN_PROCESS',18,25),(8,'test','tewet',NULL,NULL,'IN_PROCESS',18,17),(9,'Sleep','Sleep well',15,NULL,'IN_PROCESS',18,19),(10,'Build house','Big white house',NULL,NULL,'IN_PROCESS',18,19),(11,'test','test',NULL,NULL,'IN_PROCESS',18,17),(12,'Run','run fast',NULL,NULL,'IN_PROCESS',18,20);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_request`
--

DROP TABLE IF EXISTS `activity_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_request` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `user_id` int NOT NULL,
  `assigned_activity_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_request`
--

LOCK TABLES `activity_request` WRITE;
/*!40000 ALTER TABLE `activity_request` DISABLE KEYS */;
INSERT INTO `activity_request` VALUES (1,NULL,NULL,19,NULL);
/*!40000 ALTER TABLE `activity_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `declarations`
--

DROP TABLE IF EXISTS `declarations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `declarations` (
  `id` int NOT NULL,
  `author_login` varchar(45) NOT NULL,
  `inspector_login` varchar(45) DEFAULT NULL,
  `declaration_year` varchar(45) NOT NULL,
  `tax_category` varchar(45) NOT NULL,
  `income` bigint NOT NULL,
  `tax_sum_declared` bigint NOT NULL,
  `status` varchar(45) NOT NULL,
  `correction_message` varchar(700) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `author_login_idx` (`author_login`),
  KEY `inspector_login_idx` (`inspector_login`),
  CONSTRAINT `author_login` FOREIGN KEY (`author_login`) REFERENCES `user` (`login`),
  CONSTRAINT `inspector_login` FOREIGN KEY (`inspector_login`) REFERENCES `user` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `declarations`
--

LOCK TABLES `declarations` WRITE;
/*!40000 ALTER TABLE `declarations` DISABLE KEYS */;
INSERT INTO `declarations` VALUES (1,'john','sidr','2020','EMPLOYEE',1000,100,'SUBMITTED',NULL),(2,'john','ivan','2017','PREFERENTIAL',1000000,333,'UNDER_CORRECTION','test'),(3,'boroda','ivan','2019','EMPLOYEE',42984824,42424,'APPEALED','test'),(4,'boroda','ivan','2020','ENTREPRENEUR',48248287874327,42424,'APPEALED','test'),(5,'boroda','ivan','2019','PREFERENTIAL',438729874827,3141423,'APPROVED','тест'),(6,'john','ivan','2020','PREFERENTIAL',300000,2000,'SUBMITTED',NULL),(7,'boroda','ivan','2019','ENTREPRENEUR',400000,2000,'SUBMITTED',NULL),(8,'boroda','ivan','2015','PREFERENTIAL',3000000,10000,'SUBMITTED',NULL),(9,'boroda','petr','2016','PREFERENTIAL',400043,20000,'SUBMITTED','Too bad'),(10,'boroda','ivan','2017','EMPLOYEE',5000000,20000,'APPROVED',NULL),(11,'boroda','petr','2015','EMPLOYEE',2000,200,'APPROVED','Year should be 2015'),(12,'boroda','ivan','2017','PREFERENTIAL',4234243,424,'SUBMITTED',NULL),(13,'boroda','petr','2020','PREFERENTIAL',42342342,43242,'SUBMITTED',NULL);
/*!40000 ALTER TABLE `declarations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `second_name` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` enum('USER','ADMINISTRATOR') NOT NULL,
  `reports_assigned` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (17,'Snow','John','john','1','USER',NULL),(18,'Ivanov','Ivan','ivan','1','ADMINISTRATOR',14),(19,'Бородова','Наталія','boroda','1','USER',NULL),(20,'Pupkin','Vasia','vasia','1','USER',NULL),(21,'Кононученко','Анатолій','anar','1','USER',NULL),(22,'Sidorov','Sidr','sidr','1','ADMINISTRATOR',33),(24,'Petrov','Petr','petr','1','ADMINISTRATOR',14),(25,'Антонов','Антон','anton','1','USER',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-31 19:26:27
