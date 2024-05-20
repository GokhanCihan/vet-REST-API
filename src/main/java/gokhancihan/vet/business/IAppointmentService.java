package gokhancihan.vet.business;

import gokhancihan.vet.dto.request.AppointmentRequest;
import gokhancihan.vet.dto.response.AppointmentResponse;

import java.util.List;

public interface IAppointmentService {
    AppointmentResponse getById(Long id);

    List<AppointmentResponse> getAll();

    AppointmentResponse create(AppointmentRequest appointmentRequest);

    AppointmentResponse update(Long id, AppointmentRequest appointmentRequest);

    void delete(Long id);
}
