//Implementar, em Java, o algoritmo Insertion-Sort
//Insertion-Sort: comparison-based algorithm that builds a sorted array one element at a time

package Aula6;

public class ex01 {
    public static void main(String[] args) {
        int[] data = {12, 11, 13, 5, 6};
        insertionSort(data);
    }

    public static void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            // Move elements of arr[0..i-1] that are greater than key to one position ahead
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }
}
