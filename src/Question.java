public interface Question {
    String getQuestion();
    boolean isCorrect(String ans);
    String getCorrectAnswer();
    int getPoints();
    String display();
}
