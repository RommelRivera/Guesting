CREATE DATABASE  IF NOT EXISTS `guesting` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_spanish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `guesting`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: guesting
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `nombres` varchar(45) COLLATE utf8mb3_spanish_ci NOT NULL,
  `apellidos` varchar(45) COLLATE utf8mb3_spanish_ci NOT NULL,
  `doc_identidad` varchar(45) COLLATE utf8mb3_spanish_ci NOT NULL,
  `correo` varchar(45) COLLATE utf8mb3_spanish_ci NOT NULL,
  `telefono` varchar(45) COLLATE utf8mb3_spanish_ci NOT NULL,
  `tarjeta` varchar(45) COLLATE utf8mb3_spanish_ci NOT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `DUI_UNIQUE` (`doc_identidad`),
  UNIQUE KEY `correo_UNIQUE` (`correo`),
  UNIQUE KEY `telefono_UNIQUE` (`telefono`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Rommel Alejandro','Rivera Pinto','12345678-9','rrivera@mail.com','1234-5678','0000111122223333');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `habitacion`
--

DROP TABLE IF EXISTS `habitacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `habitacion` (
  `id_habitacion` int NOT NULL AUTO_INCREMENT,
  `num_habitacion` varchar(45) COLLATE utf8mb3_spanish_ci NOT NULL,
  `tipo_habitacion` int NOT NULL,
  `precio_habitacion` int NOT NULL,
  PRIMARY KEY (`id_habitacion`),
  KEY `fk_tipo_habitacion_idx` (`tipo_habitacion`),
  KEY `fk_precio_habitacion_idx` (`precio_habitacion`),
  CONSTRAINT `fk_precio_habitacion` FOREIGN KEY (`precio_habitacion`) REFERENCES `precio_habitacion` (`id_precio`),
  CONSTRAINT `fk_tipo_habitacion` FOREIGN KEY (`tipo_habitacion`) REFERENCES `tipo_habitacion` (`id_tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habitacion`
--

LOCK TABLES `habitacion` WRITE;
/*!40000 ALTER TABLE `habitacion` DISABLE KEYS */;
INSERT INTO `habitacion` VALUES (1,'201',5,5),(2,'202',6,6),(3,'202',7,7),(4,'203',1,1),(5,'204',2,2),(6,'204',3,3),(7,'204',4,4),(8,'301',5,5),(9,'302',6,6),(10,'302',7,7),(11,'303',1,1),(12,'304',2,2),(13,'304',3,3),(14,'304',4,4),(15,'701',10,10),(16,'702',8,8),(17,'702',9,9),(18,'703',10,10),(19,'704',8,8),(20,'704',9,9),(21,'801',11,11),(22,'801',12,12),(23,'801',13,13),(24,'801',14,14),(25,'802',15,15),(26,'802',16,16),(27,'803',11,11),(28,'803',12,12),(29,'803',13,13),(30,'803',14,14),(31,'804',15,15),(32,'804',16,16),(33,'805',11,11),(34,'805',12,12),(35,'805',13,13),(36,'805',14,14),(37,'806',17,17),(38,'806',18,18),(39,'806',19,19),(40,'806',20,20);
/*!40000 ALTER TABLE `habitacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precio_habitacion`
--

DROP TABLE IF EXISTS `precio_habitacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `precio_habitacion` (
  `id_precio` int NOT NULL AUTO_INCREMENT,
  `precio` int NOT NULL,
  PRIMARY KEY (`id_precio`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precio_habitacion`
--

LOCK TABLES `precio_habitacion` WRITE;
/*!40000 ALTER TABLE `precio_habitacion` DISABLE KEYS */;
INSERT INTO `precio_habitacion` VALUES (1,143),(2,156),(3,234),(4,179),(5,168),(6,173),(7,186),(8,184),(9,288),(10,205),(11,362),(12,465),(13,376),(14,478),(15,423),(16,436),(17,469),(18,548),(19,483),(20,561);
/*!40000 ALTER TABLE `precio_habitacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservacion`
--

DROP TABLE IF EXISTS `reservacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservacion` (
  `id_reservacion` int NOT NULL AUTO_INCREMENT,
  `id_cliente` int NOT NULL,
  `id_habitacion` int NOT NULL,
  `fecha_entrada` date NOT NULL,
  `fecha_salida` date NOT NULL,
  `personas` int NOT NULL,
  `total` int NOT NULL,
  PRIMARY KEY (`id_reservacion`),
  KEY `fk_cliente_idx` (`id_cliente`),
  KEY `fk_habitacion` (`id_habitacion`),
  CONSTRAINT `fk_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  CONSTRAINT `fk_habitacion` FOREIGN KEY (`id_habitacion`) REFERENCES `habitacion` (`id_habitacion`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservacion`
--

LOCK TABLES `reservacion` WRITE;
/*!40000 ALTER TABLE `reservacion` DISABLE KEYS */;
INSERT INTO `reservacion` VALUES (5,1,1,'2022-11-05','2022-11-10',2,867),(7,1,1,'2022-11-01','2022-11-04',0,477);
/*!40000 ALTER TABLE `reservacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_habitacion`
--

DROP TABLE IF EXISTS `tipo_habitacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_habitacion` (
  `id_tipo` int NOT NULL AUTO_INCREMENT,
  `nombre_habitacion` varchar(60) COLLATE utf8mb3_spanish_ci NOT NULL,
  PRIMARY KEY (`id_tipo`),
  UNIQUE KEY `nombre_habitacion_UNIQUE` (`nombre_habitacion`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_habitacion`
--

LOCK TABLES `tipo_habitacion` WRITE;
/*!40000 ALTER TABLE `tipo_habitacion` DISABLE KEYS */;
INSERT INTO `tipo_habitacion` VALUES (6,'Clásica'),(7,'Clásica con Desayuno y Cancelación Gratuita'),(1,'Deluxe'),(4,'Deluxe Cama King con Cancelación Gratuita'),(2,'Deluxe Cama King con Desayuno'),(3,'Deluxe Cama King con Desayuno y Cancelación Gratuita'),(8,'Ejecutiva Cama King'),(9,'Ejecutiva Cama King con Desayuno y Cancelación Gratuita'),(10,'Ejecutiva con Desayuno'),(5,'Estándar con Desayuno y Cancelación Gratuita'),(15,'Junior Suite Cama King con Cancelación Gratuita'),(16,'Junior Suite Cama King con Desayuno y Cancelación Gratuita'),(11,'Junior Suite Ejecutiva'),(12,'Junior Suite Ejecutiva con Cancelación Gratuita'),(13,'Junior Suite Ejecutiva con Desayuno'),(14,'Junior Suite Ejecutiva con Desayuno y Cancelación Gratuita'),(17,'Suite Presidencial'),(18,'Suite Presidencial con Cancelación Gratuita'),(19,'Suite Presidencial con Desayuno'),(20,'Suite Presidencial con Desayuno y Cancelación Gratuita');
/*!40000 ALTER TABLE `tipo_habitacion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-26 13:19:00
