/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terrarium;

/**
 *
 * @author Anja
 */
public class Herbivoor extends Organisme {
    
    public Herbivoor(){
        levenskracht = 0;
        
    }
    @Override
    public String toString() {
        return "H";
    }
    @Override
    public void actie(Organisme organisme){
        if (organisme instanceof Plant){
            this.levenskracht++;
            organisme.setLevenskracht(-1);
        }    
        else if (organisme instanceof Herbivoor){
            Matrix.getInstance().addOrganisme(Type.HERBIVOOR);
        }
        actionPerformed = true;
    }

}
