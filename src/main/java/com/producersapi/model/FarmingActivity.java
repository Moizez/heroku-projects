package com.producersapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.producersapi.enums.Period;
import lombok.Data;

@Data
@Entity
public class FarmingActivity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer value;

	@Column(name = "activity_name")
	private String label;

	private Period period = Period.Mensal;

	private float averageCash;

	@JsonIgnore
	@ManyToMany(mappedBy = "products")
	private List<Producer> producers;

	public List<Producer> getProducers() {
		return producers;
	}
}