package ie.gmit.sw;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author G00313177
 * 
 * Controller class to update and read in from the ApplicationWindow GUI view.
 * Allows for loose coupling through the MVC Pattern
 *
 */
public class Controller {
	//Create a instance of the GUI Window
	private ApplicationWindow appGui = new ApplicationWindow(this);

	/**
	 * Load the classes specified by the user input
	 * 
	 * @param jarName The complete path of the Jar to be loaded
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 */
	public void loadClasses(String jarName) throws FileNotFoundException, ClassNotFoundException, IOException{
		//Create a new reader instance
		JarReader reader = new JarReader();
		//Load the JAR in map form
		HashMap<String, Metric> jarContents = reader.getJar(jarName);
		//Put the Metrics into a JTable and add it to the GUI
		loadClassesJTable(jarContents);
	}

	/**
	 * 
	 * Loads the JAR classes into the JTable
	 * 
	 * @param jarContents the hashMap with all the Metric data
	 */
	public void loadClassesJTable(HashMap<String, Metric> jarContents){
		DefaultTableModel tm = new DefaultTableModel();
		
		//Add column names
		tm.addColumn("Class Name: ");
		tm.addColumn("Is interface: ");
		tm.addColumn("Stability: ");
		
	
		JTable table = new JTable(tm);
		//Set column sizes
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionBackground(Color.YELLOW);
	
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
			//Get the data from the hashMap and put it into string format
		      Set<String> keys = jarContents.keySet();
		      for (String key : keys){
		    	  //Temporary metric object for extracting data
		    	  Metric tempMetric = jarContents.get(key);
		    	  //String values
		    	  String className = tempMetric.getClassName();
		    	  String isInterface = Boolean.toString(tempMetric.isInterface());
		    	  String stability = Float.toString(tempMetric.getStability());
		    	  
		    	  //Create an array out of the strings
		    	  Object[] row = {className, isInterface, stability};
		    	  //Get the model of the table
		    	  DefaultTableModel model = (DefaultTableModel) table.getModel();
		    	  //Add the row to the table
		    	  model.addRow(row);
		     // }
		    
		}
	
		JScrollPane tableScroller = new JScrollPane(table);
		tableScroller.setPreferredSize(new java.awt.Dimension(485, 235));
		tableScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		//Get the mainFrame and add the new JTable
		appGui.getMainFrame().add(tableScroller, FlowLayout.LEFT);
}
}