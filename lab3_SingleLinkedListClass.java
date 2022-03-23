package javaapplication8;
import java.util.*;
import javaapplication8.ILinkedList;


public class SingleLinkedList implements ILinkedList {    

    
     public boolean contains(Object element) {    
        //Node current will point to head    
        Node current = head;    
        if(head == null) {
            return false; 
        }    
        while(current != null) {    
            //Prints each node by incrementing pointer
               if(current.data == element){
                   return true;
               }
            current = current.next;
        }    
     return false;        
    }
    //Represent a node of the singly linked list    
    static class Node{    
        Object data;    
        Node next;    
            
        public Node(Object data) {    
            this.data = data;    
        }
    }    
     
    //Represent the head and tail of the singly linked list    
    public Node head = null;    
    public Node tail = null;
    public static int size = 0;
        
    //addNode() will add a new node to the list
    static public ILinkedList sList = new SingleLinkedList();
   
    public void display() {    
        //Node current will point to head    
        Node current = head;
        System.out.print("[");
        while(current != null) {    
            //Prints each node by incrementing pointer 
            if(current.next != null) {
                System.out.print(current.data + ", ");    
                current = current.next;
            }
            else {
                System.out.print(current.data);
                current = current.next;
            }
        }    
        System.out.println("]");    
    }
    
        public void add(int index, Object element){
        if(index >= size || index < 0) {
            System.out.println("Error");
            return;
        }
        else if(index == size - 1) {
            sList.add(element);
            return;
        }
        Node current = head;
        Node newNode = new Node(element);
        int counter = 0;
        if(index == 0) {
            newNode.next = head;
            head = newNode;
            if(tail == null)
                tail = newNode;
            size++;
            return;
        }
        while(current != null) {
            if(counter == index - 1){
               newNode.next = current.next;
               current.next = newNode;
               size++;
               return;
            }
            else{
                 current = current.next;
                 counter++;
            }
        }
    }
    
    public void add(Object data) {
        //Create a new node    
        Node newNode = new Node(data);    
            
        //Checks if the list is empty    
        if(head == null) {    
            //If list is empty, both head and tail will point to new node    
            head = newNode;    
            tail = newNode;    
        }    
        else {    
            //newNode will be added after tail such that tail's next will point to newNode    
            tail.next = newNode;    
            //newNode will become new tail of the list    
            tail = newNode;    
        }    
        size++;
    }
    
    public Object get(int index) {  
        if(index > size - 1 || index < 0) {
            System.out.println("Error");
            return 0;
        }
        Node current = head;    
        int counter = 0;    
        if(head == null) { 
            System.out.println("Error");
            return 0;    
        }
        while(current != null) {
            if(counter == index)
                return current.data;
            else{
                current = current.next;
                counter++;
            }
        }
        System.out.println("Error");
        return 0;
    }
    
    public void set(int index, Object element) {    
        if(index > size - 1 || index < 0) {
            System.out.println("Error");
            return;
        }
        //Node current will point to head    
        Node current = head;    
        int counter = 0;
        if(head == null && index == 0){
            Node newNode = new Node(element);
            head = newNode;
            tail = newNode;
        }
        while(current != null) {    
            if(counter != index){
               current = current.next;
               counter++;
            }
            else{
                 current.data = element;
                 return;
            }
        }
    }
    
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public boolean isEmpty() {    
        Node current = head;    
        if(head == null)
            return true;
        return false;
    }
    
    public void remove(int index){
          if(index > size - 1 || index < 0) { //out of index
            System.out.println("Error");
            return;
          }
          Node current = head;
          int counter = 0;
          while(current != null){
              if(counter == index - 1 && counter < size - 2 && index != 0){ //in between
                  Node newCurr = current;
                  current = current.next;
                  newCurr.next = current.next;
                  size--;
                  return;
              }
              else if(counter == size - 2 && index == size - 1){ //last index
                  current.next = null;
                  tail = current;
                  size--;
                  return;
              }
              else if(index == 0){ //zero index
                  head = current.next;
                  size--;
                  return;
              }
              else{ //non of the above
                  current = current.next;
                  counter++;
              }
          }

    }
    
    public int size() {
        return size;
    }
    
    /**
     *
     * @param index1
     * @param index2
     * @return
     */
    @Override
    public ILinkedList sublist(int index1, int index2) {
        
        if(index1 > sList.size() - 1 || index2 > sList.size() - 1 || index1 > index2 || index1 < 0 || index2 < 0) {
            System.out.println("Error");
            return sList;
        }
        ILinkedList newList = new SingleLinkedList();
        Node current = head;
        int counter = 0;
        while(current != null) {
            if(counter == index1) {
                while(counter <= index2) {
                    newList.add(current.data);
                    current = current.next;
                    counter++;
                }
                return newList;
            }
            current = current.next;
            counter++;
        }
        return newList;
    }
    
   
    
    
    
    public static void main(String[] args) {
        ILinkedList List = new SingleLinkedList();
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", ""); //scan the input string
        String[] s = sin.split(", "); //convert into array of strings
        int[] arr = new int[s.length]; //array of integers
        if (s.length == 1 && s[0].isEmpty())
            arr = new int[]{};
        else {
            for(int i = 0; i < s.length; ++i)
            arr[i] = Integer.parseInt(s[i]);//convert array of strings into array of integers
        }
        for (int currentInt : arr) {
           List.add(currentInt); //convert array of integers into LinkedList
        }
        
        String method = sc.nextLine();
       switch (method) { //switch over input method selected by the user
           case "add" : {
                int element = sc.nextInt();
                List.add(element);
                List.display();
                break;
            }
         
           case "size" : {
               System.out.println(List.size());
               break;
           }
           case "contains" : {
               int element = sc.nextInt();
               if(List.contains(element))
                   System.out.println("True");
               else
                   System.out.println("False");
               break;
           }
           case "set" : {
              int index = sc.nextInt();
              int element = sc.nextInt();
              List.set(index, element);
               if(index > List.size() - 1 || index < 0)
                    break;
              List.display();
               break;
           }
           case "isEmpty" : {
               if(List.isEmpty())
                   System.out.println("True");
               else
                   System.out.println("False");
               break;
           }
           case "get" : {
               int index = sc.nextInt();
               Object value = List.get(index);
               if(index > List.size() - 1 || index < 0)
                    break;
               if((int)value != 0)
                   System.out.println((int)value);
               break;
           }
           case "addToIndex" : {
               int index = sc.nextInt();
               int element = sc.nextInt();
               List.add(index, element);
               if(index > List.size() || index < 0)
                  break;
               List.display();
               break;
               }
           case "remove" : {
               int index = sc.nextInt();
               List.remove(index);
               if(index >= List.size() || index < 0)
                    break;
               List.display();
               break;
           }
           case "sublist" : {
               int index1 = sc.nextInt();
               int index2 = sc.nextInt();
               ILinkedList newList = new SingleLinkedList();
               newList = List.sublist(index1, index2);
               if(!(index1 > sList.size() - 1 || index2 > sList.size() - 1 || index1 > index2 || index1 < 0 || index2 < 0)){
                   newList.display();
               }
               break;
           }
           case "clear" : {
               List.clear();
               System.out.println("[]");
               break;
           }
           default : System.out.println("Error");
       }
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
    }
    /* Implement your linked list class here*/
}
