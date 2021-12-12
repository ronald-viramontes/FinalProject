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
-- Table `job_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_type` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `job_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `job_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_status` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `job_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(55) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(150) NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1 COMMENT '							',
  `role` VARCHAR(45) NULL DEFAULT 'USER',
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(12) NULL DEFAULT 'XXX-XXX-XXXX',
  `image_url` VARCHAR(1000) NULL DEFAULT 'https://robohash.org/ipsacumdignissimos.jpg?size=300x400&set=set1',
  `employer` VARCHAR(255) NULL DEFAULT '',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = DEFAULT;

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
  `job_active` TINYINT NULL DEFAULT 1,
  `date_posted` DATE NOT NULL,
  `date_closed` DATE NULL,
  `job_type_id` INT NULL,
  `job_status_id` INT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_job_post_job_type1`
    FOREIGN KEY (`job_type_id`)
    REFERENCES `job_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_post_job_status1`
    FOREIGN KEY (`job_status_id`)
    REFERENCES `job_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_post_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = DEFAULT;

SHOW WARNINGS;
CREATE INDEX `fk_job_post_job_type1_idx` ON `job_post` (`job_type_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_job_post_job_status1_idx` ON `job_post` (`job_status_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_job_post_user1_idx` ON `job_post` (`user_id` ASC);

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
  `complete_date` DATE NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_developer_education_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_developer_education_user1_idx` ON `developer_education` (`user_id` ASC);

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
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_work_experience_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_work_experience_user1_idx` ON `work_experience` (`user_id` ASC);

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
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_developer_skill_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_developer_skill_user1_idx` ON `developer_skill` (`user_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `app_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `app_status` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `app_status` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `job_application`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_application` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `job_application` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `application_date` DATE NULL,
  `job_post_id` INT NOT NULL,
  `decision_date` DATE NULL,
  `user_id` INT NOT NULL,
  `app_status_id` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_job_application_job_post1`
    FOREIGN KEY (`job_post_id`)
    REFERENCES `job_post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_job_application_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_job_application_app_status1`
    FOREIGN KEY (`app_status_id`)
    REFERENCES `app_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_job_application_user1_idx` ON `job_application` (`user_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_job_application_job_post1_idx` ON `job_application` (`job_post_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_job_application_app_status1_idx` ON `job_application` (`app_status_id` ASC);

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
  `job_application_id` INT NULL,
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
  `comment` TEXT NULL,
  `comment_date` DATE NULL,
  `job_application_id` INT NULL,
  `in_reply_to_comment_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_job_application_comment_job_application1`
    FOREIGN KEY (`job_application_id`)
    REFERENCES `job_application` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_application_comment_job_application_comment1`
    FOREIGN KEY (`in_reply_to_comment_id`)
    REFERENCES `job_application_comment` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_job_application_comment_job_application1_idx` ON `job_application_comment` (`job_application_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_job_application_comment_job_application_comment1_idx` ON `job_application_comment` (`in_reply_to_comment_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `chat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `chat` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `send_timestamp` DATETIME NULL,
  `subject` VARCHAR(250) NULL,
  `message` TEXT NULL,
  `user_id` INT NOT NULL,
  `reply_user_id` INT NOT NULL,
  `in_reply_to_chat_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_chat_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_chat_chat1`
    FOREIGN KEY (`reply_user_id`)
    REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_chat_chat2`
    FOREIGN KEY (`in_reply_to_chat_id`)
    REFERENCES `chat` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_chat_user1_idx` ON `chat` (`user_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_chat_chat2_idx` ON `chat` (`in_reply_to_chat_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_chat_chat1_idx` ON `chat` (`reply_user_id` ASC);

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
-- Data for table `job_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `job_type` (`id`, `name`) VALUES (1, 'Backend Application Development');
INSERT INTO `job_type` (`id`, `name`) VALUES (2, 'Database Schema Design and Implementation');
INSERT INTO `job_type` (`id`, `name`) VALUES (3, 'Frontend Application Development');
INSERT INTO `job_type` (`id`, `name`) VALUES (4, 'Android or iOS Mobile Application');
INSERT INTO `job_type` (`id`, `name`) VALUES (5, 'Web Application Development');
INSERT INTO `job_type` (`id`, `name`) VALUES (6, 'Website HTML/CSS Development');
INSERT INTO `job_type` (`id`, `name`) VALUES (7, 'Full Stack Application Development');
INSERT INTO `job_type` (`id`, `name`) VALUES (8, 'UI/UX Design / Development');
INSERT INTO `job_type` (`id`, `name`) VALUES (9, 'Application Security Development  ');
INSERT INTO `job_type` (`id`, `name`) VALUES (10, 'Application Quality Assurance');
INSERT INTO `job_type` (`id`, `name`) VALUES (11, 'Other - Software Development Project');

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `job_status` (`id`, `name`) VALUES (1, 'Open');
INSERT INTO `job_status` (`id`, `name`) VALUES (2, 'In Progress');
INSERT INTO `job_status` (`id`, `name`) VALUES (3, 'Planning Phase');
INSERT INTO `job_status` (`id`, `name`) VALUES (4, 'Closed');
INSERT INTO `job_status` (`id`, `name`) VALUES (5, 'Complete');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `employer`) VALUES (1, 'rodfed', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'ADMIN', 'Ronald', 'Viramontes', 'ron@example.com', '286-925-7463', 'https://robohash.org/doloribusutquas.jpg?size=300x400&set=set1', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `employer`) VALUES (2, 'roger', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'USER', 'Roger', 'Willcox', 'rowilcox@example.com', '997-653-6462', 'https://robohash.org/temporaundevoluptatem.jpg?size=300x400&set=set1', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `employer`) VALUES (3, 'terry', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'USER', 'Terry', 'Craymore', 'craymore@example.com', '506-692-6113', 'https://robohash.org/dolorinsit.jpg?size=300x400&set=set1', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `employer`) VALUES (4, 'fperez', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'USER', 'Freddy', 'Pezey', 'fpezey3@va.gov', '242-208-3615', 'https://robohash.org/adipisciatempore.jpg?size=300x400&set=set1', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `employer`) VALUES (5, 'beckem', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'USER', 'Bartholomew', 'Ceccoli', 'bceccoli4@tamu.edu', '154-898-5216', 'https://robohash.org/nesciuntetipsa.jpg?size=300x400&set=set1', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `employer`) VALUES (6, 'dolton', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'USER', 'Dana', 'Lewin', 'dlewin5@foxnews.com', '100-376-6062', 'https://robohash.org/enimexqui.jpg?size=300x400&set=set1', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `employer`) VALUES (7, 'packer', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'USER', 'Colet', 'Whiff', 'cwhiff6@shutterfly.com', '971-858-0562', 'https://robohash.org/aliquidomnisdolorem.jpg?size=300x400&set=set1', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `employer`) VALUES (8, 'powerone', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'USER', 'Klement', 'Trude', 'ktrude7@cloudflare.com', '833-270-7719', 'https://robohash.org/debitisdoloribussit.jpg?size=300x400&set=set1', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `employer`) VALUES (9, 'happyone', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'USER', 'Hazlett', 'Japp', 'hjapp8@npr.org', '140-770-1673', 'https://robohash.org/vitaevoluptatemoptio.jpg?size=300x400&set=set1', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `employer`) VALUES (10, 'tracker', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'USER', 'Tore', 'Bylor', 'tvye9@google.com.br', '171-453-2797', 'https://robohash.org/etvoluptatemnulla.jpg?size=300x400&set=set1', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_post`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (1, 'Webpage for Local Homeless Shelter', '2021-12-05', '2021-12-06', 1, 0, '2021-12-05', '2021-12-07', 6, 5, 1);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (2, 'Develop a SQL database for Amazon EC2 Instance and RDS', '2021-12-06', '2022-01-21', 2, 1, '2021-12-05', NULL, 2, 1, 2);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (3, 'Design a simple website using only HTML CSS and JavaScript', '2021-12-07', '2022-01-22', 1, 1, '2021-12-05', NULL, 3, 1, 3);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (4, 'Application for tracking daily tasks and appointments. Java, Spring Boot, Gradle, and Angular', '2021-12-05', '2022-01-20', 3, 1, '2021-12-05', NULL, 7, 1, 1);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (5, 'Web application for non-profit organization inventory management', '2021-12-10', '2021-12-30', 2, 1, '2021-12-05', NULL, 5, 1, 2);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (6, 'Conduct security vulnerability assessment for non-profit webstore', '2021-12-11', '2022-01-20', 1, 1, '2021-12-05', NULL, 9, 1, 3);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (7, 'J-Unit/QA testing for newly developed web application', '2021-12-12', '2022-01-21', 2, 1, '2021-12-05', NULL, 10, 1, 4);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (8, 'Full stack app for managing medication administration for local cliniic', '2021-12-13', '2022-01-22', 2, 1, '2021-12-05', NULL, 7, 1, 5);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (9, 'Evaluate current database schema', '2021-12-14', '2022-01-19', 3, 1, '2021-12-05', NULL, 2, 1, 6);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (10, 'iOS application for tracking research references', '2021-12-15', '2022-01-20', 2, 1, '2021-12-05', NULL, 4, 1, 4);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (11, 'Design and implement new UI for existing command line application', '2021-12-11', '2022-01-21', 3, 1, '2021-12-05', NULL, 8, 1, 5);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (12, 'Single page web application for school fundraiser donations', '2021-12-17', '2022-01-22', 1, 1, '2021-12-06', NULL, 6, 1, 6);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (13, 'Review current design and security implementation for church website', '2021-12-18', '2022-01-23', 2, 1, '2021-12-07', NULL, 10, 1, 1);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (14, 'Java application for database', '2021-12-12', '2022-01-24', 1, 1, '2021-12-08', NULL, 1, 1, 2);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (15, 'Improve existing web application with bootstrap and compatible Angular components', '2021-12-13', '2022-01-25', 2, 1, '2021-12-09', NULL, 6, 1, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `developer_education`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `developer_education` (`id`, `education_type`, `institution_name`, `degree_certificate_name`, `complete_date`, `user_id`) VALUES (1, 'Bootcamp', 'Skill Distillery', 'Certificate Program', '2021-11-08', 1);
INSERT INTO `developer_education` (`id`, `education_type`, `institution_name`, `degree_certificate_name`, `complete_date`, `user_id`) VALUES (2, 'Undergraduate', 'Trident University', 'Bachelor of Science', '2016-01-15', 1);
INSERT INTO `developer_education` (`id`, `education_type`, `institution_name`, `degree_certificate_name`, `complete_date`, `user_id`) VALUES (3, 'Undergraduate', 'Eastern Illinois University', 'Bachelor of Business Administration', '2021-11-08', 2);
INSERT INTO `developer_education` (`id`, `education_type`, `institution_name`, `degree_certificate_name`, `complete_date`, `user_id`) VALUES (4, 'Undergraduate', 'Adams State University', 'Bachelor of Science', '2015-05-15', 3);
INSERT INTO `developer_education` (`id`, `education_type`, `institution_name`, `degree_certificate_name`, `complete_date`, `user_id`) VALUES (5, 'Undergraduate', 'Antioch University', 'Bachelor of Arts', '2017-01-19', 4);
INSERT INTO `developer_education` (`id`, `education_type`, `institution_name`, `degree_certificate_name`, `complete_date`, `user_id`) VALUES (6, 'Undergraduate', 'Broward College', 'Bachelor of Science', '2014-05-15', 5);
INSERT INTO `developer_education` (`id`, `education_type`, `institution_name`, `degree_certificate_name`, `complete_date`, `user_id`) VALUES (7, 'Graduate', 'Buena Vista University', 'Master of Science', '2019-09-15', 6);
INSERT INTO `developer_education` (`id`, `education_type`, `institution_name`, `degree_certificate_name`, `complete_date`, `user_id`) VALUES (8, 'Undergraduate', 'California State University-Sacramento', 'Bachelor of Science', '2005-11-01', 7);
INSERT INTO `developer_education` (`id`, `education_type`, `institution_name`, `degree_certificate_name`, `complete_date`, `user_id`) VALUES (9, 'Undergraduate', 'Cape Fear Community College', 'Associate of Applied Science', '2020-05-18', 8);
INSERT INTO `developer_education` (`id`, `education_type`, `institution_name`, `degree_certificate_name`, `complete_date`, `user_id`) VALUES (10, 'Undergraduate', 'Cleveland State University', 'Bachelor of Architecture', '2021-04-10', 9);
INSERT INTO `developer_education` (`id`, `education_type`, `institution_name`, `degree_certificate_name`, `complete_date`, `user_id`) VALUES (11, 'Graduate', 'Johnson University', 'Bachelor of Science', '2019-10-05', 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `work_experience`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (1, 'Military Paramedic', 'US Air Force', '2000-04-05', '2021-05-31', 1);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (2, 'Junior Software Developer', 'Oracle', '2015-11-08', '2021-11-08', 2);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (3, 'Senior Full Stack Developer', 'US Government', '2005-11-08', '2017-10-08', 3);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (4, 'Database Administrator', 'Amazon', '2010-05-05', '2021-01-12', 4);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (5, 'Backend Developer', 'Microsoft', '2005-10-11', '2017-06-07', 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `developer_skill`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (1, 'Java', 'Entry Level', 1);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (2, 'SQL', 'Entry Level', 1);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (3, 'JavaScript', 'Entry Level', 2);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (4, 'Python', 'Entry Level', 2);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (5, 'Angular', 'Entry Level', 1);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (6, 'Spring Boot', 'Entry Level', 3);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (7, 'Database Administrator', 'Mid Level', 4);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (8, 'Ruby', 'Expert Level', 5);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (9, 'C++', 'Mid Level', 6);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (10, 'C#', 'Mid Level', 7);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (11, 'PHP', 'Expert Level', 8);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (12, 'GO', 'Mid Level', 9);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (13, 'Swift', 'Expert Level', 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `app_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `app_status` (`id`, `name`) VALUES (1, 'Pending Review');
INSERT INTO `app_status` (`id`, `name`) VALUES (2, 'In Review');
INSERT INTO `app_status` (`id`, `name`) VALUES (3, 'Approved');
INSERT INTO `app_status` (`id`, `name`) VALUES (4, 'Declined');

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_application`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `job_application` (`id`, `application_date`, `job_post_id`, `decision_date`, `user_id`, `app_status_id`) VALUES (1, '2021-12-05', 2, NULL, 1, 1);
INSERT INTO `job_application` (`id`, `application_date`, `job_post_id`, `decision_date`, `user_id`, `app_status_id`) VALUES (2, '2021-12-05', 1, '2021-12-05', 2, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_detail`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `job_detail` (`id`, `start_date`, `finish_date`, `job_rating`, `job_rating_comment`, `job_application_id`) VALUES (1, '2021-12-05', '2021-12-06', 8, 'Excellent work on the profile page! Exceeded my expectations', 2);

COMMIT;

