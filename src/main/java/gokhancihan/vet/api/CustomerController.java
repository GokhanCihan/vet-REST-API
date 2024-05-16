package gokhancihan.vet.api;

import gokhancihan.vet.business.ICustomerService;
import gokhancihan.vet.dto.request.CustomerRequest;
import gokhancihan.vet.dto.response.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    @Autowired
    public ICustomerService customerService;

    @GetMapping("/{id}")
    public CustomerResponse getById(@PathVariable("id") Long id) {
        return this.customerService.getById(id);
    }

    @GetMapping("/name={name}")
    public CustomerResponse getByName(@PathVariable("name") String name) {
        return this.customerService.getByName(name);
    }

    @GetMapping("/all")
    public List<CustomerResponse> getAll() {
        return this.customerService.getAll();
    }

    @PostMapping()
    public CustomerResponse create(@Valid @RequestBody CustomerRequest customerRequest) {
        return this.customerService.create(customerRequest);
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable("id") Long id, @Valid @RequestBody CustomerRequest customerRequest) {
        return this.customerService.update(id, customerRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.customerService.delete(id);
    }

}
