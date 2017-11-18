package edu.spbu.matrix;

import java.util.Scanner;
import java.io.*;

/**
 * Плотная матрица
 */
public class DenseMatrix implements Matrix
{
  public int size;
  public double matrix[][];
  /**
   * загружает матрицу из файла
   * @param fileName
   */
  public DenseMatrix(String fileName) {
    //открыть файл
    File in;
    try {
      in = new File(fileName);
    } catch(FileNotFoundException e) {
      System.out.println("Файл не найден");
      return;
    }
    Scanner sc = new Scanner(in);
    size = (sc.nextLine()).split(" ").length;
    //in.close();
    //закрыть файл
  }
  /**
   * однопоточное умнджение матриц
   * должно поддерживаться для всех 4-х вариантов
   *
   * @param o
   * @return
   */
  @Override public Matrix mul(Matrix o)
  {
    return null;
  }

  /**
   * многопоточное умножение матриц
   *
   * @param o
   * @return
   */
  @Override public Matrix dmul(Matrix o)
  {
    return null;
  }

  /**
   * сравнивает с обоими вариантами
   * @param o
   * @return
   */
  @Override public boolean equals(Object o) {
    return false;
  }

}
