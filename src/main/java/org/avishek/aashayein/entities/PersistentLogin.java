package org.avishek.aashayein.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TblPersistentLogin")
public class PersistentLogin implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Series")
	private String series;

	@Column(name = "Username", unique = true, nullable = false)
	private String username;

	@Column(name = "Token", unique = true, nullable = false)
	private String token;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LastUsed")
	private Date lastUsed;

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	@Override
	public String toString() {
		return "PersistentLogin [series=" + series + ", username=" + username + ", token=" + token + ", lastUsed="
				+ lastUsed + "]";
	}

}