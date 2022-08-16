package id32287623232;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ExamFX implements AbstractExamView {
	private Vector<ExamUIEventsListener> allListeners = new Vector<ExamUIEventsListener>();

	public ExamFX(Stage theStage) throws ClassNotFoundException, IOException {

		theStage.setTitle("TEST MANAGMENT SYSTEM");

		// base:
		BorderPane borderPane1 = new BorderPane();

		// top:
		final HBox vTop = new HBox();
		vTop.setBackground(
				new Background(
						new BackgroundFill(
								new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
										new Stop(0, Color.web("#6495ED")), new Stop(1, Color.web("#F5F5F5"))),
								CornerRadii.EMPTY, Insets.EMPTY)));

		vTop.setPadding(new Insets(20, 10, 20, 10));
		Text title = new Text("TEST MANAGMENT SYSTEM");
		title.setFill(Color.BLACK);
		title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		vTop.getChildren().addAll(title);
		vTop.setAlignment(Pos.TOP_LEFT);
		borderPane1.setTop(vTop);

		// center
		HBox hbMenu = new HBox();
		hbMenu.setPadding(new Insets(0, 100, 0, 20));
		Text tfMenu = new Text("PLEASE SELECT AN OPTION");
		tfMenu.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		tfMenu.setFill(Color.BLACK);

		hbMenu.getChildren().addAll(tfMenu);
		borderPane1.setCenter(hbMenu);
		hbMenu.setAlignment(Pos.CENTER);

		// main menu
		final VBox vManu = new VBox(5);
		vManu.setPadding(new Insets(10, 10, 10, 10));
		final Button dataBase = new Button("Data Base");
		dataBase.setPrefSize(150, 35);
		final Button newQuestion = new Button("New Question");
		newQuestion.setPrefSize(150, 35);
		final Button editQuestion = new Button("Edit Question");
		editQuestion.setPrefSize(150, 35);
		final Button editAnswer = new Button("Edit Answer");
		editAnswer.setPrefSize(150, 35);
		final Button deleteAnswer = new Button("Delete Answer");
		deleteAnswer.setPrefSize(150, 35);
		final Button createTest = new Button("Create a Exam");
		createTest.setPrefSize(150, 35);
		final Button createAutomaticTest = new Button("Create an automatic Exam");
		createAutomaticTest.setPrefSize(150, 35);
		final Button copyTest = new Button("Copy an existing Exam");
		copyTest.setPrefSize(150, 35);

		final HBox vManu2 = new HBox(20);
		final Button exit = new Button("Exit");
		exit.setPrefSize(150, 35);
		vManu2.getChildren().addAll(exit);
		vManu2.setAlignment(Pos.BOTTOM_LEFT);

		vManu.getChildren().addAll(dataBase, newQuestion, editQuestion, editAnswer, deleteAnswer, createTest,
				createAutomaticTest, copyTest, vManu2);

		ToolBar toolBar = new ToolBar();
		toolBar.getItems().addAll(vManu, vManu2);
		borderPane1.setLeft(vManu);

		// bottom
		VBox statusBar = new VBox();
		statusBar.setPadding(new Insets(0, 0, 20, 0));
		TextField tfStatusBar = new TextField();
		tfStatusBar.setPrefSize(1000, 80);
		tfStatusBar.setEditable(false);
		tfStatusBar.appendText("No messages...");
		tfStatusBar.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 14));
		statusBar.getChildren().addAll(tfStatusBar);
		borderPane1.setBottom(statusBar);

// data base

		HBox hbDataBase = new HBox();
		hbDataBase.setPadding(new Insets(10, 10, 10, 10));
		hbDataBase.setSpacing(10);

		TextArea tfDataBase = new TextArea();
		tfDataBase.setPrefRowCount(10);
		tfDataBase.setPrefColumnCount(20);
		tfDataBase.setWrapText(true);

		tfDataBase.setPrefSize(600, 350);
		tfDataBase.setEditable(false);

		hbDataBase.getChildren().addAll(tfDataBase);

		dataBase.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				title.setText("DATABASE");

				for (ExamUIEventsListener l : allListeners)
					try {
						tfDataBase.setText(l.printDataToUI());
					} catch (ClassNotFoundException | IOException e1) {
						tfStatusBar.setText("No data to display");
						e1.printStackTrace();
					}

				borderPane1.setCenter(hbDataBase);
				tfStatusBar.setText("Data memory");

			}
		});

