package ua.movies.movie;

public class Movies {

private int movie_id;
private String movie_name;
private String genre;
private String release;
private int unique_id;
	
	
	
	public int getUnique_id() {
	return unique_id;
}



public void setUnique_id(int unique_id) {
	this.unique_id = unique_id;
}



	public int getMovie_id() {
	return movie_id;
}



public void setMovie_id(int movie_id) {
	this.movie_id = movie_id;
}



public String getMovie_name() {
	return movie_name;
}



public void setMovie_name(String movie_name) {
	this.movie_name = movie_name;
}



public String getGenre() {
	return genre;
}



public void setGenre(String genre) {
	this.genre = genre;
}



public String getRelease() {
	return release;
}



public void setRelease(String release) {
	this.release = release;
}








	public Movies(int movie_id, String movie_name, String genre, String release, int unique_id) {
		super();
		this.movie_id = movie_id;
		this.movie_name = movie_name;
		this.genre = genre;
		this.release = release;
		this.unique_id = unique_id;
	}



	public Movies(String movie_name, String genre, String release, int unique_id) {
		this(0,movie_name , genre , release , unique_id);
	
	}
	

	


}
