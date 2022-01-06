package adm2022.pp.service;

import adm2022.pp.domain.Stories;
import adm2022.pp.model.StoriesDTO;
import adm2022.pp.repos.StoriesRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class StoriesService {

    private final StoriesRepository storiesRepository;

    public StoriesService(final StoriesRepository storiesRepository) {
        this.storiesRepository = storiesRepository;
    }

    public List<StoriesDTO> findAll() {
        return storiesRepository.findAll()
                .stream()
                .map(stories -> mapToDTO(stories, new StoriesDTO()))
                .collect(Collectors.toList());
    }

    public StoriesDTO get(final Long id) {
        return storiesRepository.findById(id)
                .map(stories -> mapToDTO(stories, new StoriesDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final StoriesDTO storiesDTO) {
        final Stories stories = new Stories();
        mapToEntity(storiesDTO, stories);
        return storiesRepository.save(stories).getId();
    }

    public void update(final Long id, final StoriesDTO storiesDTO) {
        final Stories stories = storiesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(storiesDTO, stories);
        storiesRepository.save(stories);
    }

    public void delete(final Long id) {
        storiesRepository.deleteById(id);
    }

    private StoriesDTO mapToDTO(final Stories stories, final StoriesDTO storiesDTO) {
        storiesDTO.setId(stories.getId());
        storiesDTO.setSummary(stories.getSummary());
        storiesDTO.setDescription(stories.getDescription());
        storiesDTO.setPoints(stories.getPoints());
        return storiesDTO;
    }

    private Stories mapToEntity(final StoriesDTO storiesDTO, final Stories stories) {
        stories.setSummary(storiesDTO.getSummary());
        stories.setDescription(storiesDTO.getDescription());
        stories.setPoints(storiesDTO.getPoints());
        return stories;
    }

}
