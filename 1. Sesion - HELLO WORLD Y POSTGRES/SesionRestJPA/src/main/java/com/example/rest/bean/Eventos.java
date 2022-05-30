package com.example.rest.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_evento")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Eventos {

	@Id
	@Column(name = "evento_id")
	private Long id;
	private String titulo;

	@Column(name = "evento_fecha")
	private Date fecha;
}
