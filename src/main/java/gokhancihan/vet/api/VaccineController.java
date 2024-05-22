package gokhancihan.vet.api;

import gokhancihan.vet.business.IVaccineService;
import gokhancihan.vet.dto.request.VaccineRequest;
import gokhancihan.vet.dto.response.VaccineResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/vaccine")
@Tag(name = "Vaccination Management")
public class VaccineController {

    @Autowired
    private IVaccineService vaccineService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve vaccine information")
    public VaccineResponse getById(@PathVariable("id") Long id) {
        return vaccineService.getById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve list of all vaccinations")
    public List<VaccineResponse> getAll(){
        return vaccineService.getAll();
    }

    @GetMapping("/all/animalId={animalId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve a list of all vaccinations administered to an animal")
    public List<VaccineResponse> getAllByAnimalId(@PathVariable("animalId") Long animalId) {
        return vaccineService.getAllByAnimalId(animalId);
    }

    @GetMapping("/protection/startDate={startDate}&endDate={endDate}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve list of all vaccinations of which protection date expires within the given date range")
    public List<VaccineResponse> getAllByProtectionDate(
            @PathVariable("startDate") LocalDate startDate,
            @PathVariable("endDate") LocalDate endDate) {
        return vaccineService.getAllByProtectionDate(startDate, endDate);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create vaccination information")
    public VaccineResponse create(@Valid @RequestBody VaccineRequest vaccineRequest) {
        return vaccineService.create(vaccineRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update vaccination information")
    public VaccineResponse update(@PathVariable("id") Long id, @Valid @RequestBody VaccineRequest vaccineRequest) {
        return vaccineService.update(id, vaccineRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete vaccination information")
    public void delete(@PathVariable Long id) {
        vaccineService.delete(id);
    }
}
