package posit_if.OM;

import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author Aurelien BELIN
 * @author Yuting SHU
 */

@Entity
public class Astrologue extends Medium {
    
    private String ecole;
    private int promotion;
    
    public Astrologue(){
        super();
        ecole=null;
        promotion=0;
    }
    
    public Astrologue(String nom, String bio, String ecole,int promotion){
        super(nom,bio);
        this.ecole=ecole;
        this.promotion=promotion;
    }
    
    //Getters

    public String getEcole() {
        return ecole;
    }

    public int getPromotion() {
        return promotion;
    }

    @Override
    public String toString() {
        return "Astrologue{" +super.toString()+ ", ecole=" + ecole + ", promotion=" + promotion + '}';
    }

}
