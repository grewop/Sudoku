package sudoku.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;

public class SudokuController {
	private SudokuView sudokuView;
	
	private SudokuModel sudokuModel;

	public SudokuController(SudokuView sudokuView, SudokuModel sudokuModel) {
		this.sudokuView = sudokuView;
		this.sudokuModel = sudokuModel;

		this.sudokuView.getGameFieltOne().addActionListener(l -> sudokuModel.loadGameField(sudokuView.getMainField(), sudokuView.getGameLayoutOne()));
		this.sudokuView.getGameFieltTwo().addActionListener(l -> sudokuModel.loadGameField(sudokuView.getMainField(), sudokuView.getGameLayoutTwo()));
		this.sudokuView.getGameFieltThree().addActionListener(l -> sudokuModel.loadGameField(sudokuView.getMainField(), sudokuView.getGameLayoutThree()));
		this.sudokuView.getGameFieltFour().addActionListener(l -> sudokuModel.loadGameField(sudokuView.getMainField(), sudokuView.getGameLayoutFour()));
		this.sudokuView.getMenuSaveGame().addActionListener(new SaveArray());
		this.sudokuView.getMenuLoadGame().addActionListener(new LoadArray());
		this.sudokuView.getClearGame().addActionListener(new ClearField());
		this.sudokuView.getFinisghGame().addActionListener(new FinishGame());
		this.sudokuView.getSolveGame().addActionListener(new SolveGame());
		this.sudokuView.getExit().addActionListener(new ExitGame());
		this.sudokuView.getMenuHelpGame().addMouseListener((ClickedListener) (e) -> {
			sudokuModel.Info(sudokuView.getFrameHelp(), sudokuView.gethPanel());
		});
		this.sudokuView.getMenuInfoGame().addMouseListener((ClickedListener) (e) -> {

			sudokuModel.aboutAuthor(sudokuView.getFrameME(), sudokuView.getAuthorPanel());
		});
	}

	public SudokuView getSudokuView() {
		return sudokuView;
	}

	public void setSudokuView(SudokuView sudokuView) {
		this.sudokuView = sudokuView;
	}

	public SudokuModel getSudokuModel() {
		return sudokuModel;
	}

	public void setSudokuModel(SudokuModel sudokuModel) {
		this.sudokuModel = sudokuModel;
	}

	

	class SaveArray implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {

				sudokuModel.saveGame(sudokuView.getMainField());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	class LoadArray implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			try {
				sudokuModel.loadGame(sudokuView.getMainField());
			} catch (NumberFormatException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	class ClearField implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			sudokuModel.clearGrid(sudokuView.getMainField());

		}

	}

	class FinishGame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			sudokuModel.finishGame(sudokuView.getFrame(), sudokuView.getMainField());

		}

	}

	class SolveGame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!sudokuModel.solve(sudokuView.getMainField(), sudokuView.getFrame())) {
				JOptionPane.showMessageDialog(sudokuView.getFrame(), "Nie można rozwiązać");
				return;
			}
			sudokuModel.printBoard(sudokuView.getMainField());

		}

	}

	class ExitGame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);

		}

	}
	class NewGameLayout implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}