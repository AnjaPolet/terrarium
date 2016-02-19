

package terrarium;

import java.util.Scanner;




public class Terrarium {


    
    public static void main(String[] args) {
       
        Matrix matrix = Matrix.getInstance();
        Scanner keyboard = new Scanner(System.in);
        char [][] terrarium = matrix.display();
        for(int rij = 0;rij<terrarium[0].length;rij++){
            System.out.println();
        
            for (int kolom = 0;kolom<terrarium.length;kolom++){
                System.out.print(" "+terrarium[rij][kolom]+" ");
            }
        }
        while (!keyboard.nextLine().equals("s")){
            matrix.volgendeDag();
            terrarium=matrix.display();
        
            for(int rij = 0;rij<terrarium[0].length;rij++){
            System.out.println();
            
            for (int kolom = 0;kolom<terrarium.length;kolom++){
                System.out.print(" "+terrarium[rij][kolom]+" ");
            }
            }    
                
        }
        
        
    }
    
}
