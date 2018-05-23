package posit_if.OM;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Aurelien BELIN
 * @author Yuting SHU
 */

@Entity
public class Client implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    private String nom;
    
    private String prenom;

    private String civilite;
    
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    
    @Column(unique=true)
    private String mail;
    
    private String tel;
    
    private String addresse;
    
    private String signe_z;
    
    private String signe_ch;
    
    private String couleur;
    
    private String totem;
    
    public Client(){
        nom=null;
        prenom=null;
        civilite=null;
        dateNaissance=null;
        mail=null;
        tel=null;
        addresse=null;
        signe_z=null;
        signe_ch=null;
        couleur=null;
        totem=null;
    }
    
    public Client(String nom, String prenom, String civilite, Date dN, String mail, String tel, String add){
        this.nom=nom;
        this.prenom=prenom;
        this.civilite=civilite;
        this.dateNaissance=dN;
        this.mail=mail;
        this.tel=tel;
        this.addresse=add;
        signe_z=null;
        signe_ch=null;
        couleur=null;
        totem=null;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", civilite=" + civilite + ", dateNaissance=" + dateNaissance + ", mail=" + mail + ", tel=" + tel + ", addresse="+addresse+", signe_z=" + signe_z + ", signe_ch=" + signe_ch + ", couleur=" + couleur + ", totem=" + totem + '}';
    }
    
    //Getters
    public String getMail(){
        return mail;
    }

    public int getId() {
        return id;
    }

    public String getAddresse() {
        return addresse;
    }
    
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getCivilite() {
        return civilite;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getTel() {
        return tel;
    }

    public String getSigne_z() {
        return signe_z;
    }

    public String getSigne_ch() {
        return signe_ch;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getTotem() {
        return totem;
    }
    
    //Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setSigne_z(String signe_z) {
        this.signe_z = signe_z;
    }

    public void setSigne_ch(String signe_ch) {
        this.signe_ch = signe_ch;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setTotem(String totem) {
        this.totem = totem;
    }
    
    
}
