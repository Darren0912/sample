package control;

import adt.*;
import boundary.TeachingAssignmentUI;
import dao.CourseDAO;
import dao.TeachingAssignmentInitializer;
import dao.TutorDAO;
import entity.*;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author Ng Ding Lun
 */
public class TeachingAssignmentControl {

    private Scanner scanner = new Scanner(System.in);
    private TutorDAO tDAO = new TutorDAO();
    private CourseDAO cDAO = new CourseDAO();
    private TeachingAssignmentInitializer p = new TeachingAssignmentInitializer();
    private TeachingAssignmentUI teachingUI = new TeachingAssignmentUI();
    private ListInterface<Course> cList = new ArrayList<>();
    private ListInterface<Tutor> tList = new ArrayList<>();
    private ListInterface<TutorialGroup> tgList = new ArrayList<>();

    public TeachingAssignmentControl() {
        cList = cDAO.retrieveFromFile();
        tList = tDAO.retrieveFromFile();
        tgList = p.initializeTutorialGroup();
    }

    public void runUI() {
        int choice = 0;
        do {
            choice = teachingUI.displayMenu();
            switch (choice) {
                case 0:
                    System.out.println("Exit....");
                    break;
                case 1:
                    assignTutorCourse();
                    break;
                case 2:
                    assignGroupsTutor();
                    break;
                case 3:
                    assignTutorGroup();
                    break;
                case 4:
                    searchTutorsCourse();
                    break;
                case 5:
                    searchCourseTutors();
                    break;
                case 6:
                    listTutorsCourse();
                    break;
                case 7:
                    listCoursesTutor();
                    break;
                case 8:
                    filterTutor();
                    break;
                case 9:
                    generateReport();
                    break;

                default:
                    MessageUI.wrongInput();
                    break;
            }
        } while (choice != 0);
    }

