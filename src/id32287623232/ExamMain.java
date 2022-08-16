package id32287623232;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;


import javafx.application.Application;
import javafx.stage.Stage;

public class ExamMain extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	


	public void start(Stage primaryStage) throws Exception {
		ExamManager theModel = new ExamManager();
		File f = new File("test.dat");
		
		ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(f));
		theModel = (ExamManager) inFile.readObject();
		inFile.close();
	
		AbstractExamView theView1 = new ExamFX(new Stage());
		ExamController controller1 = new ExamController(theModel, theView1);
		
}
	


}
