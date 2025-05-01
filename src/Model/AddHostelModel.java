package Model;


public class AddHostelModel {
	private String hostel_id;
	private String staffID;
	private String hostel_name;
	private int room_capacity;
	private String hostel_category;
	private String hostel_address;
    private String staff_ID;
	
	
	public String getHostel_id() {
		return hostel_id;
	}
	public void setHostel_id(String hostel_id) {
		this.hostel_id = hostel_id;
	}
	// Getter and setter for staffID
    public String getStaffID() {
        return staffID;
    }
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
	public String getHostel_name() {
		return hostel_name;
	}
	public void setHostel_name(String hostel_name) {
		this.hostel_name = hostel_name;
	}
	public int getRoom_capacity() {
		return room_capacity;
	}
	public void setRoom_capacity(int room_capacity) {
		this.room_capacity = room_capacity;
	}
	public String getHostel_category() {
		return hostel_category;
	}
	public void setHostel_category(String hostel_category) {
		this.hostel_category = hostel_category;
	}
	

	// Getter
	public String getHostel_address() {
	    return hostel_address;
	}

	// Setter
	public void setHostel_address(String hostel_address) {
	    this.hostel_address = hostel_address;
	}
	public String getStaff_ID() {
		return staff_ID;
	}
	public void setStaff_ID(String staff_ID) {
		this.staff_ID = staff_ID;
	}

	
	 
}
