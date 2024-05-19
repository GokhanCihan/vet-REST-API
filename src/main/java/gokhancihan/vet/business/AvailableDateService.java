package gokhancihan.vet.business;

import gokhancihan.vet.dto.request.AvailableDateRequest;
import gokhancihan.vet.dto.response.AvailableDateResponse;
import gokhancihan.vet.entity.AvailableDate;
import gokhancihan.vet.repository.AvailableDateRepository;
import gokhancihan.vet.utility.exception.NotFoundException;
import gokhancihan.vet.utility.exception.RedundantDataException;
import gokhancihan.vet.utility.mapper.AvailableDateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailableDateService implements IAvailableDateService {

    @Autowired
    private AvailableDateRepository availableDateRepository;
    @Autowired
    private AvailableDateMapper availableDateMapper;


    @Override
    public AvailableDateResponse getById(Long id) {
        return availableDateMapper.toResponse(availableDateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No date with id = " + id + " found!")));
    }

    @Override
    public List<AvailableDateResponse> getAll() {
        return availableDateMapper.toResponses(availableDateRepository.findAll());
    }

    // create new date not assigned to any veterinarian
    @Override
    public AvailableDateResponse create(AvailableDateRequest availableDateRequest) {
        Optional<AvailableDate> dateFromDb = availableDateRepository
                .findByAvailableDate(availableDateRequest.getAvailableDate());
        if (dateFromDb.isPresent()) {
            throw new RedundantDataException("Available date data redundant!");
        }
        AvailableDate dateToSave = availableDateMapper.fromRequest(availableDateRequest);
        availableDateRepository.save(dateToSave);
        Optional<AvailableDate> savedDateFromDb = availableDateRepository
                .findByAvailableDate(availableDateRequest.getAvailableDate());
        if (savedDateFromDb.isEmpty()) {
            throw new RuntimeException("Couldn't fetch saved date or save unsuccessful!");
        }
        return availableDateMapper.toResponse(savedDateFromDb.get());
    }

    @Override
    public AvailableDateResponse update(Long id, AvailableDateRequest availableDateRequest) {
        Optional<AvailableDate> dateFromDb = availableDateRepository.findById(id);
        if (dateFromDb.isEmpty()) {
            throw new NotFoundException("No date with id = " + id + " found!");
        }
        AvailableDate availableDate = dateFromDb.get();
        availableDateMapper.update(availableDate, availableDateRequest);
        availableDateRepository.save(availableDate);
        Optional<AvailableDate> updatedDateFromDb = availableDateRepository
                .findByAvailableDate(availableDateRequest.getAvailableDate());
        if (updatedDateFromDb.isEmpty()) {
            throw new RuntimeException("Couldn't fetch date or update unsuccessful!");
        }
        availableDate.setId(updatedDateFromDb.get().getId());
        return availableDateMapper.toResponse(availableDate);
    }

    @Override
    public void delete(Long id) {
        availableDateRepository.delete(availableDateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No date with id = " + id + " found!")));
    }
}
