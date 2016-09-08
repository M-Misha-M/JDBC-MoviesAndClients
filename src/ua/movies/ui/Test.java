package ua.movies.ui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.sql.*;

public class Test extends JFrame {
	private JTextField textFieldTest;

	
	
	public Test() {
	    super("Month Spinner");
	    setTitle("\u0417\u0432\u0456\u0442");
	    setSize(442, 318);
	    setDefaultCloseOperation(HIDE_ON_CLOSE);
	    getContentPane().setLayout(new BorderLayout(0, 0));
	    
	    JPanel panel = new JPanel();
	    FlowLayout flowLayout = (FlowLayout) panel.getLayout();
	    flowLayout.setAlignment(FlowLayout.LEFT);
	    getContentPane().add(panel, BorderLayout.NORTH);
	    
	    JLabel lblNewLabel_2 = new JLabel("\u041C\u0456\u0441\u044F\u0446\u044C");
	    panel.add(lblNewLabel_2);
	    
	    JLabel lblNewLabel_1 = new JLabel("\u0420\u0456\u043A");
	    panel.add(lblNewLabel_1);
	    
	    JLabel lblNewLabel = new JLabel("\u0421\u0443\u043C\u043C\u0430 \u0437\u0430 \u043C\u0456\u0441\u044F\u0446\u044C");
	    panel.add(lblNewLabel);
	    
	    JPanel panel_1 = new JPanel();
	    FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
	    flowLayout_1.setAlignment(FlowLayout.LEFT);
	    getContentPane().add(panel_1, BorderLayout.CENTER);
	    
	    JLabel lblYear = new JLabel("");
	    panel_1.add(lblYear);
	    
	    JLabel labelMonth = new JLabel("");
	    panel_1.add(labelMonth);
	    
	    JLabel resultLabel = new JLabel("");
	    panel_1.add(resultLabel);
	    
	    textFieldTest = new JTextField();
	    panel_1.add(textFieldTest);
	    textFieldTest.setColumns(10);
	    
	    JButton btnNewButton = new JButton("\u0412\u0438\u0432\u0435\u0441\u0442\u0438 \u0437\u0432\u0456\u0442");
	    panel_1.add(btnNewButton);
	    
	    JLabel label = new JLabel("\u0412\u0432\u0435\u0434\u0456\u0442\u044C \u043D\u043E\u043C\u0435\u0440 \u043C\u0456\u0441\u044F\u0446\u044F \u0449\u043E\u0431 \u0434\u0456\u0437\u043D\u0430\u0442\u0438\u0441\u044C \u0441\u0443\u043C\u043C\u0443 \u043F\u0440\u043E\u0434\u0430\u043D\u043E\u0433\u043E \u0442\u043E\u0432\u0430\u0440\u0443");
	    panel_1.add(label);
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		try {
	    	 Class.forName("com.mysql.jdbc.Driver");
	    	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cursova" , "root" , "root");
	    	
	    	String sql = "SELECT id_arend, YEAR(endDate) as SalesYear, "
          + "MONTH(endDate) as SalesMonth, "
          + "SUM(price) as TotalSales " + 
    "FROM arend where month(endDate) =? " + 
"GROUP BY YEAR(endDate), MONTH(endDate) " + 
"ORDER BY YEAR(endDate), MONTH(endDate)";
           
	    	
	    	PreparedStatement pst = conn.prepareStatement(sql);	    	
	    	
	    	pst.setString(1, textFieldTest.getText());
	    	ResultSet rs  = pst.executeQuery();  
	    	
	    	if(rs.next()) { 
	            String year = rs.getString("SalesYear");
	            labelMonth.setText(year);
	            
	            
	            String month = rs.getString("SalesMonth");
	            lblYear.setText(month);
	            
	            String result  = rs.getString("TotalSales");
	           resultLabel.setText(result);
	    	}
	           
	    		
	    }catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	    	}		
	    	
	    });

	    

	    setVisible(true);
	  }

	  public static void main(String args[]) {
	    new Test();
	  }
	   
		
		
		
		
	}
	

	
		
