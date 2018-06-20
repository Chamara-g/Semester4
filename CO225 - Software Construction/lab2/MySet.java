
/* my array list: an array that behaves like a list 
 * E/14/108
 */

public class MySet<T/* Can we have any T */> { 

    int nextItem; 
    int currentCapacity; 
    T[] data; // DO NOT CHANGE

    private static int defaultSizeToCreate = 10; // how many elements to create 

    public MySet(int elements) { 
	this.nextItem = 0; 
	this.defaultSizeToCreate = elements; 
	this.currentCapacity = elements; 

	this.data = (T[]) new Object[this.defaultSizeToCreate]; 
    }

    public MySet() { 
	     this(defaultSizeToCreate); 
    }

    public boolean isEmpty() { return this.nextItem == 0; } 
    public boolean isFull() { return false; /* never get filled */} 

    public boolean add(T item) { 
	
      if( nextItem == currentCapacity - 1 ){
            
            int newArraySize = currentCapacity + defaultSizeToCreate;
            T[] newSet = (T[]) new Object[newArraySize];
            
                for (int i = 0 ; i < this.nextItem ; i++ )
                newSet[i] = this.data[i];
                
                currentCapacity = currentCapacity + defaultSizeToCreate;
                nextItem = newArraySize - MySet.defaultSizeToCreate -1;
                this.data = newSet;
       }        
       
       data[nextItem] = null; // DO NOT CHANGE 

       int num;
       
         for (  num = 0 ; num < this.nextItem ; num++ ){
          if( item.equals(this.data[num]))
              break;
        }  
      
  
        if( num  < this.nextItem ){
        
            for(int i = num ; i < this.nextItem-1 ; i++){
                this.data[i]=this.data[i+1];
            }
            this.nextItem--;
        }
      	this.data[this.nextItem++] = item ;
        return true;
        
	/* if there is any element delete it 
	 * then add the new element. 
	 * How do you handle when the array is full: 
	 * crate a new array with currentCapacity+defaultSizeToCreate, 
	 * copy the old conents into that
	 * Accessing array when full might be a problem
	 */

	/* Add the item to the array if the item is not there */
	//return false; // CHANGE ME
 
    }




    public T remove() { 
	/* remove the first element in the array 
	 * and copy the rest front. 
	 * FIFO structure. 
	 * If the ArrayList is empty return null
	 */
	/* Option 1: */
	if(isEmpty()) return null; 
	// IMPLEMENT THE REST 

	/* Option 2: */
	if(!isEmpty()) { 
            
            T removedata = this.data[0];
            
            for( int i = 1 ; i < this.nextItem ; i++){
                this.data[i-1] = data[i];
            }
            this.nextItem--;
            
            return removedata;
	    // IMPLEMENT THE REST
	}
	return null;
	// which option is better? why? 
    }


}
	 

	