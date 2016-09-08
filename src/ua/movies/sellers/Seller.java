package ua.movies.sellers;

import java.math.BigDecimal;

public class Seller {
	
private int unique_id;
private String firstName;
private String latName;
private String sex;



public Seller(String firstName, String latName, String sex) {
	this(0, firstName, latName, sex);
}


public Seller(int id, String firstName, String latName , String sex) {
    this.unique_id = id;
    this.firstName = firstName;
    this.latName = latName;
    this.sex = sex;
}


public int getUnique_id() {
	return unique_id;
}


public void setUnique_id(int unique_id) {
	this.unique_id = unique_id;
}


public String getSex() {
	return sex;
}


public void setSex(String sex) {
	this.sex = sex;
}





public String getFirstName() {
    return firstName;
}

public void setFirstName(String firstName) {
    this.firstName = firstName;
}

public String getLatName() {
    return latName;
}

public void setLatName(String latName) {
    this.latName = latName;
}




}


	

