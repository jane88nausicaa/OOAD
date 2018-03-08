CREATE DATABASE  IF NOT EXISTS `flyway` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `flyway`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: flyway
-- ------------------------------------------------------
-- Server version	5.7.17

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
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight` (
  `flightNo` varchar(45) NOT NULL,
  `eSeats` int(11) NOT NULL,
  `bSeats` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`flightNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES ('A-11',100,20,1),('A99',100,10,0),('B-11',200,10,1),('C-97',150,20,0),('C123',100,23,1),('E-90',120,10,0),('S-12',100,10,0),('www',100,100,1),('z11',100,20,0);
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passenger`
--

DROP TABLE IF EXISTS `passenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `passenger` (
  `pID` int(11) NOT NULL AUTO_INCREMENT,
  `bookingRefID` int(11) NOT NULL,
  `passportID` varchar(45) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `dob` datetime NOT NULL,
  `phone` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`pID`),
  KEY `bookingRef_idx` (`bookingRefID`),
  CONSTRAINT `bookingRef` FOREIGN KEY (`bookingRefID`) REFERENCES `ticket` (`bookingRefId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passenger`
--

LOCK TABLES `passenger` WRITE;
/*!40000 ALTER TABLE `passenger` DISABLE KEYS */;
INSERT INTO `passenger` VALUES (11,15,'a3','a1','a2','1998-12-03 00:00:00','www','www'),(12,16,'b3','b1','b2','1998-12-03 00:00:00','www','www'),(13,17,'c3','c1','c2','1998-12-03 00:00:00','www','www'),(14,18,'k3','k1','k2','1997-02-18 00:00:00','ww','ww'),(15,19,'aaa','aa2','aa1','2017-01-01 00:00:00','dar','are'),(16,20,'x3','x1','x2','1999-01-02 00:00:00','342556','awrt'),(17,21,'z3','z1','z2','2000-08-16 00:00:00','3413213','sar'),(18,22,'m3','m1','m2','2001-08-20 00:00:00','11244','2qawe'),(19,23,'a3','a1','a2','2000-01-01 00:00:00','3425','fasd'),(20,24,'b3','b1','b2','2000-01-01 00:00:00','34eq','5sad'),(21,25,'c3','c1','c2','2000-01-01 00:00:00','sdaf','darad'),(22,26,'d3','d1','d2','2000-01-01 00:00:00','dar','ad5'),(23,27,'e3','e1','e2','2000-01-01 00:00:00','ad4','ad4'),(24,28,'k3','k1','k2','2000-01-01 00:00:00','2552','da452'),(25,29,'j3','j1','j2','2000-01-01 00:00:00','32456','dae25'),(26,30,'wing3','wing1','wing2','1999-09-09 00:00:00','845204','eiuqr'),(27,31,'wing3','wing1','wing2','1999-09-09 00:00:00','12345','123344'),(28,32,'q3','q1','q2','2000-02-01 00:00:00','12345','12345'),(29,33,'qwe','qwe','qwe','1999-09-09 00:00:00','12345','12345');
/*!40000 ALTER TABLE `passenger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `reservation_results`
--

DROP TABLE IF EXISTS `reservation_results`;
/*!50001 DROP VIEW IF EXISTS `reservation_results`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `reservation_results` AS SELECT 
 1 AS `userID`,
 1 AS `bookingRefID`,
 1 AS `firstName`,
 1 AS `lastName`,
 1 AS `departTime`,
 1 AS `arriveTime`,
 1 AS `departAirPort`,
 1 AS `arriveAirport`,
 1 AS `flyingHours`,
 1 AS `Status`,
 1 AS `availableEseats`,
 1 AS `availableBseats`,
 1 AS `bookingStatus`,
 1 AS `seatType`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `seattype`
--

DROP TABLE IF EXISTS `seattype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seattype` (
  `sno` int(11) NOT NULL,
  `flightID` varchar(45) NOT NULL,
  `numOfEco` int(11) DEFAULT NULL,
  `numOfbus` int(11) DEFAULT NULL,
  `seatTypecol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`flightID`,`sno`),
  KEY `flightID_idx` (`flightID`),
  KEY `sno_idx` (`sno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seattype`
--

LOCK TABLES `seattype` WRITE;
/*!40000 ALTER TABLE `seattype` DISABLE KEYS */;
/*!40000 ALTER TABLE `seattype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `bookingRefId` int(11) NOT NULL AUTO_INCREMENT,
  `tripID` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `ticketStatus` tinyint(4) NOT NULL,
  `seatNo` varchar(45) DEFAULT NULL,
  `seatType` varchar(45) NOT NULL,
  PRIMARY KEY (`bookingRefId`),
  KEY `tripID_idx` (`tripID`),
  KEY `uid_idx` (`uid`),
  CONSTRAINT `tripID` FOREIGN KEY (`tripID`) REFERENCES `trip` (`tripID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (15,1,1,0,NULL,'e-Seat'),(16,1,1,0,NULL,'e-Seat'),(17,1,1,0,NULL,'e-Seat'),(18,1,1,0,NULL,'b-Seat'),(19,1,1,0,NULL,'e-Seat'),(20,1,2,0,NULL,'e-Seat'),(21,1,2,0,NULL,'b-Seat'),(22,301,2,1,NULL,'b-Seat'),(23,1,1,0,NULL,'e-Seat'),(24,1,1,0,NULL,'e-Seat'),(25,1,1,0,NULL,'e-Seat'),(26,1,1,0,NULL,'e-Seat'),(27,1,1,0,NULL,'e-Seat'),(28,1,1,0,NULL,'b-Seat'),(29,1,1,0,NULL,'b-Seat'),(30,1,1,0,NULL,'e-Seat'),(31,1,1,0,NULL,'e-Seat'),(32,1,1,0,NULL,'e-Seat'),(33,1,1,0,NULL,'e-Seat');
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip`
--

DROP TABLE IF EXISTS `trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trip` (
  `tripID` int(11) NOT NULL AUTO_INCREMENT,
  `flightNo` varchar(45) NOT NULL,
  `departTime` datetime NOT NULL,
  `arriveTime` datetime NOT NULL,
  `departAirport` varchar(200) NOT NULL,
  `arriveAirport` varchar(200) NOT NULL,
  `flyingHours` decimal(6,2) NOT NULL,
  `status` int(11) NOT NULL,
  `availableESeats` int(11) NOT NULL,
  `availableBSeats` int(11) NOT NULL,
  `ePrice` double NOT NULL DEFAULT '215',
  `bPrice` double NOT NULL DEFAULT '359',
  PRIMARY KEY (`tripID`),
  KEY `flightNo_idx` (`flightNo`),
  CONSTRAINT `flightNo` FOREIGN KEY (`flightNo`) REFERENCES `flight` (`flightNo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=964 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip`
--

LOCK TABLES `trip` WRITE;
/*!40000 ALTER TABLE `trip` DISABLE KEYS */;
INSERT INTO `trip` VALUES (1,'A-11','2017-10-20 00:00:00','2017-10-21 00:00:00','Dallas','New York',1.50,1,200,30,100,245),(301,'z11','2017-11-09 13:00:00','2017-11-09 15:00:00','Austin','Twin City',2.00,1,100,19,150,321),(323,'S-12','2000-10-10 00:00:00','2000-10-11 00:00:00','WW','ZZ',1.00,0,100,10,215,359),(345,'A99','2017-12-03 00:00:00','2017-12-04 00:00:00','MN','TX',4.50,1,150,20,215,359),(963,'S-12','2017-01-20 00:00:00','2017-01-21 00:00:00','CA','TX',5.00,0,200,100,215,359);
/*!40000 ALTER TABLE `trip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `passportID` varchar(45) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `dob` datetime NOT NULL,
  `phone` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'W123','Natalie','Portman','1977-03-03 00:00:00','1234567890','Portman@gmail.com','123'),(2,'wer345','Jennifer','Lawrence','1972-07-14 00:00:00','123454233','Lawrence@gmail.com','123'),(3,'u782','Emma','Stone','1979-10-11 00:00:00','12339099','Stone@gmail.com','123'),(4,'u213','Megan','Fox','1986-07-14 00:00:00','787517390','Fox@gmail.com','123');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `reservation_results`
--

/*!50001 DROP VIEW IF EXISTS `reservation_results`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `reservation_results` AS select `u`.`uid` AS `userID`,`p`.`bookingRefID` AS `bookingRefID`,`p`.`firstName` AS `firstName`,`p`.`lastName` AS `lastName`,`tr`.`departTime` AS `departTime`,`tr`.`arriveTime` AS `arriveTime`,`tr`.`departAirport` AS `departAirPort`,`tr`.`arriveAirport` AS `arriveAirport`,`tr`.`flyingHours` AS `flyingHours`,`tr`.`status` AS `Status`,`tr`.`availableESeats` AS `availableEseats`,`tr`.`availableBSeats` AS `availableBseats`,`t`.`ticketStatus` AS `bookingStatus`,`t`.`seatType` AS `seatType` from (((`user` `u` join `passenger` `p`) join `trip` `tr`) join `ticket` `t`) where ((`u`.`uid` = `t`.`uid`) and (`p`.`bookingRefID` = `t`.`bookingRefId`) and (`tr`.`tripID` = `t`.`tripID`)) */;
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

-- Dump completed on 2017-11-26 18:20:20
