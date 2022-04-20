import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ILinkedList {
/**
* Inserts a specified element at the specified position in the list.
* @param index
* @param element
*/
public void add(int index, Object element);
/**
* Inserts the specified element at the end of the list.
* @param element
*/
public void add(Object element);
/**
* @param index
* @return the element at the specified position in this list.
*/
public Object get(int index);

/**
* Replaces the element at the specified position in this list with the
* specified element.
* @param index
* @param element
*/
public void set(int index, Object element);
/**
* Removes all of the elements from this list.
*/
public void clear();
/**
* @return true if this list contains no elements.
*/
public boolean isEmpty();
/**
* Removes the element at the specified position in this list.
* @param index
*/
public void remove(int index);
/**
* @return the number of elements in this list.
*/
public int size();
/**
* @param fromIndex
* @param toIndex
* @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
*/
public ILinkedList sublist(int fromIndex, int toIndex);
/**
* @param o
* @return true if this list contains an element with the same value as the specified element.
*/
public boolean contains(Object o);
public void display();
}


class SingleLinkedList implements ILinkedList {    
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
    int size = 0;
        
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
        if(index > sList.size() || index < 0) {
            System.out.println("Error");
            return;
        }
        else if(index == size) {
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
}


interface IPolynomialSolver {
    /**
    * Set polynomial terms (coefficients & exponents)
    * @param poly: name of the polynomial
    * @param terms: array of [coefficients][exponents]
    */
    void setPolynomial(char poly, int[][] terms);
  
    /**
    * Print the polynomial in ordered human readable representation
    * @param poly: name of the polynomial
    * @return: polynomial in the form like 27x^2+x-1
    */
    String print(char poly);
  
    /**
    * Clear the polynomial
    * @param poly: name of the polynomial
    */
      void clearPolynomial(char poly);
  
    /**
    * Evaluate the polynomial
    * @param poly: name of the polynomial
    * @param value: the polynomial constant value
    * @return the value of the polynomial
    */
    float evaluatePolynomial(char poly, float value);
  
    /**
    * Add two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial
    */
    int[][] add(char poly1, char poly2);
  
    /**
    * Subtract two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial*/
    int[][] subtract(char poly1, char poly2);
  
    /**
    * Multiply two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return: the result polynomial
    */
    int[][] multiply(char poly1, char poly2);
}


public class PolynomialSolver implements IPolynomialSolver {
    
