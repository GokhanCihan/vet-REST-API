package gokhancihan.vet.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class AppointmentRequest {
    @NotNull
    private LocalDateTime appointmentDate;
    @NotNull
    private Long veterinarianId;
    @NotNull
    private Long animalId;
}
