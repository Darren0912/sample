/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import java.util.Scanner;

/**
 *
 * @author Ng Ding Lun
 */
public class TeachingAssignmentUI {

    Scanner scanner = new Scanner(System.in);

    public int displayMenu() {
        System.out.println("\n\n[ Teaching Assignment Subsystem ]");
        System.out.println("[1] Assign Tutor to Course");
        System.out.println("[2] Assign tutorial groups to tutor");
        System.out.println("[3] Assign a tutor to a tutorial group");
        System.out.println("[4] Search courses under a tutor");
        System.out.println("[5] Search tutors for a course");
        System.out.println("[6] List all tutors for a course");
        System.out.println("[7] List courses under a tutor");
        System.out.println("[8] Filter tutors based on criteria");
        System.out.println("[9] Generate relevant reports");
        System.out.println("[0] Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

    public int displayFilterMenu() {
        System.out.println("\n\n[ Filter Tutor in Teaching Assignment Subsystem ]");
        System.out.println("[1] Filter Gender (Tutor) ");
        System.out.println("[2] Filter Categories of Job (Tutor)");
        System.out.println("[3] Filter Major Study (Tutor)");
        System.out.println("[4] Filter Department (Tutor)");
        System.out.println("[5] Back to Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        return choice;
    }

    public int displayReportMenu() {
        System.out.println("\n\n[ Teaching Assignment Subsystem Report ]");
        System.out.println("[1] Tutor Assigned Report ");
        System.out.println("[2] Tutor Workload Report");
        System.out.println("[3] Course Enrollment Report");
        System.out.println("[4] Back to Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        return choice;
    }

    public String inputTutorID() {
        System.out.print("\nPlease enter Tutor ID (e.g. T0001): ");
        String selectedTutor = scanner.next();
        return selectedTutor;
    }

    public String inputCourseID() {
        System.out.print("\nPlease enter Course ID (e.g. BACS2063): ");
        String selectedCourse = scanner.next();
        return selectedCourse;
    }

    public String inputGroupID() {
        String selectedTutorGroup;
        System.out.print("\nPlease enter Tutorial Group ID (e.g. RSD01): ");
        selectedTutorGroup = scanner.next();
        return selectedTutorGroup;
    }

    public String continueAddGroup() {
        String choice;
        System.out.print("Do you want to add tutorial groups again?(Yes to continue): ");
        choice = scanner.next();
        return choice;
    }

    public void filterFooter() {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    }

    public void filterHeader() {
        System.out.print("\n-------------------------------------------------------------------------------------------------------------------------------");
    }
    
    public int filterGender() {
        System.out.println("\n\n[ Filter Tutor Gender ]");
        System.out.println("[1] Male");
        System.out.println("[2] Female");
        System.out.println("[3] Back to Filter Menu");
        System.out.print("Please Select Gender with Number: ");
        int selectedGender = scanner.nextInt();
        scanner.nextLine();
        return selectedGender;
    }

    public int filterJob() {
        System.out.println("\n\n[ Filter Tutor Job ]");
        System.out.println("[1] Full Time");
        System.out.println("[2] Part time");
        System.out.println("[3] Back to Filter Menu");
        System.out.print("Please Select Job with Number: ");
        int selectedJob = scanner.nextInt();
        scanner.nextLine();
        return selectedJob;
    }

    public int filterMajorStudy() {
        System.out.println("\n\n[ Filter Tutor Major Study ]");
        System.out.println("[1] Human Computing Interaction");
        System.out.println("[2] Data Structure and Algorithms");
        System.out.println("[3] Software Engineering");
        System.out.println("[4] Object-Oriented Analysis and Design");
        System.out.println("[5] Back to Filter Menu");
        System.out.print("Please Select Major Study with Number: ");
        int selectedStudy = scanner.nextInt();
        scanner.nextLine();
        return selectedStudy;
    }

    public int filterDepartment() {
        System.out.println("\n\n[ Filter Tutor's Department ]");
        System.out.println("[1] Department of Information And Communication Technology");
        System.out.println("[2] Department of Software Engineering And Technology");
        System.out.println("[3] Department of Computer Science And Embedded Systems");
        System.out.println("[4] Department of Object-Oriented");
        System.out.println("[5] Back to Filter Menu");
        System.out.print("Please Select Department with Number: ");
        int selectedDepartment = scanner.nextInt();
        scanner.nextLine();
        return selectedDepartment;
    }

    public void filterQualification() {
        System.out.println("\n\n[ Filter Tutor's Qualification ]");
        System.out.println("[1] Master Degree");
        System.out.println("[2] Phd");
        System.out.println("[3] Department of Computer Science And Embedded Systems");
        System.out.println("[4] Department of Object-Oriented");
        System.out.println("[5] Back to Filter Menu");
        System.out.print("Please Select Qualification with Number: ");
        int selectedQualification = scanner.nextInt();
        scanner.nextLine();
    }

    
     public void report1UI() {
        System.out.println("\n\n-----------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-50s %-20s %58s\n", "|", "Tutor Assigned Report", "|");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------\n");
    }

    public void report2UI() {
        System.out.println("\n\n-----------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-50s %-20s %58s\n", "|", "Tutor Workload Report", "|");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------\n");
    }

    public void report3UI() {
        System.out.println("\n\n-----------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-50s %-20s %55s\n", "|", "Course Enrollment Report", "|");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println("Faculty: FOCS");
        System.out.printf("\n%-50s\n", "-----------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-30s | | %-54s | | %-33s |", " Course ID", "Course Name", "Enrolled Student");
        System.out.printf("\n%-50s\n", "-----------------------------------------------------------------------------------------------------------------------------------");
    }
}
