package posit_if.OM;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Aurelien BELIN
 * @author Yuting SHU
 */

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Medium implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Column(unique=true)
    private String nom;
    
    private String bio;
    
    public Medium(){
        nom=null;
        bio=null;
    }
    
    public Medium(String nom, String bio){
        this.nom=nom;
        this.bio=bio;
    }

    @Override
    public String toString() {
        return "Medium{" + "id=" + id + ", nom=" + nom + ", bio=" + bio + '}';
    }   
    
    //Getters

    public String getNom() {
        return nom;
    }

    public String getBio() {
        return bio;
    }
    
    //Setters

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }    
       
}
