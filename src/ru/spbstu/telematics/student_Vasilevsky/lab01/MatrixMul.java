package ru.spbstu.telematics.student_Vasilevsky.lab01;
import java.util.Scanner;

/*
 * Программа считывает размерность матрицы, потом матрицу, 
 * умножает матрицу на транспонированную матрицу и выводит результат;
 */

public class MatrixMul {
	public static void main (String args[])
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("Dimension: ");

		while (!scanner.hasNextInt()) {
			System.out.println("Non int value is skipped");
			scanner.next();
		}
		int dimension = scanner.nextInt();
		dimension = Math.abs(dimension);
		

		
		System.out.print("Enter matrix\n");
		
		int[][] matrix = new int[dimension][dimension];
		int[][] tMatrix = new int[dimension][dimension];
		
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				while (!scanner.hasNextInt()) {
					System.out.println("Non int value is skipped");
					scanner.next();
				}
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
