--
-- Schema changes necessary for aidGer 1.5
--

ALTER TABLE `Stundenlohn` ADD `ID` int(11) NOT NULL AUTO_INCREMENT,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`ID`),
    ADD UNIQUE KEY `QualJahrMonat` (`Qualifikation`, `Jahr`, `Monat`);

ALTER TABLE `Finanzkategorie` DROP PRIMARY KEY,
    CHANGE `ID` `Gruppe` int(11) NOT NULL,
    AUTO_INCREMENT = 1,
    ADD `ID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST,
    ADD UNIQUE KEY `GruppeKosten` (`Gruppe`, `Kostenstelle`);