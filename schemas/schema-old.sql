-- MySQL dump 10.13  Distrib 5.1.37, for debian-linux-gnu (x86_64)
--
-- Host: se-sopra2010    Database: hv
-- ------------------------------------------------------
-- Server version	5.1.37-1ubuntu5

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
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  `Veranstaltung_ID` bigint(19) NOT NULL,
  `Hiwi_ID` bigint(19) NOT NULL,
  `Monat` bigint(2) DEFAULT NULL,
  `Jahr` bigint(4) DEFAULT NULL,
  `AnzahlStunden` float(10,1) DEFAULT NULL,
  `Fonds` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `RealKosten` float(10,2) DEFAULT NULL,
  `Bemerkung` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Kostenstelle` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `hiwi_index` (`Hiwi_ID`),
  KEY `veranstaltung_index` (`Veranstaltung_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1642 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `FinanzPlan`
--

DROP TABLE IF EXISTS `FinanzPlan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FinanzPlan` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `FinanzKategorie` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Jahr` smallint(5) DEFAULT NULL,
  `PlankostenHaushalt` int(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `HIWI`
--

DROP TABLE IF EXISTS `HIWI`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HIWI` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Vorname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Nachname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Studimail` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Stundenlohn` float(5,2) DEFAULT NULL,
  `Handicap` float(10,0) DEFAULT NULL,
  `Qualifikation` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=274 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Stundenlohn`
--

DROP TABLE IF EXISTS `Stundenlohn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Stundenlohn` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `Qualifikation` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `Jahr` int(4) NOT NULL,
  `Monat` int(2) NOT NULL,
  `Lohn` double(5,2) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=73 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Veranstaltung`
--

DROP TABLE IF EXISTS `Veranstaltung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Veranstaltung` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Bezeichnung` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Semester` varchar(6) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Dozent` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Projektart` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `Gruppenanzahl` bigint(19) DEFAULT NULL,
  `Zielpublikum` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HKS` float(10,1) DEFAULT NULL,
  `Umfang` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Teil` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Gruppe` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Bemerkung` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HKS_Genehmigt` float(10,0) DEFAULT NULL,
  `FinanzKategorie` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

