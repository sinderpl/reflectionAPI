package ie.gmit.sw;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Arrays;
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
	//Set for easier access to class names
	private Set<String> classNames;
	
	
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
		classNames = metricMap.keySet();
		
		//Call the relevant methods to calculate the metrics
		calculateDegrees();
	}
	
	/**
	 * Method used for calculating the degrees of the current class list
	 * @throws ClassNotFoundException 
	 */
	private void calculateDegrees() throws ClassNotFoundException{
		for (String name : classNames){
			
			//Dynamically loading the class
			Class cls = Class.forName(name, false, cLoader);
			
			//Metric for putting back in the hashMap
			Metric currentMetric = (Metric) metricMap.get(name);
			checkInterfaces(currentMetric, cls);
			checkConstructors(currentMetric, cls);
			checkmethodFields(currentMetric, cls);
			checkClassFields(currentMetric, cls);
			
		}
	}
	
	
	
	/**
	 * Checks whether the class implements any of the non standard interfaces and then increments,
	 * both the class and it's implemented interface.
	 * 
	 *  @param currentMetric Metric class used for inserting back into the hashmap
	 * @param cls Class object for use by the reflection API
	 */
	public void checkInterfaces(Metric currentMetric, Class cls){
		//Check for implemented interfaces
		
		
		//Added a field to check whether it is a interface or not
		if(cls.isInterface()){
			currentMetric.setInterface(true);
		}
		
		//Get all the interfaces implemented by the class
		Class[] implementedInterfaces = cls.getInterfaces();
		
		//Query through the interfaces
		for (Class clsInterface : implementedInterfaces){
			//If statment to check whether the interface is implemented
			if (classNames.contains(clsInterface.getName())){
				//Increment the out degree counter
				currentMetric.incrementOutCounter();
				
				//Increment the called class
				incrementCalledClass(clsInterface.getName());
			}
		}
		//Put the metric back in the map
		metricMap.put(cls.getName(), currentMetric);
	}
	
	
	
	/**
	 * Checks the constructors for any implemented classes and increments the classes respectively,
	 * will ignore any interfaces as those have already been checked 
	 * 
	 *  @param currentMetric Metric class used for inserting back into the hashmap
	 * @param cls Class object for use by the reflection API
	 */
	public void checkConstructors(Metric currentMetric,  Class cls){
		//Get the classes used in the constructors
		
		//Get all constructors implemented
		Constructor[] consArray = cls.getConstructors();
		
		//Get the params from the constructors
		for (Constructor cons : consArray){
			Class[]	params = cons.getParameterTypes();
			
			//I know for loop inside for loop is bad but will work for the moment
			//TODO change this later
			for(Class classParam : params){
				
				/**
				//Check whether it is a interface as we do not need to check those again
				//check whether the name is contained in our custom list of classes
				if (classParam.isInterface() == false && classNames.contains(classParam.getName())){
				**/
				//Checks the class name against our custom parameter list
				if (classNames.contains(classParam.getName())){
					//
					//Increment current class
					currentMetric.incrementOutCounter();
					//Increment the class that is referenced to this
					incrementCalledClass(classParam.getName());
				}
			}
		}
		//Put the metric back in the map
		metricMap.put(cls.getName(), currentMetric);
	}
	
	/**
	 * Small method to find and increment the in Counter of classes that are called,
	 * this is created to avoid the repetition of code
	 * @param className Name of the class that is to be implemented  (Key in the Hash Map)
	 */
	public void incrementCalledClass(String className){
		//Find the metric and put it in a temp metric
		Metric tempMetric = metricMap.get(className);
		// Increment the counter
		tempMetric.IncrementInCounter();
		//Put the metric back in the map
		metricMap.put(className, tempMetric);
	}

	/**
	 * Get the method fields and check for any implementations 
	 * (in and return parameters), of classes we want
	 * 
	 * @param currentMetric Metric class used for inserting back into the hashmap
	 * @param cls Class object for use by the reflection API
	 */
	public void checkmethodFields(Metric currentMetric,  Class cls){
		
		//Get the methods in the class
		Method[] methods = cls.getMethods();
		
		//Loop through each method
		for (Method mth : methods){
			
			//Get the parameters in from the methods
			Class[] fields = mth.getParameterTypes();
			//Query through each field
			for (Class field : fields){
				
				//Check if it is a custom class
				if (classNames.contains(field.getName())){
					
					//Increment the current class
					currentMetric.incrementOutCounter();
					//Increment called class
					incrementCalledClass(field.getName());
				}
			}
			
			
			//Check the return type of a method
			Class c = mth.getReturnType();

			//Check if it is a custom class
			if (classNames.contains(c.getName())){
				//Increment the current class
				currentMetric.incrementOutCounter();
				//Increment called class
				incrementCalledClass(c.getName());
			}
		}
		//Put the metric back in the map
		metricMap.put(cls.getName(), currentMetric);
	}
	
	/**
	 * Check the fields parameters used by a class
	 * 
	 * @param currentMetric Metric class used for inserting back into the hashmap
	 * @param cls Class object for use by the reflection API
	 * 
	 */
	public void checkClassFields(Metric currentMetric, Class cls){
		//Get the objects implemented by a class
		System.out.println("declared fields for " + cls.getName() + ": ");
		Field[] classParams = cls.getDeclaredFields();
		for (Field cles : classParams){
		}
		System.out.println();
	}
	
	
	
	
	
	
	
	
	
//end of code
}
