package roulette.entities;

import java.util.Objects;

import javax.persistence.*;

@Entity
public class RouletteEntity {

	private @Id @GeneratedValue Long id;
	private String status = Status.CLOSED.status;

	//public UserEntity user;

	public RouletteEntity () {}

	public Long getId() {
		return this.id;
	}

	public String getStatus () {
		return this.status;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStatus ( String status ) {
		this.status = status;
	}

	public static class Response  {
		public String result = Result.Failed.result;
	}
}
