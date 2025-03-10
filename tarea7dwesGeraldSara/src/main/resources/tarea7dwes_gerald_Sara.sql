-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-03-2025 a las 12:35:32
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tarea7dwes_gerald`
--
CREATE DATABASE IF NOT EXISTS `tarea7dwes_gerald` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `tarea7dwes_gerald`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id` bigint(20) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `fechanac` date DEFAULT NULL,
  `nif` varchar(10) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `telefono` varchar(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `direccion`, `email`, `fechanac`, `nif`, `nombre`, `telefono`) VALUES
(1, 'C/De la pobleza Nº0 Bajo', 'chindinelo@sineulos.com', '2000-03-08', 'Z9878768C', 'Chindinelo', '646576789'),
(2, 'C/Avenida Argentina Nº19 4ºD', 'alejandro@hotmail.com', '1999-11-08', '56798686K', 'Alejandro', '667656565');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `credenciales`
--

CREATE TABLE `credenciales` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `rol` enum('ROLE_ADMIN','ROLE_CLIENTE','ROLE_REGISTRADO') DEFAULT NULL,
  `usuario` varchar(50) DEFAULT NULL,
  `id_cliente` bigint(20) DEFAULT NULL,
  `id_persona` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `credenciales`
--

INSERT INTO `credenciales` (`id`, `password`, `rol`, `usuario`, `id_cliente`, `id_persona`) VALUES
(1, '$2b$12$DjHixMwQbYr196wde69NUekTnAPQU8w0DptYn6neFs/VYw.biIyM6', 'ROLE_ADMIN', 'admin', NULL, 1),
(2, '$2a$10$G5TXrfD.DqFvpVYexiYjQeU8LVxv0YBPjmCoOVXbxTuf59BpmWCsa', 'ROLE_REGISTRADO', 'gerald', NULL, 2),
(3, '$2a$10$qmgP1fXTRoA6if8KEyuFgOf0wB9oPmJ4m1UJXeLmeicvVumo.w95i', 'ROLE_REGISTRADO', 'sara', NULL, 3),
(4, '$2a$10$EExPxM7Th5T8ZbSOZBCvTunaS6wVSyuh1FnGJbiA18sTAii5/8l/e', 'ROLE_CLIENTE', 'chindinelo', 1, NULL),
(5, '$2a$10$y1wGioOjjHuU4VxxNdtufOig8NewGCHBOW2b9dB3cYHzrKaS5VCFa', 'ROLE_CLIENTE', 'alex', 2, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ejemplares`
--

CREATE TABLE `ejemplares` (
  `id` bigint(20) NOT NULL,
  `disponible` bit(1) DEFAULT NULL,
  `nombre` varchar(25) DEFAULT NULL,
  `id_pedido` bigint(20) DEFAULT NULL,
  `idplanta` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ejemplares`
--

INSERT INTO `ejemplares` (`id`, `disponible`, `nombre`, `id_pedido`, `idplanta`) VALUES
(1, b'1', 'BMB1', 5, 10),
(2, b'0', 'BMB2', 9, 10),
(3, b'0', 'BMB3', 9, 10),
(4, b'0', 'COST1', 1, 1),
(5, b'0', 'COST2', 1, 1),
(6, b'0', 'COST3', 3, 1),
(7, b'1', 'COST4', 5, 1),
(8, b'0', 'COST5', 9, 1),
(9, b'0', 'FIL1', 2, 4),
(10, b'1', 'FIL2', NULL, 4),
(11, b'1', 'FIL3', NULL, 4),
(12, b'1', 'FIL4', NULL, 4),
(13, b'0', 'LVD1', 1, 9),
(14, b'0', 'LVD2', 1, 9),
(15, b'0', 'LVD3', 2, 9),
(16, b'0', 'LDS1', 1, 2),
(17, b'0', 'LDS2', 1, 2),
(18, b'0', 'LDS3', 2, 2),
(19, b'0', 'MNZ1', 2, 8),
(20, b'0', 'MNZ2', 3, 8),
(21, b'0', 'MNZ3', 10, 8),
(22, b'0', 'MNT1', 1, 7),
(23, b'0', 'MNT2', 10, 7),
(24, b'0', 'MNT3', 10, 7),
(25, b'0', 'ORQ1', 4, 6),
(26, b'0', 'ORQ2', 4, 6),
(27, b'0', 'ORQ3', 4, 6),
(28, b'0', 'ORQ4', 10, 6),
(29, b'0', 'POT1', 4, 3),
(30, b'0', 'POT2', 11, 3),
(31, b'1', 'POT3', NULL, 3),
(32, b'1', 'POT4', NULL, 3),
(33, b'0', 'RSA1', 4, 5),
(34, b'1', 'RSA2', NULL, 5),
(35, b'1', 'RSA3', NULL, 5),
(36, b'1', 'BMB4', NULL, 10),
(37, b'1', 'BMB5', NULL, 10),
(38, b'1', 'BMB6', NULL, 10),
(39, b'1', 'COST6', NULL, 1),
(40, b'1', 'COST7', NULL, 1),
(41, b'1', 'COST8', NULL, 1),
(42, b'1', 'FIL5', NULL, 4),
(43, b'0', 'LVD4', 3, 9),
(44, b'1', 'LVD5', 8, 9),
(45, b'1', 'LVD6', NULL, 9),
(46, b'1', 'LDS4', 8, 2),
(47, b'1', 'LDS5', 8, 2),
(48, b'0', 'MNZ4', 10, 8),
(49, b'0', 'MNZ5', 10, 8),
(50, b'1', 'MNZ6', NULL, 8),
(51, b'0', 'MNT4', 10, 7),
(52, b'1', 'MNT5', NULL, 7),
(53, b'0', 'ORQ5', 11, 6),
(54, b'0', 'ORQ6', 11, 6),
(55, b'1', 'POT5', NULL, 3),
(56, b'1', 'POT6', NULL, 3),
(57, b'1', 'RSA4', NULL, 5),
(58, b'1', 'RSA5', NULL, 5),
(59, b'1', 'MNT6', NULL, 7),
(60, b'1', 'BMB7', NULL, 10),
(61, b'1', 'RSA6', NULL, 5),
(62, b'1', 'RSA7', NULL, 5),
(63, b'0', 'LDS6', 10, 2),
(64, b'1', 'LVD7', NULL, 9),
(65, b'1', 'LVD8', NULL, 9),
(66, b'1', 'FIL6', NULL, 4),
(67, b'1', 'COST9', NULL, 1),
(68, b'1', 'BMB8', NULL, 10),
(69, b'1', 'MNT7', NULL, 7),
(70, b'1', 'ORQ7', NULL, 6),
(71, b'1', 'POT7', NULL, 3),
(72, b'1', 'RSA8', NULL, 5),
(73, b'1', 'RSA9', NULL, 5),
(74, b'1', 'ORQ8', NULL, 6),
(75, b'1', 'ORQ9', NULL, 6),
(76, b'1', 'COST10', NULL, 1),
(77, b'1', 'BMB9', NULL, 10),
(78, b'1', 'FIL7', NULL, 4),
(79, b'1', 'FIL8', NULL, 4),
(80, b'1', 'FIL9', NULL, 4),
(81, b'1', 'LDS7', NULL, 2),
(82, b'1', 'LDS8', NULL, 2),
(83, b'1', 'MNZ7', NULL, 8),
(84, b'1', 'MNZ8', NULL, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes`
--

CREATE TABLE `mensajes` (
  `id` bigint(20) NOT NULL,
  `fechahora` datetime(6) DEFAULT NULL,
  `mensaje` longtext DEFAULT NULL,
  `id_cliente` bigint(20) DEFAULT NULL,
  `id_ejemplar` bigint(20) DEFAULT NULL,
  `id_persona` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mensajes`
--

INSERT INTO `mensajes` (`id`, `fechahora`, `mensaje`, `id_cliente`, `id_ejemplar`, `id_persona`) VALUES
(1, '2025-03-10 11:06:00.000000', 'Nuevo ejemplar de Bambú creado.', NULL, 1, 1),
(2, '2025-03-10 11:06:00.000000', 'Nuevo ejemplar de Bambú creado.', NULL, 2, 1),
(3, '2025-03-10 11:06:00.000000', 'Nuevo ejemplar de Bambú creado.', NULL, 3, 1),
(4, '2025-03-10 11:06:01.000000', 'Nuevo ejemplar de Costilla de Adán creado.', NULL, 4, 1),
(5, '2025-03-10 11:06:01.000000', 'Nuevo ejemplar de Costilla de Adán creado.', NULL, 5, 1),
(6, '2025-03-10 11:06:01.000000', 'Nuevo ejemplar de Costilla de Adán creado.', NULL, 6, 1),
(7, '2025-03-10 11:06:01.000000', 'Nuevo ejemplar de Costilla de Adán creado.', NULL, 7, 1),
(8, '2025-03-10 11:06:01.000000', 'Nuevo ejemplar de Costilla de Adán creado.', NULL, 8, 1),
(9, '2025-03-10 11:06:02.000000', 'Nuevo ejemplar de Ficus Lira creado.', NULL, 9, 1),
(10, '2025-03-10 11:06:02.000000', 'Nuevo ejemplar de Ficus Lira creado.', NULL, 10, 1),
(11, '2025-03-10 11:06:02.000000', 'Nuevo ejemplar de Ficus Lira creado.', NULL, 11, 1),
(12, '2025-03-10 11:06:03.000000', 'Nuevo ejemplar de Ficus Lira creado.', NULL, 12, 1),
(13, '2025-03-10 11:06:03.000000', 'Nuevo ejemplar de Lavanda creado.', NULL, 13, 1),
(14, '2025-03-10 11:06:03.000000', 'Nuevo ejemplar de Lavanda creado.', NULL, 14, 1),
(15, '2025-03-10 11:06:03.000000', 'Nuevo ejemplar de Lavanda creado.', NULL, 15, 1),
(16, '2025-03-10 11:06:04.000000', 'Nuevo ejemplar de Lengua de suegra creado.', NULL, 16, 1),
(17, '2025-03-10 11:06:04.000000', 'Nuevo ejemplar de Lengua de suegra creado.', NULL, 17, 1),
(18, '2025-03-10 11:06:04.000000', 'Nuevo ejemplar de Lengua de suegra creado.', NULL, 18, 1),
(19, '2025-03-10 11:06:05.000000', 'Nuevo ejemplar de Manzanilla creado.', NULL, 19, 1),
(20, '2025-03-10 11:06:05.000000', 'Nuevo ejemplar de Manzanilla creado.', NULL, 20, 1),
(21, '2025-03-10 11:06:05.000000', 'Nuevo ejemplar de Manzanilla creado.', NULL, 21, 1),
(22, '2025-03-10 11:06:06.000000', 'Nuevo ejemplar de Menta creado.', NULL, 22, 1),
(23, '2025-03-10 11:06:06.000000', 'Nuevo ejemplar de Menta creado.', NULL, 23, 1),
(24, '2025-03-10 11:06:06.000000', 'Nuevo ejemplar de Menta creado.', NULL, 24, 1),
(25, '2025-03-10 11:06:07.000000', 'Nuevo ejemplar de Orquídea creado.', NULL, 25, 1),
(26, '2025-03-10 11:06:07.000000', 'Nuevo ejemplar de Orquídea creado.', NULL, 26, 1),
(27, '2025-03-10 11:06:07.000000', 'Nuevo ejemplar de Orquídea creado.', NULL, 27, 1),
(28, '2025-03-10 11:06:07.000000', 'Nuevo ejemplar de Orquídea creado.', NULL, 28, 1),
(29, '2025-03-10 11:06:08.000000', 'Nuevo ejemplar de Pothos creado.', NULL, 29, 1),
(30, '2025-03-10 11:06:08.000000', 'Nuevo ejemplar de Pothos creado.', NULL, 30, 1),
(31, '2025-03-10 11:06:08.000000', 'Nuevo ejemplar de Pothos creado.', NULL, 31, 1),
(32, '2025-03-10 11:06:08.000000', 'Nuevo ejemplar de Pothos creado.', NULL, 32, 1),
(33, '2025-03-10 11:06:09.000000', 'Nuevo ejemplar de Rosa creado.', NULL, 33, 1),
(34, '2025-03-10 11:06:09.000000', 'Nuevo ejemplar de Rosa creado.', NULL, 34, 1),
(35, '2025-03-10 11:06:09.000000', 'Nuevo ejemplar de Rosa creado.', NULL, 35, 1),
(36, '2025-03-10 11:06:11.000000', 'Nuevo ejemplar de Bambú creado.', NULL, 36, 1),
(37, '2025-03-10 11:06:11.000000', 'Nuevo ejemplar de Bambú creado.', NULL, 37, 1),
(38, '2025-03-10 11:06:11.000000', 'Nuevo ejemplar de Bambú creado.', NULL, 38, 1),
(39, '2025-03-10 11:06:12.000000', 'Nuevo ejemplar de Costilla de Adán creado.', NULL, 39, 1),
(40, '2025-03-10 11:06:12.000000', 'Nuevo ejemplar de Costilla de Adán creado.', NULL, 40, 1),
(41, '2025-03-10 11:06:12.000000', 'Nuevo ejemplar de Costilla de Adán creado.', NULL, 41, 1),
(42, '2025-03-10 11:06:12.000000', 'Nuevo ejemplar de Ficus Lira creado.', NULL, 42, 1),
(43, '2025-03-10 11:06:13.000000', 'Nuevo ejemplar de Lavanda creado.', NULL, 43, 1),
(44, '2025-03-10 11:06:13.000000', 'Nuevo ejemplar de Lavanda creado.', NULL, 44, 1),
(45, '2025-03-10 11:06:13.000000', 'Nuevo ejemplar de Lavanda creado.', NULL, 45, 1),
(46, '2025-03-10 11:06:13.000000', 'Nuevo ejemplar de Lengua de suegra creado.', NULL, 46, 1),
(47, '2025-03-10 11:06:14.000000', 'Nuevo ejemplar de Lengua de suegra creado.', NULL, 47, 1),
(48, '2025-03-10 11:06:14.000000', 'Nuevo ejemplar de Manzanilla creado.', NULL, 48, 1),
(49, '2025-03-10 11:06:14.000000', 'Nuevo ejemplar de Manzanilla creado.', NULL, 49, 1),
(50, '2025-03-10 11:06:15.000000', 'Nuevo ejemplar de Manzanilla creado.', NULL, 50, 1),
(51, '2025-03-10 11:06:15.000000', 'Nuevo ejemplar de Menta creado.', NULL, 51, 1),
(52, '2025-03-10 11:06:15.000000', 'Nuevo ejemplar de Menta creado.', NULL, 52, 1),
(53, '2025-03-10 11:06:16.000000', 'Nuevo ejemplar de Orquídea creado.', NULL, 53, 1),
(54, '2025-03-10 11:06:16.000000', 'Nuevo ejemplar de Orquídea creado.', NULL, 54, 1),
(55, '2025-03-10 11:06:16.000000', 'Nuevo ejemplar de Pothos creado.', NULL, 55, 1),
(56, '2025-03-10 11:06:17.000000', 'Nuevo ejemplar de Pothos creado.', NULL, 56, 1),
(57, '2025-03-10 11:06:17.000000', 'Nuevo ejemplar de Rosa creado.', NULL, 57, 1),
(58, '2025-03-10 11:06:17.000000', 'Nuevo ejemplar de Rosa creado.', NULL, 58, 1),
(59, '2025-03-10 11:06:45.000000', 'Nuevo ejemplar de Menta creado.', NULL, 59, 1),
(60, '2025-03-10 11:06:46.000000', 'Nuevo ejemplar de Bambú creado.', NULL, 60, 1),
(61, '2025-03-10 11:06:49.000000', 'Nuevo ejemplar de Rosa creado.', NULL, 61, 1),
(62, '2025-03-10 11:06:49.000000', 'Nuevo ejemplar de Rosa creado.', NULL, 62, 1),
(63, '2025-03-10 11:07:00.000000', 'Ha sido regado', NULL, 1, 1),
(64, '2025-03-10 11:07:03.000000', 'Ha sido regado', NULL, 37, 1),
(65, '2025-03-10 11:07:07.000000', 'Ha sido regado', NULL, 53, 1),
(66, '2025-03-10 11:07:10.000000', 'Ha sido regado', NULL, 56, 1),
(67, '2025-03-10 11:07:13.000000', 'Ha sido regado', NULL, 4, 1),
(68, '2025-03-10 11:07:17.000000', 'Ha sido regado', NULL, 34, 1),
(69, '2025-03-10 11:07:20.000000', 'Ha sido regado', NULL, 6, 1),
(70, '2025-03-10 11:07:31.000000', 'Se está muriendo', NULL, 37, 1),
(71, '2025-03-10 11:07:34.000000', 'Se está muriendo', NULL, 4, 1),
(72, '2025-03-10 11:07:36.000000', 'Se está muriendo', NULL, 26, 1),
(73, '2025-03-10 11:07:40.000000', 'Se está muriendo', NULL, 35, 1),
(74, '2025-03-10 11:07:42.000000', 'Se está muriendo', NULL, 54, 1),
(75, '2025-03-10 11:07:45.000000', 'Se está muriendo', NULL, 3, 1),
(76, '2025-03-10 11:07:48.000000', 'Se está muriendo', NULL, 7, 1),
(77, '2025-03-10 11:08:47.000000', 'Ha crecido', NULL, 1, 2),
(78, '2025-03-10 11:08:50.000000', 'Ha crecido', NULL, 37, 2),
(79, '2025-03-10 11:08:53.000000', 'Ha crecido', NULL, 15, 2),
(80, '2025-03-10 11:08:56.000000', 'Ha crecido', NULL, 55, 2),
(81, '2025-03-10 11:09:05.000000', 'Tiene una bacteria', NULL, 36, 2),
(82, '2025-03-10 11:09:11.000000', 'Tiene una bacteria', NULL, 44, 2),
(83, '2025-03-10 11:09:14.000000', 'Tiene una bacteria', NULL, 57, 2),
(84, '2025-03-10 11:09:32.000000', 'Ha sido regada', NULL, 37, 3),
(85, '2025-03-10 11:09:36.000000', 'Ha sido regada', NULL, 10, 3),
(86, '2025-03-10 11:09:46.000000', 'Ha sido regada', NULL, 45, 3),
(87, '2025-03-10 11:09:50.000000', 'Ha sido regada', NULL, 58, 3),
(88, '2025-03-10 11:52:29.000000', 'El cliente Chindinelo compró el ejemplar COST1 el día 10/03/25 11:52:29 en el pedido 1', 1, 4, NULL),
(89, '2025-03-10 11:52:29.000000', 'El cliente Chindinelo compró el ejemplar COST2 el día 10/03/25 11:52:29 en el pedido 1', 1, 5, NULL),
(90, '2025-03-10 11:52:29.000000', 'El cliente Chindinelo compró el ejemplar LVD1 el día 10/03/25 11:52:29 en el pedido 1', 1, 13, NULL),
(91, '2025-03-10 11:52:29.000000', 'El cliente Chindinelo compró el ejemplar LVD2 el día 10/03/25 11:52:29 en el pedido 1', 1, 14, NULL),
(92, '2025-03-10 11:52:29.000000', 'El cliente Chindinelo compró el ejemplar LDS1 el día 10/03/25 11:52:29 en el pedido 1', 1, 16, NULL),
(93, '2025-03-10 11:52:29.000000', 'El cliente Chindinelo compró el ejemplar LDS2 el día 10/03/25 11:52:29 en el pedido 1', 1, 17, NULL),
(94, '2025-03-10 11:52:29.000000', 'El cliente Chindinelo compró el ejemplar MNT1 el día 10/03/25 11:52:29 en el pedido 1', 1, 22, NULL),
(95, '2025-03-10 11:54:58.000000', 'El cliente Chindinelo compró el ejemplar FIL1 el día 10/03/25 11:54:58 en el pedido 2', 1, 9, NULL),
(96, '2025-03-10 11:54:58.000000', 'El cliente Chindinelo compró el ejemplar LVD3 el día 10/03/25 11:54:58 en el pedido 2', 1, 15, NULL),
(97, '2025-03-10 11:54:58.000000', 'El cliente Chindinelo compró el ejemplar LDS3 el día 10/03/25 11:54:58 en el pedido 2', 1, 18, NULL),
(98, '2025-03-10 11:54:58.000000', 'El cliente Chindinelo compró el ejemplar MNZ1 el día 10/03/25 11:54:58 en el pedido 2', 1, 19, NULL),
(99, '2025-03-10 11:59:01.000000', 'El cliente Chindinelo compró el ejemplar COST3 el día 10/03/25 11:59:01 en el pedido 3', 1, 6, NULL),
(100, '2025-03-10 11:59:01.000000', 'El cliente Chindinelo compró el ejemplar LVD4 el día 10/03/25 11:59:01 en el pedido 3', 1, 43, NULL),
(101, '2025-03-10 11:59:01.000000', 'El cliente Chindinelo compró el ejemplar MNZ2 el día 10/03/25 11:59:01 en el pedido 3', 1, 20, NULL),
(102, '2025-03-10 11:59:57.000000', 'El cliente Chindinelo compró el ejemplar ORQ1 el día 10/03/25 11:59:57 en el pedido 4', 1, 25, NULL),
(103, '2025-03-10 11:59:57.000000', 'El cliente Chindinelo compró el ejemplar ORQ2 el día 10/03/25 11:59:57 en el pedido 4', 1, 26, NULL),
(104, '2025-03-10 11:59:57.000000', 'El cliente Chindinelo compró el ejemplar ORQ3 el día 10/03/25 11:59:57 en el pedido 4', 1, 27, NULL),
(105, '2025-03-10 11:59:57.000000', 'El cliente Chindinelo compró el ejemplar POT1 el día 10/03/25 11:59:57 en el pedido 4', 1, 29, NULL),
(106, '2025-03-10 11:59:57.000000', 'El cliente Chindinelo compró el ejemplar RSA1 el día 10/03/25 11:59:57 en el pedido 4', 1, 33, NULL),
(107, '2025-03-10 12:00:37.000000', 'El cliente Chindinelo compró el ejemplar BMB1 el día 10/03/25 12:00:37 en el pedido 5', 1, 1, NULL),
(108, '2025-03-10 12:00:37.000000', 'El cliente Chindinelo compró el ejemplar COST4 el día 10/03/25 12:00:37 en el pedido 5', 1, 7, NULL),
(109, '2025-03-10 12:07:32.000000', 'El cliente Chindinelo compró el ejemplar LVD5 el día 10/03/25 12:07:32 en el pedido 8', 1, 44, NULL),
(110, '2025-03-10 12:07:32.000000', 'El cliente Chindinelo compró el ejemplar LDS4 el día 10/03/25 12:07:32 en el pedido 8', 1, 46, NULL),
(111, '2025-03-10 12:07:32.000000', 'El cliente Chindinelo compró el ejemplar LDS5 el día 10/03/25 12:07:32 en el pedido 8', 1, 47, NULL),
(112, '2025-03-10 12:10:32.000000', 'Nuevo ejemplar de Lengua de suegra creado.', NULL, 63, 1),
(113, '2025-03-10 12:10:32.000000', 'Nuevo ejemplar de Lavanda creado.', NULL, 64, 1),
(114, '2025-03-10 12:10:34.000000', 'Nuevo ejemplar de Lavanda creado.', NULL, 65, 1),
(115, '2025-03-10 12:10:34.000000', 'Nuevo ejemplar de Ficus Lira creado.', NULL, 66, 1),
(116, '2025-03-10 12:10:35.000000', 'Nuevo ejemplar de Costilla de Adán creado.', NULL, 67, 1),
(117, '2025-03-10 12:10:35.000000', 'Nuevo ejemplar de Bambú creado.', NULL, 68, 1),
(118, '2025-03-10 12:10:36.000000', 'Nuevo ejemplar de Menta creado.', NULL, 69, 1),
(119, '2025-03-10 12:10:36.000000', 'Nuevo ejemplar de Orquídea creado.', NULL, 70, 1),
(120, '2025-03-10 12:10:37.000000', 'Nuevo ejemplar de Pothos creado.', NULL, 71, 1),
(121, '2025-03-10 12:10:37.000000', 'Nuevo ejemplar de Rosa creado.', NULL, 72, 1),
(122, '2025-03-10 12:10:37.000000', 'Nuevo ejemplar de Rosa creado.', NULL, 73, 1),
(123, '2025-03-10 12:10:38.000000', 'Nuevo ejemplar de Orquídea creado.', NULL, 74, 1),
(124, '2025-03-10 12:10:38.000000', 'Nuevo ejemplar de Orquídea creado.', NULL, 75, 1),
(125, '2025-03-10 12:11:27.000000', 'Nuevo ejemplar de Costilla de Adán creado.', NULL, 76, 1),
(126, '2025-03-10 12:11:27.000000', 'Nuevo ejemplar de Bambú creado.', NULL, 77, 1),
(127, '2025-03-10 12:11:28.000000', 'Nuevo ejemplar de Ficus Lira creado.', NULL, 78, 1),
(128, '2025-03-10 12:11:28.000000', 'Nuevo ejemplar de Ficus Lira creado.', NULL, 79, 1),
(129, '2025-03-10 12:11:28.000000', 'Nuevo ejemplar de Ficus Lira creado.', NULL, 80, 1),
(130, '2025-03-10 12:11:29.000000', 'Nuevo ejemplar de Lengua de suegra creado.', NULL, 81, 1),
(131, '2025-03-10 12:11:29.000000', 'Nuevo ejemplar de Lengua de suegra creado.', NULL, 82, 1),
(132, '2025-03-10 12:11:30.000000', 'Nuevo ejemplar de Manzanilla creado.', NULL, 83, 1),
(133, '2025-03-10 12:11:30.000000', 'Nuevo ejemplar de Manzanilla creado.', NULL, 84, 1),
(134, '2025-03-10 12:11:53.000000', 'El cliente Alejandro compró el ejemplar BMB2 el día 10/03/25 12:11:53 en el pedido 9', 2, 2, NULL),
(135, '2025-03-10 12:11:53.000000', 'El cliente Alejandro compró el ejemplar BMB3 el día 10/03/25 12:11:53 en el pedido 9', 2, 3, NULL),
(136, '2025-03-10 12:11:53.000000', 'El cliente Alejandro compró el ejemplar COST5 el día 10/03/25 12:11:53 en el pedido 9', 2, 8, NULL),
(137, '2025-03-10 12:12:02.000000', 'El cliente Alejandro compró el ejemplar LDS6 el día 10/03/25 12:12:02 en el pedido 10', 2, 63, NULL),
(138, '2025-03-10 12:12:02.000000', 'El cliente Alejandro compró el ejemplar MNZ3 el día 10/03/25 12:12:02 en el pedido 10', 2, 21, NULL),
(139, '2025-03-10 12:12:02.000000', 'El cliente Alejandro compró el ejemplar MNZ4 el día 10/03/25 12:12:02 en el pedido 10', 2, 48, NULL),
(140, '2025-03-10 12:12:02.000000', 'El cliente Alejandro compró el ejemplar MNZ5 el día 10/03/25 12:12:02 en el pedido 10', 2, 49, NULL),
(141, '2025-03-10 12:12:02.000000', 'El cliente Alejandro compró el ejemplar MNT2 el día 10/03/25 12:12:02 en el pedido 10', 2, 23, NULL),
(142, '2025-03-10 12:12:02.000000', 'El cliente Alejandro compró el ejemplar MNT3 el día 10/03/25 12:12:02 en el pedido 10', 2, 24, NULL),
(143, '2025-03-10 12:12:02.000000', 'El cliente Alejandro compró el ejemplar MNT4 el día 10/03/25 12:12:02 en el pedido 10', 2, 51, NULL),
(144, '2025-03-10 12:12:02.000000', 'El cliente Alejandro compró el ejemplar ORQ4 el día 10/03/25 12:12:02 en el pedido 10', 2, 28, NULL),
(145, '2025-03-10 12:12:11.000000', 'El cliente Alejandro compró el ejemplar ORQ5 el día 10/03/25 12:12:11 en el pedido 11', 2, 53, NULL),
(146, '2025-03-10 12:12:11.000000', 'El cliente Alejandro compró el ejemplar ORQ6 el día 10/03/25 12:12:11 en el pedido 11', 2, 54, NULL),
(147, '2025-03-10 12:12:11.000000', 'El cliente Alejandro compró el ejemplar POT2 el día 10/03/25 12:12:11 en el pedido 11', 2, 30, NULL),
(148, '2025-03-10 12:22:10.000000', 'Pedido cancelado. El ejemplar vuelve a estar disponible', NULL, 1, 1),
(149, '2025-03-10 12:22:10.000000', 'Pedido cancelado. El ejemplar vuelve a estar disponible', NULL, 7, 1),
(150, '2025-03-10 12:34:44.000000', 'Pedido cancelado. El ejemplar vuelve a estar disponible', NULL, 44, 2),
(151, '2025-03-10 12:34:44.000000', 'Pedido cancelado. El ejemplar vuelve a estar disponible', NULL, 46, 2),
(152, '2025-03-10 12:34:44.000000', 'Pedido cancelado. El ejemplar vuelve a estar disponible', NULL, 47, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `id` bigint(20) NOT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `id_cliente` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`id`, `estado`, `fecha`, `id_cliente`) VALUES
(1, 2, '2025-03-10', 1),
(2, 0, '2025-03-10', 1),
(3, 2, '2025-03-10', 1),
(4, 0, '2025-03-10', 1),
(5, 3, '2025-03-10', 1),
(8, 3, '2025-03-10', 1),
(9, 0, '2025-03-10', 2),
(10, 1, '2025-03-10', 2),
(11, 1, '2025-03-10', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

CREATE TABLE `personas` (
  `id` bigint(20) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `personas`
--

INSERT INTO `personas` (`id`, `email`, `nombre`) VALUES
(1, 'admin@viviero.es', 'admin'),
(2, 'gerald@vivero.com', 'Gerald'),
(3, 'sara@vivero.es', 'Sara');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plantas`
--

CREATE TABLE `plantas` (
  `id` bigint(20) NOT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `nombrecientifico` varchar(100) DEFAULT NULL,
  `nombrecomun` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `plantas`
--

INSERT INTO `plantas` (`id`, `codigo`, `nombrecientifico`, `nombrecomun`) VALUES
(1, 'COST', 'Monstera deliciosa', 'Costilla de Adán'),
(2, 'LDS', 'Sansevieria trifasciata', 'Lengua de suegra'),
(3, 'POT', 'Epipremnum aureum', 'Pothos'),
(4, 'FIL', 'Ficus lyrata', 'Ficus Lira'),
(5, 'RSA', 'Rosa spp', 'Rosa'),
(6, 'ORQ', 'Orchidaceae spp', 'Orquídea'),
(7, 'MNT', 'Mentha piperita', 'Menta'),
(8, 'MNZ', 'Matricaria chamomilla', 'Manzanilla'),
(9, 'LVD', 'Lavandula angustifolia', 'Lavanda'),
(10, 'BMB', 'Bambusoideae', 'Bambú');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK1c96wv36rk2hwui7qhjks3mvg` (`email`),
  ADD UNIQUE KEY `UKep7teqj7hbumyua1rquyddfce` (`nif`);

--
-- Indices de la tabla `credenciales`
--
ALTER TABLE `credenciales`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKgl50fmouks2ue8s9yclvv059j` (`usuario`),
  ADD UNIQUE KEY `UKc1d4u8wwdij8ygc6ot5iy9mt3` (`id_cliente`),
  ADD UNIQUE KEY `UKkj0bakygq84a8uwy2avcihxqi` (`id_persona`);

--
-- Indices de la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKirjys0atrrrx9a1vc0pxcg2pf` (`id_pedido`),
  ADD KEY `FKclleiwyydddhkx72v38u6uw0l` (`idplanta`);

--
-- Indices de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKlmblxpy7q5k9rq887b0kbihco` (`id_cliente`),
  ADD KEY `FKidbx1mhngh3c3ry5bqisftxbv` (`id_ejemplar`),
  ADD KEY `FK2e6au5w562m7skcvx9jckiba6` (`id_persona`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdnomiluem4t3x66t6b9aher47` (`id_cliente`);

--
-- Indices de la tabla `personas`
--
ALTER TABLE `personas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKlrw7flsg11d8nhgyvueqtnp8e` (`email`);

--
-- Indices de la tabla `plantas`
--
ALTER TABLE `plantas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKbqo6lbeads0ifdh6dohhfhryp` (`codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `credenciales`
--
ALTER TABLE `credenciales`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;

--
-- AUTO_INCREMENT de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=153;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `personas`
--
ALTER TABLE `personas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `plantas`
--
ALTER TABLE `plantas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `credenciales`
--
ALTER TABLE `credenciales`
  ADD CONSTRAINT `FK3vv5yufhdpv6yv8werm04q9gd` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`),
  ADD CONSTRAINT `FKgntr1s6h8ryu511tqjln50yp2` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id`);

--
-- Filtros para la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  ADD CONSTRAINT `FKclleiwyydddhkx72v38u6uw0l` FOREIGN KEY (`idplanta`) REFERENCES `plantas` (`id`),
  ADD CONSTRAINT `FKirjys0atrrrx9a1vc0pxcg2pf` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id`);

--
-- Filtros para la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD CONSTRAINT `FK2e6au5w562m7skcvx9jckiba6` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id`),
  ADD CONSTRAINT `FKidbx1mhngh3c3ry5bqisftxbv` FOREIGN KEY (`id_ejemplar`) REFERENCES `ejemplares` (`id`),
  ADD CONSTRAINT `FKlmblxpy7q5k9rq887b0kbihco` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`);

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `FKdnomiluem4t3x66t6b9aher47` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
