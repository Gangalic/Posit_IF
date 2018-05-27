package posit_if.Service;

import posit_if.DAO.ClientDAO;
import posit_if.DAO.ConversationDAO;
import posit_if.DAO.EmployeDAO;
import posit_if.DAO.JPAUtil;
import posit_if.DAO.MediumDAO;
import posit_if.OM.Astrologue;
import posit_if.OM.Client;
import posit_if.OM.Conversation;
import posit_if.OM.Employe;
import posit_if.OM.Medium;
import posit_if.OM.Tarologue;
import posit_if.OM.Voyant;
import posit_if.Util.AstroTest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.OptimisticLockException;
import javax.persistence.RollbackException;

/**
 *
 * @author abelin
 * @author yshu
 * Cette classe correspond au niveau service de l'application POSIT'IF
 */

public class Service {
    
    private final static String APIKEY="ASTRO-02-M0lGLURBU0ktQVNUUk8tQjAy";
    
    private final static SimpleDateFormat SDF_TIME 
            = new SimpleDateFormat("HH:mm");
    
    private final static SimpleDateFormat SDF_DATE 
            = new SimpleDateFormat("dd/MM/yyyy");
    
    /**
     * Service permettant d'enregistrer un nouveau client dans la base de 
     * données de l'application
     * @param c le client à inscrire et donc à enregistrer
     * @return le client persisté si cela s'est bien passé, null sinon
     * @throws ParseException 
     */
    public static Client inscriptionClient(Client c) throws ParseException{        
        try{
            List<String> profil= (new AstroTest(APIKEY)).getProfil(c.getPrenom()
                    ,c.getDateNaissance());
            //Creation du profil astrologique
            c.setSigne_z(profil.get(0));
            c.setSigne_ch(profil.get(1));
            c.setCouleur(profil.get(2));
            c.setTotem(profil.get(3));
        }catch(IOException e){
            System.err.println(e); 
        }
        
        JPAUtil.creerEntityManager();
        JPAUtil.ouvrirTransaction();
        
        try{
            ClientDAO.createClient(c);
            JPAUtil.validerTransaction();
            JPAUtil.fermerEntityManager();
            return c;
        }catch(RollbackException e){
            JPAUtil.annulerTransaction();
            JPAUtil.fermerEntityManager();
            return null;
        }
    }
    
    /**
     * Service permettant de retrouver un Client dans la base de données à
     * partir de son adresse mail (identifiant)
     * @param mail l'adresse mail enregistrée avec le profil du client
     * @return Le Client trouvé, null sinon
     */
    public static Client authentificationClient(String mail){
        JPAUtil.creerEntityManager();
        Client c = ClientDAO.find(mail);
        JPAUtil.fermerEntityManager();
        return c;
    }
    
    /**
     * Service permettant de modifier l'identité (nom, prénom, civilité, date de
     * naissance) d'un client déjà présent dans la base de données.
     * Mets à jour son profil astrologique.
     * @param c le client modifié à mettre à jour dans la base de données
     * @return le Client modifié, null si une erreur a eu lieu et que le Client 
     * n'a pas pu être mis à jour
     */
    public static Client modifierIdentiteClient(Client c){        
        try{
            List<String> profil= (new AstroTest(APIKEY)).getProfil(c.getPrenom()
                    ,c.getDateNaissance());
            //Creation du profil astrologique
            c.setSigne_z(profil.get(0));
            c.setSigne_ch(profil.get(1));
            c.setCouleur(profil.get(2));
            c.setTotem(profil.get(3));
        }catch(IOException e){
            System.err.println(e); //Que faire ici ??
        }
        
        try{
            JPAUtil.creerEntityManager();
            JPAUtil.ouvrirTransaction();
            Client clientModifie=ClientDAO.updateClient(c);
            JPAUtil.validerTransaction();
            JPAUtil.fermerEntityManager();
            return clientModifie;
        }catch(RollbackException e){
            JPAUtil.annulerTransaction();
            JPAUtil.fermerEntityManager();
        }
        return null;
    }
    
