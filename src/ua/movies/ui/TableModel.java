package ua.movies.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ua.movies.sellers.Seller;

public class TableModel extends AbstractTableModel {
	public static final int OBJECT_COL = -1;
	public static final int UniqueID = 0;
	private static final int FIRST_NAME_COL = 1;
	private static final int LAST_NAME_COL = 2;
	private static final int Sex = 3;
	
	
	private String[] columnNames = {"Унікальний номер", "Ім'я", "Призвіще", 
	"Стать" };
	
	private List<Seller> sellers;
	
	
	
	public TableModel(List<Seller> sell) {
		sellers = sell;
	}
	
	
	
	public int getRowCount() {
		// TODO Auto-generated method stub
		return sellers.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
	
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Seller tmpSellers = sellers.get(rowIndex);
		
		
		switch (columnIndex) {
		case UniqueID:
			return tmpSellers.getUnique_id();
		case FIRST_NAME_COL:
			return tmpSellers.getFirstName();
		case LAST_NAME_COL:
			return tmpSellers.getLatName();		
		case Sex: 
			return tmpSellers.getSex();
		case OBJECT_COL:
			return tmpSellers;
		default:
			return tmpSellers.getLatName();
		}
	}
}
	


