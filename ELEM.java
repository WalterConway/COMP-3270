
/**
 * @author Matthew Garmon
 * @author Walter Conway
 */
public class ELEM{
	private int mObject_ID;
	private int mPriority;
	public ELEM(int Object_ID, int priority){
		mObject_ID = Object_ID;
		mPriority = priority;
	}
	/**
	 * @return the object id of the ELEM object
	 */
	public int getmObject_ID() {
		return mObject_ID;
	}
	/**
	 * @param Object_ID sets the ELEM object's id to the parameter.
	 */
	public void setmObject_ID(int Object_ID) {
		mObject_ID = Object_ID;
	}
	/**
	 * @return the ELEM object's priority
	 */
	public int getPriority() {
		return mPriority;
	}
	/**
	 * @param Priority sets the priority to the parameter
	 */
	public void setPriority(int Priority) {
		mPriority = Priority;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "Object_ID: " +getmObject_ID()+ "\n" +"Priority Level: "+ getPriority();
	}

}