    /**
     * Service permettant de modifier les coordonnées (tel, adresse, mail) d'un 
     * client déjà présent dans la base de données
     * @param c le client modifié à mettre à jour dans la base de données
     * @return le Client modifié, null si une erreur a eu lieu et que le Client 
     * n'a pas pu être mis à jour
     */
    public static Client modifierCoordonneesClient(Client c){
        JPAUtil.creerEntityManager();
        try{
            JPAUtil.ouvrirTransaction();
            Client clientModifie=ClientDAO.updateClient(c);
            JPAUtil.validerTransaction();
            JPAUtil.fermerEntityManager();
            return clientModifie;
        }catch(RollbackException e){
            JPAUtil.annulerTransaction();
            JPAUtil.fermerEntityManager();
        }
        return null;
    }
    
    /**
     * Service permettant d'obtenir la liste de tous les Astrologues présents 
     * dans la base de données de POSIT'IF
     * @return la liste des noms des Astrologues
     */
    public static List<String> listeAstrologues(){
        JPAUtil.creerEntityManager();
        List<String> list=MediumDAO.findNoms("Astrologue");
        JPAUtil.fermerEntityManager();
        return list;
    }
    
    /**
     * Service permettant d'obtenir la liste de tous les Tarologues présents 
     * dans la base de données de POSIT'IF
     * @return la liste des noms des Tarologues
     */
    public static List<String> listeTarologues(){
        JPAUtil.creerEntityManager();
        List<String> list=MediumDAO.findNoms("Tarologue");
        JPAUtil.fermerEntityManager();
        return list;
    }
    
    /**
     * Service permettant d'obtenir la liste de tous les Voyants présents dans 
     * la base de données de POSIT'IF
     * @return la liste des noms des Voyants
     */
    public static List<String> listeVoyants(){
        JPAUtil.creerEntityManager();
        List<String> list=MediumDAO.findNoms("Voyant");
        JPAUtil.fermerEntityManager();
        return list;
    }
    
    /**
     * Service permettant d'obtenir la bio d'un Medium persisté dont le nom est 
     * passé en paramètre
     * @param nom le nom du Medium présent dans la base de données dont on veut 
     * la bio
     * @return la bio du Medium demandée
     */
    public static String consulterMedium(String nom){
        JPAUtil.creerEntityManager();
        String bio = MediumDAO.getDescription(nom);
        JPAUtil.fermerEntityManager();
        return bio;
    }
    
    /**
     * Renvoie l'historique des conversations d'un client
     * @param c Le Client pour lequel on veut l'historique
     * @param v_emp La version de l'hitorique désirée (true = version employé, 
     * false=version client)
     * @return Les informations des consultations du client passé en paramètre
     */
    public static List<String[]> historiqueClient(Client c, boolean v_emp){
        JPAUtil.creerEntityManager();
        List<Object[]> l=ConversationDAO.informationsClient(c,v_emp);
        
        if(!l.isEmpty()){
            List<String[]> ls = new ArrayList<>();
            String[] s;
            int i,taille;

            if(v_emp){
                i=1;
                taille = 6;
            }else{
                i=0;
                taille=4;
            }

            for(Object[] o : l){
                s=new String[taille];
		 //added "+i" for checking if voyances are finished
		 // and make sure we have no null pointer at HeureFin
                if(o[2+i]!= null && o[1+i]!= null){ 
                    if(v_emp){
                        s[1]=o[1].toString();
                        s[5]=o[4].toString();
                    }
                    s[0]=o[0].toString();
                    s[i+1]=SDF_DATE.format((Date)o[1+i]);
                    s[i+2]=SDF_TIME.format((Date)o[1+i]);
                    s[i+3]=SDF_TIME.format((Date)o[2+i]);
                    ls.add(s);
                }
            }
            return ls;
        }else{
            return null;
        }
    }
    
