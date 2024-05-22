package gokhancihan.vet.business.impl;

import gokhancihan.vet.business.IAvailableDateService;
import gokhancihan.vet.dto.request.AvailableDateRequest;
import gokhancihan.vet.dto.response.AvailableDateResponse;
import gokhancihan.vet.entity.AvailableDate;
import gokhancihan.vet.repository.AvailableDateRepository;
import gokhancihan.vet.repository.VeterinarianRepository;
import gokhancihan.vet.utility.exception.NotFoundException;
import gokhancihan.vet.utility.exception.RedundantDataException;
import gokhancihan.vet.utility.mapper.AvailableDateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AvailableDateService implements IAvailableDateService {

    @Autowired
    private AvailableDateRepository dateRepository;
    @Autowired
    private AvailableDateMapper availableDateMapper;
    @Autowired
    private VeterinarianRepository veterinarianRepository;


    @Override
    public AvailableDateResponse getById(Long id) {
        return availableDateMapper.toResponse(dateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Available date with id = " + id + " not found!")));
    }

    @Override
    public List<AvailableDateResponse> getAll() {
        return availableDateMapper.toResponses(dateRepository.findAll());
    }

    // create new date not assigned to any veterinarian
    @Override
    public AvailableDateResponse create(AvailableDateRequest availableDateRequest) {
        Optional<AvailableDate> dateFromDb = dateRepository
                .findByAvailableDate(availableDateRequest.getAvailableDate());
        if (dateFromDb.isPresent()) {
            throw new RedundantDataException("Available date data redundant!");
        }
        AvailableDate dateToSave = availableDateMapper.fromRequest(availableDateRequest);
        dateRepository.save(dateToSave);
        Optional<AvailableDate> savedDateFromDb = dateRepository
                .findByAvailableDate(availableDateRequest.getAvailableDate());
        if (savedDateFromDb.isEmpty()) {
            throw new RuntimeException("Couldn't fetch available date data or save unsuccessful!");
        }
        return availableDateMapper.toResponse(savedDateFromDb.get());
    }

    @Override
    public AvailableDateResponse update(Long id, AvailableDateRequest availableDateRequest) {
        Optional<AvailableDate> dateFromDb = dateRepository.findById(id);
        if (dateFromDb.isEmpty()) {
            throw new NotFoundException("Available date data with id = " + id + " not found!");
        }
        AvailableDate availableDate = dateFromDb.get();
        availableDateMapper.update(availableDate, availableDateRequest);
        dateRepository.save(availableDate);
        Optional<AvailableDate> updatedDateFromDb = dateRepository
                .findByAvailableDate(availableDateRequest.getAvailableDate());
        if (updatedDateFromDb.isEmpty()) {
            throw new RuntimeException("Couldn't fetch available date data or update unsuccessful!");
        }
        availableDate.setId(updatedDateFromDb.get().getId());
        return availableDateMapper.toResponse(availableDate);
    }

    @Override
    public void delete(Long id) {
        dateRepository.delete(dateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Available date with id = " + id + " not found!")));
    }
}
