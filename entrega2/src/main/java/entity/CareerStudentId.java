package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CareerStudentId implements Serializable {
    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "career_id")
    private Long careerId;
    
    

    public CareerStudentId(Long studentId, Long careerId) {
		this.studentId = studentId;
		this.careerId = careerId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        CareerStudentId that = (CareerStudentId) o;
        return Objects.equals(careerId, that.careerId) &&
                Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(careerId, studentId);
    }

    @Override
    public String toString() {
        return "CareerStudentId [studentId=" + studentId + ", careerId=" + careerId + "]";
    }

	public Long getStudentId() {
		return studentId;
	}

	public Long getCareerId() {
		return careerId;
	}
    
    
}
