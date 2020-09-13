DROP PROCEDURE IF EXISTS `createPersona`;
DELIMITER $$
CREATE PROCEDURE `createPersona`(IN nom VARCHAR(255),IN ape VARCHAR(255),IN tel INT,IN p_email VARCHAR(255),IN cumple DATE
,IN tipo_con VARCHAR(255), IN dom_calle VARCHAR(255), IN dom_alt INT, IN dom_piso INT, IN dom_dpt VARCHAR (255), loc VARCHAR(255))
BEGIN
	INSERT INTO Personas (Nombre, Apellido, Telefono, Email, FechaCumpleaños,TipoContactoID,Calle, Altura, Piso, Departamento, LocalidadID) 
VALUES (nom, ape, tel, p_email, cumple, (SELECT TipoContactoID FROM TiposContacto WHERE TiposContacto.TipoContactoNombre = tipo_con), dom_calle, dom_alt, dom_piso, dom_dpt, (SELECT LocalidadID FROM Localidades WHERE Localidades.LocalidadNombre = loc));
END $$
DELIMITER ;