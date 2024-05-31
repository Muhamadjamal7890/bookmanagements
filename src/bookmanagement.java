import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class bookmanagement {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bookmanagement window = new bookmanagement();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public bookmanagement() {
		initialize();
		connect ();
		table_load ();
		
	}
	
	

	Connection con ;
	PreparedStatement pst;
	ResultSet rs;
	
	
	public void connect ()
	  {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanagement","root","");
		}
		catch (ClassNotFoundException | SQLException ex)
		{
			
		}
	  }
	
	
	public void table_load ()
	{
		try
		{
			pst = con.prepareStatement("select * from book");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			 
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 708, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setBounds(247, 11, 162, 37);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 71, 314, 151);
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 26, 80, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 62, 64, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(10, 97, 64, 14);
		panel.add(lblNewLabel_1_1_1);
		
		txtbname = new JTextField();
		txtbname.setBounds(124, 25, 166, 20);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(124, 61, 136, 20);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(124, 96, 105, 20);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBounds(20, 233, 89, 43);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price;
				
				
				
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				
				try {
					    pst = con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
					    pst.setString(1, bname);
					    pst.setString(2, edition);
					    pst.setString(3, price);
					    pst.executeUpdate();
					    JOptionPane.showMessageDialog(null,"Record Added !!!!!");
					    table_load ();
					    txtbname.setText("");
					    txtedition.setText("");
					    txtprice.setText("");
					    txtbname.requestFocus();
					    
					
				}    
				    catch (SQLException e1) {
				    	e1.printStackTrace();
				    
				    }
				
				
				
			}

			 {
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(117, 233, 89, 43);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
				
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(btnExit);
		
		JButton btnNewButton_1_1 = new JButton("Clear");
		btnNewButton_1_1.setBounds(217, 233, 89, 43);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				txtbname.setText("");
			    txtedition.setText("");
			    txtprice.setText("");
			    txtbname.requestFocus();
				
				
				
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(btnNewButton_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(361, 70, 321, 207);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 300, 324, 75);
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Book ID");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_2.setBounds(23, 26, 71, 27);
		panel_1.add(lblNewLabel_1_1_2);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				try {
					String id = txtbid.getText();
					pst = con.prepareStatement("select name,edition,price from book where id = ?");
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();
					
			if(rs.next()==true)
			{
				String name = rs.getString(1);
				String edition = rs.getString(2);
				String price  = rs.getString(3);
				txtbname.setText(name);
				txtedition.setText(edition);
				txtprice.setText(price);
			}
				
			else
			{
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				
			}
					
	
				}
				
				catch (SQLException ex) {
					
				}
				
				
				
					
				
			
					
			}	
				
				
		});
		
		txtbid.setColumns(10);
		txtbid.setBounds(104, 25, 189, 33);
		panel_1.add(txtbid);
		
		JButton btnNewButton_1_1_1 = new JButton("UPDATE");
		btnNewButton_1_1_1.setBounds(414, 316, 102, 43);
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
                 String bname,edition,price,bid;
				
				
				
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				bid = txtbid.getText();
				try {
					    pst = con.prepareStatement("update book set name =?, edition=?, price=? where id = ? ");
					    pst.setString(1, bname);
					    pst.setString(2, edition);
					    pst.setString(3, price);
					    pst.setString(4, bid);
					    pst.executeUpdate();
					    JOptionPane.showMessageDialog(null,"Record Updated !!!!!");
					    table_load ();
					    txtbname.setText("");
					    txtedition.setText("");
					    txtprice.setText("");
					    txtbname.requestFocus();
					    
					
				}    
				    catch (SQLException e1) {
				    	e1.printStackTrace();
				    
				    }
			
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_1_1 = new JButton("DELETE");
		btnNewButton_1_1_1_1.setBounds(537, 316, 89, 43);
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
               String bid;
				
			
				
				bid = txtbid.getText();
				
				try {
					    pst = con.prepareStatement("delete from book where id = ? ");
					   
					    pst.setString(1, bid);
					    pst.executeUpdate();
					    JOptionPane.showMessageDialog(null,"Record Deleted ");
					    table_load ();
					    txtbname.setText("");
					    txtedition.setText("");
					    txtprice.setText("");
					    txtbname.requestFocus();
					    
					
				}    
				    catch (SQLException e1) {
				    	e1.printStackTrace();
				    
				    }
				
				
				
				
				
				
				
				
				
				
			}
		});
		btnNewButton_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(btnNewButton_1_1_1_1);
	}

	protected void Tableload() {
		// TODO Auto-generated method stub
		
	}
}
