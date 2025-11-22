/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Application.AplCadastrarAtor;
import Model.Domain.Ator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Heloara
 */
@WebServlet(name = "ListarAtores", urlPatterns = {"/ListarAtores"})
public class ListarAtores extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        AplCadastrarAtor apl = new AplCadastrarAtor();
        List<Ator> atores = apl.listarTodosAtores();
        
        // coloca a lista de atores na request
        request.setAttribute("atores", atores);

        // encaminha para o JSP que renderiza a tabela
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
