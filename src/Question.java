public interface Question {
    String getQuestion();
    boolean isCorrect(String ans);
    String getCorrectAns();
    int getPoints();
}
