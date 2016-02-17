

package terrarium;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;



public abstract class Organisme {
    protected int levenskracht;

    
    protected Coordinaat coordinaat;
    
    public Organisme(int rij,int kolom){
        setCoordinaat(rij,kolom);
        levenskracht=0;
        
    }
    public Coordinaat getCoordinaat() {
        return coordinaat;
    }

    public void setCoordinaat(int rij,int kolom) {
        this.coordinaat = new Coordinaat(rij,kolom);
    }

   
    
    //protected int rij;
    //protected int kolom;
    //+getters en setters + in subklassen initialiseren in de constructor (met check of plaats in array reeds bezet)
    protected boolean actionPerformed =false;

    public boolean isActionPerformed() {
        return actionPerformed;
    }

    public void setActionPerformed(boolean actionPerformed) {
        this.actionPerformed = actionPerformed;
    }

    public int getLevenskracht() {
        return levenskracht;
    }

    public void setLevenskracht(int levenskracht) {
        this.levenskracht += levenskracht;
    }
    public abstract void actie(Organisme organisme);
    
    public void beweegRandom(){
        if(!actionPerformed){
            Set<Coordinaat> ingenomenCoordinaten = Matrix.getInstance().getMap().keySet();
            List <Coordinaat> rondom = new ArrayList();
            
            Coordinaat rechts =new Coordinaat(coordinaat.getRij(),coordinaat.getKolom()+1);
            Coordinaat links=new Coordinaat(coordinaat.getRij(),coordinaat.getKolom()-1);
            Coordinaat onder=new Coordinaat(coordinaat.getRij()+1,coordinaat.getKolom());
            Coordinaat boven=new Coordinaat(coordinaat.getRij()-1,coordinaat.getKolom());
            
            if ((!ingenomenCoordinaten.contains(rechts))&&(rechts.getKolom()<6))
                rondom.add(rechts);
            if ((!ingenomenCoordinaten.contains(links))&&(links.getKolom()>=0))
                rondom.add(links);
            if ((!ingenomenCoordinaten.contains(onder))&&(onder.getRij()<6))
                rondom.add(onder);
            if ((!ingenomenCoordinaten.contains(boven))&&(boven.getRij()>=0))
                rondom.add(boven);
            
         if(rondom.size()>0){
            int nieuwePlaats = (int) (Math.random()*rondom.size());
            Matrix.getInstance().getMap().remove(coordinaat);
            this.coordinaat=rondom.get(nieuwePlaats);
            Matrix.getInstance().getMap().put(coordinaat, this);
         }
         
        }
        else actionPerformed=false;
    }
    
}
