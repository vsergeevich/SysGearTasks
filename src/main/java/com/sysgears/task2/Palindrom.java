/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysgears.task2;

import java.util.Scanner;

/**
 * Solving this task includes next steps: 
 * 1)Building an array of digits from input number.(1234 -> {1, 2, 3, 4}
 * 2)Check if the number consists of the digits "9". If true - return number + 2
 * 3)Split it into the left half and right half ({1,2}, {3, 4})
 * 4) Compare the last digit in the left half and the first digit in the
 * right half. If the right is greater than the left, increment the left and
 * stop. {1, 3}. If the right is less than the left, stop. c. If the right is
 * equal to the left, repeat step 4 with the second-last digit in the left and
 * the second digit in the right (and so on). Take the left half and append the
 * left half reversed. ThatZ's your next largest palindrome. (1331)
 * 5)If the number is already palindrome - increment increment the left part of 
 * array(step 4)
 *
 * @author Tyrin Victor
 */
public class Palindrom {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i;
        do {
            System.out.print("Enter a positive integer; enter -1 to exit: ");
            if (sc.hasNextInt()) {
                i = sc.nextInt();
                if (i == -1) {
                    return;
                }
                int r = nextPalindrom(i);
                System.out.println("Next palindrom is " + r);
            }
        } while (true);

    }

    private static int nextPalindrom(int in) {
        int i, j;
        String inputNumber = Integer.toString(in);
        int length = inputNumber.length();
        int[] arrayOfDigits = new int[length];
        for (int k = 0; k < length; k++) {
            arrayOfDigits[k] = inputNumber.charAt(k) - '0';
        }
        if (checkAllDigitsEquals9(arrayOfDigits)) { //Check if all digits are 9
            return in + 2;
        }

        int mid = length / 2;
        if (length % 2 == 0) {
            i = mid - 1;
            j = mid;
        } else {
            i = mid - 1;
            j = mid + 1;
        }

        while (i >= 0 && arrayOfDigits[i] == arrayOfDigits[j]) {
            i--;
            j++;
        }
        // further - checking is whether the number palindrome already 
        if (i == -1) {
            if (length % 2 == 0) {
                i = mid - 1;
                j = mid;
            } else {
                i = mid;
                j = mid;
            }
            return incrementCurrentPalindrome(arrayOfDigits, i, j);

        } else {
            // If the number is not a palindrome - step 4.
            if (arrayOfDigits[i] > arrayOfDigits[j]) {

                while (i >= 0) {
                    arrayOfDigits[j] = arrayOfDigits[i];
                    i--;
                    j++;
                }
                return arrayToNumber(arrayOfDigits);
            } else { 
                if (length % 2 == 0) {
                    i = mid - 1;
                    j = mid;
                } else {
                    i = mid;
                    j = i;
                }
                return incrementCurrentPalindrome(arrayOfDigits, i, j);
            }
        }
    }
  
    /**
     * Check if the number consists of the digits "9".
     *
     * @param in
     * @return
     */
    private static boolean checkAllDigitsEquals9(int in[]) {
        for (int k = 0; k < in.length; k++) {
            if (in[k] != 9) {
                return false;
            }
        }
        return true;
    }

    /**
     * Increments the value in array in position i and j. If array[i] = 9, then
     * array[i-1] = array[i-1] +1, array[i] = 0; array[j] is a mirror reflection
     * of array[i] in right part of array
     *
     * @param array
     * @param i
     * @param j
     * @return
     */
    private static int incrementCurrentPalindrome(int[] array, int i, int j) {
        array[i]++;
        array[j] = array[i];
        while (array[i] == 10) {
            array[i] = 0;
            array[j] = 0;
            array[--i]++;
            array[++j] = array[i];
        }

        while (i >= 0) {
            array[j] = array[i];
            i--;
            j++;
        }
        return arrayToNumber(array);
    }

    /**
     * Converts array of digits to integer. For example {1, 2, 1} -> 121
     *
     * @param mass
     * @return
     */
    static int arrayToNumber(int[] mass) {
        StringBuilder str = new StringBuilder("");
        for (int k = 0; k < mass.length; k++) {
            str.append(mass[k]);
        }
        return new Integer(str.toString());
    }
}
