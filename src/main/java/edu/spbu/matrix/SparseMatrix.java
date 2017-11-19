package edu.spbu.matrix;

import java.util.*;
import java.io.*;
/**
 * Разряженная матрица
 */

public class SparseMatrix implements Matrix
{
  private class Row extends HashMap<Integer, Double> {

  }

  public int sparseSize;
  //<Key,<innerKey,Element>> = <Row, <Column, value>>
  public HashMap<Integer, Row> sparseMatrix;
  public HashMap<Integer, Row> sparseTranspose;
  /**
   * загружает матрицу из файла
   * @param fileName
   */
  public SparseMatrix(String fileName) {
    try {
      //open "in" file
      FileInputStream fin;
      try {
        fin = new FileInputStream(fileName);
      } catch (FileNotFoundException e) {
        System.out.println("In file sparse not found");
        return;
      }
      //getting size from the first row
      String[] firstRow;
      Scanner scan = new Scanner(fin);
      //use local for point double
      scan.useLocale(Locale.US);
      firstRow = (scan.nextLine()).split(" ");
      sparseSize = (firstRow).length;
      sparseMatrix = new HashMap<Integer, Row>();
      Row row = new Row();
      sparseTranspose = new HashMap<Integer, Row>();
      Row rowT = new Row();
      Double tmp;
      //recording the first row
      for (int i = 0; i < sparseSize; i++) {
        tmp = Double.parseDouble(firstRow[i]);
        if (tmp != 0.0) {
          row.put(i, tmp);
          rowT.put(0, tmp);
          sparseTranspose.put(i, rowT);
        }
      }
      if (row != null) {
        sparseMatrix.put(0, row);
      }
      //recording the other rows
      for (int i = 1; i < sparseSize; i++) {
        row = new Row();
        rowT = new Row();
        for (int j = 0; j < sparseSize; j++) {
          if(scan.hasNextDouble()) {
            tmp = Double.parseDouble(firstRow[i]);
            if (tmp != 0.0) {
              row.put(j, tmp);
              rowT.put(i, tmp);
              sparseTranspose.put(j, rowT);
            }
          } else {
            System.out.println("The wrong format of number");
          }
        }
        if (row != null) {
          sparseMatrix.put(i, row);
        }
      }
      //close file
      fin.close();
    } catch (Exception e) {
      System.out.println("Something is wrong");
      return;
    }
  }

  public SparseMatrix(int size) {
    sparseSize = size;
    sparseMatrix = new HashMap<>();
    sparseTranspose = new HashMap<>();
  }

  public void printMatrix(String fileName, HashMap<Integer, Row> Matr) {
    try {
      //open "out" file
      try {
        FileOutputStream fout = new FileOutputStream(fileName);
        //Recording in file
        Row row;
        for (int i = 0; i < sparseSize; i++) {
          row = Matr.get(i);
          if (row != null) {
            for (int j = 0; j < sparseSize; j++) {
              if (row.get(j) != null) {
                fout.write((String.valueOf(row.get(j)) + " ").getBytes());
              }
            }
            fout.write(("\r\n").getBytes());
          }
        }
        fout.close();
      } catch (FileNotFoundException e) {
        System.out.println("Out file was not found");
      }
    } catch (Exception e) {
      System.out.println("Error in method printMatrix");
    }
  }
  /**
   * однопоточное умножение матриц
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
