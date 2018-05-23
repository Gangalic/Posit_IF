package posit_if.DAO;

import posit_if.OM.Medium;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Aurelien BELIN
 * @author Yuting SHU
 */
public class MediumDAO {
    
    public static void createMedium(Medium m){
        EntityManager em= JPAUtil.obtenirEntityManager();
        em.persist(m);
    }
    
    public static Medium find(String nom){
        EntityManager em= JPAUtil.obtenirEntityManager();
        Query q=em.createQuery("SELECT m FROM Medium m WHERE m.nom=:nom");
        q.setParameter("nom", nom);
        Medium m;
        try{
            m = (Medium)q.getSingleResult();
        }catch(NoResultException e){
            m=null;
        }
        return m;
    }
    
    public static void supprimerMedium(Medium m){
        EntityManager em= JPAUtil.obtenirEntityManager();
        em.remove(m);
    }
    
    //Comment différencier les types de medium ??
    public static Medium modifierMedium(Medium m){
        EntityManager em= JPAUtil.obtenirEntityManager();
        return em.merge(m);
    }
 
    public static List<String> findNoms(String type){
        EntityManager em=JPAUtil.obtenirEntityManager();
        String s="SELECT m.nom FROM "+type+" m";
        Query q=em.createQuery(s);
        List<String> l = (List<String>)q.getResultList();
        return l;
    }
    
    //Equivalent aux getters...
    public static String getDescription(String nom){
        EntityManager em=JPAUtil.obtenirEntityManager();
        Query q=em.createQuery("SELECT m.bio FROM Medium m WHERE m.nom=:nom");
        q.setParameter("nom",nom);
        return (String)q.getSingleResult();
    }
}
