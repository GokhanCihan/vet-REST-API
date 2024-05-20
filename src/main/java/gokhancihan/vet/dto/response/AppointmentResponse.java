package gokhancihan.vet.dto.response;

import gokhancihan.vet.entity.Animal;
import gokhancihan.vet.entity.Veterinarian;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class AppointmentResponse {
    private Long id;
    private LocalDateTime appointmentDate;
    private Veterinarian veterinarian;
    private Animal animal;
}
