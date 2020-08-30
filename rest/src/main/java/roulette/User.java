package roulette;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class User {

	private @Id @GeneratedValue Long id;
	private String status = Status.CLOSED.status;
	public boolean color = true; //True=Red, False=Black
	public int betAmount = 0; //0-10000
	public int betNumber = 0; //0-36
	//public User user;

	User () {}

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

	public boolean getColor () {
		return color;
	}

	public void setColor ( boolean isRed){
		this.color = isRed;
	}

	public int getBetAmount(){
		return betAmount;
	}

	public void setBetAmount ( int betAmount ) {

		if (betAmount < 0 || betAmount > 10000){
			//Exception
		}else {
			this.betAmount = betAmount;
		}
	}

	public int getBetNumber(){
		return betNumber;
	}

	public void setBetNumber(int betNumber){
		if (betNumber < 0 || betNumber > 36){
			// Handle exception
		}else {
			this.betNumber = betNumber;
		}
	}

//	public User getUser () {
//		return user;
//	}
//
//	public void setUser ( User user ) {
//		this.user = user;
//	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;
		User roulette = (User) o;
		return Objects.equals(this.id, roulette.id) && Objects.equals(this.status, roulette.status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.status);
	}

	@Override
	public String toString() {
		return "Roulette{" + "id=" + this.id + ", status='" + this.status + '\'' + '}';
	}

	public static class Response  {
		public String result = Result.Failed.result;
	}

	enum Status {
		OPEN("Open"),
		CLOSED("Closed");

		String status;

		Status ( String status ) {
			this.status = status;
		}
	}

	enum Result {
		Succeed("Succeed"),
		Failed("Failed");

		String result;

		Result ( String result ) {
			this.result = result;
		}
	}
}
