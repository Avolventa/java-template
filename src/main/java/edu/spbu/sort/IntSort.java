package edu.spbu.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by artemaliev on 07/09/15.
 */
public class IntSort {
  public static void sort (int array[]) {
    int len = array.length;
    int n = 1; // кратность сравнений (сравнивать по 1-му елем., 2-м ...)
    int shift; // сдвиг в перебираемом массиве
    int two_size; // количество элементов для 2-го массива
    int[] arr2;
    while (n < len) {
      shift = 0;
      while (shift < len) {
        if (shift + n >= len) break;
        two_size = (shift + n * 2 > len) ? (len - (shift + n)) : n;
        arr2 = merge(Arrays.copyOfRange(array, shift, shift + n),
                Arrays.copyOfRange(array, shift + n, shift + n + two_size));
        for (int i = 0; i < n + two_size; ++i)
          array[shift + i] = arr2[i]; // замена на отсортированное
        shift += n * 2;
      }
      n *= 2;
    }
  }
  private static int[] merge(int[] arr_1, int[] arr_2) {
    int len_1 = arr_1.length, len_2 = arr_2.length;
    int a = 0, b = 0, len = len_1 + len_2; // a, b - счетчики в массивах
    int[] result = new int[len];
    for (int i = 0; i < len; i++) {
      if (b < len_2 && a < len_1) {
        if (arr_1[a] > arr_2[b]) result[i] = arr_2[b++];
        else result[i] = arr_1[a++];
      } else if (b < len_2) {
        result[i] = arr_2[b++];
      } else {
        result[i] = arr_1[a++];
      }
    }
    return result;
  }


  public static void sort (List<Integer> list) {
    Collections.sort(list);
  }
}
