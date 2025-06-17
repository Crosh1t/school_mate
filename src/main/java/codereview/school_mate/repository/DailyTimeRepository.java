package codereview.school_mate.repository;

import codereview.school_mate.model.DailyTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyTimeRepository extends JpaRepository<DailyTime, Long> {
}
