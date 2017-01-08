package ie.gmit.sw;

import ie.gmit.sw.gui.ApplicationWindow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * 
 * @author G00313177
 *
 *A runner for the reflection API
 */
public class ReflectionRunner {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		//Initialise the JarReader instance
		JarReader reader  = new JarReader();
		
		
		//Get the specified jar
		HashMap<String, Metric> jarContents = reader.getJar("/home/pancakemutiny/Desktop/string-service.jar");
		ApplicationWindow appGui = new ApplicationWindow();
		appGui.loadClasses(jarContents);
		
	}
}
