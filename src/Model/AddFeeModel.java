package Model;

import java.sql.Date;

public class AddFeeModel {
    private String FeeID;
    private String staffID;
    private String feetype;
    private int amount;

    // Getter and setter for registrationID
    public String getFeeID() {
        return FeeID;
    }
    public void setFeeID(String FeeID) {
        this.FeeID = FeeID;
    }

    // Getter and setter for staffID
    public String getStaffID() {
        return staffID;
    }
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    // Getter and setter for hostelType
    public String getFeeType() {
        return feetype;
    }
    public void setFeeType(String feetype) {
        this.feetype = feetype;
    }

    // Getter and setter for registrationStatus
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
}
