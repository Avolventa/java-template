package edu.spbu.matrix;

import java.io.IOException;

public class MatrixMain {
    public static void main(String args[]) {
        try {
            DenseMatrix DMatrix = new DenseMatrix("src/main/DenseFirst.txt");
            System.out.println(DMatrix.denseSize);

            DMatrix.printMatrix("src/main/DenseOut.txt");
        } catch (Exception e) {
            System.out.println("Error in main");
            return;
        }
    }
}
