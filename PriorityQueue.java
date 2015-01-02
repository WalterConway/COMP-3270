
/**
 * @author Matthew Garmon
 * @author Walter Conway
 */
public class PriorityQueue {
	ELEM[] mELEMArray =null;	
	heap mHeap = null;
	
	/**
	 * @param elem
	 * @param numActive
	 */
	public PriorityQueue(ELEM[] elem, int numActive){
		mELEMArray = elem;
		mHeap = new heap(mELEMArray, 0, numActive);
	}

	/**
	 * @param Object_ID
	 * @param priority
	 * @throws DuplicateObjectIDException
	 * @throws FullHeapException
	 * @throws ObjectIDOutOfBoundsException
	 */
	public void enqueue(int Object_ID, int priority) throws DuplicateObjectIDException, FullHeapException, ObjectIDOutOfBoundsException{
			mHeap.insert(new ELEM(Object_ID, priority));
	}

	/**
	 * @return object id of the maximum priority 
	 * @throws EmptyHeapException when the heap is empty
	 */
	public int dequeue() throws EmptyHeapException{
		ELEM maxELEM = mHeap.removeMax();
			return maxELEM.getmObject_ID();
	}

	/**
	 * @param Object_ID
	 * @param new_priority
	 * @throws DuplicateObjectIDException when there is a object id with the same object id is already in the heap
	 * @throws FullHeapException when the heap is full
	 * @throws ObjectIDOutOfBoundsException
	 * @throws EmptyHeapException 
	 */
	public void changeWeight(int Object_ID, int new_priority) throws DuplicateObjectIDException, FullHeapException, ObjectIDOutOfBoundsException, EmptyHeapException{
		int objectLocation = mHeap.findCurrentLocationInHeap(Object_ID);
		ELEM temp = mHeap.remove(objectLocation);
		temp.setPriority(new_priority);
		mHeap.insert(temp);
	}
	
	/**
	 * @return the heap
	 */
	public heap getHeap(){
		return mHeap;
	}

	/**
	 * @param Object_ID
	 * @return the weight of the object id
	 */
	public int getWeightOfObject_ID(int Object_ID){
		return mHeap.getWeight(mHeap.findCurrentLocationInHeap(Object_ID));
	}
}
