package tdm4ex3TCP;

public class Question {
	String question;

	Question(String question){
		this.question = question;
	}
	Question(){

	}
	public void fill(String str){
		question = str;
	}
	public boolean valid(){	 
		String findStr = "?";
		return question.contains(findStr);	 
	}

}
