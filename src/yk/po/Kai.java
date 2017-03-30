package yk.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Kai implements Serializable{
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Kai [id=" + id + ", name=" + name + "]";
	}
	

}
