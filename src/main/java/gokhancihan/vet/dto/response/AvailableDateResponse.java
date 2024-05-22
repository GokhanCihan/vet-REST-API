package gokhancihan.vet.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class AvailableDateResponse {

    private Long id;

    private LocalDate availableDate;

}
