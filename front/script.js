// SCRIPT PARA CRUD DA TABELA requisicao_alteracao

const formulario = document.getElementById("form");
const campoTitulo = document.getElementById("titulo");
const campoDescricao = document.getElementById("descricao");
const campoSolicitante = document.getElementById("solicitante");
const campoDataSolicitacao = document.getElementById("dataSolicitacao");
const campoAnalista = document.getElementById("analistaResponsavel");
const campoStatus = document.getElementById("status");
const salvar = document.getElementById("btnSalvar");

const formPesquisa = document.getElementById("formPesquisaRequisicao");
const pesquisar = document.getElementById("btnPesquisar");
const listarTodos = document.getElementById("btnListarTodos");
const campoIdPesquisa = document.getElementById("idPesquisa");

const hiddenTable = document.getElementById("hiddenTable");
const tabela = document.getElementById("tabelaRequisicoes");
let tabelaCorpo = document.getElementById("corpoTabela");

let editarIsPressed = false;

salvar.addEventListener("click", function (event) {
  event.preventDefault();
  salvarRequisicao();
});

function salvarRequisicao() {
  const requisicaoData = {
    titulo: campoTitulo.value,
    descricao: campoDescricao.value,
    solicitante: campoSolicitante.value,
    dataSolicitacao: campoDataSolicitacao.value,
    analistaResponsavel: campoAnalista.value,
    status: campoStatus.value,
  };

  fetch("http://localhost:8080/requisicao_alteracao/inserir", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(requisicaoData),
  })
    .then((response) => {
      if (!response.ok) throw new Error(response.status);
      if (editarIsPressed) {
        listAll();
        editarIsPressed = false;
      }
      return response.json();
    })
    .then((data) => {
      console.log("Resposta da API:", data);
      formulario.reset();
    })
    .catch((error) => console.error("Erro:", error));
}

pesquisar.addEventListener("click", function (event) {
  event.preventDefault();
  pesquisarPorId(campoIdPesquisa.value);
});

function pesquisarPorId(id) {
  fetch(`http://localhost:8080/requisicao_alteracao/obter/${id}`, {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  })
    .then((response) => response.json())
    .then((data) => {
      createRows([data]);
      hiddenTable.style.display = "block";
      formPesquisa.reset();
    })
    .catch((error) => console.error("Erro:", error));
}

listarTodos.addEventListener("click", listAll);

function listAll() {
  fetch("http://localhost:8080/requisicao_alteracao/listar", {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  })
    .then((response) => response.json())
    .then((datas) => {
      createRows(datas);
      hiddenTable.style.display = "block";
    })
    .catch((error) => console.error("Erro:", error));
}

function createRows(datas) {
  
  let tabelaCorpoExistente = document.getElementById("corpoTabela");
  if (tabelaCorpoExistente) {
    tabelaCorpoExistente.remove();
  }
  
  const novoCorpo = document.createElement("tbody");
  novoCorpo.id = "corpoTabela";
  tabela.appendChild(novoCorpo);
  tabelaCorpo = novoCorpo;


  // tabelaCorpo.remove();
  // tabelaCorpo = document.createElement("tbody");
  // tabelaCorpo.id = "tabela";
  // tabela.appendChild(tabelaCorpo);

  for (let data of datas) {
    const tr = document.createElement("tr");
    tr.setAttribute("data-id", data.idRequisicao);

    const campos = [
      data.idRequisicao,
      data.titulo,
      data.descricao,
      data.solicitante,
      data.dataSolicitacao,
      data.analistaResponsavel,
      data.status,
    ];

    for (let campo of campos) {
      const td = document.createElement("td");
      td.textContent = campo;
      tr.appendChild(td);
    }

    const tdAcoes = document.createElement("td");
    const btnEditar = document.createElement("button");
    const btnExcluir = document.createElement("button");

    btnEditar.className = "btn btn-secondary";
    btnEditar.textContent = "Editar";
    btnEditar.addEventListener("click", () => editarRequisicao(data.idRequisicao));

    btnExcluir.className = "btn btn-danger";
    btnExcluir.textContent = "Excluir";
    btnExcluir.addEventListener("click", () => excluirRequisicao(data.idRequisicao));

    tdAcoes.appendChild(btnEditar);
    tdAcoes.appendChild(btnExcluir);
    tr.appendChild(tdAcoes);

    tabelaCorpo.appendChild(tr);
  }
}

function editarRequisicao(id) {
  const editModal = new bootstrap.Modal(document.getElementById("editModal"));
  editModal.show();

  fetch(`http://localhost:8080/requisicao_alteracao/obter/${id}`)
    .then((response) => response.json())
    .then((data) => {
      document.getElementById("editTitulo").value = data.titulo;
      document.getElementById("editDescricao").value = data.descricao;
      document.getElementById("editSolicitante").value = data.solicitante;
      document.getElementById("editDataSolicitacao").value = data.dataSolicitacao;
      document.getElementById("editAnalista").value = data.analistaResponsavel;
      document.getElementById("editStatus").value = data.status;

      document.getElementById("editForm").onsubmit = function (event) {
        event.preventDefault();
        const updated = {
          idRequisicao: id,
          titulo: document.getElementById("editTitulo").value,
          descricao: document.getElementById("editDescricao").value,
          solicitante: document.getElementById("editSolicitante").value,
          dataSolicitacao: document.getElementById("editDataSolicitacao").value,
          analistaResponsavel: document.getElementById("editAnalista").value,
          status: document.getElementById("editStatus").value,
        };

        fetch(`http://localhost:8080/requisicao_alteracao/alterar?id=${id}`, {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(updated),
        })
          .then((response) => response.json())
          .then((data) => {
            console.log("Atualizado:", data);
            listAll();
            editModal.hide();
          })
          .catch((error) => console.error("Erro:", error));
      };
    });
}

function excluirRequisicao(id) {
  fetch(`http://localhost:8080/requisicao_alteracao/deletar/${id}`, {
    method: "DELETE",
    headers: { "Content-Type": "application/json" },
  })
    .then((response) => response.json())
    .then(() => listAll())
    .catch((error) => console.error("Erro:", error));
}
