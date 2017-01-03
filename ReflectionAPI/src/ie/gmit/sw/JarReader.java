package ie.gmit.sw;
/**
 * 
 * @author G00313177
 *
 * A class to read in the Jar
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
	 */
	public void getJar(String jarName) throws FileNotFoundException, IOException, ClassNotFoundException{
		
		//Get the jar file
		File file = new File(jarName);
		
		//Convert the file to a url
		URL url = file.toURI().toURL();
        URL[] urls = new URL[]{url};
        
        //Initiate a class loader
        ClassLoader cLoader = new URLClassLoader(urls);

		JarInputStream in = new JarInputStream(new FileInputStream(new File(jarName)));
		JarEntry next = in.getNextJarEntry();
		while (next != null) {
		 if (next.getName().endsWith(".class")) {
		 String name = next.getName().replaceAll("/", "\\.");
		 name = name.replaceAll(".class", "");
		 if (!name.contains("$")) name.substring(0, name.length() - ".class".length());
		 System.out.println(name);
		 Class cls = Class.forName(name, false, cLoader);
		 ReflectionExample ex = new ReflectionExample(cls);
		 jarContents.put(name, new Metric());
		 }
		 next = in.getNextJarEntry();
		}
		in.close();
	}
	/**
	 * Returns the jarContents hash map
	 *@return HashMap<String, Metric>
	 */
	public HashMap getJarContents(){
		return jarContents;
	}
}
