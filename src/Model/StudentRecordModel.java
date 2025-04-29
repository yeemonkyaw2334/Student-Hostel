package Model;
import DBConnection.DBConfig;
import java.sql.*;

public class StudentRecordModel {
    private String studentID;
    private String staffID;
    private String studentName;
    private String studentDOB;
    private String studentNRC;
    private String address;
    private String phoneNumber;
    private String email;
    private String entryYear;
    private String gender;
    private String fatherName;
    private String guardianPhoneNumber;
    private String dataEntryDate;
    private String remark;
    // Getters and setters for the fields
    public String getStudentID() { return studentID; }
    public void setStudentID(String studentID) { this.studentID = studentID; }

    public String getStaffID() { return staffID; }
    public void setStaffID(String staffID) { this.staffID = staffID; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getStudentDOB() { return studentDOB; }
    public void setStudentDOB(String studentDOB) { this.studentDOB = studentDOB; }

    public String getStudentNRC() { return studentNRC; }
    public void setStudentNRC(String studentNRC) { this.studentNRC = studentNRC; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEntryYear() { return entryYear; }
    public void setEntryYear(String entryYear) { this.entryYear = entryYear; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }

    public String getGuardianPhoneNumber() { return guardianPhoneNumber; }
    public void setGuardianPhoneNumber(String guardianPhoneNumber) { this.guardianPhoneNumber = guardianPhoneNumber; }

    public String getDataEntryDate() { return dataEntryDate; }
    public void setDataEntryDate(String dataEntryDate) { this.dataEntryDate = dataEntryDate; }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
    
    private String regionCode;
    private String townshipCode;
    private String citizenType;
    private String number;

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getTownshipCode() {
        return townshipCode;
    }

    public void setTownshipCode(String townshipCode) {
        this.townshipCode = townshipCode;
    }

    public String getCitizenType() {
        return citizenType;
    }

    public void setCitizenType(String citizenType) {
        this.citizenType = citizenType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public void setFormattedNRC(String formattedNRC) {
        // Expected format: regionCode/townshipCode(citizenType)number
        // Example: 12/PaTa(N)123456

        try {
            String[] parts = formattedNRC.split("[/()]");

            if (parts.length == 4) {
                this.regionCode = parts[0];
                this.townshipCode = parts[1];
                this.citizenType = parts[2];
                this.number = parts[3];
            } else {
                throw new IllegalArgumentException("Invalid NRC format.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Or handle it however you like
        }
    }
    public String getFormattedNRC() {
        return regionCode + "/" + townshipCode + citizenType + number;
    }
	

}
