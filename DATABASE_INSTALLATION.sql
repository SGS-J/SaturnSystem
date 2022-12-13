-- 8.0.29 MySQL Community Server - GPL
--
-- Base de datos principal para el sistema Saturn
--
-- Host: Localhost   DB: SaturnSystemDB

-- ------------------------------------------------------------

--
-- Creación de base de datos
--

DROP DATABASE IF EXISTS SaturnSystemDB;
CREATE DATABASE SaturnSystemDB;
USE SaturnSystemDB;


--
-- Creación tabla empleados
--

CREATE TABLE `SaturnSystemDB`.`empleado` (
  `EmpleadoID` BIGINT(64) NOT NULL AUTO_INCREMENT,
  `EmpleadoNombre` VARCHAR(100) NOT NULL,
	`Contraseña` VARCHAR(45) NOT NULL,
  `Permisos` VARCHAR(10) NOT NULL,
  `Cargo` VARCHAR(45) NULL,
  `EmpleadoUUID` VARCHAR(36) NOT NULL,
  `ContraseñaSalt` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`EmpleadoID`),
  UNIQUE INDEX `EmpleadoUUID_UNIQUE` (`EmpleadoUUID` ASC) VISIBLE
)AUTO_INCREMENT=1000;


--
-- Triggers
--

-- UUID trigger

DROP TRIGGER IF EXISTS `SaturnSystemDB`.`empleado_BEFORE_INSERT`;

DELIMITER $$
USE `SaturnSystemDB`$$
CREATE DEFINER = CURRENT_USER TRIGGER `SaturnSystemDB`.`empleado_BEFORE_INSERT` BEFORE INSERT ON `empleado` FOR EACH ROW
BEGIN

SET NEW.EmpleadoUUID = UUID();

END$$
DELIMITER ;


-- Admin check triggers


DROP TRIGGER IF EXISTS `SaturnSystemDB`.`empleado_BEFORE_UPDATE`;

DELIMITER $$
USE `SaturnSystemDB`$$
CREATE DEFINER = CURRENT_USER TRIGGER `SaturnSystemDB`.`empleado_BEFORE_UPDATE` BEFORE UPDATE ON `empleado` FOR EACH ROW
BEGIN

SET @adminExists = (SELECT count(*) FROM Empleado WHERE Permisos="admin");

IF @adminExists = 1 AND OLD.Permisos = "admin" AND NEW.Permisos <> "admin" THEN
SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "No se puede actualizar los permisos del usuario administrador si solo hay uno activo";
END IF;

END$$
DELIMITER ;

-- ------------------

DROP TRIGGER IF EXISTS `SaturnSystemDB`.`empleado_BEFORE_DELETE`;

DELIMITER $$
USE `SaturnSystemDB`$$
CREATE DEFINER = CURRENT_USER TRIGGER `SaturnSystemDB`.`empleado_BEFORE_DELETE` BEFORE DELETE ON `empleado` FOR EACH ROW
BEGIN

DECLARE admin_count INT;
DECLARE admin_id INT;

SET admin_count = (SELECT count(*) FROM empleado WHERE Permisos="admin");

IF admin_count = 1 THEN
SET admin_id = (SELECT EmpleadoID FROM empleado WHERE Permisos="admin");

	IF admin_id = OLD.EmpleadoID THEN
	SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "No se puede borrar si solo hay un administrador";
	END IF;
END IF;

END$$
DELIMITER ;


--
-- Creación tabla vacantes
--

CREATE TABLE `saturnsystemdb`.`vacante` (
  `VacanteID` BIGINT(64) NOT NULL AUTO_INCREMENT,
  `VacanteNombre` VARCHAR(100) NOT NULL,
  `Oferta` INT NULL,
  `VacanteUUID` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`VacanteID`),
  UNIQUE INDEX `VacanteNombre_UNIQUE` (`VacanteNombre` ASC) VISIBLE,
  UNIQUE INDEX `VacanteUUID_UNIQUE` (`VacanteUUID` ASC) VISIBLE
) AUTO_INCREMENT = 1000;


--
-- Triggers
--


-- UUID Trigger

DROP TRIGGER IF EXISTS `saturnsystemdb`.`vacante_BEFORE_INSERT`;

