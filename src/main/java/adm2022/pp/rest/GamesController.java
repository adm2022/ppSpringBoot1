package adm2022.pp.rest;

import adm2022.pp.model.GamesDTO;
import adm2022.pp.service.GamesService;
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
@RequestMapping(value = "/api/gamess", produces = MediaType.APPLICATION_JSON_VALUE)
public class GamesController {

    private final GamesService gamesService;

    public GamesController(final GamesService gamesService) {
        this.gamesService = gamesService;
    }

    @GetMapping
    public ResponseEntity<List<GamesDTO>> getAllGamess() {
        return ResponseEntity.ok(gamesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GamesDTO> getGames(@PathVariable final Long id) {
        return ResponseEntity.ok(gamesService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createGames(@RequestBody @Valid final GamesDTO gamesDTO) {
        return new ResponseEntity<>(gamesService.create(gamesDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGames(@PathVariable final Long id,
            @RequestBody @Valid final GamesDTO gamesDTO) {
        gamesService.update(id, gamesDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGames(@PathVariable final Long id) {
        gamesService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
