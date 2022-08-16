package id32287623232;

import java.io.Serializable;

public class Answer implements Serializable {
	private String text;
	private boolean isCorrect;

	public Answer(String text, boolean isCorrect) {
		this.text = text;
		this.isCorrect = isCorrect;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public boolean equals(Answer other) {
		return this.text.equals(other.text);
	}

	public String showOnlyAnswers() {
		if (this.text == null) {
			return ""; // if the answer is deleted
		}
		return "\t_____Answer: " + this.text + "\n";
	}

	public String toString() {
		if (this.text == null) {
			return ""; // if the answer is deleted
		}
		return "\tAnswer: " + this.text + " - Is Correct?: " + this.isCorrect + "\n";
	}

}
