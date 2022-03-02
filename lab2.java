//Reverse_an_array

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public int[] reverse(int[] array){
        int z;
       for(int i = 0; i<array.length/2; i++){
           z = array[i];
           array[i] = array[array.length-i-1];
           array[array.length-1-i] = z;
       }
       return array;
      /*Implement your reverse method here*/
    
    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
    	String[] s = sin.split(", ");
		int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty())
            arr = new int[]{};
        else {
            for(int i = 0; i < s.length; ++i)
        	   arr[i] = Integer.parseInt(s[i]);
        }
      	int[] res = new Solution().reverse(arr);
     	System.out.print("[");
      	for(int i = 0; i < arr.length; ++i) {
        	System.out.print(arr[i]);
            if(i != s.length - 1)
              System.out.print(", ");
        }
        System.out.print("]");
    }
}

// ################################################################################33

//Array average value

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public double average(int[] array) {
        int sum = 0;
        for(int i = 0; i<array.length; i++){
            sum+=array[i];
        }
        return (double)sum/(double)array.length;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");
        int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty())
            System.out.println(0.0);
        else { // change the input numbers from String to Integer
            for(int i = 0; i < s.length; ++i)
               arr[i] = Integer.parseInt(s[i]);
            Solution obj = new Solution();
            double result = obj.average(arr);
            System.out.println(result);
        }
    }
}

// #############################################################################

//Sum Even and Odd

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public int[] sumEvenOdd(int[] array) {
    	int[] res = new int[]{0, 0};
        for(int i = 0; i<array.length; i++){
            if(array[i]%2==0)
                res[0]+=array[i];
            else
                res[1]+=array[i];
        }
        return res;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */  
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");;
        int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty())
            System.out.println("[0, 0]");
        else { // change the input numbers from String to Integer
            for(int i = 0; i < s.length; ++i)
               arr[i] = Integer.parseInt(s[i]);
            Solution obj = new Solution();
            int[] result = obj.sumEvenOdd(arr);
            System.out.print("[");
            for(int i = 0; i<2; i++){
                System.out.print(result[i]);
                if(i==0)
                    System.out.print(", ");
            }
            System.out.print("]");
        }
    }
}

// #################################################################################

//Move Value

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public int[] moveValue(int[] array, int value) {
        int[] result = new int[array.length];
        int i= 0,last = 1,j = 0;
        while(i<array.length){
            if(array[i]==value){
                result[array.length-last] = array[i];
                last++;
                i++;
            }
            else{
                result[j] = array[i];
                i++;
                j++;
            }
        }
    	/*
        	Implement your method here
        */
        return result;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */      
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");
        int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty())
            System.out.println("[]");
        else {
            for(int i = 0; i < s.length; i++)
               arr[i] = Integer.parseInt(s[i]);
            int n = sc.nextInt();
            int res[] = new Solution().moveValue(arr,n);
            System.out.print("[");
            for(int i = 0; i<res.length; i++){
                System.out.print(res[i]);
                if(i<res.length-1)
                    System.out.print(", ");
            }
            System.out.print("]");
        }
    }
}

// #####################################################################################

//Transpose_2d_array

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public int[][] transpose(int[][] array){
        int dim = array.length;
        int temp;
        for(int i = 0; i<dim; i++){
            for(int j = 0; j<dim; j++){
                if(j>i){
                  temp = array[i][j];
                   array[i][j] = array[j][i];
                    array[j][i] = temp;
                }
              }
            }
        return array;
    }
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");;
        double dimm = Math.sqrt((double)s.length);
        int dim = (int)dimm;
        int[][] arr = new int[dim][dim];
        if (s.length == 1 && s[0].isEmpty())
            System.out.println("[[]]");
        else {
            int k = 0;
            for(int i = 0; i <dim ; ++i){
                for(int j = 0; j<dim; j++){
                    arr[i][j] = Integer.parseInt(s[k++]);
                }
            }
         
        Solution obj = new Solution();
        int[][] result = obj.transpose(arr);
        System.out.print("[");
        for(int i = 0; i<dim; i++){
            System.out.print("[");
            for(int j = 0; j<dim; j++){
                System.out.print(result[i][j]);
                if(j<dim-1)
                    System.out.print(", ");
                }
                if(i<dim-1)
                  System.out.print("], ");
                else{
                    System.out.print("]]");
                    break;
                }
            }
        } 
    }
}

// ###############################################################################

//Fibonnaci Sequence

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public int fibonacci(int n) {
         int fib = 1,temp = 1,temp2;
        if(n==1) 
            fib = 0;
        else if(n==2||n==3)
            fib = 1;
        else {
            while(n>3){
                temp2 = fib;
                fib+=temp;
                temp = temp2;
                // 1 1 2 3 5 8 
                n--;
            }
        }
    	return fib;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Solution result = new Solution();
        int res = result.fibonacci(n);
        System.out.println(res);
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}
