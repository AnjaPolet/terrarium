package terrarium;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public final class Matrix {

    private static final Matrix UNIQUE_INSTANCE = new Matrix();  
    private static final int FORMAT = 10;
    private final OrganismeFactory factory;
    
    private final Map<Coordinaat, Organisme> map ;  
    private int vrijePlaatsen;
    private final Random randomGetal = new Random(); 

    public static int getFORMAT() {
        return FORMAT;
    }
    

    private Matrix() {
        
        map = new ConcurrentHashMap<>();
        factory = OrganismeFactory.getInstance();
        vrijePlaatsen = (FORMAT*FORMAT);
        
        
        voegToe(randomGetal.nextInt(10) + 10);
        display();
    }

    public static Matrix getInstance() {
        return UNIQUE_INSTANCE;
    }

    public Map<Coordinaat, Organisme> getMap() {
        return map;
    }


    

    private void voegToe(int aantal) {
        for (int i = 0; i < aantal; i++) {
            int soort = randomGetal.nextInt(4);  
            //findbugs: geen default in switch, maar kan eigenlijk niet voorkomen => toevoegen of niet?
            Type type;
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
                case 3:
                    type = Type.MENS;
                    break;
                default:
                    type = Type.PLANT;

            }
            addOrganisme(type);
        }
    }

    public int getVrijePlaatsen() {
        return vrijePlaatsen;
    }


//findbugs: may expose internal representation by returning reference to mutable object => stelt voor kopie door te geven
    // gezien we de array elke keer opnieuw genereren (de info zit eigenlijk in de map), moeten we daar rekening mee houden?
    // in theorie kunnen we de array ook hier initialiseren ipv er een klassevariabele van te maken => altijd de voorkeur. 
    public char [][] display() { 
        char[][] terrarium= new char[FORMAT][FORMAT];
        for (int i = 0; i < FORMAT; i++) {
            for (int j = 0; j < FORMAT; j++) {
                terrarium[i][j] = '.';
            }
        }
        for(Organisme o : map.values()){
            terrarium[o.getCoordinaat().getRij()][o.getCoordinaat().getKolom()]=o.toString().charAt(0);
        }
        return terrarium;
       
    }

    public void addOrganisme(Type type) {

        int rij = randomGetal.nextInt(FORMAT);
        int kolom = randomGetal.nextInt(FORMAT);

        while (map.containsKey(new Coordinaat(rij, kolom))) {
            rij = randomGetal.nextInt(FORMAT);
            kolom = randomGetal.nextInt(FORMAT);
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
        
        
        for (Entry<Coordinaat,Organisme> pair : map.entrySet()) {

            Coordinaat testKey = new Coordinaat(pair.getKey().getRij(), pair.getKey().getKolom() + 1);
            if (map.containsKey(testKey)) {
                pair.getValue().actie(map.get(testKey));
            }
        }
        //beweging indien geen actie gedaan
        for (Coordinaat c : map.keySet()) {
            map.get(c).beweegRandom();
        }
        
        //random aantal nieuwe planten toevoegen

        int aantalNieuwePlanten = randomGetal.nextInt(2)+1;
        for (int i = 0;
                i < aantalNieuwePlanten;
                i++) {
            if(vrijePlaatsen>0)
            addOrganisme(Type.PLANT);
        }
        for (Organisme o : map.values()){
            o.setMoved(false);
        }

       
        

    }
}
