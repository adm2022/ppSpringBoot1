package adm2022.pp.rest;

import adm2022.pp.model.MeetingDTO;
import adm2022.pp.service.MeetingService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/meetings", produces = MediaType.APPLICATION_JSON_VALUE)
public class MeetingController {

    private final MeetingService meetingService;

    public MeetingController(final MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping
    public ResponseEntity<List<MeetingDTO>> getAllMeetings() {
        return ResponseEntity.ok(meetingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeetingDTO> getMeeting(@PathVariable final Long id) {
        return ResponseEntity.ok(meetingService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createMeeting(@RequestBody @Valid final MeetingDTO meetingDTO) {
        return new ResponseEntity<>(meetingService.create(meetingDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMeeting(@PathVariable final Long id,
            @RequestBody @Valid final MeetingDTO meetingDTO) {
        meetingService.update(id, meetingDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeeting(@PathVariable final Long id) {
        meetingService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
