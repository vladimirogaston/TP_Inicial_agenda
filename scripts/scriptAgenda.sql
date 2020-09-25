CREATE DATABASE `grupo_11`;
USE grupo_11;

DROP TABLE IF EXISTS `TiposContacto`;
CREATE TABLE `TiposContacto`(
	TipoContactoID INT NOT NULL AUTO_INCREMENT,
	TipoContactoNombre VARCHAR(255) UNIQUE,
	PRIMARY KEY(TipoContactoID)
);

DROP TABLE IF EXISTS `Pais`;
CREATE TABLE `Pais`(
	PaisID INT NOT NULL AUTO_INCREMENT,
	PaisNombre VARCHAR(255) UNIQUE,
	PRIMARY KEY(PaisID)
);

DROP TABLE IF EXISTS `Provincia`;
CREATE TABLE `Provincia`(
	ProvinciaID INT NOT NULL AUTO_INCREMENT,
	ProvinciaNombre VARCHAR(255) UNIQUE,
    PaisID INT,
	PRIMARY KEY (ProvinciaID),
    FOREIGN KEY (PaisID) REFERENCES Pais (PaisID)
);

DROP TABLE IF EXISTS `Localidades`;
CREATE TABLE `Localidades`(
	LocalidadID INT NOT NULL AUTO_INCREMENT,
	LocalidadNombre VARCHAR(255) UNIQUE,
	ProvinciaID INT,
	PRIMARY KEY (LocalidadID),
	FOREIGN KEY (ProvinciaID) REFERENCES Provincia (ProvinciaID)
);

DROP TABLE IF EXISTS `personas`;
CREATE TABLE `personas`(
	idPersona INT(11) NOT NULL AUTO_INCREMENT,
	Nombre VARCHAR(50) NOT NULL,
	Telefono VARCHAR(50) NOT NULL,
	Email VARCHAR(50),
	FechaCumpleaños DATE,
	TipoContactoID INT,
	Calle VARCHAR(50),
	Altura VARCHAR(50),
	Piso VARCHAR(50),
	Departamento VARCHAR(50),
	LocalidadID INT,
	ProvinciaID INT,
	PaisID INT,
	EquipoFutbol VARCHAR(50),
	CodigoPostal INT,
	PRIMARY KEY (idPersona),
	FOREIGN KEY (TipoContactoID) REFERENCES TiposContacto (TipoContactoID),
	FOREIGN KEY (LocalidadID) REFERENCES Localidades (LocalidadID),
	FOREIGN KEY (ProvinciaID) REFERENCES Provincia (ProvinciaID),
	FOREIGN KEY (PaisID) REFERENCES Pais (PaisID)
);

DROP PROCEDURE IF EXISTS `createLocalidad`;
DELIMITER $$
CREATE PROCEDURE `createLocalidad`(
	IN loc_nombre VARCHAR (80),
	IN prov VARCHAR(80)
)
BEGIN
	INSERT INTO Localidades (LocalidadNombre, ProvinciaID) VALUES (loc_nombre,
		(SELECT ProvinciaID FROM Provincia WHERE Provincia.ProvinciaNombre = prov)
	);
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `updateLocalidad`;
DELIMITER $$
CREATE PROCEDURE `updateLocalidad`(
IN id INT
,IN nom VARCHAR (255)
,IN prov VARCHAR(80))
BEGIN
	UPDATE Localidades
	SET LocalidadNombre = nom,
	ProvinciaID = (SELECT ProvinciaID FROM Provincia WHERE Provincia.ProvinciaNombre = prov)
	WHERE LocalidadID = id;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `deleteLocalidadById`;
DELIMITER $$
CREATE PROCEDURE `deleteLocalidadById`(IN loc_id INT)
BEGIN
	DELETE FROM Localidades WHERE LocalidadID = loc_id;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `findAllLocalidades`;
DELIMITER $$
CREATE PROCEDURE `findAllLocalidades`()
BEGIN
	SELECT * FROM Localidades;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `createTipoContacto`;
DELIMITER $$
CREATE PROCEDURE `createTipoContacto`(
	IN tipo_contacto_nombre VARCHAR (255)
)
BEGIN
	INSERT INTO TiposContacto (TipoContactoNombre) VALUES (tipo_contacto_nombre);
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `updateTipoContacto`;
DELIMITER $$
CREATE PROCEDURE `updateTipoContacto`(
IN id INT
,IN nom VARCHAR (255))
BEGIN
	UPDATE TiposContacto
	SET TipoContactoNombre = nom
	WHERE TipoContactoID = id;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `deleteTipoContactoById`;
