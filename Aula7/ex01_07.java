package Aula7;

public class ex01_07 {
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

//Como n(n−1)/2 = n²/2 − n/2, descartando o termo de menor ordem e a constante, obtém-se O(n²).