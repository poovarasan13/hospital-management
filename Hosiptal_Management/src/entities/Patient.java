package entities;

import java.util.Date;

public class Patient {

	private int patient_id ;
	private String name;
	private int age;
	private String gender;
	private int contact_information;
	private Date join_date;
	private String disease;
	private  String medical_history;
	
	
	public Patient() {
		super();
	}


	public Patient(int patient_id, String name, int age, String gender, int contact_information, String disease,
			String medical_history, Date join_date) {
		super();
		this.patient_id = patient_id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.join_date=join_date;
		this.contact_information = contact_information;
		this.disease = disease;
		this.medical_history = medical_history;
	}


	public int getPatient_id() {
		return patient_id;
	}


	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public int getContact_information() {
		return contact_information;
	}


	public void setContact_information(int contact_information) {
		this.contact_information = contact_information;
	}


	public String getDisease() {
		return disease;
	}


	public void setDisease(String disease) {
		this.disease = disease;
	}


	public String getMedical_history() {
		return medical_history;
	}


	public void setMedical_history(String medical_history) {
		this.medical_history = medical_history;
	}


	
	public Date getJoin_date() {
        return join_date;
    }

    public void setJoin_date(Date join_date) {
        this.join_date = join_date;
    }

	
	
    public String toString() {
        return "Patient ID: " + patient_id + ", Name: " + name + ", Age: " + age + ", Gender: " + gender
                + ", Contact Information: " + contact_information + ", Join Date: " + join_date
                + ", Disease: " + disease + ", Medical History: " + medical_history;
    }
	

}
