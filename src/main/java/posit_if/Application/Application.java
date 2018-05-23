/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posit_if.Application;

import posit_if.DAO.JPAUtil;
import posit_if.OM.Client;
import posit_if.OM.Conversation;
import posit_if.OM.Employe;
import posit_if.Service.Service;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @author yshu
 * @author abelin
 */

//Enregistrer les mediums et les employes en dur dans Application ou dans Service ?

public class Application {
    
    /*public static void main(String[] args) throws ParseException, IOException{
        /*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d=sdf.parse("06/07/1997");
        Client c = new Client("A","B","M",d,"a@b.fr","060000000","ici");
        
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyyy hh:mm");
        Date d2 = sdf.parse("10/03/1998");
        Date d3 = sdf2.parse("09/12/2017 14:44");
        Date d4 = sdf2.parse("09/12/2017 15:32");
        Date d5 = sdf2.parse("10/03/2018 15:36");
        
        System.out.println(d3);
        System.out.println(d2);
        
        JPAUtil.init();
        //Service.initialiserMediums();
        //Service.initialiserClients();
        //Service.initialiserEmployes();
        //Service.initialiserConversations();
        
        //Test inscription
        /*System.out.println(Service.getAllClients().size());
        Service.inscriptionClient(c);
        Service.envoyerMail(true, courant);
        System.out.println(Service.getAllClients().size());
        
        //Test authentification
        Client courant = Service.authentificationClient("a@b.fr");
        System.out.println(courant);
        
        /*Test modification coordonnées client (sans recalcul du profil astrologique
        courant.setTel("0630002001");
        courant = Service.modifierIdentiteClient(courant);
        System.out.println(courant);
        
        //Test modification données client (avec recalcul du profil astrologique)
        courant.setDateNaissance(d2);
        courant = Service.modifierIdentiteClient(courant);
        System.out.println(courant);
        
        //test historique vide
        System.out.println("\nHistorique :");
        List<String[]> ser = Service.historiqueClient(courant, false);
        if(ser==null)
            System.out.println("Null");
        else{
            for(String[] s : ser)
                System.out.println(s);
        }
        
        System.out.println(Service.demandeVoyance(courant,Service.trouverMedium("Mme Lisa Maria NGUYINIA")));
        
        //Test authentifiaction employé
        Employe e=Service.authentificationEmploye(28);
        System.out.println("\nEmploye connecté : "+e);
        
        //Test Profil Client
        Service.recevoirNotifications(Service.trouverMedium("Mme Lisa Maria NGUYINIA"),courant);
        Client cEmp = Service.trouverClientEnAttente(e);
        System.out.println("\nClient en attente : "+cEmp+"\n");
        System.out.println("\nHistorique :");
        ser = Service.historiqueClient(courant, true);
        if(ser==null)
            System.out.println("Null");
        
        //Test commencer voyance
        Service.commencerVoyance(e,new Date());
        System.out.println();
        for(String s : Service.prediction(cEmp,2,3,1))
            System.out.println(s);
        System.out.println();
        
        //Test terminer voyance
        System.out.println();
        System.out.println("Test fin de voyance : ");
        System.out.println(Service.terminerVoyance(e, "OK...", new Date()));
        ser = Service.historiqueClient(courant, true);
        for(String[] s : ser)
            System.out.println(s[0]+" ; "+s[1]+" ; "+s[2]+" ; "+s[3]+" ; "+s[4]+" ; "+s[5]);
        
        //Tester les disponibilités : tentative d'accès à un médium qui ne possède aucun employé dispo pour le jouer
        //Tester les accès concurrents
        //Passer en objet (virer static)
        //Commenter le code
        //Rédiger le dossier*/
        
