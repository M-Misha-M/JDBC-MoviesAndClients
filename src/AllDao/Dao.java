package AllDao;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import ua.movies.arend.Arend;
import ua.movies.movie.Movies;
import ua.movies.sellers.*;


public class Dao {
	
	private Connection mycon;
	
	
	public Dao() throws Exception {
		
		Properties pr = new Properties();
		pr.load(new FileInputStream("demo.properties"));
		String USER  = pr.getProperty("user");
		String PASSWORD  = pr.getProperty("password");
		String db_url = pr.getProperty("dburl");
		
		mycon = DriverManager.getConnection(db_url , USER, PASSWORD);
		
		System.out.println("Connection succesful " + db_url);
		
		
	}
	
	
	
	
	
	
	public void updateEmployee(Seller theEmployee) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = mycon.prepareStatement("update sellers"
					+ " set seller_name=?, seller_lastname=?, sex=?"
					+ " where unique_id=?");
			
			// set params
			myStmt.setString(1, theEmployee.getFirstName());
			myStmt.setString(2, theEmployee.getLatName());
			myStmt.setString(3, theEmployee.getSex());
			myStmt.setInt(4, theEmployee.getUnique_id());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	
	
	
	
	public void deleteEmployee(int employeeId) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = mycon.prepareStatement("delete from sellers where unique_id=?");
			
			// set param
			myStmt.setInt(1, employeeId);
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
	}
	
	
	
	public void addSeller(Seller seller) throws Exception {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = mycon.prepareStatement("insert into sellers"
					+ " (seller_name, seller_lastname, sex)"
					+ " values (?, ?, ?)");
			
			stmt.setString(1, seller.getFirstName());
			stmt.setString(2, seller.getLatName());
			stmt.setString(3, seller.getSex());
			
			
			stmt.executeUpdate();
			
		}finally {
			close(stmt);
		}
		
	}
	
	
	
	public List<Seller> getAllEmployees() throws Exception {
		List<Seller> list = new ArrayList<>();
		
		Statement st = null;
		ResultSet res = null;
		
		
		try {
			st = mycon.createStatement();
			res = st.executeQuery("select * from sellers");
			
			while(res.next()) {
				Seller tempEmp = convertRowToEmployee(res);
				list.add(tempEmp);
			}
			return list;
			
			
		}finally {
			close(st, res);
			
		}
		
	}
	
	
	
	
	public static void close(Connection mycon , Statement st , ResultSet res) throws SQLException {
		if(res != null) {
			res.close();
		}
		
		if(st != null) {
			st.close();
		}
		
		
		if(mycon != null) {
			mycon.close();
		}
		
		
	}
	
	public void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}
	
	
	
	private void close(Statement myStmt) throws SQLException {
		close(null, myStmt, null);		
	}
	
	
	
private Seller convertRowToEmployee(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("unique_id");
		String firstName = myRs.getString("seller_name");
		String lastName = myRs.getString("seller_lastname");	
	String sex = myRs.getString("sex");
		
		
		Seller tempSeller = new Seller(id, firstName , lastName, sex);
		
		return tempSeller;
	}


public List<Seller> searchEmployees(String lastName) throws Exception {
	List<Seller> list = new ArrayList<>();

	PreparedStatement myStmt = null;
	ResultSet myRs = null;

	try {
		lastName += "%";
		myStmt = mycon.prepareStatement("select * from sellers where seller_lastname like ?");
		
		myStmt.setString(1, lastName);
		
		myRs = myStmt.executeQuery();
		
		while (myRs.next()) {
			Seller tempSeller = convertRowToEmployee(myRs);
			list.add(tempSeller);
		}
		
		return list;
	}
	finally {
		close(myStmt, myRs);
	}
}








      //Movies

public List<Movies> getAllMovies() throws Exception {
	List<Movies> list = new ArrayList<>();
	
	Statement st = null;
	ResultSet res = null;
	
	
	try {
		st = mycon.createStatement();
		res = st.executeQuery("select * from movies");
		
		while(res.next()) {
			Movies tempMovies= convertRowToMovies(res);
			list.add(tempMovies);
		}
		return list;
		
		
	}finally {
		close(st, res);
		
	}


	

}



public List<Movies> searchMovies(String movieName) throws Exception {
	List<Movies> list = new ArrayList<>();

	PreparedStatement myStmt = null;
	ResultSet myRs = null;

	try {
		movieName += "%";
		myStmt = mycon.prepareStatement("select * from movies where movie_name like ?");
		
		myStmt.setString(1, movieName);
		
		myRs = myStmt.executeQuery();
		
		while (myRs.next()) {
			Movies tempSeller = convertRowToMovies(myRs);
			list.add(tempSeller);
		}
		
		return list;
	}
	finally {
		close(myStmt, myRs);
	}
}





private Movies convertRowToMovies(ResultSet myRs) throws SQLException {
	
	int movie_id = myRs.getInt("movie_id");
	String movie_name = myRs.getString("movie_name");
	String genre = myRs.getString("genre");	
String  release = myRs.getString("release_date");
int unique_id = myRs.getInt("unique_id");


Movies tempMovies = new Movies(movie_id, movie_name, genre, release, unique_id);

return tempMovies;
	
}


