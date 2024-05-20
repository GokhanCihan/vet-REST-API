package gokhancihan.vet.business;

import gokhancihan.vet.dto.request.AppointmentRequest;
import gokhancihan.vet.dto.response.AppointmentResponse;
import gokhancihan.vet.entity.Animal;
import gokhancihan.vet.entity.Appointment;
import gokhancihan.vet.entity.Veterinarian;
import gokhancihan.vet.repository.AnimalRepository;
import gokhancihan.vet.repository.AppointmentRepository;
import gokhancihan.vet.repository.VeterinarianRepository;
import gokhancihan.vet.utility.exception.NotFoundException;
import gokhancihan.vet.utility.mapper.AppointmentMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService implements IAppointmentService {

    private final AppointmentRepository appointmentRepo;
    private AppointmentMapper appointmentMapper;
    private final AnimalRepository animalRepo;
    private final VeterinarianRepository veterinarianRepo;

    public AppointmentService(AppointmentRepository appointmentRepo, AppointmentMapper appointmentMapper,
                              AnimalRepository animalRepo, VeterinarianRepository veterinarianRepo) {
        this.appointmentRepo = appointmentRepo;
        this.appointmentMapper = appointmentMapper;
        this.animalRepo = animalRepo;
        this.veterinarianRepo = veterinarianRepo;
    }

    @Override
    public AppointmentResponse getById(Long id) {
        return appointmentMapper.toResponse(appointmentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Appointment data with id = " + id + " not found!")));
    }

    @Override
    public List<AppointmentResponse> getAll() {
        return appointmentMapper.toResponses(appointmentRepo.findAll());
    }

    @Override
    public AppointmentResponse create(AppointmentRequest request) {
        // TODO: check time if it is at in an exact hour only
        // TODO: check veterinarian available date
        Optional<Animal> animalFromDb = animalRepo.findById(request.getAnimalId());
        Optional<Veterinarian> vetFromDb = veterinarianRepo.findById(request.getVeterinarianId());
        if (animalFromDb.isEmpty()) {
            throw new NotFoundException("Animal with id = " + request.getAnimalId() + " not found!");
        }
        if (vetFromDb.isEmpty()) {
            throw new NotFoundException("Veterinarian with id = " + request.getVeterinarianId() + " not found!");
        }
        appointmentRepo.findByAppointmentDateAndAnimalId(request.getAppointmentDate(), request.getAnimalId())
                .orElseThrow(() -> new RuntimeException("There is an appointment at this time for the animal"));
        appointmentRepo.findByAppointmentDateAndVeterinarianId(request.getAppointmentDate(), request.getVeterinarianId())
                .orElseThrow(() -> new RuntimeException("There is an appointment at this time for the veterinarian"));
        Appointment appointmentToSave = appointmentMapper.fromRequest(request);
        appointmentRepo.save(appointmentToSave);
        Appointment savedAppointment = appointmentRepo
                .findByAppointmentDateAndAnimalIdAndVeterinarianId(request.getAppointmentDate(), request.getAnimalId(),
                        request.getVeterinarianId())
                .orElseThrow(() -> new RuntimeException("Couldn't fetch saved data!"));
        return appointmentMapper.toResponse(savedAppointment);
    }

    @Override
    public AppointmentResponse update(Long id, AppointmentRequest request) {
        // TODO: check time if it is at in an exact hour only
        // TODO: check veterinarian available date
        Optional<Animal> animalFromDb = animalRepo.findById(request.getAnimalId());
        Optional<Veterinarian> vetFromDb = veterinarianRepo.findById(request.getVeterinarianId());
        Optional<Appointment> appointmentFromDb = appointmentRepo.findById(id);
        if (appointmentFromDb.isEmpty()) {
            throw new NotFoundException("Appointment with id = " + id + " not found!");
        }
        if (animalFromDb.isEmpty()) {
            throw new NotFoundException("Animal with id = " + request.getAnimalId() + " not found!");
        }
        if (vetFromDb.isEmpty()) {
            throw new NotFoundException("Veterinarian with id = " + request.getVeterinarianId() + " not found!");
        }
        appointmentRepo.findByAppointmentDateAndAnimalId(request.getAppointmentDate(), request.getAnimalId())
                .orElseThrow(() -> new RuntimeException("There is an appointment at this time for the animal"));
        appointmentRepo.findByAppointmentDateAndVeterinarianId(request.getAppointmentDate(), request.getVeterinarianId())
                .orElseThrow(() -> new RuntimeException("There is an appointment at this time for the veterinarian"));
        Appointment appointment = appointmentFromDb.get();
        appointmentMapper.update(appointment, request);
        appointmentRepo.save(appointment);
        appointment.setId(appointmentFromDb.get().getId());
        return appointmentMapper.toResponse(appointment);
    }

    @Override
    public void delete(Long id) {
        Appointment appointmentFromDb = appointmentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Appointment with id = " + id + " not found!"));
        appointmentRepo.delete(appointmentFromDb);
    }
}
