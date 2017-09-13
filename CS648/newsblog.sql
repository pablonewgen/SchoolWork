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
  `username` VARCHAR(45) NOT NULL COMMENT '',
  `password` VARCHAR(45) NOT NULL COMMENT '',
  `email` VARCHAR(45) NOT NULL COMMENT '',
  `firstName` VARCHAR(45) NOT NULL COMMENT '',
  `lastName` VARCHAR(45) NOT NULL COMMENT '',
  `position` INT NOT NULL COMMENT '',
  PRIMARY KEY (`userID`)  COMMENT '',
  UNIQUE INDEX `username_UNIQUE` (`username` ASC)  COMMENT '',
  UNIQUE INDEX `password_UNIQUE` (`password` ASC)  COMMENT '',
  UNIQUE INDEX `email_UNIQUE` (`email` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`post` (
  `postID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `title` VARCHAR(45) NOT NULL COMMENT '',
  `content` VARCHAR(45) NOT NULL COMMENT '',
  `status` CHAR(10) NOT NULL COMMENT '',
  `dateCreated` DATETIME NOT NULL COMMENT '',
  `dateUpdated` DATETIME NOT NULL COMMENT '',
  `userID` INT NOT NULL COMMENT '',
  PRIMARY KEY (`postID`)  COMMENT '',
  UNIQUE INDEX `postID_UNIQUE` (`postID` ASC)  COMMENT '',
  INDEX `fk_post_user1_idx` (`userID` ASC)  COMMENT '',
  UNIQUE INDEX `userID_UNIQUE` (`userID` ASC)  COMMENT '',
  CONSTRAINT `fk_post_user1`
    FOREIGN KEY (`userID`)
    REFERENCES `mydb`.`user` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`category` (
  `categoryID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `genre` VARCHAR(45) NOT NULL COMMENT '',
  `postID` INT NOT NULL COMMENT '',
  PRIMARY KEY (`categoryID`)  COMMENT '',
  UNIQUE INDEX `categoryID_UNIQUE` (`categoryID` ASC)  COMMENT '',
  INDEX `fk_category_post1_idx` (`postID` ASC)  COMMENT '',
  UNIQUE INDEX `postID_UNIQUE` (`postID` ASC)  COMMENT '',
  UNIQUE INDEX `genre_UNIQUE` (`genre` ASC)  COMMENT '',
  CONSTRAINT `fk_category_post1`
    FOREIGN KEY (`postID`)
    REFERENCES `mydb`.`post` (`postID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`media`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`media` (
  `mediaID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `type` VARCHAR(45) NOT NULL COMMENT '',
  `content` VARCHAR(45) NOT NULL COMMENT '',
  `dateCreated` DATETIME NOT NULL COMMENT '',
  `postID` INT NOT NULL COMMENT '',
  PRIMARY KEY (`mediaID`)  COMMENT '',
  UNIQUE INDEX `mediaID_UNIQUE` (`mediaID` ASC)  COMMENT '',
  UNIQUE INDEX `type_UNIQUE` (`type` ASC)  COMMENT '',
  UNIQUE INDEX `content_UNIQUE` (`content` ASC)  COMMENT '',
  INDEX `fk_media_post1_idx` (`postID` ASC)  COMMENT '',
  UNIQUE INDEX `postID_UNIQUE` (`postID` ASC)  COMMENT '',
  CONSTRAINT `fk_media_post1`
    FOREIGN KEY (`postID`)
    REFERENCES `mydb`.`post` (`postID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`comment` (
  `commentID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `content` VARCHAR(45) NOT NULL COMMENT '',
  `dateCreated` DATETIME NOT NULL COMMENT '',
  `dateUpdated` DATETIME NOT NULL COMMENT '',
  `userID` INT NOT NULL COMMENT '',
  `postID` INT NOT NULL COMMENT '',
  PRIMARY KEY (`commentID`)  COMMENT '',
  UNIQUE INDEX `commentID_UNIQUE` (`commentID` ASC)  COMMENT '',
  UNIQUE INDEX `dateUpdated_UNIQUE` (`dateUpdated` ASC)  COMMENT '',
  UNIQUE INDEX `dateCreated_UNIQUE` (`dateCreated` ASC)  COMMENT '',
  INDEX `fk_comment_user_idx` (`userID` ASC)  COMMENT '',
  UNIQUE INDEX `userID_UNIQUE` (`userID` ASC)  COMMENT '',
  INDEX `fk_comment_post1_idx` (`postID` ASC)  COMMENT '',
  UNIQUE INDEX `postID_UNIQUE` (`postID` ASC)  COMMENT '',
  CONSTRAINT `fk_comment_user`
    FOREIGN KEY (`userID`)
    REFERENCES `mydb`.`user` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_post1`
    FOREIGN KEY (`postID`)
    REFERENCES `mydb`.`post` (`postID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
