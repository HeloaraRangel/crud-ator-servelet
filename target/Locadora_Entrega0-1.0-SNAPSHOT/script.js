/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById("livro-form");
    form.addEventListener("submit", function(e) {
        e.preventDefault(); // bloqueia o submit padrão

        const nome = document.getElementById("nomeAtor").value.trim();
        if(nome === "") {
            alert("Digite o nome do ator!");
            return;
        }

        // envia via fetch para o Servlet
        fetch("CadastrarAtor", {
            method: "POST",
            headers: {"Content-Type": "application/x-www-form-urlencoded"},
            body: "nomeAtor=" + encodeURIComponent(nome)
        })
        .then(response => response.text())
        .then(() => {
            const tabela = document.getElementById("tbodyAtores");
            const empty = document.getElementById("empty-state");
            if(empty) empty.style.display = "none";

            const novaLinha = tabela.insertRow();
            novaLinha.insertCell(0).textContent = nome;
            novaLinha.insertCell(1).innerHTML = `<button class="btn btn-warning btn-sm" onclick="alterarAtor(this)">Alterar</button>`;
            novaLinha.insertCell(2).innerHTML = `<button class="btn btn-danger btn-sm" onclick="excluirAtor(this)">Excluir</button>`;

            document.getElementById("nomeAtor").value = "";
        })
        .catch(err => console.error(err));
    });
});



//function inserirAtor() {
//  const nome = document.getElementById("nomeAtor").value.trim();
//  if (nome === "") {
//    alert("Digite o nome do ator!");
//    return;
//  }
//
//  const tabela = document.getElementById("tbodyAtores"); // correto
//  const novaLinha = tabela.insertRow();
//  // Esconde a mensagem de vazio
//    document.getElementById("empty-state").style.display = "none";
//
//  // Nome
//  const colunaNome = novaLinha.insertCell(0);
//  colunaNome.textContent = nome;
//
//  // Alterar
//  const colunaAlterar = novaLinha.insertCell(1);
//  colunaAlterar.innerHTML = `<button class="btn btn-warning btn-sm" onclick="alterarAtor(this)">Alterar</button>`;
//
//  // Excluir
//  const colunaExcluir = novaLinha.insertCell(2);
//  colunaExcluir.innerHTML = `<button class="btn btn-danger btn-sm" onclick="excluirAtor(this)">Excluir</button>`;
//
//  document.getElementById("nomeAtor").value = "";
//}


function alterarAtor(botao) {
    let linha = botao.parentNode.parentNode;
    let celulaNome = linha.cells[0];
    let celulaBotoes = linha.cells[1];
    let nomeAtual = celulaNome.textContent;

    // Transforma o nome em input
    celulaNome.innerHTML = `<input type="text" value="${nomeAtual}" style="width: 90%;">`;
    let input = celulaNome.querySelector("input");

    // Cria botão Confirmar
    let btnConfirmar = document.createElement("button");
    btnConfirmar.textContent = "Confirmar Alteração";
    btnConfirmar.className = botao.className; // mantém o estilo
    btnConfirmar.style.marginRight = "5px"; // adiciona espaço
    btnConfirmar.onclick = function() {
        let novoNome = input.value.trim();
        celulaNome.textContent = novoNome ? novoNome : nomeAtual;
        restaurarBotoes(celulaBotoes, botao.className);
    };

    // Cria botão Cancelar
    let btnCancelar = document.createElement("button");
    btnCancelar.textContent = "Cancelar Alteração";
    btnCancelar.className = botao.className; // mantém o estilo
    btnCancelar.onclick = function() {
        celulaNome.textContent = nomeAtual;
        restaurarBotoes(celulaBotoes, botao.className);
    };

    // Limpa a célula e adiciona os dois botões
    celulaBotoes.innerHTML = "";
    celulaBotoes.appendChild(btnConfirmar);
    celulaBotoes.appendChild(btnCancelar);
}

// Restaura botão original "Alterar"
function restaurarBotoes(celulaBotoes, classeBotao) {
    celulaBotoes.innerHTML = "";
    let btnAlterar = document.createElement("button");
    btnAlterar.textContent = "Alterar";
    btnAlterar.className = classeBotao;
    btnAlterar.onclick = function() {
        alterarAtor(btnAlterar);
    };
    celulaBotoes.appendChild(btnAlterar);
}



function excluirAtor(botao) {
  let linha = botao.parentNode.parentNode;
  linha.remove();

  // Verifica se a tabela está vazia
  const tabela = document.getElementById("tbodyAtores");
  if (tabela.rows.length === 0) {
    document.getElementById("empty-state").style.display = "block";
  }
}




