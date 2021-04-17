package com.producersapi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.producersapi.enums.ActivitiesName;
import com.producersapi.enums.Period;

import lombok.Data;

@Data
@Entity
public class NewProducer implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	private String nickname;

	private String phone;

	private String email;
	
	private boolean status = true;

	@JsonIgnore
	private String password;

	private int role = 1;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;

	private String cpf;
	
	@ManyToOne
	public Manager manager;
	
	private ActivitiesName activitiesName;
	
	private Period period;
	
	private String uf;

	private String city;
	
	private String zipCode;

	private String district;

	private String street;

	private String houseNumber;

	private String reference;

}
