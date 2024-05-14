public class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    public TrueFalseQuestion(String questionText, boolean correctAnswer, int points) {
        super(questionText, points);
        this.correctAnswer = correctAnswer;
    }

    public boolean checkAnswer(String answer) {
        return Boolean.parseBoolean(answer) == correctAnswer;
    }

    public void displayQuestion() {
        String questionText;
        System.out.println(questionText + " (true/false)");
    }
}