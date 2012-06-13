--
-- Schema changes necessary for aidGer 1.5
--

ALTER TABLE `Stundenlohn` ADD `ID` int(11) NOT NULL AUTO_INCREMENT,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`ID`),
    ADD UNIQUE KEY `QualJahrMonat` (`Qualifikation`, `Jahr`, `Monat`);