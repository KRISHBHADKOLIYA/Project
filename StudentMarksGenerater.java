    System.out.print("Enter the number of subjects: ");
    int numSubjects = scanner.nextInt();

    int[] marks = new int[numSubjects];
    int total = 0;

    // Input marks
    for (int i = 0; i < numSubjects; i++) {
        System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
        marks[i] = scanner.nextInt();

        // Validate input
        while (marks[i] < 0 || marks[i] > 100) {
            System.out.print("Invalid input. Enter marks between 0 and 100: ");
            marks[i] = scanner.nextInt();
        }

        total += marks[i];
    }

    // Calculate average
    double average = (double) total / numSubjects;

    // Determine grade
    char grade;
    if (average >= 90) {
        grade = 'A';
    } else if (average >= 80) {
        grade = 'B';
    } else if (average >= 70) {
        grade = 'C';
    } else if (average >= 60) {
        grade = 'D';
    } else if (average >= 50) {
        grade = 'E';
    } else {
        grade = 'F';
    }

    // Display results
    System.out.println("\n--- Result ---");
    System.out.println("Total Marks: " + total + " out of " + (numSubjects * 100));
    System.out.printf("Average Percentage: %.2f%%\n", average);
    System.out.println("Grade: " + grade);

    scanner.close();
}
