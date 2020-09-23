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
	CodigoPostal VARCHAR(50),
	PRIMARY KEY (idPersona),
	FOREIGN KEY (TipoContactoID) REFERENCES TiposContacto (TipoContactoID),
	FOREIGN KEY (LocalidadID) REFERENCES Localidades (LocalidadID),
	FOREIGN KEY (ProvinciaID) REFERENCES Provincia (ProvinciaID),
	FOREIGN KEY (PaisID) REFERENCES Pais (PaisID)
);