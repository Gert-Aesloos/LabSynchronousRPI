package ucll.da.appointmentservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ucll.da.appointmentservice.api.AppointmentApiDelegate;
import ucll.da.appointmentservice.domain.Appointment;
import ucll.da.appointmentservice.domain.AppointmentService;

@Component
public class AppointmentController implements AppointmentApiDelegate {
    @Autowired
    private AppointmentService appointmentService;

    public ResponseEntity<Void> addAppointment(Appointment appointment) {
        appointmentService.addAppointment(appointment);
        return ResponseEntity.ok().build();
    }
}