// add new question

		HBox vbQuestionType = new HBox();
		vbQuestionType.setPadding(new Insets(10, 10, 10, 10));
		vbQuestionType.setSpacing(10);

		ToggleGroup tglQuestions = new ToggleGroup();
		RadioButton rdoOpenQuestion = new RadioButton("Open Question");
		RadioButton rdoCloseQuestion = new RadioButton("Close Question");
		rdoOpenQuestion.setToggleGroup(tglQuestions);
		rdoCloseQuestion.setToggleGroup(tglQuestions);
		vbQuestionType.getChildren().addAll(rdoCloseQuestion, rdoOpenQuestion);

		//// open question
		VBox vbOpenQuestion = new VBox();
		vbOpenQuestion.setPadding(new Insets(10, 10, 10, 10));
		vbOpenQuestion.setSpacing(10);

		// box for question
		HBox hbGetOpenQuestion = new HBox();
		hbGetOpenQuestion.setPadding(new Insets(10, 10, 10, 10));
		hbGetOpenQuestion.setSpacing(10);
		Label lblNewOpenQuestion = new Label("Enter question: ");
		TextField tfNewOpenQuestion = new TextField();
		tfNewOpenQuestion.setPrefSize(500, 10);
		tfNewOpenQuestion.setEditable(true);
		hbGetOpenQuestion.getChildren().addAll(lblNewOpenQuestion, tfNewOpenQuestion);

		// box for answer
		HBox hbGetAnswer = new HBox();
		hbGetAnswer.setPadding(new Insets(10, 10, 10, 10));
		hbGetAnswer.setSpacing(20);
		Label lblAnswer = new Label("Enter answer: ");
		TextField tfAnswer = new TextField();
		tfAnswer.setPrefSize(500, 10);
		tfAnswer.setEditable(true);
		Button bSaveNewOpenQuestion = new Button("save");
		hbGetAnswer.getChildren().addAll(lblAnswer, tfAnswer, bSaveNewOpenQuestion);

		vbOpenQuestion.getChildren().addAll(hbGetOpenQuestion, hbGetAnswer);

		//
		bSaveNewOpenQuestion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				for (ExamUIEventsListener l : allListeners) // add new open question
					l.addOpenQuestion(tfNewOpenQuestion.getText(), tfAnswer.getText());

				tfNewOpenQuestion.setText("");
				tfAnswer.setText("");
				tfStatusBar.setText("The new question saved!");

				for (ExamUIEventsListener l : allListeners) {
					try {
						l.saveToBinaryFile();
					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(null, "Error");
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}

			}
		});

		//

		//// close question
		VBox vbCloseQuestion = new VBox();
		vbCloseQuestion.setPadding(new Insets(10, 10, 10, 10));
		vbCloseQuestion.setSpacing(10);

		HBox hbNewCloseQuestion = new HBox();
		hbNewCloseQuestion.setPadding(new Insets(10, 10, 10, 10));
		hbNewCloseQuestion.setSpacing(10);
		Label lblNewQuestion = new Label("Enter question: ");
		TextField tfNewCloseQuestion = new TextField();
		tfNewCloseQuestion.setPrefSize(500, 10);
		tfNewCloseQuestion.setEditable(true);
		hbNewCloseQuestion.getChildren().addAll(lblNewQuestion, tfNewCloseQuestion);

		GridPane allAnswers = new GridPane();
		allAnswers.setVgap(8);
		allAnswers.setHgap(10);
		allAnswers.setPadding(new Insets(10, 10, 10, 10));

		Label lblNewAnswer = new Label("Enter Answer: ");
		TextField tfNewAnswer = new TextField();
		RadioButton rdoFalse = new RadioButton("FALSE");
		RadioButton rdoTrue = new RadioButton("TRUE");
		ToggleGroup tglAnswers = new ToggleGroup();
		rdoFalse.setToggleGroup(tglAnswers);
		rdoTrue.setToggleGroup(tglAnswers);
		Button bAddAnswer = new Button("add answer");
		Button bSaveCloseQuestion = new Button("save");
		Button bCancel = new Button("cencel");

		allAnswers.add(lblNewAnswer, 1, 2);
		allAnswers.add(tfNewAnswer, 2, 2);
		allAnswers.add(rdoTrue, 3, 2);
		allAnswers.add(rdoFalse, 4, 2);
		allAnswers.add(bAddAnswer, 5, 2);
		allAnswers.add(bSaveCloseQuestion, 9, 7);
		allAnswers.add(bCancel, 8, 7);

		vbCloseQuestion.getChildren().addAll(hbNewCloseQuestion, allAnswers);

		// for answers

		Vector<Answer> answersCloseQuestion = new Vector<Answer>();

		bSaveCloseQuestion.setOnAction(new EventHandler<ActionEvent>() { // sum of answers
			@Override
			public void handle(ActionEvent e) {

				for (ExamUIEventsListener l : allListeners) {

					try {
						l.addCloseQuestion(tfNewCloseQuestion.getText(), answersCloseQuestion);
					} catch (InputMismatchException e1) {
						tfStatusBar.setText("Input must be string!");
						e1.printStackTrace();
					} catch (Exception e1) {
						tfStatusBar.setText(e1.getMessage());
						e1.printStackTrace();

					}
				}

				for (ExamUIEventsListener l : allListeners) {
					try {
						l.saveToBinaryFile();
						tfStatusBar.setText("The new question saved!");
					} catch (ClassNotFoundException e1) {
						tfStatusBar.setText("Error");
						e1.printStackTrace();
					} catch (IOException e1) {
						tfStatusBar.setText("Error");
						e1.printStackTrace();
					} catch (Exception e1) {
						tfStatusBar.setText(e1.getMessage());
						e1.printStackTrace();

					}
				}

				answersCloseQuestion.clear();
				tfNewCloseQuestion.clear();
				tfNewAnswer.clear();
				rdoFalse.setSelected(false);
				rdoTrue.setSelected(false);

			}
		});

		bAddAnswer.setOnAction(new EventHandler<ActionEvent>() { // sum of answers
			@Override
			public void handle(ActionEvent e) {

				answersCloseQuestion.add(new Answer(tfNewAnswer.getText(), rdoTrue.isSelected()));
				tfNewAnswer.clear();
				rdoFalse.setSelected(false);
				rdoTrue.setSelected(false);
				tfStatusBar.setText("Enter a new answer or save the Question");

			}
		});

		bCancel.setOnAction(new EventHandler<ActionEvent>() { // sum of answers
			@Override
			public void handle(ActionEvent e) {
				answersCloseQuestion.clear();
				tfNewCloseQuestion.clear();
				tfNewAnswer.clear();
				rdoFalse.setSelected(false);
				rdoTrue.setSelected(false);

			}
		});

		// button create new question
		newQuestion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				title.setText("CREATE NEW QUESTION");
				borderPane1.setCenter(vbQuestionType);
				tfStatusBar.setText("select a question type");
			}

		});

		rdoOpenQuestion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				VBox vbOpenQuestionBox = new VBox();
				vbOpenQuestionBox.getChildren().addAll(vbQuestionType, vbOpenQuestion);
				borderPane1.setCenter(vbOpenQuestionBox);
			}
		});

		rdoCloseQuestion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				VBox vbCloseQuestion1 = new VBox();

				vbCloseQuestion1.getChildren().addAll(vbQuestionType, vbCloseQuestion);

				borderPane1.setCenter(vbCloseQuestion1);

			}
		});

