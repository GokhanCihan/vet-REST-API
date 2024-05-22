package gokhancihan.vet.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class AvailableDateRequest {

    @NotNull
    @UniqueElements
    private LocalDate availableDate;

}
