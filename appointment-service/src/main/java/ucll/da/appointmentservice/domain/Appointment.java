package ucll.da.appointmentservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Appointment {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate date;
    private String neededExpertise;

    private Long doctorId;

    public Appointment(String firstName, String lastName, LocalDate date, String neededExpertise, Long doctorId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.neededExpertise = neededExpertise;
        this.doctorId = doctorId;
    }

    protected Appointment() {}

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getNeededExpertise() {
        return neededExpertise;
    }

    public Long getDoctorId() {
        return doctorId;
    }
}
