package entity;

import adt.ArrayList;
import adt.ListInterface;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Ng Ding Lun
 */
public class Course implements Serializable, Comparable<Course> {

    private String courseID;
    private String courseName;
    private int creditHours;
    private ListInterface<Tutor> tutors = new ArrayList<>();

    public Course() {
    }

    public Course(String courseID, String courseName, int creditHours) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.creditHours = creditHours;
    }

    public Course(String courseID, String courseName, int creditHours, Tutor tutor) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.tutors = new ArrayList<>();
        addTutor(tutor);
    }

    public void addTutor(Tutor tutor) {
        tutors.add(tutor);
    }

    public ListInterface<Tutor> getTutors() {
        return tutors;
    }

    public void setTutors(ListInterface<Tutor> tutors) {
        this.tutors = tutors;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.courseID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        return Objects.equals(this.courseID, other.courseID);
    }

    @Override
    public int compareTo(Course otherCourse) {
        // Compare based on courseID (you can choose any criteria you want)
        return this.courseID.compareTo(otherCourse.courseID);
    }

    @Override
    public String toString() {
        return "Course{" + "courseID=" + courseID + ", courseName=" + courseName + ", creditHours=" + creditHours + '}';
    }

    public boolean hasTutor(Tutor tutor) {
        for (int i = 1; i <= tutors.getNumberOfEntries(); i++) {
            Tutor assignedTutor = tutors.getEntry(i);
            if (assignedTutor.getTutorID().equals(tutor.getTutorID())) {
                return true; // Tutor is already assigned to the course
            }
        }
        return false; // Tutor is not assigned to the course
    }

    public void replaceTutor(Tutor newTutor) {
        for (int i = 0; i < tutors.getNumberOfEntries(); i++) {
            Tutor tutor = tutors.getEntry(i);
            if (tutor.getTutorID().equals(tutors.getEntry(i).getTutorID())) {
                tutors.replace(i, newTutor); // Replace the tutor with the new tutor
            }
        }
    }
}
