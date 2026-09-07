-- LocadoraDesktop
CREATE DATABASE IF NOT EXISTS cadastro_locadora
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

USE cadastro_locadora;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS tb_cliente;
DROP TABLE IF EXISTS tb_carro;
DROP TABLE IF EXISTS tb_funcionario;
SET FOREIGN_KEY_CHECKS = 2;

CREATE TABLE tb_cliente (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(120) NOT NULL,
    data_nascimento DATE NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    cnh VARCHAR(14) NOT NULL,
    nacionalidade VARCHAR(50) NOT NULL,
    email VARCHAR(120) NOT NULL,
    celular VARCHAR(20) NOT NULL,
    cartao_credito VARCHAR(20) NOT NULL,
    calcao INT NOT NULL,
    
    local_retirada VARCHAR(100) NOT NULL,
    local_devolucao VARCHAR(100) NOT NULL,
    data_retirada DATE NOT NULL,
    hora_retirada TIME NOT NULL,
    data_devolucao DATE NOT NULL,
    hora_devolucao TIME NOT NULL,
    
    grupo_carro VARCHAR(50) NOT NULL REFERENCES tb_carro(grupo_carro),    
    protecao VARCHAR(50) NOT NULL,
    limite_quilometragem VARCHAR(50) NOT NULL,    
    condutor_adicional VARCHAR(50) NOT NULL,
    assento_adicional VARCHAR(50) NOT NULL,
    id_funcionario INT NOT NULL REFERENCES tb_funcionario(id),
    
    PRIMARY KEY (id),
    UNIQUE KEY uk_cliente_cpf (cpf),
    UNIQUE KEY uk_cliente_email (email),
    UNIQUE KEY uk_cliente_cnh (cnh),
    UNIQUE KEY uk_cliente_celular (celular),
    UNIQUE KEY uk_cliente_cartao_credito (cartao_credito),
    KEY ix_cliente_nome (nome)
) ENGINE=InnoDB;

CREATE TABLE tb_carro (
	id INT NOT NULL AUTO_INCREMENT,
    placa VARCHAR(10) NOT NULL,
    modelo_carro VARCHAR(50) NOT NULL,
    grupo_carro VARCHAR(50) NOT NULL,
    direcao VARCHAR(50) NOT NULL,
    numero_assento int NOT NULL,
    gps TINYINT(1) NOT NULL DEFAULT 0,
    reservado TINYINT(1) NOT NULL DEFAULT 0,  
    
    PRIMARY KEY (id),
    UNIQUE KEY uk_carro_placa (placa)
) ENGINE=InnoDB;

CREATE TABLE tb_funcionario (
	id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(120) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    email VARCHAR(120) NOT NULL,
    data_nascimento DATE NOT NULL,
    senha_hash CHAR(64) NOT NULL,
    senha_salt CHAR(32) NOT NULL,
    ativo TINYINT(1) NOT NULL DEFAULT 1,

    PRIMARY KEY (id),
    UNIQUE KEY uk_funcionario_cpf (cpf),
    UNIQUE KEY uk_funcionario_email (email),
    KEY ix_funcionario_nome (nome)
) ENGINE=InnoDB;
