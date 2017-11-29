package edu.spbu.matrix;

import java.io.IOException;

public class MatrixMain {
    public static void main(String args[]) {
        try {
            DenseMatrix DMatrix = new DenseMatrix("src/main/DenseFirst.txt");
            System.out.println(DMatrix.denseSize);

            DMatrix.printMatrix("src/main/DenseOut.txt");

            DenseMatrix ResDD = new DenseMatrix(DMatrix.denseSize);
            ResDD = (DenseMatrix) DMatrix.mul(DMatrix);
            ResDD.printMatrix("src/main/DenseRes.txt");

            ResDD = (DenseMatrix) DMatrix.dmul(DMatrix);
            ResDD.printMatrix("src/main/DenseResDMul.txt");

            SparseMatrix SMatrix = new SparseMatrix("src/main/SparseFirst.txt");
            System.out.println(SMatrix.sparseSize);

            SMatrix.printMatrix("src/main/SparseOut.txt");
            SMatrix.transSparse().printMatrix("src/main/TransposeOut.txt");

            SparseMatrix ResSS = new SparseMatrix(SMatrix.sparseSize);
            ResSS = (SparseMatrix) SMatrix.mul(DMatrix);
            ResSS.printMatrix("src/main/SparseDenseRes.txt");

            ResSS = (SparseMatrix) DMatrix.mul(SMatrix);
            ResSS.printMatrix("src/main/DenseSparseRes.txt");

            ResSS = (SparseMatrix) SMatrix.mul(SMatrix);
            ResSS.printMatrix("src/main/SSRes.txt");

            ResSS = (SparseMatrix) SMatrix.dmul(SMatrix);
            ResSS.printMatrix("src/main/SSResDMul.txt");

            ResSS = (SparseMatrix) SMatrix.dmul(DMatrix);
            ResSS.printMatrix("src/main/SparseDenseResDMul.txt");

            ResSS = (SparseMatrix) DMatrix.dmul(SMatrix);
            ResSS.printMatrix("src/main/DenseSparseResDMul.txt");

            System.out.println("Processors: " + Runtime.getRuntime().availableProcessors());

        } catch (Exception e) {
            System.out.println("Error in main");
            return;
        }
    }
}
