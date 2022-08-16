package id32287623232;

import java.io.File;
import java.io.IOException;
import java.util.Vector;


public interface ExamEventsListener {

	void getDataBase(ExamManager t) throws ClassNotFoundException, IOException; //(database)
	void getAutomaticExam(ExamManager automaticExam); //(automatic exam)
	void getStringQuestionByNum(Question questions);
	void editQuestion(Question theNewQuestion);
	void editCloseQuestionAnswer(Question theNewCloseQuestion);
	void editOpenQuestionAnswer(Question theNewCloseQuestion);
	Question getTypeQuestion(Question theQuestion);
	void addOpenQuestion(OpenQuestion openQuestion);
	void deleteOpenQuestionAnswer(OpenQuestion theNewOpenQuestion);
	void deleteCloseQuestionAnswer(CloseQuestion theNewCloseQuestion);
	void addCloseQuestion(CloseQuestion closeQuestion);

}
