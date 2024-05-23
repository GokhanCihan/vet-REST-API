package gokhancihan.vet.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;

@Setter
@Getter
@RequestMapping
public class VeterinarianAvailableDateRequest {
    @NotNull
    private Long veterinarianId;
    @NotNull
    private Long availableDateId;
}
