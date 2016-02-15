/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terrarium;

import java.util.Scanner;

/**
 *
 * @author Anja
 */

public class Terrarium {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Matrix matrix = Matrix.getInstance();
        Scanner keyboard = new Scanner(System.in);
        while (keyboard.nextLine()!="s")
            matrix.volgendeDag();
    }
    
}