DELIMITER $$
USE `saturnsystemdb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `saturnsystemdb`.`vacante_BEFORE_INSERT` BEFORE INSERT ON `vacante` FOR EACH ROW
BEGIN

SET NEW.VacanteUUID = UUID();

END$$
DELIMITER ;



--
-- Creación tabla usuario
--

CREATE TABLE `saturnsystemdb`.`usuario` (
  `UsuarioID` BIGINT(64) NOT NULL,
  `UsuarioNombre` VARCHAR(100) NOT NULL,
  `Correo` VARCHAR(100) NULL,
  `HojaVidaPath` TEXT NOT NULL,
  `Telefonos` JSON NULL,
  `Estado` VARCHAR(15) NOT NULL DEFAULT 'EN_ESPERA',
  `UsuarioVacante` BIGINT(64) NULL,
  `UsuarioUUID` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`UsuarioID`),
  UNIQUE INDEX `Correo_UNIQUE` (`Correo` ASC) VISIBLE,
  UNIQUE INDEX `UsuarioUUID_UNIQUE` (`UsuarioUUID` ASC) VISIBLE,
  INDEX `UsuarioVacante_idx` (`UsuarioVacante` ASC) VISIBLE,
  CONSTRAINT `UsuarioVacante`
    FOREIGN KEY (`UsuarioVacante`)
    REFERENCES `saturnsystemdb`.`vacante` (`VacanteID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
);


--
-- Triggers
--


-- UUID Trigger

DROP TRIGGER IF EXISTS `saturnsystemdb`.`usuario_BEFORE_INSERT`;

DELIMITER $$
USE `saturnsystemdb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `saturnsystemdb`.`usuario_BEFORE_INSERT` BEFORE INSERT ON `usuario` FOR EACH ROW
BEGIN

SET NEW.UsuarioUUID = UUID();

END$$
DELIMITER ;



-- Procedimientos almacenados

-- Crear Empleado

DROP procedure IF EXISTS `SaturnSystemDB`.`crear_empleado`;

DELIMITER $$
USE `SaturnSystemDB`$$
CREATE PROCEDURE `crear_empleado` (IN nombre VARCHAR(100), IN permisos VARCHAR(10), IN cargo VARCHAR(45))
BEGIN

INSERT INTO empleado (EmpleadoNombre, Permisos, Cargo)
VALUES (nombre, permisos, cargo);

END$$

DELIMITER ;


-- Buscar Empleado por nombre

DROP procedure IF EXISTS `SaturnSystemDB`.`buscar_empleado_por_nombre`;

DELIMITER $$
USE `SaturnSystemDB`$$
CREATE PROCEDURE `buscar_empleado_por_nombre` (IN nombre VARCHAR(100))
BEGIN

SELECT * FROM empleado
WHERE EmpleadoNombre = nombre;

END$$

DELIMITER ;

-- Crear vacante

USE `saturnsystemdb`;
DROP procedure IF EXISTS `crear_vacante`;

DELIMITER $$
USE `saturnsystemdb`$$
CREATE PROCEDURE `crear_vacante` (IN nombre VARCHAR(100), IN oferta INT)
BEGIN

INSERT INTO vacante(VacanteNombre, Oferta)
VALUES(nombre, oferta);

END$$

DELIMITER ;



-- Crear Usuario

USE `saturnsystemdb`;
DROP procedure IF EXISTS `crear_usuario`;

DELIMITER $$
USE `saturnsystemdb`$$
CREATE PROCEDURE `crear_usuario` (IN nombre VARCHAR(100), IN correo VARCHAR(100), IN hojaVida TEXT, IN telefonos JSON)
BEGIN

INSERT INTO usuario(UsuarioNombre, Correo, HojaVidaPath, Telefonos)
VALUES(nombre, correo, hojaVida, telefonos);

END$$

DELIMITER ;


-- Asignar Vacante

USE `saturnsystemdb`;
DROP procedure IF EXISTS `asignar_vacante`;

DELIMITER $$
USE `saturnsystemdb`$$
CREATE PROCEDURE `asignar_vacante` (IN idUsuario BIGINT, IN idVacante BIGINT)
BEGIN

UPDATE usuario
SET UsuarioVacante = idVacante
WHERE UsuarioID = idUsuario;

SELECT * FROM usuario WHERE UsuarioID = idUsuario;

END$$

DELIMITER ;


-- Actualizar estado de Usuario

USE `saturnsystemdb`;
DROP procedure IF EXISTS `actualizar_estado_usuario`;

DELIMITER $$
USE `saturnsystemdb`$$
CREATE PROCEDURE `actualizar_estado_usuario` (IN idUsuario BIGINT, IN estado VARCHAR(15))
BEGIN	

UPDATE usuario
SET Estado = estado
WHERE UsuarioID = idUsuario;

SELECT * FROM usuario WHERE UsuarioID = idUsuario;

END$$

DELIMITER ;