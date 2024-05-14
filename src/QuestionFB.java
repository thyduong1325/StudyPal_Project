public class QuestionFB implements Question {
    private String question; // Stores the question text
    private String answer; // Stores the correct answer
    private int points; // Stores the points associated with the question

    // Constructor to initialize question, answer, and points
    public QuestionFB(String question, String answer, int points) {
        this.question = question;
        this.answer = answer;
        this.points = points;
    }

    // Getter method to retrieve the question text
    @Override
    public String getQuestion() {
        return question;
    }

    // Method to check if the provided answer is correct
    @Override
    public boolean isCorrect(String answer) {
        return this.answer.equalsIgnoreCase(answer);
    }

    // Getter method to retrieve the correct answer
    @Override
    public String getCorrectAnswer() {
        return answer;
    }

    // Getter method to retrieve the points associated with the question
    @Override
    public int getPoints() {
        return points;
    }
    
    // Method to display the question with its associated points
    @Override
    public String display() {
        return "Points: " + this.points + "\nFill in the Blank: " + question;
    }
}
