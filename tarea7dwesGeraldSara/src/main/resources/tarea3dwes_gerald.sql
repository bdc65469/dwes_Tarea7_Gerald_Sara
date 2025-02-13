-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-12-2024 a las 12:23:49
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
-- Base de datos: `tarea3dwes_gerald`
--
CREATE DATABASE IF NOT EXISTS `tarea3dwes_gerald` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `tarea3dwes_gerald`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `credenciales`
--

CREATE TABLE `credenciales` (
  `id` bigint(20) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `usuario` varchar(50) DEFAULT NULL,
  `id_persona` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `credenciales`
--

INSERT INTO `credenciales` (`id`, `password`, `usuario`, `id_persona`) VALUES
(1, NULL, 'admin', 1),
(2, 'gerald123', 'gerald', 2),
(3, 'sara123', 'sara', 3),
(4, 'luis123', 'luis', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ejemplares`
--

CREATE TABLE `ejemplares` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(25) DEFAULT NULL,
  `idplanta` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ejemplares`
--

INSERT INTO `ejemplares` (`id`, `nombre`, `idplanta`) VALUES
(1, 'ROSA1', 1),
(2, 'ROSA2', 1),
(3, 'TULIPAN1', 2),
(4, 'LAVANDA1', 4),
(5, 'HORTENSIA1', 3),
(6, 'ROSA3', 1),
(7, 'ALOE1', 6),
(8, 'MENTA1', 5),
(9, 'HORTENSIA2', 3),
(10, 'TULIPAN2', 2),
(11, 'ALOE2', 6),
(12, 'LAVANDA2', 4),
(13, 'ALOE3', 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes`
--

CREATE TABLE `mensajes` (
  `id` bigint(20) NOT NULL,
  `fechahora` datetime(6) DEFAULT NULL,
  `mensaje` longtext DEFAULT NULL,
  `id_ejemplar` bigint(20) DEFAULT NULL,
  `id_persona` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mensajes`
--

INSERT INTO `mensajes` (`id`, `fechahora`, `mensaje`, `id_ejemplar`, `id_persona`) VALUES
(1, '2024-12-02 12:04:53.000000', 'Nuevo ejemplar de Rosa creado.', 1, 1),
(2, '2024-12-02 12:05:02.000000', 'Nuevo ejemplar de Rosa creado.', 2, 1),
(3, '2024-12-02 12:05:06.000000', 'Nuevo ejemplar de Tulipan creado.', 3, 1),
(4, '2024-12-02 12:05:10.000000', 'Nuevo ejemplar de Lavanda creado.', 4, 1),
(5, '2024-12-02 12:05:29.000000', 'Nuevo ejemplar de Hortensia creado.', 5, 2),
(6, '2024-12-02 12:05:35.000000', 'Nuevo ejemplar de Rosa creado.', 6, 2),
(7, '2024-12-02 12:05:53.000000', 'Ha crecido un poco', 1, 2),
(8, '2024-12-02 12:06:08.000000', 'Hay que regarla', 1, 2),
(9, '2024-12-02 12:06:27.000000', 'Ha sido regada', 4, 2),
(10, '2024-12-02 12:06:42.000000', 'Nuevo ejemplar de Aloe vera creado.', 7, 3),
(11, '2024-12-02 12:07:00.000000', 'Ha sido plantada', 7, 3),
(12, '2024-12-02 12:07:28.000000', 'Ha sido regada', 1, 3),
(13, '2024-12-02 12:07:45.000000', 'Ha sido regada', 4, 3),
(14, '2024-12-02 12:10:19.000000', 'Nuevo ejemplar de Menta creado.', 8, 3),
(15, '2024-12-02 12:11:46.000000', 'Nuevo ejemplar de Hortensia creado.', 9, 3),
(16, '2024-12-02 12:11:48.000000', 'Nuevo ejemplar de Tulipan creado.', 10, 3),
(17, '2024-12-02 12:11:51.000000', 'Nuevo ejemplar de Aloe vera creado.', 11, 3),
(18, '2024-12-02 12:12:17.000000', 'Hay que regarla', 10, 3),
(19, '2024-12-02 12:13:58.000000', 'Hay que rearla', 8, 3),
(20, '2024-12-02 12:16:07.000000', 'Hay que tener cuidado con este ejemplar', 7, 1),
(21, '2024-12-02 12:22:41.000000', 'Nuevo ejemplar de Lavanda creado.', 12, 1),
(22, '2024-12-02 12:22:45.000000', 'Nuevo ejemplar de Aloe vera creado.', 13, 1);

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
(2, 'gerald@vivero.es', 'gerald'),
(3, 'sara@vivero.es', 'sara'),
(4, 'luis@vivero.es', 'Luis');

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
(1, 'ROSA', 'Rosa spp', 'Rosa'),
(2, 'TULIPAN', 'Tulipan gesneriana', 'Tulipan'),
(3, 'HORTENSIA', 'Hydrangea macrophylla', 'Hortensia'),
(4, 'LAVANDA', 'Lavandula angustifolia', 'Lavanda'),
(5, 'MENTA', 'Mentha piperita', 'Menta'),
(6, 'ALOE', 'Aloe barbadensis miller', 'Aloe vera');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `credenciales`
--
ALTER TABLE `credenciales`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKgl50fmouks2ue8s9yclvv059j` (`usuario`),
  ADD UNIQUE KEY `UKkj0bakygq84a8uwy2avcihxqi` (`id_persona`);

--
-- Indices de la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKclleiwyydddhkx72v38u6uw0l` (`idplanta`);

--
-- Indices de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKidbx1mhngh3c3ry5bqisftxbv` (`id_ejemplar`),
  ADD KEY `FK2e6au5w562m7skcvx9jckiba6` (`id_persona`);

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
-- AUTO_INCREMENT de la tabla `credenciales`
--
ALTER TABLE `credenciales`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `personas`
--
ALTER TABLE `personas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `plantas`
--
ALTER TABLE `plantas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `credenciales`
--
ALTER TABLE `credenciales`
  ADD CONSTRAINT `FKgntr1s6h8ryu511tqjln50yp2` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id`);

--
-- Filtros para la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  ADD CONSTRAINT `FKclleiwyydddhkx72v38u6uw0l` FOREIGN KEY (`idplanta`) REFERENCES `plantas` (`id`);

--
-- Filtros para la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD CONSTRAINT `FK2e6au5w562m7skcvx9jckiba6` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id`),
  ADD CONSTRAINT `FKidbx1mhngh3c3ry5bqisftxbv` FOREIGN KEY (`id_ejemplar`) REFERENCES `ejemplares` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
