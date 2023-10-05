package ucll.da.appointmentservice.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.ucll.da.appointmentservice.client.doctor.api.DoctorApi;

@Component
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorApi doctorApi;


    public void createAppointment(Appointment data){
        InstanceInfo
    }
}
