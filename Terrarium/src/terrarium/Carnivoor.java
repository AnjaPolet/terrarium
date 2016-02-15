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
public class Carnivoor extends Organisme {
    public Carnivoor(){
        levenskracht = 0;
    }
    @Override
    public String toString() {
        return "C";
    }
    @Override
    public void actie(Organisme organisme){
        if (organisme instanceof Herbivoor){
            this.levenskracht+=organisme.getLevenskracht();
            organisme.setLevenskracht(-organisme.getLevenskracht()-1);
        }
        else if (organisme instanceof Carnivoor){
            if(this.levenskracht>organisme.getLevenskracht()){
                this.levenskracht+=organisme.getLevenskracht();
                organisme.setLevenskracht(this.levenskracht);
            }    
            else if (this.levenskracht<organisme.getLevenskracht()){
                this.levenskracht-=organisme.getLevenskracht();
                organisme.setLevenskracht(this.levenskracht);
            }
            
        }
        actionPerformed = true;
    }
    
}
