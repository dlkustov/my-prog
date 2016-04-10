package theater.com;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;

import java.awt.Dimension;
import javax.swing.JSplitPane;





import java.awt.Event;
import java.awt.Rectangle;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;


public class CMain extends JFrame {
	///////////////////////////////////////////////////////////////
	// Загрузка объектов классов из сторонних и моих либов!!!
	///////////////////////////////////////////////////////////////
	private CPrints m_cpPrints = null; 
	///////////////////////////////////////////////////////////////
	// КОНЕЦ ЗАГРУЗКИ объектов классов из сторонних и моих либов!!!
	///////////////////////////////////////////////////////////////

	private static final long serialVersionUID = 1L;
	static private CDialogEnterUser StartDialog = null;
	private javax.swing.JMenuBar jJMenuBarMain = null;
	private JTabbedPane jTabbedPaneMain = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	private JPanel jPanel4 = null;
	private JPanel jPanel5 = null;
	private JPanel jPanel6 = null;
	private JPanel jPanel7 = null;
	private JLabel jLabel = null;
	private JButton jButtonAdd = null;
	private JButton jButtonDelete = null;
	private JLabel jLabel1 = null;
	private JButton jButtonAddUser = null;
	private JButton jButtonDeleteUser = null;
	private JLabel jLabel2 = null;
	private JButton jButtonGreateOrders = null;
	private JLabel jLabel3 = null;
	private CTheaterPlace[] m_button_place = null;
	private CDialogEnterText m_DialogEnterText = null;
	private CDialogAddUser m_DialogAddUser = null;
	public static JButton jBtnTemp = null;
	public static CDialogStart m_DialogStart = null;
	public static CMain m_Main;
	public static int m_iRulesType = -1; // 0 - Админ, 1 - Кассир, от этого пляшет интерфейс!
	public static String m_stTitle = "";  //  @jve:decl-index=0:
	JPopupMenu popup;
	//ActionEvent e;
	
	private int m_iCountPlaceAll = 625;// Общее количество ячеек
	int m_i = 0; // Счетчик для ячеек!!!
	
