package gokhancihan.vet.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;

@Setter
@Getter
@RequestMapping
public class VeterinarianAvailableDateRequest {
    private Long veterinarianId;
    private Long availableDateId;
}
