package adm2022.pp.rest;

import adm2022.pp.model.StoryDTO;
import adm2022.pp.service.StoryService;
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
@RequestMapping(value = "/api/storys", produces = MediaType.APPLICATION_JSON_VALUE)
public class StoryController {

    private final StoryService storyService;

    public StoryController(final StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping
    public ResponseEntity<List<StoryDTO>> getAllStorys() {
        return ResponseEntity.ok(storyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoryDTO> getStory(@PathVariable final Long id) {
        return ResponseEntity.ok(storyService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createStory(@RequestBody @Valid final StoryDTO storyDTO) {
        return new ResponseEntity<>(storyService.create(storyDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStory(@PathVariable final Long id,
            @RequestBody @Valid final StoryDTO storyDTO) {
        storyService.update(id, storyDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStory(@PathVariable final Long id) {
        storyService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
