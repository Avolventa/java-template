package edu.spbu.matrix;

import java.util.*;
import java.io.*;
/**
 * Разряженная матрица
 */

public class SparseMatrix implements Matrix
{
  private class Row extends HashMap<Integer, Double> {
    public boolean rEquals(Row row) {
      for (int i = 0; i < sparseSize; i++) {
        Double a = this.get(i);
        Double b = row.get(i);
        if ((a == null) && (b != null)||(a != null) && (b == null)){
          return false;
        }
        if (a != b && !a.equals(b)) {
          return false;
        }
      }
      return true;
    }
  }

  public int sparseSize;
  //<Key,<innerKey,Element>> = <Row, <Column, value>>
  public HashMap<Integer, Row> sparseMatrix;
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
      Double tmp;
      //recording the first row
      for (int i = 0; i < sparseSize; i++) {
        tmp = Double.parseDouble(firstRow[i]);
        if (!tmp.equals(0.0)) {
          row.put(i, tmp);
        }
      }
      if (row != null) {
        sparseMatrix.put(0, row);
      }
      //recording the other rows
      for (int i = 1; i < sparseSize; i++) {
        row = new Row();
        for (int j = 0; j < sparseSize; j++) {
          if(scan.hasNextDouble()) {
            tmp = scan.nextDouble();
            if (tmp != 0.0) {
              row.put(j, tmp);
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
  }

  public void printMatrix(String fileName) {
    try {
      //open "out" file
      try {
        FileOutputStream fout = new FileOutputStream(fileName);
        //Recording in file
         Row row;
        for (int i = 0; i < sparseSize; i++) {
          row = sparseMatrix.get(i);
          if (row != null) {
            for (int j = 0; j < sparseSize; j++) {
              if (row.get(j) != null) {
                fout.write((String.valueOf(row.get(j)) + " ").getBytes());
              } else {
                fout.write(("0.0 ").getBytes());
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

  public SparseMatrix transSparse() {
    SparseMatrix transpose = new SparseMatrix(sparseSize);
    for (int i = 0; i < sparseSize; i++) {
      Row row = new Row();
      for (int j = 0; j <sparseSize; j++) {
        if (sparseMatrix.get(j).get(i) != null) {
          row.put(j, (sparseMatrix.get(j)).get(i));
        }
      }
      if (row != null) {
        transpose.sparseMatrix.put(i, row);
      }
    }
    return transpose;
  }
  /**
   * однопоточное умножение матриц
   * должно поддерживаться для всех 4-х вариантов
   *
   * @param o
   * @return
   */
  @Override public Matrix mul(Matrix o) {
    if (o instanceof DenseMatrix) {
      DenseMatrix other = (DenseMatrix) o;
      SparseMatrix result = new SparseMatrix(sparseSize);
      if (sparseSize == other.denseSize) {
        for (int i = 0; i < sparseSize; i++) {
          Row row = new Row();
          for (int j = 0; j < sparseSize; j++) {
            double sum = 0.0;
            for (int k = 0; k < sparseSize; k++) {
              if (sparseMatrix.get(i).get(k) != null) {
                sum += sparseMatrix.get(i).get(k) * other.denseMatrix[k][j];
              }
            }
            if (!(sum == 0.0)) {
              row.put(j, sum);
            }
          }
          if (row != null) {
            result.sparseMatrix.put(i, row);
          }
        }
      } else {
        System.out.println("Error dimension");
      }
      return result;
    } else {
      SparseMatrix other = ((SparseMatrix) o).transSparse();
      SparseMatrix result = new SparseMatrix(sparseSize);
      if (sparseSize == other.sparseSize) {
        for (int i = 0; i < sparseSize; i++) {
          Row row = new Row();
          for (int j = 0; j < sparseSize; j++) {
            double sum = 0.0;
            for (int k = 0; k < sparseSize; k++) {
              if (sparseMatrix.get(i).get(k) != null && other.sparseMatrix.get(j).get(k) != null) {
                sum += sparseMatrix.get(i).get(k) * other.sparseMatrix.get(j).get(k);
              }
            }
            if (!(sum == 0.0)) {
              row.put(j, sum);
            }
          }
          if (row != null) {
            result.sparseMatrix.put(i, row);
          }
        }
      } else {
        System.out.println("Error dimension");
      }
      return result;
    }
  }

  /**
   * многопоточное умножение матриц
   *
   * @param o
   * @return
   */

  class NewThread implements Runnable {
    Thread t;
    int startRow;
    int endRow;
    Matrix other;
    SparseMatrix result;

    NewThread (Matrix o, SparseMatrix res, int start, int end) {
      other = o;
      result = res;
      startRow = start;
      endRow = end;
      t = new Thread(this);
      System.out.println("Thread for " + start + " row has been running");
      t.start();
    }

    @Override public void run() {
      if (other instanceof DenseMatrix) {
        DenseMatrix otherDense = (DenseMatrix) other;
        for (int i = startRow; i < endRow; i++) {
          Row row = new Row();
          for (int j = 0; j < sparseSize; j++) {
            double sum = 0.0;
            for (int k = 0; k < sparseSize; k++) {
              if (sparseMatrix.get(i).get(k) != null) {
                sum += sparseMatrix.get(i).get(k) * otherDense.denseMatrix[k][j];
              }
            }
            if (!(sum == 0.0)) {
              row.put(j, sum);
            }
          }
          if (row != null) {
            result.sparseMatrix.put(i, row);
          }
        }

      } else {
        SparseMatrix otherSparse = ((SparseMatrix) other).transSparse();
        for (int i = startRow; i < endRow; i++) {
          Row row = new Row();
          for (int j = 0; j < sparseSize; j++) {
            double sum = 0.0;
            for (int k = 0; k < sparseSize; k++) {
              if (sparseMatrix.get(i).get(k) != null && otherSparse.sparseMatrix.get(j).get(k) != null) {
                sum += sparseMatrix.get(i).get(k) * otherSparse.sparseMatrix.get(j).get(k);
              }
            }
            if (!(sum == 0.0)) {
              row.put(j, sum);
            }
          }
          if (row != null) {
            result.sparseMatrix.put(i, row);
          }
        }
      }
    }
  }

  @Override public Matrix dmul(Matrix o)
  {
    Matrix other = o;
    SparseMatrix result = new SparseMatrix(sparseSize);

    int countThreads = Runtime.getRuntime().availableProcessors();
    if (countThreads > sparseSize) {
      countThreads = sparseSize;
    }
    NewThread[] thread = new NewThread[countThreads];

    int countRows = sparseSize / countThreads;
    int add = sparseSize % countThreads;

    int start = 0;
    thread[0] = new NewThread(other, result, start, start + countRows + add);
    for (int i = 1; i < countThreads; i++) {
      start += countRows;
      thread[i] = new NewThread(other, result, start, start + countRows);
    }
    try {
      for (int i = 0; i < countThreads; i++) {
        thread[i].t.join();
      }
    } catch (InterruptedException e) {
      System.out.println("Interrupted exception");
    }
    return result;
  }

  /**
   * сравнивает с обоими вариантами
   * @param o
   * @return
   */
  @Override public boolean equals(Object o) {
    if(!(o instanceof SparseMatrix)){
      return false;
    }
    SparseMatrix other = (SparseMatrix) o;
    for (int i = 0; i < sparseSize; i++) {
      Row a = sparseMatrix.get(i);
      Row b = other.sparseMatrix.get(i);
      if ((a == null) && (b != null)||(a != null) && (b == null)){
        return false;
      }
      if (a != b && !a.rEquals(b)) {
        return false;
      }
    }
  return true;
  }

}
