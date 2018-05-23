package posit_if.OM;

import javax.persistence.Entity;

/**
 *
 * @author Aurelien BELIN
 * @author Yuting SHU
 */

@Entity
public class Tarologue extends Medium{
    
    private String cartes;
    
    public Tarologue(){
        super();
        cartes=null;
    }
    
    public Tarologue(String nom, String bio,String cartes){
        super(nom,bio);
        this.cartes=cartes;
    }

    public String getCartes() {
        return cartes;
    }

    @Override
    public String toString() {
        return "Tarologue{" +super.toString()+", cartes=" + cartes + '}';
    }            
}