    /**
     * Service vérifiant la disponibilité d'un Medium pour une voyance avec un 
     * Client c, en tenant compte de la disponibilité des Employes et des accès 
     * concurrents
     * @param c Client demandant la voyance
     * @param m Medium avec lequel le Client souhaite avoir une consultation
     * @return true si un Employe pouvant incarner ce medium est trouvé 
     * (acceptation de la voyance), false sinon (refus de la voyance)
     */
    public static boolean demandeVoyance(Client c, Medium m){
        boolean b;
        
        JPAUtil.creerEntityManager();
        Employe e=EmployeDAO.trouverEmployeDisponible(m);
        
        if(e==null){
            b=false;
        }else{
            JPAUtil.ouvrirTransaction();
            try{
                b=true;
                e.setAffectation(e.getAffectation()+1);
                e.setDisponible(false);
                EmployeDAO.updateEmploye(e);
                ConversationDAO.createConversation(new Conversation(c,e,m));
                JPAUtil.validerTransaction();
            }catch(RollbackException ex){
                b=false;
                JPAUtil.annulerTransaction();
            }catch(OptimisticLockException ex){
                b=false;
                JPAUtil.annulerTransaction();                
            }
        }
        JPAUtil.fermerEntityManager();
        return b;
    }
    
    /**
     * Service permettant de terminer une voyance, en mettant à jour la 
     * conversation correspondante en enregistrant l'heure de fin et un 
     * potentiel commentaire
     * @param e Employe réalisant la voyance
     * @param com Commentaire à enregistrer sur la voyance
     * @param heure_fin Heure de fin de la voyance
     * @return true si la mise à jour a été effectuée avec succès, false sinon
     */
    public static boolean terminerVoyance(Employe e,String com,Date heure_fin){
        boolean b;
        
        JPAUtil.creerEntityManager();
        JPAUtil.ouvrirTransaction();
        Conversation conv = ConversationDAO.findInProgress(e);
        conv.setHeure_fin(heure_fin);
        conv.setCommentaire(com);
        ConversationDAO.updateConversation(conv);
        e.setDisponible(true);
        try{
            b=true;
            EmployeDAO.updateEmploye(e);
            JPAUtil.validerTransaction();
        }catch(RollbackException ex){
            b=false;
            JPAUtil.annulerTransaction();
        }
        JPAUtil.fermerEntityManager();
        return true;
    }
    
    /**
     * Service permettant l'authentification d'un employe par son ID
     * @param id Identifiant de l'Employe
     * @return l'Employe trouvé si celui-ci existe dans la base de données au 
     * préalable, null sinon
     */
    public static Employe authentificationEmploye(int id){
        JPAUtil.creerEntityManager();
        Employe e = EmployeDAO.find(id);
        JPAUtil.fermerEntityManager();
        return e;
    }
    
    /**
     * Service permettant de trouver le Client en attente d'une voyance avec un 
     * Employe identifié
     * @param e Employe pour lequel on veut connaître le possible Client en 
     * attente
     * @return Le client de la Conversation en attente trouvée si celle ci 
     * existe, null sinon
     */
    public static Client trouverClientEnAttente(Employe e){
        JPAUtil.creerEntityManager();
        Conversation conv = ConversationDAO.findWaiting(e);
        JPAUtil.fermerEntityManager();
        if(conv!=null)
            return conv.getClient();
        else
            return null;
    }
    
	//this service was added by IHM Team
    /**
     * Service permettant de trouver le Client en progress d'une voyance avec un 
     * Employe identifié
     * @param e Employe pour lequel on veut connaître le possible Client en 
     * attente
     * @return Le client de la Conversation en progress trouvée si celle ci 
     * existe, null sinon
     */
    public static Client trouverClientEnProgress(Employe e){
        JPAUtil.creerEntityManager();
        Conversation conv = ConversationDAO.findInProgress(e);
        JPAUtil.fermerEntityManager();
        if(conv!=null)
            return conv.getClient();
        else
            return null;
    }
    
