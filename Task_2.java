package CodSoft;


//STUDENT GRADE CALCULATOR
import java.util.Scanner;

public class Task_2 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfSubjects;
        double totalMarks = 0;
        double averagePercentage;
        
        System.out.println("Enter the number of subjects:");
        numberOfSubjects = scanner.nextInt();
        
        double marks = 0 ; 
        
        System.out.println("Enter the marks obtained in each subject out of 100:");
        for (int i = 0; i < numberOfSubjects; i++) {
            marks = scanner.nextDouble();
            totalMarks += marks;
        }
        
        averagePercentage = totalMarks / numberOfSubjects;
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        
        System.out.println("Grade: " + grade);
        
        scanner.close();
    }
}


    


