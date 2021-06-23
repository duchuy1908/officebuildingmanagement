-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: quanlitoanha
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `username` varchar(25) NOT NULL,
  `password` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('admin','admin'),('CT1','1'),('CT2','2'),('CT3','3'),('CT4','4');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `bookcode` varchar(255) NOT NULL,
  `approved` int NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `tittle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bookcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('book1',1,'Huy','Truyen','Truyen tranh'),('book2',1,'Huy2','Truyen','Truyen tranh');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `macty` varchar(25) NOT NULL,
  `ten` varchar(50) DEFAULT NULL,
  `msthue` varchar(50) DEFAULT NULL,
  `vondieule` float DEFAULT NULL,
  `linhvuc` varchar(50) DEFAULT NULL,
  `sonv` int DEFAULT NULL,
  `diachi` varchar(50) DEFAULT NULL,
  `sdt` varchar(50) DEFAULT NULL,
  `dientich` float DEFAULT NULL,
  PRIMARY KEY (`macty`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES ('CT1','TNHN Hoàng Mai','0123456789',100000,'IT',10,'Hoang Mai','0334581687',100),('CT2','TNHH','0123456789',100000,'IT',20,'Hoang Mai','0334581687',95),('CT3','Software','0123456789',100,'IT',30,'Ha Noi','0334581687',100);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_service`
--

DROP TABLE IF EXISTS `company_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_service` (
  `id_register` int NOT NULL AUTO_INCREMENT,
  `company_macty` varchar(255) DEFAULT NULL,
  `service_madv` varchar(255) DEFAULT NULL,
  `register_time` date DEFAULT NULL,
  PRIMARY KEY (`id_register`),
  KEY `FKkh3pwtqq766tpmxmno6du2dtw` (`company_macty`),
  KEY `FK82wxc2hfa5oybwf6b4rl9ughy` (`service_madv`),
  CONSTRAINT `FK82wxc2hfa5oybwf6b4rl9ughy` FOREIGN KEY (`service_madv`) REFERENCES `service` (`madv`),
  CONSTRAINT `FKkh3pwtqq766tpmxmno6du2dtw` FOREIGN KEY (`company_macty`) REFERENCES `company` (`macty`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_service`
--

LOCK TABLES `company_service` WRITE;
/*!40000 ALTER TABLE `company_service` DISABLE KEYS */;
INSERT INTO `company_service` VALUES (19,'CT1','DV01','2020-12-14'),(20,'CT1','DV02','2020-12-14'),(21,'CT2','DV04','2020-12-14'),(22,'CT2','DV05','2020-12-14'),(23,'CT2','DV02','2020-12-14'),(24,'CT2','DV03','2020-12-14'),(25,'CT2','DV02','2020-12-14'),(26,'CT2','DV03','2020-12-14'),(27,'CT2','DV01','2020-12-14');
/*!40000 ALTER TABLE `company_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `manv` varchar(20) NOT NULL,
  `cmt` varchar(50) DEFAULT NULL,
  `ten` varchar(50) DEFAULT NULL,
  `ngaysinh` date DEFAULT NULL,
  `sdt` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`manv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('NVCT2','18776787','Le Duc Huy','1999-08-19','0123456789');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fee`
--

DROP TABLE IF EXISTS `fee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `phidv` float NOT NULL,
  `phimatbang` float NOT NULL,
  `company_macty` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK43u1xoq623v3e6shgtay9qvd4` (`company_macty`),
  CONSTRAINT `FK43u1xoq623v3e6shgtay9qvd4` FOREIGN KEY (`company_macty`) REFERENCES `company` (`macty`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee`
--

LOCK TABLES `fee` WRITE;
/*!40000 ALTER TABLE `fee` DISABLE KEYS */;
/*!40000 ALTER TABLE `fee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvienketoan`
--

DROP TABLE IF EXISTS `nhanvienketoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvienketoan` (
  `id` int NOT NULL,
  `name_string` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvienketoan`
--

LOCK TABLES `nhanvienketoan` WRITE;
/*!40000 ALTER TABLE `nhanvienketoan` DISABLE KEYS */;
/*!40000 ALTER TABLE `nhanvienketoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `madv` varchar(15) NOT NULL,
  `tendv` varchar(50) DEFAULT NULL,
  `loaidv` varchar(50) DEFAULT NULL,
  `dongia` float DEFAULT NULL,
  PRIMARY KEY (`madv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES ('DV01','Ve Sinh','Clean',70),('DV02','An uong','Eat',70),('DV03','Trong Xe','Save',70),('DV04','Bao Tri Thiet Bi','Maintenance',70),('DV05','Bao Ve','Security',70);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `manv` varchar(15) NOT NULL,
  `hoten` varchar(15) DEFAULT NULL,
  `ngaysinh` varchar(50) DEFAULT NULL,
  `diachi` varchar(50) DEFAULT NULL,
  `sdt` varchar(50) DEFAULT NULL,
  `bac` varchar(50) DEFAULT NULL,
  `vitri` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`manv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES ('ST2','Le Duc Hieu','1999-08-19','Hoang Mai','0334581687','6','Staff'),('ST3','Bùi Công Minh','1999-04-27','Hải Phòng','123456789','6','Staff');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'quanlitoanha'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-23 10:11:11
