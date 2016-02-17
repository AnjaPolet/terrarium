

package terrarium;



public class Herbivoor extends Organisme {
    
    public Herbivoor(int rij,int kolom){
        super(rij,kolom);
    }
    @Override
    public String toString() {
        return "H";
    }
    @Override
    public void actie(Organisme organisme){
        if (organisme instanceof Plant){
            this.levenskracht++;
            Matrix.getInstance().verwijderDodeOrganisme(organisme.getCoordinaat());
            
        }    
        else if (organisme instanceof Herbivoor){
            if(Matrix.getInstance().getVrijePlaatsen()>0)
            Matrix.getInstance().addOrganisme(Type.HERBIVOOR);
        }
        actionPerformed = true;
    }

    

}
