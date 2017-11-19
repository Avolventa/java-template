package edu.spbu.matrix;

import java.io.IOException;

public class MatrixMain {
    public static void main(String args[]) {
        try {
            DenseMatrix DMatrix = new DenseMatrix("src/main/DenseFirst.txt");
            System.out.println(DMatrix.denseSize);

            DMatrix.printMatrix("src/main/DenseOut.txt");

            DenseMatrix Res = new DenseMatrix(DMatrix.denseSize);
            Res = (DenseMatrix) DMatrix.mul(DMatrix);
            Res.printMatrix("src/main/DenseRes.txt");
        } catch (Exception e) {
            System.out.println("Error in main");
            return;
        }
    }
}
