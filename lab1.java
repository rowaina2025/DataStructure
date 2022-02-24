//lab 1 

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


interface ICalculator {
    int add(int x, int y);
    float divide(int x, int y);
}

class Operations implements ICalculator{
    public int add(int x, int y){
        int z = x+y;
        System.out.println(z);
        return z;
    }
    public float divide(int x, int y){
        try{
            float z = x*10/ y;
            z /= 10;
            System.out.println(z);
             return z;
        }
        catch(ArithmeticException e){
             System.out.println("Error");
             return 1;
        }
    }
}

public class MyClass{
   public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Operations calc = new Operations();
        int x = sc.nextInt();
        char op = sc.next().charAt(0);
        int y = sc.nextInt();
        switch(op){
         case '+': {calc.add(x,y); break;} 
         case '/': {calc.divide(x,y); break;}  
        }
    }
}
//Another MAthod

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ICalculator {

    public static int add(int x, int y){
        int z = x+y;
        System.out.println(z);
        return z;
    }
    public static float divide(int x, int y){
        try{
            float z = x*10/ y;
            z /= 10;
            System.out.println(z);
             return z;
        }
        catch(ArithmeticException e){
             System.out.println("Error");
             return 1;
        }
    }
}



public class Calculator implements ICalculator{
   public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        char op = sc.next().charAt(0);
        int y = sc.nextInt();
        switch(op){
         case '+': {ICalculator.add(x,y); break;} 
         case '/': {ICalculator.divide(x,y); break;}  
        }
    }
}

  

