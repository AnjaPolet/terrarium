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
public abstract class Organisme {
    protected int levenskracht;
    protected Matrix matrix; 
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
    
}
