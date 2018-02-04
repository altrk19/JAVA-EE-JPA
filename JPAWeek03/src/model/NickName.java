package model;

import javax.persistence.Embeddable;

@Embeddable
public class NickName {

	private String nickName;
	private String nickSurname;
	
	public NickName() {
		super();
	}
	public NickName(String nickName, String nickSurname) {
		super();
		this.nickName = nickName;
		this.nickSurname = nickSurname;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getNickSurname() {
		return nickSurname;
	}
	public void setNickSurname(String nickSurname) {
		this.nickSurname = nickSurname;
	}
}
