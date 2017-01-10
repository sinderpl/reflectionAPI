package ie.gmit.sw;
/**
 * 
 * @author G00313177
 *
 * Reads in the JAR and transfers the data to a ReflectionMetricCalculator for calculations
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.jar.JarInputStream;
import java.util.jar.JarEntry;

public class JarReader {
	
	//private ArrayList<Class> jarContents = new ArrayList<Class>();
	private HashMap<String, Metric> jarContents = new HashMap<String, Metric>();
	
	/**
	 * Constructor, no variables are necessary to initialise this
	 */
	public JarReader(){}
	/**
	 * Retrieves the jar from the specified jar, put's each entry into an array
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 * 
	 * @param jarName The full system path to the class that is to be loaded.
	 * 
	 * @return HashMap<String, Metric> A hash map with all the Metrics from the JAR calculated
	 */
	public HashMap<String, Metric> getJar(String jarName) throws FileNotFoundException, IOException, ClassNotFoundException{
		
		//Get the jar file
		File file = new File(jarName);
		
		//Convert the file to a url
		URL url = file.toURI().toURL();
        URL[] urls = new URL[]{url};
        
        //Initiate a class loader
        ClassLoader cLoader = new URLClassLoader(urls);
        	
        //Read in the input class
		JarInputStream in = new JarInputStream(new FileInputStream(file));
		JarEntry next = in.getNextJarEntry();
		
		//Loop through the jar until all classes have been inspected
		while (next != null) {
		 if (next.getName().endsWith(".class")) {
		 String name = next.getName().replaceAll("/", "\\.");
		 name = name.replaceAll(".class", "");
		 
		 //delete the package from the name
		 String className = name.replaceAll(".*\\.", "");
		 if (!name.contains("$")) name.substring(0, name.length() - ".class".length());
		 jarContents.put(name, new Metric(className));
		 }
		 next = in.getNextJarEntry();
		}
		in.close();
		
		//Send the HashMap and the Class Loader to the Calculator class for computing
		ReflectionMetricCalculator metricCalc = new ReflectionMetricCalculator(jarContents, cLoader);
		return jarContents;
	}
	/**
	 * Returns the jarContents hash map
	 *@return HashMap<String, Metric>
	 */
	public HashMap getJarContents(){
		return jarContents;
	}
}
