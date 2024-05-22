package gokhancihan.vet.api;

import gokhancihan.vet.business.IAnimalService;
import gokhancihan.vet.dto.request.AnimalRequest;
import gokhancihan.vet.dto.response.AnimalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/animal")
public class AnimalController {

    @Autowired
    private IAnimalService animalService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse getById(@PathVariable("id") Long id) {
        return this.animalService.getById(id);
    }

    @GetMapping("/name={name}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse getByName(@PathVariable("name") String name) {
        return this.animalService.getByName(name);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalResponse> getAll() {
        return this.animalService.getAll();
    }

    @GetMapping("/all/customerId={customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalResponse> getAllByCustomerId(@PathVariable("customerId") Long customerId) {
        return this.animalService.getAllByCustomerId(customerId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalResponse create(@Valid @RequestBody AnimalRequest animalRequest) {
        return this.animalService.create(animalRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse update(@PathVariable("id") Long id, @Valid @RequestBody AnimalRequest animalRequest) {
        return this.animalService.update(id, animalRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        this.animalService.delete(id);
    }
}
