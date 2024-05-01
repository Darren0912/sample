/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author Ding L
 */
public class MessageUI {

    public static void wrongInput() {
        System.out.println("\nWrong Input. Please Enter Again..");
    }

    public static void assignedTutorCourse() {
        System.out.println("\nTutor is already assigned to the selected course.");
    }

    public static void assignedTutorGroup() {
        System.out.println("\nTutor is already assigned to the selected tutorial group.");
    }

    public static void noTutor() {
        System.out.println("\nNo such Tutor");
    }

    public static void noCourse() {
        System.out.println("\nNo such course");
    }

    public static void noGroup() {
        System.out.println("\nNo such Tutorial Group\n");
    }
    
    public static void noFoundDepartment() {
        System.out.println("\nNo Tutor's Department Record");
    }

    public static void noFoundMajor() {
        System.out.println("\nNo Tutor's Major Study Record");
    }

    public static void noFoundJob() {
        System.out.println("\nNo Tutor's Job Category Record");
    }

    public static void noFoundGender() {
        System.out.println("\nNo Tutor's Gender Record");
    }
}
