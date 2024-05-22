package gokhancihan.vet.api;

import gokhancihan.vet.business.IAvailableDateService;
import gokhancihan.vet.dto.request.AvailableDateRequest;
import gokhancihan.vet.dto.response.AvailableDateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/availableDate")
public class AvailableDateController {

    @Autowired
    public IAvailableDateService availableDateService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDateResponse getById(@PathVariable("id") Long id) {
        return availableDateService.getById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDateResponse> getAll() {
        return availableDateService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AvailableDateResponse create(@RequestBody AvailableDateRequest availableDateRequest) {
        return availableDateService.create(availableDateRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDateResponse update(@PathVariable("id") Long id,
                                        @RequestBody AvailableDateRequest availableDateRequest) {
        return availableDateService.update(id, availableDateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        availableDateService.delete(id);
    }
}
