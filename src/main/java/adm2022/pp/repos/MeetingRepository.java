package adm2022.pp.repos;

import adm2022.pp.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
