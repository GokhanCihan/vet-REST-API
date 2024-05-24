package gokhancihan.vet.controller;

import gokhancihan.vet.service.IAnimalService;
import gokhancihan.vet.dto.request.AnimalRequest;
import gokhancihan.vet.dto.response.AnimalResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/animal")
@Tag(name = "Animal Management")
public class AnimalController {

    private final IAnimalService animalService;

    public AnimalController(IAnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve animal information by animal id")
    public AnimalResponse getById(@PathVariable("id") Long id) {
        return this.animalService.getById(id);
    }

    @GetMapping("/name={name}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve animal information for given animal name")
    public AnimalResponse getByName(@PathVariable("name") String name) {
        return this.animalService.getByName(name);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve list of all animals")
    public List<AnimalResponse> getAll() {
        return this.animalService.getAll();
    }

    @GetMapping("/all/customerId={customerId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve list of all animals owned by a customer")
    public List<AnimalResponse> getAllByCustomerId(@PathVariable("customerId") Long customerId) {
        return this.animalService.getAllByCustomerId(customerId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save new animal information in database", description = "Requires customerId")
    public AnimalResponse create(@Valid @RequestBody AnimalRequest animalRequest) {
        return this.animalService.create(animalRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update animal information")
    public AnimalResponse update(@PathVariable("id") Long id, @Valid @RequestBody AnimalRequest animalRequest) {
        return this.animalService.update(id, animalRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete animal information")
    public void delete(@PathVariable("id") Long id) {
        this.animalService.delete(id);
    }
}
