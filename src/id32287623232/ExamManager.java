package id32287623232;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

public class ExamManager implements Comparator<Question>, Serializable, Cloneable {

	private int courrentNumOfQuestions;
	private Vector<ExamEventsListener> listeners;
	private Vector<Question> questions;
	private int amount;

	public ExamManager() {
		// aksldkljasdas
		this.questions = new Vector<Question>();
		this.listeners = new Vector<ExamEventsListener>();

		this.amount = 0;

	}

	public ExamManager(int numOfQuestions) {
		this.courrentNumOfQuestions = numOfQuestions;
		this.questions = new Vector<Question>(numOfQuestions);
		this.listeners = new Vector<ExamEventsListener>();

		this.amount = 0;
	}

	public void registerListener(ExamEventsListener listener) {
		listeners.add(listener);
	}

	public ExamManager readData(ExamManager t) throws IOException, ClassNotFoundException {
		File f = new File("test.dat");

		ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(f));
		t = (ExamManager) inFile.readObject();
		inFile.close();

		for (ExamEventsListener l : listeners)
			l.getDataBase(t);
		return t;

	}

	// save option
	public void writeData(ExamManager t) throws IOException, ClassNotFoundException {

		File f = new File("test.dat");
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(f));
		outFile.writeObject(t);
		outFile.close();

	}

	public Answer getAnswer(CloseQuestion closeQuestion, int numOfAnswer) {

		return closeQuestion.getAnswers().elementAt(numOfAnswer);
	}

	public int getCourrentNumOfQuestions() {
		return courrentNumOfQuestions;
	}

	public void setCourrentNumOfQuestions(int courrentNumOfQuestions) {
		this.courrentNumOfQuestions = courrentNumOfQuestions;
	}

	public Vector<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Vector<Question> questions) {
		this.questions = questions;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void editQuestion(int qNum, String question) // 1.4
	{
		Question temp = (this.questions.elementAt(qNum - 1));
		temp.setText(question);

		for (ExamEventsListener l : listeners)
			l.editQuestion(temp);

	}

	public void editAnwser(int qNum, int aNum, String answer) // 1.4
	{

		CloseQuestion temp = (CloseQuestion) (this.questions.elementAt(qNum - 1));
		temp.getAnswers().elementAt(aNum - 1).setText(answer);

		for (ExamEventsListener l : listeners)
			l.editCloseQuestionAnswer(temp);

	}

	public void editAnwser(int qNum, String answer) // 1.4 +1.5
	{

		OpenQuestion temp = (OpenQuestion) (this.questions.elementAt(qNum - 1));
		temp.setAnswer(answer);

		for (ExamEventsListener l : listeners)
			l.editOpenQuestionAnswer(temp);

	}

	public void deleteAnwser(int qNum, String answer) // 1.4 +1.5
	{

		OpenQuestion temp = (OpenQuestion) (this.questions.elementAt(qNum - 1));
		temp.setAnswer(answer);

		for (ExamEventsListener l : listeners)
			l.deleteOpenQuestionAnswer(temp);

	}

	public void deleteAnwser(int qNum, int aNum, String answer) // 1.4 +1.5
	{

		CloseQuestion temp = (CloseQuestion) (this.questions.elementAt(qNum - 1));

		temp.getAnswers().elementAt(aNum - 1).setText(answer);
		temp.getAnswers().elementAt(aNum - 1).setCorrect(false);

		for (ExamEventsListener l : listeners)
			l.deleteCloseQuestionAnswer(temp);

	}

	public Question getTypeQuestion(int qNum) { // 1.4 + 1.5
		for (ExamEventsListener l : listeners)
			l.getTypeQuestion(this.questions.elementAt(qNum - 1));
		return (this.questions.elementAt(qNum - 1));
	}

	public Question getQuestion(int qNum) { // 1.4 + 1.5

		for (ExamEventsListener l : listeners)

			l.getStringQuestionByNum(this.questions.elementAt(qNum - 1));

		return (this.questions.elementAt(qNum - 1));

	}

	public void addQuestion(CloseQuestion q, Vector<Answer> a) // 1.2
	{

		q.setAmountOfAnswer(a.size());
		q.setAnswers(a);
		this.questions.add(q);
		this.amount++;

		for (ExamEventsListener l : listeners)
			l.addCloseQuestion(q);

	}

	public void addQuestion(OpenQuestion q) {

		this.questions.add(q);
		this.amount++;

		for (ExamEventsListener l : listeners)
			l.addOpenQuestion(q);

	}

	public Vector<Answer> randomAnswers(int numOfQ) { // 1.7
		int sumOfAnswers = 4;
		CloseQuestion temp = (CloseQuestion) questions.elementAt(numOfQ - 1);

		Vector<Answer> randomAnswers = new Vector<Answer>();
		for (int i = 0; i < sumOfAnswers; i++) {
			int randomAnswer = (int) (Math.random() * (temp.getAnswers().size()) + 1);
			if (randomNumbersAreNotTheSame(randomAnswers, numOfQ, randomAnswer)) {

				boolean existsTrueQuestion = false;

				if (existsTrueQuestion == false) {
					randomAnswers.add(temp.getAnswers().elementAt(randomAnswer - 1));

					if (randomAnswers.elementAt(i).isCorrect()) {
						existsTrueQuestion = true;

					}
				} else {
					i--;
				}
			}

			else {
				i--;
			}
		}
		return randomAnswers;
	}

	public void randomQuestions(ExamManager t, int sumOfQuestions) { // 1.7

		// random question:
		for (int i = 0; i < sumOfQuestions; i++) {
			int randomQuestion = (int) (Math.random() * this.amount) + 1;

			if (randomNumbersAreNotTheSame(t, randomQuestion)) {
				Question temp = this.getQuestion(randomQuestion);
				// close question:
				if (temp instanceof CloseQuestion) {
					
					if (((CloseQuestion)temp).getAmountOfAnswer() < 4) {
						sumOfQuestions++;
					}
					else {
					Vector<Answer> ans = this.randomAnswers(randomQuestion);
					t.addQuestion((CloseQuestion) temp, ans);
					}
				}
				
				// open question:
				else {
					t.addQuestion((OpenQuestion) temp);
				}
			} else {
				sumOfQuestions++;
			}
		}

	}

	public boolean randomNumbersAreNotTheSame(ExamManager t, int random) {

		for (int i = 0; i < t.getAmount(); i++) {
			if ((questions.elementAt(random - 1).equals(t.getQuestions().elementAt(i)))) {
				return false;
			}
		}
		return true;
	}

	public boolean randomNumbersAreNotTheSame(Vector<Answer> ans, int numOfQ, int random) {

		CloseQuestion temp = (CloseQuestion) this.getQuestion(numOfQ);

		for (int i = 0; i < ans.size(); i++) {
			if (ans.elementAt(i) == null) {
				return true;
			}
			if (temp.getAnswers().elementAt(random - 1).equals(ans.elementAt(i))) {
				return false;
			}
		}
		return true;
	}

	public void insertSort1() {

		Set<Question> sortedQuestions = new TreeSet<Question>(new ExamManager());
		for (int i = 0; i < this.questions.size(); i++) {
			sortedQuestions.add(this.questions.elementAt(i));
		}

		// create a set of questions from the sorted collection
		Iterator<Question> itr = sortedQuestions.iterator();

		Vector<Question> sortedExam = new Vector<Question>();

		for (int i = 0; i < this.questions.size(); i++) {
			sortedExam.add(itr.next());
			sortedExam.elementAt(i).setNum(i + 1); // serial number of Question
		}

		this.setQuestions(sortedExam);
	}

	public void createAutomaticExam(ExamManager autoTest, int sumOfQuestions) throws FileNotFoundException {
		// autoTest.insertSort1();
		
		randomQuestions(autoTest, sumOfQuestions);
		autoTest.insertSort1();
		save(autoTest);
		for (ExamEventsListener l : listeners)
			l.getAutomaticExam(autoTest);

	}

	public static void save(ExamManager t) throws FileNotFoundException {

		LocalDate date2 = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("_yyyy_MM_dd");

		File exam = new File("exam" + date2.format(dtf));
		File solution = new File("solution" + date2.format(dtf));

		PrintWriter pw1 = new PrintWriter(exam);
		PrintWriter pw2 = new PrintWriter(solution);

		Vector<Question> arr = t.getQuestions();

		for (int i = 0; i < arr.size(); i++) {

			// save exam to file
			if (arr.elementAt(i) instanceof OpenQuestion) {
				OpenQuestion temp = (OpenQuestion) arr.elementAt(i);
				pw1.print(temp.showOnlyQuestion());

			} else {
				CloseQuestion temp = (CloseQuestion) arr.elementAt(i);
				pw1.print(temp.answers());
			}

			// save solution to file
			if (arr.elementAt(i) instanceof CloseQuestion) {
				CloseQuestion temp = (CloseQuestion) arr.elementAt(i);
				pw2.print(temp.showCorrectAnswer());

			} else {
				pw2.print(arr.elementAt(i).toString());
			}
		}
		pw1.close();
		pw2.close();

	}

	public ExamManager clone() throws CloneNotSupportedException {

		return (ExamManager) super.clone();

	}

	public File getFileByNum(int num) throws CloneNotSupportedException {
		File f = new File("test");
		File g = new File(f.getAbsoluteFile().getParent());
		int j = 1;

		File[] files = g.listFiles();
		for (int i = 0; i < files.length; i++) {

			if (files[i].getName().contains("exam")) {
				if (j == num) {
					return files[i];

				}
				j++;
			}

		}
		return null;
	}

	public static String printTests() throws IOException {

		File f = new File("test");
		File g = new File(f.getAbsoluteFile().getParent());
		int j = 1;
		StringBuffer print = new StringBuffer("");

		File[] files = g.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().contains("exam")) {

				print.append("\t" + j + ") " + files[i].getName() + "\n");
				j++;
			}
		}
		return print.toString();
	}

	// compare length of answers by question. (part 2.3)

	@Override
	public int compare(Question o1, Question o2) {
		if (o1.amountOfChar() > o2.amountOfChar()) {
			return 1;
		} else if (o1.amountOfChar() < o2.amountOfChar()) {
			return -1;
		} else
			return 1; // if the length is equal, put together
	}

	public String toString() {
		StringBuffer print = new StringBuffer("");

		for (int i = 0; i < this.amount; i++) {
			this.questions.elementAt(i).setNum(i + 1);
			print.append((getQuestions().elementAt(i).toString()));
			print.append("\n______________\n");

		}

		return print.toString();

	}

}
