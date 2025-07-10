# CRUD de Requisições de Alteração

Este projeto é uma aplicação web para gerenciamento de requisições de alteração. Ele permite que você adicione, pesquise, edite, liste e exclua registros de requisições usando uma interface simples com HTML, CSS (Bootstrap) e JavaScript. A aplicação consome uma API RESTful para interação com o banco de dados.

## ✨ Funcionalidades

- **Adicionar Requisição:** Permite o cadastro de uma nova requisição com título, descrição, solicitante, data de solicitação, analista responsável e status.
- **Pesquisar por ID:** Permite buscar uma requisição específica pelo `id_requisicao`.
- **Editar Requisição:** Atualiza os dados da requisição em um modal.
- **Listar Todas as Requisições:** Exibe todas as requisições cadastradas em uma tabela.
- **Excluir Requisição:** Remove uma requisição da base de dados.

## 🛠️ Tecnologias Utilizadas

- **Frontend:**
  - HTML
  - CSS (com Bootstrap para estilização)
  - JavaScript (manipulação de DOM e consumo de API)

- **Backend:**
  - API RESTful (espera-se que esteja disponível no endpoint `http://localhost:8080/requisicao_alteracao`)

## 🚀 Como Utilizar

1. Clone o repositório.
2. Certifique-se de que a API backend está rodando em `http://localhost:8080`.
3. Abra o arquivo `index.html` no navegador.
4. Use os formulários para adicionar, pesquisar ou listar requisições.
5. Use os botões "Editar" e "Excluir" nas linhas da tabela para modificar ou remover dados.

## 📄 Campos da Tabela

- `idRequisicao` (gerado automaticamente)
- `titulo`
- `descricao`
- `solicitante`
- `dataSolicitacao`
- `analistaResponsavel`
- `status`

## 🌐 Endpoints da API

- **Adicionar Requisição**:  
  `POST http://localhost:8080/requisicao_alteracao/inserir`
  
- **Obter por ID**:  
  `GET http://localhost:8080/requisicao_alteracao/obter/{id_requisicao}`

- **Listar Todas**:  
  `GET http://localhost:8080/requisicao_alteracao/listar`

- **Atualizar Requisição**:  
  `PUT http://localhost:8080/requisicao_alteracao/alterar?id={id_requisicao}`

- **Excluir Requisição**:  
  `DELETE http://localhost:8080/requisicao_alteracao/deletar/{id_requisicao}`
