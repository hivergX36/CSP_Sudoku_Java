import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Csp_Sudoku {
    public int rows;
    public int cols;
    public int[][] data;

    public Csp_Sudoku() {
        this.rows = 9;
        this.cols = 9;
        this.data = new int[9][9];

    }

    public Csp_Sudoku(String filename) {
        String[] values;
        this.rows = 9;
        this.cols = 9;
        this.data = new int[9][9];
        File input = new File(filename);
        try (Scanner scanner = new Scanner(input)) {
            for (int i = 0; i < this.rows; i++) {
                if (scanner.hasNextLine()) {
                    values = scanner.nextLine().split(" ");
                    for (int j = 0; j < this.cols; j++) {
                        data[i][j] = Integer.parseInt(values[j]);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void set(int row, int col, int value) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        this.data[row][col] = value;
    }

    public int get(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return this.data[row][col];
    }

    public int getNbRows() {
        return this.rows;
    }

    public int getNbCols() {
        return cols;
    }

    public void display_matrix() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.print(this.get(i, j) + " ");
            }
            System.out.println();
        }
    }

    public boolean check_sudoku_constraints(int row, int col, int value) {
        // Check row
        for (int j = 0; j < cols; j++) {
            if (data[row][j] == value) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < rows; i++) {
            if (data[i][col] == value) {
                return false;
            }
        }

        // Check 3x3 subgrid
        int startRow = row - (row % 3);
        int startCol = col - (col % 3);
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (data[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    public void solve_sudoku() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (data[i][j] == 0) { // Find an empty cell
                    for (int val = 1; val <= 9; val++) {
                        if (check_sudoku_constraints(i, j, val)) {
                            this.set(i, j, val); // Place the value
                            this.solve_sudoku(); // Recursively try to solve the rest
                            this.set(i, j, 0); // Backtrack
                        }
                    }

                    return; // Return if no value can be placed
                }
            }

        }

        this.display_matrix();

    }

}