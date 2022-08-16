package id32287623232;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;

import javafx.scene.control.CheckBox;

public abstract class Question implements Serializable {
	protected int num;
	protected String text;
	private static int amount = 0;

	public Question(String text) {
		this.num = ++amount;
		this.text = text;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}



	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean equals(Question other) {
		return this.text.equals(other.text);
	}

	public int amountOfChar() {

		if (this instanceof CloseQuestion) {
			Vector<Answer> a = ((CloseQuestion) this).getAnswers();
			int amount = 0;
			for (int i = 0; i < a.size(); i++) {
				amount += a.elementAt(i).getText().length();
			}
			return amount;
		}
		if(((OpenQuestion) this).getAnswer() == null ){
			return 4;
		}
		return ((OpenQuestion) this).getAnswer().length();

	}


	public int compareTo(Question other) {
		return this.text.compareTo(other.text);
	}

}
