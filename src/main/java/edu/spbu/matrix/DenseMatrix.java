package edu.spbu.matrix;

import java.util.Scanner;
import java.io.*;

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
    try {
      //open "in" file
      FileInputStream in;
      try {
        in = new FileInputStream(fileName);
      } catch (FileNotFoundException e) {
        System.out.println("In file not found");
        return;
      }
      //getting size from the first row
      String[] firstRow;
      Scanner scan = new Scanner(in);
      firstRow = (scan.nextLine()).split(" ");
      denseSize = (firstRow).length;
      denseMatrix = new double[denseSize][denseSize];
      //recording the first row
      for (int i = 0; i < denseSize; i++) {
        denseMatrix[0][i] = Double.parseDouble(firstRow[i]);
      }
      //recording the other rows
      for (int i = 1; i < denseSize; i++) {
        for (int j = 0; j < denseSize; j++) {
          if(scan.hasNextDouble()) {
            denseMatrix[i][j] = scan.nextDouble();
          } else {
            System.out.println("The wrong format of number");
          }
        }
      }
      //close file
      in.close();
    } catch (Exception e) {
      System.out.println("Something is wrong");
      return;
    }
  }

  public void printMatrix(String fileName) {
    try {
      //open "out" file
      try {
        FileOutputStream fout = new FileOutputStream(fileName);
        //Recording in file
        for (int i = 0; i < denseSize; i++) {
          for (int j = 0; j < denseSize; j++) {
            fout.write((String.valueOf(denseMatrix[i][j]) + " ").getBytes());
          }
          fout.write(("\r\n").getBytes());
        }
      } catch (FileNotFoundException e) {
        System.out.println("Out file was not found");
      }
    } catch (Exception e) {
      System.out.println("Error in method printMatrix");
    }
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
