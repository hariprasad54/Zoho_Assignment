-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 10, 2022 at 11:06 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `commentapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `email` varchar(30) NOT NULL,
  `comment` text NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`email`, `comment`, `date`) VALUES
('alex@felix.com', 'hello this is alex', '2022-02-10 11:00:48'),
('alex@felix.com', 'good evening', '2022-02-10 15:31:57'),
('alex@felix.com', 'hello everyone', '2022-02-10 15:32:37'),
('felix@felix.com', 'hello i am felix', '2022-02-10 15:33:35'),
('felix@felix.com', 'welcome everyone', '2022-02-10 15:33:54'),
('john@abc.com', 'hello i am john', '2022-02-10 15:34:25'),
('john@abc.com', 'hello everyone', '2022-02-10 15:34:43'),
('smith@abc.com', 'hello i am smith', '2022-02-10 15:35:07'),
('smith@abc.com', 'hello everyone good evening', '2022-02-10 15:35:31');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `email` varchar(50) NOT NULL,
  `password` varchar(130) NOT NULL,
  `secretkey` varchar(130) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`email`, `password`, `secretkey`) VALUES
('alex@felix.com', 'F0QQTDw6zpkQ', 'F0QQTDw6zpkQ'),
('felix@felix.com', 'F0QQTDw6zpkQ', 'F0QQTDw6zpkQ'),
('john@abc.com', 'F0QQTDw6zpkQ', 'F0QQTDw6zpkQ'),
('smith@abc.com', 'VRtKDGE=', 'VRtKDGE=');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`email`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