    //choice 1
    public ListInterface<Course> assignTutorCourse() {
        boolean cresult = false;
        boolean tresult = false;

        while (!cresult || !tresult) {
            System.out.println("< Tutor List >");
            for (int i = 1; i <= tList.getNumberOfEntries(); i++) {
                Tutor tutor = tList.getEntry(i);
                System.out.println("\n[" + i + "]" + " Tutor ID: " + tutor.getTutorID());
                System.out.println("    Tutor Name: " + tutor.getTutorName());
                System.out.println("    Tutor Department: " + tutor.getDepartment());
                System.out.println("    Tutor Major Study: " + tutor.getMajorStudy());

            }

            String selectedTutor = teachingUI.inputTutorID();

            for (int i = 1; i <= tList.getNumberOfEntries(); i++) {
                Tutor tutor = tList.getEntry(i);
                if (selectedTutor.equals(tutor.getTutorID())) {
                    tresult = true;
                    System.out.println("\n< Course List >");
                    for (int j = 1; j <= cList.getNumberOfEntries(); j++) {
                        Course course = cList.getEntry(j);
                        System.out.println("\n[" + j + "]" + " Course ID: " + course.getCourseID());
                        System.out.println("    Course Name: " + course.getCourseName());
                    }
                    String selectedCourse = teachingUI.inputCourseID(); //input Course ID
                    for (int j = 1; j <= cList.getNumberOfEntries(); j++) {
                        Course course = cList.getEntry(j);

                        if (selectedCourse.equals(course.getCourseID())) {
                            if (!course.hasTutor(tutor)) {
                                course.addTutor(tutor);
                                cList.replace(j, course);
                                cDAO.saveToFile(cList);
                                cresult = true;
                                break;
                            } else {
                                MessageUI.assignedTutorCourse();
                                tresult = true;
                                cresult = true;
                                break;
                            }
                        }
                    }
                    if (!cresult) {
                        MessageUI.noCourse();
                        break;
                    }
                    break;
                }
            }
            if (!tresult) {
                MessageUI.noTutor();
            }

        }
        return cList;
    }

//choice 2
    public ListInterface<Tutor> assignGroupsTutor() {
        boolean tresult = false;
        String choice = "";
        String selectedTutorGroup;
        ListInterface<String> selectedGroupList = new ArrayList<>();
        do {
            boolean tgresult = false;
            int totalUnassignedGroup = 0;
            System.out.println("\n< Unassigned Tutorial Group > ");
            for (int i = 1; i <= tgList.getNumberOfEntries(); i++) {
                TutorialGroup tutorGroup = tgList.getEntry(i);
                boolean isAssigned = false;

                for (int j = 1; j <= tList.getNumberOfEntries(); j++) {
                    Tutor tutor = tList.getEntry(j);
                    ListInterface<TutorialGroup> assignedtutorialGroup = tutor.getTutogroups();

                    if (tutor.hasTutorialGroup(tutorGroup)) {
                        isAssigned = true;
                        break;
                    }
                }
                if (!(selectedGroupList.contains(tutorGroup.getTutorialGroupID())) && !isAssigned) {
                    totalUnassignedGroup++;
                    System.out.println("[" + totalUnassignedGroup + "]" + " Tutorial Group ID: " + tutorGroup.getTutorialGroupID());
                }
            }
            selectedTutorGroup = teachingUI.inputGroupID(); //input Tutorial Group ID
            for (int i = 1; i <= tgList.getNumberOfEntries(); i++) {
                TutorialGroup tutorGroup = tgList.getEntry(i);
                if (selectedTutorGroup.equals(tutorGroup.getTutorialGroupID())) {
                    tgresult = true;
                }
            }
            if (!tgresult) {
                MessageUI.noGroup();
                choice = "Yes";
            } else {
                selectedGroupList.add(selectedTutorGroup);
                choice = teachingUI.continueAddGroup();
            }
        } while (choice.equals("Yes"));

        while (!tresult) {
            ListInterface<TutorialGroup> tutorGroup = findTutorialGroupsByIDs(selectedGroupList);
            if (tutorGroup != null) {
                String output = "";
                for (int i = 1; i <= selectedGroupList.getNumberOfEntries(); i++) {
                    output += selectedGroupList.getEntry(i) + " ,";
                }
                System.out.println("\n\nSelect a Tutor for Tutorial Group ID: " + output + "\b");
                System.out.println("< Tutor List >");
                for (int j = 1; j <= tList.getNumberOfEntries(); j++) {
                    Tutor tutor = tList.getEntry(j);
                    System.out.println("[" + j + "]" + " Tutor ID: " + tutor.getTutorID());
                    System.out.println("    Tutor Name: " + tutor.getTutorName());
                    System.out.println("    Tutor Department: " + tutor.getDepartment());
                    System.out.println("    Tutor Major Study: " + tutor.getMajorStudy());
                }
                String selectedTutorID = teachingUI.inputTutorID();
                for (int i = 1; i <= tList.getNumberOfEntries(); i++) {
                    Tutor selectedTutor = tList.getEntry(i);
                    if (selectedTutor.getTutorID().equals(selectedTutorID)) {
                        tresult = true;
                    }
                }
                for (int i = 1; i <= cList.getNumberOfEntries(); i++) {
                    Course course = cList.getEntry(i);
                    ListInterface<Tutor> tutors = course.getTutors();
                    for (int k = 1; k <= tutors.getNumberOfEntries(); k++) {
                        Tutor tutor = tutors.getEntry(k);
                        if (tresult == true && tutor.getTutorID().equals(selectedTutorID)) {
                            for (int m = 1; m <= tutorGroup.getNumberOfEntries(); m++) {
                                TutorialGroup tutorGroup1 = tutorGroup.getEntry(m);
                                tutor.addTutorGroup(tutorGroup1);
                            }
                            for (int j = 1; j < tList.getNumberOfEntries(); j++) {
                                if (selectedTutorID.equals(tList.getEntry(j).getTutorID())) {
                                    tList.replace(j, tutor);
                                }
                            }
                            if (!course.hasTutor(tutor)) {
                                course.replaceTutor(tutor);
                            }
                            cList.replace(i, course);
                            cDAO.saveToFile(cList);
                            tDAO.saveToFile(tList);
                            break;
                        }
                    }
                }
                if (!tresult) {
                    MessageUI.noTutor();
                }
            }
        }
        return tList;
    }

    public ListInterface<TutorialGroup> findTutorialGroupsByIDs(ListInterface<String> selectedGroupList) {
        ListInterface<TutorialGroup> matchingGroups = new ArrayList<>();
        for (int j = 1; j <= selectedGroupList.getNumberOfEntries(); j++) {
            String groupID = selectedGroupList.getEntry(j);
            for (int i = 1; i <= tgList.getNumberOfEntries(); i++) {
                TutorialGroup tutorGroup = tgList.getEntry(i);
                if (groupID.equals(tutorGroup.getTutorialGroupID())) {
                    matchingGroups.add(tutorGroup); // Found a matching tutorial group, add it to the list
                }
            }
        }
        return matchingGroups;
    }

