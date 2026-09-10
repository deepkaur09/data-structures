import java.util.*;
import java.io.*;

class LinkedStack<E>{
    private Node<E> top;
    
    public boolean empty(){
        return this.top==null;
    }
    
    public E peek(){
        if(empty()){
            throw new NoSuchElementException();
        }
        return this.top.data;
    }
    
    public void push(E item){
        this.top = new Node<>(item, this.top);
    }
    
    public E pop(){
        if(empty()){
            throw new NoSuchElementException();
        }
        E result = this.top.data;
        this.top = this.top.next;
        return result;
    }
    
    private static class Node<E>{
        private E data;
        private Node<E> next;
        
        private Node(E data, Node<E> next){
            this.data = data;
            this.next = next;
        }
    }
}

public class LinkedStackDemo
{
    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Top of stack: " + stack.peek());

        while (!stack.empty()) {
            System.out.println("Popped: " + stack.pop());
        }

        try {
            stack.pop();
        } 
        catch (NoSuchElementException e) {
            System.out.println("Stack is empty, cannot pop!");
        }
	}
}
