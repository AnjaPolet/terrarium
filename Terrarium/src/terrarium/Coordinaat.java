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
public class Coordinaat {
    private int rij;  // TODO final
    private int kolom;  // TODO final
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
        Coordinaat c = (Coordinaat)obj;
        return this.rij==c.getRij()&&this.kolom == c.getKolom();
    }

    public int getRij() {
        return rij;
    }

    public void setRij(int rij) {
        this.rij = rij;
    }

    public int getKolom() {
        return kolom;
    }

    public void setKolom(int kolom) {
        this.kolom = kolom;
    }
}