    /**
     * Service permettant à l'Employe de commencer la consultation de voyance, 
     * en mettant à jour la Conversation correspondante en enregistrant l'heure 
     * de début et en mettant à jour l'Employe en le rendant indisponible
     * @param e Employe réalisant la Conversation
     * @param heure_dbt Heure de début de la voyance
     * @return Employe modifié
     */
    public static Employe commencerVoyance(Employe e,Date heure_dbt){
        JPAUtil.creerEntityManager();
        JPAUtil.ouvrirTransaction();
        Conversation conv = ConversationDAO.findWaiting(e);
        if(conv!=null){
            conv.setHeure_dbt(heure_dbt);
            ConversationDAO.updateConversation(conv);
            e.setDisponible(false);
            try{
                Employe empModif = EmployeDAO.updateEmploye(e);
                JPAUtil.validerTransaction();
                JPAUtil.fermerEntityManager();
                return empModif;
            }catch(RollbackException ex){
                JPAUtil.annulerTransaction();
                JPAUtil.fermerEntityManager();
                return null;
            }
        }else
            return null;
    }
    
    /**
     * Permet de faire une prédiction de voyance paramétrées
     * @param c Client pour lequel on souhaite avoir une prédiction
     * @param amour Note d'amour pour l'évaluation de la prédiction (de 1 à 4)
     * @param sante Note de sante pour l'évaluation de la prédiction (de 1 à 4)
     * @param travail Note de travail pour l'évaluation (de 1 à 4)
     * @return une liste de trois prédictions : Amour, Sante et Travail
     * @throws IOException 
     */
    public static List<String> prediction(Client c, int amour,int sante,
            int travail) throws IOException{
        List<String> prediction = (new AstroTest(APIKEY)).getPredictions(
                c.getCouleur(),c.getTotem(),amour,sante, travail); 
        return prediction;
    }
    
    /**
     * Service calculant le nombre de voyance réalisées par Medium
     * @return le nombre de voyance réalisées par Medium
     */
    public static HashMap<String,Long> nbVoyancesParMedium(){
        JPAUtil.creerEntityManager();
        HashMap<String,Long> ls = ConversationDAO.statsVoyanceMedium();
        JPAUtil.fermerEntityManager();
        return ls;
    }
    
    /**
     * Service calculant le nombre de voyance réalisées par Employe
     * @return le nombre de voyance réalisées par Employe
     */
    public static HashMap<String,Long> nbVoyancesParEmploye(){
        JPAUtil.creerEntityManager();
        HashMap<String,Long> ls = ConversationDAO.statsVoyanceEmp();
        JPAUtil.fermerEntityManager();
        return ls;
    }
    
    /**
     * Service calculant la répartition des voyances selon les Employes
     * @return la répartition des voyances réalisées selon les Employes
     */
    public static HashMap<String,Double> repatitionVoyancesEmployes(){
        JPAUtil.creerEntityManager();
        HashMap<String,Long> ls = ConversationDAO.statsVoyanceEmp();
        double max = (double)ConversationDAO.nbTotalVoyance();
        JPAUtil.fermerEntityManager();
        HashMap<String,Double> lt = new HashMap<>();
        for(String s : ls.keySet())
            lt.put(s,((double)(ls.get(s))*100/max));
        
        return lt;
    }
    
    /**
     * Enregistre un nouvel Employe dans la base de données
     * @param e Employe à enregistrer
     * @return Employe enregistré si cela a été réalisé avec succès, null sinon
     */
    public static Employe inscriptionEmploye(Employe e) {
        JPAUtil.creerEntityManager();
        JPAUtil.ouvrirTransaction();
        EmployeDAO.createEmploye(e);
        try{
            JPAUtil.validerTransaction();
            JPAUtil.fermerEntityManager();
            return e;
        }catch(RollbackException ex){
            JPAUtil.annulerTransaction();
            JPAUtil.fermerEntityManager();
            return null;
        }
    }
    
