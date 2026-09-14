/**
 * This code demonstrates recursion for Fibonacci numbers, array operations,
 * and counting vowels in a string from a file.
 * @author: Dapinderdeep Kaur
 * @version 1.0
*/

import java.util.*;
import java.io.*;

public class RecursionDemo
{
    /**
     * Recursive function to calculate the nth Fibonacci number.
     * @param num: the position in the Fibonacci sequence
     * @return the Fibonacci number at position num
    */
	public static int fib(int num) {
		if(num == 0) {
			return 0; //base case
		}
		if(num == 1) {
			return 1; //base case
		}
		//recursive call
		return fib(num -1) + fib(num - 2);
	}
	
	/**
	 * Recursive function to calculate sum of first n Fibonacci numbers
	 * @param n: the number of terms in the Fibonacci sequence to sum
     * @return the sum of the first n Fibonacci numbers
	 */
    public static int sumFib(int n) {
        if (n == 1) {
            return fib(0); // base case
        }
        //recurive call
        return fib(n - 1) + sumFib(n - 1);
    }
    
    /**
     * Recursive function to fill a String array with user input.
     * @param arr: the array to fill
     * @param index: the current entity to fill
     * @param input: Scanner object to read input from user
    */
    public static void fillArray(String[] arr, int index, Scanner input) {
    // base case
    if (index == arr.length) {
        return;
    }

    System.out.print("Enter values for element " + index + ": ");
    arr[index] = input.next();

    // recursive call
    fillArray(arr, index + 1, input);
    }
    
    /**
     * Recursive function to count the number of even numbers in a given array.
     * @param arr: the array of strings representing numbers
     * @param index: the current index to check
     * @return the count of even numbers in the array
    */
    public static int countEven(String [] arr, int index){
        //Error case: when arr is null
        if(arr == null){
           return -1;
        }
        if (index == arr.length) {
           return 0; //base case
        }
       
        int num;

        //try-catch block for exception of non-numeric value
        try {
            num = Integer.parseInt(arr[index]);
        } 
        catch (NumberFormatException e) {
            return -2;
        }
        
        // recursive call
        int count = countEven(arr, index + 1); 

        //Error case: non-numeric value
        if (count == -2){
            return -2;
        }

        if (num % 2 == 0)
            return 1 + count;
        else
            return count;
    }

    /**
     * Recursive function to count the number of vowels in a string.
     * @param str: the string to analyze
     * @param index: the current index of the string
     * @return the number of vowels in the string, -1 if the string is empty
    */
    public static int countVowels(String input, int index) {
        //Error case when string is empty
        if (input.length() == 0){
            return -1;
        }

        
        if (index == input.length()){
            return 0; //base case
        } 

        char ch = input.charAt(index);
        int count = 0;
        
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
            ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
            count = 1; 
        } 
        // recursive call
        return count + countVowels(input, index + 1);
    }
    
	public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int fibInput = 0;
        boolean validFib = false;
        //loop to get a valid number for fibonacci sum
        while (!validFib){
             System.out.println("Input a Fibonacci number: ");
            try {
                fibInput = input.nextInt();
                validFib = true;
            }
            catch (InputMismatchException e) {
                System.out.println("Enter a valid input (interger value): ");
                input.nextLine();
            }
        }
        System.out.println("Sum of Fibonacci series term: " + sumFib(fibInput));

        //input array length
        int length = 0;
        try {
            System.out.println("Enter the length of the array: ");
            length = input.nextInt();
        } 
        catch (InputMismatchException e) {
            System.out.println("Error: Array length must be a valid integer.");
            return;
        }

        String[] arr = new String[length];
        //method to fill string array
        fillArray(arr, 0, input);

        //count even numbrs in array
        int evenCount = countEven(arr, 0);
        if (evenCount == -1) {
            System.out.println("Error: Array is null.");
        } 
        else if (evenCount == -2) {
            System.out.println("Error: Invalid input(non-neumeric value)");
        } 
        else {
            System.out.println("Count of even numbers: " + evenCount);
        }

        //count vowels in a file
        try {
        File file = new File("resursion_input.txt");
        Scanner fileReader = new Scanner(file);

        if (!fileReader.hasNextLine()) {
            System.out.printf("The file \"resursion_input.txt\" is empty ");
            System.out.println(-1);
            fileReader.close();
            return;
        }

        int vowelCount = 0;
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            vowelCount += countVowels(line, 0);   
        }
        fileReader.close();

        if (vowelCount == -1) {
            System.out.println("The file contains no vowels or is empty.");
        } 
        else {
            System.out.println("Number of vowels found in given file: " + vowelCount);
        }

        }
        catch (FileNotFoundException e) {
            System.out.println("Error: File 'resursion_input.txt' not found.");
        }
    }
		
}
