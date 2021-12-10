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
INSERT INTO `job_type` (`id`, `name`) VALUES (1, 'Analyst Programmer');
INSERT INTO `job_type` (`id`, `name`) VALUES (2, 'Product Engineer');
INSERT INTO `job_type` (`id`, `name`) VALUES (3, 'Backend Developer');
INSERT INTO `job_type` (`id`, `name`) VALUES (4, 'Database Administrator');
INSERT INTO `job_type` (`id`, `name`) VALUES (5, 'Frontend Developer');
INSERT INTO `job_type` (`id`, `name`) VALUES (6, 'Mobile Application Developer');
INSERT INTO `job_type` (`id`, `name`) VALUES (7, 'Systems Administrator I');
INSERT INTO `job_type` (`id`, `name`) VALUES (8, 'Web Developer');
INSERT INTO `job_type` (`id`, `name`) VALUES (9, 'Desktop Developer');
INSERT INTO `job_type` (`id`, `name`) VALUES (10, 'Full Stack Developer');
INSERT INTO `job_type` (`id`, `name`) VALUES (11, 'Graphics Developer');
INSERT INTO `job_type` (`id`, `name`) VALUES (12, 'UI/UX');
INSERT INTO `job_type` (`id`, `name`) VALUES (13, 'Business Analysis');
INSERT INTO `job_type` (`id`, `name`) VALUES (14, 'Game Developer');
INSERT INTO `job_type` (`id`, `name`) VALUES (15, 'Data Scientist');
INSERT INTO `job_type` (`id`, `name`) VALUES (16, 'DevOps Developer');
INSERT INTO `job_type` (`id`, `name`) VALUES (17, 'CRM Developer');
INSERT INTO `job_type` (`id`, `name`) VALUES (18, 'Security Developer');
INSERT INTO `job_type` (`id`, `name`) VALUES (19, 'QA');
INSERT INTO `job_type` (`id`, `name`) VALUES (20, 'Software Integration Engineer');

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
INSERT INTO `job_status` (`id`, `name`) VALUES (6, 'Complete');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `employer`) VALUES (1, 'rodfed', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'ADMIN', 'Ronald', 'Viramontes', 'ron@example.com', '286-925-7463', 'https://robohash.org/doloribusutquas.jpg?size=300x400&set=set1', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `employer`) VALUES (2, 'jacob', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'USER', 'Jacob', 'Tweedy', 'tweedy@example.com', '997-653-6462', 'https://robohash.org/temporaundevoluptatem.jpg?size=300x400&set=set1', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `employer`) VALUES (3, 'garrett', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'USER', 'Garrett', 'Pipes', 'pipes@example.com', '506-692-6113', 'https://robohash.org/dolorinsit.jpg?size=300x400&set=set1', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `employer`) VALUES (4, 'fperez', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'USER', 'Fae', 'Pezey', 'fpezey3@va.gov', '242-208-3615', 'https://robohash.org/adipisciatempore.jpg?size=300x400&set=set1', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `employer`) VALUES (5, 'beckem', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'USER', 'Bartholomew', 'Ceccoli', 'bceccoli4@tamu.edu', '154-898-5216', 'https://robohash.org/nesciuntetipsa.jpg?size=300x400&set=set1', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `employer`) VALUES (6, 'dolton', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'USER', 'Dana', 'Lewin', 'dlewin5@foxnews.com', '100-376-6062', 'https://robohash.org/enimexqui.jpg?size=300x400&set=set1', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `employer`) VALUES (7, 'packer', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'USER', 'Colet', 'Whiff', 'cwhiff6@shutterfly.com', '971-858-0562', 'https://robohash.org/aliquidomnisdolorem.jpg?size=300x400&set=set1', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `employer`) VALUES (8, 'powerone', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'USER', 'Klement', 'Trude', 'ktrude7@cloudflare.com', '833-270-7719', 'https://robohash.org/debitisdoloribussit.jpg?size=300x400&set=set1', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `employer`) VALUES (9, 'happyone', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'USER', 'Hazlett', 'Japp', 'hjapp8@npr.org', '140-770-1673', 'https://robohash.org/vitaevoluptatemoptio.jpg?size=300x400&set=set1', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `image_url`, `employer`) VALUES (10, 'tracker', '$2a$10$.bAtAkAx6AIbgO6UImrZYeQTaQSvt5tT5MYdaWyx6hpGNpSoSruim', 1, 'USER', 'Tore', 'Vye', 'tvye9@google.com.br', '171-453-2797', 'https://robohash.org/etvoluptatemnulla.jpg?size=300x400&set=set1', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_post`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (1, 'Simple profile HTML page', '2021-12-05', '2021-12-06', 1, 0, '2021-12-05', '2021-12-06', 8, 6, 1);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (2, 'Develop a SQL database for Amazon EC2 Instance and RDS', '2021-12-06', '2022-01-21', 2, 1, '2021-12-05', NULL, 4, 1, 2);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (3, 'Design a simple website using only HTML CSS and JavaScript', '2021-12-07', '2022-01-22', 1, 1, '2021-12-05', NULL, 8, 1, 3);
INSERT INTO `job_post` (`id`, `job_requirements`, `start_date`, `completion_date`, `developers_needed`, `job_active`, `date_posted`, `date_closed`, `job_type_id`, `job_status_id`, `user_id`) VALUES (4, 'Full Stack Application using Java, Spring Boot, Gradle, and Angular.  The application is to help with inventory of warehouse stock for non-profit org.', '2021-12-05', '2022-01-20', 3, 1, '2021-12-05', NULL, 10, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `developer_education`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `developer_education` (`id`, `education_type`, `institution_name`, `degree_certificate_name`, `complete_date`, `user_id`) VALUES (1, 'Bootcamp', 'Skill Distillery', 'Certificate', '2021-11-08', 1);
INSERT INTO `developer_education` (`id`, `education_type`, `institution_name`, `degree_certificate_name`, `complete_date`, `user_id`) VALUES (2, 'Bootcamp', 'Skill Distillery', 'Certificate', '2021-11-08', 2);
INSERT INTO `developer_education` (`id`, `education_type`, `institution_name`, `degree_certificate_name`, `complete_date`, `user_id`) VALUES (3, 'Bootcamp', 'Skill Distillery', 'Certificate', '2021-11-08', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `work_experience`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (1, 'Military Paramedic', 'US Air Force', '2000-04-05', '2021-05-31', 1);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (2, 'Database Administrator', 'Kingland', '2015-11-08', '2021-11-08', 2);
INSERT INTO `work_experience` (`id`, `job_title`, `company_name`, `start_date`, `end_date`, `user_id`) VALUES (3, 'Military Linguist', 'US Air Force', '2005-11-08', '2017-10-08', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `developer_skill`
-- -----------------------------------------------------
START TRANSACTION;
USE `enginexdb`;
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (1, 'Java', 'Entry Level', 1);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (2, 'SQL', 'Entry Level', 2);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (3, 'JavaScript', 'Entry Level', 3);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (4, 'Python', 'Entry Level', 1);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (5, 'Angular', 'Entry Level', 2);
INSERT INTO `developer_skill` (`id`, `skill_title`, `skill_level`, `user_id`) VALUES (6, 'Spring Boot', 'Entry Level', 3);

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

