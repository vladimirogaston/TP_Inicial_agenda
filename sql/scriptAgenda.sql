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
	PRIMARY KEY (ProvinciaID)
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
	Nombre VARCHAR(45) NOT NULL,
	Telefono VARCHAR(20) NOT NULL,
	Email VARCHAR(255),
	FechaCumpleaños DATE,
	TipoContactoID INT,
	Calle VARCHAR(255),
	Altura INT,
	Piso INT,
	Departamento VARCHAR(3),
	LocalidadID INT,
	ProvinciaID INT,
	PaisID INT,
	PRIMARY KEY (idPersona),
	FOREIGN KEY (TipoContactoID) REFERENCES TiposContacto (TipoContactoID),
	FOREIGN KEY (LocalidadID) REFERENCES Localidades (LocalidadID),
	FOREIGN KEY (ProvinciaID) REFERENCES Provincia (ProvinciaID),
	FOREIGN KEY (PaisID) REFERENCES Pais (PaisID)
);