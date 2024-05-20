package gokhancihan.vet.utility.mapper;

import gokhancihan.vet.dto.request.AnimalRequest;
import gokhancihan.vet.dto.request.AppointmentRequest;
import gokhancihan.vet.dto.response.AnimalResponse;
import gokhancihan.vet.dto.response.AppointmentResponse;
import gokhancihan.vet.entity.Animal;
import gokhancihan.vet.entity.Appointment;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface AppointmentMapper {

    AppointmentResponse toResponse(Appointment appointment);

    @Mapping(target = "animal.id", source = "animalId")
    @Mapping(target = "veterinarian.id", source = "veterinarianId")
    Appointment fromRequest(AppointmentRequest appointmentRequest);

    List<AppointmentResponse> toResponses(List<Appointment> appointments);

    void update(@MappingTarget Appointment appointment, AppointmentRequest appointmentRequest);

}
