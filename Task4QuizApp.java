package CodSoft;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class QuizQuestion {
    private String question;
    private List<String> options;
    private String correctAnswer;

    public QuizQuestion(String question, List<String> options, String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}

class Quiz {
    private List<QuizQuestion> questions;
    private int score;
    private List<Boolean> results;

    public Quiz() {
        this.questions = new ArrayList<>();
        this.score = 0;
        this.results = new ArrayList<>();
        loadQuestions();
    }

    private void loadQuestions() {
        questions.add(new QuizQuestion("What is the capital of France?", 
            Arrays.asList("A. Paris", "B. London", "C. Berlin", "D. Madrid"), "A"));
        questions.add(new QuizQuestion("Which planet is known as the Red Planet?", 
            Arrays.asList("A. Earth", "B. Mars", "C. Jupiter", "D. Venus"), "B"));
        // Add more questions as needed
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (QuizQuestion question : questions) {
            System.out.println(question.getQuestion());
            for (String option : question.getOptions()) {
                System.out.println(option);
            }

            boolean answered = false;
            long startTime = System.currentTimeMillis();
            long endTime = startTime + 15000; // 15 seconds timer

            while (System.currentTimeMillis() < endTime && !answered) {
                if (scanner.hasNextLine()) {
                    String answer = scanner.nextLine().trim().toUpperCase();
                    answered = true;
                    if (answer.equals(question.getCorrectAnswer())) {
                        score++;
                        results.add(true);
                    } else {
                        results.add(false);
                    }
                }
            }

            if (!answered) {
                System.out.println("Time's up! Moving to the next question.");
                results.add(false);
            }
        }

        displayResults();
    }

    private void displayResults() {
        System.out.println("Quiz Over!");
        System.out.println("Your Score: " + score + "/" + questions.size());
        for (int i = 0; i < questions.size(); i++) {
            QuizQuestion question = questions.get(i);
            boolean result = results.get(i);
            System.out.println((i + 1) + ". " + question.getQuestion());
            System.out.println("Correct Answer: " + question.getCorrectAnswer());
            System.out.println("Your Answer: " + (result ? "Correct" : "Incorrect"));
        }
    }

    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.startQuiz();
    }
}
