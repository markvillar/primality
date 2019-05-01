/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parallel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author UP773229
 *
 * The algorithm was taken from: https://en.wikipedia.org/wiki/Primality_test
 *
 */
public class Primality extends Thread {

    List<Integer> listToProcess;
    List<Integer> primeList = new ArrayList<>();

    public Boolean checkIfPrime(int inputNumber) {

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

    @Override
    public void run() {

//        for (int number = 0; number < listToProcess.size(); number++) {
//            boolean result = checkIfPrime(number);
//            primeList.add(result);
//        }
        listToProcess.forEach((number) -> {
            
            boolean result = checkIfPrime(number);

            if (result == true) {
                primeList.add(number);
            }
            
        });

    }

}
