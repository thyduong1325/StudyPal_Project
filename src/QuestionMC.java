public class QuestionMC implements Question {
    private String question; // Stores the question text
    private String[] choices; // Stores the choices for the question
    private String correctChoice; // Stores the correct choice for the question
    private int points; // Stores the points associated with the question

    // Constructor to initialize the QuestionMC object
    public QuestionMC(String question, String[] choices, String correctChoice, int points) {
        this.question = question;
        this.choices = choices;
        this.correctChoice = correctChoice;
        this.points = points;
    }

    // Method to get the question text
    @Override
    public String getQuestion() {
        return question;
    }
    
    // Method to get the choices for the question
    public String[] getChoices(){
        return choices;
    }

    // Method to check if the provided answer is correct
    @Override
    public boolean isCorrect(String answer) {
        return answer.equalsIgnoreCase(correctChoice);
    }

    // Method to get the correct choice for the question
    @Override
    public String getCorrectAnswer() {
        return correctChoice;
    }

    // Method to get the points associated with the question
    @Override
    public int getPoints() {
        return points;
    }
    
    // Method to display the question details
    @Override
    public String display() {
        String s = "Points: " + this.points + "\nMultiple Choice: " + question;
        for (int i = 0 ; i < choices.length ; i++) {
            s += "\n" + Character.toString((char)(65 + i)) + ") ";
            s += choices[i];
        }
        return s;
    }
}
