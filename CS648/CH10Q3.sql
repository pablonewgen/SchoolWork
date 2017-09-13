-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `userID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `emailAddress` VARCHAR(45) NOT NULL COMMENT '',
  `firstName` VARCHAR(45) NOT NULL COMMENT '',
  `lastName` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`userID`)  COMMENT '',
  UNIQUE INDEX `emailAddress_UNIQUE` (`emailAddress` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`download`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`download` (
  `downloadID` INT NOT NULL COMMENT '',
  `filename` VARCHAR(45) NOT NULL COMMENT '',
  `dateTime` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`downloadID`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`userDownload`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`userDownload` (
  `downloadID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `userID` INT NOT NULL COMMENT '',
  PRIMARY KEY (`downloadID`)  COMMENT '',
  INDEX `fk_userDownload_user1_idx` (`userID` ASC)  COMMENT '',
  CONSTRAINT `fk_userDownload_download`
    FOREIGN KEY (`downloadID`)
    REFERENCES `mydb`.`download` (`downloadID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userDownload_user1`
    FOREIGN KEY (`userID`)
    REFERENCES `mydb`.`user` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`product` (
  `productID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `downloadID` INT NOT NULL COMMENT '',
  PRIMARY KEY (`productID`)  COMMENT '',
  INDEX `fk_product_download1_idx` (`downloadID` ASC)  COMMENT '',
  CONSTRAINT `fk_product_download1`
    FOREIGN KEY (`downloadID`)
    REFERENCES `mydb`.`download` (`downloadID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`productName`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`productName` (
  `productID` INT NOT NULL COMMENT '',
  `productName` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`productID`)  COMMENT '',
  CONSTRAINT `fk_productName_product1`
    FOREIGN KEY (`productID`)
    REFERENCES `mydb`.`product` (`productID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
