-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 27, 2020 at 12:27 PM
-- Server version: 5.7.24-log
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `amazon_sync`
--

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `AmazonOrderId` varchar(50) NOT NULL,
  `BuyerName` varchar(50) NOT NULL,
  `BuyerEmail` varchar(50) NOT NULL,
  `NumberOfItemsShipped` varchar(50) NOT NULL,
  `OrderAmount` varchar(50) NOT NULL,
  `OrderType` varchar(50) NOT NULL,
  `OrderStatus` varchar(50) NOT NULL,
  `PaymentMethod` varchar(50) NOT NULL,
  `ShippingAddress_Name` varchar(50) NOT NULL,
  `ShippingAddress_CountryCode` varchar(50) NOT NULL,
  `ShippingAddress_StateOrRegion` varchar(50) NOT NULL,
  `ShippingAddress_PostalCode` varchar(50) NOT NULL,
  `ShippingAddress_AddressType` varchar(50) NOT NULL,
  `ShippingAddress_City` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `task_details`
--

DROP TABLE IF EXISTS `task_details`;
CREATE TABLE IF NOT EXISTS `task_details` (
  `task_details_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(255) DEFAULT NULL,
  `task_polling_time` int(20) DEFAULT NULL,
  `task_file` varchar(255) DEFAULT NULL,
  `task_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`task_details_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task_details`
--

INSERT INTO `task_details` (`task_details_id`, `task_name`, `task_polling_time`, `task_file`, `task_status`) VALUES
(1, 'PRODUCT_IMPORT', 600, 'com.example.amazonsync.SyncData.ProductSync', 'Running'),
(2, 'ORDER_IMPORT', 600, 'com.example.amazonsync.SyncData.OrderSync', 'Running');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
