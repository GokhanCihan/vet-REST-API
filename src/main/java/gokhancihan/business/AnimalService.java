package gokhancihan.business;

import gokhancihan.dto.request.AnimalRequest;
import gokhancihan.dto.response.AnimalResponse;
import gokhancihan.repository.AnimalRepository;
import gokhancihan.repository.CustomerRepository;
import gokhancihan.utility.exception.NotFoundException;
import gokhancihan.utility.exception.RedundantDataException;
import gokhancihan.utility.mapper.AnimalMapper;
import gokhancihan.vet.entity.Animal;
import gokhancihan.vet.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService implements IAnimalService {
    private final AnimalRepository animalRepository;
    private final CustomerRepository customerRepository;

    public AnimalService(AnimalRepository animalRepository, CustomerRepository customerRepository) {
        this.animalRepository = animalRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public AnimalResponse getById(Long id) {
        return AnimalMapper.MAPPER.toResponse(animalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No animal with id = " + id + " found!")));
    }

    @Override
    public AnimalResponse getByName(String name) {
        return AnimalMapper.MAPPER.toResponse(animalRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("No animal with name = " + name + " found!")));
    }

    @Override
    public List<AnimalResponse> getAllByCustomerName(String customerName) {
        Optional<Customer> customer = customerRepository.findByName(customerName);
        if (customer.isEmpty()) {
            throw new NotFoundException("No customer with name = " + customerName + " found!");
        }
        return AnimalMapper.MAPPER.toResponses(animalRepository.findByCustomerId(customer.get().getId()));
    }

    @Override
    public List<AnimalResponse> getAll() {
        return AnimalMapper.MAPPER.toResponses(animalRepository.findAll());
    }

    @Override
    public AnimalResponse create(AnimalRequest animalRequest) {
        Optional<Animal> animalFromDb = animalRepository.findByNameAndCustomerId(animalRequest.getName(), animalRequest.getCustomerId());
        if (animalFromDb.isPresent()) {
            throw new RedundantDataException("Animal data redundant!");
        }
        Animal animal = AnimalMapper.MAPPER.fromRequest(animalRequest);
        return AnimalMapper.MAPPER.toResponse(animalRepository.save(animal));
    }

    @Override
    public AnimalResponse update(Long id, AnimalRequest animalRequest) {
        Optional<Animal> animalFromDb = animalRepository.findById(id);
        if (animalFromDb.isEmpty()) {
            throw new NotFoundException("No animal with id = " + id + " found!");
        }
        Animal animal = animalFromDb.get();
        AnimalMapper.MAPPER.update(animal, animalRequest);
        return AnimalMapper.MAPPER.toResponse(animalRepository.save(animal));
    }

    @Override
    public void delete(Long id) {
        Optional<Animal> animalFromDb = animalRepository.findById(id);
        if (animalFromDb.isEmpty()) {
            throw new NotFoundException("No animal with id = " + id + " found!");
        }
        animalRepository.delete(animalFromDb.get());
    }
}
