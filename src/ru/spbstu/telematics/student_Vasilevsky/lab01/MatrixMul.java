package ru.spbstu.telematics.student_Vasilevsky.lab01;
import java.util.Scanner;

/*
 * Џрограмма считывает размерность матрицы, потом матрицу, 
 * умножает матрицу на транспонированную матрицу и выводит результат;
 */

public class MatrixMul {
	public static void main (String args[])
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("Dimension: ");
		int dimension = scanner.nextInt();
		
		System.out.print("Enter matrix\n");
		
		int[][] matrix = new int[dimension][dimension];
		int[][] tMatrix = new int[dimension][dimension];
		
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				matrix[i][j] = scanner.nextInt();
				tMatrix[j][i] = matrix[i][j];
			}
		}
		
		

		int[][] result = new int[dimension][dimension];
		System.out.println("Result:");
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				for (int k=0; k < dimension; k++) {
					result[i][j] += matrix[i][k] * tMatrix[k][j];
				}
				System.out.print(result[i][j] + "\t");
			}
			System.out.print("\n");
		}
		
	}
}
