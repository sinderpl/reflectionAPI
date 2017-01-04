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
		if (outCounter > 0){
			stability = (float) inCounter/(inCounter + outCounter);
		}
		else{
			stability = 0f;
		}
		return stability;
	}
	

	//Return inCounter
	public int getInCounter() {
		return inCounter;
	}
	
	//Set inCounter
	public void setInCounter(int inCounter) {
		this.inCounter = inCounter;
	}
	
	//Get outCounter
	public int getOutCounter() {
		return outCounter;
	}
	
	//Set outCounter
	public void setOutCounter(int outCounter) {
		this.outCounter = outCounter;
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
