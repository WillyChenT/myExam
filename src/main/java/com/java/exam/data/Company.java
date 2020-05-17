package com.java.exam.data;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity(name= "COMPANY")
@Table(name = "COMPANY")
public class Company {
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
    @ApiModelProperty(position=1 ,value = "公司名", example="Starbucks")
    @Column(name="NAME")
	private String name; 
    @ApiModelProperty(position=2 ,value = "地址", example="台北市信義區基隆路一段200號5樓")
    @Column(name="ADDRESS")
	private String address;
	@ApiModelProperty(hidden = true)
	@Column(name="CREATED_BY")
	private String createdBy;
	@ApiModelProperty(hidden = true)
	@Column(name="CREATED_AT")
	private Timestamp createdAt;
	@ApiModelProperty(hidden = true)
	@Column(name="UPDATED_BY")
	private String updatedBy;
	@ApiModelProperty(hidden = true)
	@Column(name="UPDATED_AT")
	private Timestamp updatedAt;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	

	
}
