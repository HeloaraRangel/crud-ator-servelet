<%@page import="Model.Application.AplCadastrarAtor"%>
<%@page import="Model.Domain.Ator"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.Long"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sistema de Locadora de Livros</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap Icons -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <!-- Menu Lateral -->
                <nav class="col-md-3 col-lg-2 d-md-block bg-primary sidebar collapse min-vh-100">
                    <div class="position-sticky pt-3">
                        <div class="text-center mb-4">
                            <h4 class="text-white">
                                <i class="bi bi-book"></i>
                                Locadora de Livros
                            </h4>
                        </div>
                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <a class="nav-link active text-white" href="#">
                                    <i class="bi bi-plus-circle me-2"></i>
                                    Cadastro de Ator
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-white-50" href="#">
                                    <i class="bi bi-list-ul me-2"></i>
                                    Listar Todos
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>

                <!-- Conteúdo Principal -->
                <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                    <!-- Formulário de Inserção -->
                    <div class="row mt-4">
                        <div class="col-12">
                            <div class="card shadow-sm">
                                <div class="card-header bg-primary text-white">
                                    <h5 class="card-title mb-0">
                                        <i class="bi bi-pencil-square me-2"></i>
                                        Cadastrar Ator
                                    </h5>
                                </div>
                                <div class="card-body">
                                    <form id="ator-form" method="post" action="CadastrarAtor">
                                        <div class="mb-3">
                                            <label for="nomeAtor" class="form-label">Nome do Ator</label>
                                            <input type="text" class="form-control" id="nomeAtor" name="nomeAtor" required>
                                            <div class="invalid-feedback">Por favor, insira o nome do Ator.</div>
                                        </div>
                                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                            <button type="submit" class="btn btn-primary">
                                                <i class="bi bi-check-circle me-1"></i> Inserir
                                            </button>
                                            <button type="reset" class="btn btn-secondary">
                                                <i class="bi bi-x-circle me-1"></i> Limpar
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Tabela de Atores -->
                    <div class="row mt-4">
                        <div class="col-12">
                            <div class="card shadow-sm">
                                <div class="card-header bg-success text-white">
                                    <h5 class="card-title mb-0">
                                        <i class="bi bi-table me-2"></i>
                                        Atores Cadastrados
                                    </h5>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-hover table-striped">
                                            <thead class="table-dark">
                                                <tr>
                                                    <th scope="col">Nome</th>
                                                    <th scope="col">Alteração</th>
                                                    <th scope="col">Exclusão</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    List<Ator> atores = (List<Ator>) request.getAttribute("atores");
                                                    String paramId = request.getParameter("alterarId");

                                                    if (atores != null && !atores.isEmpty()) {
                                                        for (Ator ator : atores) {
                                                %>
                                                <tr>
                                                    <td>
                                                        <%
                                                            if (paramId != null && Long.parseLong(paramId) == ator.getId()) {
                                                        %>
                                                        <form method="post" action="AlterarAtor" class="d-flex">
                                                            <input type="hidden" name="id" value="<%= ator.getId()%>">
                                                            <input type="text" name="nomeAtor" class="form-control me-2" value="<%= ator.getNome()%>" required>
                                                            <button type="submit" class="btn btn-success me-1">Confirmar</button>
                                                            <a href="ListarAtores" class="btn btn-secondary">Cancelar</a>
                                                        </form>
                                                        <%
                                                            } else {
                                                                out.print(ator.getNome());
                                                            }
                                                        %>
                                                    </td>
                                                    <td>
                                                        <%
                                                            if (paramId == null || Long.parseLong(paramId) != ator.getId()) {
                                                        %>
                                                        <form method="get" action="ListarAtores">
                                                            <input type="hidden" name="alterarId" value="<%= ator.getId()%>">
                                                            <button type="submit" class="btn btn-warning btn-sm">Alterar</button>
                                                        </form>
                                                        <%
                                                            }
                                                        %>
                                                    </td>
                                                    <td>
                                                        <form action="ExcluirAtor" method="post" onsubmit="return confirm('Tem certeza que deseja excluir este ator?');">
                                                            <input type="hidden" name="id" value="<%= ator.getId()%>">
                                                            <button type="submit" class="btn btn-danger btn-sm">Excluir</button>
                                                        </form>
                                                    </td>
                                                </tr>
                                                <%
                                                    }
                                                } else {
                                                %>
                                                <tr>
                                                    <td colspan="3" class="text-center py-4 text-muted">
                                                        <i class="bi bi-inbox display-6"></i><br>
                                                        Nenhum ator cadastrado ainda.
                                                    </td>
                                                </tr>
                                                <% }%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