// (3) edit Question
		VBox vbEditQuestionByNum = new VBox();
		vbEditQuestionByNum.setPadding(new Insets(10, 10, 10, 10));
		vbEditQuestionByNum.setSpacing(10);

		HBox hbEditQuestion1 = new HBox();
		hbEditQuestion1.setPadding(new Insets(10, 10, 10, 10));
		hbEditQuestion1.setSpacing(10);
		Label lblEditQuestion = new Label("Please enter question number: ");
		TextField tfQuestionNumber = new TextField(); // input
		tfQuestionNumber.setPrefSize(30, 15);
		Button bNumOfQuestion = new Button("select");
		hbEditQuestion1.getChildren().addAll(lblEditQuestion, tfQuestionNumber, bNumOfQuestion);

		HBox hbEditQuestion2 = new HBox();
		hbEditQuestion2.setPadding(new Insets(10, 10, 10, 10));
		hbEditQuestion2.setSpacing(10);
		Label lblEditQuestion2 = new Label("Current question: ");

		TextArea tfQuestion2 = new TextArea();
		tfQuestion2.setPrefSize(400, 400);
		tfQuestion2.setEditable(false);
		tfQuestion2.setWrapText(true);

		tfQuestion2.setPrefColumnCount(10);
		tfQuestion2.setPrefColumnCount(20);
		hbEditQuestion2.getChildren().addAll(lblEditQuestion2, tfQuestion2);

		HBox hbEditQuestion3 = new HBox();
		hbEditQuestion3.setPadding(new Insets(10, 10, 10, 10));
		hbEditQuestion3.setSpacing(25);
		Label lblEditQuestion3 = new Label(" New question: ");
		TextField tfQuestion3 = new TextField();
		tfQuestion3.setEditable(true);
		tfQuestion3.setPrefSize(400, 10);

		Button bEditQuestion = new Button("save");
		hbEditQuestion3.getChildren().addAll(lblEditQuestion3, tfQuestion3, bEditQuestion);

		vbEditQuestionByNum.getChildren().addAll(hbEditQuestion1, hbEditQuestion2, hbEditQuestion3);

		bNumOfQuestion.setOnAction(new EventHandler<ActionEvent>() { // select question number
			@Override
			public void handle(ActionEvent action) {
				for (ExamUIEventsListener l : allListeners)
					tfQuestion2.setText(l.getQuestionByNum(Integer.parseInt(tfQuestionNumber.getText())));

				tfStatusBar.setText("Enter new Question");
			}
		});

		bEditQuestion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				for (ExamUIEventsListener l : allListeners)
					l.editQuestion(Integer.parseInt(tfQuestionNumber.getText()), tfQuestion3.getText());

				tfQuestionNumber.setText("");
				tfQuestion2.setText("");
				tfQuestion3.setText("");
				tfStatusBar.setText("The new question saved!");

				for (ExamUIEventsListener l : allListeners) {
					try {
						l.saveToBinaryFile();
					} catch (ClassNotFoundException e) {
						tfStatusBar.setText("Error");
					} catch (IOException e) {
						tfStatusBar.setText("Error");
					}
				}

			}
		});

		// button edit question

		editQuestion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				title.setText("EDIT QUESTION");
				borderPane1.setCenter(vbEditQuestionByNum);
				tfStatusBar.setText("select a question number and edit");

			}

		});

