package Controller;

import Model.Application.AplCadastrarAtor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "AlterarAtor", urlPatterns = {"/AlterarAtor"})
public class AlterarAtor extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idParam = request.getParameter("id");
        String nomeAtor = request.getParameter("nomeAtor");
        
        if (idParam != null && nomeAtor != null && !nomeAtor.trim().isEmpty()) {
            Long id = Long.parseLong(idParam);
            
            AplCadastrarAtor apl = new AplCadastrarAtor();
            apl.alterarAtor(id, nomeAtor);  // atualiza no banco
            
            // Redireciona para o servlet que lista atores
            response.sendRedirect("ListarAtores?status=successo");
        } else {
            response.sendRedirect("ListarAtores?status=error&message=Dados inválidos");
        }
    }
}

