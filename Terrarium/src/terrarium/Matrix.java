package terrarium;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Matrix {

    private static Matrix uniqueInstance = new Matrix();  // TODO enkele finals
    private static final int FORMAT = 6;
    private OrganismeFactory factory;
    private char[][] terrarium;
    private Map<Coordinaat, Organisme> map ;
    private int vrijePlaatsen;
    //int dag; lijkt niet zinvol om bij te houden

    private Matrix() {
        terrarium = new char[FORMAT][FORMAT];
        map = new ConcurrentHashMap<>();
        factory = new OrganismeFactory();
        vrijePlaatsen = (FORMAT*FORMAT);
        

        voegToe((int) (Math.random() * 5) + 5);
        display();
    }

    public static Matrix getInstance() {
        return uniqueInstance;
    }

    public Map<Coordinaat, Organisme> getMap() {
        return map;
    }

    public void setMap(Map<Coordinaat, Organisme> map) {
        this.map = map;
    }
    

    private void voegToe(int aantal) {
        for (int i = 0; i < aantal; i++) {
            int soort = (int) (Math.random() * 3);  // TODO kijk eens naar de class Random
            Type type = null;
            switch (soort) {
                case 0:
                    type = Type.PLANT;
                    break;
                case 1:
                    type = Type.CARNIVOOR;
                    break;
                case 2:
                    type = Type.HERBIVOOR;
                    break;

            }
            addOrganisme(type);
        }
    }

    public int getVrijePlaatsen() {
        return vrijePlaatsen;
    }

    public void setVrijePlaatsen(int vrijePlaatsen) {
        this.vrijePlaatsen = vrijePlaatsen;
    }

    public void display() {  // TODO naar main verhuizen
        for (int i = 0; i < FORMAT; i++) {
            for (int j = 0; j < FORMAT; j++) {
                terrarium[i][j] = '.';
            }
        }
        for(Organisme o : map.values()){
            terrarium[o.getCoordinaat().getRij()][o.getCoordinaat().getKolom()]=o.toString().charAt(0);
        }
        
        for(int rij = 0;rij<FORMAT;rij++){
            System.out.println();
        
            for (int kolom = 0;kolom<FORMAT;kolom++){
                System.out.print(" "+terrarium[rij][kolom]+" ");
            }
                
                }
    }

    public void addOrganisme(Type type) {

        int rij = (int) (Math.random() * FORMAT);
        int kolom = (int) (Math.random() * FORMAT);

        while (map.containsKey(new Coordinaat(rij, kolom))) {
            rij = (int) (Math.random() * FORMAT);
            kolom = (int) (Math.random() * FORMAT);
        }
        Organisme organisme = factory.createOrganisme(type, rij, kolom);
        map.put(organisme.getCoordinaat(), organisme);
        vrijePlaatsen--;

    }

    public void verwijderDodeOrganisme(Coordinaat c) {
        
                map.remove(c);
                vrijePlaatsen++;
  
    }

    public void volgendeDag() {
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            Coordinaat key = (Coordinaat)pair.getKey();
            Organisme value = (Organisme) pair.getValue();
            Coordinaat testKey = new Coordinaat(key.getRij(), key.getKolom() + 1);
            if (map.containsKey(testKey)) {
                value.actie(map.get(testKey));
            }
            
        }
        Iterator ite = map.entrySet().iterator();  
        while (ite.hasNext()){
            Map.Entry pair = (Map.Entry)ite.next();
            Coordinaat key = (Coordinaat)pair.getKey();
            map.get(key).beweegRandom();
        
        
        }

        int aantalNieuwePlanten = (int) (Math.random() * 3);
        for (int i = 0;
                i < aantalNieuwePlanten;
                i++) {
            if(vrijePlaatsen>0)
            addOrganisme(Type.PLANT);
        }

        display();
        

    }
}
