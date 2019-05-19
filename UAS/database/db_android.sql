-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 03, 2019 at 04:13 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_android`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_mahasiswa`
--

CREATE TABLE `tb_mahasiswa` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `nim` varchar(100) NOT NULL,
  `alamat` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_mahasiswa`
--

INSERT INTO `tb_mahasiswa` (`id`, `nama`, `nim`, `alamat`) VALUES
(1, 'sara', '3322', 'gatau'),
(5, 'asa', '12235', 'fgghh'),
(6, 'polo', '222', 'sama'),
(7, 'saya', '2122', 'dada'),
(8, 'sapayah', '13645425', 'kucluk'),
(10, 'tue', '123455', 'girikan'),
(11, 'aaaa', '12321', 'fffda'),
(12, 'jajjaja', '168995', ''),
(13, 'jajjaja', '168995', 'hdhdhh'),
(14, 'saya', '160900600', 'sanggir'),
(16, 'aku', '155', 'goa'),
(17, 'bahtiar', '12100', 'umah'),
(18, '', '', ''),
(19, '', '', ''),
(20, 'sawa', '', ''),
(21, 'zulfahidayat', '1232', 'raaa'),
(22, 'zulfanih', '16090060', 'aaa'),
(23, 'eee', '12345', 'awwe'),
(24, 'julep', '123321', 'sama'),
(25, '', '', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_mahasiswa`
--
ALTER TABLE `tb_mahasiswa`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_mahasiswa`
--
ALTER TABLE `tb_mahasiswa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
