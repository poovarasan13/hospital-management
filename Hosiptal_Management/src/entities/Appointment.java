package entities;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;

public class Appointment {

	
	private int appointment_id;
	private int patient_id;
	private int staff_id;
	private Date date;
	
	private String status;
	public Appointment() {
		super();
	}
	
	
	public Appointment(int appointment_id, int patient_id, int staff_id, Date date, String status) {
		super();
		this.appointment_id = appointment_id;
		this.patient_id = patient_id;
		this.staff_id = staff_id;
		this.date = date;
		
		this.status = status;
	}


	public int getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public int getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
