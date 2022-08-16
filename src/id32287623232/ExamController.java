package id32287623232;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class ExamController implements ExamEventsListener, ExamUIEventsListener {

	private ExamManager examModel;
	private AbstractExamView examView;

	public ExamController(ExamManager model, AbstractExamView view) {
		examModel = model;
		examView = view;
		// ??
		examModel.registerListener(this);
		examView.registerListener(this);
	}

	@Override
	public void getDataBase(ExamManager t) throws ClassNotFoundException, IOException {
		examModel = t;

	}

	@Override
	public String printDataToUI() throws ClassNotFoundException, IOException {

		return examModel.readData(examModel).toString();

	}

	@Override
	public void createAutomaticExam(int numOfQuestions) throws FileNotFoundException {
		ExamManager automaticExam = new ExamManager(numOfQuestions);
		examModel.createAutomaticExam(automaticExam, numOfQuestions);
	}

	@Override
	public void getAutomaticExam(ExamManager automaticExam) {
		examView.getAutomaticExam(automaticExam);
	}

	@Override
	public String getQuestionByNum(int numOfQuestion) {

		return examModel.getQuestion(numOfQuestion).toString();

	}

	@Override
	public void getStringQuestionByNum(Question question) {
		examView.getStringQuestionByNum(question);

	}

	@Override
	public void editQuestion(int questionNumber, String newQuestion) {
		
		examModel.editQuestion(questionNumber, newQuestion);

	}

	@Override
	public void editQuestion(Question theNewQuestion) {
		examView.editQuestion(theNewQuestion);

	}

	@Override
	public void saveToBinaryFile() throws ClassNotFoundException, IOException {
		File f = new File("test.dat");
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(f));
		outFile.writeObject(examModel);
		outFile.close();

	}

	@Override
	public void editCloseQuestionAnswer(int numOfQuestion, int numOfAnswer, String theNewAnswer) {
		examModel.editAnwser(numOfQuestion, numOfAnswer, theNewAnswer);

	}

	@Override
	public void editCloseQuestionAnswer(Question theNewCloseQuestion) {
		examView.editCloseQuestionAnswer(theNewCloseQuestion);

	}

	@Override
	public void editOpenQuestionAnswer(int numOfQuestion, String theNewAnswer) {
		examModel.editAnwser(numOfQuestion, theNewAnswer);

	}

	@Override
	public void editOpenQuestionAnswer(Question theNewCloseQuestion) {
		examView.editOpenQuestionAnswer(theNewCloseQuestion);

	}

	@Override
	public Question getQuestion(int numOfQuestion) {
		return examModel.getTypeQuestion(numOfQuestion);
	}

	@Override
	public Question getTypeQuestion(Question theQuestion) {
		return examView.getTypeQuestion(theQuestion);
	}

	@Override
	public void addOpenQuestion(String question, String Answer) {
		OpenQuestion temp = new OpenQuestion(question, Answer);
		examModel.addQuestion(temp);

	}

	@Override
	public void addOpenQuestion(OpenQuestion openQuestion) {
		examView.addOpenQuestion(openQuestion);

	}

	@Override
	public void deleteOpenQuestionAnswer(int questionNumber, String nullAnswer) {
		examModel.deleteAnwser(questionNumber, nullAnswer);
	}

	@Override
	public void deleteOpenQuestionAnswer(OpenQuestion theNewOpenQuestion) {
		examView.deleteOpenQuestionAnswer(theNewOpenQuestion);

	}

	@Override
	public void deleteCloseQuestionAnswer(int questionNumber, int answerNumber, String nullAnswer) {
		examModel.deleteAnwser(questionNumber, answerNumber, nullAnswer);

	}

	@Override
	public void deleteCloseQuestionAnswer(CloseQuestion theNewCloseQuestion) {
		examView.deleteCloseQuestionAnswer(theNewCloseQuestion);

	}

	@Override
	public void copyExam(int numOfExam) throws CloneNotSupportedException, IOException {
		FileInputStream fi = new FileInputStream(examModel.getFileByNum(numOfExam));
		FileOutputStream fo = new FileOutputStream("CopyExam");

		fi.transferTo(fo);

		System.out.println("\nThe file has been copied");

		fi.close();
		fo.close();

	}

	@Override
	public void createTest(Vector<Question> allQuestions) {
		ExamManager createTest = new ExamManager(allQuestions.size());

		createTest.setCourrentNumOfQuestions(allQuestions.size());
		createTest.setAmount(allQuestions.size());
		createTest.setQuestions(allQuestions);
		createTest.insertSort1();

		try {
			ExamManager.save(createTest);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addCloseQuestion(String question, Vector<Answer> allAnswers) {
		CloseQuestion temp = new CloseQuestion(question);
		temp.setAmountOfAnswer(allAnswers.size());
		temp.setAnswers(allAnswers);

		examModel.addQuestion(temp, allAnswers);
	}

	@Override
	public void addCloseQuestion(CloseQuestion closeQuestion) {
		examView.addCloseQuestion(closeQuestion);

	}
	

	

	

}
