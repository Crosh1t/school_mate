package codereview.school_mate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta. persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;

import java.util.Set;
import lombok. Data;
import lombok. EqualsAndHashCode;

@Entity
@Data
@Table(name = "school_classes")
@EqualsAndHashCode(of = {"id"})
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "reversed_name")
    private String reversedColumnName;

    @ManyToMany(mappedBy = "classes") // Указываем на поле в Teacher
    @EqualsAndHashCode.Exclude
    private Set<Teacher> teachers;

    public void setName(String name) {
        this.name = name;
        this.reversedColumnName = new StringBuilder(name).reverse().toString();
    }
}