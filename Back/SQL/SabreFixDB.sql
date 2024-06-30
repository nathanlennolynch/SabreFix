-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.5.10-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for myweapons
CREATE DATABASE IF NOT EXISTS `myweapons` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `myweapons`;

-- Dumping structure for table myweapons.repair_tickets
CREATE TABLE IF NOT EXISTS `repair_tickets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `level` int(11) NOT NULL DEFAULT 0,
  `typeId` varchar(50) NOT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `ticket_description` varchar(200) DEFAULT NULL,
  `updateDateTime` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `CreateDateTime` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

-- Dumping data for table myweapons.repair_tickets: ~4 rows (approximately)
/*!40000 ALTER TABLE `repair_tickets` DISABLE KEYS */;
REPLACE INTO `repair_tickets` (`id`, `name`, `level`, `typeId`, `phone`, `email`, `ticket_description`, `updateDateTime`, `CreateDateTime`) VALUES
	(31, 'Radioactive Mace', 80, 'MACE', '15010000000', 'test2@email.com', 'This  mace is full of power, but I feel sick when I use it for some reason!', '2024-06-24 15:58:03', '2024-06-24 15:58:03'),
	(32, 'Bent Sword', 1, 'SWORD', '5011234567', 'test3@email.com', 'It bent when I did a backflip! ;(', '2024-06-24 15:59:56', '2024-06-24 15:59:56'),
	(33, 'Broken Bow', 35, 'STAFF', '5015676969', 'mymail@email.com', 'My bow broke after hunting goblins. I know you guys don\'t fix bows but could you PLEASE make an exception? There\'s no fletchers near by!!', '2024-06-24 22:56:52', '2024-06-24 22:56:52'),
	(34, 'Unbalanced Mace', 35, 'MACE', '1501444456', 'test@email.com', 'It\'s heavier on the stick...', '2024-06-25 16:15:28', '2024-06-25 16:15:28'),
	(35, 'Broken Mace', 1, 'MACE', '5011111111', 'test2@email.com', 'It broke', '2024-06-26 14:09:42', '2024-06-26 14:09:42');
/*!40000 ALTER TABLE `repair_tickets` ENABLE KEYS */;

-- Dumping structure for table myweapons.type
CREATE TABLE IF NOT EXISTS `type` (
  `typeId` varchar(20) NOT NULL,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table myweapons.type: ~4 rows (approximately)
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
REPLACE INTO `type` (`typeId`, `description`) VALUES
	('AXE', 'Weapon pf type: axe'),
	('MACE', 'Weapon of type: mace'),
	('STAFF', 'Weapon of type: staff'),
	('SWORD', 'Weapon of type: sword');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;

-- Dumping structure for table myweapons.weapons
CREATE TABLE IF NOT EXISTS `weapons` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `level` int(11) NOT NULL,
  `typeId` varchar(20) NOT NULL,
  `updateDateTime` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `createDateTime` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `typeId_fk` (`typeId`),
  CONSTRAINT `typeId_fk` FOREIGN KEY (`typeId`) REFERENCES `type` (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;

-- Dumping data for table myweapons.weapons: ~19 rows (approximately)
/*!40000 ALTER TABLE `weapons` DISABLE KEYS */;
REPLACE INTO `weapons` (`id`, `name`, `level`, `typeId`, `updateDateTime`, `createDateTime`) VALUES
	(17, 'Starshard Staff', 48, 'STAFF', '2024-06-09 02:57:23', '2024-06-09 02:57:23'),
	(18, 'Ebonwood Wand', 12, 'STAFF', '2024-06-09 02:57:23', '2024-06-09 02:57:23'),
	(19, 'Celestial Scepter', 60, 'STAFF', '2024-06-09 02:57:23', '2024-06-09 02:57:23'),
	(23, 'Soulreaper', 48, 'SWORD', '2024-06-09 03:05:08', '2024-06-09 03:05:08'),
	(24, 'Thunderstrike', 38, 'AXE', '2024-06-09 03:05:08', '2024-06-09 03:05:08'),
	(25, 'Duskblade', 52, 'SWORD', '2024-06-09 03:05:08', '2024-06-09 03:05:08'),
	(27, 'Flameheart', 42, 'SWORD', '2024-06-09 03:05:08', '2024-06-09 03:05:08'),
	(28, 'The Ashbringer', 60, 'SWORD', '2024-06-09 04:30:47', '2024-06-09 04:30:47'),
	(29, 'Donkey\'s Jawbone', 13, 'MACE', '2024-06-09 04:36:50', '2024-06-09 04:36:50'),
	(35, 'Triangle Axe', 23, 'AXE', '2024-06-15 07:23:30', '2024-06-15 07:23:30'),
	(36, 'WhirlWind Axe', 40, 'AXE', '2024-06-15 07:28:31', '2024-06-15 07:28:31'),
	(37, 'Staff of the Eagle', 10, 'STAFF', '2024-06-15 07:30:27', '2024-06-15 07:30:27'),
	(38, 'Wizard\'s Rod of Power', 32, 'STAFF', '2024-06-15 07:31:48', '2024-06-15 07:31:48'),
	(40, 'Steel Sword', 20, 'SWORD', '2024-06-25 16:09:34', '2024-06-17 09:55:00'),
	(41, 'Broke Iron Sword', 5, 'SWORD', '2024-06-17 09:56:01', '2024-06-17 09:56:01'),
	(44, 'Iron sword', 1, 'SWORD', '2024-06-23 19:33:48', '2024-06-23 19:33:48'),
	(45, 'Light Pole', 70, 'STAFF', '2024-06-25 16:16:41', '2024-06-25 16:16:41'),
	(46, 'Bastard Sword', 40, 'SWORD', '2024-06-26 13:12:56', '2024-06-26 13:12:56');
/*!40000 ALTER TABLE `weapons` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
