-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 08-03-2015 a las 20:29:57
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `kpax`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Category`
--

DROP TABLE IF EXISTS `Category`;
CREATE TABLE IF NOT EXISTS `Category` (
  `idCategory` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`idCategory`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `name_2` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `Category`
--

INSERT INTO `Category` (`idCategory`, `name`) VALUES
(1, 'Category 1'),
(2, 'Category 2'),
(3, 'Category 3'),
(4, 'Category 4'),
(5, 'Category 5');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Comment`
--

DROP TABLE IF EXISTS `Comment`;
CREATE TABLE IF NOT EXISTS `Comment` (
  `idComment` int(11) NOT NULL AUTO_INCREMENT,
  `idGame` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  PRIMARY KEY (`idComment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Game`
--

DROP TABLE IF EXISTS `Game`;
CREATE TABLE IF NOT EXISTS `Game` (
  `idGame` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `grantPublicAccess` tinyint(1) DEFAULT '0',
  `secretGame` varchar(150) DEFAULT NULL,
  `privateKey` text,
  `idCategory` int(11) DEFAULT '0',
  `idPlatform` int(11) DEFAULT '0',
  `idSkill` int(11) DEFAULT '0',
  `creationDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `developer` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`idGame`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `GameAccess`
--

DROP TABLE IF EXISTS `GameAccess`;
CREATE TABLE IF NOT EXISTS `GameAccess` (
  `idGroup` int(11) NOT NULL,
  `idGame` int(11) NOT NULL,
  `type` varchar(10) NOT NULL DEFAULT 'allow',
  `recursive` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`idGroup`,`idGame`),
  KEY `GameAccess_Group` (`idGroup`),
  KEY `GameAccess_Game` (`idGame`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `GameInstance`
--

DROP TABLE IF EXISTS `GameInstance`;
CREATE TABLE IF NOT EXISTS `GameInstance` (
  `idGameInstance` int(11) NOT NULL AUTO_INCREMENT,
  `idGame` int(11) NOT NULL,
  `state` longtext,
  PRIMARY KEY (`idGameInstance`),
  KEY `GameInstance_Game` (`idGame`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `GameDetail`
--

DROP TABLE IF EXISTS `GameDetail`;
CREATE TABLE IF NOT EXISTS `GameDetail` (
  `idGameDetail` int(11) NOT NULL AUTO_INCREMENT,
  `gameId` int(11) NOT NULL,
  `description` text,
  `logo` varchar(150) DEFAULT NULL,
  `banner` varchar(150) DEFAULT NULL,
  `videourl` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`idGameDetail`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `GameImage`
--

DROP TABLE IF EXISTS `GameImage`;
CREATE TABLE IF NOT EXISTS `GameImage` (
  `idGameImage` int(11) NOT NULL AUTO_INCREMENT,
  `gameId` int(11) NOT NULL,
  `image` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`idGameImage`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `GameLike`
--

DROP TABLE IF EXISTS `GameLike`;
CREATE TABLE IF NOT EXISTS `GameLike` (
  `likeId` int(11) NOT NULL AUTO_INCREMENT,
  `gameId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `containerId` int(11) NOT NULL,
  PRIMARY KEY (`likeId`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=30 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `GameScore`
--

DROP TABLE IF EXISTS `GameScore`;
CREATE TABLE IF NOT EXISTS `GameScore` (
  `idScore` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `idGame` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  PRIMARY KEY (`idScore`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Group`
--

DROP TABLE IF EXISTS `Group`;
CREATE TABLE IF NOT EXISTS `Group` (
  `idGroup` int(11) NOT NULL,
  `description` longtext,
  `idGroupParent` int(11) DEFAULT NULL,
  PRIMARY KEY (`idGroup`),
  KEY `GROUP_GROUP` (`idGroupParent`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `MetaData`
--

DROP TABLE IF EXISTS `MetaData`;
CREATE TABLE IF NOT EXISTS `MetaData` (
  `idMetadata` int(11) NOT NULL AUTO_INCREMENT,
  `idGame` int(11) NOT NULL,
  `keyMeta` varchar(255) NOT NULL,
  `valueMeta` varchar(255) NOT NULL,
  PRIMARY KEY (`idMetadata`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Platform`
--

DROP TABLE IF EXISTS `Platform`;
CREATE TABLE IF NOT EXISTS `Platform` (
  `idPlatform` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`idPlatform`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `name_2` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Volcado de datos para la tabla `Platform`
--

INSERT INTO `Platform` (`idPlatform`, `name`) VALUES
(2, 'Android'),
(3, 'iOS'),
(4, 'Nintendo DS'),
(6, 'Nintendo Wii'),
(5, 'PSP'),
(1, 'Web'),
(7, 'XBox');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Realm`
--

DROP TABLE IF EXISTS `Realm`;
CREATE TABLE IF NOT EXISTS `Realm` (
  `idRealm` int(11) NOT NULL AUTO_INCREMENT,
  `alias` char(15) NOT NULL,
  PRIMARY KEY (`idRealm`),
  UNIQUE KEY `alias_UNIQUE` (`alias`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Session`
--

DROP TABLE IF EXISTS `Session`;
CREATE TABLE IF NOT EXISTS `Session` (
  `idSession` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `startTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `campusSession` varchar(250) NOT NULL,
  PRIMARY KEY (`idSession`),
  KEY `Session_User` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Skill`
--

DROP TABLE IF EXISTS `Skill`;
CREATE TABLE IF NOT EXISTS `Skill` (
  `idSkill` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`idSkill`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `name_2` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `Skill`
--

INSERT INTO `Skill` (`idSkill`, `name`) VALUES
(1, 'Skill 1'),
(2, 'Skill 2'),
(3, 'Skill 3'),
(4, 'Skill 4'),
(5, 'skill 5');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Tag`
--

DROP TABLE IF EXISTS `Tag`;
CREATE TABLE IF NOT EXISTS `Tag` (
  `idTag` int(11) NOT NULL AUTO_INCREMENT,
  `idGame` int(11) NOT NULL,
  `tag` varchar(255) NOT NULL,
  PRIMARY KEY (`idTag`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `User`
--

DROP TABLE IF EXISTS `User`;
CREATE TABLE IF NOT EXISTS `User` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `secret` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `UserGameInstance`
--

DROP TABLE IF EXISTS `UserGameInstance`;
CREATE TABLE IF NOT EXISTS `UserGameInstance` (
  `idUser` int(11) NOT NULL,
  `idGameInstance` int(11) NOT NULL,
  PRIMARY KEY (`idUser`,`idGameInstance`),
  KEY `UserGameInstance_User` (`idUser`),
  KEY `UserGameInstance_GameInstance` (`idGameInstance`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `UserGroup`
--

DROP TABLE IF EXISTS `UserGroup`;
CREATE TABLE IF NOT EXISTS `UserGroup` (
  `idUser` int(11) NOT NULL,
  `idGroup` int(11) NOT NULL,
  PRIMARY KEY (`idUser`,`idGroup`),
  KEY `UserGroup_User` (`idUser`),
  KEY `UserGroup_Group` (`idGroup`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `UserRealm`
--

DROP TABLE IF EXISTS `UserRealm`;
CREATE TABLE IF NOT EXISTS `UserRealm` (
  `idUser` int(11) NOT NULL,
  `idRealm` int(11) NOT NULL,
  PRIMARY KEY (`idUser`,`idRealm`),
  KEY `UserRealm_User` (`idUser`),
  KEY `UserRealm_Realm` (`idRealm`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura para la vista `GameSimilitudeView`
--

DROP VIEW IF EXISTS `GameSimilitudeView`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `GameSimilitudeView` AS select 
`tag1`.`idGame` AS `idGame`,
`tag2`.`idGame` AS `similarToIdGame`,
`tag1`.`tag` AS `similitude` 
from
	(`Tag` `tag1` join `Tag` `tag2` on(((`tag1`.`tag` = `tag2`.`tag`) and (`tag1`.`idGame` <> `tag2`.`idGame`)))) union select 
		`game1`.`idGame` AS `idGame`,
		`game2`.`idGame` AS `similarToIdGame`,
		`Category`.`name` AS `similitude` 
		from 
			((`Game` `game1` join `Game` `game2` 
				on(((`game1`.`idCategory` = `game2`.`idCategory`) 
					and (`game1`.`idGame` <> `game2`.`idGame`)))) 
			join `Category` on((`game1`.`idCategory` = `Category`.`idCategory`))) 
		order by `idGame`,`similarToIdGame`;

-- --------------------------------------------------------

--
-- Estructura para la vista `GameView`
--

DROP VIEW IF EXISTS `GameView`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `GameView` AS select
`Game`.`idGame` AS `idGame`,
`Game`.`name` AS `name`,
`Game`.`grantPublicAccess` AS `grantPublicAccess`,
`Game`.`secretGame` AS `secretGame`,
`Game`.`privateKey` AS `privateKey`,
`Game`.`idCategory` AS `idCategory`,
`Game`.`creationDate` AS `creationDate`,
(select count(`Comment`.`idComment`) from `Comment` where (`Comment`.`idGame` = `Game`.`idGame`)) AS `timesCommented`,
(select count(`GameLike`.`likeId`) from `GameLike` where (`GameLike`.`gameId` = `Game`.`idGame`)) AS `popularity`,
(select count(`GameScore`.`idScore`) from `GameScore` where (`GameScore`.`idGame` = `Game`.`idGame`)) AS `timesPlayed`,
(select count(`GameInstance`.`idGameInstance`) from `GameInstance` where ((`GameInstance`.`idGame` = `Game`.`idGame`) and (`GameInstance`.`state` = 'INIT'))) AS `activity` from `Game`;

-- --------------------------------------------------------

--
-- Estructura para la vista `TotalGameSimilitudeView`
--

DROP VIEW IF EXISTS `TotalGameSimilitudeView`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `TotalGameSimilitudeView` AS select 
`GameSimilitudeView`.`idGame` AS `idGame`,
`GameSimilitudeView`.`similarToIdGame` AS `similarToIdGame`,
count(0) AS `totalSimilitude`
from `GameSimilitudeView` group by `GameSimilitudeView`.`idGame`,`GameSimilitudeView`.`similarToIdGame`;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
