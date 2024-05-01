package entity;

import adt.ArrayList;
import adt.ListInterface;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Ng Ding Lun
 */
public class Tutor implements Serializable, Comparable<Tutor>{

    private String tutorID;
    private String tutorName;
    private String email;
    private String gender;
    private String department;
    private String jobTutor;
    private String majorStudy;
    private String qualification;
    
    private ListInterface<TutorialGroup> tutogroups = new ArrayList<>();

    

    public Tutor() {
    }

    public Tutor(String tutorID, String tutorName, String email, String gender, String department, String jobTutor, String majorStudy, String qualification) {
        this.tutorID = tutorID;
        this.tutorName = tutorName;
        this.email = email;
        this.gender = gender;
        this.department = department;
        this.jobTutor = jobTutor;
        this.majorStudy = majorStudy;
        this.qualification = qualification;
    }
   
    public Tutor(String tutorID, String tutorName, String email, String gender, String department,String jobTutor, String majorStudy, TutorialGroup tutorgroup) {
        this.tutorID = tutorID;
        this.tutorName = tutorName;
        this.email = email;
        this.gender = gender;
        this.jobTutor = jobTutor;
        this.majorStudy = majorStudy;
        this.tutogroups = new ArrayList<>();
        addTutorGroup(tutorgroup);
    }
    
     public void addTutorGroup(TutorialGroup tutogroup) {
        tutogroups.add(tutogroup);
    }
     

    public ListInterface<TutorialGroup> getTutogroups() {
        return tutogroups;
    }

    public void setTutogroups(ListInterface<TutorialGroup> tutogroups) {
        this.tutogroups = tutogroups;
    }

    public boolean hasTutorialGroup(TutorialGroup tutorgroup) {
        for (int i = 1; i <= tutogroups.getNumberOfEntries(); i++) {
            TutorialGroup assignedTutorGroup = tutogroups.getEntry(i);
            if (assignedTutorGroup.getTutorialGroupID().equals(tutorgroup.getTutorialGroupID())) {
                return true; // Tutor is already assigned to the course
            }
        }
        return false; // Tutor is not assigned to the course
    }
     
    public String getTutorID() {
        return tutorID;
    }

    public void setTutorID(String tutorID) {
        this.tutorID = tutorID;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

  
    public String getJobTutor() {
        return jobTutor;
    }

    public void setJobTutor(String jobTutor) {
        this.jobTutor = jobTutor;
    }

    public String getMajorStudy() {
        return majorStudy;
    }

    public void setMajorStudy(String majorStudy) {
        this.majorStudy = majorStudy;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.tutorID);
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
        final Tutor other = (Tutor) obj;
        return Objects.equals(this.tutorID, other.tutorID);
    }

    @Override
    public String toString() {
        return "Tutor{" + "tutorID=" + tutorID + ", tutorName=" + tutorName + ", email=" + email + ", gender=" + gender + ", department=" + department + ", jobTutor=" + jobTutor + ", majorStudy=" + majorStudy + ", qualification=" + qualification + '}';
    }

    @Override
    public int compareTo(Tutor o) {
        return this.tutorID.compareTo(o.tutorID);
    }

    
    

    

    
   

}
