package adm2022.pp.repos;

import adm2022.pp.domain.Stories;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StoriesRepository extends JpaRepository<Stories, Long> {
}
