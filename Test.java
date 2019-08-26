/*package sudokuapp;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import java.awt.Insets;
import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.scene.control.RadioButton;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Test extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 800, 500);
		primaryStage.setTitle("BookReader");
		primaryStage.setScene(scene);
		primaryStage.show();

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(new File("undantagsord.txt")); // Påbörjar
																	// skapande
																	// av GWC
		Set<String> stopwords = new HashSet<String>();

		while (scan.hasNext()) {
			String word = scan.next().toLowerCase();
			stopwords.add(word);
		}

		GeneralWordCounter r = new GeneralWordCounter(stopwords); // GWC har
																	// skapats

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) { // Processar boken
			String word = s.next().toLowerCase();
			r.process(word);
		}
		s.close();

		// Läser ut en mängd (Set) med alla ord och deras antal
		Set<Map.Entry<String, Integer>> counter = r.getWords();

		// Gör en observerbar lista över alla ord
		// (https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ListView.html)
		ObservableList<Map.Entry<String, Integer>> words = FXCollections.observableArrayList(counter);
		ListView<Map.Entry<String, Integer>> listView = new ListView<Map.Entry<String, Integer>>(words);

		root.setCenter(listView);

		// HBox med knappar och textfält
		// (https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/HBox.html)
		HBox hbox = new HBox();
		// ToggleGroup group = new ToggleGroup(); // V6, vad innebär
		// ToggleGroup?
		RadioButton button1 = new RadioButton("Alphabetical");
		RadioButton button2 = new RadioButton("Frequency");
		// button1.setToggleGroup(group);
		// button2.setToggleGroup(group);

		TextField text = new TextField();
		text.setPromptText("Search Word");
		Button button3 = new Button("Find");
		Button button4 = new Button("Select File");
		hbox.getChildren().addAll(button1, button2, text, button3, button4);

		button1.setOnAction(event -> {
			words.sort((s1, s2) -> s1.getKey().compareTo(s2.getKey()));
		});
		button2.setOnAction(event -> {
			words.sort((s1, s2) -> s2.getValue() - s1.getValue());
		});
		button3.setOnAction(e -> { // Går det att göra lambdauttrycket på ett
									// effektivare sätt?
			SelectionModel<Map.Entry<String, Integer>> sm = listView.getSelectionModel(); // V4
			for (int i = 0; i < words.size(); i++) {
				if (words.get(i).getKey().equalsIgnoreCase(text.getText().trim())) { // V3
					listView.scrollTo(i);
					sm.select(i);
					return;
				}
			}
			Alert alert = new Alert(AlertType.ERROR); // V5, kanske göra med
														// try/catch istället??
			alert.setTitle("Error in input");
			alert.setHeaderText(null);
			alert.setContentText("Wrong input");
			alert.showAndWait();
		});

		// V7, hur gör man??
		button4.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
		});

		// V1.
		button3.setDefaultButton(true);
		// button3.defaultButtonProperty(); Vad gör denna metoden?

		// V2.
		HBox.setHgrow(text, Priority.ALWAYS); // Förstår inte riktigt
												// Priority-parametern??

		root.setBottom(hbox);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}*/