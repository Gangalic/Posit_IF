package posit_if.DAO;

import posit_if.OM.Employe;
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
public class EmployeDAO {
    
    public static void createEmploye(Employe e){
        EntityManager em= JPAUtil.obtenirEntityManager();
        em.persist(e);
    }
    
    public static void supprimerEmploye(Employe e){
        EntityManager em= JPAUtil.obtenirEntityManager();
        em.remove(e);
    }
    
    public static Employe updateEmploye(Employe e){
        EntityManager em= JPAUtil.obtenirEntityManager();
        return em.merge(e);
    }
    
    //Requête
    public static Employe trouverEmployeDisponible(Medium m){
        EntityManager em= JPAUtil.obtenirEntityManager();
        Query q = em.createQuery("SELECT e FROM Employe e WHERE e.disponible=true AND :m MEMBER OF e.roles ORDER BY e.affectation");
        q.setParameter("m", m);
        List<Employe> list=(List<Employe>)q.getResultList();
        try{
            return list.get(0);
        }catch(Exception e){
            return null;
        }
    }

    public static Employe find(int id){
        EntityManager em= JPAUtil.obtenirEntityManager();
        Query q=em.createQuery("SELECT e FROM Employe e WHERE e.id=:id");
        q.setParameter("id", id);
        Employe e;
        try{
            e = (Employe)q.getSingleResult();
        }catch(NoResultException ex){
            e=null;
        }
        return e;
    }

    public static List<Employe> findAllEmployes() {
        EntityManager em= JPAUtil.obtenirEntityManager();
        Query q=em.createQuery("SELECT e FROM Employe e");
        return (List<Employe>)q.getResultList();
    }
    
}
