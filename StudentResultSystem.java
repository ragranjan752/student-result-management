import java.util.Scanner;

public class StudentResultSystem {

    static Scanner sc = new Scanner(System.in);

    static int[] studentId = new int[10];
    static String[] studentName = new String[10];
    static int[][] marks = new int[10][5];

    static int studentCount = 0;

    static String[] subjects = {
            "Java",
            "DSA",
            "Math",
            "English",
            "Science"
    };

    public static void main(String[] args) {

        while (true) {

            displayMenu();

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                addStudent();
            } else if (choice == 2) {
                displayStudents();
            } else if (choice == 3) {
                searchStudent();
            } else if (choice == 4) {
                sortStudents();
            } else if (choice == 5) {
                findTopper();
            } else if (choice == 6) {
                subjectAnalysis();
            } else if (choice == 7) {
                generateResult();
            } else if (choice == 8) {
                System.out.println("\nThank you for using Student Result System!");
                break;
            } else {
                System.out.println("\nInvalid choice!");
            }
        }

        sc.close();
    }

    static void displayMenu() {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("       STUDENT RESULT MANAGEMENT");
        System.out.println("==========================================");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Sort Students");
        System.out.println("5. Find Topper");
        System.out.println("6. Subject-wise Analysis");
        System.out.println("7. Generate Student Result");
        System.out.println("8. Exit");
        System.out.println("==========================================");
    }

    static void addStudent() {

        if (studentCount == 10) {
            System.out.println("\nMaximum 10 students can be added.");
            return;
        }

        System.out.println("\n========== ADD STUDENT ==========");

        System.out.print("Enter Student ID: ");
        studentId[studentCount] = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Student Name: ");
        studentName[studentCount] = sc.nextLine();

        System.out.println("\nEnter Marks:");

        for (int j = 0; j < 5; j++) {

            System.out.print(subjects[j] + ": ");
            marks[studentCount][j] = sc.nextInt();

            while (marks[studentCount][j] < 0 ||
                    marks[studentCount][j] > 100) {

                System.out.println("Marks must be between 0 and 100.");

                System.out.print(subjects[j] + ": ");
                marks[studentCount][j] = sc.nextInt();
            }
        }

        studentCount++;

        System.out.println("\nStudent added successfully!");
    }

    static void displayStudents() {

        if (studentCount == 0) {
            System.out.println("\nNo students available.");
            return;
        }

        System.out.println("\n================ ALL STUDENTS ================");

        System.out.printf(
                "%-8s %-15s %-7s %-7s %-7s %-8s %-8s%n",
                "ID",
                "Name",
                "Java",
                "DSA",
                "Math",
                "English",
                "Science"
        );

        System.out.println(
                "--------------------------------------------------------------"
        );

        for (int i = 0; i < studentCount; i++) {

            System.out.printf(
                    "%-8d %-15s %-7d %-7d %-7d %-8d %-8d%n",
                    studentId[i],
                    studentName[i],
                    marks[i][0],
                    marks[i][1],
                    marks[i][2],
                    marks[i][3],
                    marks[i][4]
            );
        }
    }

    static void searchStudent() {

        if (studentCount == 0) {
            System.out.println("\nNo students available.");
            return;
        }

        System.out.println("\n========== SEARCH STUDENT ==========");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        int index = -1;

        if (choice == 1) {

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            for (int i = 0; i < studentCount; i++) {

                if (studentId[i] == id) {
                    index = i;
                    break;
                }
            }
        } else if (choice == 2) {

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            for (int i = 0; i < studentCount; i++) {

                if (studentName[i].equalsIgnoreCase(name)) {
                    index = i;
                    break;
                }
            }
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        if (index == -1) {
            System.out.println("\nStudent not found.");
        } else {
            displayStudentDetails(index);
        }
    }


    static void displayStudentDetails(int index) {

        System.out.println("\n====================================");
        System.out.println("         STUDENT DETAILS");
        System.out.println("====================================");

        System.out.println("ID   : " + studentId[index]);
        System.out.println("Name : " + studentName[index]);

        System.out.println("\nMarks:");

        for (int j = 0; j < 5; j++) {
            System.out.println(subjects[j] + " : " + marks[index][j]);
        }

        int total = calculateTotal(index);
        double percentage = calculatePercentage(index);

        System.out.println("------------------------------------");
        System.out.println("Total      : " + total + " / 500");
        System.out.println("Percentage : " + percentage + "%");
        System.out.println("Grade      : " + calculateGrade(percentage));

        if (isPass(index)) {
            System.out.println("Result     : PASS");
        } else {
            System.out.println("Result     : FAIL");
        }

        System.out.println("====================================");
    }

    static void sortStudents() {

        if (studentCount < 2) {
            System.out.println("\nNot enough students to sort.");
            return;
        }

        System.out.println("\n========== SORT STUDENTS ==========");
        System.out.println("1. Sort by Total Marks");
        System.out.println("2. Sort by Student ID");
        System.out.println("3. Sort by Name");

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        System.out.println("\n1. Ascending");
        System.out.println("2. Descending");

        System.out.print("Enter order: ");
        int order = sc.nextInt();

        if (choice == 1) {
            sortByTotal(order);
        } else if (choice == 2) {
            sortById(order);
        } else if (choice == 3) {
            sortByName(order);
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        System.out.println("\nStudents sorted successfully!");

        displayStudents();
    }

    static void sortByTotal(int order) {

        for (int i = 0; i < studentCount - 1; i++) {

            for (int j = 0; j < studentCount - i - 1; j++) {

                int total1 = calculateTotal(j);
                int total2 = calculateTotal(j + 1);

                boolean swap = false;

                if (order == 1 && total1 > total2) {
                    swap = true;
                }

                if (order == 2 && total1 < total2) {
                    swap = true;
                }

                if (swap) {
                    swapStudents(j, j + 1);
                }
            }
        }
    }

    static void sortById(int order) {

        for (int i = 0; i < studentCount - 1; i++) {

            for (int j = 0; j < studentCount - i - 1; j++) {

                boolean swap = false;

                if (order == 1 && studentId[j] > studentId[j + 1]) {
                    swap = true;
                }

                if (order == 2 && studentId[j] < studentId[j + 1]) {
                    swap = true;
                }

                if (swap) {
                    swapStudents(j, j + 1);
                }
            }
        }
    }

    static void sortByName(int order) {

        for (int i = 0; i < studentCount - 1; i++) {

            for (int j = 0; j < studentCount - i - 1; j++) {

                int comparison =
                        studentName[j].compareToIgnoreCase(studentName[j + 1]);

                boolean swap = false;

                if (order == 1 && comparison > 0) {
                    swap = true;
                }

                if (order == 2 && comparison < 0) {
                    swap = true;
                }

                if (swap) {
                    swapStudents(j, j + 1);
                }
            }
        }
    }

    static void swapStudents(int i, int j) {

        int tempId = studentId[i];
        studentId[i] = studentId[j];
        studentId[j] = tempId;

        String tempName = studentName[i];
        studentName[i] = studentName[j];
        studentName[j] = tempName;

        for (int k = 0; k < 5; k++) {

            int tempMarks = marks[i][k];

            marks[i][k] = marks[j][k];

            marks[j][k] = tempMarks;
        }
    }

    static void findTopper() {

        if (studentCount == 0) {
            System.out.println("\nNo students available.");
            return;
        }

        int topperIndex = 0;

        for (int i = 1; i < studentCount; i++) {

            if (calculateTotal(i) > calculateTotal(topperIndex)) {
                topperIndex = i;
            }
        }

        System.out.println("\n====================================");
        System.out.println("             TOPPER");
        System.out.println("====================================");

        System.out.println("Name       : " + studentName[topperIndex]);
        System.out.println("ID         : " + studentId[topperIndex]);
        System.out.println("Total      : " + calculateTotal(topperIndex));
        System.out.println("Percentage : " +
                calculatePercentage(topperIndex) + "%");
        System.out.println("Grade      : " +
                calculateGrade(calculatePercentage(topperIndex)));

        System.out.println("====================================");
    }

    static void subjectAnalysis() {

        if (studentCount == 0) {
            System.out.println("\nNo students available.");
            return;
        }

        System.out.println("\n========== SUBJECT ANALYSIS ==========");

        for (int subject = 0; subject < 5; subject++) {

            int highest = marks[0][subject];
            int lowest = marks[0][subject];

            int sum = 0;

            int highestStudent = 0;

            for (int student = 0; student < studentCount; student++) {

                int mark = marks[student][subject];

                sum += mark;

                if (mark > highest) {
                    highest = mark;
                    highestStudent = student;
                }

                if (mark < lowest) {
                    lowest = mark;
                }
            }

            double average = (double) sum / studentCount;

            System.out.println("\nSubject : " + subjects[subject]);
            System.out.println("Highest : " + highest +
                    " (" + studentName[highestStudent] + ")");
            System.out.println("Lowest  : " + lowest);
            System.out.println("Average : " + average);
        }
    }

    static void generateResult() {

        if (studentCount == 0) {
            System.out.println("\nNo students available.");
            return;
        }

        System.out.print("\nEnter Student ID: ");
        int id = sc.nextInt();

        int index = -1;

        for (int i = 0; i < studentCount; i++) {

            if (studentId[i] == id) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("\nStudent not found.");
            return;
        }

        printResultPattern();

        System.out.println("\nStudent ID   : " + studentId[index]);
        System.out.println("Student Name : " + studentName[index]);

        System.out.println("\n-----------------------------");

        for (int j = 0; j < 5; j++) {

            System.out.printf(
                    "%-10s : %d%n",
                    subjects[j],
                    marks[index][j]
            );
        }

        int total = calculateTotal(index);
        double percentage = calculatePercentage(index);

        System.out.println("-----------------------------");

        System.out.println("Total        : " + total + " / 500");
        System.out.println("Percentage   : " + percentage + "%");
        System.out.println("Grade        : " + calculateGrade(percentage));

        if (isPass(index)) {
            System.out.println("Result       : PASS");
        } else {
            System.out.println("Result       : FAIL");
        }

        System.out.println("=============================");
    }

    static int calculateTotal(int index) {

        int total = 0;

        for (int j = 0; j < 5; j++) {
            total += marks[index][j];
        }

        return total;
    }

    static double calculatePercentage(int index) {

        int total = calculateTotal(index);

        return total / 5.0;
    }

    static String calculateGrade(double percentage) {

        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    static boolean isPass(int index) {

        for (int j = 0; j < 5; j++) {

            if (marks[index][j] < 35) {
                return false;
            }
        }

        return true;
    }


    static void printResultPattern() {

        System.out.println();

        for (int i = 0; i < 40; i++) {
            System.out.print("=");
        }

        System.out.println();

        System.out.println("          STUDENT RESULT");

        for (int i = 0; i < 40; i++) {
            System.out.print("=");
        }

        System.out.println();
    }
}