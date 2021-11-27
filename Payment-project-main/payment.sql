-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.3.13-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for payment
CREATE DATABASE IF NOT EXISTS `payment` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `payment`;

-- Dumping structure for table payment.bank
CREATE TABLE IF NOT EXISTS `bank` (
  `bic` char(11) NOT NULL,
  `bankname` varchar(100) NOT NULL,
  PRIMARY KEY (`bic`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table payment.bank: ~3 rows (approximately)
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT INTO `bank` (`bic`, `bankname`) VALUES
	('ABBLINBBXXX', 'AB BANK LIMITED'),
	('ABNAINBBAHM', 'ABN AMRO BANK N.V.'),
	('ACBLINBBXXX', 'ABHYUDAYA CO-OPERATIVE BANK LTD.');
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;

-- Dumping structure for table payment.currency
CREATE TABLE IF NOT EXISTS `currency` (
  `currencycode` char(3) NOT NULL,
  `currencyname` varchar(100) NOT NULL,
  `conversionrate` decimal(10,0) NOT NULL,
  PRIMARY KEY (`currencycode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table payment.currency: ~5 rows (approximately)
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` (`currencycode`, `currencyname`, `conversionrate`) VALUES
	('EUR', 'Euro', 84),
	('GBP', 'Great British Pound', 102),
	('INR', 'Indian Rupees', 1),
	('JPY', 'Japanese Yen', 1),
	('USD', 'US Dollar', 74);
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;

-- Dumping structure for table payment.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `customerid` char(14) NOT NULL,
  `accountholdername` varchar(50) NOT NULL,
  `overdraftflag` tinyint(1) NOT NULL DEFAULT 0,
  `clearbalance` decimal(10,0) NOT NULL,
  `customeraddress` varchar(100) NOT NULL,
  `customercity` varchar(100) NOT NULL,
  `customertype` char(1) NOT NULL,
  `bic` char(11) NOT NULL,
  PRIMARY KEY (`customerid`),
  UNIQUE KEY `accountholdername` (`accountholdername`),
  KEY `customr_ibfk-1` (`bic`),
  CONSTRAINT `customr_ibfk-1` FOREIGN KEY (`bic`) REFERENCES `bank` (`bic`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table payment.customer: ~3 rows (approximately)
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`customerid`, `accountholdername`, `overdraftflag`, `clearbalance`, `customeraddress`, `customercity`, `customertype`, `bic`) VALUES
	('69652133523248', 'HDFC BANK -(CHENNAI)', 1, 223997, 'CHENNAI', 'CHENNAI', 'B', 'ABBLINBBXXX'),
	('71319440983198', 'A M MAYANNA', 0, 219967, 'HYDERABAD', 'HYDERABAD', 'I', 'ABBLINBBXXX'),
	('83020817828620', 'A KRISHNA MOHAN', 1, 57500, 'KARIMNAGAR', 'KARIMNAGAR', 'I', 'ABBLINBBXXX');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- Dumping structure for table payment.customeruser
CREATE TABLE IF NOT EXISTS `customeruser` (
  `userid` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `customerid` char(14) NOT NULL,
  `userpassword` varchar(100) NOT NULL,
  `roles` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `username` (`username`),
  KEY `customerid` (`customerid`),
  CONSTRAINT `customeruser_ibfk_1` FOREIGN KEY (`customerid`) REFERENCES `customer` (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table payment.customeruser: ~1 rows (approximately)
/*!40000 ALTER TABLE `customeruser` DISABLE KEYS */;
INSERT INTO `customeruser` (`userid`, `username`, `customerid`, `userpassword`, `roles`) VALUES
	(1, 'Shalini Mittal', '71319440983198', 'shalini', 'ROLE_USER');
/*!40000 ALTER TABLE `customeruser` ENABLE KEYS */;

-- Dumping structure for table payment.employee
CREATE TABLE IF NOT EXISTS `employee` (
  `employeeid` int(11) NOT NULL,
  `employeename` varchar(100) NOT NULL,
  `employeepassword` varchar(100) NOT NULL,
  PRIMARY KEY (`employeeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table payment.employee: ~0 rows (approximately)
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;

-- Dumping structure for table payment.logger
CREATE TABLE IF NOT EXISTS `logger` (
  `loggerid` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` char(14) NOT NULL,
  `userid` int(11) DEFAULT NULL,
  `employeeid` int(11) DEFAULT NULL,
  `screename` varchar(100) DEFAULT NULL,
  `action` varchar(100) DEFAULT NULL,
  `ipaddress` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`loggerid`),
  KEY `employeeid` (`employeeid`),
  KEY `userid` (`userid`),
  KEY `logger_ibfk_1` (`customerid`),
  CONSTRAINT `logger_ibfk_1` FOREIGN KEY (`customerid`) REFERENCES `customer` (`customerid`),
  CONSTRAINT `logger_ibfk_2` FOREIGN KEY (`employeeid`) REFERENCES `employee` (`employeeid`),
  CONSTRAINT `logger_ibfk_3` FOREIGN KEY (`userid`) REFERENCES `customeruser` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table payment.logger: ~1 rows (approximately)
/*!40000 ALTER TABLE `logger` DISABLE KEYS */;
INSERT INTO `logger` (`loggerid`, `customerid`, `userid`, `employeeid`, `screename`, `action`, `ipaddress`) VALUES
	(1, '71319440983198', NULL, NULL, 'Initiate Payment', 'Payment Transfer', '192.168.1.1');
/*!40000 ALTER TABLE `logger` ENABLE KEYS */;

-- Dumping structure for table payment.message
CREATE TABLE IF NOT EXISTS `message` (
  `messagecode` char(4) NOT NULL,
  `instruction` varchar(200) NOT NULL,
  PRIMARY KEY (`messagecode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table payment.message: ~9 rows (approximately)
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` (`messagecode`, `instruction`) VALUES
	('CHQB', 'beneficiary customer must be paid by cheque only.'),
	('CORT', 'Payment is made in settlement for a trade.'),
	('HOLD', 'Beneficiary customer or claimant will call upon identification.'),
	('INTC', 'Payment between two companies that belongs to the same group.'),
	('PHOB', 'Please advise the intermediary institution by phone.'),
	('PHOI', 'Please advise the intermediary by phone.'),
	('PHON', 'Please advise the account with institution by phone.'),
	('REPA', 'Payments has a related e-Payments reference.'),
	('SDVA', 'Payment must be executed with same day value to the');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;

-- Dumping structure for table payment.transaction
CREATE TABLE IF NOT EXISTS `transaction` (
  `transactionid` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` char(14) NOT NULL,
  `senderBIC` char(11) NOT NULL,
  `receiverBIC` char(11) DEFAULT NULL,
  `receiveraccountholdernumber` char(14) NOT NULL,
  `receiveraccountholdername` varchar(100) NOT NULL,
  `transfertypecode` char(1) NOT NULL,
  `messagecode` char(4) NOT NULL,
  `currencyamount` decimal(10,0) NOT NULL DEFAULT 0,
  `transferfees` decimal(10,0) NOT NULL,
  `transferdate` date NOT NULL,
  PRIMARY KEY (`transactionid`),
  KEY `messagecode` (`messagecode`),
  KEY `receiverBIC` (`receiverBIC`),
  KEY `senderBIC` (`senderBIC`),
  KEY `transfertypecode` (`transfertypecode`),
  KEY `transaction_ibfk_2` (`customerid`),
  KEY `transaction_ibfk_7` (`receiveraccountholdernumber`),
  CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`customerid`) REFERENCES `customer` (`customerid`),
  CONSTRAINT `transaction_ibfk_3` FOREIGN KEY (`messagecode`) REFERENCES `message` (`messagecode`),
  CONSTRAINT `transaction_ibfk_4` FOREIGN KEY (`receiverBIC`) REFERENCES `bank` (`bic`),
  CONSTRAINT `transaction_ibfk_5` FOREIGN KEY (`senderBIC`) REFERENCES `bank` (`bic`),
  CONSTRAINT `transaction_ibfk_6` FOREIGN KEY (`transfertypecode`) REFERENCES `transfertypes` (`transfertypecode`),
  CONSTRAINT `transaction_ibfk_7` FOREIGN KEY (`receiveraccountholdernumber`) REFERENCES `customer` (`customerid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Dumping data for table payment.transaction: ~2 rows (approximately)
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` (`transactionid`, `customerid`, `senderBIC`, `receiverBIC`, `receiveraccountholdernumber`, `receiveraccountholdername`, `transfertypecode`, `messagecode`, `currencyamount`, `transferfees`, `transferdate`) VALUES
	(6, '71319440983198', 'ABBLINBBXXX', 'ABBLINBBXXX', '83020817828620', 'A KRISHNA MOHAN', 'C', 'REPA', 1000, 3, '2021-11-27'),
	(7, '71319440983198', 'ABBLINBBXXX', 'ABBLINBBXXX', '83020817828620', 'A KRISHNA MOHAN', 'C', 'CORT', 500, 1, '2021-11-27');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;

-- Dumping structure for table payment.transfertypes
CREATE TABLE IF NOT EXISTS `transfertypes` (
  `transfertypecode` char(1) NOT NULL,
  `transfertypedescription` varchar(100) NOT NULL,
  PRIMARY KEY (`transfertypecode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table payment.transfertypes: ~3 rows (approximately)
/*!40000 ALTER TABLE `transfertypes` DISABLE KEYS */;
INSERT INTO `transfertypes` (`transfertypecode`, `transfertypedescription`) VALUES
	('B', 'Bank Transfer'),
	('C', 'Customer Transfer'),
	('O', 'Bank Transfer for Own Account');
/*!40000 ALTER TABLE `transfertypes` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
