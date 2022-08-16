package id32287623232;

import java.io.File;
import java.io.IOException;

public interface AbstractExamView {

	
		void registerListener(ExamUIEventsListener listener);
		String printDataToUI() throws ClassNotFoundException, IOException;
		void getAutomaticExam(ExamManager automaticExam);
		String getStringQuestionByNum(Question question);
		void editQuestion(Question theNewQuestion);
		void editCloseQuestionAnswer(Question theNewCloseQuestion);
		void editOpenQuestionAnswer(Question theNewCloseQuestion);
		Question getTypeQuestion(Question theQuestion);
		void addOpenQuestion(OpenQuestion openQuestion);
		void deleteOpenQuestionAnswer(OpenQuestion theNewOpenQuestion);
		void deleteCloseQuestionAnswer(CloseQuestion theNewCloseQuestion);
		public void addCloseQuestion(CloseQuestion closeQuestion);






	
}
