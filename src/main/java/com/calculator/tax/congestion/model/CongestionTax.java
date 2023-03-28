package com.calculator.tax.congestion.model;


import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "congestion_tax")
public class CongestionTax {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_time", nullable = false)
    private LocalTime fromTime;

    @Column(name = "to_time", nullable = false)
    private LocalTime toTime;

    @Column(name = "amount")
    private int amount;
    
    @Column(name = "city")
    private String city;
    
      

    public CongestionTax() {}


	public CongestionTax(Long id, LocalTime fromTime, LocalTime toTime, int amount, String city) {
		super();
		this.id = id;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.amount = amount;
		this.city = city;
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalTime fromTime) {
        this.fromTime = fromTime;
    }

    public LocalTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalTime toTime) {
        this.toTime = toTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
