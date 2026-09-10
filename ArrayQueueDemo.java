class ArrayQueue<E>{
    
    private E[] theData;
    private int front;
    private int rear;
    private int size;
    private int capacity;
    
    @SuppressWarnings("unchecked")
    public ArrayQueue(int initCapacity){
        capacity = initCapacity;
        theData = (E[]) new Object[capacity];
        front = 0;
        rear = capacity - 1;
        size = 0;
        
    }
    
     
    public E poll(){
        if(size == 0){
            return null;
        }
        E result = theData[front];
        front = (front + 1) % capacity;
        size--;
        return result;
    }
    
    public boolean offer(E item){
        if(size == capacity){
            reallocate();
        }
        size++;
        rear = (rear + 1) % capacity;
        theData[rear] = item;
        return true;
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    @SuppressWarnings("unchecked")
    private void reallocate(){
        int newCapacity = capacity * 2;
        E[] newData = (E[]) new Object[newCapacity];
        for(int i = 0; i < size; i++){
            newData[i] = theData[(front + i) % capacity];
        }
        theData = newData;
        front = 0;
        rear = size - 1;
        capacity = newCapacity;
    }
    
}

public class ArrayQueueDemo
{
	 public static void main(String[] args) {
        ArrayQueue<String> q = new ArrayQueue<>(2); 

        q.offer("mike");
        q.offer("sam");
        q.offer("peter");   
        q.offer("parker");
		while (!q.isEmpty()) {
		    System.out.println(q.poll());
		}
	}
}