	public static byte[] m_ByteSalt = {
        (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
        (byte)0x56, (byte)0x34, (byte)0xE3, (byte)0x03
    };
	
	public static SecretKey m_Key = null;
	public static DesEncrypter m_Encrypter = null;
	
	public static String m_stPathConnectCFG = "settings/Connect.cfg";  //  @jve:decl-index=0:
	
	public static CUserInfo m_UserInfo = null;

	public static JScrollPane jScrollPaneUsers = null;
	//public static JTable jTableUsers = null;

	public static JScrollPane jScrollPaneParam = null;
	public static JTable jTableParam = null;

	private JLabel jLabel4 = null;

	private JButton jButtonAddGenre = null;

	private JButton jButtonDeleteGenre = null;

	public static JScrollPane jScrollPaneGenre = null;
	public static JTable jTableGenre = null;
	
	public static String m_stDateCreate = null;

	private JLabel jLabel5 = null;

	public static JTextField jTextFieldStartDate = null;
	//public static JTextField jTextFieldStartDate = null;

	private JLabel jLabel6 = null;

	public static JTextField jTextFieldEndDate = null;
	
	private CWindowCalendar m_WindowCalendar = null;
	public static int m_iStartEndDateCurrentSelect = 0;

	private JLabel jLabel7 = null;

	private JComboBox jComboBoxSelectRoom = null;

	public static JScrollPane jScrollPaneSeanse = null;

	private JTable jTableSeanse = null;

	/**
	 * This method initializes jTabbedPaneMain	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPaneMain() {
		if (jTabbedPaneMain == null) {
			jTabbedPaneMain = new JTabbedPane();
			jTabbedPaneMain.setVisible(true);
			jTabbedPaneMain.setEnabled(true);
			
			jTabbedPaneMain.addTab("Репертуар", null, getJPanel1(), null);
			jTabbedPaneMain.addTab("Пользователи", null, getJPanel2(), null);
			jTabbedPaneMain.addTab("Жанры", null, getJPanel3(), null);
			jTabbedPaneMain.addTab("Отчеты", null, getJPanel4(), null);
			jTabbedPaneMain.addTab("Параметры", null, getJPanel5(), null);
			jTabbedPaneMain.addTab("Зрительный зал", null, getJPanel6(), BorderLayout.NORTH);
			jTabbedPaneMain.addTab("Монитор зрителя", null, getJPanel7(), null);
		
		}
		return jTabbedPaneMain;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(528, 40, 49, 25));
			jLabel7.setText("Зал:");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(280, 40, 89, 25));
			jLabel6.setText("по дату:");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(8, 40, 121, 25));
			jLabel5.setText("С даты:");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(8, 8, 105, 25));
			jLabel.setText("РЕПЕРТУАР ");
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.add(jLabel, null);
			jPanel1.add(getJButtonAdd(), null);
			jPanel1.add(getJButtonDelete(), null);
			jPanel1.add(jLabel5, null);
			jPanel1.add(getJTextFieldStartDate(), null);
			jPanel1.add(jLabel6, null);
			jPanel1.add(getJTextFieldEndDate(), null);
			jPanel1.add(jLabel7, null);
			jPanel1.add(getJComboBoxSelectRoom(), null);
			jPanel1.add(getJScrollPaneSeanse(), null);
		}
		return jPanel1;
	}
	private static void LoadResources()
	{
		try
		{
			CLoaderJar.addFile("lib/commons-beanutils-1.8.0.jar");
			CLoaderJar.addFile("lib/commons-collections-2.1.jar");
			CLoaderJar.addFile("lib/commons-digester-1.7.jar");
			CLoaderJar.addFile("lib/commons-javaflow-20060411.jar");
			CLoaderJar.addFile("lib/commons-logging-1.0.2.jar");
			CLoaderJar.addFile("lib/jasperreports-3.5.2.jar");
			CLoaderJar.addFile("lib/jcalendar-1.3.3.jar");
			CLoaderJar.addFile("lib/jdt-compiler-3.1.1.jar");
			CLoaderJar.addFile("lib/looks-2.0.1.jar");
			CLoaderJar.addFile("lib/mysql-connector-java-5.1.7-bin.jar");
			CLoaderJar.addFile("lib/log4j-1.2.16.jar");
		}
		catch (Exception e)
		{
			System.out.println("Косяк с загрузкой либов!!!");
		}
	}
	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Пользователи");
			jLabel1.setBounds(new Rectangle(8, 8, 137, 25));
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.add(jLabel1, null);
			jPanel2.add(getJButtonAddUser(), null);
			jPanel2.add(getJButtonDeleteUser(), null);
			jPanel2.add(getJScrollPaneUsers(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(8, 8, 105, 25));
			jLabel4.setText("ЖАНРЫ");
			jPanel3 = new JPanel();
			jPanel3.setLayout(null);
			jPanel3.add(jLabel4, null);
			jPanel3.add(getJButtonAddGenre(), null);
			jPanel3.add(getJButtonDeleteGenre(), null);
			jPanel3.add(getJScrollPaneGenre(), null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jPanel4	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(8, 8, 137, 25));
			jLabel2.setText("Отчеты");
			jPanel4 = new JPanel();
			jPanel4.setLayout(null);
			jPanel4.add(jLabel2, null);
			jPanel4.add(getJButtonGreateOrders(), null);
		}
		return jPanel4;
	}

	/**
	 * This method initializes jPanel5	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(8, 8, 153, 25));
			jLabel3.setText("Параметры");
			jPanel5 = new JPanel();
			jPanel5.setLayout(null);
			jPanel5.add(jLabel3, null);
			jPanel5.add(getJScrollPaneParam(), null);
		}
		return jPanel5;
	}

	/**
	 * This method initializes jPanel6	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			//GridLayout gridLayout = new GridLayout();
			//gridLayout.setRows(50);
			jPanel6 = new JPanel();
			jPanel6.setLayout(new GridLayout(25,25));
			m_button_place = new CTheaterPlace[m_iCountPlaceAll];//это только массив создан.
			//static final int i = 0;
			
			ActionListener listener = new ActionListener() {
		       // @Override
		        public void actionPerformed(ActionEvent e) {
		            if (e.getSource() instanceof JButton) {
		            	jBtnTemp = ((JButton)e.getSource());
		                String text = jBtnTemp.getText();
		                
		                	ActionListener actionListener = new ActionListener() {
							
							public void actionPerformed(ActionEvent e) {
								String stRes = e.getActionCommand();
							    if (stRes.equals("Сделать просто полом"))
							    {
							    	jBtnTemp.setBackground(Color.WHITE);
							    	jBtnTemp.setText("");
							    }
							    else if (stRes.equals("Создать место зрителя"))
							    	jBtnTemp.setBackground(Color.green);
							    else if (stRes.equals("Создать бронь"))
							    	jBtnTemp.setBackground(Color.RED);
							    else if (stRes.equals("Снять бронь"))
							    	jBtnTemp.setBackground(Color.GREEN);
							    else if (stRes.equals("Написать текст"))
							    {
							    	m_DialogEnterText = new CDialogEnterText(null);
							    	m_DialogEnterText.setModalityType(ModalityType.APPLICATION_MODAL);
							    	m_DialogEnterText.setVisible(true);
							    }
							    else if (stRes.equals("Удалить текст"))
							    {
							    	jBtnTemp.setText("");
							    	
							    }
							    else if (stRes.equals("Выделить другим цветом"))
							    {
							    	jBtnTemp.setBackground(Color.ORANGE);
							    	
							    }
							    
							    
								
							}
						};
		                
		                
		                //JOptionPane.showMessageDialog(null, text);
		                popup = new JPopupMenu();
		                JMenuItem numberMenuItem = new JMenuItem("Ячейка № " + text);
		                numberMenuItem.addActionListener(actionListener);
		                popup.add(numberMenuItem);
		                
		                numberMenuItem = new JMenuItem("Сделать просто полом");
		                numberMenuItem.addActionListener(actionListener);
		                popup.add(numberMenuItem);
		                
		                numberMenuItem = new JMenuItem("Создать место зрителя");
		                numberMenuItem.addActionListener(actionListener);
		                popup.add(numberMenuItem);
		                
		                numberMenuItem = new JMenuItem("Создать бронь");
		                numberMenuItem.addActionListener(actionListener);
		                popup.add(numberMenuItem);
		                
		                numberMenuItem = new JMenuItem("Снять бронь");
		                numberMenuItem.addActionListener(actionListener);
		                popup.add(numberMenuItem);
		                
		                numberMenuItem = new JMenuItem("Выделить другим цветом");
		                numberMenuItem.addActionListener(actionListener);
		                popup.add(numberMenuItem);
		                
		                numberMenuItem = new JMenuItem("Написать текст");
		                numberMenuItem.addActionListener(actionListener);
		                popup.add(numberMenuItem);
		                
		                numberMenuItem = new JMenuItem("Удалить текст");
		                numberMenuItem.addActionListener(actionListener);
		                popup.add(numberMenuItem);
		                
		                numberMenuItem = new JMenuItem("Продать билет");
		                numberMenuItem.addActionListener(actionListener);
		                popup.add(numberMenuItem);
		                
		                ((JButton) e.getSource()).addMouseListener(new MouseAdapter()
		                {
		                	public void mouseClicked(MouseEvent event) 
		                	{
		                		if (SwingUtilities.isLeftMouseButton(event))
		                		popup.show(jBtnTemp, event.getX(), event.getY());
		                		}
		                			
		                		});
		                
		            }
		        }
		    };
			for(m_i = 0; m_i < m_iCountPlaceAll; m_i++)
			{
				//m_button_place = new CTheaterPlace(Integer.toString(i));
				m_button_place[m_i] = new CTheaterPlace();// вызов конструктора для создания объектов
				m_button_place[m_i].setText(Integer.toString(m_i));
				m_button_place[m_i].setBackground(Color.WHITE);
				m_button_place[m_i].addActionListener(listener);
				//m_button_place[m_i].setFont(new Font("Sans Serif", Font.PLAIN, 6));
				//m_button_place = new CTheaterPlace();
				//m_button_place.setEnabled(false);
				//m_button_place[i].setText(Integer.toString(i));
				 //Add action listener to button
/*				m_button_place[i].addActionListener(new ActionListener() {
		 
		            public void actionPerformed(ActionEvent e)
		            {
		                //Execute when button is pressed
		                System.out.println(m_button_place[i].getText());
		            }
		        });  */    
				//m_button_place.setBorder()
				jPanel6.add(m_button_place[m_i]);
			}
			
			
			//Теперь когда массив содержит объекты можна с ними работать
			//final int b=0;
	/*		for(i = 0;i<625;i++)
			{
				m_button_place[i].setText(Integer.toString(i));
				m_button_place[i].addActionListener(new ActionListener() {
					 
		            public void actionPerformed(ActionEvent e)
		            {
		                //Execute when button is pressed
		                System.out.println(m_button_place[i].getText());
		            }
		        });
			}*/
			
			
			
			
			/*button = new JButton("Number 1");
			jPanel6.add(button);
			button = new JButton("Number 1");
			jPanel6.add(button);
			button = new JButton("Number 1");
			jPanel6.add(button);
			button = new JButton("Number 1");
			jPanel6.add(button);
			button = new JButton("Number 1");
			jPanel6.add(button);*/
		}
		return jPanel6;
	}

	/**
	 * This method initializes jPanel7	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel7() {
		if (jPanel7 == null) {
			jPanel7 = new JPanel();
			jPanel7.setLayout(null);
		}
		return jPanel7;
	}

	/**
	 * This method initializes jButtonAdd	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonAdd() {
		if (jButtonAdd == null) {
			jButtonAdd = new JButton();
			jButtonAdd.setBounds(new Rectangle(120, 8, 153, 25));
			jButtonAdd.setText("Добавить");
			jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					CDialogNewEventAdd DialogNewEventAdd = new CDialogNewEventAdd(null);
					DialogNewEventAdd.setVisible(true);
				}
			});
		}
		return jButtonAdd;
	}

	/**
	 * This method initializes jButtonDelete	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonDelete() {
		if (jButtonDelete == null) {
			jButtonDelete = new JButton();
			jButtonDelete.setBounds(new Rectangle(280, 8, 145, 25));
			jButtonDelete.setText("Удалить");
		}
		return jButtonDelete;
	}

	/**
	 * This method initializes jButtonAddUser	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonAddUser() {
		if (jButtonAddUser == null) {
			jButtonAddUser = new JButton();
			jButtonAddUser.setText("Добавить");
			jButtonAddUser.setBounds(new Rectangle(152, 8, 129, 25));
			jButtonAddUser.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					m_DialogAddUser = new CDialogAddUser(null);
					m_DialogAddUser.setVisible(true);
				}
			});
		}
		return jButtonAddUser;
	}

	/**
	 * This method initializes jButtonDeleteUser	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonDeleteUser() {
		if (jButtonDeleteUser == null) {
			jButtonDeleteUser = new JButton();
			jButtonDeleteUser.setText("Удалить");
			jButtonDeleteUser.setBounds(new Rectangle(288, 8, 121, 25));
			jButtonDeleteUser.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Object[] options = {"Да","Отмена"};
					
					int n = JOptionPane.showOptionDialog(null, "Вы действительно хотите удалить пользователя?",
							"Вопрос?",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,
							null,options,options[1]);
					if(n == 0)
					{
						if(CData.DeleteSelectedUser(CData.m_iSelectedIdUser))
						{
							CData.CreateTableUsers();
							JOptionPane.showMessageDialog(null, "Пользователь успешно удален!");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Ошибка удаления пользователя!");
						}
					}
				}
			});
		}
		return jButtonDeleteUser;
	}

	/**
	 * This method initializes jButtonGreateOrders	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonGreateOrders() {
		if (jButtonGreateOrders == null) {
			jButtonGreateOrders = new JButton();
			jButtonGreateOrders.setBounds(new Rectangle(160, 8, 177, 25));
			jButtonGreateOrders.setText("Сформировать");
		}
		return jButtonGreateOrders;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public static JScrollPane getJScrollPaneUsers() {
		if (jScrollPaneUsers == null) {
			jScrollPaneUsers = new JScrollPane();
			//jScrollPaneUsers.setLayout(null);
			jScrollPaneUsers.setBounds(new Rectangle(8, 40, 739, 291));
			//jScrollPaneUsers.setViewportView(getJTableUsers());
		}
		return jScrollPaneUsers;
	}

	/**
	 * This method initializes jScrollPaneParam	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public static JScrollPane getJScrollPaneParam() {
		if (jScrollPaneParam == null) {
			jScrollPaneParam = new JScrollPane();
			jScrollPaneParam.setBounds(new Rectangle(8, 40, 569, 140));
			//jScrollPaneParam.setViewportView(getJTableParam());
		}
		return jScrollPaneParam;
	}

	/**
	 * This method initializes jTableParam	
	 * 	
	 * @return javax.swing.JTable	
	 */
	/*private JTable getJTableParam() {
		if (jTableParam == null) {
			jTableParam = new JTable();
		}
		return jTableParam;
	}*/

	/**
	 * This method initializes jButtonAddGenre	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonAddGenre() {
		if (jButtonAddGenre == null) {
			jButtonAddGenre = new JButton();
			jButtonAddGenre.setBounds(new Rectangle(120, 8, 145, 25));
			jButtonAddGenre.setText("Добавить");
			jButtonAddGenre.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					CDialogAddGenre DialogAddGenre = new CDialogAddGenre(null);
					DialogAddGenre.setVisible(true);
				}
			});
		}
		return jButtonAddGenre;
	}

	/**
	 * This method initializes jButtonDeleteGenre	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonDeleteGenre() {
		if (jButtonDeleteGenre == null) {
			jButtonDeleteGenre = new JButton();
			jButtonDeleteGenre.setBounds(new Rectangle(272, 8, 137, 25));
			jButtonDeleteGenre.setText("Удалить");
			jButtonDeleteGenre.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Object[] options = {"Да","Отмена"};
					
					int n = JOptionPane.showOptionDialog(null, "Вы действительно хотите удалить жанр?",
							"Вопрос?",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,
							null,options,options[1]);
					if(n == 0)
					{
						if(CData.DeleteSelectedGenre(CData.m_iSelectedIdGenre))
						{
							CData.CreateTableGenre();
							JOptionPane.showMessageDialog(null, "Жанр успешно удален!");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Ошибка удаления жанра!");
						}
					}
				}
			});
		}
		return jButtonDeleteGenre;
	}

	/**
	 * This method initializes jScrollPaneGenre	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public static JScrollPane getJScrollPaneGenre() {
		if (jScrollPaneGenre == null) {
			jScrollPaneGenre = new JScrollPane();
			jScrollPaneGenre.setBounds(new Rectangle(8, 40, 577, 276));
			//jScrollPaneGenre.setViewportView(getJTableGenre());
		}
		return jScrollPaneGenre;
	}

	/**
	 * This method initializes jTableGenre	
	 * 	
	 * @return javax.swing.JTable	
	 */
