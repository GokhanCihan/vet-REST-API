package gokhancihan.vet.api;

import gokhancihan.vet.business.IAvailableDateService;
import gokhancihan.vet.dto.request.AvailableDateRequest;
import gokhancihan.vet.dto.response.AvailableDateResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/available_date")
public class AvailableDateController {

    @Autowired
    public IAvailableDateService availableDateService;

    @GetMapping("/{id}")
    public AvailableDateResponse getById(@PathVariable("id") Long id) {
        return availableDateService.getById(id);
    }

    @GetMapping("/all")
    public List<AvailableDateResponse> getAll() {
        return availableDateService.getAll();
    }

    @PostMapping()
    public AvailableDateResponse create(@RequestBody AvailableDateRequest availableDateRequest) {
        return availableDateService.create(availableDateRequest);
    }

    @PutMapping("/{id}")
    public AvailableDateResponse update(
            @PathVariable("id") Long id,
            @RequestBody AvailableDateRequest availableDateRequest) {
        return availableDateService.update(id, availableDateRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        availableDateService.delete(id);
    }
}
