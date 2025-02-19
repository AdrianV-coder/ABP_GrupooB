-- MySQL dump 10.13  Distrib 8.0.41, for Linux (x86_64)
--
-- Host: localhost    Database: abp_grupob
-- ------------------------------------------------------
-- Server version	8.0.41-0ubuntu0.22.04.1

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
-- Current Database: `abp_grupob`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `abp_grupob` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `abp_grupob`;

--
-- Table structure for table `articulo`
--

DROP TABLE IF EXISTS `articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  `fecha_creacion` date DEFAULT NULL,
  `precio` double NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `id_categoria` int DEFAULT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7n3o8lpr7d7uy4pxv4cueloal` (`id_categoria`),
  KEY `FKl52er27j5f2mprj85fj7xeqih` (`id_usuario`),
  CONSTRAINT `FK7n3o8lpr7d7uy4pxv4cueloal` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`),
  CONSTRAINT `FKl52er27j5f2mprj85fj7xeqih` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
INSERT INTO `articulo` VALUES (59,'camisa','2025-02-17',15,'Camisa Hombre',2,13),(60,'nueva','2025-02-17',150,'Nintendo Switch',1,13),(61,'sin uso','2025-02-17',1000,'iPhone 15',1,13),(64,'pueblazo','2025-02-17',55,'Aiacor',3,6),(65,'odoo','2025-02-18',77,'Odoo',1,18),(66,'telefono','2025-02-19',700,'Android',1,13);
/*!40000 ALTER TABLE `articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Electronica'),(2,'Ropa'),(3,'Muebles'),(4,'Coches'),(5,'Libros');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favoritos`
--

DROP TABLE IF EXISTS `favoritos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favoritos` (
  `usuario_id` int NOT NULL,
  `articulo_id` int NOT NULL,
  KEY `fk_favoritos_articulo` (`articulo_id`),
  KEY `FK197jjv60qkknc6s6uvnel1nr9` (`usuario_id`),
  CONSTRAINT `FK197jjv60qkknc6s6uvnel1nr9` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `fk_favoritos_articulo` FOREIGN KEY (`articulo_id`) REFERENCES `articulo` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favoritos`
--

LOCK TABLES `favoritos` WRITE;
/*!40000 ALTER TABLE `favoritos` DISABLE KEYS */;
INSERT INTO `favoritos` VALUES (6,61),(18,61);
/*!40000 ALTER TABLE `favoritos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto`
--

DROP TABLE IF EXISTS `foto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(255) NOT NULL,
  `id_articulo` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKg6b20vxcgm366hp802ie74w4` (`id_articulo`),
  CONSTRAINT `fk_foto_articulo` FOREIGN KEY (`id_articulo`) REFERENCES `articulo` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto`
--

LOCK TABLES `foto` WRITE;
/*!40000 ALTER TABLE `foto` DISABLE KEYS */;
INSERT INTO `foto` VALUES (17,'http://10.0.0.4:8080/uploads/1739794204209_upload.jpg',59),(18,'http://10.0.0.4:8080/uploads/1739794556402_upload.jpg',60),(19,'http://10.0.0.4:8080/uploads/1739794587389_upload.jpg',61),(22,'http://10.0.0.4:8080/uploads/1739807900213_upload.jpg',64),(23,'http://10.0.0.4:8080/uploads/1739915529197_upload.jpg',65),(24,'http://10.0.0.4:8080/uploads/1739952969992_upload.jpg',66);
/*!40000 ALTER TABLE `foto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(255) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `latitud` double NOT NULL,
  `longitud` double NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `premium` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (2,'ortorrosa','123abc','corre@gmail.com',234,12,'PeciaLopez',NULL),(3,'ortorrosa','123abc','corre@gmail.com',234,12,'Jose',NULL),(5,'ortorrosa','123abc','corre1@gmail.com',234,12,'Peci',NULL),(6,'Tomas Guerra','1234','admin@gmail.com',0,0,'Joaquin',NULL),(7,'Benavent Moreno','','odoo@gmail.com',0,0,'Vicent',NULL),(9,'Tomas Pecia','123456','prova@gmail.com',0,0,'Pere',NULL),(10,'perez','1234','paco@gmail.com',0,0,'Paco',NULL),(11,'Vernich Oltra','','adrianvernicholtra@gmail.com',0,0,'Adrian',NULL),(12,'Tomas Guerra','1234','pere@gmail',0,0,'Joaquin',NULL),(13,'Tortosa Perales','1234','pau@gmail.com',0,0,'Pau',NULL),(14,'Palet Molla','1234','david@gmail.com',0,0,'David',NULL),(15,'Perez','1234','paquito@gmail.com',1,1,'Paco',NULL),(16,'eeee','1234','eeee@gmail.com',38.9623954,-0.5832175,'eeee',NULL),(17,'Benavent Moreno','1234','vicent@gmail.com',38.9722195,-0.5746571,'Vicent',NULL),(18,'paco','1234','pacopaco@gmail.com',38.9623954,-0.5832175,'Paco',NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valoracion`
--

DROP TABLE IF EXISTS `valoracion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `valoracion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  `estrellas` int NOT NULL,
  `id_usuario_valora` int NOT NULL,
  `id_usuario_valorat` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl5tfefgyagwrrg3k9msdnfenf` (`id_usuario_valora`),
  KEY `FK2wkiep30ywsr5ek2lyb2lh12s` (`id_usuario_valorat`),
  CONSTRAINT `FK2wkiep30ywsr5ek2lyb2lh12s` FOREIGN KEY (`id_usuario_valorat`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKl5tfefgyagwrrg3k9msdnfenf` FOREIGN KEY (`id_usuario_valora`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valoracion`
--

LOCK TABLES `valoracion` WRITE;
/*!40000 ALTER TABLE `valoracion` DISABLE KEYS */;
INSERT INTO `valoracion` VALUES (1,'Excelente servicio, muy recomendable.',5,2,3),(2,'Excelente servicio, muy recomendable.',5,2,3),(3,'Excelente servicio, muy recomendable.',5,13,7),(4,'Bien',4,18,13),(5,'me cago en mis muertos',1,18,2),(6,'XDDD',5,18,2),(7,'muy buena persona',5,13,18),(8,'mal',3,18,13),(9,'no puntual',3,18,13),(10,'Muy bien',5,18,13),(11,'',3,13,18),(12,'',5,13,2),(13,'godgod',5,6,18),(14,'',1,6,18);
/*!40000 ALTER TABLE `valoracion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-19 12:00:31
