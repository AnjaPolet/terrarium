

package terrarium;



public class Carnivoor extends Organisme {
    public Carnivoor(int rij,int kolom){
        super(rij,kolom);
    }
    @Override
    public String toString() {
        return "C";
    }
    @Override
    public void actie(Organisme organisme){
        if (organisme instanceof Herbivoor){
            this.levenskracht+=organisme.getLevenskracht();
            Matrix.getInstance().verwijderDodeOrganisme(organisme.getCoordinaat());
        }
        else if (organisme instanceof Carnivoor){
            if(this.levenskracht>organisme.getLevenskracht()){
                this.levenskracht+=organisme.getLevenskracht();
                organisme.setLevenskracht(0);
            }    
            else if (this.levenskracht<organisme.getLevenskracht()){
                organisme.setLevenskracht(organisme.getLevenskracht()+this.levenskracht);
                this.levenskracht=0;
            }
            
        }
        else if (organisme instanceof Mens) {
             
                if (this.levenskracht > organisme.getLevenskracht()) {
                    this.levenskracht += organisme.getLevenskracht();
                    Matrix.getInstance().verwijderDodeOrganisme(organisme.getCoordinaat());
                } else if (this.levenskracht < organisme.getLevenskracht()) {
                    organisme.setLevenskracht(organisme.getLevenskracht() + this.levenskracht);
                    Matrix.getInstance().verwijderDodeOrganisme(this.getCoordinaat());
                }
        }
        actionPerformed = true;
    }
    
}
