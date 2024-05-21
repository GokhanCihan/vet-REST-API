package gokhancihan.vet.api;

import gokhancihan.vet.business.IAppointmentService;
import gokhancihan.vet.dto.request.AppointmentRequest;
import gokhancihan.vet.dto.response.AppointmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/appointment")
public class AppointmentController {

    @Autowired
    private IAppointmentService appointmentService;

    @GetMapping("/{id}")
    public AppointmentResponse getById(@PathVariable("id") Long id) {
        return appointmentService.getById(id);
    }

    @GetMapping("/all")
    public List<AppointmentResponse> getAll() {
        return appointmentService.getAll();
    }

    @GetMapping("/all/veterinarianId={id}&startDate={startDate}&endDate={endDate}")
    public List<AppointmentResponse> getAllByVeterinarianAndDateRange(
            @PathVariable("id") Long veterinarianId,
            @PathVariable("startDate") LocalDate startDate,
            @PathVariable("endDate") LocalDate endDate) {
        return appointmentService.getAllInRangeForVeterinarian(veterinarianId, startDate, endDate);
    }

    @GetMapping("/all/animalId={id}&startDate{startDate}&endDate{endDate}")
    public List<AppointmentResponse> getAllByAnimalAndDateRange(
            @PathVariable("id") Long animalId,
            @PathVariable("startDate") LocalDate startDate,
            @PathVariable("endDate") LocalDate endDate) {
        return appointmentService.getAllInRangeForAnimal(animalId, startDate, endDate);
    }

    @PostMapping()
    public AppointmentResponse create(@RequestBody AppointmentRequest appointmentRequest) {
        return appointmentService.create(appointmentRequest);
    }


    @PutMapping("/{id}")
    public AppointmentResponse update(@PathVariable("id") Long id, @RequestBody AppointmentRequest appointmentRequest) {
        return appointmentService.update(id, appointmentRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        appointmentService.delete(id);
    }
}