    /**
     * Enregistre un nouveau Medium dans la base de données
     * @param m Medium à enregistrer
     * @return Medium enregistré si cela a été réalisé avec succès, null sinon
     */
    public static Medium inscriptionMedium(Medium m){
        JPAUtil.creerEntityManager();
        JPAUtil.ouvrirTransaction();
        MediumDAO.createMedium(m);
        try{
            JPAUtil.validerTransaction();
            JPAUtil.fermerEntityManager();
            return m;
        }catch(RollbackException e){
            JPAUtil.annulerTransaction();
            JPAUtil.fermerEntityManager();
            return null;
        }
    }
    
    //Inutiles pour le bon fonctionnement de notre application
    public static void initialiserEmployes(){        
        List<Medium> l1=new ArrayList<>();
        l1.add(trouverMedium("Gwenaël"));
        l1.add(trouverMedium("Mme Irma"));
        l1.add(trouverMedium("Mme Sara"));
        
        List<Medium> l2 = new ArrayList<>();
        l2.add(trouverMedium("Mme Irma"));
        l2.add(trouverMedium("Gwenaël"));
        l2.add(trouverMedium("J. Dalmarre"));
        l2.add(trouverMedium("Mme Lisa Maria NGUYINIA"));
        l2.add(trouverMedium("Mme Mounia Mounia"));
        
        List<Medium> l3 = new ArrayList<>();
        l3.add(trouverMedium("Mme Sara"));
        l3.add(trouverMedium("Mme Irma"));
        
        List<Medium> l4 = new ArrayList<>();
        l4.add(trouverMedium("Gwenaël"));
        l4.add(trouverMedium("Mme Irma"));
        l4.add(trouverMedium("J. Dalmarre"));
        l4.add(trouverMedium("Mme Mounia Mounia"));
        
        inscriptionEmploye(new Employe("GIREUX","Zouhair","0640506070",l1));
        inscriptionEmploye(new Employe("TCHIUMAKOVA","Nicolas","0641516171",l2));
        inscriptionEmploye(new Employe("KEMARO","Cédric","064256272",l3));
        inscriptionEmploye(new Employe("PAMITESCU","Olena","0643536373",l4));
    }
    
    public static void initialiserMediums(){
        inscriptionMedium(new Voyant("Gwenaël","Spécialiste des grandes conversations au-delà de TOUTES les frontières.","Boule de Cristal"));
        inscriptionMedium(new Voyant("J. Dalmarre","Votre avenir est devant vous : regardons-le ensemble !","Marc de Café"));
        inscriptionMedium(new Tarologue("Mme Irma","Comprenez votre entourage grâce à mes cartes ! Résultats rapides.","Tarot de Marseille"));
        inscriptionMedium(new Tarologue("Mme Lisa Maria NGUYINIA","Mes cartes spécialisées pour la région Bretagne répondront à toutes vos questions personnelles.","Tarot de Brocéliande"));
        inscriptionMedium(new Astrologue("Mme Sara","Basée à Champigny-sur-Marne, Mme Sara vous révèlera votre avenir pour éclairer votre passé.","Ecole Normale Supérieur d'Astrologie (ENS-Astro)",2006));
        inscriptionMedium(new Astrologue("Mme Mounia Mounia","Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter !","Institut des Nouveaux Savoirs Astrologiques",2010));
    }
    
