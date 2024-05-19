package gokhancihan.vet.api;

import gokhancihan.vet.business.IVeterinarianService;
import gokhancihan.vet.dto.request.VeterinarianRequest;
import gokhancihan.vet.dto.response.VeterinarianResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/veterinarian")
public class VeterinarianController {

    @Autowired
    private IVeterinarianService veterinarianService;

    @GetMapping("/{id}")
    public VeterinarianResponse getById(@PathVariable("id") Long id) {
        return veterinarianService.getById(id);
    }

    @GetMapping("/all")
    public List<VeterinarianResponse> getAll() {
        return veterinarianService.getAll();
    }

    @PostMapping()
    public VeterinarianResponse create(@Valid @RequestBody VeterinarianRequest veterinarianRequest) {
        return veterinarianService.create(veterinarianRequest);
    }

    @PutMapping("/{id}")
    public VeterinarianResponse update(
            @PathVariable("id") Long id,
            @Valid @RequestBody VeterinarianRequest veterinarianRequest) {
        return veterinarianService.update(id, veterinarianRequest);
    }

    @PutMapping("/{veterinarianId}/availableDate/{DateId}/")
    public VeterinarianResponse createFor(@PathVariable("veterinarianId") Long id,
                                           @PathVariable("DateId") Long availableDateId) {
        return veterinarianService.addAvailableDate(id, availableDateId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        veterinarianService.delete(id);
    }

}
