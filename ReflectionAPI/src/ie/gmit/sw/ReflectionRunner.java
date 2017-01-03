package ie.gmit.sw;

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
		JarReader reader  = new JarReader();
		reader.getJar("/home/pancakemutiny/Desktop/string-service.jar");
		
		
	}
}
