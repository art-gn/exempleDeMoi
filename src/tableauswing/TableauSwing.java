/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableauswing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author clement
 */
public class TableauSwing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        // TODO code application logic here
        
        JFrame fenetre = new JFrame();
        Container footer = new Container();
        Container c = fenetre.getContentPane();
        fenetre.setSize(400, 400);
        footer.setLayout(new GridLayout(1, 3));
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        CatalogueDePannes sourceDonneesPannes = new CatalogueDePannes();
        
        
        
        //add des champs d'input
        sourceDonneesPannes.loadFromJSON();    
        JTextField entry1 = new JTextField(10);
        JTextField entry2 = new JTextField(10);
        JButton leplus = new JButton("+");
        leplus.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int i;
                i = Integer.parseInt(entry1.getText()) ;
                System.out.println("oui");
                sourceDonneesPannes.add(i, entry2.getText());
            }

       
        } );
        JTable table = new JTable(sourceDonneesPannes);

        c.add(new JScrollPane(table));
        c.add(footer,BorderLayout.SOUTH);
        footer.add(entry1);
        footer.add(entry2);
        footer.add(leplus);
        sourceDonneesPannes.loadFromXML();
        
        
        
        fenetre.setVisible(true);
       
        
        
         
        sourceDonneesPannes.loadFromCode();
        
        sourceDonneesPannes.loadFromCSV();
        


    }
    
}
