package gokhancihan.business;

import gokhancihan.dto.request.CustomerRequest;
import gokhancihan.dto.response.CustomerResponse;
import gokhancihan.repository.CustomerRepository;
import gokhancihan.utility.exception.NotFoundException;
import gokhancihan.utility.exception.RedundantDataException;
import gokhancihan.utility.mapper.CustomerMapper;
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
        return CustomerMapper.MAPPER.toResponse(customerFromDb.get());
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest customerRequest) {
        Optional<Customer> customerFromDb = customerRepository.findById(id);
        if (customerFromDb.isEmpty()) {
            throw new NotFoundException("No customer with id = " + id + " found!");
        }
        Customer customer = customerFromDb.get();
        CustomerMapper.MAPPER.update(customer, customerRequest);
        return CustomerMapper.MAPPER.toResponse(customerRepository.save(customer));
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
