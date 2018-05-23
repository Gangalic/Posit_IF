package posit_if.OM;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Aurelien BELIN
 * @author Yuting SHU
 */

@Entity
public class Conversation implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date heure_dbt;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date heure_fin;
    
    private String commentaire;
    
    @ManyToOne
    private Client client;
    
    @ManyToOne
    private Employe employe;
    
    @ManyToOne
    private Medium medium;
    
    /*@Transient
    private final SimpleDateFormat SDF = new SimpleDateFormat("hh:mm");*/

    public Conversation() {
        this.heure_dbt = null;
        this.heure_fin = null;
        this.commentaire = null;
        this.client = null;
        this.employe = null;
        this.medium = null;
    }

    public Conversation(Client client, Employe employe, Medium medium) {
        this.heure_fin = null;
        this.heure_dbt = null;
        this.commentaire = "";
        this.client = client;
        this.employe = employe;
        this.medium = medium;
    }

    @Override
    public String toString() {
        return "Conversation{" + "heure_dbt=" + heure_dbt + ", heure_fin=" + heure_fin + ", commentaire=" + commentaire + ", client=" + client + ", employe=" + employe + ", medium=" + medium + '}';
    }
    
    //Getters

    public Date getHeure_dbt() {
        return heure_dbt;
    }

    public Date getHeure_fin() {
        return heure_fin;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Client getClient() {
        return client;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Medium getMedium() {
        return medium;
    }
    
    //Setters

    public void setHeure_dbt(Date heure_dbt) {
        this.heure_dbt = heure_dbt;
    }

    public void setHeure_fin(Date heure_fin) {
        this.heure_fin = heure_fin;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    
    
    
}
