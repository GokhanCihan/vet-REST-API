package gokhancihan.vet.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class AvailableDateResponse {

    private Long id;

    private LocalDate availableDate;

}
