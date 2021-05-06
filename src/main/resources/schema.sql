-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: localhost    Database: tennisleague
-- ------------------------------------------------------
-- Server version	8.0.24


DROP TABLE IF EXISTS `match_participants`;
DROP TABLE IF EXISTS `tennis_match`;
DROP TABLE IF EXISTS `round`;
DROP TABLE IF EXISTS `participant`;



CREATE TABLE `participant` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `last_modified_on` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `qualified` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `round` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `last_modified_on` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `tennis_match` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `last_modified_on` datetime DEFAULT NULL,
  `scheduled_on` datetime DEFAULT NULL,
  `round_id` bigint DEFAULT NULL,
  `winner_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpqaggj110xx7cghp2es1gcjsq` (`round_id`),
  KEY `FKadufqemkelve9wdp1idf9kud4` (`winner_id`),
  CONSTRAINT `FKadufqemkelve9wdp1idf9kud4` FOREIGN KEY (`winner_id`) REFERENCES `participant` (`id`),
  CONSTRAINT `FKpqaggj110xx7cghp2es1gcjsq` FOREIGN KEY (`round_id`) REFERENCES `round` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `match_participants` (
  `match_id` bigint NOT NULL,
  `participant_id` bigint NOT NULL,
  KEY `FKotfpivkl4rybflr2b1ryt8y64` (`participant_id`),
  KEY `FKnsfirxp9b03b6qja01jui4mak` (`match_id`),
  CONSTRAINT `FKnsfirxp9b03b6qja01jui4mak` FOREIGN KEY (`match_id`) REFERENCES `tennis_match` (`id`),
  CONSTRAINT `FKotfpivkl4rybflr2b1ryt8y64` FOREIGN KEY (`participant_id`) REFERENCES `participant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;