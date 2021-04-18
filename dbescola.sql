CREATE DATABASE dbescola
GO

USE dbescola
GO

CREATE TABLE professor(
	codigo INT NOT NULL,
	nome VARCHAR(100) NOT NULL,
	titulacao VARCHAR(50) NOT NULL,
	PRIMARY KEY(codigo)
)
GO

CREATE TABLE disciplina(
	codigo INT NOT NULL,
	nome VARCHAR(100) NOT NULL,
	codigoProfessor INT NOT NULL
	PRIMARY KEY(codigo),
	FOREIGN KEY(codigoProfessor) REFERENCES professor(codigo)
)
GO