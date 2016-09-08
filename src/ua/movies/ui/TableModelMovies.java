package ua.movies.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ua.movies.movie.Movies;
import ua.movies.sellers.Seller;

public class TableModelMovies extends AbstractTableModel {
	public static final int OBJECT_COL = -1;
	private static final int Name = 0;
	private static final int Genre = 1;
	private static final int Release = 2;
	private static final int UniqueKey = 3;
	
	private String[] columnNames = { "Назва", "Жанр", "Дата релізу",
	"Унікальний номер продавця" };
	
	private List<Movies> movies;
	
	
	public TableModelMovies(List<Movies> theMovies) {
		movies = theMovies;
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public int getRowCount() {
		return movies.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Movies tempMovies = movies.get(rowIndex);
	
		
		switch(columnIndex) {
		case Name:
			return tempMovies.getMovie_name();
		case Genre:
			return tempMovies.getGenre();
		case Release:
			return tempMovies.getRelease();
		case UniqueKey:
			return tempMovies.getUnique_id();
		case OBJECT_COL:
			return tempMovies;
		default:
			return tempMovies.getMovie_name();
		
		
		}
	}
	
	
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
