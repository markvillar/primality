/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public static void main(String args[]) {

        int inputNumber = 50000000;

        //Generate ArrayList of boolean values
        List<Boolean> primes = new ArrayList<>(Arrays.asList(new Boolean[inputNumber + 1]));
        //Set all boolean values to false
        Collections.fill(primes, false);

        //Generate numbers from 0 to inputNumber for Testing
        List<Integer> generatedNumbers = IntStream.range(1, (inputNumber + 1)).boxed().collect(Collectors.toList());

        //List to store prime numbers
        List<Integer> primeSet = new ArrayList<>(Arrays.asList(new Integer[inputNumber + 1]));

        //Start timers
        long startTime = System.currentTimeMillis();
        long startTimeNano = System.nanoTime();

        //Test the values
        generatedNumbers.forEach((number) -> {
            boolean result = checkIfPrime(number);
            primes.set(number, result);
        });

        //Retrieve all prime numbers
        for (int iterator = 0; iterator < primes.size(); iterator++) {

            boolean result = primes.get(iterator);

            if (result == true) {
                primeSet.set(iterator, iterator);
            }

        }

        //End timers
        long endTimeNano = System.nanoTime();
        long endTime = System.currentTimeMillis();

        //Uncomment to output all prime values
        //primeSet.stream().filter(value -> value != null).forEach((result) -> {
        //    System.out.print(result + ", ");
        //});

        System.out.println(" ");
        System.out.println("Sequential Version");
        System.out.println("Calculated in " + (endTimeNano - startTimeNano) + " nanoseconds");
        System.out.println("Calculated in " + (endTime - startTime) + " milliseconds");

    }

}