// edit answer
		VBox vbEditAnswerByQuestion = new VBox();
		vbEditAnswerByQuestion.setPadding(new Insets(10, 10, 10, 10));
		vbEditAnswerByQuestion.setSpacing(10);

		HBox hbNumOfQuestion = new HBox();
		hbNumOfQuestion.setPadding(new Insets(10, 10, 10, 10));
		hbNumOfQuestion.setSpacing(10);
		Label lblNumOfQuestion = new Label("Please enter question number: ");
		TextField tfNumOfQuestion = new TextField(); // input
		tfNumOfQuestion.setPrefSize(30, 15);
		Button btNumOfQuestion = new Button("select");
		hbNumOfQuestion.getChildren().addAll(lblNumOfQuestion, tfNumOfQuestion, btNumOfQuestion);

		HBox hbTheQuestion = new HBox();
		hbTheQuestion.setPadding(new Insets(10, 10, 10, 10));
		hbTheQuestion.setSpacing(10);
		Label lblCurrentQuestion = new Label("Current question: ");

		TextArea tfCurrentQuestion = new TextArea();
		tfCurrentQuestion.setPrefSize(400, 400);
		tfCurrentQuestion.setEditable(false);
		tfCurrentQuestion.setWrapText(true);

		tfCurrentQuestion.setPrefColumnCount(10);
		tfCurrentQuestion.setPrefColumnCount(20);
		hbTheQuestion.getChildren().addAll(lblCurrentQuestion, tfCurrentQuestion);
		//

		HBox hbNumOfAnswer = new HBox();
		hbNumOfAnswer.setPadding(new Insets(10, 10, 10, 10));
		hbNumOfAnswer.setSpacing(15);

		Label lblNumOfAnswer = new Label("Answer number: ");
		TextField tfNumOfAnswer = new TextField(); // input
		tfNumOfAnswer.setPrefSize(30, 15);
		hbNumOfAnswer.getChildren().addAll(lblNumOfAnswer, tfNumOfAnswer);
		tfNumOfAnswer.setEditable(false);
		tfNumOfAnswer.setDisable(true);

		HBox hbTheNewAnswer = new HBox();
		hbTheNewAnswer.setPadding(new Insets(10, 10, 10, 10));
		hbTheNewAnswer.setSpacing(35);
		Label lblTheNewAnswer = new Label("New answer: ");
		TextField tfEditAnswer = new TextField();
		tfEditAnswer.setPrefSize(400, 10);
		tfEditAnswer.setPromptText("for american questions, enter answer number");
		tfEditAnswer.setDisable(true);
		tfEditAnswer.setEditable(false); // change
		Button bSaveEditAnswer = new Button("save");
		hbTheNewAnswer.getChildren().addAll(lblTheNewAnswer, tfEditAnswer, bSaveEditAnswer);

		btNumOfQuestion.setOnAction(new EventHandler<ActionEvent>() { // select question number
			@Override
			public void handle(ActionEvent action) {
				for (ExamUIEventsListener l : allListeners) {
					tfCurrentQuestion.setText(l.getQuestionByNum(Integer.parseInt(tfNumOfQuestion.getText())));

					if (l.getQuestion(Integer.parseInt(tfNumOfQuestion.getText())) instanceof CloseQuestion) {
						tfNumOfAnswer.setEditable(true);
						tfNumOfAnswer.setDisable(false);

					} else {
						tfNumOfAnswer.setEditable(false);
					}
				}
				tfEditAnswer.setEditable(true);
				tfEditAnswer.setDisable(false);
				tfStatusBar.setText("Enter new answer");
			}
		});
		//

		vbEditAnswerByQuestion.getChildren().addAll(hbNumOfQuestion, hbTheQuestion, hbNumOfAnswer, hbTheNewAnswer);

		bSaveEditAnswer.setOnAction(new EventHandler<ActionEvent>() { // select question number
			@Override
			public void handle(ActionEvent action) {
				for (ExamUIEventsListener l : allListeners) {
					if (l.getQuestion(Integer.parseInt(tfNumOfQuestion.getText())) instanceof CloseQuestion) {

						l.editCloseQuestionAnswer(Integer.parseInt(tfNumOfQuestion.getText()),
								Integer.parseInt(tfNumOfAnswer.getText()), tfEditAnswer.getText());

					} else {
						l.editOpenQuestionAnswer(Integer.parseInt(tfNumOfQuestion.getText()), tfEditAnswer.getText());

					}
				}
				tfNumOfQuestion.setText("");
				tfNumOfAnswer.setText("");
				tfEditAnswer.setText("");
				tfCurrentQuestion.setText("");
				tfStatusBar.setText("The new answer saved!");

				for (ExamUIEventsListener l : allListeners) {
					try {
						l.saveToBinaryFile();
						tfStatusBar.setText("The new answer saved!");

					} catch (ClassNotFoundException e) {
						tfStatusBar.setText("Error");
					} catch (IOException e) {
						tfStatusBar.setText("Error");
					}
				}

			}
		});
	
