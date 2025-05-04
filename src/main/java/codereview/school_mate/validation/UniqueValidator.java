package codereview.school_mate.validation;

import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    @Autowired
    private EntityManager entityManager;

    private Class<?> entityClass;
    private String fieldName;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.entityClass = constraintAnnotation.entityClass();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        String jpql = "SELECT COUNT(e) FROM " + entityClass.getSimpleName() +
                " e WHERE e." + fieldName + " = :value";
        Long count = entityManager.createQuery(jpql, Long.class)
                .setParameter("value", value)
                .getSingleResult();

        return count == 0;
    }
}