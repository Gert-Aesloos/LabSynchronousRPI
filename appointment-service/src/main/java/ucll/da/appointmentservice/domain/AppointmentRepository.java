package ucll.da.appointmentservice.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment,Long> {
    List<Appointment> getAppointmentByDoctorIdAndPlannedDate(Long doctor, LocalDate date);
}