// deleteAnswer

		VBox vbDeleteAnswerByQuestion = new VBox();
		vbDeleteAnswerByQuestion.setPadding(new Insets(10, 10, 10, 10));
		vbDeleteAnswerByQuestion.setSpacing(10);

		HBox hbNumOfQuestion2 = new HBox();
		hbNumOfQuestion2.setPadding(new Insets(10, 10, 10, 10));
		hbNumOfQuestion2.setSpacing(10);
		Label lblNumOfQuestion2 = new Label("Please enter question number: ");
		TextField tfNumOfQuestion2 = new TextField(); // input
		tfNumOfQuestion2.setPrefSize(30, 15);
		Button bQuestion = new Button("select");

		hbNumOfQuestion2.getChildren().addAll(lblNumOfQuestion2, tfNumOfQuestion2, bQuestion);

		HBox hbShowQuestion = new HBox();
		hbShowQuestion.setPadding(new Insets(10, 10, 10, 10));
		hbShowQuestion.setSpacing(10);
		TextArea taQuestion = new TextArea();
		taQuestion.setPrefSize(400, 400);
		taQuestion.setEditable(false);
		taQuestion.setWrapText(true);
		Label lblCurrentQuestion2 = new Label("Current question: ");

		hbShowQuestion.getChildren().addAll(lblCurrentQuestion2, taQuestion);

		HBox hbNumAnswer = new HBox();
		hbNumAnswer.setPadding(new Insets(10, 10, 10, 10));
		hbNumAnswer.setSpacing(15);
		Label lblNumAnswer = new Label("Answer number: ");
		TextField tfNumAnswer = new TextField(); // input
		tfNumAnswer.setPrefSize(30, 15);
		tfNumAnswer.setEditable(false);
		tfNumAnswer.setDisable(true);
		Button bDeleteAnswer = new Button("delete");

		hbNumAnswer.getChildren().addAll(lblNumAnswer, tfNumAnswer, bDeleteAnswer);

		vbDeleteAnswerByQuestion.getChildren().addAll(hbNumOfQuestion2, hbShowQuestion, hbNumAnswer);

		bQuestion.setOnAction(new EventHandler<ActionEvent>() { // select question number
			@Override
			public void handle(ActionEvent action) {
				for (ExamUIEventsListener l : allListeners) {
					taQuestion.setText(l.getQuestionByNum(Integer.parseInt(tfNumOfQuestion2.getText())));

					if (l.getQuestion(Integer.parseInt(tfNumOfQuestion2.getText())) instanceof CloseQuestion) {
						tfNumAnswer.setEditable(true);
						tfNumAnswer.setDisable(false);
						tfStatusBar.setText("Enter answer number and press - delete");

					} else {
						tfNumAnswer.setEditable(false);
						tfStatusBar.setText("press delete");
					}
				}
				tfEditAnswer.setEditable(true);
				tfEditAnswer.setDisable(false);

			}
		});

		bDeleteAnswer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				for (ExamUIEventsListener l : allListeners) {
					if (l.getQuestion(Integer.parseInt(tfNumOfQuestion2.getText())) instanceof CloseQuestion) {

						l.deleteCloseQuestionAnswer(Integer.parseInt(tfNumOfQuestion2.getText()),
								Integer.parseInt(tfNumAnswer.getText()), null);

					} else {
						l.deleteOpenQuestionAnswer(Integer.parseInt(tfNumOfQuestion2.getText()), null);

					}
				}

				tfNumOfQuestion2.setText("");
				tfNumAnswer.setText("");
				taQuestion.setText("");
				tfStatusBar.setText("The answer has been deleted!");

				for (ExamUIEventsListener l : allListeners) {
					try {
						l.saveToBinaryFile();
						tfStatusBar.setText("The answer has been deleted!");
					} catch (ClassNotFoundException e) {
						tfStatusBar.setText("Error");
					} catch (IOException e) {
						tfStatusBar.setText("Error");
					}
				}

			}
		});

