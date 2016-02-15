package terrarium;

import java.util.Random;

public class Matrix {

    private static Matrix uniqueInstance = new Matrix();
    private static final int FORMAT = 6;
    private OrganismeFactory factory;
    private Organisme[][] terrarium;
    //int dag; lijkt niet zinvol om bij te houden

    private Matrix() {
        terrarium = new Organisme[FORMAT][FORMAT];
        factory = new OrganismeFactory();
        voegToe((int) (Math.random() * 5) + 5);
        display();
    }

    public static Matrix getInstance() {
        return uniqueInstance;
    }

    private void voegToe(int aantal) {
        for (int i = 0; i < aantal; i++) {
            int kolom = (int) (Math.random() * FORMAT);
            int rij = (int) (Math.random() * FORMAT);
            int soort = (int) (Math.random() * 3);
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
            if (terrarium[rij][kolom] == null) {
                terrarium[rij][kolom] = factory.createOrganisme(type);
            }
        }
    }

    public void display() {
        for (int i = 0; i < FORMAT; i++) {
            System.out.println();
            for (int y = 0; y < FORMAT; y++) {
                if (terrarium[i][y] != null) {
                    System.out.print(terrarium[i][y]);
                } else {
                    System.out.print(".");
                }
            }

        }
    }

    public void addOrganisme(Type type) {
        Organisme organisme = factory.createOrganisme(type);
        int rij = (int) (Math.random() * FORMAT);
        int kolom = (int) (Math.random() * FORMAT);
        while (terrarium[rij][kolom] != null) {
            rij = (int) (Math.random() * FORMAT);
            kolom = (int) (Math.random() * FORMAT);

        }
        terrarium[rij][kolom] = organisme;

    }

    public void verwijderDodeOrganisme() {
        for (int i = 0; i < FORMAT; i++) {
            for (int y = 0; y < FORMAT; y++) {
                if ( terrarium[i][y] != null) {
                    if (terrarium[i][y].getLevenskracht() < 0)
                        terrarium[i][y] = null;
                }
            }
        }
    }

    public boolean[] plaatsenVrijRondom(int rij, int kolom) {
        boolean[] vrij = new boolean[4];
        //boven
        if (rij != 0 && terrarium[rij - 1][kolom] == null) {
            vrij[0] = true;
        }
        //onder
        if (rij < FORMAT - 1 && terrarium[rij + 1][kolom] == null) {
            vrij[1] = true;
        }
        //links
        if (kolom != 0 && terrarium[rij][kolom - 1] == null) {
            vrij[2] = true;
        }
        //rechts
        if (kolom < FORMAT - 1 && terrarium[rij][kolom + 1] == null) {
            vrij[3] = true;
        }
        return vrij;
    }

    public void beweegRandom(int rij, int kolom) {
        int beweging = (int) (Math.random() * 4);
        boolean[] vrijePlaatsen = plaatsenVrijRondom(rij, kolom);
        boolean kanBewegen=false;
        for (boolean b : vrijePlaatsen)
            if(b)
                kanBewegen=true;
        if(kanBewegen){
        while (!vrijePlaatsen[beweging]) {
            beweging = (int) (Math.random() * 4);
        }
        switch(beweging){
            case 0:
                terrarium[rij - 1][kolom]=terrarium[rij][kolom];
                terrarium[rij][kolom]=null;
                break;
            case 1:
                terrarium[rij + 1][kolom]=terrarium[rij][kolom];
                terrarium[rij][kolom]=null;
                break;
            case 2:
                terrarium[rij][kolom - 1]=terrarium[rij][kolom];
                terrarium[rij][kolom]=null;
                break;
            case 3:
                terrarium[rij][kolom + 1]=terrarium[rij][kolom];
                terrarium[rij][kolom]=null;
                break;
                
                
        }
        }

    }

    public void volgendeDag() {
        
        for (int i = 0; i < FORMAT; i++) {
            for (int y = 0; y < FORMAT - 1; y++) {
                if (terrarium[i][y] != null && terrarium[i][y + 1] != null) {
                    terrarium[i][y].actie(terrarium[i][y + 1]);
                    
                    
                } 
                
            }        
        }        
        for (int x = 0; x < FORMAT; x++) {
            for (int z = 0; z < FORMAT ; z++) {
                if (terrarium[x][z] != null && !(terrarium[x][z]instanceof Plant)){
                    if (!terrarium[x][z].isActionPerformed())
                        beweegRandom(x, z);
                    else terrarium[x][z].setActionPerformed(false);
                        }
                
                        
                    
                
            }
        }
            
        
        

        int aantalNieuwePlanten = (int) (Math.random() * 3);
        for (int i = 0;
                i < aantalNieuwePlanten;
                i++) {
            addOrganisme(Type.PLANT);
        }

        verwijderDodeOrganisme();

        display();
    }

}
