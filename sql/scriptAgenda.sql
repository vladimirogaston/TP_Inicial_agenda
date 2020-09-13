CREATE DATABASE `grupo_11`;
USE grupo_11;

DROP TABLE IF EXISTS `Personas`;
CREATE TABLE `Personas`(
PersonaID INT NOT NULL AUTO_INCREMENT,
Nombre VARCHAR(255),
Apellido VARCHAR(255),
Telefono INT,
Email VARCHAR(255),
FechaCumpleaños DATE,
TipoContactoID INT,
Calle VARCHAR(255),
Altura INT,
Piso INT,
Departamento VARCHAR(3),
LocalidadID INT,
PRIMARY KEY (PersonaID),
FOREIGN KEY (TipoContactoID) REFERENCES TiposContacto (TipoContactoID),
FOREIGN KEY (LocalidadID) REFERENCES Localidades (LocalidadID)
);

DROP TABLE IF EXISTS `TiposContacto`;
CREATE TABLE TiposContacto(
TipoContactoID INT NOT NULL AUTO_INCREMENT,
TipoContactoNombre VARCHAR(255) UNIQUE,
PRIMARY KEY(TipoContactoID)
);

DROP TABLE IF EXISTS `Localidades`;
CREATE TABLE Localidades(
LocalidadID INT NOT NULL AUTO_INCREMENT,
LocalidadNombre VARCHAR(255) UNIQUE,
PRIMARY KEY (LocalidadID)
);