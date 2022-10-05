package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    private String career;
    private BigInteger year;
    private BigInteger graduated;
    private BigInteger registered;
    
    

    public ReportDTO(String career, BigInteger year, BigInteger graduated, BigInteger registered) {
		super();
		this.career = career;
		this.year = year;
		this.graduated = graduated;
		this.registered = registered;
	}



	@Override
    public String toString() {
        return "\n" + career + "\n" + year + "\n graduated=" + graduated + "\n registered=" + registered;
    }
}
