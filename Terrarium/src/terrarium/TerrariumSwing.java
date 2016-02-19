package terrarium;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TerrariumSwing extends JFrame implements ActionListener {

    private JPanel bedienigsPaneel;
    private JPanel terrariumPaneel;
    private JButton volgendeDag;
    private JButton stop;
    private JLabel[][] terrariumArray;
    private final Matrix matrix = Matrix.getInstance();
    private char[][] input;
    private final static ImageIcon LEEG = new ImageIcon("C:\\startbestanden\\img\\leeg.jpg");
    private final static ImageIcon PLANT = new ImageIcon("C:\\startbestanden\\img\\plant.jpg");
    private final static ImageIcon HERBIVOOR = new ImageIcon("C:\\startbestanden\\img\\herbivoor.png");
    private final static ImageIcon CARNIVOOR = new ImageIcon("C:\\startbestanden\\img\\carnivoor.png");
    private final static ImageIcon MAN = new ImageIcon("C:\\startbestanden\\img\\man.png");
    private final static ImageIcon VROUW = new ImageIcon("C:\\startbestanden\\img\\girl.png");
    private final Map<Character, ImageIcon> karakterIconMap = new HashMap<>();

    public TerrariumSwing() {
        setTitle("Terrarium");
    }

    public static void main(String[] args) {
        TerrariumSwing frame = new TerrariumSwing();
        frame.createGUI();
        frame.pack();
        
        frame.setVisible(true);

    }

    private void wijzigAfbeelding(char[][] letters) {

        for (int rij = 0; rij < letters[0].length; rij++) {
            for (int kolom = 0; kolom < letters.length; kolom++) {

                terrariumArray[rij][kolom].setIcon(karakterIconMap.get(letters[rij][kolom]));

            }

        }

    }

    private void createGUI() {
        karakterIconMap.put('.', LEEG);
        karakterIconMap.put('P', PLANT);
        karakterIconMap.put('C', CARNIVOOR);
        karakterIconMap.put('H', HERBIVOOR);
        karakterIconMap.put('M', MAN);
        karakterIconMap.put('V', VROUW);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        input = matrix.display();
        terrariumArray = new JLabel[input[0].length][input.length];
        terrariumPaneel = new JPanel();
        terrariumPaneel.setLayout(new GridLayout(input[0].length, input.length));
        for (int rij = 0; rij < input[0].length; rij++) {
            for (int kolom = 0; kolom < input[0].length; kolom++) {
                terrariumArray[rij][kolom] = new JLabel();
                terrariumPaneel.add(terrariumArray[rij][kolom]);
            }
        }
        wijzigAfbeelding(input);
        add(terrariumPaneel, BorderLayout.CENTER);

        bedienigsPaneel = new JPanel();
        volgendeDag = new JButton("Volgende Dag");
        volgendeDag.addActionListener(this);
        stop = new JButton("Stop");
        stop.addActionListener(this);
        bedienigsPaneel.add(volgendeDag);
        bedienigsPaneel.add(stop);
        add(bedienigsPaneel, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == volgendeDag) {
            matrix.volgendeDag();
            input = matrix.display();
            wijzigAfbeelding(input);

        } else {
            System.exit(0);
        }

    }

}
