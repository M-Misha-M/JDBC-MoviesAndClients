package ua.movies.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ua.movies.arend.Arend;

public class TableModelForArends extends AbstractTableModel {
	public static final int OBJECT_COL = -1;
	//public static final int id = 0;
	public static final int name = 0;
	public static final int UniqueID = 1;
	public static final int MovieID = 2;
	private static final int StardDate = 3;
	private static final int EndDate = 4;
	private static final int Status = 5;
	private static final int price = 6;
	

	
	
	private String[] columnNames = {"Назва фільму" , "Номер клієнта", "Номер фільму" ,  "Поч дата прокату", "Дата повернення", 
	"Статус фільму" , "Ціна в грн" };
	
	
	private List<Arend> arend;
	
	
	 public TableModelForArends(List<Arend> ar) {
		arend = ar;
	}
	 
	
	
	@Override
	public int getRowCount() {
		return arend.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Arend tmpArend = arend.get(rowIndex);
		
		
		switch(columnIndex) {
		
		case name: 
			return tmpArend.getMovie();
		case UniqueID: 
			return tmpArend.getUniqueID();
			
		case MovieID: 
			return tmpArend.getMovie_id();
		case StardDate: 
			return tmpArend.getStartDate();
			
		case EndDate: 
			return tmpArend.getEndDate();
			
		case Status: 
			return tmpArend.getStatus();
		case price:
			return tmpArend.getPrice();
		case OBJECT_COL:
			return tmpArend;
	
		default:
			return tmpArend.getUniqueID();
		}
	}

}