// copy test

		VBox vbCopyTest = new VBox();
		vbCopyTest.setPadding(new Insets(10, 10, 10, 150));
		vbCopyTest.setSpacing(10);

		HBox hbCopyTest = new HBox();
		hbCopyTest.setPadding(new Insets(10, 10, 10, 10));
		hbCopyTest.setSpacing(10);

		TextArea tCopyExam = new TextArea();

		tCopyExam.setPrefSize(350, 600);
		tCopyExam.setEditable(false);
		tCopyExam.setWrapText(true);
		tCopyExam.setText("Test stored in memory:\n\n" + ExamManager.printTests());

		HBox hbNumOfExam = new HBox();
		hbNumOfExam.setPadding(new Insets(10, 10, 10, 10));
		hbNumOfExam.setSpacing(15);
		Label lbNumOfExam = new Label("Exam Number: ");
		TextField tfNumOfExam = new TextField(); // input
		tfNumOfExam.setPrefSize(30, 15);
		tfNumOfExam.setEditable(true);
		Button bCopy = new Button("copy ");

		hbNumOfExam.getChildren().addAll(lbNumOfExam, tfNumOfExam, bCopy);
		vbCopyTest.getChildren().addAll(tCopyExam);
		hbCopyTest.getChildren().addAll(hbNumOfExam, vbCopyTest);

		bCopy.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				for (ExamUIEventsListener l : allListeners)
					try {
						l.copyExam(Integer.parseInt(tfNumOfExam.getText()));
						tfStatusBar.setText("The file has been copied!");
						tfNumOfExam.setText("");
					} catch (NumberFormatException | CloneNotSupportedException | IOException e1) {
						tfNumOfExam.setText("");
						tfStatusBar.setText("Error");
						e1.printStackTrace();
					}
			}

		});

// copy exam
		copyTest.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				title.setText("COPY AN EXISTING EXAM");
				borderPane1.setCenter(hbCopyTest);
				tfStatusBar.setText("select exam number for copying");

			}

		});

// create automatic test

		VBox vbAutomaticExam = new VBox();
		vbAutomaticExam.setPadding(new Insets(10, 500, 10, 10));
		vbAutomaticExam.setSpacing(10);

		HBox hbNumOfQuestions = new HBox();
		hbNumOfQuestions.setPadding(new Insets(0, 10, 10, 10));
		hbNumOfQuestions.setSpacing(10);
		Label lblNumOfQuestions = new Label("Number of questions");
		TextField tfNumOfQuestions = new TextField();
		tfNumOfQuestions.setPrefSize(30, 15);
		Button bNumOfQuestions = new Button("create");
		hbNumOfQuestions.getChildren().addAll(lblNumOfQuestions, tfNumOfQuestions, bNumOfQuestions);
	
		vbAutomaticExam.getChildren().addAll(hbNumOfQuestions);
		
		bNumOfQuestions.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				for (ExamUIEventsListener l : allListeners)
					try {
						l.createAutomaticExam(Integer.parseInt(tfNumOfQuestions.getText()));
						tfStatusBar.setText("The test is created!");
					} catch (FileNotFoundException e) {
						tfStatusBar.setText("Error");
					} catch (Exception e) {
						tfStatusBar.setText("Error");

					}

				tfNumOfQuestions.setText("");

			}
		});

	

