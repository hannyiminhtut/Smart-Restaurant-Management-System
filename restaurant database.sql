-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 02, 2024 at 06:44 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `restaurant`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`id`, `name`, `password`) VALUES
(1, 'admin', '13579');

-- --------------------------------------------------------

--
-- Table structure for table `cashoutorders`
--

CREATE TABLE `cashoutorders` (
  `id` int(11) NOT NULL,
  `table_id` int(11) DEFAULT NULL,
  `dish_id` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cashoutorders`
--

INSERT INTO `cashoutorders` (`id`, `table_id`, `dish_id`, `price`, `count`, `created_at`) VALUES
(1, 1, 1, 7500, 2, '2024-07-28 13:37:13'),
(2, 1, 3, 4500, 2, '2024-07-28 13:37:13'),
(3, 3, 2, 5000, 2, '2024-07-28 13:37:13'),
(5, 2, 3, 4500, 2, '2024-07-28 13:37:13'),
(6, 1, 3, 4500, 3, '2024-07-28 13:37:13'),
(7, 3, 4, 25000, 1, '2024-07-28 13:37:13'),
(8, 3, 4, 25000, 1, '2024-07-28 13:37:13'),
(10, 5, 4, 25000, 2, '2024-07-28 13:37:13'),
(12, 1, 4, 25000, 2, '2024-08-02 14:47:39'),
(13, 3, 1, 10000, 2, '2024-08-03 14:32:19'),
(14, 5, 3, 4500, 2, '2024-08-06 06:09:57'),
(15, 13, 1, 10000, 3, '2024-08-08 15:05:43'),
(16, 3, 1, 10000, 2, '2024-08-18 09:57:49'),
(17, 1, 4, 25000, 1, '2024-08-18 12:21:58'),
(18, 1, 3, 4500, 2, '2024-08-27 05:46:09'),
(19, 3, 4, 25000, 1, '2024-08-27 13:18:14'),
(20, 4, 4, 5200, 1, '2024-08-30 17:51:01');

-- --------------------------------------------------------

--
-- Table structure for table `dishes`
--

CREATE TABLE `dishes` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dishes`
--

INSERT INTO `dishes` (`id`, `name`, `price`, `enable`) VALUES
(1, 'Mala Maocoi', 11000, 1),
(2, 'Mala Xia Gou', 9500, 1),
(3, 'Pounded Seafood', 5500, 1),
(4, 'Thai Soup', 5200, 1),
(5, 'Tom Yum Soup', 5200, 1),
(6, 'Thai Style Fried Noodle', 4800, 1),
(7, 'Fried Noodle', 4500, 1),
(8, 'Pounded Enoki Mushroom', 3800, 1),
(9, 'Kway Teow ', 3500, 1),
(10, 'Chicken Salad', 3500, 1);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `table_id` int(11) NOT NULL,
  `dish_id` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT 1,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Triggers `orders`
--
DELIMITER $$
CREATE TRIGGER `orders_trigger` AFTER DELETE ON `orders` FOR EACH ROW begin
insert into cashOutOrders(id,table_id,dish_id,price,count,created_at) values (old.id,old.table_id,old.dish_id,old.price,old.count,old.created_at);
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tables`
--

CREATE TABLE `tables` (
  `id` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `charge` int(11) NOT NULL,
  `chair` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tables`
--

INSERT INTO `tables` (`id`, `enable`, `charge`, `chair`) VALUES
(1, 1, 0, 0),
(2, 1, 0, 0),
(3, 1, 0, 0),
(4, 1, 0, 0),
(5, 1, 0, 0),
(6, 1, 0, 0),
(7, 1, 0, 0),
(8, 1, 0, 0),
(9, 1, 0, 0),
(10, 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `password`, `enable`, `role`) VALUES
(1, 'Han Nyi Min Htut', '2029', 1, 1),
(2, 'May Phyo Thu Kyaw', '123', 1, 1),
(3, 'Win Pa Pa Thaw', '123', 1, 1),
(4, 'Htet Myat Yadanar', '123', 1, 1),
(5, 'Swe ZIn Hnin', '123', 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cashoutorders`
--
ALTER TABLE `cashoutorders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dishes`
--
ALTER TABLE `dishes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tables`
--
ALTER TABLE `tables`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cashoutorders`
--
ALTER TABLE `cashoutorders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `dishes`
--
ALTER TABLE `dishes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `tables`
--
ALTER TABLE `tables`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
