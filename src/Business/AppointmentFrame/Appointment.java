/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.AppointmentFrame;

import Business.UserAccount.UserAccount;
import java.util.Date;
import java.util.List;

/**
 *
 * @author divya
 */
public class Appointment {
    private Date appointmentDate;
    private int appointmentID;
    private String requestID;
    private String fosterFamilyName;
    private UserAccount userAccount;
    private int[] appointmentTime;  
    private List<Appointment> appointments;
    private AppointmentInspectionDetails appointmentInspectionDetails;
    boolean medicalAppointment;

    public AppointmentInspectionDetails getAppointmentInspectionDetails() {
        return appointmentInspectionDetails;
    }

    public void setAppointmentInspectionDetails(AppointmentInspectionDetails appointmentInspectionDetails) {
        this.appointmentInspectionDetails = appointmentInspectionDetails;
    }
    
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getFosterFamilyName() {
        return fosterFamilyName;
    }

    public void setFosterFamilyName(String fosterFamilyName) {
        this.fosterFamilyName = fosterFamilyName;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public int[] getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(int[] appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void scheduleAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    public boolean isMedicalAppointment() {
        return medicalAppointment;
    }

    public void setMedicalAppointment(boolean medicalAppointment) {
        this.medicalAppointment = medicalAppointment;
    }
    
    
}
