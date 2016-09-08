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
import ua.movies.movie.Movies;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.awt.event.ActionEvent;

public class AddMovieDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField MovieNameTextField;
	JComboBox genreComboBox;
	private JTextField ReleaseDateTextField;
	private JTextField UniqueIdTextField;
	private Dao sellerDao;
	private AppMovies appMovies;
	
	private Movies previousMovies = null;
	private boolean updateMode = false;
	
	
	public AddMovieDialog(AppMovies theAppMovies, Dao theSellerDAO , Movies thepreviousMovies , boolean theUpdateMode ) {
		this();
		sellerDao = theSellerDAO;
		appMovies = theAppMovies;
		
		previousMovies = thepreviousMovies;
		
		updateMode = theUpdateMode;
		
		
		if (updateMode) {
			setTitle("Update Movie");
			
			populateGui(previousMovies);
		}
	}

	private void populateGui(Movies theMovie) {
		MovieNameTextField.setText(theMovie.getMovie_name() );
		 //genreComboBox.setToolTipText(theMovie.getGenre());
		genreComboBox.getEditor().setItem(theMovie.getGenre());
		 ReleaseDateTextField.setText(theMovie.getRelease());
		 UniqueIdTextField.setText(String.valueOf(theMovie.getUnique_id()));
		
	}
	
	public AddMovieDialog(AppMovies movies ,
			Dao sellerDao ) {
		this(movies, sellerDao, null, false);
	}	
	
	protected void saveMovie() {
		
		String movieName = MovieNameTextField.getText();
		String genre = genreComboBox.getSelectedItem().toString();
		String release = ReleaseDateTextField.getText();
		int uniqueId = Integer.parseInt(UniqueIdTextField.getText());
		
		
		Movies tempMovies = null;
		
		
		if(updateMode) {
			tempMovies = previousMovies;
			
			tempMovies.setMovie_name(movieName);
			tempMovies.setGenre(genre);
			tempMovies.setRelease(release);
			tempMovies.setUnique_id(uniqueId);
		}else {
			tempMovies = new Movies(movieName, genre, release, uniqueId);
		}
		
		try {
			
			if(updateMode) {
				sellerDao.updateMovie(tempMovies);
			}else {
				sellerDao.addMovie(tempMovies);
			}
			
			
			setVisible(false);
			dispose();
			appMovies.refreshMoviesView();
			
			JOptionPane.showMessageDialog(appMovies,
					"Movies added succesfully.",
					"Movie Added",
					JOptionPane.INFORMATION_MESSAGE);
			
		}catch(Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
			
		}
		
		
	}
	

	/**
	 * Create the dialog.
	 */
	public AddMovieDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel label = new JLabel("\u041D\u0430\u0437\u0432\u0430");
			contentPanel.add(label, "2, 2, right, default");
		}
		{
			MovieNameTextField = new JTextField();
			contentPanel.add(MovieNameTextField, "4, 2, fill, default");
			MovieNameTextField.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u0416\u0430\u043D\u0440");
			contentPanel.add(label, "2, 4, right, default");
		}
		{
			String[] genreStrings = { "Екшн", "Мультфільм" , "Фантастика" , "Жахи" , "Пригоди" };
			 genreComboBox = new JComboBox(genreStrings);
			contentPanel.add(genreComboBox, "4, 4, fill, default");
		}
		{
			JLabel label = new JLabel("\u0414\u0430\u0442\u0430 \u0432\u0438\u043F\u0443\u0441\u043A\u0443 \u0444\u0456\u043B\u044C\u043C\u0430");
			contentPanel.add(label, "2, 6, right, default");
		}
		{
			ReleaseDateTextField = new JTextField();
			contentPanel.add(ReleaseDateTextField, "4, 6, fill, default");
			ReleaseDateTextField.setColumns(10);
		}
		{
			JLabel lblId = new JLabel("Id \u043F\u0440\u043E\u0434\u0430\u0432\u0446\u044F, \u0449\u043E \u043F\u0440\u043E\u0434\u0430\u0454 \u0444\u0456\u043B\u044C\u043C");
			contentPanel.add(lblId, "2, 8, right, default");
		}
		{
			UniqueIdTextField = new JTextField();
			contentPanel.add(UniqueIdTextField, "4, 8, fill, default");
			UniqueIdTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u0417\u0431\u0435\u0440\u0435\u0433\u0442\u0438");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						saveMovie();
						
						
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
