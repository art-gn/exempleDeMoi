/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableauswing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author clement
 */
public class CatalogueDePannes extends AbstractTableModel{
    private ArrayList<Panne> pannes = new ArrayList<>();
    
    private static final String[] COLUMN_NAMES = {"id", "nom"};
    
    public void loadFromCode(){
        pannes.add(new Panne(1, "Batterie HS"));
        pannes.add(new Panne(2, "Phares grillés"));
        pannes.add(new Panne(3, "Radiateur percé"));
        pannes.add(new Panne(4, "Pneus à plat"));
        fireTableDataChanged();
    }
    
    public void loadFromCSV() throws FileNotFoundException, IOException{
        URL url = getClass().getResource("pannes.csv");
        File file = new File(url.getPath());
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";
        while((line = reader.readLine()) != null){
            String[] parts = line.split(",");
            pannes.add(new Panne(new Integer(parts[0]), parts[1]));
        }
        reader.close();
        fireTableDataChanged();
    }
    
    public void loadFromXML() throws Exception{
        URL url = getClass().getResource("pannes.xml");
        File file = new File(url.getPath());
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file);
        NodeList list = document.getElementsByTagName("panne");
        /*TransformerFactory tFactory =
        TransformerFactory.newInstance();
        Transformer transformer = 
        tFactory.newTransformer();

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(System.out);
        transformer.transform(source, result)*/
        for(int i = 0; i < list.getLength() ; i ++){
            Node node = list.item(i);
            pannes.add(
                new Panne(
                    new Integer(
                        node.getAttributes().getNamedItem("id").getTextContent()
                    ),
                    node.getTextContent()
                )
            );
        }
        fireTableDataChanged();
        
    }

    public void loadFromJSON() throws IOException, ParseException{
        URL url = getClass().getResource("pannes.json");
        File file = new File(url.getPath());
        
        JSONParser parser = new JSONParser();
        
        JSONObject obj = (JSONObject) parser.parse(new FileReader(file));
        
        JSONArray pannesJSON = (JSONArray) obj.get("pannes");
        System.out.println(pannesJSON);

        for(Object panne : pannesJSON) {
            JSONObject panneJSON = (JSONObject) panne;
            pannes.add(
                new Panne(
                      (long) panneJSON.get("id"),
                      (String) panneJSON.get("nom")));
        }
        fireTableDataChanged();
    }
    

    public int getRowCount() {
        return pannes.size();
    }

    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0)
            return pannes.get(rowIndex).getId();
        return pannes.get(rowIndex).getNom();
    }

    @Override
    public String getColumnName(int column) {
        
        return COLUMN_NAMES[column];
    }



    void add(int text1, String text0) {
       pannes.add(new Panne(text1, text0));
       fireTableDataChanged();
    }

    
    
}