    //choice 3
    public ListInterface<Tutor> assignTutorGroup() {
        int totalUnassignedGroup = 0;
        boolean tgresult = false;
        boolean tresult = false;
        boolean exist = false;
        while (!tgresult || !tresult || !exist) {
            System.out.println("\n< Tutor List >");
            for (int i = 1; i <= tList.getNumberOfEntries(); i++) {
                Tutor tutor = tList.getEntry(i);
                System.out.println("\n[" + i + "]" + " Tutor Name: " + tutor.getTutorName());
                System.out.println("    Tutor Department: " + tutor.getDepartment());
                System.out.println("    Tutor Major Study: " + tutor.getMajorStudy());
            }

            String selectedTutorID = teachingUI.inputTutorID();
            for (int i = 1; i <= tList.getNumberOfEntries(); i++) {
                Tutor selectedTutor = tList.getEntry(i);
                if (selectedTutor.getTutorID().equals(selectedTutorID)) {
                    tresult = true;
                }
            }
            for (int m = 1; m <= cList.getNumberOfEntries(); m++) {
                Course course = cList.getEntry(m);
                ListInterface<Tutor> tutors = course.getTutors();

                for (int i = 1; i <= tutors.getNumberOfEntries(); i++) {
                    Tutor tutor = tutors.getEntry(i);
                    if (tresult == true &&tutor.getTutorID().equals(selectedTutorID)) {
                        System.out.println("\n< Unassigned Tutorial Group > ");
                        for (int j = 1; j <= tgList.getNumberOfEntries(); j++) {
                            TutorialGroup tutorGroup = tgList.getEntry(j);
                            boolean isAssigned = false;

                            for (int k = 1; k <= tList.getNumberOfEntries(); k++) {
                                Tutor tutor1 = tList.getEntry(k);
                                ListInterface<TutorialGroup> assignedtutorialGroup = tutor.getTutogroups();
                                if (tutor1.hasTutorialGroup(tutorGroup)) {
                                    isAssigned = true;
                                    break;
                                }
                            }
                            if (!isAssigned) {
                                totalUnassignedGroup++;
                                    System.out.println("[" + totalUnassignedGroup + "]" + " Tutorial Group ID: " + tutorGroup.getTutorialGroupID());
                            }
                        }

                        String selectedTutorGroup = teachingUI.inputGroupID(); //input Tutorial Group ID
                        for (int k = 1; k <= tgList.getNumberOfEntries(); k++) {
                            TutorialGroup tutorGroup = tgList.getEntry(k);
                            if (selectedTutorGroup.equals(tutorGroup.getTutorialGroupID())) {
                                tgresult = true;
                                if (!tutor.hasTutorialGroup(tutorGroup)) {
                                    tutor.addTutorGroup(tutorGroup);
                                    for (int j = 1; j <= tList.getNumberOfEntries(); j++) {
                                        if (selectedTutorID.equals(tList.getEntry(j).getTutorID())) {
                                            tList.replace(j, tutor);
                                        }
                                    }
                                    if (!course.hasTutor(tutor)) {
                                        course.replaceTutor(tutor);
                                    }
                                    cList.replace(m, course);
                                    cDAO.saveToFile(cList);
                                    tDAO.saveToFile(tList);  
                                    exist = true;
                                    return tList;
                                    
                                } else {
                                    MessageUI.assignedTutorGroup();
                                    tresult = true;
                                    tgresult = true;
                                    exist = false;
                                    break;
                                }
                            }
                        }
                        if (!tgresult) {
                            MessageUI.noGroup();
                            break;
                        }
                       
                    }
                    
                }
              
            }

            if (!tresult) {
                MessageUI.noTutor();
            }
        }

        return tList;
    }

    //choice 4
    public void searchTutorsCourse() {
        String tutorID = teachingUI.inputTutorID();

        String outputStr = "";
        boolean tutorFound = false;
        int totalCourse = 0;
        for (int i = 1; i <= cList.getNumberOfEntries(); i++) {
            Course course = cList.getEntry(i);

            ListInterface<Tutor> tutors = course.getTutors();
            for (int j = 1; j <= tutors.getNumberOfEntries(); j++) {
                Tutor tutor = tutors.getEntry(j);
                if (tutorID.contains(tutor.getTutorID())) {
                    totalCourse++;
                    outputStr += "\n[" + totalCourse + "]" + " Course ID: " + course.getCourseID() + "\n    Course Name: " + course.getCourseName();
                    tutorFound = true;

                }
            }
        }
        if (!tutorFound) {
            MessageUI.noTutor();
        } else {
            System.out.print(outputStr);
        }
        System.out.print("\nTotal Course Found: " + totalCourse + "\n");

        scanner.nextLine();
    }

