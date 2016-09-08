package ua.movies.arend;

import java.math.BigDecimal;

import ua.movies.movie.Movies;

public class Arend {


	private String startDate;
	private String endDate;
	private String status;
	private int arendID;
	private int uniqueID;
	private int movie_id;
	
	private String movie;
	
	
	



	



	public String getMovie() {
		return movie;
	}



	public void setMovie(String movie) {
		this.movie = movie;
	}



	public int getMovie_id() {
		return movie_id;
	}



	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}



	public int getUniqueID() {
		return uniqueID;
	}



	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}




	private BigDecimal price;
	
	
	public BigDecimal getPrice() {
		return price;
	}




	public void setPrice(BigDecimal price) {
		this.price = price;
	}




	public int getArendID() {
		return arendID;
	}




	public void setArendID(int arendID) {
		this.arendID = arendID;
	}



	public String getStartDate() {
		return startDate;
	}






	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}






	public String getEndDate() {
		return endDate;
	}






	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}






	public String getStatus() {
		return status;
	}






	public void setStatus(String status) {
		this.status = status;
	}




	public Arend(String mov, int movieId,  int uniqueID , String startDate, String endDate, String status , BigDecimal price) {
		super();
		this.movie = mov;
		this.uniqueID = uniqueID;
		this.movie_id = movieId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.arendID = arendID;
		this.price = price;
	  }
	
	
	/*public Arend(int uniqueID, int movieId,  String startDate, String endDate, String status , BigDecimal price) {
		this(0 , uniqueID , movieId, startDate , endDate , status , price);
	}*/
	
	
	/*public Arend(String startDate, String endDate, String status , BigDecimal price) {
		this(0 ,0, startDate , endDate , status , price);
	}*/
	
	}