public void addMovie(Movies movie) throws Exception {
	
	PreparedStatement stmt = null;
	
	try {
		stmt = mycon.prepareStatement("insert into movies"
				+ " (movie_name, genre, release_date, unique_id)"
				+ " values (?, ?, ?, ?)");
		
		stmt.setString(1, movie.getMovie_name());
		stmt.setString(2, movie.getGenre());
		stmt.setString(3, movie.getRelease());
		stmt.setInt(4, movie.getUnique_id());
		
		
		stmt.executeUpdate();
		
	}finally {
		close(stmt);
	}
	
}


public void deleteMovie(int movieId) throws SQLException {
	PreparedStatement myStmt = null;

	try {
		// prepare statement
		myStmt = mycon.prepareStatement("delete from movies where movie_id=?");
		
		// set param
		myStmt.setInt(1, movieId);
		
		// execute SQL
		myStmt.executeUpdate();			
	}
	finally {
		close(myStmt);
	}
}




public void updateMovie(Movies theMovies) throws SQLException {
	PreparedStatement myStmt = null;

	try {
		// prepare statement
		myStmt = mycon.prepareStatement("update movies"
				+ " set movie_name=?, genre=?, release_date=?, unique_id =?"
				+ " where movie_id=?");
		
		// set params
		myStmt.setString(1, theMovies.getMovie_name());
		myStmt.setString(2, theMovies.getGenre());
		myStmt.setString(3, theMovies.getRelease());
	myStmt.setInt(4, theMovies.getUnique_id());
	
	myStmt.setInt(5, theMovies.getMovie_id());
		
		// execute SQL
		myStmt.executeUpdate();			
	}
	finally {
		close(myStmt);
	}
	
}






//Arend

public List<Arend> getAllArends() throws Exception {
	List<Arend> list = new ArrayList<>();
	
	Statement st = null;
	ResultSet res = null;
	
	
	try {
		st = mycon.createStatement();
		res = st.executeQuery("SELECT movies.movie_name , arend.unique_id , arend.movie_id , arend.startDate," +  
" arend.endDate , arend.status , arend.price from movies inner join arend on" + 
" movies.movie_id = arend.movie_id; ");
		
		while(res.next()) {
			Arend tempArend= convertRowToArend(res);
			list.add(tempArend);
		}
		return list;
		
		
	}finally {
		close(st, res);
		
	}


}


public Arend convertRowToArend(ResultSet myRs) throws SQLException {
	//int arendId = myRs.getInt("id_arend");
	
	String nameMovie = myRs.getString("movies.movie_name");
	int uniqueID = myRs.getInt("unique_id");
	int movieID = myRs.getInt("movie_id");
	String startDate = myRs.getString("startDate");
	String endDate = myRs.getString("endDate");	
	
String  status = myRs.getString("status");
BigDecimal price = myRs.getBigDecimal("price");


Arend tempArend = new Arend(nameMovie ,uniqueID , movieID , startDate, endDate, status , price);

return tempArend;
  }











public void deleteArend(int arendID) throws SQLException {
	PreparedStatement myStmt = null;
	try {
		// prepare statement
		myStmt = mycon.prepareStatement("delete from arend where id_arend=?");
		
		// set param
		myStmt.setInt(1, arendID);
		
		// execute SQL
		myStmt.executeUpdate();			
	}
	finally {
		close(myStmt);
	}
	
}




public void addArends(Arend arend) throws Exception {
	
	PreparedStatement stmt = null;
	
	try {
		stmt = mycon.prepareStatement("insert into arend"
				+ " (unique_id , movie_id , startDate, endDate, status, price)"
				+ " values (?, ?, ?, ? , ? , ?)");
		stmt.setInt(1, arend.getUniqueID());
		stmt.setInt(2, arend.getMovie_id());
		stmt.setString(3, arend.getStartDate());
		stmt.setString(4, arend.getEndDate());
		stmt.setString(5, arend.getStatus());
		stmt.setBigDecimal(6, arend.getPrice());
		
		
		stmt.executeUpdate();
		
	}finally {
		close(stmt);
	}
	


}

public void updateArend(Arend theArend) throws SQLException {
	PreparedStatement stmt = null;

	try {
		// prepare statement
		stmt = mycon.prepareStatement("update arend"
				+ " set unique_id=?, movie_id = ?,  startDate=?, endDate=?, status =? , price=?"
				+ " where id_arend=?");
		
		// set params
		stmt.setInt(1,theArend.getUniqueID() );
		stmt.setInt(2, theArend.getMovie_id());
		stmt.setString(3, theArend.getStartDate());
		stmt.setString(4, theArend.getEndDate());
		stmt.setString(5, theArend.getStatus());
		stmt.setBigDecimal(6, theArend.getPrice());
	stmt.setInt(7, theArend.getArendID());
		
		// execute SQL
		stmt.executeUpdate();			
	}
	finally {
		close(stmt);
	}



}



public List<Arend> searchArend(int id) throws Exception {
	List<Arend> list = new ArrayList<>();

	PreparedStatement myStmt = null;
	ResultSet myRs = null;

	try {
		id += Integer.parseInt("%");
		myStmt = mycon.prepareStatement("select * from arend where movie_id like ?");
		
		myStmt.setInt(1, id);
		
		myRs = myStmt.executeQuery();
		
		while (myRs.next()) {
			Arend tempArend = convertRowToArend(myRs);
			list.add(tempArend);
		}
		
		return list;
	}
	finally {
		close(myStmt, myRs);
	}
}








}
