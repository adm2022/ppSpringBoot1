package adm2022.pp.service;

import adm2022.pp.domain.Meeting;
import adm2022.pp.domain.Story;
import adm2022.pp.model.StoryDTO;
import adm2022.pp.repos.MeetingRepository;
import adm2022.pp.repos.StoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class StoryService {

    private final StoryRepository storyRepository;
    private final MeetingRepository meetingRepository;

    public StoryService(final StoryRepository storyRepository,
            final MeetingRepository meetingRepository) {
        this.storyRepository = storyRepository;
        this.meetingRepository = meetingRepository;
    }

    public List<StoryDTO> findAll() {
        return storyRepository.findAll()
                .stream()
                .map(story -> mapToDTO(story, new StoryDTO()))
                .collect(Collectors.toList());
    }

    public StoryDTO get(final Long id) {
        return storyRepository.findById(id)
                .map(story -> mapToDTO(story, new StoryDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final StoryDTO storyDTO) {
        final Story story = new Story();
        mapToEntity(storyDTO, story);
        return storyRepository.save(story).getId();
    }

    public void update(final Long id, final StoryDTO storyDTO) {
        final Story story = storyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(storyDTO, story);
        storyRepository.save(story);
    }

    public void delete(final Long id) {
        storyRepository.deleteById(id);
    }

    private StoryDTO mapToDTO(final Story story, final StoryDTO storyDTO) {
        storyDTO.setId(story.getId());
        storyDTO.setSummary(story.getSummary());
        storyDTO.setDescription(story.getDescription());
        storyDTO.setDateCreation(story.getDateCreation());
        storyDTO.setPoints(story.getPoints());
        storyDTO.setMeetingStorys(story.getMeetingStorys() == null ? null : story.getMeetingStorys().getId());
        return storyDTO;
    }

    private Story mapToEntity(final StoryDTO storyDTO, final Story story) {
        story.setSummary(storyDTO.getSummary());
        story.setDescription(storyDTO.getDescription());
        story.setDateCreation(storyDTO.getDateCreation());
        story.setPoints(storyDTO.getPoints());
        if (storyDTO.getMeetingStorys() != null && (story.getMeetingStorys() == null || !story.getMeetingStorys().getId().equals(storyDTO.getMeetingStorys()))) {
            final Meeting meetingStorys = meetingRepository.findById(storyDTO.getMeetingStorys())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "meetingStorys not found"));
            story.setMeetingStorys(meetingStorys);
        }
        return story;
    }

}
