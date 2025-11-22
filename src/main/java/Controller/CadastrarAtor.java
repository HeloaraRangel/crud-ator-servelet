///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package Controller;

import Model.Application.AplCadastrarAtor;
import Model.Domain.Ator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CadastrarAtor", urlPatterns = {"/CadastrarAtor"})
public class CadastrarAtor extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String nomeAtor = request.getParameter("nomeAtor");

        if (nomeAtor != null && !nomeAtor.trim().isEmpty()) {
            Ator ator = new Ator(nomeAtor);
            AplCadastrarAtor aplCadastrarAtor = new AplCadastrarAtor();
            aplCadastrarAtor.inserirAtor(ator);
            //TESTANDO
            //response.sendRedirect("index.jsp");
            //esse era oque tava dando certo:
            //response.sendRedirect("index.html?status=successo"); // Redireciona para a página principal com status de sucesso, indicando que conseguiu
            
            
            
            //TAVA ASSIM ANTES:
            //response.sendRedirect("index.jsp?status=successo");
            response.sendRedirect("ListarAtores?status=success");
        } else {
            
            //TAVA ASSIM ANTES:
            //response.sendRedirect("index.jsp?status=error&message=Nome do ator não pode ser vazio.");
            response.sendRedirect("ListarAtores?status=error&message=Nome do ator não pode ser vazio.");

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para cadastrar ator";
    }
    
    
}

