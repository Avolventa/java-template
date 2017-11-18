package edu.spbu.matrix;

public class MatrixMain {
    public static void main(String args[]) {
        DenseMatrix DMatrix = new DenseMatrix("src/main/DenseFirst.txt");
        System.out.println(DMatrix.size);
    }
}
