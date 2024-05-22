package gokhancihan.vet.api;

import gokhancihan.vet.business.IAvailableDateService;
import gokhancihan.vet.dto.request.AvailableDateRequest;
import gokhancihan.vet.dto.response.AvailableDateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/availableDate")
@Tag(name = "Available Date Management")
public class AvailableDateController {

    public final IAvailableDateService availableDateService;

    public AvailableDateController(IAvailableDateService availableDateService) {
        this.availableDateService = availableDateService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve available date information")
    public AvailableDateResponse getById(@PathVariable("id") Long id) {
        return availableDateService.getById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve list of all available dates")
    public List<AvailableDateResponse> getAll() {
        return availableDateService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create an available date")
    public AvailableDateResponse create(@RequestBody AvailableDateRequest availableDateRequest) {
        return availableDateService.create(availableDateRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update available date information")
    public AvailableDateResponse update(@PathVariable("id") Long id,
                                        @RequestBody AvailableDateRequest availableDateRequest) {
        return availableDateService.update(id, availableDateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete an available date")
    public void delete(@PathVariable("id") Long id) {
        availableDateService.delete(id);
    }
}