    public static void initialiserClients() throws ParseException{
        inscriptionClient(new Client("BORROTI MATIAS DANTAS","Raphaël","M.",SDF_DATE.parse("10/07/1976"),"rborrotimatiasdantas4171@free.fr","0328178508","8 Rue Arago, Villeurbanne"));
        inscriptionClient(new Client("OLMEADA MARAIS","Nor","Mme",SDF_DATE.parse("09/12/1983"),"nolmeadamarais1551@gmail.com","0418932546","5 Rue Léon Fabre, Villeurbanne"));
        inscriptionClient(new Client("RAYES GEMEZ","Olena","Mme",SDF_DATE.parse("28/08/1992"),"orayesgemez5313@outlook.com","0532731620","12 Rue de la Prevoyance, Villeurbanne"));
        inscriptionClient(new Client("SING","Ainhoa","Mme",SDF_DATE.parse("09/11/1982"),"asing8183@free.fr","0705224200","4 Rue Phelypeaux, Villeurbanne"));
        inscriptionClient(new Client("ABDIULLINA","David Alexander","M.",SDF_DATE.parse("07/01/1975"),"david-alexander.abdiullina@laposte.net","0590232772","8 Rue Wilhelmine, Villeurbanne"));
        inscriptionClient(new Client("WOAGNER","Moez","M.",SDF_DATE.parse("16/08/1984"),"moez.woagner@laposte.net","0832205629","6 Rue Camille Koechlin, Villeurbanne"));
	inscriptionClient(new Client("HONRY","Matteo","M.",SDF_DATE.parse("17/02/1996"),"matteo.honry@yahoo.com","0482381862","9 Impasse Guillet, Villeurbanne"));
	inscriptionClient(new Client("CECCANI","Kevin","M.",SDF_DATE.parse("16/02/1982"),"kevin.ceccani@hotmail.com","0664426037","20 Rue Decomberousse, Villeurbanne"));
	inscriptionClient(new Client("VOYRET","Alice","Mme",SDF_DATE.parse("13/08/1988"),"alice.voyret@hotmail.com","0486856520","1 Rue d'Alsace, Villeurbanne"));	
	inscriptionClient(new Client("RINERD","Julien","M.",SDF_DATE.parse("16/05/1989"),"jrinerd5241@yahoo.com","0727252485","4 Rue de la Jeunesse, Villeurbanne"));
	inscriptionClient(new Client("WOSTPHOL","Olivier","M.",SDF_DATE.parse("24/05/1983"),"owostphol@gmail.com","0860680312","7 Rue de la Cloche, Villeurbanne"));	
	inscriptionClient(new Client("GIYRAUD","Vincent","M.",SDF_DATE.parse("07/12/1994"),"vincent.giyraud@yahoo.com","0518940604","4 Rue Marcel Doret, Villeurbanne"));	
	inscriptionClient(new Client("KLOEN","Benjamin","M.",SDF_DATE.parse("07/07/1997"),"benjamin.kloen@yahoo.com","0220989602","16 Rue Jules Kumer, Villeurbanne"));	
	inscriptionClient(new Client("UNLU","Adrien","M.",SDF_DATE.parse("09/04/1986"),"adrien.unlu@laposte.net","0367699654","4 Rue du Luxembourg, Villeurbanne"));	
	inscriptionClient(new Client("MIE","Romain","M.",SDF_DATE.parse("17/11/1994"),"romain.mie@free.fr","0307363387","1 Rue Denis Papin, Villeurbanne"));	
	inscriptionClient(new Client("BEUOLLOT","Audrey-Laure","Mme",SDF_DATE.parse("10/04/1993"),"audrey-laure.beuollot@gmail.com","0202294489","8 Rue Leo Lagrange, Villeurbanne"));	
	inscriptionClient(new Client("DALMARRE","Jose Luis","M.",SDF_DATE.parse("17/05/1994"),"jose-luis.dalmarre@free.fr","0549997540","18 Rue Baudelaire, Villeurbanne"));	
	inscriptionClient(new Client("BOUTILIB","Stéfan","M.",SDF_DATE.parse("17/09/1987"),"stefan.boutilib@gmail.com","0552505347","6 Rue du Perou, Villeurbanne"));
	inscriptionClient(new Client("GOGUOLLOT","Rémy","M.",SDF_DATE.parse("03/06/1987"),"rgoguollot@gmail.com","0477151696","3 Rue Leo Lagrange, Villeurbanne"));	
	inscriptionClient(new Client("CRAPAN","Louis","M.",SDF_DATE.parse("16/12/1977"),"lcrapan8910@hotmail.com","0351726095","3 Rue des 2 Frères, Villeurbanne"));	
    }
    
