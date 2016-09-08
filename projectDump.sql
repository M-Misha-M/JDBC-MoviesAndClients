-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: cursova
-- ------------------------------------------------------
-- Server version	5.7.12-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `cursova`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `cursova` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cursova`;

--
-- Table structure for table `arend`
--

DROP TABLE IF EXISTS `arend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `arend` (
  `id_arend` int(11) NOT NULL AUTO_INCREMENT,
  `startDate` date DEFAULT '2016-06-23',
  `endDate` date DEFAULT '2016-05-21',
  `unique_id` int(11) NOT NULL,
  `status` varchar(45) DEFAULT 'прокат',
  `price` decimal(13,2) DEFAULT '0.00',
  `movie_id` int(11) NOT NULL,
  PRIMARY KEY (`id_arend`),
  KEY `FK_Movies_idx` (`unique_id`),
  KEY `FK_Movies_idx1` (`movie_id`),
  CONSTRAINT `FK_Clients` FOREIGN KEY (`unique_id`) REFERENCES `sellers` (`unique_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Movies` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`movie_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arend`
--

LOCK TABLES `arend` WRITE;
/*!40000 ALTER TABLE `arend` DISABLE KEYS */;
INSERT INTO `arend` VALUES (1,'2016-01-15','2016-01-17',1,'прокат',50.00,1),(2,'2016-02-13','2016-02-15',2,'прокат',100.00,2),(3,'2016-03-01','2016-03-04',3,'прокат',40.00,3),(4,'2016-03-04','2016-03-08',4,'прострочений',60.00,4),(5,'2016-04-01','2016-04-02',5,'прокат',70.00,5),(6,'2016-05-15','2016-05-19',14,'прострочений',80.00,6),(7,'2016-06-16','2016-06-18',7,'прокат',90.00,7),(8,'2016-06-18','2016-06-23',8,'прострочений',45.00,8),(11,'2016-06-04','2016-06-06',9,'прокат',30.00,9),(18,'2016-06-23','2016-05-21',11,'прострочений',10.00,12),(19,'2016-06-23','2016-05-21',8,'прокат',0.00,12),(20,'2016-05-06','2016-05-12',6,'прострочений',50.00,15),(23,'2016-07-21','2016-07-25',14,'прострочений',34.00,7),(24,'2016-08-12','2016-08-13',3,'прокат',20.00,1);
/*!40000 ALTER TABLE `arend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movies` (
  `movie_id` int(11) NOT NULL AUTO_INCREMENT,
  `movie_name` varchar(45) NOT NULL,
  `genre` varchar(45) NOT NULL,
  `release_date` varchar(4) NOT NULL,
  `unique_id` int(11) NOT NULL,
  PRIMARY KEY (`movie_id`),
  KEY `sellers_idx` (`unique_id`),
  CONSTRAINT `FK_SELLERS` FOREIGN KEY (`unique_id`) REFERENCES `sellers` (`unique_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (1,'Трасформери','Екшн','2007',5),(2,'Аліса в Задзеркаллі','Мультфільм','2016',1),(3,'Футурама','Мультфільм','1996',4),(4,'ДМБ','Комедія','2000',2),(5,'Джеймс Бонд','Екшн','1996',2),(6,'Аліса в Країні Чудес','Фантастика','2013',6),(7,'Чужий','Жахи','1995',3),(8,'Чужий2','Жахи','1997',7),(9,'Сімпсони','Мультфільм','1980',6),(11,'Термінатор','Екшн','1984',10),(12,'Особливо небезспечний','Екшн','2007',11),(15,'Інтерстеллар','Екшн','2014',13);
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger movies_after_ins_trig after insert on movies
for each row
begin
  insert into arend (movie_id) values (new.movie_id);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `sellers`
--

DROP TABLE IF EXISTS `sellers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sellers` (
  `unique_id` int(11) NOT NULL AUTO_INCREMENT,
  `seller_name` varchar(45) NOT NULL,
  `seller_lastname` varchar(45) NOT NULL,
  `sex` varchar(45) NOT NULL,
  PRIMARY KEY (`unique_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sellers`
--

LOCK TABLES `sellers` WRITE;
/*!40000 ALTER TABLE `sellers` DISABLE KEYS */;
INSERT INTO `sellers` VALUES (1,'Богдан','Тарантіно','чоловік'),(2,'Олексій','Вітряний','чоловік'),(3,'Олена','Дубич','жінка'),(4,'Вікторія','Опанасюк','жінка'),(5,'Павло','Хороший','чоловік'),(6,'Олексій','Кухарчук','чоловік'),(7,'Сергій','Куксов','чоловік'),(8,'Іван','Бальо','чоловік'),(9,'Михайло','Кончук','чоловік'),(10,'Ігор','Шендера','чоловік'),(11,'Андрій','Клюйко','чоловік'),(12,'Текст','Текст','чоловік'),(13,'Корито','Іван','чоловік'),(14,'Кобилянська','Ольга','жінка');
/*!40000 ALTER TABLE `sellers` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger sellers_after_ins_trig after insert on sellers
for each row
begin
  insert into arend (unique_id) values (new.unique_id);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Temporary view structure for view `test`
--

DROP TABLE IF EXISTS `test`;
/*!50001 DROP VIEW IF EXISTS `test`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `test` AS SELECT 
 1 AS `unique_id`,
 1 AS `seller_name`,
 1 AS `seller_lastname`,
 1 AS `sex`*/;
SET character_set_client = @saved_cs_client;

--
-- Current Database: `cursova`
--

USE `cursova`;

--
-- Final view structure for view `test`
--

/*!50001 DROP VIEW IF EXISTS `test`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `test` AS select `sellers`.`unique_id` AS `unique_id`,`sellers`.`seller_name` AS `seller_name`,`sellers`.`seller_lastname` AS `seller_lastname`,`sellers`.`sex` AS `sex` from `sellers` where (`sellers`.`sex` = 'чоловік') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-08 19:44:44
