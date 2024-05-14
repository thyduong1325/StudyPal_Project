public class MultipleChoiceQuestion extends Question {
    private String[] choices;
    private char correctAnswer;

    public MultipleChoiceQuestion(String questionText, String[] choices, char correctAnswer, int points) {
        super(questionText, points);
        this.choices = choices;
        this.correctAnswer = correctAnswer;
    }

    public boolean checkAnswer(String answer) {
        return answer.charAt(0) == correctAnswer;
    }

    public void displayQuestion() {
        char[] questionText;
        System.out.println(questionText);
        for (int i = 0; i < choices.length; i++) {
            System.out.println((char)('A' + i) + ": " + choices[i]);
        }
    }
}