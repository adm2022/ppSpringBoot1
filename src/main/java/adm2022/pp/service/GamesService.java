package adm2022.pp.service;

import adm2022.pp.domain.Games;
import adm2022.pp.domain.Stories;
import adm2022.pp.model.GamesDTO;
import adm2022.pp.repos.GamesRepository;
import adm2022.pp.repos.StoriesRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class GamesService {

    private final GamesRepository gamesRepository;
    private final StoriesRepository storiesRepository;

    public GamesService(final GamesRepository gamesRepository,
            final StoriesRepository storiesRepository) {
        this.gamesRepository = gamesRepository;
        this.storiesRepository = storiesRepository;
    }

    public List<GamesDTO> findAll() {
        return gamesRepository.findAll()
                .stream()
                .map(games -> mapToDTO(games, new GamesDTO()))
                .collect(Collectors.toList());
    }

    public GamesDTO get(final Long id) {
        return gamesRepository.findById(id)
                .map(games -> mapToDTO(games, new GamesDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final GamesDTO gamesDTO) {
        final Games games = new Games();
        mapToEntity(gamesDTO, games);
        return gamesRepository.save(games).getId();
    }

    public void update(final Long id, final GamesDTO gamesDTO) {
        final Games games = gamesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(gamesDTO, games);
        gamesRepository.save(games);
    }

    public void delete(final Long id) {
        gamesRepository.deleteById(id);
    }

    private GamesDTO mapToDTO(final Games games, final GamesDTO gamesDTO) {
        gamesDTO.setId(games.getId());
        gamesDTO.setName(games.getName());
        gamesDTO.setGameStories(games.getGameStories() == null ? null : games.getGameStories().getId());
        return gamesDTO;
    }

    private Games mapToEntity(final GamesDTO gamesDTO, final Games games) {
        games.setName(gamesDTO.getName());
        if (gamesDTO.getGameStories() != null && (games.getGameStories() == null || !games.getGameStories().getId().equals(gamesDTO.getGameStories()))) {
            final Stories gameStories = storiesRepository.findById(gamesDTO.getGameStories())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "gameStories not found"));
            games.setGameStories(gameStories);
        }
        return games;
    }

}
