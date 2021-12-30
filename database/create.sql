CREATE DATABASE `TheJavaPlace` DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `Sub` (
  `Id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `Thread_Id` bigint unsigned DEFAULT NULL,
  `Name` varchar(60) NOT NULL,
  `Url` varchar(20) NOT NULL,
  `Total_Threads` bigint unsigned NOT NULL,
  `Total_Comments` bigint unsigned NOT NULL,
  `Date_Created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UNIQUE_SUB_URL` (`Url`),
  UNIQUE KEY `UNIQUE_SUB_NAME` (`Name`),
  KEY `Thread_Sub_Thread` (`Thread_Id`),
  CONSTRAINT `Thread_Sub_Thread` FOREIGN KEY (`Thread_Id`) REFERENCES `Thread` (`Id`)
) ENGINE=InnoDB;

CREATE TABLE `Thread` (
  `Id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `User_Id` bigint unsigned NOT NULL,
  `Sub_Url` varchar(20) NOT NULL,
  `Title` varchar(50) NOT NULL,
  `Body` text NOT NULL,
  `Comment_Count` int unsigned NOT NULL DEFAULT '0',
  `Date_Created` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  KEY `Sub_Thread_Sub` (`Sub_Url`),
  KEY `User_Thread_User` (`User_Id`),
  FULLTEXT KEY `Thread_Search` (`Title`,`Body`),
  CONSTRAINT `Sub_Thread_Sub` FOREIGN KEY (`Sub_Url`) REFERENCES `Sub` (`Url`),
  CONSTRAINT `User_Thread_User` FOREIGN KEY (`User_Id`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB;

CREATE TABLE `Thread_Comment` (
  `Id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `Thread_Id` bigint unsigned NOT NULL,
  `User_Id` bigint unsigned NOT NULL,
  `Body` text NOT NULL,
  `Date_Created` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  KEY `Thread_Comment_Thread` (`Thread_Id`),
  KEY `User_Comment_User` (`User_Id`),
  FULLTEXT KEY `Thread_Comment_Search` (`Body`),
  CONSTRAINT `Thread_Comment_Thread` FOREIGN KEY (`Thread_Id`) REFERENCES `Thread` (`Id`) ON DELETE CASCADE,
  CONSTRAINT `User_Comment_User` FOREIGN KEY (`User_Id`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB;

CREATE TABLE `User` (
  `Id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NOT NULL,
  `Hashed_Password` binary(60) NOT NULL,
  `Email` varchar(60) NOT NULL,
  `Recovery_Key` binary(50) DEFAULT NULL,
  `Recovery_Key_Timestamp` timestamp NULL DEFAULT NULL,
  `Profile_Image` mediumblob,
  `Role` varchar(30) DEFAULT 'User',
  `Date_Created` datetime DEFAULT CURRENT_TIMESTAMP,
  `Last_Active` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Account_Non_Expired` tinyint(1) NOT NULL DEFAULT '1',
  `Account_Non_Locked` tinyint(1) NOT NULL DEFAULT '1',
  `Credentials_Non_Expired` tinyint(1) NOT NULL DEFAULT '1',
  `Enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Username_Unique` (`Username`),
  UNIQUE KEY `Email_Unique` (`Email`)
) ENGINE=InnoDB;

CREATE TABLE `User_Authority` (
  `User_Id` bigint unsigned NOT NULL,
  `Authority` varchar(100) NOT NULL,
  UNIQUE KEY `USER_AUTHORITY_UNIQUE` (`User_Id`,`Authority`),
  CONSTRAINT `User_Authority_User` FOREIGN KEY (`User_Id`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB;