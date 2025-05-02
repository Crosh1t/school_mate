package codereview.school_mate.repository;

<<<<<<< HEAD
public interface StudentRepository {
=======
import codereview.school_mate.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
>>>>>>> pr-3
}
