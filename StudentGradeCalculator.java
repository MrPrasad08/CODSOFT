import java.util.Scanner;

public class StudentGradeCalculator {

    public static String GradeCalculator(double score) {
        if (score >= 90) {
            return "Grade: A+  - Outstanding!";
        } else if (score >= 80) {
            return "Grade: A  - Excellent work!";
        } else if (score >= 70) {
            return "Grade: B  - Good job!";
        } else if (score >= 60) {
            return "Grade: C  - Fair result.";
        } else if (score >= 50) {
            return "Grade: D  - You passed";
        } else {
            return "Grade: F  - Don't give up! Failure is the first step to success.";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Begin the evaluation process
        System.err.println("==========================================");
        System.err.println("  ");
        System.out.println(" Welcome to the Student Grade Calculator!");
        System.err.println("  ");
        System.err.println("==========================================");
        System.out.println("\n How many subjects have you studied?");
        int Total = scanner.nextInt();

        double cumulativeScore = 0;
        String[] subjects = new String[Total];

        // Collect subject names and marks
        for (int i = 0; i < Total; i++) {
            System.out.print("\nEnter the subject name " + (i + 1) + ": ");
            scanner.nextLine();
            subjects[i] = scanner.nextLine();

            System.out.print("  Enter the marks scored in " + subjects[i] + " (out of 100): ");
            double marks = scanner.nextDouble();

            // Validate marks
            while (marks < 0 || marks > 100) {
                System.out.println("Marks must be between 0 and 100.");
                System.out.print("Re-enter valid marks for " + subjects[i] + ": ");
                marks = scanner.nextDouble();
            }

            cumulativeScore += marks;
        }

        // Calculate performance
        double averageScore = cumulativeScore / Total;
        String finalGrade = GradeCalculator(averageScore);

        // Display final results
        System.out.println("\n----------- FINAL REPORT -----------");
        System.out.printf("Total Marks Scored: %.2f / %d\n", cumulativeScore, Total * 100);
        System.out.printf("Average Score: %.2f%%\n", averageScore);
        System.out.println(" " + finalGrade);
        System.out.println("---------------------------------------");

        scanner.close();
    }
}
