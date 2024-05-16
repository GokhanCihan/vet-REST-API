package gokhancihan.vet.business;

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

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse getById(Long id) {
        return CustomerMapper.MAPPER.toResponse(customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No customer with id = " + id + " found!")));
    }

    @Override
    public CustomerResponse getByName(String name) {
        return CustomerMapper.MAPPER.toResponse(customerRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("No customer with name = " + name + " found!")));
    }

    @Override
    public List<CustomerResponse> getAll() {
        return CustomerMapper.MAPPER.toResponses(customerRepository.findAll());
    }

    @Override
    public CustomerResponse create(CustomerRequest customerRequest) {
        Optional<Customer> customerFromDb = customerRepository
                .findByNameAndMail(customerRequest.getName(), customerRequest.getMail());
        if (customerFromDb.isPresent()) {
            throw new RedundantDataException("Customer data redundant!");
        }
        Customer customerToSave = CustomerMapper.MAPPER.fromRequest(customerRequest);
        customerRepository.save(customerToSave);
        Optional<Customer> savedCustomerFromDb = customerRepository
                .findByNameAndMail(customerRequest.getName(), customerRequest.getMail());
        if (savedCustomerFromDb.isEmpty()){
            throw new RuntimeException("Couldn't fetch saved customer or save unsuccessful!");
        }
        return CustomerMapper.MAPPER.toResponse(savedCustomerFromDb.get());
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest customerRequest) {
        Optional<Customer> customerFromDb = customerRepository.findById(id);
        if (customerFromDb.isEmpty()) {
            throw new NotFoundException("No customer with id = " + id + " found!");
        }
        Customer customer = customerFromDb.get();
        CustomerMapper.MAPPER.update(customer, customerRequest);
        customerRepository.save(customer);
        Optional<Customer> updatedCustomerFromDb = customerRepository
                .findByNameAndMail(customerRequest.getName(), customerRequest.getMail());
        if (updatedCustomerFromDb.isEmpty()){
            throw new RuntimeException("Couldn't fetch saved customer or update unsuccessful!");
        }
        customer.setId(updatedCustomerFromDb.get().getId());
        return CustomerMapper.MAPPER.toResponse(customer);
    }

    @Override
    public void delete(Long id) {
        Optional<Customer> customerFromDb = customerRepository.findById(id);
        if (customerFromDb.isEmpty()) {
            throw new NotFoundException("No customer with id = " + id + " found!");
        }
        customerRepository.delete(customerFromDb.get());
    }
}
