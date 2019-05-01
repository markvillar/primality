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
import com.google.common.Lists;

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

        int numberOfCores = Runtime.getRuntime().availableProcessors();

        int inputNumber = 100;

        List<Integer> listOfPrimes = new ArrayList<>();

        //Generate ArrayList of boolean values
        List<Boolean> primes = new ArrayList<>(Arrays.asList(new Boolean[inputNumber + 1]));
        //Set all boolean values to false
        Collections.fill(primes, false);

        //List to store prime numbers
        List<Integer> primeSet = new ArrayList<>(Arrays.asList(new Integer[inputNumber + 1]));

        //Generate numbers from 0 to inputNumber for Testing
        List<Integer> generatedNumbers;
        generatedNumbers = IntStream.range(1, (inputNumber + 1)).boxed().collect(Collectors.toList());

        List<List<Integer>> listOfLists = Lists.partition(generatedNumbers, numberOfCores);

        for (List<Integer> sublist : listOfLists) {
            System.out.println(sublist);
        }

        //Start timers
        long startTime = System.currentTimeMillis();
        long startTimeNano = System.nanoTime();

        //Create threads
        Primality[] thread = new Primality[numberOfCores];

        for (int core = 0; core < numberOfCores; core++) {
            thread[core] = new Primality();
        }

//        Primality thread1 = new Primality();
//        thread1.listToProcess = generatedNumbers.subList(0, 25);
//
//        Primality thread2 = new Primality();
//        thread2.listToProcess = generatedNumbers.subList(25, 50);
//
//        Primality thread3 = new Primality();
//        thread3.listToProcess = generatedNumbers.subList(50, 75);
//
//        Primality thread4 = new Primality();
//        thread4.listToProcess = generatedNumbers.subList(75, 100);

        //Start threads
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();

        for (int process = 0; process < thread.length; process++) {
            thread[process].start();
        }

        try {
//            thread1.join();
//            thread2.join();
//            thread3.join();
//            thread4.join();
            for (int process = 0; process < thread.length; process++) {
                thread[process].join();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

//        listOfPrimes.addAll(thread1.primeList);
//        listOfPrimes.addAll(thread2.primeList);
//        listOfPrimes.addAll(thread3.primeList);
//        listOfPrimes.addAll(thread4.primeList);

        listOfPrimes.forEach((number) -> {
            System.out.print(number + ", ");
        });

        //End timers
        long endTimeNano = System.nanoTime();
        long endTime = System.currentTimeMillis();

        System.out.println(" ");
        System.out.println("Parallel Version");
        System.out.println("Calculated in " + (endTimeNano - startTimeNano) + " nanoseconds");
        System.out.println("Calculated in " + (endTime - startTime) + " milliseconds");

    }

}
