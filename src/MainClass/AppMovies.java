package MainClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import AllDao.Dao;
import ua.movies.arend.Arend;
import ua.movies.movie.Movies;
import ua.movies.sellers.Seller;
import ua.movies.ui.AddArendDialog;
import ua.movies.ui.AddMovieDialog;
import ua.movies.ui.AddSellerDialog;
import ua.movies.ui.TableModel;
import ua.movies.ui.TableModelForArends;
import ua.movies.ui.TableModelMovies;
import ua.movies.ui.Test;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;

public class AppMovies extends JFrame {

	private static JPanel contentPane;
	private JTextField lastNameTextField;
	private Dao sellerDAO;

	
	
	
	private JTable table_1;
	private JTextField textFindField;
	private JTable tableMovies;
	private JTable table;
	private JTable table_2;
	private JTextField FindArendTextField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppMovies frame  = new AppMovies();
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	

	/**
	 * Create the frame.
	 */
	public AppMovies() {
		
try {
			
	 sellerDAO = new Dao();
			
		}catch(Exception exc) {
			JOptionPane.showMessageDialog(this, "Error " + exc , "Error ", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		 JFrame frame = new JFrame();
		 frame.setTitle("\u041F\u0440\u043E\u043A\u0430\u0442 \u0444\u0456\u043B\u044C\u043C\u0456\u0432");
		  frame.setSize(699, 535);
		 JTabbedPane tabby = new JTabbedPane();
		 JPanel panel1= new JPanel();
	        JPanel panel2 = new JPanel();
	        JPanel panel3 = new JPanel();
	        JPanel panel4 = new JPanel();
	        
	        //додаємо панелі у JTabbedPane
	        tabby.addTab("Клієнти", panel1);
	        panel1.setLayout(new BorderLayout(0, 0));
	        
	        JPanel panel = new JPanel();
	        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
	        flowLayout.setAlignment(FlowLayout.LEFT);
	        panel1.add(panel, BorderLayout.NORTH);
	        
	      
	       
	        
	        lastNameTextField = new JTextField();
	        panel.add(lastNameTextField);
	        lastNameTextField.setColumns(10);
	        frame.getContentPane().setLayout(new BorderLayout(0, 0));
	        
	        JButton button = new JButton("\u0414\u043E\u0434\u0430\u0442\u0438");
	        button.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		AddSellerDialog dialog = new AddSellerDialog(AppMovies.this , sellerDAO);
	        		
	        		dialog.setVisible(true);
	        	}
	        });
	        
