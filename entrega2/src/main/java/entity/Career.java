package entity;

import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NaturalIdCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Career {
    //@description Atributos
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(nullable=false)
    @NaturalId
    private String name;
    @Column
    private int length;
    @OneToMany(mappedBy = "career", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<CareerStudent> students;

    //@description Constructores
    public Career() {}

    public Career(String name, int length) {
        super();
        this.name = name;
        this.length = length;
        this.students = new ArrayList<CareerStudent>();
    }

    //@description Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<CareerStudent> getStudents() {
        return students;
    }

    public Long getId() {
        return id;
    }

    public void addStudent(Student s) {
        CareerStudent cs = new CareerStudent(s, this);
        students.add(cs);
        s.getCareers().add(cs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Career career = (Career) o;
        return Objects.equals(name, career.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Career [id=" + id + ", name=" + name + ", length=" + length + "]";
    }
    
    
    
    
}
