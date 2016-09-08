package ua.movies.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import AllDao.Dao;
import MainClass.AppMovies;
import ua.movies.arend.Arend;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class AddArendDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	
	private Dao dao;
	private AppMovies appMovies;
	
	
	
	private JTextField textFieldPrice;
	
	
	
	
	private Arend previousArend= null;
	private boolean updateMode = false;
	private JTextField textFieldStart;
	private JTextField textFieldEnd;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JComboBox comboBox;
	private JComboBox statusComboBox;
	private JLabel label_4;
	private JTextField textFieldUniqueMovieId;
	private JTextField textFieldClient;
	private JTextField textFieldMovieName;
	private JLabel label_5;
	private JLabel label_6;
	
	
	
	
	public AddArendDialog(AppMovies theAppMovies,
			Dao theSellerDAO, Arend thePreviousArend, boolean theUpdateMode) {
		this();
		dao = theSellerDAO;
		appMovies = theAppMovies;

		previousArend= thePreviousArend;
		
		updateMode = theUpdateMode;

		if (updateMode) {
			setTitle("Update Employee");
			
			populateGui(previousArend);
		}
	}
	
	
	private void populateGui(Arend theArend) {
		//spinnerDate.setValue(theArend.getStartDate());
		//spinnerDate2.setValue(theArend.getEndDate());
		textFieldUniqueMovieId.setText(String.valueOf(theArend.getMovie_id()));
		textFieldClient.setText(String.valueOf(theArend.getUniqueID()));
		textFieldStart.setText(theArend.getStartDate());
		textFieldEnd.setText(theArend.getEndDate());
		statusComboBox.getEditor().setItem(theArend.getStatus());	
		
		textFieldPrice.setText(theArend.getPrice().toString());
		
		
	}

	
	
	public AddArendDialog(AppMovies theEmployeeSearchApp,
			Dao theEmployeeDAO) {
		this(theEmployeeSearchApp, theEmployeeDAO, null, false);
	}
	

	

	/**
	 * Create the dialog.
	 */
	public AddArendDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			
			  
		}
		{
			label = new JLabel("\u0414\u0430\u0442\u0430 \u043F\u043E\u0447\u0430\u0442\u043A\u0443 \u043F\u0440\u043E\u043A\u0430\u0442\u0443");
			contentPanel.add(label, "2, 2");
		}
		{
			textFieldStart = new JTextField();
			contentPanel.add(textFieldStart, "8, 2, fill, default");
			textFieldStart.setColumns(10);
		}
		{
			label_1 = new JLabel("\u0414\u0430\u0442\u0430 \u043A\u0456\u043D\u0446\u044F \u043F\u0440\u043E\u043A\u0430\u0442\u0443");
			contentPanel.add(label_1, "2, 4");
		}
		{
			textFieldEnd = new JTextField();
			contentPanel.add(textFieldEnd, "8, 4, fill, default");
			textFieldEnd.setColumns(10);
		}
		{
			label_2 = new JLabel("\u0421\u0442\u0430\u0442\u0443\u0441");
			contentPanel.add(label_2, "2, 6");
		}
		{
			
			String[] StatusString = { "прострочений", "прокат" };
			 statusComboBox = new JComboBox(StatusString);
			contentPanel.add(statusComboBox, "8, 6, fill, default");
		}
		{
			label_3 = new JLabel("\u0426\u0456\u043D\u0430");
			contentPanel.add(label_3, "2, 8");
		}
		{
			textFieldPrice = new JTextField();
			contentPanel.add(textFieldPrice, "8, 8, fill, default");
			textFieldPrice.setColumns(10);
		}
		{
			label_4 = new JLabel("\u041D\u043E\u043C\u0435\u0440 \u0444\u0456\u043B\u044C\u043C\u0443 ");
			contentPanel.add(label_4, "2, 10");
		}
		{
			textFieldUniqueMovieId = new JTextField();
			contentPanel.add(textFieldUniqueMovieId, "8, 10, fill, default");
			textFieldUniqueMovieId.setColumns(10);
		}
		{
			label_5 = new JLabel("\u041D\u043E\u043C\u0435\u0440 \u043A\u043B\u0456\u0454\u043D\u0442\u0443");
			contentPanel.add(label_5, "2, 12");
		}
		{
			textFieldClient = new JTextField();
			contentPanel.add(textFieldClient, "8, 12, fill, default");
			textFieldClient.setColumns(10);
		}
		{
			label_6 = new JLabel("\u041D\u0430\u0437\u0432\u0430 \u0444\u0456\u043B\u044C\u043C\u0443(\u0430\u0432\u0442\u043E\u043C\u0430\u0442\u0438\u0447\u043D\u043E)");
			contentPanel.add(label_6, "2, 14");
		}
		{
			textFieldMovieName = new JTextField();
			textFieldMovieName.setEditable(false);
			textFieldMovieName.setEnabled(false);
			contentPanel.add(textFieldMovieName, "8, 14, fill, default");
			textFieldMovieName.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u0414\u043E\u0434\u0430\u0442\u0438");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveArend();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	
	protected void saveArend() {
		
		// get the employee info from gui
		String movieName = textFieldMovieName.getText();
		int uniqueId = Integer.parseInt(textFieldClient.getText());	
		int movieId = Integer.parseInt(textFieldUniqueMovieId.getText());	
			String formatStartDate = textFieldStart.getText();
		    String endDate = textFieldEnd.getText();
		    String status =  statusComboBox.getSelectedItem().toString();		
				String priceStr = textFieldPrice.getText();
				BigDecimal price = convertStringToBigDecimal(priceStr);
				

				Arend tempArend = null;
					
				if (updateMode) {
					
					tempArend = previousArend;
					tempArend.setUniqueID(uniqueId);
					tempArend.setMovie_id(movieId);
					tempArend.setStartDate(formatStartDate);
					tempArend.setEndDate(endDate);
				    tempArend.setStatus(status);
					tempArend.setPrice(price);
				
					
				}else {
					tempArend = new Arend(movieName , uniqueId, movieId,formatStartDate, endDate, status, price);
				}
				
				
				
				
				try {
					if (updateMode) {
					dao.updateArend(tempArend);
					}else {
						dao.addArends(tempArend);
					}

					// close dialog
					setVisible(false);
					dispose();

					// refresh gui list
					appMovies.refreshArendView();;
					
					// show success message
					JOptionPane.showMessageDialog(appMovies,
							"Information about arend added succesfully.",
							"Arend Added",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(
							appMovies,
							"Error saving arend: "
									+ exc.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
		
	}


	private BigDecimal convertStringToBigDecimal(String salaryStr) {
		BigDecimal result = null;

		try {
			double salaryDouble = Double.parseDouble(salaryStr);

			result = BigDecimal.valueOf(salaryDouble);
		} catch (Exception exc) {
			System.out.println("Invalid value. Defaulting to 0.0");
			result = BigDecimal.valueOf(0.0);
		}

		return result;
	}

}
