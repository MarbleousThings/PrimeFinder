/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primefinder;

/**
 *
 * @author Eric Marble
 * 
 */
import java.util.Scanner;
public class PrimeFinder {

    /** This is a small application that uses the Sieve of Eratosthenes algorithm to locate the n-th prime number.
     *  @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        if( args.length > 0){
            n = Integer.parseInt(args[0]);
        }
        else if(scanner.hasNextInt()){
            n = scanner.nextInt();
        }
        else{ 
            System.err.println("Restart with an integer input");
            return;
        }
        PrimeFinder pf = new PrimeFinder();
        System.out.println(pf.generatePrimes(n));
    }
    
    //Create a list of integers up to the 3/2 power of n. 
    public int generatePrimes(int n){
       int result = -1;
       int ceiling = (int)Math.round(Math.pow(n, 1.5));
       long[] integers = new long[ceiling];
       if (n == 1){return 2;} //shortcut
       if (n == 2){return 3;}
       if (n == 3){return 5;}
       if (n == 4){return 7;}
       if (n == 5){return 11;}
       integers = populateIntegerList(integers);
       result = getNthPrime(integers, ceiling, n);
       return result;
    }
    public long[] populateIntegerList(long[] integers){
       //Populate the list of integers for the sieve
       for(int i = 1; i<integers.length; i++){ 
           integers[i] = 1; //assume all numbers are prime to start
       }
       integers[0] = 0;
       integers[1] = 0;
       return integers;
    }
    public int getNthPrime(long[] integerList, int length, int n){
       //Eliminate nonprimes from list, then return the nth prime
       int primeCounter = 0;
       for(int i = 2; i<length; i++){ 
           if(integerList[i] == 1){ //check if known prime
              for(int j = 0; j< length; j++){
                  if((i*i+j*i) < length ){
                    integerList[i*i+j*i] = 0; //factor out by checking against known primes
                  }
               }
           primeCounter++;   
           if(primeCounter == n){
               return i;}
           }
       }
       return -1;
    }
}

