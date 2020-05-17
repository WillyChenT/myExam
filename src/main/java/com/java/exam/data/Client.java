package com.java.exam.data;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity(name = "CLIENT")
@Table(name = "CLIENT")
public class Client {
	
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
    @ApiModelProperty(position=1 ,value = "公司ID", example="2")
	@Column(name="COMPANY_ID")
	private String company_id;
    @ApiModelProperty(position=2 ,value = "姓名", example="王天才")
	@Column(name="NAME")
	private String name;
    @ApiModelProperty(position=3 ,value = "E-Mail", example="godisme@gmail.com")
	@Column(name="EMAIL")
	private String email;
    @ApiModelProperty(position=4 ,value = "電話", example="0943943943")
	@Column(name="PHONE")
	private String phone;
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
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
