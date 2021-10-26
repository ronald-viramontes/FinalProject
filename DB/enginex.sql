-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema enginexdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `enginexdb` ;

-- -----------------------------------------------------
-- Schema enginexdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `enginexdb` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `enginexdb` ;

-- -----------------------------------------------------
-- Table `developer_account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `developer_account` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `developer_account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(250) NOT NULL,
  `last_name` VARCHAR(250) NOT NULL,
  `email` VARCHAR(250) NOT NULL,
  `phone_number` VARCHAR(10) NULL,
  `username` VARCHAR(250) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `account_active` TINYINT NOT NULL DEFAULT 1,
  `image_url` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `job_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_type` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `job_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `job_type_name` VARCHAR(255) NOT NULL,
  `job_type_description` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `company` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `company` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `company_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `client_account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `client_account` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `client_account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(250) NOT NULL,
  `last_name` VARCHAR(250) NOT NULL,
  `phone_number` VARCHAR(10) NULL,
  `email` VARCHAR(250) NOT NULL,
  `username` VARCHAR(250) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `account_active` TINYINT NOT NULL DEFAULT 1,
  `image_url` TEXT NULL,
  `company_id` INT NOT NULL,
  PRIMARY KEY (`id`, `company_id`),
  CONSTRAINT `fk_client_account_company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_client_account_company1_idx` ON `client_account` (`company_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `status` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `status` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `job_post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_post` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `job_post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `job_requirements` TEXT NOT NULL,
  `start_date` DATE NULL,
  `completion_date` DATE NULL,
  `developers_needed` INT NULL,
  `job_active` TINYINT NULL,
  `date_posted` DATE NOT NULL,
  `date_closed` DATE NULL,
  `job_type_id` INT NOT NULL,
  `client_account_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`id`, `job_type_id`, `client_account_id`, `status_id`),
  CONSTRAINT `fk_job_post_job_type1`
    FOREIGN KEY (`job_type_id`)
    REFERENCES `job_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_post_client_account1`
    FOREIGN KEY (`client_account_id`)
    REFERENCES `client_account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_post_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_job_post_job_type1_idx` ON `job_post` (`job_type_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_job_post_client_account1_idx` ON `job_post` (`client_account_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_job_post_status1_idx` ON `job_post` (`status_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `developer_education`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `developer_education` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `developer_education` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `education_type` TEXT NULL,
  `institution_name` TEXT NOT NULL,
  `degree_certificate_name` TEXT NOT NULL,
  `complete_date` DATE NOT NULL,
  `developer_account_id` INT NOT NULL,
  PRIMARY KEY (`id`, `developer_account_id`),
  CONSTRAINT `fk_developer_education_developer_account1`
    FOREIGN KEY (`developer_account_id`)
    REFERENCES `developer_account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_developer_education_developer_account1_idx` ON `developer_education` (`developer_account_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `work_experience`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `work_experience` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `work_experience` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `job_title` VARCHAR(250) NULL,
  `company_name` TEXT NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  `developer_account_id` INT NOT NULL,
  PRIMARY KEY (`id`, `developer_account_id`),
  CONSTRAINT `fk_work_experience_developer_account1`
    FOREIGN KEY (`developer_account_id`)
    REFERENCES `developer_account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_work_experience_developer_account1_idx` ON `work_experience` (`developer_account_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `developer_skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `developer_skill` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `developer_skill` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `skill_title` VARCHAR(400) NULL,
  `skill_level` VARCHAR(250) NULL,
  `developer_account_id` INT NOT NULL,
  PRIMARY KEY (`id`, `developer_account_id`),
  CONSTRAINT `fk_developer_skills_developer_account1`
    FOREIGN KEY (`developer_account_id`)
    REFERENCES `developer_account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_developer_skills_developer_account1_idx` ON `developer_skill` (`developer_account_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `job_application`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_application` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `job_application` (
  `id` INT NOT NULL,
  `application_approval` TINYINT NULL,
  `application_status` VARCHAR(45) NULL,
  `application_date` DATE NULL,
  `job_post_id` INT NOT NULL,
  `developer_account_id` INT NOT NULL,
  `decision_date` DATE NULL,
  PRIMARY KEY (`id`, `job_post_id`, `developer_account_id`),
  CONSTRAINT `fk_job_application_status_job_post1`
    FOREIGN KEY (`job_post_id`)
    REFERENCES `job_post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_application_developer_account1`
    FOREIGN KEY (`developer_account_id`)
    REFERENCES `developer_account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_job_application_status_job_post1_idx` ON `job_application` (`job_post_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_job_application_developer_account1_idx` ON `job_application` (`developer_account_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `job_detail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_detail` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `job_detail` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `start_date` DATE NULL,
  `finish_date` DATE NULL,
  `job_rating` INT NULL,
  `job_rating_comment` TEXT NULL,
  `job_post_id` INT NOT NULL,
  PRIMARY KEY (`id`, `job_post_id`),
  CONSTRAINT `fk_job_details_job_post1`
    FOREIGN KEY (`job_post_id`)
    REFERENCES `job_post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_job_details_job_post1_idx` ON `job_detail` (`job_post_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `developer_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `developer_rating` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `developer_rating` (
  `id` INT NOT NULL,
  `rating` INT NULL,
  `comments` TEXT NULL,
  `job_application_id` INT NOT NULL,
  PRIMARY KEY (`id`, `job_application_id`),
  CONSTRAINT `fk_developer_rating_job_application1`
    FOREIGN KEY (`job_application_id`)
    REFERENCES `job_application` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_developer_rating_job_application1_idx` ON `developer_rating` (`job_application_id` ASC);

SHOW WARNINGS;
SET SQL_MODE = '';
DROP USER IF EXISTS enginexuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
SHOW WARNINGS;
CREATE USER 'enginexuser'@'localhost' IDENTIFIED BY 'enginex';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'enginexuser'@'localhost';
SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `developer_account`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `developer_account` (`id`, `first_name`, `last_name`, `email`, `phone_number`, `username`, `password`, `account_active`, `image_url`) VALUES (1, 'Ron', 'Viramontes', 'example@email.com', '5551215555', 'ron', '123', 0, NULL);

COMMIT;