        /*HashMap<String,Long> h1=Service.nbVoyancesParEmploye();
        HashMap<String,Long> h2=Service.nbVoyancesParMedium();
        HashMap<String,Double> h3=Service.repatitionVoyancesEmployes();
        
        System.out.println("Nb voyances par Employés : ");
        for(String s : h1.keySet())
            System.out.println(s+" | "+h1.get(s));
        
        System.out.println("Nb voyances par Medium : ");
        for(String s : h2.keySet())
            System.out.println(s+" | "+h2.get(s));
        
        System.out.println("Répartition voyances Employés : ");
        for(String s : h3.keySet())
            System.out.println(s+" | "+h3.get(s));
        
        Client c = Service.authentificationClient("nolmeadamarais1551@gmail.com");
        Client c2 = Service.authentificationClient("orayesgemez5313@outlook.com");
        
        System.out.println(Service.demandeVoyance(c, Service.trouverMedium("Mme Lisa Maria NGUYINIA")));
        System.out.println(Service.demandeVoyance(c2, Service.trouverMedium("Mme Lisa Maria NGUYINIA")));

        JPAUtil.destroy();
    }*/
    
    public static void main(String[] args) throws ParseException, IOException{
        
        JPAUtil.init();
      
//        Service.initialiserMediums();  
//        Service.initialiserClients();
//        Service.initialiserEmployes();
//        Service.initialiserConversations();
        System.out.println(Service.listeVoyants());
//        
//        System.out.println("Bonjour!Bienvenue a Posit'IF!");
//        choisirStatus();
//        
//        Scanner in = new Scanner(System.in);
//        String status = in.nextLine();
//
//        while(status.equals("bye")==false){
//            if(status.equals("client")){
//                fenetreClient();
//            }
//            if(status.equals("employe")){
//                fenetreEmploye();
//            }
//            
//            choisirStatus();
//            status = in.nextLine();
//        }
        JPAUtil.destroy();
    }
    
    public static void choisirStatus(){
        System.out.println("Si vous etes client, veuillez taper \"client \";");
        System.out.println("Si vous etes employe, veuillez taper \"employe\";");
        System.out.println("Pour quitter le programme, veuillez taper \"bye\";");
    }
    
    public static void commandeClient(){
        System.out.println("Pour aller a la page \"Profil\"--Veuillez taper \"profil\".");
        System.out.println("Pour aller a la page \"Voyance\"--Veuillez taper \"voyance\".");
        System.out.println("Pour aller a la page \"Historique\"--Veuillez taper \"historique\".");
        System.out.println("Pour se deconnecter--Veuillez taper \"deconnecter\".");
    }
    
    public static void commandeEmploye(){
        System.out.println("Pour faire une voyance.--Veuillez taper \"voyance\".");
        System.out.println("Pour consulter statistique.--Veuillez taper \"statistique\".");
        System.out.println("Pour se deconnecter--Veuillez taper \"deconnecter\".");
    }
    
    public static void commandeModifierDonnees(){
        System.out.println("Pour modifier le nom.--Veuillez taper \"nom\".");
        System.out.println("Pour modifier le prenom.--Veuillez taper \"prenom\".");
        System.out.println("Pour modifier le civilite.--Veuillez taper \"civilite\".");
        System.out.println("Pour modifier le date de naissance.--Veuillez taper \"dn \".");
        System.out.println("Si vous avez fini de modifier vos donnees.--Veuillez taper \"fini \" pour mettre a jour.");
    }
    
