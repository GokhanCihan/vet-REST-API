package gokhancihan.vet.api;

import gokhancihan.vet.business.IVaccineService;
import gokhancihan.vet.dto.request.VaccineRequest;
import gokhancihan.vet.dto.response.VaccineResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/vaccine")
public class VaccineController {

    @Autowired
    private IVaccineService vaccineService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VaccineResponse getById(@PathVariable("id") Long id) {
        return vaccineService.getById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<VaccineResponse> getAll(){
        return vaccineService.getAll();
    }

    @GetMapping("/all/animalId={animalId}")
    @ResponseStatus(HttpStatus.OK)
    public List<VaccineResponse> getAllByAnimalId(@PathVariable("animalId") Long animalId) {
        return vaccineService.getAllByAnimalId(animalId);
    }

    @GetMapping("/protection/startDate={startDate}&endDate={endDate}")
    @ResponseStatus(HttpStatus.OK)
    public List<VaccineResponse> getAllByProtectionDate(
            @PathVariable("startDate") LocalDate startDate,
            @PathVariable("endDate") LocalDate endDate) {
        return vaccineService.getAllByProtectionDate(startDate, endDate);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public VaccineResponse create(@Valid @RequestBody VaccineRequest vaccineRequest) {
        return vaccineService.create(vaccineRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VaccineResponse update(@PathVariable("id") Long id, @Valid @RequestBody VaccineRequest vaccineRequest) {
        return vaccineService.update(id, vaccineRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        vaccineService.delete(id);
    }
}
