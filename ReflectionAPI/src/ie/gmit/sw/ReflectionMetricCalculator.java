package ie.gmit.sw;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
/**
 * 
 * @author G00313177
 * 
 * Calculates the specific in and out coming degrees for the metrics.
 * 
 *
 */

public class ReflectionMetricCalculator {
	
	
	//Private map for local storage of the Metrics
	private HashMap<String, Metric> metricMap; //TODO check if this works (pass by reference)
	//Class loader for dynamic loading
	private ClassLoader cLoader;
	
	
	/**
	 * Constructor
	 * @param Metric map is passed in for local usage;
	 * @param classLoader is passed in to dynamically load the classes
	 * @throws ClassNotFoundException 
	 */
	public ReflectionMetricCalculator(HashMap<String, Metric> metricMap, ClassLoader cLoader) throws ClassNotFoundException{
		//Assign the variables
		this.metricMap = metricMap;
		this.cLoader = cLoader;
		
		//Call the relevant methods to calculate the metrics
		calculateInDegree();
	}
	
	/**
	 * Method used for calculating the in degree of the current class
	 * @throws ClassNotFoundException 
	 */
	private void calculateInDegree() throws ClassNotFoundException{
		Set<String> classNames = metricMap.keySet();
		
		
		for (String name : classNames){
			
			//Dynamically loading the class
			Class cls = Class.forName(name, false, cLoader);
			//Metric for putting back in the hashMap
			Metric currentMetric = (Metric) metricMap.get(name);
			
			//Added a field to check whether it is a interface or not
			if(cls.isInterface()){
				currentMetric.setInterface(true);
			}
			
			
			
			
			//Check for implemented interfaces
			
			//Get all the interfaces implemented by the class
			Class[] implementedInterfaces = cls.getInterfaces();
			
			//Query through the interfaces
			for (Class clsInterface : implementedInterfaces){
				//If statment to check whether the interface is implemented
				if (classNames.contains(clsInterface.getName())){
					//Increment the out degree counter
					currentMetric.setOutCounter(currentMetric.getOutCounter()+1);
				}
				System.out.println(currentMetric.getOutCounter());
			}
			
			System.out.println(cls.toString());
			
			//Put the metric back in the map
			metricMap.put(name, currentMetric);
		}
	}

}
