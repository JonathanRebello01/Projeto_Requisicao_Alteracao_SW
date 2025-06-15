-- Active: 1722699540176@@localhost@3306@bancoRequisicao
-- Apagar o banco de dados, se existir
DROP DATABASE IF EXISTS bancoRequisicao;

-- Criar o banco de dados
CREATE DATABASE bancoRequisicao;

-- Atribuir os privilégios de acesso ao usuário root
GRANT ALL PRIVILEGES ON bancoRequisicao.* TO 'root'@'localhost';

-- Acessar o banco de dados criado
USE bancoRequisicao;

-- Criar a tabela requisicao_alteracao
CREATE TABLE requisicao_alteracao (
    id_requisicao INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL,
    solicitante VARCHAR(50) NOT NULL,
    data_solicitacao VARCHAR(50),
    analista_responsavel VARCHAR(50),
    status VARCHAR(30) NOT NULL
);

-- Visualizar os registros da tabela
SELECT * FROM requisicao_alteracao;
