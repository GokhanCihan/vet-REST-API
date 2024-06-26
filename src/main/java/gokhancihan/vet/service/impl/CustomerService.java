package gokhancihan.vet.service.impl;

import gokhancihan.vet.service.ICustomerService;
import gokhancihan.vet.dto.request.CustomerRequest;
import gokhancihan.vet.dto.response.CustomerResponse;
import gokhancihan.vet.repository.CustomerRepository;
import gokhancihan.vet.utility.exception.NotFoundException;
import gokhancihan.vet.utility.exception.RedundantDataException;
import gokhancihan.vet.utility.mapper.CustomerMapper;
import gokhancihan.vet.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponse getById(Long id) {
        return customerMapper.toResponse(customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer data with id = " + id + " not found!")));
    }

    @Override
    public CustomerResponse getByName(String name) {
        return customerMapper.toResponse(customerRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Customer data with name = " + name + " not found!")));
    }

    @Override
    public List<CustomerResponse> getAll() {
        return customerMapper.toResponses(customerRepository.findAll());
    }

    @Override
    public CustomerResponse create(CustomerRequest customerRequest) {
        Optional<Customer> customerFromDb = customerRepository
                .findByNameAndMail(customerRequest.getName(), customerRequest.getMail());
        if (customerFromDb.isPresent()) {
            throw new RedundantDataException("Customer data redundant!");
        }
        Customer customerToSave = customerMapper.fromRequest(customerRequest);
        customerRepository.save(customerToSave);
        Optional<Customer> savedCustomerFromDb = customerRepository
                .findByNameAndMail(customerRequest.getName(), customerRequest.getMail());
        if (savedCustomerFromDb.isEmpty()) {
            throw new RuntimeException("Couldn't fetch customer data or save was unsuccessful!");
        }
        return customerMapper.toResponse(savedCustomerFromDb.get());
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest customerRequest) {
        Optional<Customer> customerFromDb = customerRepository.findById(id);
        if (customerFromDb.isEmpty()) {
            throw new NotFoundException("Customer data with id = " + id + " not found!");
        }
        Customer customer = customerFromDb.get();
        customerMapper.update(customer, customerRequest);
        customerRepository.save(customer);
        Optional<Customer> updatedCustomerFromDb = customerRepository
                .findByNameAndMail(customerRequest.getName(), customerRequest.getMail());
        if (updatedCustomerFromDb.isEmpty()) {
            throw new RuntimeException("Couldn't fetch customer data or update was unsuccessful!");
        }
        customer.setId(updatedCustomerFromDb.get().getId());
        return customerMapper.toResponse(customer);
    }

    @Override
    public void delete(Long id) {
        Optional<Customer> customerFromDb = customerRepository.findById(id);
        if (customerFromDb.isEmpty()) {
            throw new NotFoundException("Customer data with id = " + id + " not found!");
        }
        customerRepository.delete(customerFromDb.get());
    }
}
