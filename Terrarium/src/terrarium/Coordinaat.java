
package terrarium;


public class Coordinaat {
    private final int rij;  
    private final int kolom; 
    public Coordinaat(int rij, int kolom){
        this.rij=rij;
        this.kolom=kolom;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.rij;
        hash = 67 * hash + this.kolom;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinaat){
        Coordinaat c = (Coordinaat)obj;
        return this.rij==c.getRij()&&this.kolom == c.getKolom();
        }
        else return false;
    }

    public int getRij() {
        return rij;
    }

    

    public int getKolom() {
        return kolom;
    }

    
}
