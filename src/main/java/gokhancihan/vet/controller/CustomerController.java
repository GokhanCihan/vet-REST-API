package gokhancihan.vet.controller;

import gokhancihan.vet.service.ICustomerService;
import gokhancihan.vet.dto.request.CustomerRequest;
import gokhancihan.vet.dto.response.CustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
@Tag(name = "Customer Management")
public class CustomerController {

    public final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve information of a customer")
    public CustomerResponse getById(@PathVariable("id") Long id) {
        return this.customerService.getById(id);
    }

    @GetMapping("/name={name}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve information of a customer")
    public CustomerResponse getByName(@PathVariable("name") String name) {
        return this.customerService.getByName(name);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve list of all customers")
    public List<CustomerResponse> getAll() {
        return this.customerService.getAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create customer information")
    public CustomerResponse create(@Valid @RequestBody CustomerRequest customerRequest) {
        return this.customerService.create(customerRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update customer information")
    public CustomerResponse update(@PathVariable("id") Long id, @Valid @RequestBody CustomerRequest customerRequest) {
        return this.customerService.update(id, customerRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete customer")
    public void delete(@PathVariable("id") Long id) {
        this.customerService.delete(id);
    }

}
