-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.32-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema gestionclubes
--

CREATE DATABASE IF NOT EXISTS gestionclubes;
USE gestionclubes;

--
-- Definition of table `accion`
--

DROP TABLE IF EXISTS `accion`;
CREATE TABLE `accion` (
  `ACCION_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ACCION_NOMBRE` varchar(45) NOT NULL,
  `ACCION_FECHAINICIO` date NOT NULL,
  `ACCION_FECHAFIN` datetime NOT NULL,
  `ACCION_SOCIO_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ACCION_ID`),
  KEY `FK_ACCION_1` (`ACCION_SOCIO_ID`),
  CONSTRAINT `FK_ACCION_1` FOREIGN KEY (`ACCION_SOCIO_ID`) REFERENCES `socio` (`SOCIO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accion`
--

/*!40000 ALTER TABLE `accion` DISABLE KEYS */;
INSERT INTO `accion` (`ACCION_ID`,`ACCION_NOMBRE`,`ACCION_FECHAINICIO`,`ACCION_FECHAFIN`,`ACCION_SOCIO_ID`) VALUES 
 (1,'Reservar pista','2011-12-09','2011-12-12 00:00:00',1),
 (2,'Reservar piscina','2011-12-10','2011-12-25 00:00:00',1),
 (3,'Reservar Cancha','2011-06-08','2011-06-07 00:00:00',4),
 (4,'Reservar','2011-06-08','2011-06-08 00:00:00',4);
/*!40000 ALTER TABLE `accion` ENABLE KEYS */;


--
-- Definition of table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE `categoria` (
  `CATEGORIA_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CATEGORIA_NOMBRE` varchar(45) NOT NULL,
  `CATEGORIA_SUBCATEGORIA` varchar(45) NOT NULL,
  PRIMARY KEY (`CATEGORIA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categoria`
--

/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` (`CATEGORIA_ID`,`CATEGORIA_NOMBRE`,`CATEGORIA_SUBCATEGORIA`) VALUES 
 (1,'Categoria 1','SubCategoria 1'),
 (2,'Categoria 2','SubCategoria 1'),
 (4,'Categoria 1','SubCategoria 2');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;


--
-- Definition of table `foto`
--

DROP TABLE IF EXISTS `foto`;
CREATE TABLE `foto` (
  `FOTO_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FOTO_NOMBRE` varchar(45) NOT NULL,
  `FOTO_URL` varchar(45) NOT NULL,
  `FOTO_COMENTARIO` varchar(45) NOT NULL,
  PRIMARY KEY (`FOTO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `foto`
--

/*!40000 ALTER TABLE `foto` DISABLE KEYS */;
INSERT INTO `foto` (`FOTO_ID`,`FOTO_NOMBRE`,`FOTO_URL`,`FOTO_COMENTARIO`) VALUES 
 (1,'Foto Julen','banner.jpg','Comentario Julen'),
 (2,'Foto Julen 2','boby.jpg','Comentario fotoJulen 2'),
 (3,'Foto Julen 2','bottomMenu.gif','Comentario fotoJulen 2'),
 (4,'Foto julen 2','bottomMenu.gif','Comentario fotojulen 2'),
 (5,'Foto Julen 3','boxtop.png','Comentario foto Julen 3'),
 (6,'Foto ','','Comentario foto '),
 (7,'Foto ','','Comentario foto '),
 (8,'Foto ','','Comentario foto ');
/*!40000 ALTER TABLE `foto` ENABLE KEYS */;


--
-- Definition of table `instalacion`
--

DROP TABLE IF EXISTS `instalacion`;
CREATE TABLE `instalacion` (
  `INSTALACION_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `INSTALACION_NOMBRE` varchar(45) NOT NULL,
  `INSTALACION_TIPO` int(10) unsigned NOT NULL,
  `INSTALACION_TAR_ADULTO` double NOT NULL,
  `INSTALACION_TAR_MENOR` double NOT NULL,
  PRIMARY KEY (`INSTALACION_ID`),
  KEY `FK_instalacion_1` (`INSTALACION_TIPO`),
  CONSTRAINT `FK_instalacion_1` FOREIGN KEY (`INSTALACION_TIPO`) REFERENCES `tipo_instalacion` (`TIPO_INSTALACION_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `instalacion`
--

/*!40000 ALTER TABLE `instalacion` DISABLE KEYS */;
INSERT INTO `instalacion` (`INSTALACION_ID`,`INSTALACION_NOMBRE`,`INSTALACION_TIPO`,`INSTALACION_TAR_ADULTO`,`INSTALACION_TAR_MENOR`) VALUES 
 (1,'Cancha de Alonsotegi',1,5.5,3.5),
 (2,'Cancha de Bilbao',3,5.3,2.3),
 (3,'Instalacion 3',6,3.3,3.1);
/*!40000 ALTER TABLE `instalacion` ENABLE KEYS */;


--
-- Definition of table `instalacion_horario`
--

DROP TABLE IF EXISTS `instalacion_horario`;
CREATE TABLE `instalacion_horario` (
  `INSTALACION_HORARIO_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `INSTALACION_HORARIO_FINICIO` date NOT NULL,
  `INSTALACION_HORARIO_FFIN` date NOT NULL,
  `INSTALACION_HORARIO_HINICIO` time NOT NULL,
  `INSTALACION_HORARIO_HFIN` time NOT NULL,
  `INSTALACION_HORARIO_OBSERVACIONES` varchar(400) NOT NULL,
  `INSTALACION_HORARIO_INSTALACION_ID` int(10) unsigned NOT NULL,
  `INSTALACION_HORARIO_LUNES` tinyint(1) NOT NULL,
  `INSTALACION_HORARIO_MARTES` tinyint(1) NOT NULL,
  `INSTALACION_HORARIO_MIERCOLES` tinyint(1) NOT NULL,
  `INSTALACION_HORARIO_JUEVES` tinyint(1) NOT NULL,
  `INSTALACION_HORARIO_VIERNES` tinyint(1) NOT NULL,
  `INSTALACION_HORARIO_SABADO` tinyint(1) NOT NULL,
  `INSTALACION_HORARIO_DOMINGO` tinyint(1) NOT NULL,
  PRIMARY KEY (`INSTALACION_HORARIO_ID`),
  KEY `FK_instalacion_horario_1` (`INSTALACION_HORARIO_INSTALACION_ID`),
  CONSTRAINT `FK_instalacion_horario_1` FOREIGN KEY (`INSTALACION_HORARIO_INSTALACION_ID`) REFERENCES `instalacion` (`INSTALACION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `instalacion_horario`
--

/*!40000 ALTER TABLE `instalacion_horario` DISABLE KEYS */;
INSERT INTO `instalacion_horario` (`INSTALACION_HORARIO_ID`,`INSTALACION_HORARIO_FINICIO`,`INSTALACION_HORARIO_FFIN`,`INSTALACION_HORARIO_HINICIO`,`INSTALACION_HORARIO_HFIN`,`INSTALACION_HORARIO_OBSERVACIONES`,`INSTALACION_HORARIO_INSTALACION_ID`,`INSTALACION_HORARIO_LUNES`,`INSTALACION_HORARIO_MARTES`,`INSTALACION_HORARIO_MIERCOLES`,`INSTALACION_HORARIO_JUEVES`,`INSTALACION_HORARIO_VIERNES`,`INSTALACION_HORARIO_SABADO`,`INSTALACION_HORARIO_DOMINGO`) VALUES 
 (1,'2011-06-12','2011-06-14','16:00:00','18:00:00','observaciones',1,1,1,1,1,1,1,1),
 (2,'2011-06-13','2011-06-15','18:00:00','21:00:00','observaciones 1',1,1,0,1,0,1,0,0),
 (3,'2011-06-02','2011-06-02','13:00:00','09:05:13','miercoles 2',1,0,0,1,1,1,0,0),
 (5,'2011-06-01','2011-06-01','00:00:00','05:05:05','055555',1,1,1,0,0,1,1,0),
 (7,'2011-06-22','2011-06-22','00:00:01','00:00:01','martesssss',1,0,1,1,1,1,1,0),
 (20,'2011-06-15','2011-06-15','00:01:09','01:02:09','martes miercoles',3,0,1,1,0,0,0,0);
/*!40000 ALTER TABLE `instalacion_horario` ENABLE KEYS */;


--
-- Definition of table `invitacion`
--

DROP TABLE IF EXISTS `invitacion`;
CREATE TABLE `invitacion` (
  `INVITACION_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `INVITACION_SOCIO_ID` int(10) unsigned NOT NULL,
  `INVITACION_FECHA` date NOT NULL,
  `INVITACION_INVITADO` varchar(45) NOT NULL,
  `INVITACION_CANTIDAD` int(10) unsigned NOT NULL,
  `INVITACION_IMPORTE` double NOT NULL,
  `INVITACION_PAGO_ID` int(10) unsigned NOT NULL,
  `INVITACION_NOMBRE` varchar(45) NOT NULL,
  `INVITACION_APELLIDOS` varchar(45) NOT NULL,
  PRIMARY KEY (`INVITACION_ID`),
  KEY `FK_INVITACION_2` (`INVITACION_SOCIO_ID`),
  KEY `FK_INVITACION_1` (`INVITACION_PAGO_ID`) USING BTREE,
  CONSTRAINT `FK_invitacion_1` FOREIGN KEY (`INVITACION_PAGO_ID`) REFERENCES `tipo_pago` (`TIPO_PAGO_ID`),
  CONSTRAINT `FK_INVITACION_2` FOREIGN KEY (`INVITACION_SOCIO_ID`) REFERENCES `socio` (`SOCIO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invitacion`
--

/*!40000 ALTER TABLE `invitacion` DISABLE KEYS */;
INSERT INTO `invitacion` (`INVITACION_ID`,`INVITACION_SOCIO_ID`,`INVITACION_FECHA`,`INVITACION_INVITADO`,`INVITACION_CANTIDAD`,`INVITACION_IMPORTE`,`INVITACION_PAGO_ID`,`INVITACION_NOMBRE`,`INVITACION_APELLIDOS`) VALUES 
 (1,1,'2011-06-15','Bittor Asteinza',2,3.5,6,'Julen','Acerete Atxalandabaso'),
 (2,1,'2011-06-15','Bittor',2,2.5,9,'Julen','Acerete Atxalandabaso'),
 (3,1,'2011-06-15','Asteinza',2,3.9,6,'Julen','Acerete Atxalandabaso'),
 (4,1,'2011-06-15','Alguien',5,5,6,'Julen','Acerete Atxalandabaso'),
 (5,1,'2011-06-16','Kepa',2,3.5,6,'Julen','Acerete Atxalandabaso');
/*!40000 ALTER TABLE `invitacion` ENABLE KEYS */;


--
-- Definition of table `mensajes`
--

DROP TABLE IF EXISTS `mensajes`;
CREATE TABLE `mensajes` (
  `MENSAJES_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `MENSAJES_FECHA` date NOT NULL,
  `MENSAJES_ID_EMISOR` int(10) unsigned NOT NULL,
  `MENSAJES_ID_DESTINATARIO` int(10) unsigned NOT NULL,
  `MENSAJES_ASUNTO` varchar(200) NOT NULL,
  `MENSAJES_TEXTO` varchar(2000) NOT NULL,
  PRIMARY KEY (`MENSAJES_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mensajes`
--

/*!40000 ALTER TABLE `mensajes` DISABLE KEYS */;
INSERT INTO `mensajes` (`MENSAJES_ID`,`MENSAJES_FECHA`,`MENSAJES_ID_EMISOR`,`MENSAJES_ID_DESTINATARIO`,`MENSAJES_ASUNTO`,`MENSAJES_TEXTO`) VALUES 
 (1,'2011-06-09',3,2,'Asunto 1','Texto Texto Texto Texto Texto Texto'),
 (2,'2011-06-10',1,2,'Asunto 2','Texto'),
 (3,'2011-06-09',2,2,'Alta','Textoooo'),
 (4,'2011-06-09',2,2,'Alta','Textoooo'),
 (5,'2011-06-09',2,2,'Asuntoooo 2','Textooooo 2'),
 (6,'2011-06-09',2,2,'Asuntoooo 3','Textooo 3'),
 (7,'2011-06-09',2,3,'Asuntoooo 3','Textooo 3'),
 (8,'2011-06-09',2,2,'asunto','texto'),
 (9,'2011-06-09',2,2,'aaassunnnnttooo','aaassunnnnttooo'),
 (14,'2011-06-09',2,2,'qqq','qqq'),
 (15,'2011-06-09',2,3,'qqq','qqq'),
 (16,'2011-06-28',8,8,'asunto 1','mensaje 1'),
 (17,'2011-06-28',2,8,'asunto 1','mensaje 1'),
 (18,'2011-06-28',2,1,'Asunto 3','Mensaje 3'),
 (19,'2011-06-28',2,1,'Asunto 3','Mensaje 3');
/*!40000 ALTER TABLE `mensajes` ENABLE KEYS */;


--
-- Definition of table `producto`
--

DROP TABLE IF EXISTS `producto`;
CREATE TABLE `producto` (
  `PRODUCTO_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `PRODUCTO_CATEGORIA_ID` int(10) unsigned NOT NULL,
  `PRODUCTO_NOMBRE` varchar(45) NOT NULL,
  `PRODUCTO_PRECIO` double NOT NULL,
  `PRODUCTO_CATEGORIA_NOMBRE` varchar(45) NOT NULL,
  `PRODUCTO_CATEGORIA_SUBCATEGORIA` varchar(45) NOT NULL,
  PRIMARY KEY (`PRODUCTO_ID`),
  KEY `FK_producto_1` (`PRODUCTO_CATEGORIA_ID`),
  CONSTRAINT `FK_producto_1` FOREIGN KEY (`PRODUCTO_CATEGORIA_ID`) REFERENCES `categoria` (`CATEGORIA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `producto`
--

/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` (`PRODUCTO_ID`,`PRODUCTO_CATEGORIA_ID`,`PRODUCTO_NOMBRE`,`PRODUCTO_PRECIO`,`PRODUCTO_CATEGORIA_NOMBRE`,`PRODUCTO_CATEGORIA_SUBCATEGORIA`) VALUES 
 (2,2,'Palos 2',7,'Categoria 2','SubCategoria 1'),
 (3,1,'Palos 8',9,'Categoria 1','SubCategoria 1'),
 (4,4,'Gorra',3.3,'Categoria 1','SubCategoria 2'),
 (6,1,'Palos 9',5,'Categoria 1','SubCategoria 1'),
 (7,1,'Gorra',8.5,'Categoria 1','SubCategoria 1'),
 (9,1,'Gorra 5',8.5,'Categoria 1','SubCategoria 1');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;


--
-- Definition of table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
CREATE TABLE `reserva` (
  `RESERVA_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `RESERVA_INSTALACION_ID` int(10) unsigned NOT NULL,
  `RESERVA_FECHA` date NOT NULL,
  `RESERVA_HORA_INICIO` time NOT NULL,
  `RESERVA_HORA_FIN` time NOT NULL,
  `RESERVA_SOCIO_ID` int(10) unsigned NOT NULL,
  `RESERVA_IMPORTE` double NOT NULL,
  `RESERVA_ESTADO_ID` int(10) unsigned NOT NULL,
  `RESERVA_FORMA_PAGO_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`RESERVA_ID`),
  KEY `FK_reserva_1` (`RESERVA_INSTALACION_ID`),
  KEY `FK_reserva_2` (`RESERVA_SOCIO_ID`),
  KEY `FK_reserva_3` (`RESERVA_FORMA_PAGO_ID`),
  KEY `FK_reserva_4` (`RESERVA_ESTADO_ID`),
  CONSTRAINT `FK_reserva_1` FOREIGN KEY (`RESERVA_INSTALACION_ID`) REFERENCES `instalacion` (`INSTALACION_ID`),
  CONSTRAINT `FK_reserva_2` FOREIGN KEY (`RESERVA_SOCIO_ID`) REFERENCES `socio` (`SOCIO_ID`),
  CONSTRAINT `FK_reserva_3` FOREIGN KEY (`RESERVA_FORMA_PAGO_ID`) REFERENCES `tipo_pago` (`TIPO_PAGO_ID`),
  CONSTRAINT `FK_reserva_4` FOREIGN KEY (`RESERVA_ESTADO_ID`) REFERENCES `tipo_reserva` (`TIPO_RESERVA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reserva`
--

/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
INSERT INTO `reserva` (`RESERVA_ID`,`RESERVA_INSTALACION_ID`,`RESERVA_FECHA`,`RESERVA_HORA_INICIO`,`RESERVA_HORA_FIN`,`RESERVA_SOCIO_ID`,`RESERVA_IMPORTE`,`RESERVA_ESTADO_ID`,`RESERVA_FORMA_PAGO_ID`) VALUES 
 (3,1,'2011-06-14','09:05:02','05:02:04',1,5.5,1,7),
 (5,3,'2011-07-01','09:00:30','05:02:04',1,3.3,7,10);
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;


--
-- Definition of table `socio`
--

DROP TABLE IF EXISTS `socio`;
CREATE TABLE `socio` (
  `SOCIO_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `SOCIO_NUM_SOCIO` varchar(45) NOT NULL,
  `SOCIO_DNI` varchar(45) NOT NULL,
  `SOCIO_NOMBRE` varchar(45) NOT NULL,
  `SOCIO_APELLIDOS` varchar(45) NOT NULL,
  `SOCIO_SEXO` varchar(45) NOT NULL,
  `SOCIO_FECHANAC` date NOT NULL,
  `SOCIO_PROFESION` varchar(45) NOT NULL,
  `SOCIO_ANTIGUEDAD` varchar(45) NOT NULL,
  `SOCIO_IDFOTO` int(10) unsigned NOT NULL,
  `SOCIO_OBSERVACIONES` varchar(45) NOT NULL,
  `SOCIO_TIPO` int(10) unsigned NOT NULL,
  `SOCIO_MARTUBERRI` tinyint(1) NOT NULL,
  `SOCIO_DIRECCION` varchar(45) NOT NULL,
  `SOCIO_CP` varchar(5) NOT NULL,
  `SOCIO_POBLACION` varchar(45) NOT NULL,
  `SOCIO_PROVINCIA` varchar(45) NOT NULL,
  `SOCIO_TELEFONO1` varchar(11) NOT NULL,
  `SOCIO_TELEFONO2` varchar(11) NOT NULL,
  `SOCIO_FAX` varchar(45) NOT NULL,
  `SOCIO_EMAIL` varchar(45) NOT NULL,
  `SOCIO_BANCO` varchar(45) NOT NULL,
  `SOCIO_NUMCUENTA` varchar(45) NOT NULL,
  `SOCIO_IDUSUARIO` int(10) unsigned NOT NULL,
  `SOCIO_FECHAALTA` date NOT NULL,
  PRIMARY KEY (`SOCIO_ID`),
  KEY `FK_socio_1` (`SOCIO_IDFOTO`),
  KEY `FK_socio_2` (`SOCIO_IDUSUARIO`),
  KEY `FK_socio_3` (`SOCIO_TIPO`),
  CONSTRAINT `FK_socio_1` FOREIGN KEY (`SOCIO_IDFOTO`) REFERENCES `foto` (`FOTO_ID`),
  CONSTRAINT `FK_socio_2` FOREIGN KEY (`SOCIO_IDUSUARIO`) REFERENCES `usuario` (`USUARIO_ID`),
  CONSTRAINT `FK_socio_3` FOREIGN KEY (`SOCIO_TIPO`) REFERENCES `tipo_socio` (`TIPO_SOCIO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `socio`
--

/*!40000 ALTER TABLE `socio` DISABLE KEYS */;
INSERT INTO `socio` (`SOCIO_ID`,`SOCIO_NUM_SOCIO`,`SOCIO_DNI`,`SOCIO_NOMBRE`,`SOCIO_APELLIDOS`,`SOCIO_SEXO`,`SOCIO_FECHANAC`,`SOCIO_PROFESION`,`SOCIO_ANTIGUEDAD`,`SOCIO_IDFOTO`,`SOCIO_OBSERVACIONES`,`SOCIO_TIPO`,`SOCIO_MARTUBERRI`,`SOCIO_DIRECCION`,`SOCIO_CP`,`SOCIO_POBLACION`,`SOCIO_PROVINCIA`,`SOCIO_TELEFONO1`,`SOCIO_TELEFONO2`,`SOCIO_FAX`,`SOCIO_EMAIL`,`SOCIO_BANCO`,`SOCIO_NUMCUENTA`,`SOCIO_IDUSUARIO`,`SOCIO_FECHAALTA`) VALUES 
 (1,'1','78874968X','Julen','Acerete Atxalandabaso','Varon','1981-07-08','Informatico','2003',1,'qqqqqq',2,1,'Erdiko 11','48810','Alonsotegi','Bizkaia','111111111','222222222','951874632','julen@yahoo.es','BBK','9874563210',1,'2011-07-14'),
 (2,'2','2','Bittor','Asteinza','Varon','2011-06-27','Informatico','2008',1,'aaaaaa',3,1,'q','q','Bermeo','Bizkaia','1','2','3','4','BBK','7',1,'2011-06-27'),
 (4,'numSocio 3','dni 3','Julen 3','Acerete 3','Varon','2011-06-01','profesion 3','1980',5,'observaciones 3',3,1,'direccion 3','cp 3','poblacion 3','provincia 3','telefono1 3','telefono2 3','fax 3','email 3','banco 3','numcuenta 3',8,'2011-06-01');
/*!40000 ALTER TABLE `socio` ENABLE KEYS */;


--
-- Definition of table `socio_familia`
--

DROP TABLE IF EXISTS `socio_familia`;
CREATE TABLE `socio_familia` (
  `SOCIO_FAMILIA_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `SOCIO_FAMILIA_SOCIO_ID` int(10) unsigned NOT NULL,
  `SOCIO_FAMILIA_FAMILIAR_ID` int(10) unsigned NOT NULL,
  `SOCIO_FAMILIA_TIPO_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`SOCIO_FAMILIA_ID`),
  KEY `FK_socio_familia_1` (`SOCIO_FAMILIA_SOCIO_ID`),
  KEY `FK_socio_familia_2` (`SOCIO_FAMILIA_FAMILIAR_ID`),
  KEY `FK_socio_familia_3` (`SOCIO_FAMILIA_TIPO_ID`),
  CONSTRAINT `FK_socio_familia_1` FOREIGN KEY (`SOCIO_FAMILIA_SOCIO_ID`) REFERENCES `socio` (`SOCIO_ID`),
  CONSTRAINT `FK_socio_familia_2` FOREIGN KEY (`SOCIO_FAMILIA_FAMILIAR_ID`) REFERENCES `socio` (`SOCIO_ID`),
  CONSTRAINT `FK_socio_familia_3` FOREIGN KEY (`SOCIO_FAMILIA_TIPO_ID`) REFERENCES `tipo_familiar` (`TIPO_FAMILIAR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `socio_familia`
--

/*!40000 ALTER TABLE `socio_familia` DISABLE KEYS */;
INSERT INTO `socio_familia` (`SOCIO_FAMILIA_ID`,`SOCIO_FAMILIA_SOCIO_ID`,`SOCIO_FAMILIA_FAMILIAR_ID`,`SOCIO_FAMILIA_TIPO_ID`) VALUES 
 (1,1,2,2),
 (2,4,1,1),
 (3,4,1,2),
 (4,4,1,1);
/*!40000 ALTER TABLE `socio_familia` ENABLE KEYS */;


--
-- Definition of table `tipo_convenio`
--

DROP TABLE IF EXISTS `tipo_convenio`;
CREATE TABLE `tipo_convenio` (
  `TIPO_CONVENIO_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TIPO_CONVENIO_NOMBRE` varchar(45) NOT NULL,
  PRIMARY KEY (`TIPO_CONVENIO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipo_convenio`
--

/*!40000 ALTER TABLE `tipo_convenio` DISABLE KEYS */;
INSERT INTO `tipo_convenio` (`TIPO_CONVENIO_ID`,`TIPO_CONVENIO_NOMBRE`) VALUES 
 (1,'UPV'),
 (2,'Jolaseta');
/*!40000 ALTER TABLE `tipo_convenio` ENABLE KEYS */;


--
-- Definition of table `tipo_familiar`
--

DROP TABLE IF EXISTS `tipo_familiar`;
CREATE TABLE `tipo_familiar` (
  `TIPO_FAMILIAR_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TIPO_FAMILIAR_NOMBRE` varchar(45) NOT NULL,
  PRIMARY KEY (`TIPO_FAMILIAR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipo_familiar`
--

/*!40000 ALTER TABLE `tipo_familiar` DISABLE KEYS */;
INSERT INTO `tipo_familiar` (`TIPO_FAMILIAR_ID`,`TIPO_FAMILIAR_NOMBRE`) VALUES 
 (1,'Hijo/a'),
 (2,'Otros parientes 2');
/*!40000 ALTER TABLE `tipo_familiar` ENABLE KEYS */;


--
-- Definition of table `tipo_instalacion`
--

DROP TABLE IF EXISTS `tipo_instalacion`;
CREATE TABLE `tipo_instalacion` (
  `TIPO_INSTALACION_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TIPO_INSTALACION_NOMBRE` varchar(45) NOT NULL,
  PRIMARY KEY (`TIPO_INSTALACION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipo_instalacion`
--

/*!40000 ALTER TABLE `tipo_instalacion` DISABLE KEYS */;
INSERT INTO `tipo_instalacion` (`TIPO_INSTALACION_ID`,`TIPO_INSTALACION_NOMBRE`) VALUES 
 (1,'Cancha Padel'),
 (2,'Cancha Tenis'),
 (3,'Cancha Padel 2'),
 (6,'Cancha Badminton 2'),
 (11,'Cancha 3'),
 (12,'Cancha 2'),
 (15,'Cancha 5');
/*!40000 ALTER TABLE `tipo_instalacion` ENABLE KEYS */;


--
-- Definition of table `tipo_invitacion`
--

DROP TABLE IF EXISTS `tipo_invitacion`;
CREATE TABLE `tipo_invitacion` (
  `TIPO_INVITACION_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TIPO_INV_ADUL_LAB` double DEFAULT NULL,
  `TIPO_INV_ADUL_FEST` double DEFAULT NULL,
  `TIPO_INV_MEN_LAB` double DEFAULT NULL,
  `TIPO_INV_MEN_FEST` double DEFAULT NULL,
  `TIPO_INV_MAXVISITAS` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`TIPO_INVITACION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipo_invitacion`
--

/*!40000 ALTER TABLE `tipo_invitacion` DISABLE KEYS */;
INSERT INTO `tipo_invitacion` (`TIPO_INVITACION_ID`,`TIPO_INV_ADUL_LAB`,`TIPO_INV_ADUL_FEST`,`TIPO_INV_MEN_LAB`,`TIPO_INV_MEN_FEST`,`TIPO_INV_MAXVISITAS`) VALUES 
 (1,3,2.5,0,5,20);
/*!40000 ALTER TABLE `tipo_invitacion` ENABLE KEYS */;


--
-- Definition of table `tipo_pago`
--

DROP TABLE IF EXISTS `tipo_pago`;
CREATE TABLE `tipo_pago` (
  `TIPO_PAGO_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TIPO_PAGO_MODO` varchar(45) NOT NULL,
  PRIMARY KEY (`TIPO_PAGO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipo_pago`
--

/*!40000 ALTER TABLE `tipo_pago` DISABLE KEYS */;
INSERT INTO `tipo_pago` (`TIPO_PAGO_ID`,`TIPO_PAGO_MODO`) VALUES 
 (6,'Efectivo'),
 (7,'Cheque'),
 (8,'Cheque 2'),
 (9,'cheque 35'),
 (10,'cheque 28');
/*!40000 ALTER TABLE `tipo_pago` ENABLE KEYS */;


--
-- Definition of table `tipo_reserva`
--

DROP TABLE IF EXISTS `tipo_reserva`;
CREATE TABLE `tipo_reserva` (
  `TIPO_RESERVA_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TIPO_RESERVA_ESTADO` varchar(45) NOT NULL,
  `TIPO_RESERVA_NORMAS` varchar(45) NOT NULL,
  PRIMARY KEY (`TIPO_RESERVA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipo_reserva`
--

/*!40000 ALTER TABLE `tipo_reserva` DISABLE KEYS */;
INSERT INTO `tipo_reserva` (`TIPO_RESERVA_ID`,`TIPO_RESERVA_ESTADO`,`TIPO_RESERVA_NORMAS`) VALUES 
 (1,'Pagada','c:\\...'),
 (2,'Pendiente','URVINA-LAYCOVEL.ppt'),
 (5,'Muy pagada','COLOCARCALA.pdf'),
 (7,'Casi Pendiente','DocumentoRequisitosGestiÃ³nClubes.doc'),
 (8,'Pendiente 3','Laycovel.sql');
/*!40000 ALTER TABLE `tipo_reserva` ENABLE KEYS */;


--
-- Definition of table `tipo_socio`
--

DROP TABLE IF EXISTS `tipo_socio`;
CREATE TABLE `tipo_socio` (
  `TIPO_SOCIO_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TIPO_SOCIO_NOMBRE` varchar(45) NOT NULL,
  `TIPO_SOCIO_CUOTA` double NOT NULL,
  PRIMARY KEY (`TIPO_SOCIO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipo_socio`
--

/*!40000 ALTER TABLE `tipo_socio` DISABLE KEYS */;
INSERT INTO `tipo_socio` (`TIPO_SOCIO_ID`,`TIPO_SOCIO_NOMBRE`,`TIPO_SOCIO_CUOTA`) VALUES 
 (1,'Socio Standard',5),
 (2,'Socio Martuberri',3),
 (3,'Martuberri 3',3.5);
/*!40000 ALTER TABLE `tipo_socio` ENABLE KEYS */;


--
-- Definition of table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `USUARIO_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USUARIO_NOMBRE` varchar(45) NOT NULL,
  `USUARIO_PASSWORD` varchar(45) NOT NULL,
  PRIMARY KEY (`USUARIO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`USUARIO_ID`,`USUARIO_NOMBRE`,`USUARIO_PASSWORD`) VALUES 
 (1,'n','p'),
 (2,'Julen','Admin'),
 (3,'Bittor','Bittor'),
 (4,'Bittor','Admin'),
 (5,'Julen 2','Julen 2'),
 (6,'u2','p2'),
 (7,'u2','p2'),
 (8,'Julen 3','Acerete 3');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;


--
-- Definition of table `usuario_convenio`
--

DROP TABLE IF EXISTS `usuario_convenio`;
CREATE TABLE `usuario_convenio` (
  `USUARIO_CONVENIO_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USUARIO_CONVENIO_USU_ID` int(10) unsigned NOT NULL,
  `USUARIO_CONVENIO_TIPCONV_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`USUARIO_CONVENIO_ID`),
  KEY `FK_usuario_convenio_1` (`USUARIO_CONVENIO_USU_ID`),
  KEY `FK_usuario_convenio_2` (`USUARIO_CONVENIO_TIPCONV_ID`),
  CONSTRAINT `FK_usuario_convenio_1` FOREIGN KEY (`USUARIO_CONVENIO_USU_ID`) REFERENCES `usuario` (`USUARIO_ID`),
  CONSTRAINT `FK_usuario_convenio_2` FOREIGN KEY (`USUARIO_CONVENIO_TIPCONV_ID`) REFERENCES `tipo_convenio` (`TIPO_CONVENIO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario_convenio`
--

/*!40000 ALTER TABLE `usuario_convenio` DISABLE KEYS */;
INSERT INTO `usuario_convenio` (`USUARIO_CONVENIO_ID`,`USUARIO_CONVENIO_USU_ID`,`USUARIO_CONVENIO_TIPCONV_ID`) VALUES 
 (1,1,1),
 (2,1,2),
 (7,1,1),
 (8,1,2),
 (9,1,1);
/*!40000 ALTER TABLE `usuario_convenio` ENABLE KEYS */;


--
-- Definition of table `venta`
--

DROP TABLE IF EXISTS `venta`;
CREATE TABLE `venta` (
  `VENTA_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `VENTA_SOCIO_ID` int(10) unsigned NOT NULL,
  `VENTA_FECHA` date NOT NULL,
  `VENTA_PRODUCTO_ID` int(10) unsigned NOT NULL,
  `VENTA_TIPO_PAGO_ID` int(10) unsigned NOT NULL,
  `VENTA_PAGADO` tinyint(1) NOT NULL,
  `VENTA_UNIDADES` int(10) unsigned NOT NULL,
  `VENTA_IMPORTE` double NOT NULL,
  `VENTA_SOCIO_NOMBRE` varchar(45) NOT NULL,
  `VENTA_SOCIO_APELLIDOS` varchar(45) NOT NULL,
  `VENTA_CATEGORIA` varchar(45) NOT NULL,
  `VENTA_SUBCATEGORIA` varchar(45) NOT NULL,
  `VENTA_PRODUCTO_NOMBRE` varchar(45) NOT NULL,
  PRIMARY KEY (`VENTA_ID`),
  KEY `FK_venta_1` (`VENTA_SOCIO_ID`),
  KEY `FK_venta_2` (`VENTA_PRODUCTO_ID`),
  KEY `FK_venta_3` (`VENTA_TIPO_PAGO_ID`),
  CONSTRAINT `FK_venta_1` FOREIGN KEY (`VENTA_SOCIO_ID`) REFERENCES `socio` (`SOCIO_ID`),
  CONSTRAINT `FK_venta_2` FOREIGN KEY (`VENTA_PRODUCTO_ID`) REFERENCES `producto` (`PRODUCTO_ID`),
  CONSTRAINT `FK_venta_3` FOREIGN KEY (`VENTA_TIPO_PAGO_ID`) REFERENCES `tipo_pago` (`TIPO_PAGO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `venta`
--

/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
INSERT INTO `venta` (`VENTA_ID`,`VENTA_SOCIO_ID`,`VENTA_FECHA`,`VENTA_PRODUCTO_ID`,`VENTA_TIPO_PAGO_ID`,`VENTA_PAGADO`,`VENTA_UNIDADES`,`VENTA_IMPORTE`,`VENTA_SOCIO_NOMBRE`,`VENTA_SOCIO_APELLIDOS`,`VENTA_CATEGORIA`,`VENTA_SUBCATEGORIA`,`VENTA_PRODUCTO_NOMBRE`) VALUES 
 (1,1,'2011-06-20',6,6,1,0,0,'Julen','Acerete Atxalandabaso','Categoria 1','SubCategoria 1','Palos 9'),
 (2,1,'2011-06-21',6,6,1,3,9.9,'Julen','Acerete Atxalandabaso','Categoria 1','SubCategoria 1','Palos 9'),
 (3,1,'2011-06-20',4,6,1,1,5.25,'Julen','Acerete Atxalandabaso','Categoria 1','SubCategoria 2','Gorra'),
 (4,1,'2011-06-21',4,6,1,2,5,'Julen','Acerete Atxalandabaso','Categoria 1','SubCategoria 2','Gorra'),
 (5,1,'2011-06-21',4,6,1,3,2.5,'Julen','Acerete Atxalandabaso','Categoria 1','SubCategoria 2','Gorra'),
 (6,1,'2011-06-21',6,9,0,1,1,'Julen','Acerete Atxalandabaso','Categoria 1','SubCategoria 1','Palos 9'),
 (7,1,'2011-06-22',3,7,1,7,25,'Julen','Acerete Atxalandabaso','Categoria 1','SubCategoria 1','Palos 8');
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;


--
-- Definition of table `visita`
--

DROP TABLE IF EXISTS `visita`;
CREATE TABLE `visita` (
  `VISITA_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `VISITA_NOMBRE` varchar(45) NOT NULL,
  `VISITA_APELLIDOS` varchar(45) NOT NULL,
  `VISITA_TELEFONO` varchar(45) NOT NULL,
  `VISITA_DIRECCION` varchar(45) NOT NULL,
  `VISITA_POBLACION` varchar(45) NOT NULL,
  `VISITA_ATENDIDO` varchar(45) NOT NULL,
  `VISITA_FECHA` date NOT NULL,
  PRIMARY KEY (`VISITA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `visita`
--

/*!40000 ALTER TABLE `visita` DISABLE KEYS */;
INSERT INTO `visita` (`VISITA_ID`,`VISITA_NOMBRE`,`VISITA_APELLIDOS`,`VISITA_TELEFONO`,`VISITA_DIRECCION`,`VISITA_POBLACION`,`VISITA_ATENDIDO`,`VISITA_FECHA`) VALUES 
 (1,'Julen','Acerete Atxalandabaso','963258741','Erdiko 11','Alonsotegi','Kepa','2011-06-16'),
 (2,'Bittor','Asteinza','987456321','Bermeo 1','Bermeo','Kepa','2011-06-15'),
 (5,'Julen','Atxalandabaso','965874123','Erdiko','Alonsotegi','Iosu','2011-06-26');
/*!40000 ALTER TABLE `visita` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
