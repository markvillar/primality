/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author UP773229
 * 
 * The algorithm was taken from: https://en.wikipedia.org/wiki/Primality_test
 * 
 */
public class Primality {

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

}
