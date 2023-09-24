--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
  `username` varchar(30) NOT NULL,
  `authority` varchar(255) NOT NULL,
  PRIMARY KEY (`username`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;

CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nrgoi6sdvipfsloa7ykxwlslf` (`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Table structure for table `category_skills`
--

DROP TABLE IF EXISTS `category_skills`;

CREATE TABLE `category_skills` (
  `skill_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`skill_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8;


--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;

CREATE TABLE `countries` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;

CREATE TABLE `cities` (
  `city_id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`city_id`),
  KEY `FK6gatmv9dwedve82icy8wrkdmk` (`country_id`),
  CONSTRAINT `FK6gatmv9dwedve82icy8wrkdmk` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;

CREATE TABLE `locations` (
  `location_id` int(11) NOT NULL AUTO_INCREMENT,
  `city_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`location_id`),
  KEY `FKgvg10vlf7gqvbgf9rbbho3gyg` (`city_id`),
  CONSTRAINT `FKgvg10vlf7gqvbgf9rbbho3gyg` FOREIGN KEY (`city_id`) REFERENCES `cities` (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

--
-- Table structure for table `personal_profile`
--

DROP TABLE IF EXISTS `personal_profile`;

CREATE TABLE `personal_profile` (
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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;

CREATE TABLE `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;

CREATE TABLE `skills` (
  `skill_id` int(11) NOT NULL AUTO_INCREMENT,
  `skill` varchar(255) NOT NULL,
  `category_skill_category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`skill_id`),
  UNIQUE KEY `UK_k4a75ts3x4rxj5kkwq2ylmyvf` (`skill`),
  KEY `FKq391o4yyriv7jsuqh0n3opg2h` (`category_skill_category_id`),
  CONSTRAINT `FKq391o4yyriv7jsuqh0n3opg2h` FOREIGN KEY (`category_skill_category_id`) REFERENCES `category_skills` (`skill_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

--
-- Table structure for table `expertise_profile`
--

DROP TABLE IF EXISTS `expertise_profile`;

CREATE TABLE `expertise_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `category_skill_category_id` int(11) DEFAULT NULL,
  `availability` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3mu6f7gnwye8amp57mf7cpp5a` (`category_skill_category_id`),
  CONSTRAINT `FK3mu6f7gnwye8amp57mf7cpp5a` FOREIGN KEY (`category_skill_category_id`) REFERENCES `category_skills` (`skill_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;



--
-- Table structure for table `expertise_profile_skills`
--

DROP TABLE IF EXISTS `expertise_profile_skills`;

CREATE TABLE `expertise_profile_skills` (
  `expertise_profile_id` int(11) NOT NULL,
  `skills_skill_id` int(11) NOT NULL,
  UNIQUE KEY `UK_nly4sf6igu5dbdk82rblace7l` (`skills_skill_id`),
  KEY `FKner4qgbla4je9nbwqn28naspf` (`expertise_profile_id`),
  CONSTRAINT `FKner4qgbla4je9nbwqn28naspf` FOREIGN KEY (`expertise_profile_id`) REFERENCES `expertise_profile` (`id`),
  CONSTRAINT `FKnsclrcj944iirspw8fkc5yp7l` FOREIGN KEY (`skills_skill_id`) REFERENCES `skills` (`skill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
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
  CONSTRAINT `FKsu3q0o93bnxn4bmx05ijwhyrj` FOREIGN KEY (`expertise_profile_id`) REFERENCES `expertise_profile` (`id`),
  KEY `FKn7it8of9eaee7x9daigoq30xd` (`personal_profile_id`),
  CONSTRAINT `FKn7it8of9eaee7x9daigoq30xd` FOREIGN KEY (`personal_profile_id`) REFERENCES `personal_profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

--
-- Table structure for table `requests`
--

DROP TABLE IF EXISTS `requests`;

CREATE TABLE `requests` (
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

--
-- Table structure for table `posts_table`
--

DROP TABLE IF EXISTS `posts_table`;

CREATE TABLE `posts_table` (
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
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

--
-- Table structure for table `post_likes_table`
--

DROP TABLE IF EXISTS `post_likes_table`;

CREATE TABLE `post_likes_table` (
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`post_id`,`user_id`),
  KEY `FKffht3vry8tn1gn6j3lspni5g7` (`user_id`),
  CONSTRAINT `FK5xqdwksmwu3pul7gu22ws815h` FOREIGN KEY (`post_id`) REFERENCES `posts_table` (`post_id`),
  CONSTRAINT `FKffht3vry8tn1gn6j3lspni5g7` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `feed_back`
--

DROP TABLE IF EXISTS `feed_back`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feed_back` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `connections_table`
--

DROP TABLE IF EXISTS `connections_table`;

CREATE TABLE `connections_table` (
  `connection_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_accepter_id` int(11) DEFAULT NULL,
  `user_requester_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`connection_id`),
  KEY `FKoqnpe7indmmieldy8tddycsy1` (`user_requester_id`),
  CONSTRAINT `FKoqnpe7indmmieldy8tddycsy1` FOREIGN KEY (`user_requester_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `connection_users`
--

DROP TABLE IF EXISTS `connection_users`;

CREATE TABLE `connection_users` (
  `user_a` int(11) NOT NULL,
  `user_b` int(11) NOT NULL,
  PRIMARY KEY (`user_a`,`user_b`),
  KEY `FKqme3o6ss546tc72kwd11w8r3q` (`user_b`),
  CONSTRAINT `FK3m37qk73ip7rr7ufehrmlp2ns` FOREIGN KEY (`user_a`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKqme3o6ss546tc72kwd11w8r3q` FOREIGN KEY (`user_b`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `comments_table`
--

DROP TABLE IF EXISTS `comments_table`;

CREATE TABLE `comments_table` (
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
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

--
-- Table structure for table `comment_likes_table`
--

DROP TABLE IF EXISTS `comment_likes_table`;

CREATE TABLE `comment_likes_table` (
  `comment_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`comment_id`,`user_id`),
  KEY `FKbt38yfvtantp2jvgf511j0wpj` (`user_id`),
  CONSTRAINT `FKbt38yfvtantp2jvgf511j0wpj` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKcoqgc1cu7jw29qqk4aaas38m` FOREIGN KEY (`comment_id`) REFERENCES `comments_table` (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
