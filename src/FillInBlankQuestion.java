public class FillInBlankQuestion extends Question {
    private String correctAnswer;

    public FillInBlankQuestion(String questionText, String correctAnswer, int points) {
        super(questionText, points);
        this.correctAnswer = correctAnswer;
    }

    public boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase(correctAnswer);
    }

    public void displayQuestion() {
        String questionText;
        System.out.println(questionText + " _______");
    }
}