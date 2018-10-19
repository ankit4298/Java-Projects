-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 21, 2018 at 10:17 AM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hr`
--

-- --------------------------------------------------------

--
-- Table structure for table `designation_sal`
--

CREATE TABLE `designation_sal` (
  `des` varchar(25) NOT NULL,
  `sal` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `designation_sal`
--

INSERT INTO `designation_sal` (`des`, `sal`) VALUES
('Programmer', '15000.00'),
('Software Analyst', '20000.00'),
('Software Architect', '11000.00'),
('Project Leader', '14500.00'),
('DBA', '14500.00'),
('Web Developer', '15500.00'),
('Data Scientist', '20000.00'),
('CA', '17500.00'),
('CEO', '30000.00');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `eid` varchar(20) NOT NULL,
  `ename` varchar(40) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phno` bigint(11) NOT NULL,
  `street` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `state` varchar(100) NOT NULL,
  `country` varchar(100) NOT NULL,
  `doj` date NOT NULL,
  `designation` varchar(15) NOT NULL,
  `level` varchar(3) NOT NULL,
  `salary` decimal(10,2) NOT NULL,
  `deptno` int(10) NOT NULL,
  `manager_id` varchar(20) DEFAULT NULL,
  `pid` varchar(20) DEFAULT NULL,
  `status_proj` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`eid`, `ename`, `email`, `phno`, `street`, `city`, `state`, `country`, `doj`, `designation`, `level`, `salary`, `deptno`, `manager_id`, `pid`, `status_proj`) VALUES
('H123', 'Ankit', 'ankit@gmail.com', 0000000000, 'panhvati', 'nashik', 'maharashtra', 'india', '2018-04-11', 'CEO', 'L1', '50000.00', 150, '0', '0', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `employee_login_details`
--

CREATE TABLE `employee_login_details` (
  `eid` varchar(20) NOT NULL,
  `pass` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `executive_login_details`
--

CREATE TABLE `executive_login_details` (
  `eid` varchar(20) NOT NULL,
  `pass` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `executive_login_details`
--

INSERT INTO `executive_login_details` (`eid`, `pass`) VALUES
('H123', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `latest_id`
--

CREATE TABLE `latest_id` (
  `employee_eid` int(20) NOT NULL,
  `manager_eid` int(20) NOT NULL,
  `executive_eid` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `latest_id`
--

INSERT INTO `latest_id` (`employee_eid`, `manager_eid`, `executive_eid`) VALUES
(1001, 111, 123);

-- --------------------------------------------------------

--
-- Table structure for table `leaves`
--

CREATE TABLE `leaves` (
  `eid` varchar(20) NOT NULL,
  `leaves_taken` int(11) NOT NULL,
  `paid_leaves_left` int(11) NOT NULL,
  `unpaid_leaves` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `leaves`
--

INSERT INTO `leaves` (`eid`, `leaves_taken`, `paid_leaves_left`, `unpaid_leaves`) VALUES
('H123', 0, 14, 0);

-- --------------------------------------------------------

--
-- Table structure for table `leaves_approved`
--

CREATE TABLE `leaves_approved` (
  `eid` varchar(20) NOT NULL,
  `no_of_leaves` int(5) NOT NULL,
  `month` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `leave_applications`
--

CREATE TABLE `leave_applications` (
  `eid` varchar(20) NOT NULL,
  `no_of_days` int(5) NOT NULL DEFAULT '0',
  `start_date` varchar(10) NOT NULL,
  `leave_sub` varchar(250) NOT NULL,
  `leave_content` varchar(1000) NOT NULL,
  `timestamp` varchar(20) NOT NULL,
  `approval` varchar(20) NOT NULL DEFAULT 'Pending'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `levels`
--

CREATE TABLE `levels` (
  `level` varchar(3) NOT NULL,
  `sal` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `levels`
--

INSERT INTO `levels` (`level`, `sal`) VALUES
('L1', '20000.00'),
('L2', '15000.00'),
('L3', '10000.00');

-- --------------------------------------------------------

--
-- Table structure for table `manager_login_details`
--

CREATE TABLE `manager_login_details` (
  `eid` varchar(20) NOT NULL,
  `pass` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

CREATE TABLE `projects` (
  `pid` varchar(20) NOT NULL,
  `pname` varchar(40) NOT NULL,
  `pleader` varchar(20) NOT NULL,
  `startdate` varchar(10) NOT NULL,
  `deadline` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `salary_structure`
--

CREATE TABLE `salary_structure` (
  `eid` varchar(20) NOT NULL,
  `designation_sal` decimal(10,2) NOT NULL,
  `gross_sal` decimal(10,2) NOT NULL,
  `tax_per` varchar(5) NOT NULL,
  `taxed_sal` decimal(10,2) NOT NULL,
  `net_sal` decimal(10,2) NOT NULL,
  `gross_annual_sal` decimal(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `salary_structure`
--

INSERT INTO `salary_structure` (`eid`, `designation_sal`, `gross_sal`, `tax_per`, `taxed_sal`, `net_sal`, `gross_annual_sal`) VALUES
('H123', '30000.00', '50000.00', '6%', '47000.00', '47000.00', '600000.00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`eid`);

--
-- Indexes for table `employee_login_details`
--
ALTER TABLE `employee_login_details`
  ADD PRIMARY KEY (`eid`);

--
-- Indexes for table `executive_login_details`
--
ALTER TABLE `executive_login_details`
  ADD PRIMARY KEY (`eid`);

--
-- Indexes for table `leaves`
--
ALTER TABLE `leaves`
  ADD PRIMARY KEY (`eid`);

--
-- Indexes for table `manager_login_details`
--
ALTER TABLE `manager_login_details`
  ADD PRIMARY KEY (`eid`);

--
-- Indexes for table `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`pid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
