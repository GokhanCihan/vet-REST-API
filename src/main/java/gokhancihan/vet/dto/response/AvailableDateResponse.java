package gokhancihan.vet.dto.response;

import gokhancihan.vet.entity.Veterinarian;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
public class AvailableDateResponse {

    private LocalDate availableDate;

    private List<Veterinarian> veterinarians;

}
