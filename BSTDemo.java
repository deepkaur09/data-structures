
import java.util.*;
import java.io.*;

class Node<E> implements Serializable {
    E data;
    Node<E> left;
    Node<E> right;

    Node(E data) {
        this.data = data;
        left = null;
        right = null;
    }
}


class BinaryTree<E> implements Serializable {
    protected Node<E> root;

    public BinaryTree() {
        root = null;
    }

    
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        this.root = new Node<>(data);
        if (leftTree != null) {
            this.root.left = leftTree.root;
        }
        if (rightTree != null) {
            this.root.right = rightTree.root;
        }
    }
   
    public BinaryTree<E> getLeftSubTree() {
        if (root == null || root.left == null) {
            return null;
        }
        BinaryTree<E> leftTree = new BinaryTree<>();
        leftTree.root = root.left;
        return leftTree;
    }

    public BinaryTree<E> getRightSubTree() {
        if (root == null || root.right == null) {
            return null;
        }
        BinaryTree<E> rightTree = new BinaryTree<>();
        rightTree.root = root.right;
        return rightTree;
    }
}

class BinarySearchTree<E extends Comparable> extends BinaryTree<E>{
    
    private boolean addReturn;
    
    private E deleteReturn;
    
    public boolean add(E item){
        root = add(item, root);
        return addReturn;
    }
    
    private Node <E> add(E item, Node <E> start){
        if(start == null){
            addReturn = true;
            return new Node<>(item);
        }
        int result = item.compareTo(start.data);
        if(result == 0){
            addReturn = false;
            return start;
        }
        if(result < 0){
            start.left = add(item, start.left);
            return start;
        }
        start.right = add(item, start.right);
        return start;
    }
    
    public E getData(){
        return this.root.data;
    }

    private E findLargest(Node <E> start){
        if(start.right.right == null){
            E result = start.right.data;
            start.right = start.right.left;
            return result;
        }
        return findLargest(start.right);
    }
    
    public boolean contains(E target){
        return contains(target, root);
    }
    
    public boolean contains(E target, Node <E> start){
        if(start == null){
            return false;
        }
        int result = target.compareTo(start.data);
        if(result == 0){
            return true;
        }
        if(result < 0){
            return contains(target, start.left);
        }
        return contains(target, start.right);
    }
    
    public E delete(E item){
        this.root = delete(item, this.root);
        return deleteReturn;
    }
    
    private Node<E> delete(E item, Node <E> start){
        if(start == null){
            deleteReturn = null;
            return null;
        }
        int result = item.compareTo(start.data);
        if(result < 0){
            start.left = delete(item, start.left);
            return start;
        }
        if(result > 0){
            start.right = delete(item, start.right);
            return start;
        }
        deleteReturn = start.data;
        
        if(start.right == null){
            return start.left;
        }
        
        if(start.left == null){
            return start.right;
        }
        if(start.left.right == null){
            start.data = start.left.data;
            start.left = start.left.left;
            return start;
        }
        //start.left = delete(start.data, start.left); // as explained
        start.data = findLargest(start.left);
        return start;
    }

 }


public class BSTDemo {
    public static void main(String[] args) {

        BinaryTree<Integer> leftTree = new BinaryTree<>(5, null, null);
        BinaryTree<Integer> rightTree = new BinaryTree<>(5, null, null);

        BinaryTree<Integer> tree = new BinaryTree<>(1, leftTree, rightTree);

        System.out.println("Tree created!");
        
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(5);
        bst.add(2);
        bst.add(8);
        bst.add(1);
        bst.add(3);
 
        System.out.println("Contains 2: " + bst.contains(2));
        System.out.println("Deleted: " + bst.delete(2));
        System.out.println("Contains 2 after delete: " + bst.contains(2));

    
        try {
            ObjectOutputStream os = new ObjectOutputStream(
                    new FileOutputStream("out.dat"));   
            os.writeObject(tree);
            os.close();  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}