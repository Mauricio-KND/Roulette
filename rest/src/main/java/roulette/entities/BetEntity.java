package roulette.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BetEntity {

	private @Id @GeneratedValue Long id;
	@OneToOne
	private RouletteEntity rouletteEntity;
	@OneToOne
	private UserEntity userEntity;
	private  boolean color = true; //True=Red, False=Black
	public int betAmount = 0; //0-10000
	public int betNumber = 0; //0-36

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public RouletteEntity getRouletteEntity () {
		return rouletteEntity;
	}

	public void setRouletteEntity ( RouletteEntity rouletteEntity ) {
		this.rouletteEntity = rouletteEntity;
	}

	public UserEntity getUserEntity () {
		return userEntity;
	}

	public void setUserEntity ( UserEntity userEntity ) {
		this.userEntity = userEntity;
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
}