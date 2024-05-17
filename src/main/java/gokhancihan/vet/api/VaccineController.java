package gokhancihan.vet.api;

import gokhancihan.vet.business.IVaccineService;
import gokhancihan.vet.dto.request.VaccineRequest;
import gokhancihan.vet.dto.response.VaccineResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/vaccine")
public class VaccineController {

    @Autowired
    private IVaccineService vaccineService;

    @GetMapping("/{id}")
    public VaccineResponse getById(@PathVariable("id") Long id) {
        return vaccineService.getById(id);
    }

    @PostMapping()
    public VaccineResponse create(@Valid @RequestBody VaccineRequest vaccineRequest) {
        return vaccineService.create(vaccineRequest);
    }

    @PutMapping("/{id}")
    public VaccineResponse update(@PathVariable("id") Long id, @Valid @RequestBody VaccineRequest vaccineRequest) {
        return vaccineService.update(id, vaccineRequest);
    }
}
