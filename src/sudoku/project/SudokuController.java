package sudoku.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;

public class SudokuController {
	private SudokuView sudokuView;
	private SudokuModel sudokuModel;
	private int valueField[][] = new int[9][9];

	public SudokuController(SudokuView sudokuView, SudokuModel sudokuModel) {
		this.sudokuView = sudokuView;
		this.sudokuModel = sudokuModel;

		this.sudokuView.getGameFieltOne().addActionListener(l -> loadGameFields((sudokuView.getMainField()),
				sudokuModel.loadGameField(arrayInt(sudokuView.getMainField()), sudokuView.getGameLayoutOne())));
		this.sudokuView.getGameFieltTwo().addActionListener(l -> loadGameFields((sudokuView.getMainField()),
				sudokuModel.loadGameField(arrayInt(sudokuView.getMainField()), sudokuView.getGameLayoutTwo())));
		this.sudokuView.getGameFieltThree().addActionListener(l -> loadGameFields((sudokuView.getMainField()),
				sudokuModel.loadGameField(arrayInt(sudokuView.getMainField()), sudokuView.getGameLayoutThree())));

		this.sudokuView.getGameFieltFour().addActionListener(l -> loadGameFields((sudokuView.getMainField()),
				sudokuModel.loadGameField(arrayInt(sudokuView.getMainField()), sudokuView.getGameLayoutFour())));
		this.sudokuView.getMenuSaveGame().addActionListener(new SaveArray());
		this.sudokuView.getMenuLoadGame().addActionListener(new LoadArray());
		this.sudokuView.getClearGame().addActionListener(new ClearField());
		this.sudokuView.getFinisghGame().addActionListener(new FinishGame());
		this.sudokuView.getSolveGame().addActionListener(new SolveGame());
		this.sudokuView.getExit().addActionListener(new ExitGame());
		this.sudokuView.getMenuHelpGame().addMouseListener((ClickedListener) (e) -> {

			sudokuView.getFrameHelp().add(sudokuView.gethPanel());

			sudokuView.getFrameHelp().setVisible(true);
		});
		this.sudokuView.getMenuInfoGame().addMouseListener((ClickedListener) (e) -> {
			sudokuView.getFrameME().add(sudokuView.getAuthorPanel());

			sudokuView.getFrameME().setVisible(true);

		});
	}

	// int[][] itexfield z modelu
	public int[][] arrayInt(MyTextField[][] jTextFields) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				if (jTextFields[i][j].getText().equals("")) {
					valueField[i][j] = 0;
				} else {
					valueField[i][j] = Integer.parseInt(jTextFields[i][j].getText());
				}

			}

		}

		return valueField;

	}

	public void loadGameFields(MyTextField[][] jTextFields, int[][] gameField) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int temp = gameField[i][j];
				if (temp == 0) {
					jTextFields[i][j].setText("");
				} else {
					jTextFields[i][j].setText(String.valueOf(temp));
				}

			}
		}
	}

	public void loadSolveGameFields(MyTextField[][] jTextFields, int[][] gameField) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int temp = gameField[i][j];
				if (temp == 0) {
					jTextFields[i][j].setText(String.valueOf(temp));
				} else {
					jTextFields[i][j].setText(String.valueOf(temp));
				}

			}
		}
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

				sudokuModel.saveGame(arrayInt(sudokuView.getMainField()));
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
				loadGameFields(sudokuView.getMainField(), sudokuModel.loadGame(arrayInt(sudokuView.getMainField())));
			} catch (NumberFormatException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	class ClearField implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			loadGameFields(sudokuView.getMainField(), sudokuModel.clearGrid(arrayInt(sudokuView.getMainField())));

		}

	}

	class FinishGame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			sudokuModel.finishGame(arrayInt(sudokuView.getMainField()));

		}

	}

	class SolveGame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!sudokuModel.solve(arrayInt(sudokuView.getMainField()), valueField)) {
				JOptionPane.showMessageDialog(sudokuView.getFrame(), "Nie można rozwiązać");
				return;
			}

			loadSolveGameFields(sudokuView.getMainField(), sudokuModel.getReturnSolve());
		}

	}

	class ExitGame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);

		}

	}

	class NewGameLayout implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}

}