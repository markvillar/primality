/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parallel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.google.common.collect.Lists;

/**
 *
 * @author UP773229
 */
public class Main {

    public static Boolean checkIfPrime(int inputNumber) {

        if (inputNumber <= 3) {
            return inputNumber > 1;
        } else if (inputNumber % 2 == 0 || inputNumber % 3 == 0) {
            return false;
        }

        int number = 5;

        while (number * number <= inputNumber) {

            if (inputNumber % number == 0 || inputNumber % (number + 2) == 0) {
                return false;
            }

            number = number + 6;
        }

        return true;
    }

    // Driver Program to test above function 
    public static void main(String args[]) throws Exception {
        
        int availableProcessors = Runtime.getRuntime().availableProcessors();

        int inputNumber = 200;

        int numberOfIntegersPerThread = inputNumber / availableProcessors;

        List<Integer> listOfPrimes = new ArrayList<>();

        //Generate ArrayList of boolean values
        List<Boolean> primes = new ArrayList<>(Arrays.asList(new Boolean[inputNumber + 1]));
        //Set all boolean values to false
        Collections.fill(primes, false);

        //Generate numbers from 0 to inputNumber for Testing
        List<Integer> generatedNumbers;
        generatedNumbers = IntStream.range(1, (inputNumber + 1)).boxed().collect(Collectors.toList());

        //Partition the list according to the available Processors in the System
        List<List<Integer>> listOfLists = Lists.partition(generatedNumbers, numberOfIntegersPerThread);

        //Create threads
        Primality[] threads = new Primality[availableProcessors];

        //List of lists for individual threads
        for (int listIndex = 0; listIndex < availableProcessors; listIndex++) {
            threads[listIndex] = new Primality();
            threads[listIndex].listToProcess = listOfLists.get(listIndex);
        }

        //Start timers
        long startTime = System.currentTimeMillis();
        long startTimeNano = System.nanoTime();

        //Start All Threads
        for (Primality thread : threads) {
            thread.start();
        }

        //Join All Threads
        try {
            for (Primality thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Error: " + e);
        }

        //Collect the results from Threads
        for (Primality thread : threads) {
            listOfPrimes.addAll(thread.primeList);
        }

        //End timers
        long endTimeNano = System.nanoTime();
        long endTime = System.currentTimeMillis();

        //Uncomment to output all prime values
        //listOfPrimes.forEach((number) -> {
        //    System.out.print(number + ", ");
        //});

        System.out.println(" ");
        System.out.println("Parallel Version");
        System.out.println("Calculated in " + (endTimeNano - startTimeNano) + " nanoseconds");
        System.out.println("Calculated in " + (endTime - startTime) + " milliseconds");

    }

}
