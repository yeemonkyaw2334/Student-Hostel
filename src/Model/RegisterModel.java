package Model;

import java.sql.Date;

public class RegisterModel {
    private String registrationID;
    private String staffID;
    private String studentID;
    private String hostelType;
    private String studentName;
    private Date registrationDate;
    private String registrationStatus;

    // Getter and setter for registrationID
    public String getRegistrationID() {
        return registrationID;
    }
    public void setRegistrationID(String registrationID) {
        this.registrationID = registrationID;
    }

    // Getter and setter for staffID
    public String getStaffID() {
        return staffID;
    }
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    // Getter and setter for studentID
    public String getStudentID() {
        return studentID;
    }
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    // Getter and setter for studentName
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    // Getter and setter for hostelType
    public String getHostelType() {
        return hostelType;
    }
    public void setHostelType(String hostelType) {
        this.hostelType = hostelType;
    }

    // Getter and setter for registrationStatus
    public String getRegistrationStatus() {
        return registrationStatus;
    }
    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    // Getter and setter for registrationDate
    public Date getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    
}
