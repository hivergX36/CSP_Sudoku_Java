public class Main {
public static void main(String[] args) {
    
    Csp_Sudoku sudoku = new Csp_Sudoku("data.txt");
    sudoku.solve_sudoku();
    sudoku.display_matrix();

         
}
}
