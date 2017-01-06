package ie.gmit.sw;

/**
 * 
 * @author G00313177
 * 
 * 
 * A Metric class that encapsulates the data needed to calculate the stability of a given class
 * 
 * @param inCounter Numerical measure of classes pointing a the class this metric represents
 * @param outCounter Numerical measure of classes the class represented by this metric points at
 * @param className The name of the class represented by this metric
 */
public class Metric {
	
	private int inCounter;
	private int outCounter;
	private String className;
	private boolean isInterface;
	
	public boolean isInterface() {
		return isInterface;
	}

	public void setInterface(boolean isInterface) {
		this.isInterface = isInterface;
	}

	/**
	 * Constructor
	 * @param name The name of the class without the package 
	 */
	public Metric(String name){
		className  = name;
	}
	
	/**
	 * Returns the stability of the current object class as a float value
	 * 
	 * @return Float value for the stability
	 */
	public float getStability(){
		//Local return variable to represent the stability as a float value
		float stability = 1f;
		
		//Make sure outCounter is not zero to avoid division by 0,
		//inCounter does not need to be checked at this point
		if (outCounter >= 0){
			stability = (float) outCounter/(inCounter + outCounter);
		}
		else{
			stability = 0f;
		}
		return stability;
	}
	
	//Getters and Setters

	//Return inCounter
	public int getInCounter() {
		return inCounter;
	}
	
	//Set inCounter
	/**
	 * I have modified the set in Counter to an increment method instead
	 */
	public void IncrementInCounter() {
		this.inCounter++;
	}
	
	//Get outCounter
	public int getOutCounter() {
		return outCounter;
	}
	
	//Set outCounter 
	/**
	 * 	I have modified the set out Counter to an increment method instead
	 */
	public void incrementOutCounter() {
		this.outCounter++;
	}
	
	//Get className
	public String getClassName() {
		return className;
	}
	
	//Set className
	public void setClassName(String className) {
		this.className = className;
	}

}
