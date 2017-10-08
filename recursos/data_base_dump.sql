DROP TABLE projeto;

#tabela de usuarios
CREATE TABLE usuario (
	nome VARCHAR(45) NOT NULL,
	email VARCHAR(45),
    senha VARCHAr(45),
    telefone INTEGER(15) NULL,
    cargo INT(1) NOT NULL,
    PRIMARY KEY (email, senha)
);



#tabela de projetos
CREATE TABLE projeto (
	id INTEGER(5) AUTO_INCREMENT PRIMARY KEY,
    srcRaiz VARCHAR(100) NOT NULL,
    nome VARCHAR(45) NOT NULL,
    prefixoCT VARCHAR(10) NOT NULL,
    prefixoCU VARCHAR(10) NOT NULL,
    prefixoRT VARCHAR(10) NOT NULL,
    descricao TEXT NOT NULL,
    usuario_dono VARCHAR(45) NOT NULL,
    CONSTRAINT FOREIGN KEY (usuario_dono) REFERENCES usuario(email) 
);

# tabela de casos de uso
CREATE TABLE caso_de_uso (
	codigo VARCHAR(20) PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    texto TEXT NOT NULL,
    projetoID INTEGER NOT NULL,
    usuario_dono VARCHAR(45) NOT NULL,
    CONSTRAINT FOREIGN KEY (usuario_dono) REFERENCES usuario(email),
    CONSTRAINT FOREIGN KEY (projetoID) REFERENCES projeto(id)
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
    CONSTRAINT FOREIGN KEY (emailUsuario) REFERENCES usuario (email),
    CONSTRAINT FOREIGN KEY (projetoID) REFERENCES projeto(id)
);

CREATE TABLE roteiros_teste (
	codigo VARCHAR(20) PRIMARY KEY,
    descricao TEXT NOT NULL,
    situacao INT NOT NULL,
    projetoID INTEGER NOT NULL,
    emailUsuario VARCHAR(45) NOT NULL,
    CONSTRAINT FOREIGN KEY (emailUsuario) REFERENCES usuario (email),
    CONSTRAINT FOREIGN KEY (projetoID) REFERENCES projeto(id)
);
    
INSERT INTO usuario VALUES ('root', 'root@root', 'root', null, 1);
INSERT INTO usuario VALUES ('Matheus Ale da Silva', 'm.matheus.ale@gmail.com', 'adm', null, 1);
INSERT INTO usuario VALUES ('Buno silva', 'bruno@live.com', 'jurubeba', null, 2);
INSERT INTO usuario VALUES ('Eduardo Gonsaga', 'ginsaghinha@gmail.com', 'gugulegal123', null, 3);
INSERT INTO usuario VALUES ('Isabella Bitencurt', 'bela.curt@gmail.com', 'belabela', null, 3);
INSERT INTO usuausuariorio VALUES ('Amanda Souza', 'amanda_sozinha@gmail.com', 'WWFFEaw@445UffOOpp', null, 3);


    
    