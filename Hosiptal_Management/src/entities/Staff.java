package entities;

public class Staff {
private int staff_id ;
private String name;
private String role;
private int department_id;


public Staff() {
	super();
}


public Staff(int staff_id, String name, String role, int department_id) {
	super();
	this.staff_id = staff_id;
	this.name = name;
	this.role = role;
	this.department_id = department_id;
}


public int getStaff_id() {
	return staff_id;
}


public void setStaff_id(int staff_id) {
	this.staff_id = staff_id;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public String getRole() {
	return role;
}


public void setRole(String role) {
	this.role = role;
}


public int getDepartment_id() {
	return department_id;
}


public void setDepartment_id(int department_id) {
	this.department_id = department_id;
}


@Override
public String toString() {
	return "Staff [staff_id=" + staff_id + ", name=" + name + ", role=" + role + ", department_id=" + department_id
			+ "]";
}




}
