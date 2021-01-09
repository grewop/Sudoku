package sudoku.project;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import view.mainPanel;

public class SudokuController {
	private SudokuView sudokuView;
	private MyTextField[][] mainField;
	private SudokuModel sudokuModel;

	public SudokuController(SudokuView sudokuView, SudokuModel sudokuModel) {
		this.sudokuView = sudokuView;
		this.sudokuModel = sudokuModel;

		this.sudokuView.getMenuSaveGame().addActionListener(new saveArray());
		this.sudokuView.getMenuLoadGame().addActionListener(new loadArray());
		this.sudokuView.getNewGame().addActionListener(new clearField());
		this.sudokuView.getFinisghGame().addActionListener(new finishGame());
		this.sudokuView.getSolveGame().addActionListener(new solveGame());
		this.sudokuView.getExit().addActionListener(new exitGame());
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

	public void markFields() {
		Border border = BorderFactory.createLineBorder(Color.green, 2);
	}

	class saveArray implements ActionListener {

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

	class loadArray implements ActionListener {

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

	class clearField implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			sudokuModel.clearGrid(sudokuView.getMainField());

		}

	}

	class finishGame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			sudokuModel.finishGame(sudokuView.getFrame(), sudokuView.getMainField());

		}

	}

	class solveGame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!sudokuModel.solve(sudokuView.getMainField(), sudokuView.getFrame())) {
				JOptionPane.showMessageDialog(sudokuView.getFrame(), "Nie można rozwiązać");
				return;
			}
			sudokuModel.printBoard(sudokuView.getMainField());

		}

	}

	class exitGame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);

		}

	}

}