/*	private JTable getJTableGenre() {
		if (jTableGenre == null) {
			jTableGenre = new JTable();
		}
		return jTableGenre;
	}*/

	/**
	 * This method initializes jTextFieldStartDate	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldStartDate() {
		if (jTextFieldStartDate == null) {
			jTextFieldStartDate = new JTextField();
			jTextFieldStartDate.setBounds(new Rectangle(136, 40, 137, 25));
			jTextFieldStartDate.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
/*					m_WindowCalendar = new CWindowCalendar(null);
					m_WindowCalendar.setVisible(true);*/
				}
			});
			jTextFieldStartDate.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					CMain.m_iStartEndDateCurrentSelect = 0;
					m_WindowCalendar = new CWindowCalendar(null);
					m_WindowCalendar.setVisible(true);
				}
			});
		}
		return jTextFieldStartDate;
	}

	/**
	 * This method initializes jTextFieldEndDate	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldEndDate() {
		if (jTextFieldEndDate == null) {
			jTextFieldEndDate = new JTextField();
			jTextFieldEndDate.setBounds(new Rectangle(376, 40, 145, 25));
			jTextFieldEndDate.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					CMain.m_iStartEndDateCurrentSelect = 1;
					m_WindowCalendar = new CWindowCalendar(null);
					m_WindowCalendar.setVisible(true);
				}
			});
		}
		return jTextFieldEndDate;
	}

	/**
	 * This method initializes jComboBoxSelectRoom	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxSelectRoom() {
		String[] petStrings = { 
				"Главный зал(Детский)", 
				"Главный зал(Обычный)",
				"Главный зал(Премьера)"};
		if (jComboBoxSelectRoom == null) {
			jComboBoxSelectRoom = new JComboBox(petStrings);
			jComboBoxSelectRoom.setBounds(new Rectangle(584, 40, 233, 25));
		}
		return jComboBoxSelectRoom;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
		public static JScrollPane getJScrollPaneSeanse() {
		if (jScrollPaneSeanse == null) {
			jScrollPaneSeanse = new JScrollPane();
			jScrollPaneSeanse.setBounds(new Rectangle(8, 72, 1041, 513));
			//jScrollPaneSeanse.setViewportView(getJTableSeanse());
		}
		return jScrollPaneSeanse;
	}

	/**
	 * This method initializes jTableSeanse	
	 * 	
	 * @return javax.swing.JTable	
	 */
