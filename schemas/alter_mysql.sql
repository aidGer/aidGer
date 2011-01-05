# some data needs to be migrated, BEFORE the now obsolete columns get deleted:

UPDATE `Hiwi` SET `Qualifikation` = 'u' WHERE `Stundenlohn` = 8.05;
UPDATE `Hiwi` SET `Qualifikation` = 'u' WHERE `Stundenlohn` = 8.38;
UPDATE `Hiwi` SET `Qualifikation` = 'b' WHERE `Stundenlohn` = 9.37;
UPDATE `Hiwi` SET `Qualifikation` = 'b' WHERE `Stundenlohn` = 9.65;
UPDATE `Hiwi` SET `Qualifikation` = 'g' WHERE `Stundenlohn` = 12.73;


ALTER TABLE `Beschaeftigung` 
CHANGE COLUMN `ID` `ID` INT NOT NULL AUTO_INCREMENT,
CHANGE COLUMN `Hiwi_ID` `Hilfskraft_ID` INT NOT NULL,
DROP `RealKosten`,
ADD`Qualifikation` varchar(1) DEFAULT NULL,
ADD `Vertrag_ID` INT NOT NULL,
CHANGE COLUMN `Kostenstelle` `Kostenstelle` INT(11) NOT NULL,
CHANGE COLUMN `Monat` `Monat` TINYINT DEFAULT NULL,
CHANGE COLUMN `Jahr` `Jahr` SMALLINT DEFAULT NULL;




ALTER TABLE `FinanzPlan` RENAME `Finanzkategorie`,
CHANGE COLUMN `Finanzkategorie` `Name` VARCHAR(150) DEFAULT NULL,
CHANGE COLUMN `PlankostenHaushalt` `Plankosten` int(10) DEFAULT NULL,
ADD `Kostenstelle` INT NOT NULL,
DROP PRIMARY KEY, 
ADD PRIMARY KEY (`ID`, `Kostenstelle`);


ALTER TABLE `Hiwi` RENAME `Hilfskraft`,
CHANGE COLUMN `ID` `ID` INT NOT NULL AUTO_INCREMENT,
CHANGE COLUMN `Studimail` `Email` varchar(255) DEFAULT NULL,
DROP `Stundenlohn`,
DROP `Handicap`;


ALTER TABLE `Stundenlohn` DROP `ID`,
ADD PRIMARY KEY (`Qualifikation`, `Jahr`, `Monat`);


ALTER TABLE `Veranstaltung` CHANGE COLUMN `ID` `ID` INT NOT NULL AUTO_INCREMENT,
DROP `Projektart`,
ADD `Betreuer` VARCHAR(255) DEFAULT NULL,
CHANGE COLUMN `Gruppenanzahl` `Gruppenanzahl` INT DEFAULT NULL,
DROP `HKS_Genehmigt`,
ADD `Finanzkategorie_ID` INT NOT NULL;

# do not Drop Finanzkategorie yet, since existing data needs to be migrated first:
UPDATE `Veranstaltung` SET `Finanzkategorie`= 'nicht zugeordnet' WHERE `Finanzkategorie`='';
UPDATE `Veranstaltung` SET `Finanzkategorie`= 'nicht zugeordnet' WHERE ISNULL(`Finanzkategorie`);

UPDATE `Veranstaltung` v SET v.`Finanzkategorie_ID`= (SELECT `ID` FROM `Finanzkategorie` f WHERE f.`Name`=v.`Finanzkategorie` LIMIT 1);

# now drop Finanzkategorie
ALTER TABLE `Veranstaltung` DROP `FinanzKategorie`;



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

# Once the schema is updated, fix the existing data to conform to the new schema:

UPDATE `Veranstaltung` SET `Gruppenanzahl`=0 WHERE ISNULL(Gruppenanzahl);
UPDATE `Veranstaltung` SET `HKS`=0 WHERE ISNULL(HKS);

INSERT INTO `Hilfskraft` (`ID`, `Vorname`, `Nachname`, `Qualifikation`) VALUES (0,'Nicht zugeordnet','Nicht zugeordnet','u');
UPDATE `Hilfskraft` SET `ID`=0 WHERE `Vorname`='Nicht zugeordnet';


INSERT INTO `Finanzkategorie` (`ID`,`Name`,`Jahr`,`Plankosten`,`Kostenstelle`) VALUES (0,'Nicht zugeordnet',0,0,0);
UPDATE `Finanzkategorie` SET `ID`=0 WHERE `Name`='Nicht zugeordnet';


INSERT INTO `Veranstaltung` (`ID`,`Bezeichnung`,`HKS`,`Gruppenanzahl`,`Semester`,`Finanzkategorie_ID`,`Teil`) VALUES (0, 'Nicht zugeordnet', 0, 0, 0, 0,'-');
UPDATE `Veranstaltung` SET `ID`=0 WHERE `Bezeichnung`='Nicht zugeordnet';

INSERT INTO `Vertrag` (`ID`,`Hilfskraft_ID`) VALUES (0,0);
UPDATE `Vertrag` SET `ID`=0 WHERE `Hilfskraft_ID`=0; 

UPDATE `Beschaeftigung` SET `Qualifikation`='u' WHERE ISNULL(`Qualifikation`);

# fix a few broken data sets:
UPDATE `Beschaeftigung` SET `Hilfskraft_ID`=0 WHERE `Hilfskraft_ID`=22;
UPDATE `Beschaeftigung` SET `Veranstaltung_ID`=0 WHERE `Veranstaltung_ID`=31;

UPDATE `Veranstaltung` SET `Teil`='-' WHERE ISNULL(`Teil`);

UPDATE `Beschaeftigung` SET `ID`=10 WHERE `ID`=-1;

# create fascade to support legacy OOo App:
CREATE View `Hiwi` AS SELECT `ID`,`Vorname`,`Nachname`, 1 AS `Handicap`, (SELECT MAX(`Lohn`) FROM `Stundenlohn` `s` WHERE `s`.`Qualifikation` = `h`.`Qualifikation`) AS `Stundenlohn` FROM `Hilfskraft` `h`;