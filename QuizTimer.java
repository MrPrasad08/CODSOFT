import java.util.Scanner;
import java.util.*;

class Question {
    String questionText;
    String[] options;
    char correctAnswer;

    public Question(String questionText, String[] options, char correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

class QuizApp {
    private List<Question> questionList = new ArrayList<>();
    private int score = 0;
    private Scanner scanner = new Scanner(System.in);
    private final int TIME_LIMIT = 10;

    public void addQuestions() {
        questionList.add(new Question(
                "What is the capital of India?",
                new String[]{"A. Gujarat", "B. Mumbai", "C. New Delhi", "D. Maharashtra"},
                'C'));

        questionList.add(new Question(
                "Which of these is the  Procedure Oriented programming language?",
                new String[]{"A. C", "B. Python", "C. C++", "D. Swift"},
                'A'));

        questionList.add(new Question(
                "What is the value of 5 * 7?",
                new String[]{"A. 5", "B. 35", "C. 30", "D. 10"},
                'B'));
        
        questionList.add(new Question(
                "What is the national animal of india?",
                new String[]{"A. Lion", "B. Zebra", "C. Monkey", "D. Royal Bengal Tiger"},
                'D'));
                
        questionList.add(new Question(
                "How much percentage of water in Earth?",
                new String[]{"A. 100%", "B. 58%", "C. 71%", "D. 20%"},
                'C'));
    }

    public void startQuiz() {
        System.out.println("Welcome to the Timed Quiz! You have 10 seconds to answer each question.");

        for (int i = 0; i < questionList.size(); i++) {
            Question currentQuestion = questionList.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + currentQuestion.questionText);

            for (String option : currentQuestion.options) {
                System.out.println(option);
            }

            Timer timer = new Timer();
            TimerTask timeoutTask = new TimerTask() {
                public void run() {
                    System.out.println("\nTime's up!");
                    synchronized (scanner) {
                        scanner.notify();
                    }
                }
            };
            timer.schedule(timeoutTask, TIME_LIMIT * 1000L);

            String answer = "";
            System.out.print("Your answer (A/B/C/D): ");
            synchronized (scanner) {
                try {
                    if (scanner.hasNextLine()) {
                        answer = scanner.nextLine().toUpperCase();
                    }
                    timer.cancel();
                } catch (Exception e) {
                    System.out.println("Invalid input.");
                }
            }

            if (!answer.isEmpty() && answer.charAt(0) == currentQuestion.correctAnswer) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer was: " + currentQuestion.correctAnswer);
            }
        }

        displayResults();
    }

    private void displayResults() {
        System.out.println("\nQuiz Completed!");
        System.out.println("Your final score is: " + score + " out of " + questionList.size());
    }
}

public class QuizTimer {
    public static void main(String[] args) {
        QuizApp quiz = new QuizApp();
        quiz.addQuestions();
        quiz.startQuiz();
    }
}
