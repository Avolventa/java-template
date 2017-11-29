package edu.spbu.matrix;

import org.junit.Test;
import org.junit.Assert;

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
    Assert.assertTrue(expected.equals(d1.mul(d2)));
  }

  @Test
  public void mulSD() {
    Matrix s1 = new SparseMatrix("s1.txt");
    Matrix d2 = new DenseMatrix("d2.txt");
    Matrix expected = new SparseMatrix("resultSD.txt");
    Assert.assertTrue(expected.equals(s1.mul(d2)));
  }

  @Test
  public void mulDS() {
    Matrix d1 = new DenseMatrix("d1.txt");
    Matrix s2 = new SparseMatrix("s2.txt");
    Matrix expected = new SparseMatrix("resultDS.txt");
    Assert.assertTrue(expected.equals(d1.mul(s2)));
  }

  @Test
  public void mulSS() {
    Matrix s1 = new SparseMatrix("s1.txt");
    Matrix s2 = new SparseMatrix("s1.txt");
    Matrix expected = new SparseMatrix("resultSS.txt");
    Assert.assertTrue(expected.equals(s1.mul(s1)));
  }

  @Test
  public void dmulDD() {
    Matrix d1 = new DenseMatrix("d1.txt");
    Matrix d2 = new DenseMatrix("d2.txt");
    Matrix expected = new DenseMatrix("resultDD.txt");
    Assert.assertTrue(expected.equals(d1.dmul(d2)));
  }

  @Test
  public void dmulSD() {
    Matrix s1 = new SparseMatrix("s1.txt");
    Matrix d2 = new DenseMatrix("d2.txt");
    Matrix expected = new SparseMatrix("resultSD.txt");
    Assert.assertTrue(expected.equals(s1.dmul(d2)));
  }

  @Test
  public void dmulDS() {
    Matrix d1 = new DenseMatrix("d1.txt");
    Matrix s2 = new SparseMatrix("s2.txt");
    Matrix expected = new SparseMatrix("resultDS.txt");
    Assert.assertTrue(expected.equals(d1.dmul(s2)));
  }

  @Test
  public void dmulSS() {
    Matrix s1 = new SparseMatrix("s1.txt");
    Matrix s2 = new SparseMatrix("s1.txt");
    Matrix expected = new SparseMatrix("resultSS.txt");
    Assert.assertTrue(expected.equals(s1.dmul(s1)));
  }

  @Test
  public void catchEquals() {
    Matrix s1 = new SparseMatrix("s1.txt");
    Matrix expected = new SparseMatrix("s1.txt");
    Assert.assertTrue(expected.equals(s1));
  }
}
