-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия на сървъра:            10.6.4-MariaDB - mariadb.org binary distribution
-- ОС на сървъра:                Win64
-- HeidiSQL Версия:              11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Дъмп на структурата на БД wearedb
DROP DATABASE IF EXISTS `wearedb`;
CREATE DATABASE IF NOT EXISTS `wearedb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `wearedb`;

-- Дъмп структура за таблица wearedb.authorities
DROP TABLE IF EXISTS `authorities`;
CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(30) NOT NULL,
  `authority` varchar(255) NOT NULL,
  PRIMARY KEY (`username`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Дъмп данни за таблица wearedb.authorities: ~2 rows (приблизително)
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` (`username`, `authority`) VALUES
	('admin', 'ROLE_ADMIN'),
	('admin', 'ROLE_USER');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;

-- Дъмп структура за таблица wearedb.authority
DROP TABLE IF EXISTS `authority`;
CREATE TABLE IF NOT EXISTS `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nrgoi6sdvipfsloa7ykxwlslf` (`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Дъмп данни за таблица wearedb.authority: ~2 rows (приблизително)
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` (`id`, `authority`) VALUES
	(2, 'ROLE_ADMIN'),
	(1, 'ROLE_USER');
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;

-- Дъмп структура за таблица wearedb.category_skills
DROP TABLE IF EXISTS `category_skills`;
CREATE TABLE IF NOT EXISTS `category_skills` (
  `skill_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`skill_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8mb3;

-- Дъмп данни за таблица wearedb.category_skills: ~58 rows (приблизително)
/*!40000 ALTER TABLE `category_skills` DISABLE KEYS */;
INSERT INTO `category_skills` (`skill_category_id`, `name`) VALUES
	(100, 'All'),
	(101, 'Accountant'),
	(102, 'Actor'),
	(103, 'Archaeologist'),
	(104, 'Astronaut'),
	(105, 'Author'),
	(106, 'Baker'),
	(107, 'Bus driver'),
	(108, 'Car service'),
	(109, 'Chef/Cook'),
	(110, 'Cleaner'),
	(111, 'Dentist'),
	(112, 'Designer'),
	(113, 'Doctor'),
	(114, 'Electrician'),
	(115, 'Factory worker'),
	(116, 'Farmer'),
	(117, 'Firefighter'),
	(118, 'Fisherman'),
	(119, 'Gardener'),
	(120, 'Hairdresser'),
	(121, 'Journalist'),
	(122, 'Judge'),
	(123, 'Lawyer'),
	(124, 'Lecturer'),
	(125, 'Lifeguard'),
	(126, 'Mechanic'),
	(127, 'Model'),
	(128, 'Musician'),
	(129, 'Nanny'),
	(130, 'Newsreader'),
	(131, 'Nurses'),
	(132, 'Optician'),
	(133, 'Painter'),
	(134, 'Pharmacist'),
	(135, 'Photographer'),
	(136, 'Pilot'),
	(137, 'Plumber'),
	(138, 'Police Officer'),
	(139, 'Policeman'),
	(140, 'Politician'),
	(141, 'Postman'),
	(142, 'Real estate agent'),
	(143, 'Receptionist'),
	(144, 'Scientist'),
	(145, 'Secretary'),
	(146, 'Shop assistant'),
	(147, 'Software developer'),
	(148, 'Soldier'),
	(149, 'Tailor'),
	(150, 'Teachers'),
	(151, 'Translator'),
	(152, 'Travel agent'),
	(153, 'Veterinarian'),
	(154, 'Waiter/Waitress'),
	(155, 'Window cleaner'),
	(156, 'Writer'),
	(157, 'Marketing');
/*!40000 ALTER TABLE `category_skills` ENABLE KEYS */;

-- Дъмп структура за таблица wearedb.cities
DROP TABLE IF EXISTS `cities`;
CREATE TABLE IF NOT EXISTS `cities` (
  `city_id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`city_id`),
  KEY `FK6gatmv9dwedve82icy8wrkdmk` (`country_id`),
  CONSTRAINT `FK6gatmv9dwedve82icy8wrkdmk` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb3;

-- Дъмп данни за таблица wearedb.cities: ~39 rows (приблизително)
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` (`city_id`, `city`, `country_id`) VALUES
	(1, 'Sofia', 1),
	(2, 'Plovdiv', 1),
	(3, 'Varna', 1),
	(4, 'Burgas', 1),
	(5, 'Ruse', 1),
	(6, 'Stara Zagora', 1),
	(7, 'Pleven', 1),
	(8, 'Sliven', 1),
	(9, 'Dobrich', 1),
	(10, 'Shumen', 1),
	(11, 'Pernik', 1),
	(12, 'Haskovo', 1),
	(13, 'Vratsa', 1),
	(14, 'Kyustendil', 1),
	(15, 'Montana', 1),
	(16, 'Lovech', 1),
	(17, 'Razgrad', 1),
	(18, 'Borino', 1),
	(19, 'Madan', 1),
	(20, 'Zlatograd', 1),
	(21, 'Pazardzhik', 1),
	(22, 'Smolyan', 1),
	(23, 'Blagoevgrad', 1),
	(24, 'Nedelino', 1),
	(25, 'Rudozem', 1),
	(26, 'Devin', 1),
	(27, 'Veliko Tarnovo', 1),
	(28, 'Vidin', 1),
	(29, 'Kirkovo', 1),
	(30, 'Krumovgrad', 1),
	(31, 'Dzhebel', 1),
	(32, 'Silistra', 1),
	(33, 'Sarnitsa', 1),
	(34, 'Shiroka Laka', 1),
	(35, 'Yambol', 1),
	(36, 'Dospat', 1),
	(37, 'Kardzhali', 1),
	(38, 'Gabrovo', 1),
	(39, 'Targovishte', 1);
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;

-- Дъмп структура за таблица wearedb.comments_table
DROP TABLE IF EXISTS `comments_table`;
CREATE TABLE IF NOT EXISTS `comments_table` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) DEFAULT NULL,
  `date_time` varchar(255) DEFAULT NULL,
  `post_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FK2u9jv4nfg7r53p4jsa5r6du44` (`post_id`),
  KEY `FK3ef9s87opud5wxtobcp24ej3n` (`user_id`),
  CONSTRAINT `FK2u9jv4nfg7r53p4jsa5r6du44` FOREIGN KEY (`post_id`) REFERENCES `posts_table` (`post_id`),
  CONSTRAINT `FK3ef9s87opud5wxtobcp24ej3n` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb3;

-- Дъмп данни за таблица wearedb.comments_table: ~0 rows (приблизително)
/*!40000 ALTER TABLE `comments_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments_table` ENABLE KEYS */;

-- Дъмп структура за таблица wearedb.comment_likes_table
DROP TABLE IF EXISTS `comment_likes_table`;
CREATE TABLE IF NOT EXISTS `comment_likes_table` (
  `comment_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`comment_id`,`user_id`),
  KEY `FKbt38yfvtantp2jvgf511j0wpj` (`user_id`),
  CONSTRAINT `FKbt38yfvtantp2jvgf511j0wpj` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKcoqgc1cu7jw29qqk4aaas38m` FOREIGN KEY (`comment_id`) REFERENCES `comments_table` (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Дъмп данни за таблица wearedb.comment_likes_table: ~0 rows (приблизително)
/*!40000 ALTER TABLE `comment_likes_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_likes_table` ENABLE KEYS */;

-- Дъмп структура за таблица wearedb.connections_table
DROP TABLE IF EXISTS `connections_table`;
CREATE TABLE IF NOT EXISTS `connections_table` (
  `connection_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_accepter_id` int(11) DEFAULT NULL,
  `user_requester_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`connection_id`),
  KEY `FKoqnpe7indmmieldy8tddycsy1` (`user_requester_id`),
  CONSTRAINT `FKoqnpe7indmmieldy8tddycsy1` FOREIGN KEY (`user_requester_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Дъмп данни за таблица wearedb.connections_table: ~0 rows (приблизително)
/*!40000 ALTER TABLE `connections_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `connections_table` ENABLE KEYS */;

-- Дъмп структура за таблица wearedb.connection_users
DROP TABLE IF EXISTS `connection_users`;
CREATE TABLE IF NOT EXISTS `connection_users` (
  `user_a` int(11) NOT NULL,
  `user_b` int(11) NOT NULL,
  PRIMARY KEY (`user_a`,`user_b`),
  KEY `FKqme3o6ss546tc72kwd11w8r3q` (`user_b`),
  CONSTRAINT `FK3m37qk73ip7rr7ufehrmlp2ns` FOREIGN KEY (`user_a`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKqme3o6ss546tc72kwd11w8r3q` FOREIGN KEY (`user_b`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Дъмп данни за таблица wearedb.connection_users: ~0 rows (приблизително)
/*!40000 ALTER TABLE `connection_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `connection_users` ENABLE KEYS */;

-- Дъмп структура за таблица wearedb.countries
DROP TABLE IF EXISTS `countries`;
CREATE TABLE IF NOT EXISTS `countries` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- Дъмп данни за таблица wearedb.countries: ~0 rows (приблизително)
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` (`id`, `country`) VALUES
	(1, 'BULGARIA');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;

-- Дъмп структура за таблица wearedb.expertise_profile
DROP TABLE IF EXISTS `expertise_profile`;
CREATE TABLE IF NOT EXISTS `expertise_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `category_skill_category_id` int(11) DEFAULT NULL,
  `availability` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3mu6f7gnwye8amp57mf7cpp5a` (`category_skill_category_id`),
  CONSTRAINT `FK3mu6f7gnwye8amp57mf7cpp5a` FOREIGN KEY (`category_skill_category_id`) REFERENCES `category_skills` (`skill_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb3;

-- Дъмп данни за таблица wearedb.expertise_profile: ~2 rows (приблизително)
/*!40000 ALTER TABLE `expertise_profile` DISABLE KEYS */;
INSERT INTO `expertise_profile` (`id`, `end_time`, `start_time`, `category_skill_category_id`, `availability`) VALUES
	(39, NULL, NULL, 100, 0),
	(40, NULL, NULL, 100, 0);
/*!40000 ALTER TABLE `expertise_profile` ENABLE KEYS */;

-- Дъмп структура за таблица wearedb.expertise_profile_skills
DROP TABLE IF EXISTS `expertise_profile_skills`;
CREATE TABLE IF NOT EXISTS `expertise_profile_skills` (
  `expertise_profile_id` int(11) NOT NULL,
  `skills_skill_id` int(11) NOT NULL,
  UNIQUE KEY `UK_nly4sf6igu5dbdk82rblace7l` (`skills_skill_id`),
  KEY `FKner4qgbla4je9nbwqn28naspf` (`expertise_profile_id`),
  CONSTRAINT `FKner4qgbla4je9nbwqn28naspf` FOREIGN KEY (`expertise_profile_id`) REFERENCES `expertise_profile` (`id`),
  CONSTRAINT `FKnsclrcj944iirspw8fkc5yp7l` FOREIGN KEY (`skills_skill_id`) REFERENCES `skills` (`skill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Дъмп данни за таблица wearedb.expertise_profile_skills: ~0 rows (приблизително)
/*!40000 ALTER TABLE `expertise_profile_skills` DISABLE KEYS */;
/*!40000 ALTER TABLE `expertise_profile_skills` ENABLE KEYS */;

-- Дъмп структура за таблица wearedb.feed_back
DROP TABLE IF EXISTS `feed_back`;
CREATE TABLE IF NOT EXISTS `feed_back` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Дъмп данни за таблица wearedb.feed_back: ~0 rows (приблизително)
/*!40000 ALTER TABLE `feed_back` DISABLE KEYS */;
/*!40000 ALTER TABLE `feed_back` ENABLE KEYS */;

-- Дъмп структура за таблица wearedb.locations
DROP TABLE IF EXISTS `locations`;
CREATE TABLE IF NOT EXISTS `locations` (
  `location_id` int(11) NOT NULL AUTO_INCREMENT,
  `city_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`location_id`),
  KEY `FKgvg10vlf7gqvbgf9rbbho3gyg` (`city_id`),
  CONSTRAINT `FKgvg10vlf7gqvbgf9rbbho3gyg` FOREIGN KEY (`city_id`) REFERENCES `cities` (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb3;

-- Дъмп данни за таблица wearedb.locations: ~0 rows (приблизително)
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;

-- Дъмп структура за таблица wearedb.personal_profile
DROP TABLE IF EXISTS `personal_profile`;
CREATE TABLE IF NOT EXISTS `personal_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `birth_year` date DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `personal_review` varchar(255) DEFAULT NULL,
  `picture` longblob DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  `member_since` datetime DEFAULT NULL,
  `picture_privacy` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7fbfvwlfl9l3r660y7nr66lbw` (`location_id`),
  CONSTRAINT `FK7fbfvwlfl9l3r660y7nr66lbw` FOREIGN KEY (`location_id`) REFERENCES `locations` (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb3;

-- Дъмп данни за таблица wearedb.personal_profile: ~1 rows (приблизително)
/*!40000 ALTER TABLE `personal_profile` DISABLE KEYS */;
INSERT INTO `personal_profile` (`id`, `birth_year`, `first_name`, `last_name`, `personal_review`, `picture`, `sex`, `location_id`, `member_since`, `picture_privacy`) VALUES
	(39, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-08-17 16:29:02', b'0'),
	(40, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-08-18 12:43:13', b'0');
/*!40000 ALTER TABLE `personal_profile` ENABLE KEYS */;

-- Дъмп структура за таблица wearedb.posts_table
DROP TABLE IF EXISTS `posts_table`;
CREATE TABLE IF NOT EXISTS `posts_table` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) DEFAULT NULL,
  `date_time` varchar(255) DEFAULT NULL,
  `is_public` bit(1) DEFAULT NULL,
  `picture` longtext DEFAULT NULL,
  `rank` double NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `FKbsjv9jobx8p2xsrr3ath7s1h2` (`user_id`),
  CONSTRAINT `FKbsjv9jobx8p2xsrr3ath7s1h2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb3;

-- Дъмп данни за таблица wearedb.posts_table: ~0 rows (приблизително)
/*!40000 ALTER TABLE `posts_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `posts_table` ENABLE KEYS */;

-- Дъмп структура за таблица wearedb.post_likes_table
DROP TABLE IF EXISTS `post_likes_table`;
CREATE TABLE IF NOT EXISTS `post_likes_table` (
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`post_id`,`user_id`),
  KEY `FKffht3vry8tn1gn6j3lspni5g7` (`user_id`),
  CONSTRAINT `FK5xqdwksmwu3pul7gu22ws815h` FOREIGN KEY (`post_id`) REFERENCES `posts_table` (`post_id`),
  CONSTRAINT `FKffht3vry8tn1gn6j3lspni5g7` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Дъмп данни за таблица wearedb.post_likes_table: ~0 rows (приблизително)
/*!40000 ALTER TABLE `post_likes_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `post_likes_table` ENABLE KEYS */;

-- Дъмп структура за таблица wearedb.requests
DROP TABLE IF EXISTS `requests`;
CREATE TABLE IF NOT EXISTS `requests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `approved` bit(1) NOT NULL,
  `seen` bit(1) NOT NULL,
  `time_stamp` datetime DEFAULT NULL,
  `receiver_user_id` int(11) DEFAULT NULL,
  `sender_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKal1njfrgngnhtqdnlcrxkigun` (`receiver_user_id`),
  KEY `FK1o3mc2ayitw4twloiclwsjioc` (`sender_user_id`),
  CONSTRAINT `FK1o3mc2ayitw4twloiclwsjioc` FOREIGN KEY (`sender_user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKal1njfrgngnhtqdnlcrxkigun` FOREIGN KEY (`receiver_user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3;

-- Дъмп данни за таблица wearedb.requests: ~0 rows (приблизително)
/*!40000 ALTER TABLE `requests` DISABLE KEYS */;
/*!40000 ALTER TABLE `requests` ENABLE KEYS */;

-- Дъмп структура за таблица wearedb.resource
DROP TABLE IF EXISTS `resource`;
CREATE TABLE IF NOT EXISTS `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Дъмп данни за таблица wearedb.resource: ~0 rows (приблизително)
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;

-- Дъмп структура за таблица wearedb.skills
DROP TABLE IF EXISTS `skills`;
CREATE TABLE IF NOT EXISTS `skills` (
  `skill_id` int(11) NOT NULL AUTO_INCREMENT,
  `skill` varchar(255) NOT NULL,
  `category_skill_category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`skill_id`),
  UNIQUE KEY `UK_k4a75ts3x4rxj5kkwq2ylmyvf` (`skill`),
  KEY `FKq391o4yyriv7jsuqh0n3opg2h` (`category_skill_category_id`),
  CONSTRAINT `FKq391o4yyriv7jsuqh0n3opg2h` FOREIGN KEY (`category_skill_category_id`) REFERENCES `category_skills` (`skill_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb3;

-- Дъмп данни за таблица wearedb.skills: ~0 rows (приблизително)
/*!40000 ALTER TABLE `skills` DISABLE KEYS */;
/*!40000 ALTER TABLE `skills` ENABLE KEYS */;

-- Дъмп структура за таблица wearedb.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `enabled` int(11) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(30) NOT NULL,
  `expertise_profile_id` int(11) DEFAULT NULL,
  `personal_profile_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `FKsu3q0o93bnxn4bmx05ijwhyrj` (`expertise_profile_id`),
  KEY `FKn7it8of9eaee7x9daigoq30xd` (`personal_profile_id`),
  CONSTRAINT `FKn7it8of9eaee7x9daigoq30xd` FOREIGN KEY (`personal_profile_id`) REFERENCES `personal_profile` (`id`),
  CONSTRAINT `FKsu3q0o93bnxn4bmx05ijwhyrj` FOREIGN KEY (`expertise_profile_id`) REFERENCES `expertise_profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb3;

-- Дъмп данни за таблица wearedb.users: ~1 rows (приблизително)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`user_id`, `email`, `enabled`, `password`, `username`, `expertise_profile_id`, `personal_profile_id`) VALUES
	(40, 'admin@gmail.com', 1, '$2a$10$y0Rz5kz/muJ0B7zTBU8Z1.wA7am2QiaDbrLe4EFbjIbJI0u/3NDDq', 'admin', 40, 40);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
