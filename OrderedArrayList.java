
public class OrderedArrayList<T extends Comparable<T>> {

	/** This is an array of Objects of type T */
	 T[] array;
	 int numItems = 0;


	@SuppressWarnings("unchecked")
	/**
	 * Construct an OrderedArrayList with 10 empty slots. Note the recipe for 
	 * creating an array with the generic type. You'll want to reuse this 
	 * recipe in the insert() method.  You'll also possibly want to tweak this 
	 * constructor a bit if you add other instance variables.
	 */
	public OrderedArrayList() {
		array = (T[]) new Comparable[10];

	}

	@SuppressWarnings("unchecked")
	/**
	 * _Part 1: Implement this method._
	 * 
	 * Inserts a new item in the OrderedArrayList. This method should ensure
	 * that the list can hold the new item, and grow the backing array if
	 * necessary. If the backing array must grow to accommodate the new item, it
	 * should grow by a factor of 2. The new item should be placed in sorted
	 * order using insertion sort. Note that the new item should be placed
	 * *after* any other equivalent items that are already in the list.
	 * 
	 * @return the index at which the item was placed.
	 */
	public int insert(T item) {
		
		int itemIndex = 0;
		
		// If there's no items currently in the array, just add the first one here...
		if (numItems == 0){
			// System.out.println("First item is " + item);
			array[0] = item;
			numItems++;
			return 0;
		}
		
		// Doubles the array size if necessary.
	    if (numItems == array.length){
	        T[] temp = (T[]) new Comparable[array.length * 2];
	        for (int j = 0; j < array.length; j++){
	            temp[j] = array[j];
	        }
	        array = temp;		
	    }
	    
    	int superCount = 0; // Used in the for loop below
	    
	    // Add item in array using insertion sort.
	    for (int j = numItems - 1; j >= -1; j--){	    	
	    	
	    	// If the current item is less than or same as 'item' then the item should go in front
	    	if (array[j].compareTo(item) <= 0){
	    		// System.out.println("j is " + j);
	    		array[j+1] = item;
		        itemIndex = j+1;
		        break;
	    	}
	    	
	    	// else the 'item' should be in a position lower so we need to move 
	    	// the current item in the null space above.
	    	else {
	    		array[j+1] = array[j];
	    		superCount++;
	    	}
	    	
	    	// if the loop moved every previous item up, simply insert item into index 0.
	    	if (superCount == numItems){
	    		array[0] = item;
	    		itemIndex = 0;
	    		break;
	    	}
	    }
    	numItems++;
        return itemIndex;
	}

	/**
	 * _Part 2: Implement this method._
	 * 
	 * @return the number of items in the list.
	 */
	public int size() {
		int count = 0;
		for (int i = 0; i < array.length; i++)
			if (array[i] != null) count++;
		return count;
	}

	/**
	 * _Part 3: Implement this method._
	 * 
	 * Gets an item from the ordered array list. You can assume that this method
	 * will only be called with valid values of index. Specifically, values that
	 * are in the range 0 - (size-1). To impress your friends and build your
	 * street cred, consider adding checks that the index supplied is in fact in
	 * bounds. If it is not, you can raise an IndexOutOfBoundsException.
	 * 
	 * @param index
	 *            the index to get an item from
	 * @return an item at the specified index
	 */
	public T get(int index) {
		T item = array[index];
		return item;
	}

	/**
	 * _Part 3: Implement this method._
	 * 
	 * Counts the items in the ordered array list that are equal to the item at
	 * the specified index. Be sure to take advantage of the fact that the list
	 * is sorted here. You should not have to run through the entire list to
	 * make this count.
	 * 
	 * @param index
	 *            an index in the range 0..(size-1)
	 * @return the number of items in the list equal to the item returned by
	 *         get(index)
	 */
	public int countEquivalent(int index) {
		int equivalents = 1;
		int indexSave = index; // needed for the second while loop
		
		while (array[index + 1] == array[index]){
			equivalents++;
			index++;
		}
		
		index = indexSave; // Set index back to original value
		
		while(index != 0 && array[index - 1] == array[index]){
			equivalents++;
			index--;
		}
		
		return equivalents;		
	}

	/**
	 * _Part 4: Implement this method._
	 * 
	 * Finds the location of the first object that is equal to the specified
	 * object. Linear search is sufficient here, but feel free to leverage your
	 * binary search code too.
	 * 
	 * @param obj
	 *            an object to search for in the list
	 * @return the first index of an object equal to the one specified, or -1 if
	 *         no such object is found.
	 */
	public int find(T obj) {
		for (int i = 0; i < array.length; i++)
			if (array[i] == obj) return i;

		// object not in array if above test failed
		return -1;
	}

	/**
	 * _Part 5: Implement this method._
	 * 
	 * Removes all the objects equal to the specified object.
	 * 
	 * @param obj
	 *            an object equal to the one(s) you'd like to remove
	 * @return the number of objects removed
	 */
	public int remove(T obj) {
		int removedCount = 0;
		
		for (int i = 0; i < array.length; i++){
			if (array[i] == obj){
				array[i] = null;
				removedCount++;
			}
		}
		return removedCount;
	}
	
	
	// Used for verification and testing
	public void printArray(){
    	for (int i = 0; i < numItems; i++){
    		System.out.println("At index " + i + " --- " + array[i]);
    	}
	}
	
	/**
	 * This method is included for testing purposes.
	 * Typically, you would not want to expose a private instance variable
	 * as we are doing here. However, it does have value when the code is 
	 * going through a testing phase. Do not modify or remove this method.
	 * Some WebCAT tests may rely upon it.
	 */
	public Comparable<T>[] dbg_getBackingStore() { return array; }
}