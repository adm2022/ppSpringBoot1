package adm2022.pp.rest;

import adm2022.pp.model.StoriesDTO;
import adm2022.pp.service.StoriesService;
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
@RequestMapping(value = "/api/storiess", produces = MediaType.APPLICATION_JSON_VALUE)
public class StoriesController {

    private final StoriesService storiesService;

    public StoriesController(final StoriesService storiesService) {
        this.storiesService = storiesService;
    }

    @GetMapping
    public ResponseEntity<List<StoriesDTO>> getAllStoriess() {
        return ResponseEntity.ok(storiesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoriesDTO> getStories(@PathVariable final Long id) {
        return ResponseEntity.ok(storiesService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createStories(@RequestBody @Valid final StoriesDTO storiesDTO) {
        return new ResponseEntity<>(storiesService.create(storiesDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStories(@PathVariable final Long id,
            @RequestBody @Valid final StoriesDTO storiesDTO) {
        storiesService.update(id, storiesDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStories(@PathVariable final Long id) {
        storiesService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
