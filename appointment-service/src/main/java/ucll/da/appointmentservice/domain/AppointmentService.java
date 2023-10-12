package ucll.da.appointmentservice.domain;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

import ucll.da.appointmentservice.client.doctor.api.DoctorApi;
import ucll.da.appointmentservice.client.doctor.model.Doctor;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorApi doctorApi;

    @Autowired
    private EurekaClient discoveryClient;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;


    public void addAppointment(Appointment appointment){
        InstanceInfo instanceInfo = discoveryClient.getNextServerFromEureka("doctor-service",false);
        doctorApi.getApiClient().setBasePath(instanceInfo.getHomePageUrl());

        List<Doctor> doctorList = circuitBreakerFactory.create("doctorApi").run(()-> doctorApi.getDoctors(appointment.getNeededExpertise()),throwable -> new ArrayList<>());

        Doctor doctor = null;
        for (Doctor d: doctorList) {
            List<Appointment> appointmentList = appointmentRepository.getAppointmentByDoctorIdAndPlannedDate(d.getId(), appointment.getPlannedDate());

            if (appointmentList.isEmpty()) {
                doctor = d;
            }
        }

        try {
            Appointment newAppointment = new Appointment(appointment.getFirstName(), appointment.getLastName(), appointment.getPlannedDate(), appointment.getNeededExpertise(), doctor.getId());
            appointmentRepository.save(newAppointment);
        } catch (Exception e) {
            throw new RuntimeException("e");
        }

    }
}
