package ua.movies.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import AllDao.Dao;
import MainClass.AppMovies;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import ua.movies.sellers.*;
import javax.swing.JComboBox;

public class AddSellerDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField firstNameTextField;
	private JTextField LastNameTextField;
	private Dao sellerDao;
	private AppMovies appMovies;
	private JComboBox SexComboBox;
	
	private Seller previousSeller = null;
	private boolean updateMode = false;
	
	
	public AddSellerDialog(AppMovies movies,
			Dao theSellerDAO, Seller thePreviousSeller, boolean theUpdateMode) {
		this();
		sellerDao = theSellerDAO;
		appMovies = movies;

		previousSeller = thePreviousSeller;
		
		updateMode = theUpdateMode;

		if (updateMode) {
			setTitle("Update Seller");
			
			populateGui(previousSeller);
		}
	}
	
	
	
	
	private void populateGui(Seller theEmployee) {

		firstNameTextField.setText(theEmployee.getFirstName());
		LastNameTextField.setText(theEmployee.getLatName());
		SexComboBox.getEditor().setItem(theEmployee.getSex());	
	}
	
	public AddSellerDialog(AppMovies mov,
			Dao theSellerDAO) {
		this(mov, theSellerDAO, null, false);
	}
	
	
	
	/*public AddSellerDialog(AppMovies appMovies, Dao theSellerDAO) {
		this();
		this.appMovies = appMovies;
		sellerDao = theSellerDAO;
	}*/
	


	
	
	
	protected BigDecimal convertStringToBigDecimal(String salaryStr) {

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
	
	
	protected void saveSeller() {

		// get the employee info from gui
		String firstName = firstNameTextField.getText();
		String lastName = LastNameTextField.getText();
		String sex = SexComboBox.getSelectedItem().toString();
	

		
		Seller tempEmployee = null;

		if (updateMode) {
			tempEmployee = previousSeller;
			
			tempEmployee.setLatName(lastName);
			tempEmployee.setFirstName(firstName);
			tempEmployee.setSex(sex);
			
		} else {
			tempEmployee = new Seller(lastName, firstName,sex);
		}

		try {
			// save to the database
			if (updateMode) {
				sellerDao.updateEmployee(tempEmployee);
			} else {
				sellerDao.addSeller(tempEmployee);
			}

			// close dialog
			setVisible(false);
			dispose();

			// refresh gui list
			appMovies.refreshEmployeesView();

			// show success message
			JOptionPane.showMessageDialog(appMovies,
					"Seller saved succesfully.", "Employee Saved",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(appMovies,
					"Error saving seller: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	
	

	/**
	 * Create the dialog.
	 */
	public AddSellerDialog() {
		setTitle("\u0414\u043E\u0434\u0430\u0432\u0430\u043D\u043D\u044F \u043F\u0440\u043E\u0434\u0430\u0432\u0446\u044F");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("84px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("328px:grow"),},
			new RowSpec[] {
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel label = new JLabel("\u0406\u043C'\u044F");
			contentPanel.add(label, "2, 2, right, center");
		}
		{
			firstNameTextField = new JTextField();
			contentPanel.add(firstNameTextField, "4, 2, fill, top");
			firstNameTextField.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u041F\u0440\u0456\u0437\u0432\u0438\u0449\u0435");
			contentPanel.add(label, "2, 4, right, center");
		}
		{
			LastNameTextField = new JTextField();
			contentPanel.add(LastNameTextField, "4, 4, fill, top");
			LastNameTextField.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u0421\u0442\u0430\u0442\u044C");
			contentPanel.add(label, "2, 6, right, center");
		}
		{
			
			String[] SexStrings = { "чоловік", "жінка" };
			 SexComboBox = new JComboBox(SexStrings);
			
			contentPanel.add(SexComboBox, "4, 6, fill, default");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u0414\u043E\u0434\u0430\u0442\u0438");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveSeller();
						
						
						
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

}