// automatic exam - main button
		createAutomaticTest.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				title.setText("CREATE AN AUTOMATIC EXAM");
				borderPane1.setCenter(vbAutomaticExam);
				tfStatusBar.setText("Enter the number of questions you want in the test. Then click create");

			}

		});

// delete answer - main button
		deleteAnswer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				title.setText("DELETE ANSWER");
				borderPane1.setCenter(vbDeleteAnswerByQuestion);
				tfStatusBar.setText("select a question number and delete your answer");

			}

		});
// edit answer - main button
		editAnswer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				title.setText("EDIT ANSWER");
				borderPane1.setCenter(vbEditAnswerByQuestion);
				tfStatusBar.setText("select a question number and edit your answer");

			}

		});

// exit - main button
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				title.setText("TEST MANAGMENT SYSTEM");

				borderPane1.setCenter(hbMenu);
				tfStatusBar.setText("No messages...");

			}

		});

// crate test

		GridPane gpCreateTest = new GridPane();
		gpCreateTest.setVgap(8);
		gpCreateTest.setHgap(10);
		gpCreateTest.setPadding(new Insets(10, 10, 10, 10));

		VBox vbDataCreatTest = new VBox();
		vbDataCreatTest.setPadding(new Insets(10, 10, 50, 50));
		vbDataCreatTest.setSpacing(10);
		vbDataCreatTest.setPrefHeight(100);

		TextArea tData = new TextArea();
		tData.setPrefSize(450, 400);
		tData.setEditable(false);
		tData.setWrapText(true);
		TextArea tNewTestData = new TextArea();
		tNewTestData.setPrefSize(450, 400);
		tNewTestData.setEditable(false);
		tNewTestData.setWrapText(true);
		vbDataCreatTest.getChildren().addAll(tData, tNewTestData);

		HBox hbCreateTest = new HBox();
		hbCreateTest.setPadding(new Insets(10, 10, 10, 10));
		hbCreateTest.setSpacing(20);
		hbCreateTest.getChildren().addAll(gpCreateTest, vbDataCreatTest);
		Label lbNumOfQuestion = new Label("Question Number:");
		TextField tfNumOfQuestion1 = new TextField(); // input
		tfNumOfQuestion1.setPrefSize(30, 15);

		Button bSelectQuestion = new Button("select");

		GridPane.setConstraints(lbNumOfQuestion, 0, 1);
		GridPane.setConstraints(tfNumOfQuestion1, 1, 1);
		GridPane.setConstraints(bSelectQuestion, 2, 1);
		gpCreateTest.getChildren().addAll(lbNumOfQuestion, tfNumOfQuestion1, bSelectQuestion);

		Label lbNumOfAnswer1 = new Label("Answer Number:");
		TextField tfNumOfAnswer1 = new TextField(); // input
		tfNumOfAnswer1.setPrefSize(30, 15);
		tfNumOfAnswer1.setEditable(true);
		tfNumOfAnswer1.setDisable(true);

		Button bSelectAnswer = new Button("add");
		bSelectAnswer.setDisable(true);

		Button bAddQuestion = new Button("add question");
		Button bSaveTest = new Button("save exam");
		bAddQuestion.setDisable(true);
		bSaveTest.setDisable(true);

		GridPane.setConstraints(lbNumOfAnswer1, 0, 3);
		GridPane.setConstraints(tfNumOfAnswer1, 1, 3);
		GridPane.setConstraints(bSelectAnswer, 2, 3);
		GridPane.setConstraints(bAddQuestion, 0, 5);
		GridPane.setConstraints(bSaveTest, 0, 6);

		gpCreateTest.getChildren().addAll(lbNumOfAnswer1, tfNumOfAnswer1, bSelectAnswer, bAddQuestion, bSaveTest);

		createTest.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				title.setText("CREATE A TEST");
				for (ExamUIEventsListener l : allListeners)
					try {
						tData.setText("DataBase:\n\n" + l.printDataToUI());
					} catch (ClassNotFoundException e1) {
						tfStatusBar.setText("No Data");
						e1.printStackTrace();
					} catch (IOException e1) {
						tfStatusBar.setText("Error");
						e1.printStackTrace();
					}

				borderPane1.setCenter(hbCreateTest);

				tfStatusBar.setText("Enter question number");

			}

		});

		// select question
		bSelectQuestion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				for (ExamUIEventsListener l : allListeners) {
					tNewTestData.setText(l.getQuestionByNum(Integer.parseInt(tfNumOfQuestion1.getText())));
					Question temp = l.getQuestion(Integer.parseInt(tfNumOfQuestion1.getText()));

					if (temp instanceof CloseQuestion) {

						bSelectAnswer.setDisable(false);
						tfNumOfAnswer1.setEditable(true);
						tfNumOfAnswer1.setDisable(false);
						bAddQuestion.setDisable(false);
						tfStatusBar.setText("Choose your answers");

					}
					if (temp instanceof OpenQuestion) {

						bSelectAnswer.setDisable(true);
						tfNumOfAnswer1.setDisable(true);
						bAddQuestion.setDisable(false);

					}
					tfStatusBar.setText("To add to the test, click - add question -");
					bSaveTest.setDisable(false);
				}

			}

		});
		Vector<Answer> allAnswers1 = new Vector<Answer>();
		Vector<Integer> value = new Vector<Integer>();

		bSelectAnswer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				value.add(Integer.parseInt(tfNumOfAnswer1.getText()));

				tfNumOfAnswer1.clear();
				tfStatusBar.setText("Choose your answers");
			}

		});

		Vector<Question> allQuestions = new Vector<Question>();

		bAddQuestion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				for (ExamUIEventsListener l : allListeners) {

					Question temp = l.getQuestion(Integer.parseInt(tfNumOfQuestion1.getText()));

					if (temp instanceof CloseQuestion) {
						CloseQuestion t = new CloseQuestion(temp.getText());

						Iterator<Integer> h = value.iterator();
						while (h.hasNext()) {
							((CloseQuestion) t).addAnswer(((CloseQuestion) temp).getAnswers().elementAt(h.next()));

						}
						allQuestions.add(t);
						value.clear();

						tfNumOfQuestion1.clear();
						bSelectAnswer.setDisable(false);
						tfNumOfAnswer1.setEditable(true);
						tfNumOfAnswer1.setDisable(false);
						bAddQuestion.setDisable(false);
						tfStatusBar.setText("Choose your answers, then click - add question - ");

					}
					if (temp instanceof OpenQuestion) {
						// add open question to vector

						OpenQuestion t = new OpenQuestion(temp.getText(), ((OpenQuestion) temp).getAnswer());

						allQuestions.add(t);
						bSelectAnswer.setDisable(true);
						tfNumOfAnswer1.setDisable(true);
						bAddQuestion.setDisable(false);
						tfNumOfQuestion1.clear();
						tfStatusBar.setText("To add to the test, click - add question -");

					}
					bSaveTest.setDisable(false);

				}
				tfNumOfQuestion1.clear();
				bAddQuestion.setDisable(true);
				allAnswers1.clear();

				// addCloseQuestion
				bSelectQuestion.setDisable(false);
				tfNumOfQuestion1.setDisable(false);

			}

		});

		bSaveTest.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				for (ExamUIEventsListener l : allListeners) {

					l.createTest(allQuestions);
					allQuestions.clear();

				}

				bSelectQuestion.setDisable(false);
				tfNumOfQuestion1.setDisable(false);

				tfStatusBar.setText("The test is created!");
			}

		});

		theStage.setScene(new Scene(borderPane1, 1000, 500));
		theStage.show();
	}

	@Override
	public void registerListener(ExamUIEventsListener newListener) {
		allListeners.add(newListener);
	}

	@Override
	public String printDataToUI() throws ClassNotFoundException, IOException {
		return "bfgbgfb";
	}

	@Override
	public void getAutomaticExam(ExamManager automaticExam) {
		
	}

	@Override
	public String getStringQuestionByNum(Question question) {
		return question.getText();
		// TODO Auto-generated method stub

	}

	@Override
	public void editQuestion(Question theNewQuestion) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editCloseQuestionAnswer(Question theNewCloseQuestion) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editOpenQuestionAnswer(Question theNewCloseQuestion) {
		// TODO Auto-generated method stub

	}

	@Override
	public Question getTypeQuestion(Question theQuestion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addOpenQuestion(OpenQuestion openQuestion) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteOpenQuestionAnswer(OpenQuestion theNewOpenQuestion) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCloseQuestionAnswer(CloseQuestion theNewCloseQuestion) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCloseQuestion(CloseQuestion closeQuestion) {
		// TODO Auto-generated method stub

	}


}
