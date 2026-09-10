import java.io.*;
import java.util.*;

 class ArrayStack<E>{
    private E [] data;
    private int top;
    
    public ArrayStack(){
        this.data = (E[])(new Object[10]);
        this.top = -1;
    }
    
    //O(1)
    public boolean empty(){
        return this.top == -1;
    }
    
    //O(1)
    public E peek(){
        if(empty()){
            throw new EmptyStackException();
        }
        return this.data[top];
    }
    
    //O(n): worst case scenario when full
    public void push(E item){
        if(top == this.data.length-1){
            grow();
        }
        top++;
        this.data[top] = item;
    }
    
    //O(1)
    public E pop(){
        if(empty()){
            throw new EmptyStackException();
        }
       //top-- Use then decrement
       return this.data[top--];
    }
    
    private void grow(){
        E[] temp = (E[]) new Object[this.data.length * 2];
        for(int i = 0; i < this.data.length; i++){
            temp[i] = this.data[i];
        }
        this.data = temp;
    }
}

public class StackDemo{
	public static void main(String[] args) {
	    ArrayStack<Integer> s = new ArrayStack<>();
	    
	    s.push(5);
	    s.push(45);
	    s.push(33);
	    System.out.println("Popped: "+ s.pop());
	    s.push(2);
	     System.out.println("Top of Stack: " + s.peek());
	    s.push(9);
	    while(!s.empty()){
	        System.out.println("Popped: " + s.pop());
	    }
	    System.out.println("check if empty: " + s.empty());
	}
}
