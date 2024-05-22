package gokhancihan.vet.api;

import gokhancihan.vet.business.ICustomerService;
import gokhancihan.vet.dto.request.CustomerRequest;
import gokhancihan.vet.dto.response.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    @Autowired
    public ICustomerService customerService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getById(@PathVariable("id") Long id) {
        return this.customerService.getById(id);
    }

    @GetMapping("/name={name}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getByName(@PathVariable("name") String name) {
        return this.customerService.getByName(name);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> getAll() {
        return this.customerService.getAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse create(@Valid @RequestBody CustomerRequest customerRequest) {
        return this.customerService.create(customerRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse update(@PathVariable("id") Long id, @Valid @RequestBody CustomerRequest customerRequest) {
        return this.customerService.update(id, customerRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        this.customerService.delete(id);
    }

}
