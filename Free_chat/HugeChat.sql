/*
SQLyog Trial v10.2 
MySQL - 5.7.17 : Database - hugechat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hugechat` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `hugechat`;

/*Table structure for table `manyrelation` */

DROP TABLE IF EXISTS `manyrelation`;

CREATE TABLE `manyrelation` (
  `QunID` int(11) NOT NULL AUTO_INCREMENT,
  `User1` int(11) DEFAULT NULL,
  `User2` int(11) DEFAULT NULL,
  `User3` int(11) DEFAULT NULL,
  `User4` int(11) DEFAULT NULL,
  `User5` int(11) DEFAULT NULL,
  `QunName` text NOT NULL,
  PRIMARY KEY (`QunID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `manyrelation` */

insert  into `manyrelation`(`QunID`,`User1`,`User2`,`User3`,`User4`,`User5`,`QunName`) values (5,9,10,11,12,13,'606'),(6,1,2,3,4,5,'606'),(7,2,4,6,8,10,'Fight'),(8,4,6,7,8,9,'Famliy'),(9,1,4,6,7,10,'Friends');

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `MessageId` int(11) NOT NULL AUTO_INCREMENT,
  `time` varchar(19) DEFAULT NULL,
  `sender` int(11) DEFAULT NULL,
  `receiver` int(11) DEFAULT NULL,
  `content` text,
  `Qunname` varchar(15) DEFAULT NULL,
  `MessageType` int(11) DEFAULT NULL,
  PRIMARY KEY (`MessageId`)
) ENGINE=InnoDB AUTO_INCREMENT=238 DEFAULT CHARSET=latin1;

/*Data for the table `message` */

insert  into `message`(`MessageId`,`time`,`sender`,`receiver`,`content`,`Qunname`,`MessageType`) values (227,'2017-09-13 22:09:55',1,3,'hi i am SX',NULL,3),(228,'2017-09-13 22:09:11',3,1,'hi i am JJR',NULL,3),(229,'2017-09-13 22:09:22',1,3,'nice to see you ',NULL,3),(230,'2017-09-13 22:09:32',3,1,'nice to see you too',NULL,3),(231,'2017-09-13 22:09:49',2,1,'hi i am XHC',NULL,3),(232,'2017-09-13 22:09:59',1,2,'HI i am SHUXIN',NULL,3),(233,'2017-09-13 22:09:08',2,1,'nice to see you ',NULL,3),(234,'2017-09-13 22:09:17',1,2,'nice to see you too',NULL,3),(235,'2017-09-13 22:09:48',1,NULL,'hi i am SX 606','606',4),(236,'2017-09-13 22:09:02',2,NULL,' hi  i am XHC 606','606',4),(237,'2017-09-13 22:09:21',3,NULL,'HI i am JJR  606','606',4);

/*Table structure for table `relation` */

DROP TABLE IF EXISTS `relation`;

CREATE TABLE `relation` (
  `User1` int(11) DEFAULT NULL,
  `User2` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `relation` */

insert  into `relation`(`User1`,`User2`) values (1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(2,3),(2,4),(2,5),(2,7),(2,9),(3,5),(3,6),(3,8),(3,9),(3,10),(4,8),(4,9),(4,10),(5,6),(5,7),(5,9),(5,10),(6,9),(6,8),(7,8),(7,10),(8,9),(9,10);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) NOT NULL,
  `UserPsw` varchar(50) NOT NULL,
  `UserIamge` int(11) NOT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`UserId`,`UserName`,`UserPsw`,`UserIamge`) values (8,'LQ','123456',1),(7,'WJH','123456',1),(6,'CYD','123456',1),(5,'ZZY','123456',1),(4,'ZZM','123456',1),(3,'JJR','123456',1),(2,'XHC','123456',1),(1,'SX','123456',1),(9,'WCM','123456',1),(10,'WZF','123456',1),(26,'QQQ','123456',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
