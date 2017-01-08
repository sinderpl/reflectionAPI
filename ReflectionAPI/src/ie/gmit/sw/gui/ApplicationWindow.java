package ie.gmit.sw.gui;

import ie.gmit.sw.Metric;

import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import javax.swing.*;
/**
 * 
 * @author G00313177
 * 
 * Initiates and handles the GUI that the user will interact with.
 * Allows for loading of JAR's through system file explorer.
 * Prints out the stability of each class in a JAR.
 *
 */
public class ApplicationWindow {
	
	//Main frame of the application
	private JFrame mainFrame;
	//JTable of contents
	private JTable contentTable;
	//Main JPanel
	private JPanel mainPanel;
	
	/**
	 * Basic constructor for initialising the window
	 */
	public ApplicationWindow(){
		//Create a window for the application
		mainFrame = new JFrame();
		mainFrame.setTitle("Class stability calculator");
		mainFrame.setSize(550, 500);
		mainFrame.setResizable(false);
		mainFrame.setLayout(new FlowLayout());
		
		//Set up the JPanel
		mainPanel = new JPanel();
		
		
		//Add the contents to the JFrame
		mainFrame.add(mainPanel);
		
		//Set the frame to be visible
		mainFrame.setVisible(true);
	}
	
	/**
	 * 
	 * Loads the JAR classes into the JTable
	 * 
	 * @param jarContents the hashMap with all the Metric data
	 */
	public void loadClasses(HashMap<String, Metric> jarContents){
		//Initialise the names of columns in the table
		Vector columnNames = new Vector();
		columnNames.add("Class Name");
		columnNames.add( "Is Interface");
		columnNames.add("Stability");
		
		//Array for the data to be displayed
		//Vector<String>  data = new Vector<String>();
		Vector data = new Vector();
		//Get the names from the map
		Set<String> names = jarContents.keySet();
		
		//Iterate through the names
		for (String name : names){
			//Get the Metric object into a temporary variable for data retrieval
			Metric tempMetric = jarContents.get(name);
			//Put the necessary data into a object array
			//String[] tempObjectArray = {tempMetric.getClassName().toString(), String.valueOf(tempMetric.isInterface()) , String.valueOf(tempMetric.getStability())};
			//Put the object array into the two dimmensional data array for the JTable
			data.add(tempMetric.getClassName().toString());
			data.add(String.valueOf(tempMetric.isInterface()));
			data.add(String.valueOf(tempMetric.getStability()));
		}
			
		//Create the table with the data
		contentTable = new JTable(data,columnNames);
				
		//Add the table to the JPanel
		mainPanel.add(contentTable);
		//Reload the frame
		mainFrame.setVisible(true);
	}
}
