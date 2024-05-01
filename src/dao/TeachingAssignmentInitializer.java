/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.ArrayList;
import adt.ListInterface;
import entity.Course;
import entity.Tutor;
import entity.TutorialGroup;

/**
 *
 * @author Ng Ding Lun
 */
public class TeachingAssignmentInitializer {

    public ListInterface<TutorialGroup> initializeTutorialGroup() {
        //应该是tutorialgroup加tutor object
        ListInterface<TutorialGroup> tgList = new ArrayList<>();
        TutorialGroup tutorGroup1 = new TutorialGroup("RSD01", 22);
        TutorialGroup tutorGroup2 = new TutorialGroup("RSD02", 18);
        TutorialGroup tutorGroup3 = new TutorialGroup("RSD03", 24);
        TutorialGroup tutorGroup4 = new TutorialGroup("RSD04", 24);
        TutorialGroup tutorGroup5 = new TutorialGroup("RSD05", 17);
        TutorialGroup tutorGroup6 = new TutorialGroup("RIS01", 20);
        TutorialGroup tutorGroup7 = new TutorialGroup("RIS02", 21);
        TutorialGroup tutorGroup8 = new TutorialGroup("RIS03", 19);
        TutorialGroup tutorGroup9 = new TutorialGroup("RIS04", 21);
        TutorialGroup tutorGroup10 = new TutorialGroup("RIS05", 19);

        tgList.add(tutorGroup1);
        tgList.add(tutorGroup2);
        tgList.add(tutorGroup3);
        tgList.add(tutorGroup4);
        tgList.add(tutorGroup5);
        tgList.add(tutorGroup6);
        tgList.add(tutorGroup7);
        tgList.add(tutorGroup8);
        tgList.add(tutorGroup9);
        tgList.add(tutorGroup10);

        return tgList;
    }

    public ListInterface<Tutor> initializeTutor() {
        //应该是tutor加course object
        ListInterface<Tutor> tList = new ArrayList<>();

        Tutor tutor1 = new Tutor("T0001", "Dr Aw Kien Sin", "awks@tarc.edu.my", "Male", "Department of Information And Communication Technology", "Full Time", "Human Computing Interaction", "Doctor of Philosophy(PhD) UKM");
        Tutor tutor2 = new Tutor("T0002", "Ts. Bavani A/P Raja Pandian", "bavanirp@tarc.edu.my", "Female", "Department of Software Engineering And Technology", "Full Time", "Software Engineering", "Doctor of Philosophy(PhD) in Tech");
        Tutor tutor3 = new Tutor("T0003", "Encik Lai Joo Choi", "laijc@tarc.edu.my", "Male", "Department of Information Systems And Security", "Full Time", "Human Computing Interaction", "Master Degree in Computer Science");
        Tutor tutor4 = new Tutor("T0004", "Puan Kathleen Tan Swee Neo", "tansn@tarc.edu.my", "Female", "Department of Computer Science And Embedded Systems", "Full Time", "Data Structure and Algorithms", "Doctor of Philosophy Canditate");
        Tutor tutor5 = new Tutor("T0005", "Puan Thamarai A/P Subramaniam", "thamarai@tarc.edu.my", "Female", "Department of Computer Science And Embedded Systems", "Full Time", "Data Structure and Algorithms", "Master Degree of Computer Science");
        Tutor tutor6 = new Tutor("T0006", "TIN TIN TING", "ting@tarc.edu.my", "Female", "Department of Object-Oriented", "Part Time", "Object-Oriented Analysis and Design", "Master Degree of Computer Science");

        tutor1.addTutorGroup(initializeTutorialGroup().getEntry(1));
        tutor2.addTutorGroup(initializeTutorialGroup().getEntry(2));
        tutor2.addTutorGroup(initializeTutorialGroup().getEntry(3));

        tList.add(tutor1);
        tList.add(tutor2);
        tList.add(tutor3);
        tList.add(tutor4);
        tList.add(tutor5);
        tList.add(tutor6);

        return tList;
    }

    public ListInterface<Course> initializeCourse() {

        ListInterface<Course> cList = new ArrayList<>();
        Course course1 = new Course("BAIT2203", "Human Computing Interaction", 3);
        Course course2 = new Course("BACS2063", "Data Structure and Algorithms", 4);
        Course course3 = new Course("BACS2163", "Software Engineering", 2);
        Course course4 = new Course("BACS2053", "Object-Oriented Analysis and Design", 3);
        //Human Computing Interaction
        course1.addTutor(initializeTutor().getEntry(1));
        course1.addTutor(initializeTutor().getEntry(3));
        //Data Structure and Algorithms
        course2.addTutor(initializeTutor().getEntry(4));
        course2.addTutor(initializeTutor().getEntry(5));
        //Software Engineering
        course3.addTutor(initializeTutor().getEntry(2));
        //Object-Oriented Analysis and Design
        course4.addTutor(initializeTutor().getEntry(6));

        cList.add(course1);
        cList.add(course2);
        cList.add(course3);
        cList.add(course4);

        return cList;
    }

    public static void main(String[] args) {
        // To illustrate how to use the initializeProducts() method
        TeachingAssignmentInitializer p = new TeachingAssignmentInitializer();
        CourseDAO cDAO = new CourseDAO();
        TutorDAO tDAO = new TutorDAO();
        cDAO.saveToFile(p.initializeCourse());
        tDAO.saveToFile(p.initializeTutor());
    }
}
