package ie.gmit.sw;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
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
	//Status label
	JLabel statusLabel;
	//Check whether a JAR file has been chosen
	boolean isJar = false;
	//Controller reference
	Controller c ;
	
	/**
	 * Basic constructor for initialising the window
	 */
	public ApplicationWindow(final Controller c){
		//Assign the controller for reference
		this.c = c;
		//Some of the code has been adapted from the moodle GUI example
		//Create a window for the application
		mainFrame = new JFrame();
		mainFrame.setTitle("Class stability calculator");
		mainFrame.setSize(550, 500);
		mainFrame.setResizable(false);
		mainFrame.setLayout(new FlowLayout());
		
		//Set up the JPanel
		mainPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		mainPanel.setBorder(new javax.swing.border.TitledBorder("Select the JAR to be examined"));
		mainPanel.setPreferredSize(new java.awt.Dimension(500, 100));
		mainPanel.setMaximumSize(new java.awt.Dimension(500, 100));
		mainPanel.setMinimumSize(new java.awt.Dimension(500, 100));
		
		//Text field representing the JAR data
		final JTextField txtFileName =  new JTextField(20);
		txtFileName.setPreferredSize(new java.awt.Dimension(100, 30));
		txtFileName.setMaximumSize(new java.awt.Dimension(100, 30));
		txtFileName.setMargin(new java.awt.Insets(2, 2, 2, 2));
		txtFileName.setMinimumSize(new java.awt.Dimension(100, 30));
		
		
		//Browse button
		JButton btnChooseFile = new JButton("Find JAR");
		btnChooseFile.setToolTipText("Select the JAR to be examined");
        btnChooseFile.setPreferredSize(new java.awt.Dimension(90, 30));
        btnChooseFile.setMaximumSize(new java.awt.Dimension(90, 30));
        btnChooseFile.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnChooseFile.setMinimumSize(new java.awt.Dimension(90, 30));
		btnChooseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
        		JFileChooser fc = new JFileChooser("./");
        		int returnVal = fc.showOpenDialog(mainFrame);
            	if (returnVal == JFileChooser.APPROVE_OPTION) {
                	File file = fc.getSelectedFile().getAbsoluteFile();
                	String name = file.getAbsolutePath(); 
                	txtFileName.setText(name);
                	System.out.println("You selected the following file: " + name);
                	
                	
                	//Verify whether the file selected is actually a JAR
                	int dotPosition = name.lastIndexOf(".");
                	String extension = "";
                	if (dotPosition != -1) {
                	    extension = name.substring(dotPosition);
                	    if(extension.equals(".jar")){
                	    	isJar = true;
                	    }
                	    else{
                	    	isJar = false;
                	    }
                	    System.out.println("extension is :" + extension);
                	}
                	
                	
                	//Verify whether a JAR has been selected and feed back to user
                	if(isJar){
                		statusLabel.setText("Status: You have selected a JAR file, loading now.");
                		//Notify the controller and add the output to the Frame
                		try {
							c.loadClasses(name);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                	}
                	else{
                		statusLabel.setText("Status: The file selected is not a JAR, please try again");
                	}
                }
			}
            
        });
		
		//Feedback JLabel
		statusLabel = new JLabel("Status: Please choose JAR file.");
		
		//Add content to the main JPanel
		mainPanel.add(txtFileName);
		mainPanel.add(btnChooseFile);
		mainPanel.add(statusLabel);
		
		//Add the main JPanel to the JFrame
		mainFrame.add(mainPanel);
		
		//Set the frame to be visible
		mainFrame.setVisible(true);
	}
	
	
	/*
	 * Getter for the application MainFrame
	 * 
	 */
	public JFrame getMainFrame(){
		return mainFrame;
	}
	
	//End of code
}
