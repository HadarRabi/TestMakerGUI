package id32287623232;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

public interface ExamUIEventsListener {

	String printDataToUI() throws ClassNotFoundException, IOException;
	void createAutomaticExam(int numOfQuestions) throws FileNotFoundException;
	String getQuestionByNum(int numOfQuestion);
	void editQuestion(int questionNumber, String newQuestion);
	void saveToBinaryFile() throws ClassNotFoundException, IOException;;
	void editCloseQuestionAnswer(int numOfQuestion, int numOfAnswer, String theNewAnswer);
	void editOpenQuestionAnswer(int numOfQuestion, String theNewAnswer);
	Question getQuestion(int numOfQuestion);
	void addOpenQuestion(String question, String Answer);
	void deleteOpenQuestionAnswer(int questionNumber, String nullAnswer);
	void deleteCloseQuestionAnswer(int questionNumber,int answerNumber ,String nullAnswer);
	void copyExam(int numOfExam) throws CloneNotSupportedException, FileNotFoundException, IOException;
	void createTest(Vector <Question> allQuestions );
	void addCloseQuestion(String question, Vector <Answer>  allAnswers);



}
