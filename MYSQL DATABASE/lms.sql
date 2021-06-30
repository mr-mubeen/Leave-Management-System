-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 31, 2021 at 04:58 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lms`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `a_id` int(11) NOT NULL,
  `a_user` varchar(200) NOT NULL,
  `a_pass` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`a_id`, `a_user`, `a_pass`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `approve_leave`
--

CREATE TABLE `approve_leave` (
  `app_id` int(11) NOT NULL,
  `app_e_id` int(11) NOT NULL,
  `l_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `e_id` int(10) NOT NULL,
  `emp_name` varchar(200) NOT NULL,
  `emp_password` varchar(255) NOT NULL,
  `emp_joining_date` date NOT NULL DEFAULT current_timestamp(),
  `m_id` int(11) DEFAULT NULL,
  `emp_pic` varchar(1500) NOT NULL,
  `SL` int(11) NOT NULL DEFAULT 10,
  `PL` int(11) NOT NULL DEFAULT 10,
  `CL` int(11) NOT NULL DEFAULT 10
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`e_id`, `emp_name`, `emp_password`, `emp_joining_date`, `m_id`, `emp_pic`, `SL`, `PL`, `CL`) VALUES
(2, 'Ahmed', 'ahmed', '2021-05-23', 1, '', 10, 10, 5);

-- --------------------------------------------------------

--
-- Table structure for table `leaves`
--

CREATE TABLE `leaves` (
  `L_id` int(11) NOT NULL,
  `L_e_id` int(11) NOT NULL,
  `L_m_id` int(11) NOT NULL,
  `note` text NOT NULL,
  `L_s_date` int(11) NOT NULL,
  `type` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL DEFAULT 'Pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `leaves`
--

INSERT INTO `leaves` (`L_id`, `L_e_id`, `L_m_id`, `note`, `L_s_date`, `type`, `status`) VALUES
(1, 2, 1, 'dasd', 0, 'SL', ''),
(2, 2, 1, 'dasd', 20210513, 'SL', 'Pending'),
(3, 2, 1, 'dasd', 20210513, 'SL', 'Pending'),
(4, 2, 1, 'dasd', 20210513, 'PL', 'Pending'),
(5, 2, 1, 'fgdfg', 20200505, 'PL', 'Pending'),
(6, 2, 1, 'dd in', 20200515, 'SL', 'Pending'),
(7, 2, 1, 'fdsdffsd', 20200520, 'SL', 'Pending'),
(8, 2, 1, 'dcasxc', 20200520, 'SL', 'Pending'),
(9, 2, 1, 'sdfgdfge', 0, 'SL', 'Pending'),
(10, 2, 1, '423RASD', 0, 'SL', 'Pending'),
(11, 2, 1, 'DFGDFG', 0, 'SL', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `manager`
--

CREATE TABLE `manager` (
  `m_id` int(100) NOT NULL,
  `m_name` varchar(200) DEFAULT NULL,
  `m_pass` varchar(255) DEFAULT NULL,
  `m_joining_date` date DEFAULT NULL,
  `m_pic` varchar(1500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `manager`
--

INSERT INTO `manager` (`m_id`, `m_name`, `m_pass`, `m_joining_date`, `m_pic`) VALUES
(1, 'Usman', 'hello', '0000-00-00', NULL),
(2, NULL, NULL, '0000-00-00', NULL),
(3, 'ali', 'asd', NULL, NULL),
(4, 'dahad', '123', '0000-00-00', NULL),
(5, '42', '234', '0000-00-00', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`a_id`);

--
-- Indexes for table `approve_leave`
--
ALTER TABLE `approve_leave`
  ADD PRIMARY KEY (`app_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`e_id`),
  ADD KEY `m_id` (`m_id`);

--
-- Indexes for table `leaves`
--
ALTER TABLE `leaves`
  ADD PRIMARY KEY (`L_id`);

--
-- Indexes for table `manager`
--
ALTER TABLE `manager`
  ADD PRIMARY KEY (`m_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `a_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `approve_leave`
--
ALTER TABLE `approve_leave`
  MODIFY `app_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `e_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `leaves`
--
ALTER TABLE `leaves`
  MODIFY `L_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `manager`
--
ALTER TABLE `manager`
  MODIFY `m_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`m_id`) REFERENCES `manager` (`m_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
