/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableauswing;

import java.awt.Container;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;

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
        
        Container c = fenetre.getContentPane();
        fenetre.setSize(400, 400);
 
        
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        CatalogueDePannes sourceDonneesPannes = new CatalogueDePannes();
        
        
        sourceDonneesPannes.loadFromJSON();    
        
        JTable table = new JTable(sourceDonneesPannes);

        c.add(new JScrollPane(table));
        
        sourceDonneesPannes.loadFromXML();
        
        fenetre.setVisible(true);
       
        
        
         
        sourceDonneesPannes.loadFromCode();
        
        sourceDonneesPannes.loadFromCSV();
        


    }
    
}