    public static void fenetreEmploye() throws IOException{
        Scanner in = new Scanner(System.in);
        System.out.println("Veuillez taper votre ID pour se connecter.");
        int id = in.nextInt();
        Employe e=Service.authentificationEmploye(id);

        if(e==null){
            System.out.println("Vous etes pas notre employe.");
        }
        else{
            System.out.println("\nEmploye connecté : "+e);
            String commande = "";
            while(commande.equals("deconnecter")==false){ 
                
                commandeEmploye();
                commande = in.nextLine();
                if(commande.equals("voyance")){
                    //Test Profil Client
                    Client cEmp = Service.trouverClientEnAttente(e);
                    //Service.recevoirNotifications(Service.trouverMedium("Mme Lisa Maria NGUYINIA"),cEmp);//????
                    if(cEmp!=null){
                        System.out.println("\nClient en attente : "+cEmp+"\n");
                        System.out.println("\nHistorique :");
                        List<String[]> ser = Service.historiqueClient(cEmp, true);
                        if(ser==null)
                            System.out.println("Null");
                        else{
                                for(String[] s : ser)
                                    System.out.println(s);
                            }

                        System.out.println("Commencer la voyance ?");
                        in.nextLine();
                        //Test commencer voyance
                        Service.commencerVoyance(e,new Date());
                        System.out.println("Veillez entrer la note d'amour");
                        int amour=in.nextInt();
                        System.out.println("Veillez entrer la note de sante");
                        int sante=in.nextInt(); 
                        System.out.println("Veillez entrer la note de travail");
                        int travail=in.nextInt();
                        for(String s : Service.prediction(cEmp,amour,sante,travail))
                            System.out.println(s);
                        System.out.println();

                        //Test terminer voyance
                        System.out.println();
                        String com="commentaire";
                        System.out.println("Commentaire : ");
                        com = in.nextLine();
                        System.out.println("Terminer la voyance ?");
                        in.nextLine();
                        System.out.println(Service.terminerVoyance(e, com, new Date()));
                        System.out.println();
                        ser = Service.historiqueClient(cEmp, true);
                        for(String[] s : ser)
                            System.out.println(s[0]+" ; "+s[1]+" ; "+s[2]+" ; "+s[3]+" ; "+s[4]+" ; "+s[5]);
                        System.out.println();
                    }
                    
                }
                
                
                if(commande.equals("statistique")){
                    
                    System.out.println();
                    for(Conversation conv : Service.getAllConversations())
                        System.out.println(conv);
                    System.out.println();
                        
                    System.out.println("\n Nombre Voyances Par Employe :");
                    HashMap<String,Long> nbVparE = Service.nbVoyancesParEmploye();
                    if(nbVparE==null)
                        System.out.println("Null");
                    else{
                        for(String s : nbVparE.keySet())
                            System.out.println(s+" | "+nbVparE.get(s));
                    }

                    System.out.println("\n Nombre Voyances Par Medium :");
                    HashMap<String,Long> nbVparM = Service.nbVoyancesParMedium();
                    if(nbVparM==null)
                        System.out.println("Null");
                    else{
                        for(String s : nbVparM.keySet())
                            System.out.println(s+" | "+nbVparM.get(s));
                    }

                    System.out.println("\n Repartition Voyances Employes :");
                    HashMap<String,Double> repVE = Service.repatitionVoyancesEmployes();
                    if(repVE==null)
                        System.out.println("Null");
                    else{
                        for(String s : repVE.keySet())
                            System.out.println(s+" | "+repVE.get(s));
                    }
                }
            }
        }
    }
    
    public static void fenetreClient() throws ParseException{
        System.out.println("Deja un compte?");
        System.out.println("Oui, je veux connecter a mon comtpe.--Veuillez taper \"connecter \"");
        System.out.println("Non, je veux faire une iscription.--Veuillez taper \"inscrire \"");
        Scanner in=new Scanner(System.in);
        String action = in.nextLine();
        if(action.equals("connecter")){
            System.out.println("Veuillez entrer votre mail pour se connecter.");
            String mail = in.nextLine();
            Client courant = Service.authentificationClient(mail);
            if(courant==null){
                System.out.println("Vous etes pas encore mon client.");
            }
            else{
                commandeClient();
                String commande = in.nextLine();
                while(commande.equals("deconnecter")==false){
                    if(commande.equals("profil")){
                        pageProfilClient(courant);
                    }
                    if(commande.equals("voyance")){
                        pageVoyanceClient(courant);
                    }
                    if(commande.equals("historique")){
                        System.out.println("\nHistorique :");
                        List<String[]> ser = Service.historiqueClient(courant, false);
                        if(ser==null)
                            System.out.println("Null");
                        else{
                            for(String[] s : ser)
                                System.out.println(s[0]+" ; "+s[1]+" ; "+s[2]+" ; "+s[3]);;
                        }
                    }
                    commandeClient();
                    commande = in.nextLine();
                }
            } 
        }
        if(action.equals("inscrire")){
            inscrireClient();
        }
    
    }
    
