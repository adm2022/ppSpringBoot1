package adm2022.pp.repos;

import adm2022.pp.domain.Story;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StoryRepository extends JpaRepository<Story, Long> {
}