    //choice 5
    public void searchCourseTutors() {
        String courseID = teachingUI.inputCourseID(); //input Course ID
        String outputStr = "";
        boolean courseFound = false;
        int totalTutor = 0;
        for (int i = 1; i <= cList.getNumberOfEntries(); i++) {
            Course course = cList.getEntry(i);
            ListInterface<Tutor> tutors = course.getTutors();
            if (courseID.contains(course.getCourseID())) {
                for (int j = 1; j <= tutors.getNumberOfEntries(); j++) {
                    Tutor tutor = tutors.getEntry(j);
                    totalTutor++;
                    outputStr += "\n[" + totalTutor + "]" + " Tutor ID: " + tutor.getTutorID() + "\n    Tutor Name: " + tutor.getTutorName();
                    courseFound = true;
                }
            }
        }
        if (!courseFound) {
            MessageUI.noCourse();
        } else {
            System.out.print(outputStr);
        }
        System.out.print("\nTotal Tutor Found: " + totalTutor + "\n");

        scanner.nextLine();
    }

    //choice 6
    public void listTutorsCourse() {
        cList.sort();
        System.out.println("");
        System.out.println("< Sorted Course List >");
        for (int i = 1; i <= cList.getNumberOfEntries(); i++) {
            Course course = cList.getEntry(i);

            System.out.println("[" + i + "]" + " Course ID: " + course.getCourseID());
            System.out.printf("%3s %-12s\n", " ", "Course Name: " + course.getCourseName());
            ListInterface<Tutor> tutors = course.getTutors();
            System.out.println("\n< Assigned Course to Tutors >");
            for (int j = 1; j <= tutors.getNumberOfEntries(); j++) {
                Tutor tutor = tutors.getEntry(j);
                System.out.println(j + "." + " Tutor ID: " + tutor.getTutorID());
                System.out.printf("%2s %-12s\n", " ", "Tutor Name: " + tutor.getTutorName());
                System.out.printf("%2s %-12s\n", " ", "Tutor Email: " + tutor.getEmail());
                System.out.printf("%2s %-12s\n", " ", "Tutor Department: " + tutor.getDepartment());
                System.out.printf("%2s %-12s\n", " ", "Tutor Qualification: " + tutor.getQualification());
            }
            System.out.println("--------------------------\n");
        }
        scanner.nextLine();
    }

    //choice 7
    public void listCoursesTutor() {
        tList.sort();
        System.out.println("< Sorted Tutor List >");
        for (int i = 1; i <= tList.getNumberOfEntries(); i++) {
            Tutor tutor = tList.getEntry(i);
            System.out.println("Tutor ID: " + tutor.getTutorID());
            System.out.println("Tutor Name: " + tutor.getTutorName());
            ListInterface<Course> displayedCourses = new ArrayList<>();
            int totalCourses = 0;
            for (int j = 1; j <= cList.getNumberOfEntries(); j++) {
                Course course = cList.getEntry(j);
                ListInterface<Tutor> tutors = course.getTutors();
                for (int m = 1; m <= tutors.getNumberOfEntries(); m++) {
                    Tutor tutor1 = tutors.getEntry(m);
                    if (tutor.equals(tutor1) && !displayedCourses.contains(course)) {
                        totalCourses++;
                        System.out.println("[" + totalCourses + "]" + " Course ID: " + course.getCourseID());
                        System.out.printf("%3s %-12s\n", " ", "Course Name: " + course.getCourseName());
                        System.out.printf("%3s %-12s\n", " ", "Course Credit Hours: " + course.getCreditHours());

                    }

                }
            }
            System.out.println("--------------------------\n");
        }
        scanner.nextLine();
    }