    public static void pageProfilClient(Client courant) throws ParseException{
        Scanner in=new Scanner(System.in);
        System.out.println(Service.trouverClient(courant.getMail()));
        System.out.println();
        
        System.out.println("Pour modifier votre donnÃ©es personnelles.--Veuillez taper \"donnees \".");
        System.out.println("Pour modifier votre coordonnÃ©ess.--Veuillez taper \"coordonnees\".");
        System.out.println("Pour changer la page.--Veuillez taper \"changer\".");
        String modifier = in.nextLine();
        
        while(modifier.equals("changer")==false){
            
            if(modifier.equals("donnees")){
                commandeModifierDonnees();
                String donnees = in.nextLine();
                while(donnees.equals("fini")==false){
                    if(donnees.equals("nom")){
                        System.out.println("Veuillez entrer votre nouveau nom.");
                        String nom = in.nextLine();
                        courant.setNom(nom);
                    }
                    if(donnees.equals("prenom")){
                        System.out.println("Veuillez entrer votre nouveau prenom.");
                        String prenom = in.nextLine();
                        courant.setPrenom(prenom);
                    }
                    if(donnees.equals("civilite")){
                        System.out.println("Veuillez entrer votre civilite.");
                        String civilite = in.nextLine();
                        courant.setCivilite(civilite);
                    }
                    if(donnees.equals("dn")){
                        System.out.println("Veuillez entrer votre date de naissance.");
                        String dn = in.nextLine();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date d=sdf.parse(dn);
                        courant.setDateNaissance(d);
                    }
                    commandeModifierDonnees();
                    donnees = in.nextLine();

                }
                courant = Service.modifierIdentiteClient(courant);
                System.out.println(Service.trouverClient(courant.getMail()));
            }

            if(modifier.equals("coordonnees")){
                System.out.println("Pour modifier le mail.--Veuillez taper \"mail \".");
                System.out.println("Pour modifier le numero de telephone.--Veuillez taper \"tel\".");
                System.out.println("Pour modifier l'adresse.--Veuillez taper \"add \".");
                System.out.println("Si vous avez fini de modifier.--Veuillez taper \"fini \" pour mettre a jour.");

                String donnees = in.nextLine();

                if(donnees.equals("mail")){
                    System.out.println("Veuillez entrer votre nouveau mail.");
                    String newMail = in.nextLine();
                    courant.setMail(newMail);
                    System.out.println("Mail est modifie.");
                }
                 if(donnees.equals("tel")){
                    System.out.println("Veuillez entrer votre nouveau numero de telephone.");
                    String tel = in.nextLine();
                    courant.setTel(tel);
                    System.out.println("Telephone est modifie.");
                }
                if(donnees.equals("add")){
                    System.out.println("Veuillez entrer votre nouveau addresse.");
                    String add = in.nextLine();
                    courant.setAddresse(add);
                    System.out.println("Addresse est modifiee.");
                }
                courant = Service.modifierCoordonneesClient(courant);
            }
            System.out.println(Service.trouverClient(courant.getMail()));
            System.out.println();
            System.out.println("Pour modifier votre donnÃ©es personnelles.--Veuillez taper \"donnees \".");
            System.out.println("Pour modifier votre coordonnÃ©ess.--Veuillez taper \"coordonnees\".");
            System.out.println("Pour changer la page.--Veuillez taper \"changer\".");
            modifier = in.nextLine();
        }
        System.out.println("Veuillez entrer la page destination.");
    }
    
