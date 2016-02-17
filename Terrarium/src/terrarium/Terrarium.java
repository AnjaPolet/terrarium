

package terrarium;

import java.util.Scanner;




public class Terrarium {


    
    public static void main(String[] args) {
        Matrix matrix = Matrix.getInstance();
        Scanner keyboard = new Scanner(System.in);
        while (!keyboard.nextLine().equals("s"))
            matrix.volgendeDag();
        
        //probleem: verplaatsen loopt nog steeds verkeerd
        //analyse: denk dat soms 2 verplaatsingen gebeuren ipv 1
        //oorzaak: als P of V naar beneden of naar rechts wordt verplaatst, komt
        //deze P of V in de array 2 keer of zelfs meer aan de beurt
        //voorgestelde oplossing:
        //organisme coördinaten geven die random gegenereerd worden in constructor
        //organismen in een lijst steken ipv rechtstreeks in een array[][]
        //lijst telkens overlopen en obv de coördinaten in een char[][] het juiste 
        //teken plaatsen
        
    }
    
}