    //choice 8
    public void filterTutor() {
        boolean result = false;
        while (!result) {
            int choice = teachingUI.displayFilterMenu();
            ListInterface<Tutor> filteredTutors = new ArrayList<>();
            switch (choice) {
                case 1:
                    int selectedGender = teachingUI.filterGender();
                    tList.sort();

                    switch (selectedGender) {
                        case 1:
                            teachingUI.filterHeader();
                            System.out.printf("\n%-10s %-35s %-25s %-10s\n", "Tutor ID", "Tutor Name", "Email", "Gender");
                            for (int i = 1; i <= tList.getNumberOfEntries(); i++) {
                                Tutor tutor = tList.getEntry(i);
                                if (tutor.getGender().equals("Male")) {
                                    filteredTutors.add(tutor);
                                }
                            }
                            tList.retainAll(filteredTutors);
                            for (int i = 1; i <= filteredTutors.getNumberOfEntries(); i++) {
                                Tutor filteredTutor = filteredTutors.getEntry(i);
                                System.out.printf("%-10s %-35s %-25s %-10s\n", filteredTutor.getTutorID(), filteredTutor.getTutorName(), filteredTutor.getEmail(), filteredTutor.getGender());
                            }
                            if (filteredTutors == null) {
                                MessageUI.noFoundGender();
                            }
                            teachingUI.filterFooter();
                            result = true;
                            break;
                        case 2:
                            teachingUI.filterHeader();
                            System.out.printf("\n%-10s %-35s %-25s %-10s\n", "Tutor ID", "Tutor Name", "Email", "Gender");
                            for (int i = 1; i <= tList.getNumberOfEntries(); i++) {
                                Tutor tutor = tList.getEntry(i);
                                if (tutor.getGender().equals("Female")) {
                                    filteredTutors.add(tutor);
                                }
                            }
                            tList.retainAll(filteredTutors);
                            for (int i = 1; i <= filteredTutors.getNumberOfEntries(); i++) {
                                Tutor filteredTutor = filteredTutors.getEntry(i);
                                System.out.printf("%-10s %-35s %-25s %-10s\n", filteredTutor.getTutorID(), filteredTutor.getTutorName(), filteredTutor.getEmail(), filteredTutor.getGender());
                            }
                            if (filteredTutors == null) {
                                MessageUI.noFoundGender();
                            }
                            result = true;
                            teachingUI.filterFooter();
                            break;
                        case 3:
                            break;
                        default:
                            MessageUI.wrongInput();
                            break;
                    }
                    break;
                case 2:
                    int selectedJob = teachingUI.filterJob();
                    tList.sort();
                    switch (selectedJob) {
                        case 1:
                            teachingUI.filterHeader();
                            System.out.printf("\n%-10s %-35s %-25s %-10s\n", "Tutor ID", "Tutor Name", "Email", "Job Categories");
                            for (int i = 1; i <= tList.getNumberOfEntries(); i++) {
                                Tutor tutor = tList.getEntry(i);
                                if (tutor.getJobTutor().contains("Full Time")) {
                                    filteredTutors.add(tutor);
                                }
                            }
                            tList.retainAll(filteredTutors);
                            for (int i = 1; i <= filteredTutors.getNumberOfEntries(); i++) {
                                Tutor filteredTutor = filteredTutors.getEntry(i);
                                System.out.printf("%-10s %-35s %-25s %-10s\n", filteredTutor.getTutorID(), filteredTutor.getTutorName(), filteredTutor.getEmail(), filteredTutor.getJobTutor());
                            }
                            if (filteredTutors == null) {
                                MessageUI.noFoundGender();
                            }
                            result = true;
                            teachingUI.filterFooter();
                            break;
                        case 2:
                            teachingUI.filterHeader();
                            System.out.printf("\n%-10s %-35s %-25s %-10s\n", "Tutor ID", "Tutor Name", "Email", "Job Categories");
                            for (int i = 1; i <= tList.getNumberOfEntries(); i++) {
                                Tutor tutor = tList.getEntry(i);
                                if (tutor.getJobTutor().contains("Part Time")) {
                                    filteredTutors.add(tutor);
                                }
                            }

                            tList.retainAll(filteredTutors);
                            for (int i = 1; i <= filteredTutors.getNumberOfEntries(); i++) {
                                Tutor filteredTutor = filteredTutors.getEntry(i);
                                System.out.printf("%-10s %-35s %-25s %-10s\n", filteredTutor.getTutorID(), filteredTutor.getTutorName(), filteredTutor.getEmail(), filteredTutor.getJobTutor());
                            }
                            if (filteredTutors == null) {
                                MessageUI.noFoundGender();
                            }
                            result = true;
                            teachingUI.filterFooter();
                            break;
                        case 3:
                            break;
                        default:
                            MessageUI.wrongInput();
                            break;
                    }
                    break;
                case 3:
                    int selectedStudy = teachingUI.filterMajorStudy();
                    tList.sort();
                    switch (selectedStudy) {
                        case 1:
                            teachingUI.filterHeader();
                            System.out.printf("\n%-10s %-35s %-25s %-10s\n", "Tutor ID", "Tutor Name", "Email", "Major Study");
                            for (int i = 1; i <= tList.getNumberOfEntries(); i++) {
                                Tutor tutor = tList.getEntry(i);
                                if (tutor.getMajorStudy().contains("Human Computing Interaction")) {
                                    filteredTutors.add(tutor);
                                }
                            }
                            tList.retainAll(filteredTutors);
                            for (int i = 1; i <= filteredTutors.getNumberOfEntries(); i++) {
                                Tutor filteredTutor = filteredTutors.getEntry(i);
                                System.out.printf("%-10s %-35s %-25s %-10s\n", filteredTutor.getTutorID(), filteredTutor.getTutorName(), filteredTutor.getEmail(), filteredTutor.getMajorStudy());
                            }
                            if (filteredTutors == null) {
                                MessageUI.noFoundGender();
                            }
                            result = true;
                            teachingUI.filterFooter();
                            break;
                        case 2:
                            teachingUI.filterHeader();
                            System.out.printf("\n%-10s %-35s %-25s %-10s\n", "Tutor ID", "Tutor Name", "Email", "Major Study");
                            for (int i = 1; i <= tList.getNumberOfEntries(); i++) {
                                Tutor tutor = tList.getEntry(i);
                                if (tutor.getMajorStudy().contains("Data Structure and Algorithms")) {
                                    filteredTutors.add(tutor);
                                }
                            }
                            tList.retainAll(filteredTutors);
                            for (int i = 1; i <= filteredTutors.getNumberOfEntries(); i++) {
                                Tutor filteredTutor = filteredTutors.getEntry(i);
                                System.out.printf("%-10s %-35s %-25s %-10s\n", filteredTutor.getTutorID(), filteredTutor.getTutorName(), filteredTutor.getEmail(), filteredTutor.getMajorStudy());
                            }
                            if (filteredTutors == null) {
                                MessageUI.noFoundGender();
                            }
                            result = true;
                            teachingUI.filterFooter();
                            break;
                        case 3:
                            teachingUI.filterHeader();
                            System.out.printf("\n%-10s %-35s %-25s %-10s\n", "Tutor ID", "Tutor Name", "Email", "Major Study");
                            for (int i = 1; i <= tList.getNumberOfEntries(); i++) {
                                Tutor tutor = tList.getEntry(i);
                                if (tutor.getMajorStudy().contains("Software Engineering")) {
                                    filteredTutors.add(tutor);
                                }
                            }
                            tList.retainAll(filteredTutors);
                            for (int i = 1; i <= filteredTutors.getNumberOfEntries(); i++) {
                                Tutor filteredTutor = filteredTutors.getEntry(i);
                                System.out.printf("%-10s %-35s %-25s %-10s\n", filteredTutor.getTutorID(), filteredTutor.getTutorName(), filteredTutor.getEmail(), filteredTutor.getMajorStudy());
                            }
                            if (filteredTutors == null) {
                                MessageUI.noFoundGender();
                            }
                            result = true;
                            teachingUI.filterFooter();
                            break;

                        case 4:
                            teachingUI.filterHeader();
                            System.out.printf("\n%-10s %-35s %-25s %-10s\n", "Tutor ID", "Tutor Name", "Email", "Major Study");
                            for (int i = 1; i <= tList.getNumberOfEntries(); i++) {
                                Tutor tutor = tList.getEntry(i);
                                if (tutor.getMajorStudy().contains("Object-Oriented Analysis and Design")) {
                                    filteredTutors.add(tutor);
                                }
                            }
                            tList.retainAll(filteredTutors);
                            for (int i = 1; i <= filteredTutors.getNumberOfEntries(); i++) {
                                Tutor filteredTutor = filteredTutors.getEntry(i);
                                System.out.printf("%-10s %-35s %-25s %-10s\n", filteredTutor.getTutorID(), filteredTutor.getTutorName(), filteredTutor.getEmail(), filteredTutor.getMajorStudy());
                            }
                            if (filteredTutors == null) {
                                MessageUI.noFoundGender();
                            }
                            result = true;
                            teachingUI.filterFooter();
                            break;

                        case 5:
                            break;
                        default:
                            MessageUI.wrongInput();
                            break;
                    }
                    break;
                case 4:
                    int selectedDepartment = teachingUI.filterDepartment();
                    tList.sort();
                    switch (selectedDepartment) {
                        case 1:
                            teachingUI.filterHeader();
                            System.out.printf("\n%-10s %-35s %-25s %-10s\n", "Tutor ID", "Tutor Name", "Email", "Department");
                            for (int i = 1; i <= tList.getNumberOfEntries(); i++) {
                                Tutor tutor = tList.getEntry(i);
                                if (tutor.getDepartment().contains("Department of Information And Communication Technology")) {
                                    filteredTutors.add(tutor);
                                }
                            }
                            tList.retainAll(filteredTutors);
                            for (int i = 1; i <= filteredTutors.getNumberOfEntries(); i++) {
                                Tutor filteredTutor = filteredTutors.getEntry(i);
                                System.out.printf("%-10s %-35s %-25s %-10s\n", filteredTutor.getTutorID(), filteredTutor.getTutorName(), filteredTutor.getEmail(), filteredTutor.getDepartment());
                            }
                            if (filteredTutors == null) {
                                MessageUI.noFoundGender();
                            }
                            result = true;
                            teachingUI.filterFooter();
                            break;
                        case 2:
                            teachingUI.filterHeader();
                            System.out.printf("\n%-10s %-35s %-25s %-10s\n", "Tutor ID", "Tutor Name", "Email", "Department");
                            for (int i = 1; i <= tList.getNumberOfEntries(); i++) {
                                Tutor tutor = tList.getEntry(i);
                                if (tutor.getDepartment().contains("Department of Software Engineering And Technology")) {
                                    filteredTutors.add(tutor);
                                }
                            }
                            for (int i = 1; i <= filteredTutors.getNumberOfEntries(); i++) {
                                Tutor filteredTutor = filteredTutors.getEntry(i);
                                System.out.printf("%-10s %-35s %-25s %-10s\n", filteredTutor.getTutorID(), filteredTutor.getTutorName(), filteredTutor.getEmail(), filteredTutor.getDepartment());
                            }
                            if (filteredTutors == null) {
                                MessageUI.noFoundGender();
                            }
                            result = true;
                            teachingUI.filterFooter();
                            break;
                        case 3:
                            teachingUI.filterHeader();
                            System.out.printf("\n%-10s %-35s %-25s %-10s\n", "Tutor ID", "Tutor Name", "Email", "Department");
                            for (int i = 1; i <= tList.getNumberOfEntries(); i++) {
                                Tutor tutor = tList.getEntry(i);
                                if (tutor.getDepartment().contains("Department of Computer Science And Embedded Systems")) {
                                    filteredTutors.add(tutor);
                                }
                            }
                            for (int i = 1; i <= filteredTutors.getNumberOfEntries(); i++) {
                                Tutor filteredTutor = filteredTutors.getEntry(i);
                                System.out.printf("%-10s %-35s %-25s %-10s\n", filteredTutor.getTutorID(), filteredTutor.getTutorName(), filteredTutor.getEmail(), filteredTutor.getDepartment());
                            }
                            if (filteredTutors == null) {
                                MessageUI.noFoundGender();
                            }
                            result = true;
                            teachingUI.filterFooter();
                            break;
                        case 4:
                            teachingUI.filterHeader();
                            System.out.printf("\n%-10s %-35s %-25s %-10s\n", "Tutor ID", "Tutor Name", "Email", "Department");
                            for (int i = 1; i <= tList.getNumberOfEntries(); i++) {
                                Tutor tutor = tList.getEntry(i);
                                if (tutor.getDepartment().contains("Department of Object-Oriented")) {
                                    filteredTutors.add(tutor);
                                }
                            }
                            for (int i = 1; i <= filteredTutors.getNumberOfEntries(); i++) {
                                Tutor filteredTutor = filteredTutors.getEntry(i);
                                System.out.printf("%-10s %-35s %-25s %-10s\n", filteredTutor.getTutorID(), filteredTutor.getTutorName(), filteredTutor.getEmail(), filteredTutor.getDepartment());
                            }
                            if (filteredTutors == null) {
                                MessageUI.noFoundGender();
                            }
                            result = true;
                            teachingUI.filterFooter();
                            break;
                        case 5:
                            break;
                        default:
                            MessageUI.wrongInput();
                            break;
                    }
                    break;

                case 5:
                    runUI();
                    break;
                default:
                    MessageUI.wrongInput();
                    break;

            }
        }
    }

