DROP PROCEDURE IF EXISTS `createLocalidad`;
DELIMITER $$
CREATE PROCEDURE `createLocalidad`(
	IN loc_nombre VARCHAR (255)
)
BEGIN
	INSERT INTO Localidades (LocalidadNombre) VALUES (loc_nombre);
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `updateLocalidad`;
DELIMITER $$
CREATE PROCEDURE `updateLocalidad`(IN loc_id INT, IN loc_nombre VARCHAR (255))
BEGIN
	UPDATE Localidades
	SET LocalidadNombre = loc_nombre
	WHERE LocalidadID = loc_id;
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
CREATE PROCEDURE `updateLocalidad`(IN tipo_contacto_id INT, IN tipo_contacto_nombre VARCHAR (255))
BEGIN
	UPDATE TiposContacto
	SET TipoContactoNombre = tipo_contacto_nombre
	WHERE TipoContatoID = tipo_contacto_id;
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
CREATE PROCEDURE `createPersona`(IN nom VARCHAR(255),IN ape VARCHAR(255),IN tel INT,IN p_email VARCHAR(255),IN cumple DATE
,IN tipo_con VARCHAR(255), IN dom_calle VARCHAR(255), IN dom_alt INT, IN dom_piso INT, IN dom_dpt VARCHAR (255), loc VARCHAR(255))
BEGIN
	INSERT INTO Personas (Nombre, Apellido, Telefono, Email, FechaCumpleaños,TipoContactoID,Calle, Altura, Piso, Departamento, LocalidadID) 
VALUES (nom, ape, tel, p_email, cumple, (SELECT TipoContactoID FROM TiposContacto WHERE TiposContacto.TipoContactoNombre = tipo_con), dom_calle, dom_alt, dom_piso, dom_dpt, (SELECT LocalidadID FROM Localidades WHERE Localidades.LocalidadNombre = loc));
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `findAllPersonas`;
DELIMITER $$
CREATE PROCEDURE `findAllPersonas`()
BEGIN
SELECT idPersona, Nombre,Apellido,Telefono,Email,FechaCumpleaños,TipoContactoNombre,Calle,Altura,Piso,Departamento,LocalidadNombre FROM personas INNER JOIN Localidades ON personas.LocalidadID = Localidades.LocalidadID INNER JOIN TiposContacto ON personas.TipoContactoID = TiposContacto.TipoContactoID;
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
,IN nom VARCHAR(255)
,IN ape VARCHAR(255)
,IN tel VARCHAR(255)
,IN mail VARCHAR(255)
,IN cumple DATE
,IN tipo VARCHAR(255)
,IN dom_calle VARCHAR(255)
,IN dom_alt INT
,IN dom_piso INT
,IN dom_dpt VARCHAR(4)
,IN loc VARCHAR(255)
)
BEGIN
	UPDATE personas
	SET Nombre = nom, Apellido = ape, Telefono = tel, Email = mail, FechaCumpleaños = cumple, Calle = dom_calle
	,Altura = dom_alt, Piso = dom_piso, Departamento = dom_dpt
	,TipoContactoID = (SELECT TipoContactoID FROM TiposContacto WHERE TiposContacto.TipoContactoNombre = tipo)
	,LocalidadID = (SELECT LocalidadID FROM Localidades WHERE Localidades.LocalidadNombre = loc)
	WHERE idPersona = id;
END $$
DELIMITER ;