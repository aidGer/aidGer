
-- Beschäftigung ALTER
ALTER TABLE `Beschaeftigung` CHANGE COLUMN `ID` `ID` INT NOT NULL AUTO_INCREMENT;
ALTER TABLE `Beschaeftigung` CHANGE COLUMN `Hiwi_ID` `Hilfskraft_ID` INT NOT NULL;
ALTER TABLE `Beschaeftigung` DROP `RealKosten`;
ALTER TABLE `Beschaeftigung` ADD`Qualifikation` varchar(1) DEFAULT NULL;

--Finanzkategorie
ALTER TABLE `FinanzPlan` RENAME `Finanzkategorie`,
CHANGE COLUMN `FinanzKategorie` `Name` VARCHAR(150) DEFAULT NULL,
CHANGE COLUMN `PlankostenHaushalt` `Plankosten` int(10) DEFAULT NULL,
ADD `Kostenstelle` INT NOT NULL,
DROP PRIMARY KEY, 
ADD PRIMARY KEY (`ID`, `Kostenstelle`);

--HIWI

ALTER TABLE `Hiwi` RENAME `Hilfskraft`,
CHANGE COLUMN `ID` `ID` INT NOT NULL AUTO_INCREMENT,
CHANGE COLUMN `Studimail` `Email` varchar(255) DEFAULT NULL;

-- Stundenlohn
ALTER TABLE `Stundenlohn` DROP `ID`,
ADD PRIMARY KEY (`Qualifikation`, `Jahr`, `Monat`);

--Veranstaltung
ALTER TABLE `Veranstaltung` CHANGE COLUMN `ID` `ID` INT NOT NULL AUTO_INCREMENT,
DROP `Projektart`,
ADD `Betreuer` VARCHAR(255) DEFAULT NULL,
CHANGE COLUMN `Gruppenanzahl` `Gruppenanzahl` INT DEFAULT NULL,
DROP `HKS_Genehmigt`,
DROP `FinanzKategorie`,
ADD `Finanzkategorie_ID` INT NOT NULL;


CREATE TABLE `Vorgang` (
`ID` INT NOT NULL AUTO_INCREMENT,
`Hilfskraft_ID` INT DEFAULT NULL, 
`Veranstaltung_ID` INT DEFAULT NULL,
`Art` varchar(20) NOT NULL,
`Datum` DATE NOT NULL,
`Inhalt` TEXT DEFAULT NULL,
`Sender` varchar(50) DEFAULT NULL,
`Dokumententyp` varchar(50) DEFAULT NULL,
`Bearbeiter` varchar(2) DEFAULT NULL,
`Bemerkung` varchar(255) NOT NULL,
PRIMARY KEY (ID),
FOREIGN KEY VORGANG_VERANSTALTUNG_FK (`Veranstaltung_ID`) REFERENCES `Veranstaltung`(`ID`),
FOREIGN KEY VORGANG_HILFSKRAFT_FK (`Hilfskraft_ID`) REFERENCES `Hilfskraft`(`ID`)
);

CREATE TABLE `Vertrag` (
`ID` INT NOT NULL AUTO_INCREMENT,
`Hilfskraft_ID` INT NOT NULL,
`Art` varchar(255) DEFAULT NULL,
`DatumAnfang` date DEFAULT NULL,
`DatumEnde` date DEFAULT NULL,
`DatumAbschluss` date DEFAULT NULL,
`DatumBestaetigung` date DEFAULT NULL,
`Delegation` smallint DEFAULT NULL,
PRIMARY KEY (`ID`),
FOREIGN KEY VERTRAG_HILFSKRAFT_FK (`Hilfskraft_ID`) REFERENCES `Hilfskraft`(`ID`)
);


