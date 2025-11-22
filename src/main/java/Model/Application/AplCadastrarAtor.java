package Model.Application;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Model.Domain.Ator;
import Utils.ConexaoHibernate;
import java.util.List;

//
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
///**
// *
// * @author Heloara

public class AplCadastrarAtor {

    public void inserirAtor(Ator ator) {
        Transaction transaction = null;
        try (Session session = ConexaoHibernate.getSessionFactory()) {
            transaction = session.beginTransaction();
            session.save(ator);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public List<Ator> listarTodosAtores() {
        try (Session session = ConexaoHibernate.getSessionFactory()) {
            return session.createQuery("from Ator", Ator.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void alterarAtor(Long id, String novoNome) {
        Session session = ConexaoHibernate.getSessionFactory();
        session.beginTransaction();

        Ator ator = session.get(Ator.class, id); 
        if (ator != null) {
            ator.setNome(novoNome);
            session.update(ator);
        }

        session.getTransaction().commit();
        session.close();
    }
    
     public void excluirAtor(Long id) {
        Session session = ConexaoHibernate.getSessionFactory();
        session.beginTransaction();

        Ator ator = session.get(Ator.class, id); // busca pelo ID
        if (ator != null) {
            session.delete(ator); 
        }

        session.getTransaction().commit();
        session.close();
    }


}