    //choice 9
    public void generateReport() {
        int choice = teachingUI.displayReportMenu();

        switch (choice) {
            case 1:
                teachingUI.report1UI();
                cList.sort();
                for (int i = 1; i <= cList.getNumberOfEntries(); i++) {

                    Course course = cList.getEntry(i);
                    ListInterface<Tutor> tutors = course.getTutors();
                    int totalTutor = 0;
                    System.out.println("[" + i + "]" + " Course ID: " + course.getCourseID());
                    System.out.printf("%3s %-12s\n", " ", "Course Name: " + course.getCourseName());
                    System.out.printf("\n%-50s\n", "-----------------------------------------------------------------------------------------------------------------------------------");
                    System.out.printf("| %-30s | | %-54s | | %-33s |\n", "Tutor Name", "Department ", "Qualification");
                    for (int j = 1; j <= tutors.getNumberOfEntries(); j++) {

                        Tutor tutor = tutors.getEntry(j);

                        System.out.printf("| %-30s | | %-54s | | %-33s |\n", tutor.getTutorName(), tutor.getDepartment(), tutor.getQualification());

                        totalTutor++;
                    }
                    System.out.printf("%-50s\n", "-----------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("Total Tutor: " + totalTutor);
                    System.out.printf("%-50s", "-----------------------------------------------------------------------------------------------------------------------------------\n\n");
                }
                scanner.nextLine();
                break;
            case 2:
                String outputID;
                teachingUI.report2UI();

                for (int j = 1; j <= tList.getNumberOfEntries(); j++) {
                    boolean tutorAvailability = false;
                    outputID = "";
                    int totalTutorialGroup = 0;
                    Tutor tutor = tList.getEntry(j);
                    ListInterface<TutorialGroup> tutorGroup = tutor.getTutogroups();

                    // Display Tutor ID and Name
                    System.out.println("[" + j + "]" + " Tutor Name: " + tutor.getTutorName());
                    System.out.printf("%3s %-12s\n", " ", "Tutor Email: " + tutor.getEmail());
                    System.out.printf("%3s %-12s\n", " ", "Tutor Department: " + tutor.getDepartment());
                    // Collect Tutorial Group IDs and count total tutorial groups
                    for (int k = 1; k <= tutorGroup.getNumberOfEntries(); k++) {
                        TutorialGroup tutorialGroup = tutorGroup.getEntry(k);
                        String tutorialGroupID = tutorialGroup.getTutorialGroupID();
                        outputID += tutorialGroupID + ",";
                        totalTutorialGroup++;
                    }
                    if (totalTutorialGroup == 0) {
                        outputID = "No Tutorial Group ";
                    }
                    System.out.printf("\n%-50s\n", "-----------------------------------------------------------------------------------------------------------------------------------");
                    System.out.printf("| %-30s | | %-54s | | %-33s |\n", "Course ID", "Course Name", "Tutorial Group");

                    ListInterface<Course> displayedCourses = new ArrayList<>();
                    int tutorTotalCredit = 0;
                    cList.sort();
                    for (int i = 1; i <= cList.getNumberOfEntries(); i++) {
                        Course course = cList.getEntry(i);
                        ListInterface<Tutor> tutors = course.getTutors();

                        for (int m = 1; m <= tutors.getNumberOfEntries(); m++) {
                            Tutor tutor1 = tutors.getEntry(m);

                            // Check if the current tutor matches the tutor in the course
                            if (tutor.equals(tutor1) && !displayedCourses.contains(course)) {
                                System.out.printf("| %-30s | | %-54s | | %-35s |\n", course.getCourseID(), course.getCourseName(), outputID + "\b");

                                int courseCredit = totalTutorialGroup * course.getCreditHours();
                                tutorTotalCredit += courseCredit; // Add course credit to tutor's total credit
                                displayedCourses.add(course); // Mark this course as displayed
                            }
                        }
                        if (tutorTotalCredit > 15) {

                        }
                    }
                    System.out.printf("%-50s\n", "-----------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("Total Credit for Tutor " + tutor.getTutorName() + ": " + tutorTotalCredit);
                    System.out.printf("%-50s", "-----------------------------------------------------------------------------------------------------------------------------------\n\n");

                }
                scanner.nextLine();
                break;
            case 3:
                int totalCourse = 0;
                int totalEnrolledStudent = 0;
                int enrolledStudent = 0;
                teachingUI.report3UI();
                cList.sort();

                for (int i = 1; i <= cList.getNumberOfEntries(); i++) {
                    Course course = cList.getEntry(i);

                    ListInterface<Tutor> tutors = course.getTutors();

                    // Check if there are tutors assigned to this course
                    if (!tutors.isEmpty()) {
                        boolean coursePrinted = false; // Flag to track if this course has been printed
                        enrolledStudent = 0;
                        for (int j = 1; j <= tutors.getNumberOfEntries(); j++) {

                            Tutor tutor = tutors.getEntry(j);
                            ListInterface<TutorialGroup> tutorialGroups = tutor.getTutogroups();

                            for (int k = 1; k <= tutorialGroups.getNumberOfEntries(); k++) {
                                TutorialGroup tutorGroup = tutorialGroups.getEntry(k);
                                enrolledStudent += tutorGroup.getNumStudent();
                            }

                            // If we printed the course for this tutor, set the flag to true
                            if (enrolledStudent > 0) {
                                coursePrinted = true;
                            }
                        }
                        totalEnrolledStudent += enrolledStudent;

                        // If the course was printed at least once, increment the total course count
                    }

                    System.out.printf("| %-30s | | %-54s | | %-33s |\n", course.getCourseID(), course.getCourseName(), enrolledStudent);
                    totalCourse++;

                }

                System.out.printf("%-50s\n\n", "-----------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("Total Courses: " + totalCourse);
                System.out.print("\nTotal Enrolled Student: " + totalEnrolledStudent);
                System.out.printf("\n%-50s\n\n", "-----------------------------------------------------------------------------------------------------------------------------------");
                scanner.nextLine();
                break;
            case 4:
                runUI();
                break;
            default:
                MessageUI.wrongInput();
                break;
        }
    }

    public static void main(String[] args) {
        TeachingAssignmentInitializer p = new TeachingAssignmentInitializer();
        TeachingAssignmentControl tControl = new TeachingAssignmentControl();
        tControl.runUI();

    }
}
