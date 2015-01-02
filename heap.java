/**
 * @author Matthew Garmon
 * @author Walter Conway
 */
public class heap {
	private ELEM[] Heap;
	private int size; //max size of the heap
	private int n; //Number of elements now in the heap, heap-size
	private int[] locationArray;

	/**
	 * @param heapArray The ELEM array of either empty or full ELEMS
	 * @param numberOfItemsInTheHeap if the ELEMs that are already in the array are structured in a heap then the size of how many are already in the heap.
	 * @param maxSizeOfHeap The total size of the array that will be used as the heap.
	 */
	public heap(ELEM[] heapArray, int numberOfItemsInTheHeap,int maxSizeOfHeap){
		Heap = heapArray;
		n = numberOfItemsInTheHeap;
		size = maxSizeOfHeap;
		//Keeps the location of the object in the heap
		locationArray = new int[maxSizeOfHeap];
		for(int i = 0; i < maxSizeOfHeap; i++){
			if(Heap[i] != null){
				locationArray[i] = Heap[i].getmObject_ID(); 
			}else{
				locationArray[i] = -1;
			}
		}
	}
	/**
	 * @param pos the index of the heap to start the sifting down process.
	 */
	private void siftDown(int pos){
		int largest;
		int l = leftChild(pos);
		int r = rightChild(pos);
		//changed the less than or equal to to just less than
		if(l < n && Heap[l].getPriority() > Heap[pos].getPriority()){
			largest = l;
		}else {
			largest = pos;
		}
		//changed the less than or equal to to just less than
		if(r < n && Heap[r].getPriority() > Heap[largest].getPriority()){
			largest=r;
		}
		if(largest != pos){
			exchange(pos, largest);
			siftDown(largest);
		}			
	}

	/**
	 * @param x index of the heap that you want to exchange with parameter y.
	 * @param y index of the heap that you want to exchange with parameter x.
	 */
	private void exchange(int x, int y){
		ELEM temp = Heap[x];
		updateObjectLocationInHeap(Heap[y].getmObject_ID(), x);
		Heap[x] = Heap[y];
		updateObjectLocationInHeap(temp.getmObject_ID(), y);
		Heap[y] = temp;
	}

	/**
	 * @return the amount of items that are in the heap.
	 */
	public int heapSize(){
		return n;
	}

	/**
	 * @param pos in the heap
	 * @return true if pos is a leaf position
	 */
	public boolean isLeaf(int pos){
		return (pos >= n/2) && (pos < n);
	}
	/**
	 * @param pos in the heap
	 * @return the index of the left child of the parameter.
	 */
	public int leftChild(int pos){

		return 2*pos + 1; //add another 1 since the array starts at 0
	}
	/**
	 * @param pos in the heap
	 * @return the index of the right child of the parameter.
	 */
	public int rightChild(int pos){
		return 2*pos +2; //add another 1 since the array starts at 0 
	}
	/**
	 * @param pos in the heap
	 * @return the index of the parent of the parameter.
	 */
	public int parent(int pos){
		return (int)Math.floor((pos-1)/2);
	}

	/**
	 * @param newItem the ELEM to be inserted into the heap.
	 * @throws DuplicateObjectIDException if there is an object id already in existence in the heap.
	 * @throws FullHeapException if the heap is full.
	 * @throws ObjectIDOutOfBoundsException if the object id that was negative
	 */
	public void insert(ELEM newItem) throws DuplicateObjectIDException, FullHeapException, ObjectIDOutOfBoundsException{
		if(n==size){
			throw new FullHeapException("The Heap is Full. The Item "+newItem.getmObject_ID() + "was not inserted.");
		} 
		//Adding catch to avoid multiple instances of same ID
		else {
			if(newItem.getmObject_ID() > locationArray.length - 1 || newItem.getmObject_ID() < 0){
				int differenceCalc = (newItem.getmObject_ID() - (locationArray.length-1));
				throw new ObjectIDOutOfBoundsException("Object ID of "+newItem.getmObject_ID()+" is out of bounds. Please decrease it by atleast " + differenceCalc);
			} else
			{
				if (locationArray[newItem.getmObject_ID()] != -1){			
					//error the object ID already exists
					throw new DuplicateObjectIDException("Object ID already in use " + newItem.getmObject_ID());
				}
				else {
					Heap[n] = new ELEM(newItem.getmObject_ID(),-1);
					locationArray[newItem.getmObject_ID()] = n;
					heapChangeWeight(n++, newItem.getPriority());
				}
			}
		}
	}

