package posit_if.DAO;

import posit_if.OM.Client;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Aurelien BELIN
 * @author Yuting SHU
 */

public class ClientDAO {
   
    public static void createClient(Client c){
        EntityManager em= JPAUtil.obtenirEntityManager();
        em.persist(c);
    }
    
    public static Client updateClient(Client c){
        EntityManager em= JPAUtil.obtenirEntityManager();
        return em.merge(c);
    }
    
    public static void removeClient(Client c){
        EntityManager em= JPAUtil.obtenirEntityManager();
        em.remove(c);
    }
    
    public static Client find(String mail){
        EntityManager em= JPAUtil.obtenirEntityManager();
        Query q=em.createQuery("SELECT c FROM Client c WHERE c.mail=:mail");
        q.setParameter("mail", mail);
        Client c;
        try{
            c = (Client)q.getSingleResult();
        }catch(NoResultException e){
            c=null;
        }
        return c;
    }
    
    public static List<Client> findAllClients(){
        EntityManager em= JPAUtil.obtenirEntityManager();
        Query q=em.createQuery("SELECT c FROM Client c");
        List<Client> list = (List<Client>)q.getResultList();
        return list;
    }
    
}
