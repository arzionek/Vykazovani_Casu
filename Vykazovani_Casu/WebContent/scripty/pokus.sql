-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Počítač: localhost
-- Vygenerováno: Úte 25. bře 2014, 19:48
-- Verze MySQL: 5.5.27
-- Verze PHP: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Databáze: `pokus`
--

-- --------------------------------------------------------

--
-- Struktura tabulky `uzivatel`
--

CREATE TABLE IF NOT EXISTS `uzivatel` (
  `id_uzivatel` int(11) NOT NULL AUTO_INCREMENT,
  `jmeno` varchar(30) COLLATE utf8_czech_ci NOT NULL,
  `prijmeni` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `titul_pred` varchar(30) COLLATE utf8_czech_ci DEFAULT '',
  `titul_za` varchar(20) COLLATE utf8_czech_ci DEFAULT '',
  `login` varchar(30) COLLATE utf8_czech_ci NOT NULL,
  `heslo` varchar(30) COLLATE utf8_czech_ci NOT NULL DEFAULT 'admin',
  PRIMARY KEY (`id_uzivatel`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci AUTO_INCREMENT=2 ;

--
-- Vypisuji data pro tabulku `uzivatel`
--

INSERT INTO `uzivatel` (`id_uzivatel`, `jmeno`, `prijmeni`, `titul_pred`, `titul_za`, `login`, `heslo`) VALUES
(1, 'Vendelín', 'Pokus', 'prof. Ing.', 'Ph.D.', 'admin', 'admin');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