	        JButton button_9 = new JButton("\u0412\u0438\u0432\u0456\u0434 \u0456\u043D\u0444\u043E");
	        button_9.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		List<Seller> seller = null;
	        		try {
						seller = sellerDAO.getAllEmployees();
						
						
						
						TableModel model = new TableModel(seller);
						table_1.setModel(model);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        		
	        		
	        	}
	        });
	        panel.add(button_9);
	        panel.add(button);
	        
	        JButton button_1 = new JButton("\u041E\u043D\u043E\u0432\u0438\u0442\u0438");
	        button_1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		
	        		int row = table_1.getSelectedRow();
					
					// make sure a row is selected
					if (row < 0) {
						JOptionPane.showMessageDialog(AppMovies.this, "You must select an employee", "Error",
								JOptionPane.ERROR_MESSAGE);				
						return;
					}
					
					// get the current employee
					Seller tempEmployee = (Seller) table_1.getValueAt(row, TableModel.OBJECT_COL);
					
					// create dialog
					AddSellerDialog dialog = new AddSellerDialog(AppMovies.this, sellerDAO, 
																tempEmployee, true);

					// show dialog
					dialog.setVisible(true);
				
	        	}
	        });
	        panel.add(button_1);
	        
	        JButton button_2 = new JButton("\u0412\u0438\u0434\u0430\u043B\u0438\u0442\u0438");
	        button_2.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		
	        		try {
						// get the selected row
						int row = table_1.getSelectedRow();

						// make sure a row is selected
						if (row < 0) {
							JOptionPane.showMessageDialog(AppMovies.this, 
									"You must select an employee", "Error", JOptionPane.ERROR_MESSAGE);				
							return;
						}

						// prompt the user
						int response = JOptionPane.showConfirmDialog(
								AppMovies.this, "Delete this employee?", "Confirm", 
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

						if (response != JOptionPane.YES_OPTION) {
							return;
						}

						// get the current employee
						Seller tempEmployee = (Seller) table_1.getValueAt(row, TableModel.OBJECT_COL);

						// delete the employee
						sellerDAO.deleteEmployee(tempEmployee.getUnique_id());

						// refresh GUI
						refreshEmployeesView();

						// show success message
						JOptionPane.showMessageDialog(AppMovies.this,
								"Employee deleted succesfully.", "Employee Deleted",
								JOptionPane.INFORMATION_MESSAGE);

					} catch (Exception exc) {
						JOptionPane.showMessageDialog(AppMovies.this,
								"Error deleting employee: " + exc.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
	        		
	        	}
	        });
	        panel.add(button_2);
	        
	        JButton btnSearch = new JButton("\u041F\u043E\u0448\u0443\u043A \u043F\u043E \u043F\u0440\u0438\u0437\u0432\u0456\u0449\u0443");
	        btnSearch.addActionListener(new ActionListener() {
	        	
	        	
	        	
	        	
	        	
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		try {
	        		String lastName  = lastNameTextField.getText();
					List<Seller> seller = null;
					
					if(lastName != null && lastName.trim().length() > 0 ) {
						seller = sellerDAO.searchEmployees(lastName);
					}else {
						seller = sellerDAO.getAllEmployees();
					}
					
					
					
					TableModel model = new TableModel(seller);
					table_1.setModel(model);
					
					
	        	}catch(Exception exc) {
					JOptionPane.showMessageDialog(AppMovies.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
	        		
	        	}	
	        	
	        });
	        panel.add(btnSearch);
	        
	        JScrollPane scrollPane = new JScrollPane();
	        panel1.add(scrollPane, BorderLayout.CENTER);
	        
	        table_1 = new JTable();
	        scrollPane.setViewportView(table_1);
	        tabby.addTab("Фільми", panel2);
	        panel2.setLayout(new BorderLayout(0, 0));
	        
	        JPanel panel_1 = new JPanel();
	        FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
	        flowLayout_1.setAlignment(FlowLayout.LEFT);
	        panel2.add(panel_1, BorderLayout.NORTH);
	        
	        textFindField = new JTextField();
	        panel_1.add(textFindField);
	        textFindField.setColumns(10);
	        
	        JButton buttonMovieSearch = new JButton("\u041F\u043E\u0448\u0443\u043A");
	        buttonMovieSearch.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		try {
		        		String movieName  = textFindField.getText();
						List<Movies> mov = null;
						
						if(movieName != null && movieName.trim().length() > 0 ) {
							mov = sellerDAO.searchMovies(movieName);
						}else {
							mov = sellerDAO.getAllMovies();
						}
						
						
						
						TableModelMovies model = new TableModelMovies(mov);
						tableMovies.setModel(model);
						
						
		        	}catch(Exception exc) {
						JOptionPane.showMessageDialog(AppMovies.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
					}	
	        		
	        		
	        	}
	        });
	        panel_1.add(buttonMovieSearch);
	        
	        JButton button_3 = new JButton("\u0414\u043E\u0434\u0430\u0442\u0438");
	        button_3.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		AddMovieDialog dialog = new AddMovieDialog(AppMovies.this , sellerDAO);
	        		dialog.setVisible(true);
	        		
	        	}
	        });
	        panel_1.add(button_3);
	        
	        JButton button_4 = new JButton("\u0412\u0438\u0434\u0430\u043B\u0438\u0442\u0438");
	        button_4.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		try {
						// get the selected row
						int row = tableMovies.getSelectedRow();

						// make sure a row is selected
						if (row < 0) {
							JOptionPane.showMessageDialog(AppMovies.this, 
									"You must select a movie", "Error", JOptionPane.ERROR_MESSAGE);				
							return;
						}

						// prompt the user
						int response = JOptionPane.showConfirmDialog(
								AppMovies.this, "Delete this movie?", "Confirm", 
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

						if (response != JOptionPane.YES_OPTION) {
							return;
						}

						// get the current employee
						Movies tempMovies = (Movies) tableMovies.getValueAt(row, TableModelMovies.OBJECT_COL);

						// delete the employee
						sellerDAO.deleteMovie(tempMovies.getMovie_id());

						// refresh GUI
						refreshMoviesView();

						// show success message
						JOptionPane.showMessageDialog(AppMovies.this,
								"Employee deleted succesfully.", "Movies Deleted",
								JOptionPane.INFORMATION_MESSAGE);

					} catch (Exception exc) {
						JOptionPane.showMessageDialog(AppMovies.this,
								"Error deleting movies: " + exc.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}

	        		
	        	}
	        });
	        panel_1.add(button_4);
	        
	        JButton button_5 = new JButton("\u041E\u043D\u043E\u0432\u0438\u0442\u0438");
	        button_5.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		int row = tableMovies.getSelectedRow();
	        		
	        		if (row < 0) {
						JOptionPane.showMessageDialog(AppMovies.this, "Виберіть об'єкт", "Error",
								JOptionPane.ERROR_MESSAGE);		
						return;
	        		
	        	   }
	        		
	        		
	        		Movies tempMovies = (Movies) tableMovies.getValueAt(row, TableModelMovies.OBJECT_COL);
	        		AddMovieDialog dialog = new AddMovieDialog(AppMovies.this, sellerDAO, 
							tempMovies, true);
	        		dialog.setVisible(true);
	        	}
	        });
	        panel_1.add(button_5);
	        
	        JButton button_10 = new JButton("\u0412\u0438\u0432\u0456\u0434 \u0456\u043D\u0444\u043E");
	        button_10.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		List<Movies> mov = null;
	        		try {
						mov = sellerDAO.getAllMovies();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				
				
				
				TableModelMovies model = new TableModelMovies(mov);
				tableMovies.setModel(model);
				
	        		
	        		
	        		
	        	}
	        });
	        panel_1.add(button_10);
	        
	        JScrollPane scrollPane_1 = new JScrollPane();
	        panel2.add(scrollPane_1, BorderLayout.CENTER);
	        
	        tableMovies = new JTable();
	     
	     
	        scrollPane_1.setViewportView(tableMovies);
	        
	        
	        tabby.addTab("Клієнти-Фільми", panel3);
	        panel3.setLayout(new BorderLayout(0, 0));
	        
	        JPanel panel_2 = new JPanel();
	        FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
	        flowLayout_2.setAlignment(FlowLayout.LEFT);
	        panel3.add(panel_2, BorderLayout.NORTH);
	        
	        JButton btnTest = new JButton("\u041F\u043E\u0448\u0443\u043A");
	        btnTest.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		Connection con;
					try {
						con = DriverManager.getConnection
						        ("jdbc:mysql://localhost:3306/cursova" , "root" , "root");
						 Statement stmt = con.createStatement();
			                String query ="SELECT movie_name , seller_name ,seller_lastname from sellers inner join movies on sellers.unique_id = movies.unique_id";
			        		ResultSet rs = stmt.executeQuery(query);
			        				ResultSetMetaData md = rs.getMetaData();
			        				int columnCount = md.getColumnCount();

			        				String[] cols = {"Назва фільму" , "Ім'я" , "Призвіще"};
			        				

			        				DefaultTableModel model = new DefaultTableModel(cols, 0);
			        				

			        				while(rs.next()) {
			        				    Object[] row = new Object[columnCount];
			        				    for (int i = 1; i <= columnCount; i++) {
			        				        row[i - 1] = rs.getObject(i);
			        				    }
			        				    model.addRow(row);
			        				}

			        				table.setModel(model);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	               
	        		
	        		
	        	}
	        });
	        panel_2.add(btnTest);
	        
	        JCheckBox checkBox = new JCheckBox("\u041F\u0440\u0456\u0437\u0432. \u043A\u043B\u0456\u0454\u043D\u0442\u0456\u0432  \u044F\u043A\u0456 \u043C\u0430\u044E\u0442\u044C \u0434\u0435\u043A\u0456\u043B\u044C\u043A\u0430 \u0444\u0456\u043B\u044C\u043C\u0456\u0432" , false);
	        checkBox.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (checkBox.isSelected()) {
						
						Connection con;
						try {
							con = DriverManager.getConnection
							        ("jdbc:mysql://localhost:3306/cursova" , "root" , "root");
							 Statement stmt = con.createStatement();
				                String query ="SELECT p.seller_name , p.seller_lastname , s.movie_name "
				                		 + "FROM sellers p  INNER JOIN movies s ON  p.unique_id = s.unique_id "
				                		+ "WHERE p.unique_id IN(SELECT unique_id FROM movies "
				                		 + "GROUP BY unique_id HAVING COUNT(*) > 1)";

				        		ResultSet rs = stmt.executeQuery(query);
				        				ResultSetMetaData md = rs.getMetaData();
				        				int columnCount = md.getColumnCount();

				        				String[] cols = {"Назва фільму" , "Ім'я" , "Призвіще"};
				        				

				        				DefaultTableModel model = new DefaultTableModel(cols, 0);
				        				

				        				while(rs.next()) {
				        				    Object[] row = new Object[columnCount];
				        				    for (int i = 1; i <= columnCount; i++) {
				        				        row[i - 1] = rs.getObject(i);
				        				    }
				        				    model.addRow(row);
				        				}

				        				table.setModel(model);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						
						
						
						
						
					}
					
				}
			});
	        
	        panel_2.add(checkBox);
	        
	        JScrollPane scrollPane_2 = new JScrollPane();
	        panel3.add(scrollPane_2, BorderLayout.CENTER);
	        
	        table = new JTable();
	        scrollPane_2.setViewportView(table);
	        
	        
	        
	        tabby.addTab("Аренда", panel4);
	        panel4.setLayout(new BorderLayout(0, 0));
	        
	        JPanel panel_3 = new JPanel();
	        FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
	        flowLayout_3.setAlignment(FlowLayout.LEFT);
	        panel4.add(panel_3, BorderLayout.NORTH);
	        
	        FindArendTextField = new JTextField();
	        panel_3.add(FindArendTextField);
	        FindArendTextField.setColumns(10);
	        
	        JButton button_6 = new JButton("\u0412\u0438\u0432\u0435\u0441\u0442\u0438 \u0456\u043D\u0444\u043E\u0440\u043C\u0430\u0446\u0456\u044E");
	        button_6.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		try {
		        		
						List<Arend> ar = null;
					
						  
							   ar = sellerDAO.getAllArends(); 						  
						   					
						
						TableModelForArends model = new TableModelForArends(ar);
						table_2.isEditing();
						table_2.setModel(model);
						
						
		        	}catch(Exception exc) {
						JOptionPane.showMessageDialog(AppMovies.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
					}	
	        		
	        		
	        		
	        		
	        	}
	        });
	        panel_3.add(button_6);
	        
	        JButton btnTst = new JButton("\u041F\u043E\u0448\u0443\u043A \u043F\u043E \u043D\u043E\u043C\u0435\u0440\u0443 \u0444\u0456\u043B\u044C\u043C\u0443");
	        btnTst.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		List<Arend> list = new ArrayList<>();

        			PreparedStatement myStmt = null;
        			ResultSet myRs = null;

        			try {
        				Connection mycon = DriverManager.getConnection
        		                ("jdbc:mysql://localhost:3306/cursova" , "root" , "root");
        				int movieId = Integer.parseInt(FindArendTextField.getText());
        				
        				myStmt = mycon.prepareStatement("SELECT movies.movie_name , arend.unique_id , arend.movie_id , arend.startDate," +  
" arend.endDate , arend.status , arend.price from movies inner join arend on" + 
" movies.movie_id = arend.movie_id where movies.movie_id like ?");
        				
        				//myStmt.setString(1, movieId);
        				myStmt.setInt(1, movieId);
        				
        				myRs = myStmt.executeQuery();
        				
        				while (myRs.next()) {
        					Arend tempSeller = sellerDAO.convertRowToArend(myRs);
        					list.add(tempSeller);
        				}
					
					
					TableModelForArends model = new TableModelForArends(list);
					table_2.setModel(model);
        		
        		
        		}catch(Exception exc) {
					JOptionPane.showMessageDialog(AppMovies.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}finally {
					try {
						sellerDAO.close(myStmt, myRs);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
        		
					}
	        	}
        	
        });
	        		
	        	
	        		
	   
	       
	        panel_3.add(btnTst);
	        
	        JPanel panel_4 = new JPanel();
	        FlowLayout flowLayout_4 = (FlowLayout) panel_4.getLayout();
	        flowLayout_4.setAlignment(FlowLayout.LEFT);
	        panel4.add(panel_4, BorderLayout.SOUTH);
	        
	        JButton button_7 = new JButton("\u0412\u0438\u0434\u0430\u043B\u0438\u0442\u0438 ");
	        button_7.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		try {
						// get the selected row
						int row = table_2.getSelectedRow();

						// make sure a row is selected
						if (row < 0) {
							JOptionPane.showMessageDialog(AppMovies.this, 
									"Виберіть рядок", "Error", JOptionPane.ERROR_MESSAGE);				
							return;
						}

						// prompt the user
						int response = JOptionPane.showConfirmDialog(
								AppMovies.this, "Видалити?", "Confirm", 
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

						if (response != JOptionPane.YES_OPTION) {
							return;
						}

						// get the current employee
						Arend tempEmployee = (Arend) table_2.getValueAt(row, TableModelForArends.OBJECT_COL);

						// delete the employee
						sellerDAO.deleteArend(tempEmployee.getArendID());

						// refresh GUI
						refreshArendView();

						// show success message
						JOptionPane.showMessageDialog(AppMovies.this,
								"Arend deleted succesfully.", "Arend Deleted",
								JOptionPane.INFORMATION_MESSAGE);

					} catch (Exception exc) {
						JOptionPane.showMessageDialog(AppMovies.this,
								"Error deleting arend info: " + exc.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
	        		
	        		
	        		
	        	}
	        });
	        panel_4.add(button_7);
	        
	        JButton btnTrst = new JButton("\u0414\u043E\u0434\u0430\u0442\u0438");
	        btnTrst.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		AddArendDialog dialog = new AddArendDialog(AppMovies.this, sellerDAO);

					// show dialog
					dialog.setVisible(true);
	        		
	        		
	        		
	        		      		
	        		
	        	}
	        });
	        panel_4.add(btnTrst);
	        
	        JButton button_8 = new JButton("\u043E\u043D\u043E\u0432\u0438\u0442\u0438");
	        button_8.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		
	        		// get the selected item
					int row = table_2.getSelectedRow();
					
					// make sure a row is selected
					if (row < 0) {
						JOptionPane.showMessageDialog(AppMovies.this, "You must select an arend", "Error",
								JOptionPane.ERROR_MESSAGE);				
						return;
					}
					
					// get the current employee
					Arend tempArend = (Arend) table_2.getValueAt(row, TableModelForArends.OBJECT_COL);
					
					// create dialog
					AddArendDialog dialog = new AddArendDialog(AppMovies.this, sellerDAO, 
																tempArend, true);

					// show dialog
					dialog.setVisible(true);
				
	        		
					
	        		
	        		
	        	}
	        });
	        panel_4.add(button_8);
	        
	        JLabel label = new JLabel("\u0414\u043B\u044F \u043F\u043E\u0448\u0443\u043A\u0443 \u043F\u0440\u043E\u0441\u0442\u0440\u043E\u0447\u0435\u043D\u043E\u0433\u043E \u0442\u043E\u0432\u0430\u0440\u0443 \u043D\u0430\u0442\u0438\u0441\u043D\u0456\u0441\u0442\u044C \u0417\u0430\u043F\u0438\u0442\u0438-> \u043F\u0440. \u0442\u043E\u0432\u0430\u0440");
	        panel_4.add(label);
	        
	        JScrollPane scrollPane_3 = new JScrollPane();
	        panel4.add(scrollPane_3, BorderLayout.CENTER);
	        
	        table_2 = new JTable();
	        scrollPane_3.setViewportView(table_2);
	        JMenu fileMenu=new JMenu("Запити");
	        JMenuItem openItem = new JMenuItem ("Прострочений товар");
	        
	        JMenu fileMenu2=new JMenu("Звіт");
	        JMenuItem openItem2 = new JMenuItem ("Звіт по доходам");
	        openItem2.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		Test test = new Test();
	        		test.setVisible(true);
	        		
	        		
	        	}
	        });
	        
	        openItem.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		Connection con;
					try {
						con = DriverManager.getConnection
						        ("jdbc:mysql://localhost:3306/cursova" , "root" , "root");
						 Statement stmt = con.createStatement();
			                String query ="SELECT sellers.seller_name , seller_lastname , movies.movie_name, movies.movie_id , arend.status from sellers " + 
              "inner join movies on sellers.unique_id = movies.unique_id " + 
                    "inner join arend on movies.unique_id = arend.unique_id where status = 'прострочений'";
			        		ResultSet rs = stmt.executeQuery(query);
			        				ResultSetMetaData md = rs.getMetaData();
			        				int columnCount = md.getColumnCount();

			        				String[] cols = {"Ім'я клієнта" , "Призвіще" ,  "Назва фільму" , "Номер фільму" , "Статус"};
			        				

			        				DefaultTableModel model = new DefaultTableModel(cols, 0);
			        				

			        				while(rs.next()) {
			        				    Object[] row = new Object[columnCount];
			        				    for (int i = 1; i <= columnCount; i++) {
			        				        row[i - 1] = rs.getObject(i);
			        				    }
			        				    model.addRow(row);
			        				}

			        				table_2.setModel(model);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        		
	        		
	        		
	        	}
	        });
	        fileMenu.add(openItem);
	        fileMenu2.add(openItem2);
	    
	        JMenuBar menuBar = new JMenuBar();
	        menuBar.add(fileMenu);
	        menuBar.add(fileMenu2);
	        frame.setJMenuBar(menuBar);
	       
	       
	        
	        
	        // додаємо вкладки у фрейм
	        frame.getContentPane().add(tabby);
	        frame.setVisible(true);
	}
	
	
	
	





	public void refreshEmployeesView() {
		try {
			List<Seller> employees = sellerDAO.getAllEmployees();

			// create the model and update the "table"
			TableModel model = new TableModel(employees);

			table_1.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	
	
	public void refreshMoviesView() {
		try {
			List<Movies> movies = sellerDAO.getAllMovies();

			// create the model and update the "table"
			TableModelMovies model = new TableModelMovies(movies);
			
			tableMovies.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	
	
	public void refreshArendView() {
		try {
		List<Arend> employees = sellerDAO.getAllArends();

		// create the model and update the "table"
		TableModelForArends model = new TableModelForArends(employees);
         model.fireTableDataChanged();
		table_2.setModel(model);
	}catch (Exception exc) {
		JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
	}
	
	
	
}