/*	private JTable getJTableSeanse() {
		if (jTableSeanse == null) {
			jTableSeanse = new JTable();
		}
		return jTableSeanse;
	}*/

	/**
	 * This method initializes jTableUsers	
	 * 	
	 * @return javax.swing.JTable	
	 */
	/*public static JTable getJTableUsers() {
	String[] columnNames = {
        "id",
        "логин",
        "Пароль",
        "ФИО",
        "Права пользователя"
};
	ArrayList ArrayTypeJobs = new ArrayList();
	 
	String data[][] = new String[ArrayTypeJobs.size()][5];
		if (jTableUsers == null) {
			jTableUsers = new JTable(data,columnNames);
		}
		return jTableUsers;
	}
*/
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				CMain thisClass = new CMain();
				m_Main = thisClass;
				m_Main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				m_Main.setVisible(true);
				// Окно авторизации!!!
				if(IsConnectCFG(m_stPathConnectCFG))// Если конфиг существует, то все норм!!!
				{
					StartDialog = new CDialogEnterUser(thisClass);
					StartDialog.setModalityType(ModalityType.APPLICATION_MODAL);
					StartDialog.setVisible(true);
				}
				else
				{
					m_DialogStart = new CDialogStart(thisClass);
					m_DialogStart.setModalityType(ModalityType.APPLICATION_MODAL);
					m_DialogStart.setVisible(true);
				}

			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public CMain() {
		super();
		initialize();
		///////////////////////////////////////////////////////////////
		// Загрузка объектов классов из сторонних и моих либов!!!
		///////////////////////////////////////////////////////////////
		m_cpPrints = new CPrints(); 
		System.out.println("Load - m_cpPrints...");
		///////////////////////////////////////////////////////////////
		// КОНЕЦ ЗАГРУЗКИ объектов классов из сторонних и моих либов!!!
		///////////////////////////////////////////////////////////////
		//CData.CreateTableUsers();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.LoadResources();
		this.setTitle(m_stTitle);
		this.setContentPane(getJTabbedPaneMain());
		this.setSize(1073, 717);
		this.setJMenuBar(getJJMenuBarMain());
		this.setLocationRelativeTo(null);
	}
	/**
	 * This method initializes jJMenuBarMain	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBarMain() {
		if (jJMenuBarMain == null) {
			jJMenuBarMain = new JMenuBar();
			setJMenuBar(jJMenuBarMain);
			JMenu file = new JMenu("Меню");
			
			JMenuItem itmSettings = new JMenuItem("Открыть базу...");
			itmSettings.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});

	         file.add(itmSettings);
			
			JMenuItem itmExit = new JMenuItem("Выход");
	         itmExit.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) 
				{
					//CMain.GetMain().dispose();
					System.exit(0);

				}
			});

	         file.add(itmExit);
	         jJMenuBarMain.add(file);
	         
	         JMenu fileUtils = new JMenu("Инструменты");
	         
	         JMenuItem itmJavaMyAdmin = new JMenuItem("Создать и восстановить базу данных");
	         itmJavaMyAdmin.addActionListener(new ActionListener() {
					
					//@Override
					public void actionPerformed(ActionEvent e) {
						Runtime a = Runtime.getRuntime(); 
						try
						{
							//Process proc = a.exec("d:/eclipse_projectOrdersAndOffers/Convertor.jar");
					//		Reader r = new InputStreamReader(new BufferedInputStream(Runtime.getRuntime().exec("java -jar progs/JavaMyAdmin.jar").getInputStream()));
						}
						catch (Exception ex) 
						{
							
						}
					}
				});
	         
	         fileUtils.add(itmJavaMyAdmin);
	         
	         JMenuItem itmCalc = new JMenuItem("Создать новую базу данных");
	         itmCalc.addActionListener(new ActionListener() {
					
				//	@Override
					public void actionPerformed(ActionEvent e) {
						Runtime a = Runtime.getRuntime(); 
						try
						{
							//Process proc = a.exec("d:/eclipse_projectOrdersAndOffers/Convertor.jar");
					//		Reader r = new InputStreamReader(new BufferedInputStream(Runtime.getRuntime().exec("java -jar progs/Convertor.jar").getInputStream()));
						}
						catch (Exception ex) 
						{
							
						}
					}
				});
	         
	         fileUtils.add(itmCalc);
	         
	         jJMenuBarMain.add(fileUtils);
	         
	         
	         JMenu fileAbout = new JMenu("Помощь");
	         
	         JMenuItem itmHelpDoc = new JMenuItem("Руководство");
	         itmHelpDoc.addActionListener(new ActionListener() {
					
			//		@Override
					public void actionPerformed(ActionEvent e) {
						/*CDialogAbout DialogAbout = new CDialogAbout(null);
						DialogAbout.setVisible(true);*/
					}
				});
	         fileAbout.add(itmHelpDoc);
	         
	         JMenuItem itmAbout = new JMenuItem("О программе");
	         itmAbout.addActionListener(new ActionListener() {
					
				//	@Override
					public void actionPerformed(ActionEvent e) {
				////		CDialogAbout DialogAbout = new CDialogAbout(null);
				//		DialogAbout.setVisible(true);
					}
				});
	         fileAbout.add(itmAbout);
	         
	         jJMenuBarMain.add(fileAbout);
		}
		return jJMenuBarMain;
	}
	private static boolean IsConnectCFG(String stPathConnectCFG)
	{
		boolean bRet = true;
		
		DataOutputStream OutStream;
		
		try
		{
			m_Key = new SecretKeySpec(CMain.m_ByteSalt, "DES");//KeyGenerator.getInstance("DES").generateKey();
			CMain.m_Encrypter = new DesEncrypter(m_Key);
		}
		catch (Exception ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		File file = new File(CMain.m_stPathConnectCFG);
		// Файл настроек не существует!!!
		if(!file.exists())
		{
			bRet = false;
			System.out.println("Файл настроек не существует!!!");
			return bRet;
		}
		else
		{
			ArrayList alSettings = null;
			alSettings = new ArrayList();
			
				//List<String> myArr = new ArrayList<String>();
			BufferedReader reader = null;
			try
			{
				reader = new BufferedReader(new FileReader(CMain.m_stPathConnectCFG));
			}
			catch(Exception e)
			{
				
			}
		    String line;
		    try
		    {
				 while ((line = reader.readLine()) != null)
				 {
					 alSettings.add(m_Encrypter.decrypt(line));
					 //System.out.println("Перебор!!!");
				 }
		    }
		    catch(Exception ex)
		    {
		    	
		    }
		    String stTemp = null;
		    String stResult = null;
		    // Достаем все из файла!
		    for(int i = 0; i < alSettings.size(); i++)
		    {
		    	stTemp = (String)alSettings.get(i);
		    	if(i == 0)
		    	{
		    		stResult = stTemp.replaceFirst("saveparams=", "");
		    		if(stResult.contains("0"))
		    		{
		    			break;
		    		}
		    		else
		    		{
		    			//jCheckBoxSaveParams.setSelected(true);
		    			i++;
		    		}
		    		
		    	}
		    	stTemp = (String)alSettings.get(i);
		    	stResult = null;
		    	if(i == 1)
		    	{
		    		stResult = stTemp.replaceFirst("savelogin=", "");
	    		
		    		if(stResult.contains("0"))
		    		{
		    			//jCheckBoxSavePass.setSelected(false);
		    			i++;
		    		}
		    		else
		    		{
		    			//this.jCheckBoxSavePass.setSelected(true);
		    			i++;
		    		}
		    	}
		    	stTemp = (String)alSettings.get(i);
		    	stResult = null;
		    	if(i == 2)
		    	{
		    		stResult = stTemp.replaceFirst("login=", "");
		    		System.out.println(stResult);
		    		//jTextFieldLogin.setText(stResult);
		    		CData.stUser = stResult;
		    		i++;
		    	}
		    	
		    	stTemp = (String)alSettings.get(i);
		    	if(i == 3/* && jCheckBoxSavePass.isSelected()*/)
		    	{
		    		stResult = stTemp.replaceFirst("pass=", "");
		    		//jPasswordFieldPass.setText(stResult);
		    		CData.stPass = stResult;
		    		i++;
		    	}
		    	
		    	stTemp = (String)alSettings.get(i);
		    	if(i == 4)
		    	{
		    		stResult = stTemp.replaceFirst("server=", "");
		    		CData.m_stServer = stResult;
		    		//jTextFieldServer.setText(stResult);
		    		i++;
		    	}
		    	
		    	stTemp = (String)alSettings.get(i);
		    	if(i == 5)
		    	{
		    		stResult = stTemp.replaceFirst("port=", "");
		    		CData.m_stPort =stResult;
		    		//jTextFieldPort.setText(stResult);
		    		i++;
		    	}
		    	
		    	stTemp = (String)alSettings.get(i);
		    	if(i == 6)
		    	{
		    		stResult = stTemp.replaceFirst("name_utm5=", "");
		    		CData.m_stDBName = stResult;
		    		//jTextFieldNameUTM5.setText(stResult);
		    		i++;
		    	}
		    	
		    	// Заполнение полей для UTM5_OFFER
		    	//stTemp = (String)alSettings.get(i);!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		    	/*if(i == 7)
		    	{
		    		stResult = stTemp.replaceFirst("login_offer=", "");
		    		CDataOffer.stUser = stResult;
		    		//jTextFieldLoginOffer.setText(stResult);
		    		i++;
		    	}
		    	stTemp = (String)alSettings.get(i);
		    	if(i == 8)
		    	{
		    		stResult = stTemp.replaceFirst("pass_offer=", "");
		    		//jPasswordFieldPassOffer.setText(stResult);
		    		CDataOffer.stPass = stResult;
		    		i++;
		    	}
		    	stTemp = (String)alSettings.get(i);
		    	if(i == 9)
		    	{
		    		stResult = stTemp.replaceFirst("server_offer=", "");
		    		//jTextFieldServerOffer.setText(stResult);
		    		CDataOffer.m_stServer = stResult;
		    		i++;
		    	}
		    	stTemp = (String)alSettings.get(i);
		    	if(i == 10)
		    	{
		    		stResult = stTemp.replaceFirst("port_offer=", "");
		    		//jTextFieldPortOffer.setText(stResult);
		    		CDataOffer.m_stPort = stResult;
		    		i++;
		    	}
		    	stTemp = (String)alSettings.get(i);
		    	if(i == 11)
		    	{
		    		stResult = stTemp.replaceFirst("name_utm_offer=", "");
		    		//jTextFieldNameUTMOFFER.setText(stResult);
		    		CDataOffer.m_stDBName = stResult;
		    		i++;
		    	}*/
		    }
		    // Строки соединения!!!
		    // "jdbc:mysql://localhost:3306/BGBilling";
		    CData.stConnectionStringPath = "jdbc:mysql://" + CData.m_stServer + ":" + CData.m_stPort + "/" + CData.m_stDBName;
		    System.out.println("CData.stConnectionStringPath = " + CData.stConnectionStringPath);
		}
		
		return bRet;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
