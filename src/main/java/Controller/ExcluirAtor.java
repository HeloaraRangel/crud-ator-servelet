/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.Application.AplCadastrarAtor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *
 * @author Heloara
 */

@WebServlet(name = "ExcluirAtor", urlPatterns = {"/ExcluirAtor"})
public class ExcluirAtor extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idParam = request.getParameter("id");
        
        if (idParam != null) {
            Long id = Long.parseLong(idParam);
            
            AplCadastrarAtor apl = new AplCadastrarAtor();
            apl.excluirAtor(id);  // método que exclui do banco
            
            // REDIRECIONA para o servlet de listagem para atualizar a tabela visual
            response.sendRedirect("ListarAtores?status=success");
        } else {
            response.sendRedirect("ListarAtores?status=error&message=ID inválido");
        }
    }
}


