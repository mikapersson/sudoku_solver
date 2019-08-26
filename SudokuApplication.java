package sudokuapp;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class SudokuApplication extends Application {
	private Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		// Scene scene = new Scene(root);
		// primaryStage.setTitle("My Title");
		// primaryStage.setScene(scene);
		// primaryStage.show();

		// Gör huvudfönstret
		stage = primaryStage;
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 720, 760);
		stage.setTitle("Sudoku");
		stage.setScene(scene);
		stage.show();

		// Skapar sudokulösare
		SudokuSolver sud = new SudokuSolver();

		// Skapar sudokubräde och tillhörande värde-matris
		TilePane tiles = new TilePane(7, 7);
		tiles.setPrefColumns(9);
		tiles.setPrefRows(9);
		tiles.setPadding(new Insets(20));
		TextField[][] board = new TextField[9][9];
		final int SIZE = 70;
		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++) {
				TextField text = new TextField();
				text.setFont(Font.font("Complex", FontWeight.BOLD, 30));
				text.setAlignment(Pos.CENTER);
				text.setPrefSize(SIZE, SIZE);
				if ((i < 3 && k < 3) || (i < 3 && k > 5) || (i > 5 && k < 3) || (i > 5 && k > 5)
						|| (i > 2 && i < 6 && k > 2 && k < 6)) {
					text.setStyle("-fx-background-color: #FFD700;");
				}
				board[i][k] = text;
				tiles.getChildren().add(text);
			}
		}

		stage.setResizable(false); // Det ska inte gå att ändra fönstrets
									// storlek

		// Skapar knappar
		HBox hbox = new HBox(10);
		Button solve = new Button("Solve");
		Button clear = new Button("Clear");
		hbox.getChildren().addAll(solve, clear);
		HBox.setHgrow(solve, Priority.ALWAYS);
		HBox.setHgrow(clear, Priority.ALWAYS);
		hbox.setPadding(new Insets(0, 20, 20, 20)); // Mellanrum mellan hbox och
													// resterande
		solve.setMaxWidth(Double.MAX_VALUE);
		clear.setMaxWidth(Double.MAX_VALUE);
		hbox.setSpacing(15);

		root.setTop(tiles); // Sudokubräde ovanför knapparna
		root.setBottom(hbox);

		solve.setOnAction(event -> { // *Klickar på solve*
			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 9; c++) {
					String value = board[r][c].getText();

					// Undersöker värdet i rutan
					if (value.length() == 0) {
						sud.setValue(r, c, 0);

					} else if (value.length() == 1) {
						char tempChar = value.charAt(0);
						if (Character.isDigit(tempChar)) {
							sud.setValue(r, c, Integer.parseInt(value)); // Lägger
																		// in
																		// värdet
																		// från
																		// rutan
																		// till
																		// sudokulösaren
						} else { // Om rutan innehåller fler än ett tecken
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Fel");
							alert.setHeaderText(null);
							alert.setContentText("Felaktigt värde i rad " + (r + 1) + ", column " + (c + 1) + ": \""
									+ value + "\". Fyll i en siffra från 1 till 9.");
							board[r][c].setStyle("-fx-background-color: #ff0000;"); // Färgar
																					// felaktig
																					// rutan
																					// röd
							alert.showAndWait();
							if ((r < 3 && c < 3) || (r < 3 && c > 5) || (r > 5 && c < 3) || (r > 5 && c > 5)
									|| (r > 2 && r < 6 && c > 2 && c < 6)) {
								board[r][c].setStyle("-fx-background-color: #FFD700;");
							} else { // Om rutan var vit
								board[r][c].setStyle("-fx-background-color: #ffffff;");
							}
							return;
						}
					} else { // Om rutan innehåller fler tecken
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Fel");
						alert.setHeaderText(null);
						alert.setContentText("Felaktigt värde i rad " + (r + 1) + ", column " + (c + 1) + ": \"" + value
								+ "\". Fyll i en siffra från 1 till 9.");
						board[r][c].setStyle("-fx-background-color: #ff0000;");
						alert.showAndWait();
						if ((r < 3 && c < 3) || (r < 3 && c > 5) || (r > 5 && c < 3) || (r > 5 && c > 5)
								|| (r > 2 && r < 6 && c > 2 && c < 6)) {
							board[r][c].setStyle("-fx-background-color: #FFD700;");
						} else {
							board[r][c].setStyle("-fx-background-color: #ffffff;");
						}
						return;
					}
				}
			}

			if (sud.solve()) { // Om det finns en lösning för sudokut
				for (int r = 0; r < 9; r++) {
					for (int c = 0; c < 9; c++) {
						board[r][c].setText(Integer.toString(sud.getValue(r,c))); // Lägger
																				// in
																				// resultatet
																				// i
																				// rutorna
					}
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Fel");
				alert.setHeaderText(null);
				alert.setContentText("Finns ingen lösning!");
				alert.showAndWait();
			}
		});

		// Nollställer brädet
		clear.setOnAction(event -> {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					board[i][j].clear();
				}
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
