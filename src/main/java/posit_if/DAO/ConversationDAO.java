package posit_if.DAO;

import posit_if.OM.Client;
import posit_if.OM.Conversation;
import posit_if.OM.Employe;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Aurelien BELIN
 * @author Yuting SHU
 */
public class ConversationDAO {
    
    public static void createConversation(Conversation c){
        EntityManager em= JPAUtil.obtenirEntityManager();
        em.persist(c);
    }
    
    public static Conversation updateConversation(Conversation conv){
        EntityManager em= JPAUtil.obtenirEntityManager();
        return em.merge(conv);
    }
    
    public static void removeConversation(Conversation conv){
        EntityManager em= JPAUtil.obtenirEntityManager();
        em.remove(conv);
    }
    
    //Est ce qu'on doit donner accès aux objets Clients à la couche IHM ?? Si oui, identification par id car IHM le possède ; sinon, par mail.
    public static List<Object[]> informationsClient(Client c, boolean b){
        EntityManager em= JPAUtil.obtenirEntityManager();
        Query q;
        if(!b) //Version Client
            q=em.createQuery("SELECT c.medium.nom, c.heure_dbt, c.heure_fin FROM Conversation c WHERE c.client=:client ORDER BY c.heure_dbt DESC");
        else //Version Employe
             q=em.createQuery("SELECT c.medium.nom, c.employe.nom, c.heure_dbt, c.heure_fin, c.commentaire FROM Conversation c WHERE c.client=:client ORDER BY c.heure_dbt DESC");
        q.setParameter("client",c);
        List<Object[]> l = (List<Object[]>)q.getResultList();
        return l;
    }
    
    //Un employe ne peut avoir qu'une seule conversation en cours en même temps => heure_fin null 
    public static Conversation findInProgress(Employe e){
        EntityManager em= JPAUtil.obtenirEntityManager();
        Query q=em.createQuery("SELECT c FROM Conversation c WHERE c.employe=:emp AND c.heure_fin=null AND c.heure_dbt!=null");
        q.setParameter("emp",e);
        Conversation conv;
        try{
            conv = (Conversation)q.getSingleResult();
        }catch(NoResultException ex){
            conv = null;
        }
        return conv;
    }
    
    public static Conversation findWaiting(Employe e){
        EntityManager em= JPAUtil.obtenirEntityManager();
        Query q=em.createQuery("SELECT c FROM Conversation c WHERE c.employe=:emp AND c.heure_fin is null AND c.heure_dbt is null");
        q.setParameter("emp",e);
        //Conversation conv = ((List<Conversation>)q.getResultList()).get(1);
        Conversation conv;
        try{
            conv = (Conversation)q.getSingleResult();
        }catch(NoResultException ex){
            conv = null;
        }
        return conv;
    }
    
    public static HashMap<String,Long> statsVoyanceMedium(){
        EntityManager em= JPAUtil.obtenirEntityManager();
        Query q=em.createQuery("SELECT c.medium.nom, COUNT(c) FROM Conversation c WHERE c.heure_fin is not null Group by c.medium");
        List<Object[]> l = (List<Object[]>)q.getResultList();
        HashMap<String,Long> h=new HashMap<>();
        for(Object[] o : l)
            h.put((String)o[0],(Long)o[1]);
        return h;
    }

    public static HashMap<String,Long> statsVoyanceEmp(){
        EntityManager em= JPAUtil.obtenirEntityManager();
        Query q=em.createQuery("SELECT c.employe.nom, COUNT(c) FROM Conversation c WHERE c.heure_fin is not null Group by c.employe");
        List<Object[]> l = (List<Object[]>)q.getResultList();
        HashMap<String,Long> h=new HashMap<>();
        for(Object[] o : l)
            h.put((String)o[0],(Long)o[1]);
        return h;
    }
    
    public static long nbTotalVoyance(){
        EntityManager em= JPAUtil.obtenirEntityManager();
        Query q=em.createQuery("SELECT COUNT(c) FROM Conversation c");
        return (long)q.getSingleResult();
    }

    public static List<Conversation> findAllConversations() {
        EntityManager em= JPAUtil.obtenirEntityManager();
        Query q=em.createQuery("SELECT c FROM Conversation c");
        return (List<Conversation>)q.getResultList();
    }
}
