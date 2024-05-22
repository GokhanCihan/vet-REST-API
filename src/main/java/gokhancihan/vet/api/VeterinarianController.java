package gokhancihan.vet.api;

import gokhancihan.vet.business.IVeterinarianService;
import gokhancihan.vet.dto.request.VeterinarianAvailableDateRequest;
import gokhancihan.vet.dto.request.VeterinarianRequest;
import gokhancihan.vet.dto.response.VeterinarianResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/veterinarian")
@Tag(name = "Veterinarian Management")
public class VeterinarianController {

    @Autowired
    private IVeterinarianService veterinarianService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve veterinarian information")
    public VeterinarianResponse getById(@PathVariable("id") Long id) {
        return veterinarianService.getById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve list of all veterinarians")
    public List<VeterinarianResponse> getAll() {
        return veterinarianService.getAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create veterinarian information")
    public VeterinarianResponse create(@Valid @RequestBody VeterinarianRequest veterinarianRequest) {
        return veterinarianService.create(veterinarianRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update veterinarian information")
    public VeterinarianResponse update(
            @PathVariable("id") Long id,
            @Valid @RequestBody VeterinarianRequest veterinarianRequest) {
        return veterinarianService.update(id, veterinarianRequest);
    }

    @PutMapping("/availableDate")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Assign an available date to a veterinarian")
    public VeterinarianResponse createFor(@RequestBody VeterinarianAvailableDateRequest vetDateRequest) {
        return veterinarianService.addAvailableDate(vetDateRequest);
    }

    @DeleteMapping("/availableDate/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Remove an available date from a veterinarian")
    public VeterinarianResponse removeFrom(@RequestBody VeterinarianAvailableDateRequest vetDateRequest) {
        return veterinarianService.removeAvailableDate(vetDateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete veterinarian")
    public void delete(@PathVariable("id") Long id) {
        veterinarianService.delete(id);
    }

}
