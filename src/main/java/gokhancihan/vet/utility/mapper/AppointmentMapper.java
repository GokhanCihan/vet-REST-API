package gokhancihan.vet.utility.mapper;

import gokhancihan.vet.dto.request.AppointmentRequest;
import gokhancihan.vet.dto.response.AppointmentResponse;
import gokhancihan.vet.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {AnimalMapper.class, VeterinarianMapper.class})
public interface AppointmentMapper {

    @Mapping(target = "veterinarian.availableDates", ignore = true)
    @Mapping(target = "veterinarian.appointments", ignore = true)
    @Mapping(target = "animal.customer", ignore = true)
    @Mapping(target = "animal.vaccines", ignore = true)
    @Mapping(target = "animal.appointments", ignore = true)
    @Mapping(target = "animal", source = "animal")
    @Mapping(target = "veterinarian", source = "veterinarian")
    AppointmentResponse toResponse(Appointment appointment);

    @Mapping(target = "animal.id", source = "animalId")
    @Mapping(target = "veterinarian.id", source = "veterinarianId")
    Appointment fromRequest(AppointmentRequest appointmentRequest);

    List<AppointmentResponse> toResponses(List<Appointment> appointments);

    void update(@MappingTarget Appointment appointment, AppointmentRequest appointmentRequest);

}