	/**
	 * @return The highest priority in the heap
	 * @throws EmptyHeapException if the heap is empty
	 */
	public ELEM removeMax() throws EmptyHeapException{
		ELEM max = null;
		if(n == 0){
			throw new EmptyHeapException("Heap is Empty");
		} else{
			max = Heap[0];
			updateObjectLocationInHeap(max.getmObject_ID(), -1);
			Heap[0] = Heap[--n];
			Heap[n]=null;
			siftDown(0);
		}
		return max;
	}
	/**
	 * @param pos the location in the heap
	 * @return the ELEM object at the parameter pos in the heap
	 * @throws EmptyHeapException if the heap size is empty
	 */
	public ELEM remove(int pos) throws EmptyHeapException{
		ELEM temp = null;
		if(n == 0){
			throw new EmptyHeapException("Heap is Empty");
		} else{
			if(pos == n){
				temp = Heap[--n];		
				updateObjectLocationInHeap(temp.getmObject_ID(), -1);
				Heap[n] = null;
				return temp;
			} else {
				temp = Heap[pos];
				updateObjectLocationInHeap(temp.getmObject_ID(), -1);
				Heap[pos] = Heap[--n];
				Heap[n] = null;
				siftDown(pos);
			}
		}
		return temp;
	}
	/**
	 * The method buildHeap goes through the remaining nodes 
	 * of the tree and runs siftDown on each one.
	 */
	public void buildHeap(){
		n = size;
		for(int i = (int)Math.floor((size-1)/2); i >= 0; i--){
			siftDown(i);
	}

	}

	/**
	 * @param pos the position in the heap
	 * @param key value of the object id in the pos
	 */
	public void heapChangeWeight(int pos, int key){
		Heap[pos].setPriority(key);
		while(pos > 0 && Heap[parent(pos)].getPriority() < Heap[pos].getPriority()){
			exchange(pos, parent(pos));
			pos = parent(pos);
		}
	}

	/**
	 * @param Object_ID the object id
	 * @return the location of the object id in the heap
	 */
	public int findCurrentLocationInHeap(int Object_ID){
		return locationArray[Object_ID];
	}

	/**
	 * @param Object_ID the object id
	 * @param newLocation the location in the heap 
	 */
	private void updateObjectLocationInHeap(int Object_ID, int newLocation){
		locationArray[Object_ID] = newLocation;
	}

	/**
	 * @return true if the heap is empty false otherwise.
	 */
	public boolean isEmpty(){
		return n == 0;
	}

	/**
	 * @return the String representation of the location array
	 */
	public String locationArrayToString() {
		String temp = "";
		for (int i = 0; i < size; i++) {
			temp = temp + " " + locationArray[i]; 
		}
		return temp;
	}

	/**
	 * @param pos index of the heap array
	 * @return the weight of ELEM at the parameter
	 */
	public int getWeight(int pos){
		return Heap[pos].getPriority();
	}
	
	/**
	 * @return the heap array
	 */
	public ELEM[] getHeapArray(){
		return Heap;
	}
	
	/**
	 * @return the location of each ELEM object in the heap
	 */
	public int[] getLocationArray(){
		return locationArray;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String temp = "";
		for (int i = 0; i < n; i++) {
			temp = temp + " " + Heap[i].getmObject_ID();
		}
		return temp;
	}




}
