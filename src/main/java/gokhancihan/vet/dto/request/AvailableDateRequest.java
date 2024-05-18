package gokhancihan.vet.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class AvailableDateRequest {

    @NotNull
    @UniqueElements
    private LocalDate availableDate;

}
