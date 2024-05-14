public class QuestionTF implements Question {
    private String question;
    private boolean answer;
    private int points;

    public QuestionTF(String question, Boolean answer, int points) {
        this.question = question;
        this.answer = answer;
        this.points = points;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public boolean isCorrect(String answer) {
    	if (!answer.equalsIgnoreCase("true") && !answer.equalsIgnoreCase("false"))
    		return false;
    	else if (answer.equalsIgnoreCase("true"))
    		return true == this.answer;
        return false == this.answer;
    }

    @Override
    public String getCorrectAnswer() {
        return Boolean.toString(answer);
    }

    @Override
    public int getPoints() {
        return points;
    }
    
    @Override
    public String display() {
    	return "Points: " + this.points + "\nTrue/False: " + question;
    }
}
