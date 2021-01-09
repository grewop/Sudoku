package sudoku.project;

import javax.swing.SwingUtilities;



public class Main {

	public static void main(String[] args) {
		SudokuModel sudokuModel = new SudokuModel();
		SudokuView sudokuView = new SudokuView();
		
		
		SudokuController sudokuController = new SudokuController(sudokuView, sudokuModel);
		//sudokuView.setVisible(true);
		//SwingUtilities.invokeLater(SudokuView::new);

	}

}
