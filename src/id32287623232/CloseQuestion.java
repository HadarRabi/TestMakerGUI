package id32287623232;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

public class CloseQuestion extends Question implements Serializable {

	private Vector<Answer> answers;
	private int amountOfAnswer;

	public CloseQuestion(String text) {
		super(text);
		answers = new Vector<Answer>();
		this.amountOfAnswer = 0;
	}

	public Vector<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Vector<Answer> a) {
		this.answers = a;
	}

	public int getAmountOfAnswer() {
		return amountOfAnswer;
	}

	public void setAmountOfAnswer(int amountOfAnswer) {
		this.amountOfAnswer = amountOfAnswer;
	}

	public void addAnswer(Answer a) {

		answers.add(a);

	}

	public String addTwoAnswers(Vector<Answer> a) {

		boolean noRightAnswer = true;
		boolean trueAnswer = false;
		boolean moreThenOne = false;

		for (int i = 0; i < a.size(); i++) {

			if (a.elementAt(i).isCorrect()) {
				if (trueAnswer == true) {
					moreThenOne = true;

				} else {
					noRightAnswer = false;
					trueAnswer = true;
				}
			}
		}

		return "\tAnswer: No right answer - Is Correct?: " + noRightAnswer + "\n"
				+ "\tAnswer: More than one right answer - Is Correct?: " + moreThenOne + "\n";

	}

	public String showCorrectAnswer() {

		String desc = "\n" + String.valueOf(this.num) + ". Question: " + this.text + "\n";

		for (int i = 0; i < answers.size(); i++) {
			if (this.answers.elementAt(i).isCorrect())
				desc += this.answers.elementAt(i);
		}
		return desc;

	}

	public String answers() {

		String desc = "\n" + String.valueOf(this.num) + ". Question: " + this.text + "\n";

		for (int i = 0; i < answers.size(); i++) {
			desc += this.answers.elementAt(i).showOnlyAnswers();
		}
		desc += "\t_____Answer: No right answer\n" + "\t_____Answer: More than one right answer\n";

		return desc;

	}

	public boolean equals(CloseQuestion other) {
		return this.text.equals(other.text);
	}

	public String toString() {

		String desc = "\n" + String.valueOf(this.num) + ". Question: " + this.text + "\n";

		for (int i = 0; i < answers.size(); i++) {
			desc += this.answers.elementAt(i);
		}
		desc += addTwoAnswers(answers);
		return desc;
	}
}
