-- MYSQL

CREATE DATABASE  IF NOT EXISTS `student_tracker`;
USE `student_tracker`;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name`varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- POSTGRESS

-- Create database
CREATE DATABASE student_tracker;

-- Connect to the database (in psql)
\c student_tracker;

-- Drop table if exists
DROP TABLE IF EXISTS student;

-- Create table
CREATE TABLE student (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(45),
  last_name VARCHAR(45),
  email VARCHAR(45)
);
