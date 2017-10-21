package edu.spbu.matrix;

import java.util.Scanner;
import java.io.File;

/**
 * Плотная матрица
 */
public class DenseMatrix implements Matrix
{
  public int denseSize;
  public double denseMatrix[][];
  /**
   * загружает матрицу из файла
   * @param fileName
   */
  public DenseMatrix(String fileName) {
    //открыть файл
    Scanner sc = new Scanner(new File(fileName));
    denseSize = (Scanner.nextLine()).split(" ").length;
    while () {

    }

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