    public static void initialiserConversations(){
        Conversation c= new Conversation(trouverClient("nolmeadamarais1551@gmail.com"),trouverEmploye(27),trouverMedium("Mme Irma"));
        JPAUtil.creerEntityManager();
        JPAUtil.ouvrirTransaction();
        ConversationDAO.createConversation(c);
        JPAUtil.validerTransaction();
        commencerVoyance(trouverEmploye(27),new Date());
        
        Conversation c2= new Conversation(trouverClient("kevin.ceccani@hotmail.com"),trouverEmploye(28),trouverMedium("Mme Irma"));
        JPAUtil.creerEntityManager();
        JPAUtil.ouvrirTransaction();
        ConversationDAO.createConversation(c2);
        JPAUtil.validerTransaction();
        commencerVoyance(trouverEmploye(28),new Date());
        terminerVoyance(trouverEmploye(27),"Commentaire 1",new Date());
        
        Conversation c3= new Conversation(trouverClient("audrey-laure.beuollot@gmail.com"),trouverEmploye(29),trouverMedium("Mme Irma"));
        JPAUtil.creerEntityManager();
        JPAUtil.ouvrirTransaction();
        ConversationDAO.createConversation(c3);
        JPAUtil.validerTransaction();
        commencerVoyance(trouverEmploye(29),new Date());
        terminerVoyance(trouverEmploye(28),"Commentaire 2",new Date());
        
        Conversation c4= new Conversation(trouverClient("audrey-laure.beuollot@gmail.com"),trouverEmploye(30),trouverMedium("Gwenaël"));
        JPAUtil.creerEntityManager();
        JPAUtil.ouvrirTransaction();
        ConversationDAO.createConversation(c4);
        JPAUtil.validerTransaction();
        commencerVoyance(trouverEmploye(30),new Date());
        terminerVoyance(trouverEmploye(29),"Commentaire 3",new Date());
        
        Conversation c5= new Conversation(trouverClient("kevin.ceccani@hotmail.com"),trouverEmploye(27),trouverMedium("Gwenaël"));
        JPAUtil.creerEntityManager();
        JPAUtil.ouvrirTransaction();
        ConversationDAO.createConversation(c5);
        JPAUtil.validerTransaction();
        commencerVoyance(trouverEmploye(27),new Date());
        terminerVoyance(trouverEmploye(30),"Commentaire 4",new Date());
        
        Conversation c6= new Conversation(trouverClient("alice.voyret@hotmail.com"),trouverEmploye(28),trouverMedium("Mme Sara"));
        JPAUtil.creerEntityManager();
        JPAUtil.ouvrirTransaction();
        ConversationDAO.createConversation(c6);
        JPAUtil.validerTransaction();
        commencerVoyance(trouverEmploye(28),new Date());
        terminerVoyance(trouverEmploye(27),"Commentaire 5",new Date());
        
        Conversation c7= new Conversation(trouverClient("audrey-laure.beuollot@gmail.com"),trouverEmploye(29),trouverMedium("Mme Irma"));
        JPAUtil.creerEntityManager();
        JPAUtil.ouvrirTransaction();
        ConversationDAO.createConversation(c7);
        JPAUtil.validerTransaction();
        commencerVoyance(trouverEmploye(29),new Date());
        terminerVoyance(trouverEmploye(28),"Commentaire 6",new Date());
        
        Conversation c8= new Conversation(trouverClient("asing8183@free.fr"),trouverEmploye(30),trouverMedium("J. Dalmarre"));
        JPAUtil.creerEntityManager();
        JPAUtil.ouvrirTransaction();
        ConversationDAO.createConversation(c8);
        JPAUtil.validerTransaction();
        commencerVoyance(trouverEmploye(30),new Date());
        terminerVoyance(trouverEmploye(29),"Commentaire 7",new Date());
        
        Conversation c9= new Conversation(trouverClient("asing8183@free.fr"),trouverEmploye(27),trouverMedium("Mme Irma"));
        JPAUtil.creerEntityManager();
        JPAUtil.ouvrirTransaction();
        ConversationDAO.createConversation(c9);
        JPAUtil.validerTransaction();
        commencerVoyance(trouverEmploye(27),new Date());
        terminerVoyance(trouverEmploye(30),"Commentaire 8",new Date());
        
        terminerVoyance(trouverEmploye(27),"Commentaire 9",new Date());
        
        Conversation c10= new Conversation(trouverClient("romain.mie@free.fr"),trouverEmploye(27),trouverMedium("Mme Sara"));
        JPAUtil.creerEntityManager();
        JPAUtil.ouvrirTransaction();
        ConversationDAO.createConversation(c10);
        JPAUtil.validerTransaction();
        commencerVoyance(trouverEmploye(27),new Date());
        terminerVoyance(trouverEmploye(27),"Commentaire 10",new Date());
        
        Employe e1=trouverEmploye(27);
        e1.setAffectation(4);
        Employe e2=trouverEmploye(28);
        e2.setAffectation(2);
        Employe e3=trouverEmploye(29);
        e3.setAffectation(2);
        Employe e4=trouverEmploye(30);
        e4.setAffectation(2);
        
        JPAUtil.creerEntityManager();
        JPAUtil.ouvrirTransaction();
        EmployeDAO.updateEmploye(e1);
        EmployeDAO.updateEmploye(e2);
        EmployeDAO.updateEmploye(e3);
        EmployeDAO.updateEmploye(e4);
        JPAUtil.validerTransaction();
    }
    
