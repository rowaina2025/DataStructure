import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IExpressionEvaluator {
  
/**
* Takes a symbolic/numeric infix expression as input and converts it to
* postfix notation. There is no assumption on spaces between terms or the
* length of the term (e.g., two digits symbolic or numeric term)
*
* @param expression infix expression
* @return postfix expression
*/
  
public String infixToPostfix(String expression);
  
  
/**
* Evaluate a postfix numeric expression, with a single space separator
* @param expression postfix expression
* @return the expression evaluated value
*/
  
public int evaluate(String expression);
   
}


 class MyStack {
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
   
    public int size() {
        return size;
    }
    
    public Object pop(){
        if (head == null){ //empty stack
            System.out.println("Error"); 
            System.exit(0);
        }
        Object element = null;
        Node current = head;          
        head = current.next;
        size--;
        element = current.data;
        return element;
    }
   
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
   
    public boolean isEmpty() {    
        Node current = head;    
        if(head == null)
            return true;
        return false;
    }
   
    public Object peek() {
        if(head == null){
            int x =  0; 
            return (Object)x;
        }
        Node peek = head;
        Object element = peek.data;
       return element; 
    }
 }

public class Evaluator implements IExpressionEvaluator {
    public static MyStack Q = new MyStack();
    public static MyStack val = new MyStack();
    public static int A, B, C;
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine();
        IExpressionEvaluator postfix = new Evaluator();
        String post = postfix.infixToPostfix(sin);
        String a, b, c;
        a = sc.nextLine();
        b = sc.nextLine();
        c = sc.nextLine();
        A = Integer.parseInt(a.substring(2,a.length()));
        B = Integer.parseInt(b.substring(2,b.length()));
        C = Integer.parseInt(c.substring(2,c.length()));
        val.push(A);
        val.push(B);
        val.push(C);
        int evaluation = postfix.evaluate(post);
        System.out.println(post);
        System.out.println(evaluation);
    }
    
    int priority (char op){
       int pr = 0;
       if(op == '*' || op == '/')
          pr = 2;
       else if(op == '-' || op == '+')
          pr = 1;
       else if(op == '^')
           pr = 3;
       else if(op == 'a' || op == 'b' || op == 'c')
           pr = -2;
        else if(op == '(' || op == ')')
            pr = -1;
        else{
            System.out.println("Error");
            System.exit(0);
        }
       return pr; 
    }

    @Override
    public String infixToPostfix(String exp) {
        String postfix = "";
        for(int i = 0; i < exp.length(); i++){
            if(priority(exp.charAt(exp.length()-1)) > 0){
                System.out.println("Error");
                System.exit(0);
            }
           if(exp.length() >= i+2 && (i==0||priority(exp.charAt(i-1)) != -2) && exp.charAt(i) == '-' && exp.charAt(i+1) == '-'){
             i++; 
            }
            else if(exp.length() >= i+2 &&exp.charAt(i) == '-' && exp.charAt(i+1) == '-' && priority(exp.charAt(i-1)) != -1){
             exp = exp.substring(0,i) + '+' + exp.substring(i+2,exp.length());
             i--;
          }
            else if(Q.size() == 0 && priority(exp.charAt(i)) >= 0){ // push operator to an empty stack
               Q.push(exp.charAt(i));
            }
         
          
         else if(priority(exp.charAt(i)) == -2){ //add variable to the expression
             postfix += exp.charAt(i);
         }
         else if(Q.size()!= 0&&priority(exp.charAt(i)) > priority((char)Q.peek()) && priority(exp.charAt(i)) > 0){ //push higher precedance operator which will be poped out once less precedance operator is found
             Q.push(exp.charAt(i));
         }
         else if(Q.size()!= 0&&priority(exp.charAt(i)) <= priority((char)Q.peek()) && priority(exp.charAt(i)) > 0){
             while(Q.size()!= 0&&priority(exp.charAt(i))<= priority((char)Q.peek()) && priority(exp.charAt(i)) > 0){
                 postfix += Q.pop(); //pop operator till less precedance is found
             }
                 Q.push(exp.charAt(i));
         }
         else if(exp.charAt(i) == ')'){
             while(!Q.isEmpty()&&(char)Q.peek() != '(')
                 postfix += Q.pop();
             if(!Q.isEmpty() && (char)Q.peek() == '(')
                Q.pop();
             else{
                 System.out.println("Error");
                 System.exit(0);
             }
         }
         else if(exp.charAt(i) == '('){
            if(i+2 == exp.length() || i+1 ==exp.length() || priority(exp.charAt(i+1)) != -2){
                System.out.println("Error");
                System.exit(0);
            }
            Q.push(exp.charAt(i));
         }
            else if(exp.length() >= i+2 && exp.charAt(i) != exp.charAt(i+1) && priority(exp.charAt(i)) == 1 && exp.charAt(i) == exp.charAt(i+1)){System.out.println("Error"); System.exit(0);}
        }
        while(!Q.isEmpty()){
            if((char)Q.peek() != '(')
               postfix += Q.pop();
            else{
                System.out.println("Error");
                System.exit(0);
            }
        }
        return postfix;
    }
    @Override
    public int evaluate(String expression) {
        MyStack temp = val;
        MyStack st = new MyStack();
        int c = (int)temp.pop(), b = (int)temp.pop(), a =(int)temp.pop();
        int var1 = 0, var2 = 0;
        for(int i = 0; i<expression.length(); i++){
            if(expression.charAt(i) == 'a'){
                st.push(a);
            }
            if(expression.charAt(i)== 'b'){
                st.push(b);
            }
            if(expression.charAt(i) == 'c'){
                st.push(c);
            } 
            else if(expression.charAt(i) == '*'){
                if(st.size()<2){
                    System.out.println("Error");
                    System.exit(0);
                }
                var1 = (int)st.pop();
                var2 = (int)st.pop();
                st.push(var1*var2);
            }
            else if(expression.charAt(i) == '/'){
                if(st.size()<2){
                    System.out.println("Error");
                    System.exit(0);
                }
                var1 = (int)st.pop();
                var2 = (int)st.pop();
                st.push(var2/var1);
            }
            else if(expression.charAt(i) == '-'){
                if(st.size()==1){
                    st.push(-1*(int)st.pop());
                    continue;
                }
                if(st.size()<2){
                    System.out.println("Error");
                    System.exit(0);
                }
                var1 = (int)st.pop();
                var2 = (int)st.pop();
                st.push(var2-var1);
            }
            else if(expression.charAt(i) == '+'){
                 if(st.size()<2){
                    System.out.println("Error");
                    System.exit(0);
                }
                var1 = (int)st.pop();
                var2 = (int)st.pop();
                st.push(var2+var1);
            }
            else if(expression.charAt(i) == '^'){
                if(st.size()<2){
                    System.out.println("Error");
                    System.exit(0);
                }
                var1 = (int)st.pop();
                var2 = (int)st.pop();
                st.push((int)Math.pow(var2,var1));
            }
        }
        
        return (int)st.pop();
    }
  }
 
