package mggdevit.demolibrary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import mggdevit.demolibrary.validation.ValidEmail;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true, nullable = false)
	@ValidEmail
	private String email;

//	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String fullName;

	public User() {
	}

//	public User(Long id, String fullName) {
//		this.id = id;
//		this.fullName = fullName;
//	}

//	public User(String fullName) {
//		this(UUID.randomUUID().toString(), fullName);
//	}

	public User(String fullName) {
		this.fullName = fullName;
		this.email = fullName.replaceAll(" ", "") + "@email.eml";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + "]";
	}

}