    public static void pageVoyanceClient(Client courant){
        Scanner in=new Scanner(System.in);
        
        System.out.println("Pour regarder la liste de Astrologues.--Veuillez taper \"astro \".");
        System.out.println("Pour regarder la liste de Tarologues.--Veuillez taper \"taro \".");
        System.out.println("Pour regarder la liste de Voyants.--Veuillez taper \"voyant \".");
        System.out.println("Pour consulter le medium.--Veuillez taper \"medium \".");
        System.out.println("Pour demander une voyance.--Veuillez taper \"demande \".");
        System.out.println("Pour changer la page.--Veuillez taper \"changer\".");
        String demande = in.nextLine();
        
        while(demande.equals("changer")==false){
            if(demande.equals("astro")){
                List<String> lA =  Service.listeAstrologues();
                System.out.println();
                if(lA==null)
                    System.out.println("Null");
                else{
                    for(String s : lA)
                        System.out.println(s);
                }
                System.out.println();
             }
            if(demande.equals("taro")){
                List<String> lT =  Service.listeTarologues();
                System.out.println();
                if(lT==null)
                    System.out.println("Null");
                else{
                    for(String s : lT)
                        System.out.println(s);
                }
                System.out.println();
            }
            if(demande.equals("voyant")){
                List<String> lV =  Service.listeVoyants();
                System.out.println();
                if(lV==null)
                    System.out.println("Null");
                else{
                    for(String s : lV)
                        System.out.println(s);
                }
                System.out.println();
            }
            if(demande.equals("medium")){
                System.out.println();
                System.out.println("Veuillez entrer le nom du medium que vous voulez consulter.");
                String nom = in.nextLine();
                System.out.println(Service.consulterMedium(nom));
                System.out.println();
            }
            if(demande.equals("demande")){
                System.out.println();
                System.out.println("Veuillez entrer le nom du medium que vous voulez demander.");
                String nom = in.nextLine();
                for(Employe e : Service.getAllEmployes()){
                    e.getRoles().size();
                    //System.out.println(e.getNom()+" : "+e.getAffectation()+" ; "+e.getRoles().contains(Service.trouverMedium(nom)));
                    System.out.println(e);
                }
                System.out.println(Service.demandeVoyance(courant,Service.trouverMedium(nom)));
                System.out.println();
                for(Conversation conv : Service.getAllConversations()){
                    conv.getEmploye().getRoles().size();
                    System.out.println(conv);
                }
                System.out.println();
                for(Employe e : Service.getAllEmployes())
                    System.out.println(e.getNom()+" : "+e.getAffectation());
                System.out.println();
            } 
            
            System.out.println("Pour regarder la liste de Astrologues.--Veuillez taper \"astro \".");
            System.out.println("Pour regarder la liste de Tarologues.--Veuillez taper \"taro \".");
            System.out.println("Pour regarder la liste de Voyants.--Veuillez taper \"voyant \".");
            System.out.println("Pour consulter le medium.--Veuillez taper \"medium \".");
            System.out.println("Pour demander une voyance.--Veuillez taper \"demande \".");
            System.out.println("Pour changer la page.--Veuillez taper \"changer\".");
            demande = in.nextLine();
        }
        System.out.println("Veuillez entrer la page destination.");
    }
    
    public static void inscrireClient() throws ParseException{
        Scanner in=new Scanner(System.in);
        System.out.println();
        for(Client c : Service.getAllClients())
            System.out.println(c.getPrenom()+" "+c.getNom());
        System.out.println();
        System.out.println("Veuillez entrer votre nom.");
        String nom = in.nextLine();
        System.out.println("Veuillez entrer votre prenom.");
        String prenom = in.nextLine();
        System.out.println("Veuillez entrer votre civilite.");
        String civilite = in.nextLine();
        System.out.println("Veuillez entrer votre date de naissance sous forme \"dd/MM/yyyy\".");
        String dN = in.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d=sdf.parse(dN);
        System.out.println("Veuillez entrer votre mail.");
        String mail = in.nextLine();
        System.out.println("Veuillez entrer votre numero de telephone.");
        String tel = in.nextLine();
        System.out.println("Veuillez entrer votre adresse.");
        String add = in.nextLine();
        Client c = new Client(nom,prenom,civilite,d,mail,tel,add);
        Service.inscriptionClient(c);
        Service.envoyerMail(true, c);
        
        System.out.println();
        for(Client x : Service.getAllClients())
            System.out.println(x.getPrenom()+" "+x.getNom());
    }
}
