package com.anu.user.userservice.Entities;

import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name="User")
public class User {

	@Id
	@Column(name="userId")
	private String userId;
	
	@Column(name="userEmail")
	private String email;
	
	@Column(name="userName" , length=20)
	private String userName;
	
	@Column(name="userAbout")
	private String about;

	@Transient
	private List<Rating> ratings=new ArrayList<>();
	
}
