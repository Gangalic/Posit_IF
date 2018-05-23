package posit_if.OM;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Aurelien BELIN
 * @author Yuting SHU
 */

@Entity
public class Employe implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    private String nom;
    
    private String prenom;
    
    private String tel;
    
    private int affectation;
    
    private boolean disponible;
    
    @ManyToMany
    private List<Medium> roles;
    
    @Version
    private int version;
    
    public Employe(){
        nom=null;
        prenom=null;
        tel=null;
        affectation=0;
        disponible=true;
        roles=null;
    }
    
    public Employe(String nom, String prenom, String tel, List<Medium> roles){
        this.nom=nom;
        this.prenom=prenom;
        this.tel=tel;
        this.affectation=0;
        this.disponible=true;
        this.roles=roles;
    }

    @Override
    public String toString() {
        return "Employe{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", affectation=" + affectation + ", roles=" + roles + '}';
    }
    
    //Getters

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTel() {
        return tel;
    }

    public int getAffectation() {
        return affectation;
    }

    public List<Medium> getRoles() {
        return roles;
    }
    
    public boolean getDisponible(){
        return disponible;
    }
    
    //Setters
    public void setAffectation(int aff){
        this.affectation=aff;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setRoles(List<Medium> roles) {
        this.roles = roles;
    }
    
    public void addRole(Medium m){
        this.roles.add(m);
    }
    
    public void removeRole(Medium m){
        this.roles.remove(m);
    }
    
    public void setDisponible(boolean b){
        this.disponible=b;
    }
}
