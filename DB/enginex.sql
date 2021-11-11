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
  `description` TEXT NULL,
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
  `name` VARCHAR(255) NULL DEFAULT NULL,
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
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(150) NOT NULL,
  `enabled` TINYINT NULL DEFAULT 1,
  `role` VARCHAR(45) NULL DEFAULT NULL,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(12) NULL DEFAULT NULL,
  `image_url` VARCHAR(1000) NULL DEFAULT NULL,
  `company_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_user_company1_idx` ON `user` (`company_id` ASC);

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
  `job_status_id` INT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_job_post_job_type1`
    FOREIGN KEY (`job_type_id`)
    REFERENCES `job_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_post_status1`
    FOREIGN KEY (`job_status_id`)
    REFERENCES `job_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_post_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_job_post_job_type1_idx` ON `job_post` (`job_type_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_job_post_status1_idx` ON `job_post` (`job_status_id` ASC);

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
  `education_type` TEXT NULL DEFAULT NULL,
  `institution_name` TEXT NOT NULL,
  `degree_certificate_name` TEXT NOT NULL,
  `complete_date` DATE NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_developer_education_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
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
  `job_title` VARCHAR(250) NULL DEFAULT NULL,
  `company_name` TEXT NULL DEFAULT NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_work_experience_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
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
  `skill_title` VARCHAR(400) NULL DEFAULT NULL,
  `skill_level` VARCHAR(250) NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_developer_skill_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_developer_skill_user1_idx` ON `developer_skill` (`user_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `job_application`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_application` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `job_application` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `application_approval` TINYINT NULL,
  `application_status` VARCHAR(45) NULL DEFAULT NULL,
  `application_date` DATE NULL,
  `job_post_id` INT NOT NULL,
  `decision_date` DATE NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_job_application_status_job_post1`
    FOREIGN KEY (`job_post_id`)
    REFERENCES `job_post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_application_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_job_application_status_job_post1_idx` ON `job_application` (`job_post_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_job_application_user1_idx` ON `job_application` (`user_id` ASC);

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

-- -----------------------------------------------------
-- Table `chat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `chat` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `send_timestamp` DATETIME NULL,
  `subject` VARCHAR(250) NULL,
  `message` TEXT NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  `receiver_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_chat_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_chat_chat1`
    FOREIGN KEY (`receiver_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_chat_user1_idx` ON `chat` (`user_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_chat_chat1_idx` ON `chat` (`receiver_id` ASC);

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
INSERT INTO `job_type` (`id`, `name`, `description`) VALUES (1, 'Analyst Programmer', '');
INSERT INTO `job_type` (`id`, `name`, `description`) VALUES (2, 'Product Engineer', '');
INSERT INTO `job_type` (`id`, `name`, `description`) VALUES (3, 'Backend Developer', '');
INSERT INTO `job_type` (`id`, `name`, `description`) VALUES (4, 'Database Administrator', '');
INSERT INTO `job_type` (`id`, `name`, `description`) VALUES (5, 'Frontend Developer', '');
INSERT INTO `job_type` (`id`, `name`, `description`) VALUES (6, 'Mobile Application Developer', '');
INSERT INTO `job_type` (`id`, `name`, `description`) VALUES (7, 'Systems Administrator I', '');
INSERT INTO `job_type` (`id`, `name`, `description`) VALUES (8, 'Web Developer', '');
INSERT INTO `job_type` (`id`, `name`, `description`) VALUES (9, 'Desktop Developer', '');
INSERT INTO `job_type` (`id`, `name`, `description`) VALUES (10, 'Full Stack Developer', '');
INSERT INTO `job_type` (`id`, `name`, `description`) VALUES (11, 'Graphics Developer', '');
INSERT INTO `job_type` (`id`, `name`, `description`) VALUES (12, 'UI/UX', '');
INSERT INTO `job_type` (`id`, `name`, `description`) VALUES (13, 'Business Analysis', '');
INSERT INTO `job_type` (`id`, `name`, `description`) VALUES (14, 'Game Developer', '');
INSERT INTO `job_type` (`id`, `name`, `description`) VALUES (15, 'Data Scientist', '');
INSERT INTO `job_type` (`id`, `name`, `description`) VALUES (16, 'DevOps Developer', '');
INSERT INTO `job_type` (`id`, `name`, `description`) VALUES (17, 'CRM Developer', '');
INSERT INTO `job_type` (`id`, `name`, `description`) VALUES (18, 'Security Developer', '');
INSERT INTO `job_type` (`id`, `name`, `description`) VALUES (19, 'QA', '');
INSERT INTO `job_type` (`id`, `name`, `description`) VALUES (20, 'Software Integration Engineer', '');

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `job_status` (`id`, `name`) VALUES (1, 'Open');
INSERT INTO `job_status` (`id`, `name`) VALUES (2, 'In Progress');
INSERT INTO `job_status` (`id`, `name`) VALUES (3, 'In Review');
INSERT INTO `job_status` (`id`, `name`) VALUES (4, 'Planning Phase');
INSERT INTO `job_status` (`id`, `name`) VALUES (5, 'Closed');

COMMIT;


-- -----------------------------------------------------
-- Data for table `company`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `company` (`id`, `company_name`) VALUES (1, 'Apple Inc.');
INSERT INTO `company` (`id`, `company_name`) VALUES (2, 'Microsoft Corp');
INSERT INTO `company` (`id`, `company_name`) VALUES (3, 'Alphabet Inc.');
INSERT INTO `company` (`id`, `company_name`) VALUES (4, 'Amazon');
INSERT INTO `company` (`id`, `company_name`) VALUES (5, 'Facebook');
INSERT INTO `company` (`id`, `company_name`) VALUES (6, 'Nvidia Corp.');
INSERT INTO `company` (`id`, `company_name`) VALUES (7, 'Dell Technologies');
INSERT INTO `company` (`id`, `company_name`) VALUES (8, 'R & D Software');
INSERT INTO `company` (`id`, `company_name`) VALUES (9, 'Burton Systems Software');
INSERT INTO `company` (`id`, `company_name`) VALUES (10, 'Southern Software Tech & Rs');
INSERT INTO `company` (`id`, `company_name`) VALUES (11, 'Despatch Cloud');
INSERT INTO `company` (`id`, `company_name`) VALUES (12, 'Lifecycle Mobile');
INSERT INTO `company` (`id`, `company_name`) VALUES (13, 'Agency Technologies');
INSERT INTO `company` (`id`, `company_name`) VALUES (14, 'Hensley Solutions');
INSERT INTO `company` (`id`, `company_name`) VALUES (15, 'Spartan Enterprise Group');
INSERT INTO `company` (`id`, `company_name`) VALUES (16, 'Concurrent Tech');
INSERT INTO `company` (`id`, `company_name`) VALUES (17, 'The Little Guy Tech');
INSERT INTO `company` (`id`, `company_name`) VALUES (18, 'Zoomzone');
INSERT INTO `company` (`id`, `company_name`) VALUES (19, 'Voonyx');
INSERT INTO `company` (`id`, `company_name`) VALUES (20, 'Ionic Solutions');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `company_id`) VALUES (1, 'rodfed', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'Developer', 'Ronald', 'Viramontes', 'ron@example.com', '286-925-7463', 'https://robohash.org/doloribusutquas.jpg?size=300x400&set=set1', 1);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `company_id`) VALUES (2, 'jacob', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'Client', 'Jacob', 'Tweedy', 'tweedy@example.com', '997-653-6462', 'https://robohash.org/temporaundevoluptatem.jpg?size=300x400&set=set1', 2);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `company_id`) VALUES (3, 'garrett', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'Developer', 'Garrett', 'Pipes', 'pipes@example.com', '506-692-6113', 'https://robohash.org/dolorinsit.jpg?size=300x400&set=set1', 3);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `company_id`) VALUES (4, 'fperez', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'Client', 'Fae', 'Pezey', 'fpezey3@va.gov', '242-208-3615', 'https://robohash.org/adipisciatempore.jpg?size=300x400&set=set1', 4);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `company_id`) VALUES (5, 'beckem', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'Developer', 'Bartholomew', 'Ceccoli', 'bceccoli4@tamu.edu', '154-898-5216', 'https://robohash.org/nesciuntetipsa.jpg?size=300x400&set=set1', 5);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `company_id`) VALUES (6, 'dolton', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'Client', 'Dana', 'Lewin', 'dlewin5@foxnews.com', '100-376-6062', 'https://robohash.org/enimexqui.jpg?size=300x400&set=set1', 6);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `company_id`) VALUES (7, 'packer', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'Developer', 'Colet', 'Whiff', 'cwhiff6@shutterfly.com', '971-858-0562', 'https://robohash.org/aliquidomnisdolorem.jpg?size=300x400&set=set1', 7);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `company_id`) VALUES (8, 'powerone', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'Client', 'Klement', 'Trude', 'ktrude7@cloudflare.com', '833-270-7719', 'https://robohash.org/debitisdoloribussit.jpg?size=300x400&set=set1', 8);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `company_id`) VALUES (9, 'happyone', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'Developer', 'Hazlett', 'Japp', 'hjapp8@npr.org', '140-770-1673', 'https://robohash.org/vitaevoluptatemoptio.jpg?size=300x400&set=set1', 9);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `company_id`) VALUES (10, 'tracker', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'Client', 'Tore', 'Vye', 'tvye9@google.com.br', '171-453-2797', 'https://robohash.org/etvoluptatemnulla.jpg?size=300x400&set=set1', 10);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `company_id`) VALUES (11, 'twinkle', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'Developer', 'Callida', 'Bowling', 'cbowlinga@usa.gov', '963-961-1064', 'https://robohash.org/doloribusdebitisdolor.jpg?size=300x400&set=set1', 11);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `company_id`) VALUES (12, 'sapper', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'Client', 'Suzie', 'Ambrogiotti', 'sambrogiottib@smh.com.au', '959-407-2035', 'https://robohash.org/eaqueatodio.jpg?size=300x400&set=set1', 12);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `company_id`) VALUES (13, 'quantum', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'Developer', 'Myrtle', 'Bayman', 'mbaymanc@umn.edu', '342-220-8148', 'https://robohash.org/illoprovidentnihil.jpg?size=300x400&set=set1', 13);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `company_id`) VALUES (14, 'intrepid', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'Client', 'Faulkner', 'Rignold', 'frignoldd@usnews.com', '619-397-7049', 'https://robohash.org/dignissimosanimiveniam.jpg?size=300x400&set=set1', 14);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `company_id`) VALUES (15, 'tomstone', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'Developer', 'April', 'Ibbitson', 'aibbitsone@cmu.edu', '961-118-0451', 'https://robohash.org/voluptasconsequaturvoluptate.jpg?size=300x400&set=set1', 15);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `company_id`) VALUES (16, 'antapol', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'Client', 'Auguste', 'Runnacles', 'arunnaclesf@icq.com', '295-915-8815', 'https://robohash.org/suntrerumlibero.jpg?size=300x400&set=set1', 16);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `company_id`) VALUES (17, 'caper', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'Developer', 'Trueman', 'Brabbs', 'tbrabbsg@home.pl', '256-952-7102', 'https://robohash.org/atquerationeenim.jpg?size=300x400&set=set1', 17);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `company_id`) VALUES (18, 'alpha', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'Client', 'Nadiya', 'Kilgour', 'nkilgourh@nih.gov', '367-263-9039', 'https://robohash.org/estexcepturiut.jpg?size=300x400&set=set1', 18);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `company_id`) VALUES (19, 'gandoff', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'Developer', 'Grazia', 'Danielis', 'gdanielisi@cnbc.com', '125-742-7125', 'https://robohash.org/corporisquiut.jpg?size=300x400&set=set1', 19);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `company_id`) VALUES (20, 'omabe', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'Client', 'Ollie', 'Mabe', 'omabej@google.com', '436-237-2536', 'https://robohash.org/ipsacumdignissimos.jpg?size=300x400&set=set1', 20);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_post`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (1, 'Full Stack Developer', '2021-11-04', '2021-11-30', 1, 1, '2021-10-29', NULL, 7, 1, 3);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (2, 'C#', '2021-11-04', '2021-11-28', 1, 1, '2021-10-27', NULL, 9, 1, 2);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (3, 'Arduino', '2021-11-04', '2021-11-09', 1, 1, '2021-10-21', NULL, 19, 1, 3);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (4, 'UI/UX', '2021-11-04', '2021-11-19', 1, 1, '2021-10-12', NULL, 5, 1, 1);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (5, 'Spring REST', '2021-11-04', '2021-11-21', 3, 1, '2021-10-27', NULL, 10, 1, 3);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (6, 'C++', '2021-11-04', '2021-11-30', 1, 1, '2021-10-20', NULL, 16, 1, 1);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (7, 'Spring REST', '2021-11-04', '2021-11-28', 1, 1, '2021-10-26', NULL, 2, 1, 1);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (8, 'Graphics Developer', '2021-11-04', '2021-11-09', 4, 1, '2021-10-22', NULL, 16, 1, 2);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (9, 'Desktop Developer', '2021-11-04', '2021-11-10', 4, 1, '2021-10-21', NULL, 10, 1, 2);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (10, 'SQL', '2021-11-04', '2021-11-11', 2, 1, '2021-10-20', NULL, 12, 1, 3);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (11, 'Graphics Developer', '2021-11-04', '2021-11-30', 4, 1, '2021-10-13', NULL, 18, 1, 1);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (12, 'C++', '2021-11-04', '2021-11-28', 1, 1, '2021-10-27', NULL, 7, 1, 3);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (13, 'Arduino', '2021-11-04', '2021-11-09', 3, 1, '2021-10-12', NULL, 9, 1, 2);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (14, 'Web Developer', '2021-11-04', '2021-11-19', 2, 1, '2021-11-01', NULL, 19, 1, 3);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (15, 'C#', '2021-11-04', '2021-11-21', 3, 1, '2021-10-17', NULL, 20, 1, 1);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (16, 'SQL', '2021-11-04', '2021-11-17', 3, 1, '2021-10-23', NULL, 13, 1, 2);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (17, 'Desktop Developer', '2021-11-04', '2021-11-18', 2, 1, '2021-10-12', NULL, 10, 1, 3);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (18, 'C#', '2021-11-04', '2021-11-19', 1, 1, '2021-10-21', NULL, 19, 1, 1);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (19, 'C#', '2021-11-04', '2021-11-20', 4, 1, '2021-10-28', NULL, 9, 1, 3);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (20, 'C++', '2021-11-04', '2021-11-30', 4, 1, '2021-10-24', NULL, 10, 1, 3);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (21, 'JavaScript', '2021-11-04', '2021-11-28', 1, 1, '2021-10-15', NULL, 3, 1, 3);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (22, 'C#', '2021-11-04', '2021-11-09', 2, 1, '2021-10-19', NULL, 5, 1, 1);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (23, 'Web Developer', '2021-11-04', '2021-11-19', 1, 1, '2021-10-20', NULL, 13, 1, 2);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (24, 'Web Developer', '2021-11-04', '2021-11-21', 4, 1, '2021-10-26', NULL, 16, 1, 3);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (25, 'JavaScript', '2021-11-04', '2021-11-30', 2, 1, '2021-10-31', NULL, 2, 1, 2);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (26, 'C#', '2021-11-04', '2021-11-28', 2, 1, '2021-10-19', NULL, 3, 1, 2);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (27, 'Full Stack Developer', '2021-11-04', '2021-11-09', 3, 1, '2021-10-22', NULL, 3, 1, 2);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (28, 'Spring REST', '2021-11-04', '2021-11-19', 3, 1, '2021-10-22', NULL, 8, 1, 2);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (29, 'SQL', '2021-11-04', '2021-11-21', 4, 1, '2021-10-23', NULL, 1, 1, 2);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (30, 'UI/UX', '2021-11-04', '2021-11-30', 1, 1, '2021-10-16', NULL, 5, 1, 1);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (31, 'Game Developer', '2021-11-04', '2021-11-28', 3, 1, '2021-10-24', NULL, 4, 1, 3);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (32, 'JavaScript', '2021-11-04', '2021-11-09', 4, 1, '2021-10-17', NULL, 19, 1, 2);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (33, 'Business Analysis', '2021-11-04', '2021-11-19', 1, 1, '2021-10-26', NULL, 13, 1, 1);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (34, 'Business Analysis', '2021-11-04', '2021-11-21', 2, 1, '2021-11-01', NULL, 12, 1, 2);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (35, 'Business Analysis', '2021-11-04', '2021-11-30', 3, 1, '2021-10-12', NULL, 19, 1, 2);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (36, 'UI/UX', '2021-11-04', '2021-11-28', 3, 1, '2021-10-18', NULL, 20, 1, 1);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (37, 'C++', '2021-11-04', '2021-11-09', 2, 1, '2021-10-24', NULL, 2, 1, 2);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (38, 'Web Developer', '2021-11-04', '2021-11-19', 2, 1, '2021-10-12', NULL, 10, 1, 3);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (39, 'C#', '2021-11-04', '2021-11-21', 4, 1, '2021-10-29', NULL, 16, 1, 2);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (40, 'Spring REST', '2021-11-04', '2021-11-06', 4, 1, '2021-10-15', NULL, 4, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `developer_education`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `developer_education` (`id`, `education_type`, `institution_name`, `degree_certificate_name`, `complete_date`, `user_id`) VALUES (1, 'Undergraduate', 'B. S. Abdur Rahman University', 'Bachelor of Science', '2019-07-25', 2);
INSERT INTO `developer_education` (`id`, `education_type`, `institution_name`, `degree_certificate_name`, `complete_date`, `user_id`) VALUES (2, 'Graduate', 'Canadian Mennonite University', 'Master of Science', '2020-05-08', 3);
INSERT INTO `developer_education` (`id`, `education_type`, `institution_name`, `degree_certificate_name`, `complete_date`, `user_id`) VALUES (3, 'Associate', 'University of New Hampshire', 'Associate of Science', '2017-06-01', 1);
INSERT INTO `developer_education` (`id`, `education_type`, `institution_name`, `degree_certificate_name`, `complete_date`, `user_id`) VALUES (4, 'High School', 'Hunan Normal University', 'Certificate', '2010-01-03', 2);
INSERT INTO `developer_education` (`id`, `education_type`, `institution_name`, `degree_certificate_name`, `complete_date`, `user_id`) VALUES (5, 'Trade School', 'Skill Distillery', 'Technical Bootcamp', '2021-05-31', 3);
INSERT INTO `developer_education` (`id`, `education_type`, `institution_name`, `degree_certificate_name`, `complete_date`, `user_id`) VALUES (6, 'Undergraduate', 'City University', 'Bachelor of Science', '2020-01-12', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `work_experience`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (1, 'Web Developer', 'Edgepulse', '2004-04-04', '2008-02-20', 1);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (2, 'Desktop Developer', 'Dynabox', '2001-02-22', '2007-04-26', 2);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (3, 'Full Stack Developer', 'Leexo', '2001-04-19', '2018-05-11', 3);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (4, 'Graphics Developer', 'Realmix', '2004-06-22', '2009-11-09', 4);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (5, 'UI/UX', 'Rhyloo', '2002-09-09', '2013-09-11', 5);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (6, 'Business Analysis', 'Edgetag', '2002-07-19', '2016-07-27', 6);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (7, 'Game Developer', 'Ntag', '2003-02-23', '2011-05-17', 7);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (8, 'Help Desk Technician', 'Kwilith', '2003-02-11', '2013-01-25', 8);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (9, 'Web Developer I', 'Jaxworks', '2005-09-27', '2011-08-26', 6);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (10, 'Administrative Assistant IV', 'Kwimbee', '2002-03-11', '2020-03-20', 7);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (11, 'Tax Accountant', 'Feedspan', '2002-05-09', '2010-04-25', 8);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (12, 'Professor', 'Buzzshare', '2001-10-27', '2007-03-13', 9);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (13, 'Research Assistant I', 'Minyx', '2003-12-16', '2008-01-08', 10);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (14, 'Civil Engineer', 'Yodo', '2003-10-23', '2012-12-21', 1);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (15, 'Help Desk Operator', 'Twitterbeat', '2001-10-20', '2012-06-13', 2);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (16, 'Registered Nurse', 'Realmix', '2003-03-06', '2009-01-22', 3);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (17, 'SQL', 'Jabbercube', '2005-09-12', '2011-03-28', 1);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (18, 'Spring REST', 'Zooveo', '2003-07-18', '2013-06-18', 2);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (19, 'JavaScript', 'Skidoo', '2000-11-07', '2010-11-08', 3);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (20, 'C++', 'Myworks', '2003-05-31', '2008-05-11', 4);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (21, 'C#', 'Oyoyo', '2002-07-05', '2019-03-23', 5);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (22, 'Arduino', 'Gigaclub', '2003-10-24', '2015-07-13', 8);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (23, 'Associate Professor', 'Thoughtworks', '2004-02-17', '2014-07-28', 9);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (24, 'Safety Technician I', 'Abata', '2004-01-02', '2011-09-28', 1);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (25, 'Structural Analysis Engineer', 'Zoomzone', '2004-03-19', '2008-02-09', 11);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (26, 'Geological Engineer', 'Wordtune', '2005-03-09', '2019-03-04', 12);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (27, 'Administrative Assistant IV', 'Bubbletube', '2001-08-10', '2017-01-03', 13);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (28, 'Registered Nurse', 'Flipbug', '2004-02-04', '2007-02-08', 2);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (29, 'Actuary', 'Yambee', '2001-11-17', '2010-07-04', 8);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (30, 'GIS Technical Architect', 'Mynte', '2002-04-12', '2010-06-14', 9);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (31, 'Research Associate', 'Wordify', '2001-04-13', '2007-01-10', 2);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (32, 'Desktop Support Technician', 'Plajo', '2005-05-07', '2016-11-28', 11);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (33, 'Operator', 'Linkbridge', '2003-04-19', '2016-02-03', 12);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (34, 'Accounting Assistant II', 'Rhynyx', '2005-07-05', '2020-07-26', 3);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (35, 'GIS Technical Architect', 'Yambee', '2001-06-11', '2021-05-11', 14);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (36, 'Technical Writer', 'Trunyx', '2004-09-26', '2007-01-29', 1);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (37, 'Database Administrator', 'Vidoo', '2001-06-22', '2008-11-06', 9);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (38, 'Frontend Developer', 'Voomm', '2001-06-04', '2010-12-13', 10);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (39, 'Mobile Application Developer', 'Viva', '2002-05-21', '2017-10-15', 11);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (40, 'Systems Administrator I', 'Kwimbee', '2003-01-05', '2014-03-13', 12);

COMMIT;


-- -----------------------------------------------------
-- Data for table `developer_skill`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (1, 'Analyst Programmer', 'Expert', 1);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (2, 'Product Engineer', 'Expert', 2);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (3, 'Backend Developer', 'Advanced Beginner', 3);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (4, 'Database Administrator', 'Proficient', 4);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (5, 'Frontend Developer', 'Proficient', 3);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (6, 'Mobile Application Developer', 'Expert', 4);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (7, 'Systems Administrator I', 'Advanced Beginner', 5);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (8, 'Web Developer', 'Proficient', 6);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (9, 'Desktop Developer', 'Competent', 7);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (10, 'Full Stack Developer', 'Proficient', 8);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (11, 'Graphics Developer', 'Proficient', 1);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (12, 'UI/UX', 'Expert', 2);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (13, 'Business Analysis', 'Novice', 3);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (14, 'Game Developer', 'Novice', 4);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (15, 'Data Scientist', 'Novice', 5);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (16, 'DevOps Developer', 'Advanced Beginner', 6);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (17, 'CRM Developer', 'Novice', 14);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (18, 'Security Developer', 'Competent', 15);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (19, 'QA', 'Advanced Beginner', 16);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (20, 'Java', 'Competent', 17);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (21, 'Python', 'Expert', 18);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (22, 'SQL', 'Advanced Beginner', 19);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (23, 'Spring REST', 'Novice', 20);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (24, 'JavaScript', 'Competent', 1);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (25, 'C++', 'Expert', 2);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (26, 'C#', 'Expert', 3);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (27, 'Arduino', 'Competent', 4);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (28, 'Swift', 'Competent', 5);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (29, 'Angular', 'Expert', 6);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (30, 'Analyst Programmer', 'Expert', 7);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (31, 'Product Engineer', 'Competent', 8);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (32, 'Backend Developer', 'Proficient', 5);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (33, 'Database Administrator', 'Advanced Beginner', 6);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (34, 'Frontend Developer', 'Expert', 7);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (35, 'Mobile Application Developer', 'Novice', 8);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (36, 'Systems Administrator I', 'Competent', 9);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (37, 'Web Developer', 'Advanced Beginner', 10);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (38, 'Desktop Developer', 'Competent', 11);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (39, 'Full Stack Developer', 'Proficient', 3);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (40, 'Graphics Developer', 'Advanced Beginner', 4);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (41, 'UI/UX', 'Advanced Beginner', 5);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (42, 'Business Analysis', 'Proficient', 13);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (43, 'Game Developer', 'Competent', 14);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (44, 'Data Scientist', 'Proficient', 15);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (45, 'DevOps Developer', 'Novice', 16);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (46, 'CRM Developer', 'Advanced Beginner', 17);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (47, 'Security Developer', 'Competent', 18);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (48, 'QA', 'Advanced Beginner', 3);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (49, 'Software Integration Engineer', 'Advanced Beginner', 4);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (50, 'Robotics', 'Advanced Beginner', 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_application`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `job_application` (`id`, `application_approval`, `application_status`, `application_date`, `job_post_id`, `decision_date`, `user_id`) VALUES (1, 0, 'Pending', '2021-11-01', 1, '2021-11-30', 1);
INSERT INTO `job_application` (`id`, `application_approval`, `application_status`, `application_date`, `job_post_id`, `decision_date`, `user_id`) VALUES (2, 0, 'Pending', '2021-11-02', 2, '2021-11-28', 1);
INSERT INTO `job_application` (`id`, `application_approval`, `application_status`, `application_date`, `job_post_id`, `decision_date`, `user_id`) VALUES (3, 0, 'Pending', '2021-11-03', 3, '2021-11-09', 1);
INSERT INTO `job_application` (`id`, `application_approval`, `application_status`, `application_date`, `job_post_id`, `decision_date`, `user_id`) VALUES (4, 0, 'Pending', '2021-11-03', 4, '2021-11-19', 3);
INSERT INTO `job_application` (`id`, `application_approval`, `application_status`, `application_date`, `job_post_id`, `decision_date`, `user_id`) VALUES (5, 0, 'Pending', '2021-11-04', 5, '2021-11-21', 3);
INSERT INTO `job_application` (`id`, `application_approval`, `application_status`, `application_date`, `job_post_id`, `decision_date`, `user_id`) VALUES (6, 0, 'Pending', '2021-11-01', 6, '2021-11-30', 1);
INSERT INTO `job_application` (`id`, `application_approval`, `application_status`, `application_date`, `job_post_id`, `decision_date`, `user_id`) VALUES (7, 0, 'Pending', '2021-11-02', 7, '2021-11-28', 1);
INSERT INTO `job_application` (`id`, `application_approval`, `application_status`, `application_date`, `job_post_id`, `decision_date`, `user_id`) VALUES (8, 0, 'Pending', '2021-11-03', 8, '2021-11-09', 2);
INSERT INTO `job_application` (`id`, `application_approval`, `application_status`, `application_date`, `job_post_id`, `decision_date`, `user_id`) VALUES (9, 0, 'Pending', '2021-11-03', 9, '2021-11-10', 2);
INSERT INTO `job_application` (`id`, `application_approval`, `application_status`, `application_date`, `job_post_id`, `decision_date`, `user_id`) VALUES (10, 0, 'Pending', '2021-11-04', 10, '2021-11-11', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_application_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `job_application_comment` (`id`, `comment`, `comment_date`, `job_application_id`, `in_reply_to_comment_id`) VALUES (1, 'Hello, would like to know any additional details about the job.', '2021-11-01', 1, NULL);
INSERT INTO `job_application_comment` (`id`, `comment`, `comment_date`, `job_application_id`, `in_reply_to_comment_id`) VALUES (2, 'Thank you for your reply!', '2021-11-02', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `chat`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `chat` (`id`, `send_timestamp`, `subject`, `message`, `user_id`, `receiver_id`) VALUES (1, '2021-11-10 10:10:10', 'Future jobs', 'Hello, Do you have any upcoming jobs?', 1, 2);

COMMIT;

