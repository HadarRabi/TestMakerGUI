package id32287623232;

import java.io.Serializable;

import javafx.scene.control.CheckBox;

public class OpenQuestion extends Question implements Serializable {
	private String answer;


	public OpenQuestion(String text, String answer) {
		super(text);
		this.answer = answer;

	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}


	
	public boolean equals(OpenQuestion other) {
		return this.text.equals(other.text);
	}

	public String showOnlyQuestion() {
		String desc = "\n" + String.valueOf(this.num) + ". Question: " + this.text + "\n\t______________________\n";

		return desc;
	}

	public String toString() {
		String desc = "\n" + String.valueOf(this.num) + ". Question: " + this.text + "\n";

		desc += "\t Answer: " + this.answer;

		return desc;
	}

}