    public static void envoyerMail(boolean b, Client c){
        System.out.println("Expediteur : contact@posit.if\nPour : "+c.getMail()+"\nSujet : Bienvenue chez Posit'IF");
        System.out.println("\nBonjour "+c.getPrenom()+",");
        if(b){
            System.out.println("Nous vous confirmons votre inscription au service POSIT'IF. Votre numéro de client est : "+c.getId());
        }else{
            System.out.println("Votre inscription au service POSIT'IF a malencontreusement échoué... Merci de recommencer ultérieurement.");
        }
    }
    
    public static void recevoirNotifications(Medium m ,Client c){
        System.out.println("Notification :");
        System.out.println("Voyance demandée pour le client "+c.getNom()+" "+c.getPrenom()+" (#"+c.getId()+"), Médium : "+m.getNom());
    }

    public static List<Conversation> getAllConversations() {
        JPAUtil.creerEntityManager();        
        List<Conversation> list = ConversationDAO.findAllConversations();
        JPAUtil.fermerEntityManager();
        return list;
    }

    public static List<Employe> getAllEmployes() {
        JPAUtil.creerEntityManager();        
        List<Employe> list = EmployeDAO.findAllEmployes();
        JPAUtil.fermerEntityManager();
        return list;
    }
    
    public static List<Client> getAllClients(){
        JPAUtil.creerEntityManager();        
        List<Client> list = ClientDAO.findAllClients();
        JPAUtil.fermerEntityManager();
        return list;
    }
    
    public static Employe trouverEmploye(int id){
        JPAUtil.creerEntityManager();
        Employe e=EmployeDAO.find(id);
        JPAUtil.fermerEntityManager();
        return e;
    }
    
    public static Client trouverClient(String mail){
        JPAUtil.creerEntityManager();
        Client c=ClientDAO.find(mail);
        JPAUtil.fermerEntityManager();
        return c;
    }
    
    public static Medium trouverMedium(String nom){
        JPAUtil.creerEntityManager();
        Medium m= MediumDAO.find(nom);
        JPAUtil.fermerEntityManager();
        return m;
    }
}
