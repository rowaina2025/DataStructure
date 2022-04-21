/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author user
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IStack {
  
  /*** Removes the element at the top of stack and returnsthat element.
  * @return top of stack element, or through exception if empty
  */
  
  public Object pop();
  
  /*** Get the element at the top of stack without removing it from stack.
  * @return top of stack element, or through exception if empty
  */
  
  public Object peek();
  
  /*** Pushes an item onto the top of this stack.
  * @param object to insert*
  */
  
  public void push(Object element);
  
  /*** Tests if this stack is empty
  * @return true if stack empty
  */
  public boolean isEmpty();
  
  public int size();
  public void display();
  public Object get(int index) ;
}

public class MyStack implements IStack {
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
    int size = 0;
    @Override
    public int size() {
        return size;
    }
    @Override
    public Object pop(){
        if (head == null){ //empty stack
            System.out.println("Error"); 
            System.exit(0);
        }
        Object element = null;
        Node current = head;          
        head = current.next;
        size--;
        return element;
    }
    @Override
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
    @Override
    public void push(Object data) {
        //Create a new node    
        Node newNode = new Node(data);    
            
        //Checks if the list is empty    
        if(head == null) {    
            //If list is empty, both head and tail will point to new node    
            head = newNode;    
            tail = newNode;    
        }    
        else {    
            newNode.next = head;
            head = newNode;
            size++;
            return;   
        }    
        size++;
    }
    @Override
    public boolean isEmpty() {    
        Node current = head;    
        if(head == null)
            return true;
        return false;
    }
    @Override
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
    @Override
    public Object peek() {
        if(head == null){
           System.out.println("Error");
           System.exit(0);
        }
        Node peek = head;
        Object element = peek.data;
       return element; 
    }

    public static IStack Q = new MyStack(); 
    
    public static void main(String[] args) {
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
        for(int i = 0; i< s.length/2; i++){
            arr[i] = arr[i] ^ arr[s.length - i - 1];
            arr[s.length - i - 1] = arr[i] ^ arr[s.length - i - 1];
            arr[i] = arr[i] ^ arr[s.length - i - 1];
        }
        for (int currentInt : arr) {
           Q.push(currentInt); //convert array of integers into Stack
        }
        String method = sc.nextLine();
        switch(method){
            case("push"): {
                int element = sc.nextInt();
                Q.push((Object)element);
                Q.display();
                break;
            }
            case("pop"): {
                Q.pop();
                Q.display();
                break;
            }
            case("peek"): {
                Object element = Q.peek();
                System.out.println((int)element);
                break;
            }
            case("isEmpty"): {
                if(Q.isEmpty()){
                   System.out.println("True");
                }
                else{
                   System.out.println("False");
                }
                break;
            }
            case("size"): {
                System.out.println(Q.size());
                break;
            }
            default: {
                    System.out.println("Error");
            }
        }
    }
    
}
