package gokhancihan.vet.api;

import gokhancihan.vet.business.AppointmentService;
import gokhancihan.vet.business.IAppointmentService;
import gokhancihan.vet.dto.request.AppointmentRequest;
import gokhancihan.vet.dto.response.AppointmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping()
    public List<AppointmentResponse> getAll() {
        return appointmentService.getAll();
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
