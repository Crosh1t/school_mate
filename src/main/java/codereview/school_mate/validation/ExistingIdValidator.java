package codereview.school_mate.validation;

import codereview.school_mate.model.Parent;
import codereview.school_mate.model.SchoolClass;
import codereview.school_mate.model.Subject;
import codereview.school_mate.repository.ParentRepository;
import codereview.school_mate.repository.SchoolClassRepository;
import codereview.school_mate.repository.SubjectRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ExistingIdValidator implements ConstraintValidator<ExistingId, Long> {

    private static final Map<Class<?>, JpaRepository<?, Long>> repositories = new HashMap<>();

    private Class<?> entityClass;

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public void initialize(ExistingId constraintAnnotation) {
        this.entityClass = constraintAnnotation.entityClass();

        repositories.put(SchoolClass.class, schoolClassRepository);
        repositories.put(Parent.class, parentRepository);
        repositories.put(Subject.class, subjectRepository);
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        if (id == null) {
            return true;
        }

        JpaRepository<?, Long> repository = repositories.get(entityClass);
        if (repository == null) {
            throw new IllegalStateException("Репозиторий для " + entityClass + " не найден");
        }

        return repository.existsById(id);
    }
}
