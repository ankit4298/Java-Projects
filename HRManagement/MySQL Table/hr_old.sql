-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 19, 2018 at 06:43 AM
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
('Programmer', '25000.00'),
('Software Architect', '30000.00'),
('Analyst', '22000.00'),
('Software Tester', '21000.00');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `eid` varchar(20) NOT NULL,
  `ename` varchar(20) NOT NULL,
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
('E007', 'akshay', 'gaikwadakshay663@gmail.com', 8741444888, 'nashik', 'nashik', 'MH15', 'India', '0000-00-00', 'Programmer', 'L1', '90000.00', 1, NULL, NULL, NULL),
('E1234', 'Ankit', 'Ankit4298@gmail.com', 8793451298, 'PNCHVT', 'NSK', 'MH', 'IN', '0000-00-00', 'Sales', 'L3', '25000.00', 652, '0', '0', 'NULL'),
('E1235', 'xyz', 'abx@gmail.com', 8793451222, 'aa', 'aa', 'aa', 'aa', '2018-06-19', 'Analyst', '', '0.00', 112, '0', '0', ''),
('E1236', 'udasiduv', 'aaa@gmail.com', 1234567890, 'a', 'nudb', 'budw', 'uwb', '2018-06-19', 'Programmer', '', '0.00', 100, '0', '0', ''),
('E789', 'Donald Trump', 'unitedpeople.up@gmail.com', 8799955, 'ZZ', 'RR', 'NY', 'USA', '0000-00-00', 'Programmer', 'L1', '22540.00', 150, '0', '0', 'NULL'),
('H123', 'Administrator', 'ankit4298@gmail.com', 222222222, 'ABC', 'XYZ', 'MNO', 'PQR', '0000-00-00', 'CEO', 'L2', '90000.00', 1, '0', '0', NULL),
('M111', 'XYZ', 'xyz@gmail.com', 87999999999, 'as', 'asa', 'sasasa', 'sasasa', '2011-12-11', 'Manager', 'L1', '90000.00', 0, NULL, NULL, NULL),
('M112', 'ttadtacs', 'eee@ee.com', 1278877722, 'assasa', 'fgd', 'gdfgd', 'gfdf', '2018-06-19', 'Software Tester', '', '0.00', 255, '0', '0', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `employee_login_details`
--

CREATE TABLE `employee_login_details` (
  `eid` varchar(20) NOT NULL,
  `pass` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee_login_details`
--

INSERT INTO `employee_login_details` (`eid`, `pass`) VALUES
('E007', '007'),
('E1234', 'qqq'),
('E1235', '1235'),
('E1236', 'aaa'),
('E789', 'ppp');

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
(1236, 112, 123);

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
('E007', 3, 37, 0),
('E1234', 6, 34, 0),
('E1235', 0, 4, 0),
('E1236', 0, 4, 0),
('E789', 0, 40, 0),
('H123', 43, 0, 3),
('M111', 14, 26, 0),
('M112', 0, 4, 0);

-- --------------------------------------------------------

--
-- Table structure for table `leave_applications`
--

CREATE TABLE `leave_applications` (
  `eid` varchar(20) NOT NULL,
  `no_of_days` int(5) NOT NULL DEFAULT '0',
  `leave_sub` varchar(250) NOT NULL,
  `leave_content` varchar(1000) NOT NULL,
  `timestamp` varchar(20) NOT NULL,
  `approval` varchar(8) NOT NULL DEFAULT 'Pending'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `leave_applications`
--

INSERT INTO `leave_applications` (`eid`, `no_of_days`, `leave_sub`, `leave_content`, `timestamp`, `approval`) VALUES
('E1234', 6, 'weirhf 8q', 'ghwefy8weqyfr8 qwtef8 twqe8fgwqeg f', '', 'Pending'),
('E1234', 321, 'sdfas df', 'asdfasdfasdf', '', 'Pending'),
('E1234', 12, 'sdf', 'asdfa', '', 'Pending'),
('E1234', 12, 'fsd', 'fasdfsadfasdf', '17/06/2018 15:19:56', 'Approved'),
('E1234', 2, 'asfdsa', 'fasdfasd', '17/06/2018 15:39:12', 'Approved'),
('E007', 2, 'udqwdu', 'feisf giwueggf wg fygs fgs gfu gwugqwi dgqwdg', '18/06/2018 12:52:36', 'Approved');

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
('L1', '10000.00'),
('L2', '5000.00'),
('L3', '2000.00');

-- --------------------------------------------------------

--
-- Table structure for table `manager_login_details`
--

CREATE TABLE `manager_login_details` (
  `eid` varchar(20) NOT NULL,
  `pass` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `manager_login_details`
--

INSERT INTO `manager_login_details` (`eid`, `pass`) VALUES
('M111', '111'),
('M112', 'eee');

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
