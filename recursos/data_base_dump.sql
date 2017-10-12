CREATE SCHEMA pbtest DEFAULT CHARACTER SET utf8;
USE pbtest;

#tabela de usuarios
CREATE TABLE usuario (
	nome VARCHAR(45) NOT NULL,
	email VARCHAR(45),
    senha VARCHAR(45),
    telefone INTEGER NULL,
    cargo INT(1) NOT NULL,
    biografia TEXT,
    PRIMARY KEY (email, senha)
);

#tabela de projetos
CREATE TABLE projeto (
    nome VARCHAR(45) NOT NULL,
    srcRaiz TEXT NOT NULL,
    prefixoCT VARCHAR(10) NOT NULL,
    prefixoCU VARCHAR(10) NOT NULL,
    prefixoRT VARCHAR(10) NOT NULL,
    descricao TEXT NOT NULL,
    usuario_dono VARCHAR(45) NOT NULL,
	id INTEGER AUTO_INCREMENT,
    contadorCT INT DEFAULT 0000,
    contadorCU INT DEFAULT 0000,
    contadorRT INT DEFAULT 0000,
    CONSTRAINT FOREIGN KEY (usuario_dono) REFERENCES usuario(email) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY(id)
);


# tabela de casos de uso
CREATE TABLE caso_de_uso (
    codigo VARCHAR(20) PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    atores TEXT NOT NULL,
    objetivo TEXT NOT NULL,
    descricao TEXT NOT NULL,
    projetoID INTEGER NOT NULL,
    usuario_dono VARCHAR(45) NOT NULL,
    CONSTRAINT FOREIGN KEY (usuario_dono) REFERENCES usuario(email)  ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (projetoID) REFERENCES projeto(id)  ON DELETE CASCADE ON UPDATE CASCADE
);

# tabela de casos de teste
CREATE TABLE caso_de_teste (
	codigo VARCHAR(20) PRIMARY KEY,
    resultado TEXT,
    nomeClasse TEXT NOT NULL,
    classeTeste TEXT NOT NULL,
    descricao TEXT NOT NULL,
    projetoID INTEGER NOT NULL,
    emailUsuario VARCHAR(45) NOT NULL,
    CONSTRAINT FOREIGN KEY (emailUsuario) REFERENCES usuario (email)  ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (projetoID) REFERENCES projeto(id)  ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE roteiros_teste (
	codigo VARCHAR(20) PRIMARY KEY,
    descricao TEXT NOT NULL,
    situacao INT NOT NULL,
    projetoID INTEGER NOT NULL,
    emailUsuario VARCHAR(45) NOT NULL,
    CONSTRAINT FOREIGN KEY (emailUsuario) REFERENCES usuario (email)  ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (projetoID) REFERENCES projeto(id)  ON DELETE CASCADE ON UPDATE CASCADE
);
    
INSERT INTO usuario VALUES ('root', 'root@root', 'root', null, 1,'usuario root do sistema.');
INSERT INTO usuario VALUES ('Matheus Ale da Silva', 'm.matheus.ale@gmail.com', 'adm', null, 1, '');
INSERT INTO usuario VALUES ('Buno silva', 'bruno@live.com', 'jurubeba', null, 2, '');
INSERT INTO usuario VALUES ('Eduardo Gonsaga', 'ginsaghinha@gmail.com', 'gugulegal123', null, 3, '');
INSERT INTO usuario VALUES ('Isabella Bitencurt', 'bela.curt@gmail.com', 'belabela', null, 3, '');
INSERT INTO usuario VALUES ('Amanda Souza', 'amanda_sozinha@gmail.com', 'WWFFEaw@445UffOOpp', null, 3, '');

SELECT * FROM usuario;
SELECT * FROM projeto;
    