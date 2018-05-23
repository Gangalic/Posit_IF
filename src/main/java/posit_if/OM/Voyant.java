package posit_if.OM;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Aurelien BELIN
 * @author Yuting SHU
 */

@Entity
public class Voyant extends Medium implements Serializable{
    
    private String support;
    
    public Voyant(){
        super();
        support=null;
    }
    
    public Voyant(String nom, String bio,String sup){
        super(nom,bio);
        this.support=sup;
    }

    //Setter and getter
    public String getSupport() {
        return support;
    }

    @Override
    public String toString() {
        return "Voyant{" +super.toString()+ ", support=" + support + '}';
    }
    
}