    public static ILinkedList A = new SingleLinkedList();
    public static ILinkedList B = new SingleLinkedList();
    public static ILinkedList C = new SingleLinkedList();
    public static ILinkedList T = new SingleLinkedList();
    public static ILinkedList T1 = new SingleLinkedList();
    public static ILinkedList R = new SingleLinkedList();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true) {
        String op;
        try {
            op = sc.nextLine();    
        } catch(Exception e) {
            return;
        }
        switch(op)
        {
            case("set") : {
                char poly = sc.nextLine().charAt(0);
                String sin = sc.nextLine().replaceAll("\\[|\\]", ""); //scan the input string
                String[] s = sin.split(","); //convert into array of strings
                int[] arr = new int[s.length]; //array of integers
                if (s.length == 1 && s[0].isEmpty()) {
                    System.out.println("Error");
                    System.exit(0);
                    break;
                }
                else {
                    for(int i = 0; i < s.length; ++i)
                    arr[i] = Integer.parseInt(s[i]);//convert array of strings into array of integers
                }
                int[][] newArr = new int[1][arr.length];
                for(int i = 0; i < arr.length; i++)
                    newArr[0][i] = arr[i];
                new PolynomialSolver().setPolynomial(poly, newArr);
                break;
            }
            case("add") : {
                char poly1 = sc.nextLine().charAt(0);
                char poly2 = sc.nextLine().charAt(0);
                int[][] res = new PolynomialSolver().add(poly1, poly2);
                if(T.isEmpty() || T1.isEmpty() || res == null){
                    System.out.println("Error");
                    System.exit(0);
                }
                else {
                    new PolynomialSolver().setPolynomial('R', res);
                    System.out.println(new PolynomialSolver().print('R'));
                }
                break;
            }
            case("sub") : {
                char poly1 = sc.nextLine().charAt(0);
                char poly2 = sc.nextLine().charAt(0);
                int[][] res = new PolynomialSolver().subtract(poly1, poly2);
                if(T.isEmpty() || T1.isEmpty() || res == null){
                    System.out.println("Error");
                    System.exit(0);
                }
                else {
                    new PolynomialSolver().setPolynomial('R', res);
                    System.out.println(new PolynomialSolver().print('R'));
                }
                break;
            }
            case("mult") : {
                char poly1 = sc.nextLine().charAt(0);
                char poly2 = sc.nextLine().charAt(0);
                int[][] arr = new PolynomialSolver().multiply(poly1, poly2);
                if(T.isEmpty() || T1.isEmpty() || arr == null){
                    System.out.println("Error");
                    System.exit(0);
                }
                else {
                    new PolynomialSolver().setPolynomial('R', arr);
                    System.out.println(new PolynomialSolver().print('R'));
                }
                break;
            }
            case("print") : {
                char poly = sc.nextLine().charAt(0);
                if(poly == 'R') {
                    System.out.println("Error");
                    System.exit(0);
                    break;
                }
                System.out.println(new PolynomialSolver().print(poly));
                break;
            }
            case("eval") : {
                char poly = sc.nextLine().charAt(0);
                float value = sc.nextFloat();
                float res = new PolynomialSolver().evaluatePolynomial(poly, value);
                if(T.isEmpty()){
                    System.out.println("Error");
                    System.exit(0);
                }
                else
                    System.out.println((int)res);
                break;
            }
            case("clear") : {
                char poly = sc.nextLine().charAt(0); 
                new PolynomialSolver().clearPolynomial(poly);
                break;
            }
            default: {
                System.out.println("Error");
                System.exit(0);
            }
        }
    }
}
    
    public void setPolynomial(char poly, int[][] terms) {
        switch(poly)
        {
            case('A') : {
                A.clear();
                for(int i = 0; i < terms[0].length; i++) {
                    A.add(terms[0][i]);
                }
                break;
            }
            case('B') : {
                B.clear();
                for(int i = 0; i < terms[0].length; i++) {
                    B.add(terms[0][i]);
                }
                break;
            }
            case('C') : {
                C.clear();
                for(int i = 0; i < terms[0].length; i++) {
                    C.add(terms[0][i]);
                }
                break;
            }
            case('R') : {
                R.clear();
                for(int i = 0; i < terms[0].length; i++) {
                    R.add(terms[0][i]);
                }
                break;
            }
            default : {
                System.out.println("Error");
                System.exit(0);    
            }
        }
    }
    
    public String print(char poly) {
        switch(poly)
        {
            case('A') : {
                T = A;
                break;
            }
            case('B') : {
                T = B;
                break;
            }
            case('C') : {
                T = C;
                break;
            }
            case('R') : {
                T = R;
                break;
            }
            default : {
                System.out.println("Error");
                System.exit(0);    
                return "Error";
            }
        }
        String sout = "";
        if(T.isEmpty()){
            System.out.println("Error");
            System.exit(0);
            return "Error";
        }
        int counter = 0;
        for(int i = T.size() - 1; i >= 1; i--) {
            if((int)T.get(counter) == 0) {
                if((int)T.get(counter + 1) > 0 && sout != "")
                    sout += "+";
                counter++;
                continue;
            }
            if((int)T.get(counter) != 1)
                sout += Integer.toString((int)T.get(counter));
            if(i > 1)
                sout += "x^" + Integer.toString(i);
            else 
                sout += "x";
            if((int)T.get(counter + 1) > 0)
                sout += "+";
            counter++;
        }
        if((int)T.get(counter) != 0)
            sout += Integer.toString((int)T.get(counter));  
        return sout;
    }
    
    public int[][] add(char poly1, char poly2) {
        if(poly1 == 'A')
            T = A;
        else if(poly1 == 'B')
            T = B;
        else if(poly1 == 'C')
            T = C;
        else
            return new int[][] {};
        if(poly2 == 'A')
            T1 = A;
        else if(poly2 == 'B')
            T1 = B;
        else if(poly2 == 'C')
            T1 = C;
        else
            return new int[][] {};
        int size = T.size() > T1.size() ? T.size() : T1.size();
        int[][] res = new int[1][size];
        int counter = size - 1;
        int counter1 = T.size() - 1;
        int counter2 = T1.size() - 1;
        while(counter1 >= 0 && counter2 >= 0)
            res[0][counter--] = (int)T.get(counter1--) + (int)T1.get(counter2--);
        while(counter >= 0 && counter1 >= 0)
            res[0][counter--] = (int)T.get(counter1--);
        while(counter >= 0 && counter2 >= 0)
            res[0][counter--] = (int)T1.get(counter2--);
        return res;
    }
    
    public int[][] subtract(char poly1, char poly2) {
        if(poly1 == 'A')
            T = A;
        else if(poly1 == 'B')
            T = B;
        else if(poly1 == 'C')
            T = C;
        else
            return new int[][] {};
        if(poly2 == 'A')
            T1 = A;
        else if(poly2 == 'B')
            T1 = B;
        else if(poly2 == 'C')
            T1 = C;
        else
            return new int[][] {};
        int size = T.size() > T1.size() ? T.size() : T1.size();
        int[][] res = new int[1][size];
        int counter = size - 1;
        int counter1 = T.size() - 1;
        int counter2 = T1.size() - 1;
        while(counter1 >= 0 && counter2 >= 0)
            res[0][counter--] = (int)T.get(counter1--) - (int)T1.get(counter2--);
        while(counter >= 0 && counter1 >= 0)
            res[0][counter--] = (int)T.get(counter1--);
        while(counter >= 0 && counter2 >= 0)
            res[0][counter--] = (int)T1.get(counter2--);
        return res;
    }
    
    public float evaluatePolynomial(char poly, float value){
        float result = 0;
        switch(poly){
            case ('A') : {
                T = A;
                break;
            }
            case ('B') : {
                T = B;
                break;
            }
            case ('C') : {
                T = C;
                break;
            }
            default : {
                T.clear();
                return 0;
            }
        }
        int j = 0;
        for(int i = T.size() - 1; i >= 0; i--)
            result += (int)T.get(j++) * Math.pow(value, i);
        return result;
    }
    
    public void clearPolynomial(char poly){
        int check = 1;
        switch(poly){
            case ('A') : {
                if(A.isEmpty())
                    check = 0;
                A.clear();
                break;
            }
            case ('B') : {
                if(B.isEmpty())
                    check = 0;
                B.clear();
                break;
            }
            case ('C') : {
                if(C.isEmpty())
                    check = 0;
                C.clear();
                break;
            }
            default : {
                check = 0;
            }
        }
        if(check == 0){
            System.out.println("Error");
            System.exit(0);
        }
        else
            System.out.println("[]");
    }
    
    public int[][] multiply(char poly1, char poly2){
        switch(poly1) {
            case ('A') : {
                T = A;
                break;
            }
            case ('B') : {
                T = B;
                break;
            }
            case ('C') : {
                T = C;
                break;
            }
            default : {
                return new int [][] {};
            }
        }
        switch(poly2){
            case ('A') : {
                T1 = A;
                break;
            }
            case ('B') : {
                T1 = B;
                break;
            }
            case ('C') : {
                T1 = C;
                break;
            }
            default : {
                return new int [][] {};
            }
        }
        int size = T.size() + T1.size() - 1;
        int[][] arr = new int [1][size];
        int t = 0, t1 = 0;
        for(int i = T.size() - 1; i >= 0; i--) {
            t1 = 0;
            for(int j = T1.size() - 1; j >= 0; j--) {
                int term = 0;
                term = (int)T.get(t) * (int)T1.get(t1++);
                arr[0][t + t1 - 1] += term;
            }
            t++;
        }
        return arr;
    }
}
