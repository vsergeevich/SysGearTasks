/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysgears.task1;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * To solve this task we generate an array of random numbers. In this array
 * every elements we add twice, except for one(unique). Then we write this array
 * to a file in YAML-format and read its in new array(Integer). Search an unique
 * elemen we make using an operation bitwise XOR. For example 10^10 = 0;
 * 10^10^15 = 15
 *
 * @author Tyrin Victor
 */
public class Task1 {

    public static void main(String[] args) throws Exception {
        Task1 task1 = new Task1();
        int generatedArray[] = task1.generateRandomArray(20); //GeFnerating array, size = 20
        task1.writeToYamlFile(generatedArray); //Saving to file
        YamlReader reader = new YamlReader(new FileReader("data.yml")); 
        Integer[] list = reader.read(Integer[].class); // Reading from file
        System.out.println("Array: " + Arrays.toString(list));
        System.out.println("Unique element in array is " + task1.searchUniqueElement(list));
    }

    /**
     * Generate array of random numbers between 0 and 100. Every elements
     * contains twice, except the last(unique - max element from array plus 1).
     * Because the desired number of elements is an odd, check @param arraySize.
     * If it is even - increase the size of the array.
     *
     * @param arraySize
     * @return
     */
    private int[] generateRandomArray(int arraySize) {
        arraySize = (arraySize % 2 == 0) ? arraySize + 1 : arraySize;
        Random random = new Random();
        int[] array = new int[arraySize];
        int tmpRandom, maxElem = 0;
        for (int i = 0; i < arraySize - 1; i++) {
            tmpRandom = random.nextInt(100);
            if (maxElem < tmpRandom) {
                maxElem = tmpRandom;
            }
            array[i] = array[++i] = tmpRandom;
        }
        array[arraySize - 1] = maxElem + 1;
        return array;
    }

    /**
     * Searches an unique element using an operation bitwise XOR
     *
     * @param arrray
     * @return number without pair
     */
    private int searchUniqueElement(Integer[] arrray) {
        int rez = 0;
        for (int i : arrray) {
            rez = rez ^ i;
        }
        return rez;
    }
/**
 * Save the array to a file in YAML-format
 * @param array
 * @throws IOException 
 */
    private void writeToYamlFile(int[] array) throws IOException {
        YamlWriter writer = new YamlWriter(new FileWriter("data.yml"));
        writer.write(array);
        writer.close();
    }
}
