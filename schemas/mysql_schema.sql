-- MySQL dump 10.13  Distrib 5.1.51, for unknown-linux-gnu (x86_64)
--
-- Host: localhost    Database: aidger
-- ------------------------------------------------------
-- Server version	5.1.51

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
-- Table structure for table `Beschaeftigung`
--

DROP TABLE IF EXISTS `Beschaeftigung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Beschaeftigung` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Veranstaltung_ID` bigint(19) NOT NULL,
  `Hilfskraft_ID` int(11) NOT NULL,
  `Monat` bigint(2) DEFAULT NULL,
  `Jahr` bigint(4) DEFAULT NULL,
  `AnzahlStunden` float(10,1) DEFAULT NULL,
  `Fonds` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Bemerkung` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Kostenstelle` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Qualifikation` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `hiwi_index` (`Hilfskraft_ID`),
  KEY `veranstaltung_index` (`Veranstaltung_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Beschaeftigung`
--

LOCK TABLES `Beschaeftigung` WRITE;
/*!40000 ALTER TABLE `Beschaeftigung` DISABLE KEYS */;
/*!40000 ALTER TABLE `Beschaeftigung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Finanzkategorie`
--

DROP TABLE IF EXISTS `Finanzkategorie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Finanzkategorie` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `Name` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Jahr` smallint(5) DEFAULT NULL,
  `Plankosten` int(10) DEFAULT NULL,
  `Kostenstelle` int(11) NOT NULL,
  PRIMARY KEY (`ID`,`Kostenstelle`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Finanzkategorie`
--

LOCK TABLES `Finanzkategorie` WRITE;
/*!40000 ALTER TABLE `Finanzkategorie` DISABLE KEYS */;
/*!40000 ALTER TABLE `Finanzkategorie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Hilfskraft`
--

DROP TABLE IF EXISTS `Hilfskraft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Hilfskraft` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Vorname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Nachname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Qualifikation` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Hilfskraft`
--

LOCK TABLES `Hilfskraft` WRITE;
/*!40000 ALTER TABLE `Hilfskraft` DISABLE KEYS */;
/*!40000 ALTER TABLE `Hilfskraft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Stundenlohn`
--

DROP TABLE IF EXISTS `Stundenlohn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Stundenlohn` (
  `Qualifikation` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `Jahr` int(4) NOT NULL,
  `Monat` int(2) NOT NULL,
  `Lohn` double(5,2) NOT NULL,
  PRIMARY KEY (`Qualifikation`,`Jahr`,`Monat`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Stundenlohn`
--

LOCK TABLES `Stundenlohn` WRITE;
/*!40000 ALTER TABLE `Stundenlohn` DISABLE KEYS */;
/*!40000 ALTER TABLE `Stundenlohn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Veranstaltung`
--

DROP TABLE IF EXISTS `Veranstaltung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Veranstaltung` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Bezeichnung` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Semester` varchar(6) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Dozent` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Gruppenanzahl` int(11) DEFAULT NULL,
  `Zielpublikum` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HKS` float(10,1) DEFAULT NULL,
  `Umfang` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Teil` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Gruppe` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Bemerkung` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Betreuer` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Finanzkategorie_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Veranstaltung`
--

LOCK TABLES `Veranstaltung` WRITE;
/*!40000 ALTER TABLE `Veranstaltung` DISABLE KEYS */;
/*!40000 ALTER TABLE `Veranstaltung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Vertrag`
--

DROP TABLE IF EXISTS `Vertrag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Vertrag` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Hilfskraft_ID` int(11) NOT NULL,
  `Art` varchar(255) DEFAULT NULL,
  `DatumAnfang` date DEFAULT NULL,
  `DatumEnde` date DEFAULT NULL,
  `DatumAbschluss` date DEFAULT NULL,
  `DatumBestaetigung` date DEFAULT NULL,
  `Delegation` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `VERTRAG_HILFSKRAFT_FK` (`Hilfskraft_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Vertrag`
--

LOCK TABLES `Vertrag` WRITE;
/*!40000 ALTER TABLE `Vertrag` DISABLE KEYS */;
/*!40000 ALTER TABLE `Vertrag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Vorgang`
--

DROP TABLE IF EXISTS `Vorgang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Vorgang` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Hilfskraft_ID` int(11) DEFAULT NULL,
  `Veranstaltung_ID` int(11) DEFAULT NULL,
  `Art` varchar(20) NOT NULL,
  `Datum` date NOT NULL,
  `Inhalt` text,
  `Sender` varchar(50) DEFAULT NULL,
  `Dokumententyp` varchar(50) DEFAULT NULL,
  `Bearbeiter` varchar(2) DEFAULT NULL,
  `Bemerkung` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `VORGANG_VERANSTALTUNG_FK` (`Veranstaltung_ID`),
  KEY `VORGANG_HILFSKRAFT_FK` (`Hilfskraft_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Vorgang`
--

LOCK TABLES `Vorgang` WRITE;
/*!40000 ALTER TABLE `Vorgang` DISABLE KEYS */;
/*!40000 ALTER TABLE `Vorgang` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-12-06 12:52:40
