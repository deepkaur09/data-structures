
 class LinkedList<E>
{
        
        private Node<E> head;
        private int size;
        
        // to return size of list
        public int size(){
            return this.size;
        }
        
        public boolean isEmpty(){
            //to check if list is empty
            return this.size == 0;
        }
        
        public void addFirst(E element){
            this.head = new Node(element, this.head);
            this.size++;
        }
        
        //this.head.data is the only way to get as head is only referring
        //and doig this.data makes no sense as their is nothing like that.
        //O(1), only one operation no matter how many element.
        public E getFirst(){
            if(this.head != null){
                return this.head.data;
            }
            return null;
        }
        
        //remove and return first element
        public E removeFirst(){
            if(!(isEmpty())){
                 E result = this.head.data;
                 this.head = this.head.next;
                 this.size--;
                 return result;
            }
            return null;
        }
        
        //removing last Node
        public E removeLast(){
            if(size <=1){
                return removeFirst();
            }
            //if size is 1 only will cause error
            Node <E> temp = getNode(size-2);
            E result = temp.next.data;
            
            temp.next = null;
            this.size--;
            return result;
        }
        
        //removing the Node at given index
        public E remove(int index){
            if(index >= size){
                throw new IndexOutOfBoundsException();
            }
            if(index == 0){
                return removeFirst();
            }
            Node <E> temp = getNode(index-1);
            E result = temp.next.data;
            temp.next = temp.next.next;
            this.size--;
            return result;
        }
    
      //O(n) cause need to go throgh all the elements
        public void addEnd(E element){
            if (isEmpty()){
                addFirst(element);
            }
            else{
                 this.size++;
                 Node<E> temp = head;
                 while(temp.next != null){
                     temp = temp.next;
                 }
            
            temp.next = new Node<>(element);
            }
        
        }
        
        
        //add at last position
        public void add(E item){
            add(size, item);
        }
    
       
       //helper method to add Node after curr Node
       private void addAfter(Node<E> curr, E item){
           curr.next = new Node<>(item, curr.next);
           this.size++;
       }
       
       //helper method to reaches to indeces
       private Node<E> getNode(int index){
            if(index >= this.size || index<0){
               throw new IndexOutOfBoundsException();
           }
           Node<E> temp = this.head;
           for(int i = 0; i < index; i++){
               temp = temp.next;
           }
           return temp;
       }
       
       //method to get a element at particular index
       public E getAtIndex(int index){
           return getNode(index).data;
       }
       
       
       //set method to replace value at given index
        public E setAtIndex(int index, E value){
           Node<E> temp = getNode(index);
           E result = temp.data;
           temp.data = value;
           return result;
       }
       
       //to add E at a given index
       public void add(int index, E item){
           if(index == 0){
               addFirst(item);
           }
           else{
               //get the Node at index-1 because it is supposed to be added after that, and pass that Node into addAfter method
               addAfter(getNode(index - 1), item);
           }
           
       }
  
        public void print(){
            Node<E> temp = this.head;
            
            for(int i = 0; i < this.size; i++){
                System.out.println(temp.data);
                temp = temp.next;
            }
        }
        
        
    private static class Node<E>{
        
        private E data;
        private Node<E> next;
        
        private Node(E data){
            this.data = data;
        }
        private Node(E data, Node <E> next){
            this.data = data;
            this.next = next;
        }
    }
}

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();

        list.addFirst("sam");
        list.addFirst("mike");        
        list.addEnd("parker");        
        list.add(2, "peter");      

        System.out.println("Size: " + list.size());
        System.out.println("isEmpty: " + list.isEmpty());

        list.print();             

        System.out.println("getFirst: " + list.getFirst());
        System.out.println("getAtIndex(2): " + list.getAtIndex(2));

        list.setAtIndex(1, "SAM");
        System.out.println("After setAtIndex(1, SAM):");
        list.print();                 

        System.out.println("removeFirst: " + list.removeFirst());
        System.out.println("removeLast: " + list.removeLast());  
        System.out.println("remove(0): " + list.remove(0));

        System.out.println("Final list:");
        list.print();        

        System.out.println("Final size: " + list.size());
    }
}

