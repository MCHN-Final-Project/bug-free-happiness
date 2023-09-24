-- Depending on your setup local/remote or one/many databases you may or may not need to select a db:
-- use my-remote-db-example;

-- After the regstration you can promote the first user 'exampleUsername' to admin:
-- INSERT INTO `authorities` (`username`, `authority`) VALUES ('exampleUsername', 'ROLE_ADMIN');


INSERT INTO `authority` VALUES (2,'ROLE_ADMIN'),(1,'ROLE_USER');

INSERT INTO `category_skills` VALUES (100,'All'),(101,'Accountant'),(102,'Actor'),(103,'Archaeologist'),(104,'Astronaut'),(105,'Author'),(106,'Baker'),(107,'Bus driver'),(108,'Car service'),(109,'Chef/Cook'),(110,'Cleaner'),(111,'Dentist'),(112,'Designer'),(113,'Doctor'),(114,'Electrician'),(115,'Factory worker'),(116,'Farmer'),(117,'Firefighter'),(118,'Fisherman'),(119,'Gardener'),(120,'Hairdresser'),(121,'Journalist'),(122,'Judge'),(123,'Lawyer'),(124,'Lecturer'),(125,'Lifeguard'),(126,'Mechanic'),(127,'Model'),(128,'Musician'),(129,'Nanny'),(130,'Newsreader'),(131,'Nurses'),(132,'Optician'),(133,'Painter'),(134,'Pharmacist'),(135,'Photographer'),(136,'Pilot'),(137,'Plumber'),(138,'Police Officer'),(139,'Policeman'),(140,'Politician'),(141,'Postman'),(142,'Real estate agent'),(143,'Receptionist'),(144,'Scientist'),(145,'Secretary'),(146,'Shop assistant'),(147,'Software developer'),(148,'Soldier'),(149,'Tailor'),(150,'Teachers'),(151,'Translator'),(152,'Travel agent'),(153,'Veterinarian'),(154,'Waiter/Waitress'),(155,'Window cleaner'),(156,'Writer'),(157,'Marketing');

INSERT INTO `countries` VALUES (1,'BULGARIA');

INSERT INTO `cities` VALUES (1,'Sofia',1),(2,'Plovdiv',1),(3,'Varna',1),(4,'Burgas',1),(5,'Ruse',1),(6,'Stara Zagora',1),(7,'Pleven',1),(8,'Sliven',1),(9,'Dobrich',1),(10,'Shumen',1),(11,'Pernik',1),(12,'Haskovo',1),(13,'Vratsa',1),(14,'Kyustendil',1),(15,'Montana',1),(16,'Lovech',1),(17,'Razgrad',1),(18,'Borino',1),(19,'Madan',1),(20,'Zlatograd',1),(21,'Pazardzhik',1),(22,'Smolyan',1),(23,'Blagoevgrad',1),(24,'Nedelino',1),(25,'Rudozem',1),(26,'Devin',1),(27,'Veliko Tarnovo',1),(28,'Vidin',1),(29,'Kirkovo',1),(30,'Krumovgrad',1),(31,'Dzhebel',1),(32,'Silistra',1),(33,'Sarnitsa',1),(34,'Shiroka Laka',1),(35,'Yambol',1),(36,'Dospat',1),(37,'Kardzhali',1),(38,'Gabrovo',1),(39,'Targovishte',1);
