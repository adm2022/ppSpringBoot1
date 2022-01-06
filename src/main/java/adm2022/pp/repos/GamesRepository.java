package adm2022.pp.repos;

import adm2022.pp.domain.Games;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GamesRepository extends JpaRepository<Games, Long> {
}