DELIMITER $$
CREATE PROCEDURE `deleteTipoContactoById`(IN tipo_contacto_id INT)
BEGIN
	DELETE FROM TiposContacto WHERE TipoContactoID = tipo_contacto_id;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `findAllTiposContacto`;
DELIMITER $$
CREATE PROCEDURE `findAllTiposContacto`()
BEGIN
	SELECT * FROM TiposContacto;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `createPersona`;
DELIMITER $$
CREATE PROCEDURE `createPersona`(
 IN nom VARCHAR(50)
,IN tel VARCHAR(50)
,IN mail VARCHAR(50)
,IN cumple DATE
,IN tipo_con VARCHAR(50)
,IN dom_calle VARCHAR(50)
,IN dom_alt VARCHAR(50)
,IN dom_piso VARCHAR(50)
,IN dom_dpt VARCHAR(50)
,IN loc VARCHAR(50)
,IN prov VARCHAR(50)
,IN pai VARCHAR(50)
,IN eq VARCHAR(50)
,IN cp INT
)
BEGIN
INSERT INTO personas (Nombre,Telefono,Email,FechaCumpleaños,TipoContactoID,Calle,Altura,Piso,Departamento,LocalidadID,
ProvinciaID,PaisID,EquipoFutbol,CodigoPostal) VALUES (nom,tel,mail,cumple, (SELECT TipoContactoID FROM TiposContacto WHERE TiposContacto.TipoContactoNombre = tipo_con),
dom_calle,dom_alt,dom_piso,dom_dpt,(SELECT LocalidadID FROM Localidades WHERE Localidades.LocalidadNombre = loc),(SELECT ProvinciaID FROM Provincia WHERE Provincia.ProvinciaNombre = prov),(SELECT PaisID FROM Pais WHERE Pais.PaisNombre = pai), eq, cp);
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `findAllPersonas`;
DELIMITER $$
CREATE PROCEDURE `findAllPersonas`()
BEGIN
SELECT idPersona,Nombre,Telefono,Email,FechaCumpleaños,TipoContactoNombre,Calle,Altura,Piso,Departamento,LocalidadNombre,ProvinciaNombre,
       PaisNombre, EquipoFutbol, CodigoPostal FROM personas INNER JOIN TiposContacto ON personas.TipoContactoID = TiposContacto.TipoContactoID INNER JOIN Localidades ON personas.LocalidadID = Localidades.LocalidadID INNER JOIN Provincia ON personas.ProvinciaID = Provincia.ProvinciaID INNER JOIN Pais on personas.PaisID = Pais.PaisID;	
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `deletePersonaById`;
DELIMITER $$
CREATE PROCEDURE `deletePersonaById`(IN persona_id INT)
BEGIN
	DELETE FROM personas WHERE personas.idPersona = persona_id;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `updatePersona`;
DELIMITER $$
CREATE PROCEDURE `updatePersona`(
 IN id INT
,IN nom VARCHAR(50)
,IN tel VARCHAR(50)
,IN mail VARCHAR(50)
,IN cumple DATE
,IN tipo VARCHAR(50)
,IN dom_calle VARCHAR(50)
,IN dom_alt VARCHAR(50)
,IN dom_piso VARCHAR(50)
,IN dom_dpt VARCHAR(50)
,IN loc VARCHAR(50)
,IN pro VARCHAR(50)
,IN pai VARCHAR(50)
,IN eq VARCHAR(50)
,IN cp INT
)
BEGIN
	UPDATE personas
	SET Nombre = nom, Telefono = tel, Email = mail, FechaCumpleaños = cumple, Calle = dom_calle
	,Altura = dom_alt, Piso = dom_piso, Departamento = dom_dpt
	,TipoContactoID = (SELECT TipoContactoID FROM TiposContacto WHERE TiposContacto.TipoContactoNombre = tipo)
	,LocalidadID = (SELECT LocalidadID FROM Localidades WHERE Localidades.LocalidadNombre = loc)
	,ProvinciaID = (SELECT ProvinciaID FROM Provincia WHERE Provincia.ProvinciaNombre = pro)
	,PaisID = (SELECT PaisID FROM Pais WHERE Pais.PaisNombre = pai)
	,EquipoFutbol = eq
	,CodigoPostal = cp
	WHERE idPersona = id;
END $$
DELIMITER ;