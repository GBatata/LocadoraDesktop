CREATE DATABASE IF NOT EXISTS cadastro_locadora
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

USE cadastro_locadora;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS tb_locacao;
DROP TABLE IF EXISTS tb_carro;
DROP TABLE IF EXISTS tb_cliente;
DROP TABLE IF EXISTS tb_funcionario;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE tb_funcionario (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(120) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    email VARCHAR(120) NOT NULL,
    data_nascimento DATE NOT NULL,
    perfil ENUM('ADMIN', 'FUNCIONARIO') NOT NULL DEFAULT 'FUNCIONARIO',
    ativo TINYINT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    UNIQUE KEY uk_funcionario_cpf (cpf),
    UNIQUE KEY uk_funcionario_email (email),
    KEY ix_funcionario_nome (nome)
) ENGINE=InnoDB;

CREATE TABLE tb_cliente (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(120) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    cnh VARCHAR(20) NOT NULL,
    data_nascimento DATE NOT NULL,
    nacionalidade VARCHAR(50) NOT NULL,
    reside_brasil TINYINT(1) NOT NULL DEFAULT 1,
    email VARCHAR(120),
    celular VARCHAR(20) NOT NULL,
    ativo TINYINT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    UNIQUE KEY uk_cliente_cpf (cpf),
    UNIQUE KEY uk_cliente_cnh (cnh),
    KEY ix_cliente_nome (nome)
) ENGINE=InnoDB;

CREATE TABLE tb_carro (
    id INT NOT NULL AUTO_INCREMENT,
    placa VARCHAR(10) NOT NULL,
    modelo_carro VARCHAR(50) NOT NULL,
    grupo_carro VARCHAR(50) NOT NULL,
    cambio ENUM('MANUAL', 'AUTOMATICO') NOT NULL,
    numero_assentos INT NOT NULL,
    gps TINYINT(1) NOT NULL DEFAULT 0,
    local_atual VARCHAR(100) NOT NULL,
    limite_quilometragem VARCHAR(50) NOT NULL,
    valor_diaria DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    valor_caucao DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    status ENUM('DISPONIVEL', 'LOCADO', 'INATIVO') NOT NULL DEFAULT 'DISPONIVEL',
    PRIMARY KEY (id),
    UNIQUE KEY uk_carro_placa (placa),
    KEY ix_carro_modelo (modelo_carro),
    KEY ix_carro_local_grupo_status (local_atual, grupo_carro, status)
) ENGINE=InnoDB;

CREATE TABLE tb_locacao (
    id INT NOT NULL AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    id_carro INT NOT NULL,
    id_funcionario INT NOT NULL,
    data_retirada DATETIME NOT NULL,
    data_prevista_devolucao DATETIME NOT NULL,
    data_devolucao DATETIME,
    local_retirada VARCHAR(100) NOT NULL,
    local_devolucao VARCHAR(100) NOT NULL,
    protecao VARCHAR(50) NOT NULL,
    condutor_adicional VARCHAR(120),
    cadeirinha TINYINT(1) NOT NULL DEFAULT 0,
    limite_quilometragem VARCHAR(50) NOT NULL,
    cartao_conferido TINYINT(1) NOT NULL DEFAULT 0,
    quantidade_diarias INT NOT NULL DEFAULT 1,
    valor_diaria DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    valor_protecao DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    valor_adicionais DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    valor_caucao DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    valor_total DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    total_pago DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    status ENUM('RESERVADA', 'EM_ANDAMENTO', 'FINALIZADA', 'CANCELADA') NOT NULL DEFAULT 'RESERVADA',
    PRIMARY KEY (id),
    KEY ix_locacao_cliente (id_cliente),
    KEY ix_locacao_funcionario (id_funcionario),
    KEY ix_locacao_carro_periodo (id_carro, status, data_retirada, data_prevista_devolucao),
    CONSTRAINT fk_locacao_cliente FOREIGN KEY (id_cliente)
        REFERENCES tb_cliente (id) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_locacao_carro FOREIGN KEY (id_carro)
        REFERENCES tb_carro (id) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_locacao_funcionario FOREIGN KEY (id_funcionario)
        REFERENCES tb_funcionario (id) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

