package adm2022.pp.service;

import adm2022.pp.domain.Meeting;
import adm2022.pp.model.MeetingDTO;
import adm2022.pp.repos.MeetingRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;

    public MeetingService(final MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public List<MeetingDTO> findAll() {
        return meetingRepository.findAll()
                .stream()
                .map(meeting -> mapToDTO(meeting, new MeetingDTO()))
                .collect(Collectors.toList());
    }

    public MeetingDTO get(final Long id) {
        return meetingRepository.findById(id)
                .map(meeting -> mapToDTO(meeting, new MeetingDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final MeetingDTO meetingDTO) {
        final Meeting meeting = new Meeting();
        mapToEntity(meetingDTO, meeting);
        return meetingRepository.save(meeting).getId();
    }

    public void update(final Long id, final MeetingDTO meetingDTO) {
        final Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(meetingDTO, meeting);
        meetingRepository.save(meeting);
    }

    public void delete(final Long id) {
        meetingRepository.deleteById(id);
    }

    private MeetingDTO mapToDTO(final Meeting meeting, final MeetingDTO meetingDTO) {
        meetingDTO.setId(meeting.getId());
        meetingDTO.setName(meeting.getName());
        meetingDTO.setDateCreation(meeting.getDateCreation());
        return meetingDTO;
    }

    private Meeting mapToEntity(final MeetingDTO meetingDTO, final Meeting meeting) {
        meeting.setName(meetingDTO.getName());
        meeting.setDateCreation(meetingDTO.getDateCreation());
        return meeting;
    }

}
