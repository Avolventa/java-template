package edu.spbu.matrix;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatrixTest
{
  /**
   * ожидается 4 таких теста
   */
  @Test
  public void mulDD() {
    Matrix d1 = new DenseMatrix("d1.txt");
    Matrix d2 = new DenseMatrix("d2.txt");
    Matrix expected = new DenseMatrix("resultDD.txt");
    assertEquals(expected, d1.mul(d2));
  }

  @Test
  public void mulSD() {
    Matrix s1 = new SparseMatrix("s1.txt");
    Matrix d2 = new DenseMatrix("d2.txt");
    Matrix expected = new DenseMatrix("resultSD.txt");
    assertEquals(expected, s1.mul(d2));
  }

  @Test
  public void mulDS() {
    Matrix d1 = new DenseMatrix("d1.txt");
    Matrix s2 = new SparseMatrix("s2.txt");
    Matrix expected = new DenseMatrix("resultDS.txt");
    assertEquals(expected, d1.mul(s2));
  }

  @Test
  public void mulSS() {
    Matrix s1 = new SparseMatrix("s1.txt");
    Matrix s2 = new SparseMatrix("s2.txt");
    Matrix expected = new DenseMatrix("resultSS.txt");
    assertEquals(expected, s1.mul(s2));
  }
}
