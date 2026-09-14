import java.util.*;
import java.io.*;

class BinaryTree<E> implements Serializable{
    
    private Node<E> root;
    
    public BinaryTree(){}
    
    //protected as we can use it in thuis class and classes thta inherit from this class
    protected BinaryTree(Node <E> root){
        this.root = root;
    } 
    
    public E getData(){
        return this.root.data;
    }
    
    //non a binary search tree could be any binary tree
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree){
        this.root = new Node<>(data);
        if(leftTree != null){
            this.root.left = leftTree.root;
        }
        if(rightTree != null){
            this.root.right = rightTree.root;
        }
    }
    
    public boolean isLeaf(){
        //considered that empty tree is a leaf
        //if a want to consider is as non-leaf then need if statement returning false.
        return root == null || root.left == null && root.right == null;
    }
    
    public BinaryTree<E> getLeftTree(){
        //root.left is a Node that will not get us tree
        return new BinaryTree<>(root.left);
    }
        
    public BinaryTree<E> getRightTree(){
        //root.left is a Node that will not get us tree
        return new BinaryTree<>(root.right);
    }
        
        
        //@param level: to know which level we are at to add spaces
    private String preOrder(Node <E> start, int level){
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < level; i++){
            sb.append("\t");
        }
            
        if(start == null){
            sb.append("null\n");
        }
            //string is time consuming as we rebuild it every time
        else{
            sb.append(start.data);
            sb.append("\n");
            sb.append(preOrder(start.left, level + 1));
            sb.append(preOrder(start.right, level + 1));
        }
            
        return sb.toString();
    }
        
        //starter method to call the recursive preOrder 
    public String toString(){
        return preOrder(root, 1);
    }
    
    //inner Node class
    private static class Node<E> implements Serializable{
        private E data;
        private Node<E> left;
        private Node<E> right;
        
        private Node(E data){
            this.data = data;
        }
    }

}

public class BinaryTreeDemo
{
	public static void main(String[] args) {
	    BinaryTree<Integer> leftTree = new BinaryTree<>(5, null, null);
	    BinaryTree<Integer> rightTree = new BinaryTree<>(5, null, null);
	    
		BinaryTree<Integer> tree = new BinaryTree<>(1, leftTree, rightTree);
		System.out.println(tree);
		
		//stored as a binary file
		try{
		    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("out.txt"));
		    os.writeObject(tree);
		}
		catch(IOException e){
		    e.printStackTrace();
		}
		
	}
}
