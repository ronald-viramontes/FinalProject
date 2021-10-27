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
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `enabled` TINYINT NULL DEFAULT 1,
  `role` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `developer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `developer` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `developer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(250) NOT NULL,
  `last_name` VARCHAR(250) NOT NULL,
  `email` VARCHAR(250) NOT NULL,
  `phone_number` VARCHAR(10) NULL DEFAULT NULL,
  `image_url` TEXT NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_developer_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_developer_user1_idx` ON `developer` (`user_id` ASC);

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
-- Table `client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `client` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(250) NOT NULL,
  `last_name` VARCHAR(250) NOT NULL,
  `phone_number` VARCHAR(10) NULL DEFAULT NULL,
  `email` VARCHAR(250) NOT NULL,
  `image_url` TEXT NULL,
  `company_id` INT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_client_company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_client_company1_idx` ON `client` (`company_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_client_user1_idx` ON `client` (`user_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `job_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_status` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `job_status` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
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
  `client_id` INT NOT NULL,
  `job_status_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_job_post_job_type1`
    FOREIGN KEY (`job_type_id`)
    REFERENCES `job_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_post_client1`
    FOREIGN KEY (`client_id`)
    REFERENCES `client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_post_status1`
    FOREIGN KEY (`job_status_id`)
    REFERENCES `job_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_job_post_job_type1_idx` ON `job_post` (`job_type_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_job_post_client1_idx` ON `job_post` (`client_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_job_post_status1_idx` ON `job_post` (`job_status_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `developer_education`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `developer_education` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `developer_education` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `education_type` TEXT NULL DEFAULT NULL,
  `institution_name` TEXT NOT NULL,
  `degree_certificate_name` TEXT NOT NULL,
  `complete_date` DATE NOT NULL,
  `developer_account_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_developer_education_developer1`
    FOREIGN KEY (`developer_account_id`)
    REFERENCES `developer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_developer_education_developer1_idx` ON `developer_education` (`developer_account_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `work_experience`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `work_experience` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `work_experience` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `job_title` VARCHAR(250) NULL DEFAULT NULL,
  `company_name` TEXT NULL DEFAULT NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  `developer_account_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_work_experience_developer1`
    FOREIGN KEY (`developer_account_id`)
    REFERENCES `developer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_work_experience_developer1_idx` ON `work_experience` (`developer_account_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `developer_skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `developer_skill` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `developer_skill` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `skill_title` VARCHAR(400) NULL DEFAULT NULL,
  `skill_level` VARCHAR(250) NULL DEFAULT NULL,
  `developer_account_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_developer_skills_developer1`
    FOREIGN KEY (`developer_account_id`)
    REFERENCES `developer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_developer_skills_developer1_idx` ON `developer_skill` (`developer_account_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `job_application`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_application` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `job_application` (
  `id` INT NOT NULL,
  `application_approval` TINYINT NULL,
  `application_status` VARCHAR(45) NULL DEFAULT NULL,
  `application_date` DATE NULL,
  `job_post_id` INT NOT NULL,
  `developer_id` INT NOT NULL,
  `decision_date` DATE NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_job_application_status_job_post1`
    FOREIGN KEY (`job_post_id`)
    REFERENCES `job_post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_application_developer1`
    FOREIGN KEY (`developer_id`)
    REFERENCES `developer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_job_application_status_job_post1_idx` ON `job_application` (`job_post_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_job_application_developer1_idx` ON `job_application` (`developer_id` ASC);

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
  `job_rating_comment` TEXT NULL DEFAULT NULL,
  `job_application_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_job_detail_job_application1`
    FOREIGN KEY (`job_application_id`)
    REFERENCES `job_application` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_job_detail_job_application1_idx` ON `job_detail` (`job_application_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `job_application_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_application_comment` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `job_application_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` TEXT NULL DEFAULT NULL,
  `comment_date` DATE NULL,
  `job_application_id` INT NOT NULL,
  `in_reply_to_comment_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_job_application_comment_job_application1`
    FOREIGN KEY (`job_application_id`)
    REFERENCES `job_application` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_application_comment_job_application_comment1`
    FOREIGN KEY (`in_reply_to_comment_id`)
    REFERENCES `job_application_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_job_application_comment_job_application1_idx` ON `job_application_comment` (`job_application_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_job_application_comment_job_application_comment1_idx` ON `job_application_comment` (`in_reply_to_comment_id` ASC);

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
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`) VALUES (1, 'rodfed', '123', 1, 'developer');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`) VALUES (2, 'garrett', '123', 1, 'admin');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`) VALUES (3, 'jacob', '123', 1, 'admin');
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`) VALUES (4, 'donjohn', '123', 1, 'client');

COMMIT;


-- -----------------------------------------------------
-- Data for table `developer`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `developer` (`id`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `user_id`) VALUES (1, 'Ron', 'Viramontes', 'example@email.com', '5551215555', 'https://i.natgeofe.com/k/093c14b4-978e-41f7-b1aa-3aff5d1c608a/gray-wolf-closeup_2x3.jpg', 1);
INSERT INTO `developer` (`id`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `user_id`) VALUES (2, 'Garrett', 'Pipes', 'example@email.com', '5551116656', 'https://funkidsjokes.com/wp-content/uploads/2017/10/wizard-1454385_640.png', 2);
INSERT INTO `developer` (`id`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `user_id`) VALUES (3, 'Jacob', 'Tweedy', 'example@email.com', '5556664888', 'https://zoo.sandiegozoo.org/sites/default/files/styles/hero_mobile_560x670/public/2019-01/hero-lion.jpg?itok=QOrb0EWP', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `job_type` (`id`, `job_type_name`, `job_type_description`) VALUES (1, 'Full stack developer', 'Ability to create a full stack application with a Java backend and Angular frontend.');

COMMIT;


-- -----------------------------------------------------
-- Data for table `company`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `company` (`id`, `company_name`) VALUES (1, 'Hollywood');

COMMIT;


-- -----------------------------------------------------
-- Data for table `client`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `client` (`id`, `first_name`, `last_name`, `phone_number`, `email`, `image_url`, `company_id`, `user_id`) VALUES (1, 'Don', 'Johnson', '5055551212', 'djohnson@example.com', 'https://m.media-amazon.com/images/M/MV5BMTg4ODYwOTU0N15BMl5BanBnXkFtZTgwNjc5NDM2MTE@._V1_.jpg', 1, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `job_status` (`id`, `name`) VALUES (1, 'Complete');

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_post`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `client_id`, `job_status_id`) VALUES (1, 'Full stack application to manage orders restaurant orders for my small business', '2021-09-08', '2021-10-20', 1, 0, '2021-09-08', '2021-09-09', 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `developer_education`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `developer_education` (`id`, `education_type`, `institution_name`, `degree_certificate_name`, `complete_date`, `developer_account_id`) VALUES (1, 'Trade Skill', 'Skill Distillery', 'Certificate', '2021-11-08', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `work_experience`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `developer_account_id`) VALUES (1, 'Full stack java developer', 'Amazon', '2021-01-01', NULL, 1);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `developer_account_id`) VALUES (2, 'Full stack java developer', 'Microsoft', '2020-05-16', NULL, 2);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `developer_account_id`) VALUES (3, 'Database Administrator', 'Oracle', '2019-01-15', NULL, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `developer_skill`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `developer_account_id`) VALUES (1, 'Java', 'Entry Level', 1);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `developer_account_id`) VALUES (2, 'Python', 'Mid Level', 2);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `developer_account_id`) VALUES (3, 'Database Administrator', 'Expert', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_application`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `job_application` (`id`, `application_approval`, `application_status`, `application_date`, `job_post_id`, `developer_id`, `decision_date`) VALUES (1, 1, 'Approved', '2021-09-09', 1, 1, '2021-09-09');

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_detail`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `job_detail` (`id`, `start_date`, `finish_date`, `job_rating`, `job_rating_comment`, `job_application_id`) VALUES (1, '2021-09-09', '2021-09-15', 5, 'Excellent work!', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_application_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `job_application_comment` (`id`, `comment`, `comment_date`, `job_application_id`, `in_reply_to_comment_id`) VALUES (1, 'Talented developer!', '2021-09-19', 1, NULL);
INSERT INTO `job_application_comment` (`id`, `comment`, `comment_date`, `job_application_id`, `in_reply_to_comment_id`) VALUES (2, 'Thank you for your comment!', '2021-09-19', 1, 1);

COMMIT;

