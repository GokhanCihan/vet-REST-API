package gokhancihan.vet.business;

import gokhancihan.vet.dto.request.AppointmentRequest;
import gokhancihan.vet.dto.response.AppointmentResponse;

import java.time.LocalDate;
import java.util.List;

public interface IAppointmentService {
    AppointmentResponse getById(Long id);

    List<AppointmentResponse> getAll();

    List<AppointmentResponse> getAllInRangeForVeterinarian(Long veterinarianId, LocalDate startDate,
                                                           LocalDate endDate);

    List<AppointmentResponse> getAllInRangeForAnimal(Long AnimalId, LocalDate startDate, LocalDate endDate);

    AppointmentResponse create(AppointmentRequest appointmentRequest);

    AppointmentResponse update(Long id, AppointmentRequest appointmentRequest);

    void delete(Long id);
}
