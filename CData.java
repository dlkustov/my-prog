package theater.com;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class CData
{
	public static String stConnectionStringPath = "jdbc:mysql://localhost:3306/BGBilling";
	public static String stUser = "root";
	public static String stPass = "root";
	public static String m_stServer = "localhost";
	public static String m_stPort = "3306";
	public static String m_stDBName = "DBName";
	public static String stDriver = "com.mysql.jdbc.Driver";
	public static Statement stmt = null;
	public static ResultSet rs = null;
	
	// Таблица для заполнения пользователей
	public static  DefaultTableModel m_ModelUsers = null;
	public static JTable m_jTableUsers = null;
	public static String m_iSelectedIdUser = null;
	// Таблица для заполнения параметров!!!
	public static  DefaultTableModel m_ModelParam = null;
	public static JTable m_jTableParam = null;
	
	// Таблица для заполнения жанров!!!
	public static  DefaultTableModel m_ModelGenre = null;
	public static JTable m_jTableGenre = null;
	public static String m_iSelectedIdGenre = null;
	
	// Таблица заполнения репертуара!!!
	public static DefaultTableModel m_ModelSeanse = null;
	public static JTable m_jTableSeanse = null;
	
	public CData() 
	{
	}
	static public boolean RegisterDriver()
	{
		boolean bRet = true;
		try 
		{
		      Class.forName(stDriver).newInstance();
		}
		catch (Exception e)
	    {
			  JOptionPane.showMessageDialog(null, e.toString());
		      e.printStackTrace(System.err);
		      bRet = false;
	    }
		return bRet;
	}
	 static public Connection CreateConnection(String stConnectionStringPath, String stUser, String stPass)
     {
		 Connection myConn = null;
		 Properties properties=new Properties();
		 properties.setProperty("user",stUser);
		 properties.setProperty("password",stPass);
		 properties.setProperty("useUnicode","true");
		 properties.setProperty("characterEncoding","utf8");
		 
         try
         {
             myConn = DriverManager.getConnection(stConnectionStringPath,properties);
         }
         catch (Exception e)
         {
        	 JOptionPane.showMessageDialog(null,e.getMessage() );
             return null;
         }
         return myConn;
     }
	 static public boolean Open(Connection myConnection)
     {
         if (myConnection != null)
         {
             try
             {
                 myConnection = DriverManager.getConnection(stConnectionStringPath,stUser,stPass);
                 return true;
             }
             catch (Exception e)
             {
                 return false;
             }
         }
         return true;
     }
	 //////////////////////////////////////////////////////////////////////
	 // Получение инфы по одному юзеру с логином и паролем!!!
	 /////////////////////////////////////////////////////////////////////
	 static public CUserInfo GetUserInfo(String stLogin, String stPass)
	 {
		 CUserInfo UserInfo = null;
		Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		if(conn != null)
		{
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
			    	
			      stmt = conn.createStatement();
	    		  rs = stmt.executeQuery("SELECT id,login,pass,user_name,rules FROM users WHERE login = '" + 
   				  stLogin + "' AND pass = '" + stPass + "'");

			      String stID = null;
			      while (rs.next()) 
			      {
			    	  UserInfo = new CUserInfo();
			    	  System.out.println("id = " + Integer.toString(rs.getInt("id")));
			    	  UserInfo.m_iID = rs.getInt("id");
			    	  System.out.println("login = " + rs.getString("login"));
			    	  UserInfo.m_stLogin = rs.getString("login");
			    	  System.out.println("pass = " + rs.getString("pass"));
			    	  UserInfo.m_stPass =  rs.getString("pass");
			    	  System.out.println("user_name = " + rs.getString("user_name"));
			    	  UserInfo.m_stName = rs.getString("user_name");
			    	  System.out.println("rules = " + Integer.toString(rs.getInt("rules")));
			    	  UserInfo.m_iRules = rs.getInt("rules");
			      }
			    } 
			    catch (Exception ex) 
			    {
			      ex.printStackTrace(System.err);
			    }
			    finally 
			    {
			      try 
			      {
			        if (rs != null) 
			        {
			        	rs.close(); 
			        }
			        if (stmt != null) 
			        {
			        	stmt.close(); 
			        }
			        if (conn != null) 
			        { 
			        	conn.close(); 
			        }
			      }
			      catch (SQLException ex) 
			      {
			        ex.printStackTrace(System.err);
			      }
			    }
		}
		return UserInfo;

	 }
	 //////////////////////////////////////////////////////////////////////
	 // Получение инфы по всем юзерам!!!
	 /////////////////////////////////////////////////////////////////////
	 static public ArrayList GetUserInfoAll()
	 {
		 ArrayList alAllUsers = null;
		 CUserInfo UserInfo = null;
		Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		if(conn != null)
		{
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
			    	
			      stmt = conn.createStatement();
			      rs = stmt.executeQuery("SELECT id,login,pass,user_name,rules FROM users;");
	    		  alAllUsers = new ArrayList();
			      String stID = null;
			      while (rs.next()) 
			      {
			    	  UserInfo = new CUserInfo();
			    	  UserInfo.m_iID = rs.getInt("id");
			    	  UserInfo.m_stLogin = rs.getString("login");
			    	  UserInfo.m_stPass =  rs.getString("pass");
			    	  UserInfo.m_stName = rs.getString("user_name");
			    	  UserInfo.m_iRules = rs.getInt("rules");
			    	  alAllUsers.add(UserInfo);
			      }
			    } 
			    catch (Exception ex) 
			    {
			      ex.printStackTrace(System.err);
			    }
			    finally 
			    {
			      try 
			      {
			        if (rs != null) 
			        {
			        	rs.close(); 
			        }
			        if (stmt != null) 
			        {
			        	stmt.close(); 
			        }
			        if (conn != null) 
			        { 
			        	conn.close(); 
			        }
			      }
			      catch (SQLException ex) 
			      {
			        ex.printStackTrace(System.err);
			      }
			    }
		}
		return alAllUsers;

	 }
	 ////////////////////////////////////////////////////////////////////////////////////////////
	 // Создания пустой таблицы для заполнения(обновления) пользователями!!!
	 ////////////////////////////////////////////////////////////////////////////////////////////
	 static public void CreateTableUsers()
	 {
		 String[] columnNames = 
		 {
				 "id",
			     "логин",
			     "Пароль",
			     "ФИО",
			     "Права пользователя"
		 };
		     ArrayList alUserInfoAll = GetUserInfoAll();
		 
			String data[][] = new String[alUserInfoAll.size()][5];
			
			  for(int j = 0; j < alUserInfoAll.size(); j++)
			  {
				  data[j][0] = Integer.toString(((CUserInfo)alUserInfoAll.get(j)).m_iID);
				  data[j][1] = (String)(((CUserInfo)alUserInfoAll.get(j)).m_stLogin);
				  data[j][2] = ((CUserInfo)alUserInfoAll.get(j)).m_stPass;
				  data[j][3] = ((CUserInfo)alUserInfoAll.get(j)).m_stName;
				  if(((CUserInfo)alUserInfoAll.get(j)).m_iRules == 0)
				  {
					  data[j][4] = "Администратор";
				  }
				  if(((CUserInfo)alUserInfoAll.get(j)).m_iRules == 1)
				  {
					  data[j][4] = "Кассир";
				  }
			  }
		 m_ModelUsers = new DefaultTableModel(data,columnNames);
		 m_jTableUsers = new JTable(m_ModelUsers)
		 {
			    public boolean isCellEditable(int rowIndex, int vColIndex) {
			        return false;
			    }
			};
			TableColumnModel tcm = m_jTableUsers.getColumnModel();
			tcm.getColumn(0).setPreferredWidth(4);     //Name
/*			tcm.getColumn(1).setPreferredWidth(100);    //Title
			tcm.getColumn(2).setPreferredWidth(100);    //Surname
			tcm.getColumn(3).setPreferredWidth(100); */
			m_ModelUsers.fireTableDataChanged();
			m_jTableUsers.setSelectionMode(m_jTableUsers.getSelectionModel().SINGLE_SELECTION);
		CMain.m_Main.getJScrollPaneUsers().setViewportView(m_jTableUsers);
		
		m_jTableUsers.addMouseListener(new java.awt.event.MouseAdapter()
		 {
		public void mouseClicked(java.awt.event.MouseEvent e)
			{
				if(e.getClickCount() == 2)
				{
					System.out.println("Mouse event, clickCount = " + e.getClickCount());
					
					int[] selectedRows = m_jTableUsers.getSelectedRows();
	                for(int i = 0; i < selectedRows.length; i++) 
	                {
	                     int selIndex = selectedRows[i];
	                     //m_iIDType = Integer.parseInt(ConvertUtils.convert(m_jTableSelectedJobs.getValueAt(selIndex, 0)));
	                     //m_stNameType = m_jTableSelectedJobs.getValueAt(selIndex, 1).toString();
	                     //System.out.println(m_iIDType);
	                     //System.out.println(m_stNameType);
	                     //RemoveSelectedJobs(selIndex);
	                }
				}
				if(e.getClickCount() == 1)
				{
					///System.out.println("Mouse event, clickCount11111111 = " + e.getClickCount());
					
					int[] selectedRows = m_jTableUsers.getSelectedRows();
					//int[] selectedRows = m_jTableUsers.getSelect
	                for(int i = 0; i < selectedRows.length; i++) 
	                {
	                     int selIndex = selectedRows[i];
	                     //m_iIDType = Integer.parseInt(ConvertUtils.convert(m_jTableSelectedJobs.getValueAt(selIndex, 0)));
	                     m_iSelectedIdUser = m_jTableUsers.getValueAt(selIndex, 0).toString();
	                     System.out.println(selIndex);
	                     //System.out.println(m_stNameType);
	                     //RemoveSelectedJobs(selIndex);
	                }
				}
			}
		});
	 }
	 /////////////////////////////////////////////////////////////////////////////
	 // Создание нового юзера!!!
	 ////////////////////////////////////////////////////////////////////////////
	 static public boolean CreateUser( String stLogin, String stPass, String stUserName, String stRules )
	 {
		 boolean bRet = true;
		 Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		 if(conn != null)
		 {
			    try 
			    {
			      stmt = conn.createStatement();
			      stmt.executeUpdate("INSERT INTO users(login,pass,user_name,rules)" +
			      		" VALUES('" + stLogin + "','" + stPass + "','" + stUserName + "','" + stRules + "');");
			    } 
			    catch (Exception ex) 
			    {
			      ex.printStackTrace(System.err);
			      bRet = false;
			    }
			    finally 
			    {
			      try 
			      {
			        if (rs != null) 
			        {
			        	rs.close(); 
			        }
			        if (stmt != null) 
			        {
			        	stmt.close(); 
			        }
			        if (conn != null) 
			        { 
			        	conn.close(); 
			        }
			      }
			      catch (SQLException ex) 
			      {
			        ex.printStackTrace(System.err);
			        bRet = false;
			      }
			    }
		 }
		 else
		 {
			 bRet = false;
		 }
			 
			 return bRet;
	 }
	 ///////////////////////////////////////////////////////////////////////////
	 // Удаление выбранного пользователя!!!
	 ///////////////////////////////////////////////////////////////////////////
	 static public boolean DeleteSelectedUser(String stIdSelectedUser)
	 {
		 boolean bRet = true;
		 if(stIdSelectedUser == null)
		 {
			 JOptionPane.showMessageDialog(null, "Сперва выберите пользователя!");
			 return false;
		 }
		 // Удаление наряда
	 	Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		
		if(conn != null)
		{
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
			      String stMysqlOrder = "DELETE FROM users WHERE id='" + stIdSelectedUser + "';";
			      stmt = conn.createStatement();
			      stmt.executeUpdate(stMysqlOrder);
			    } 
			    catch (Exception ex) 
			    {
			      ex.printStackTrace(System.err);
			      bRet = false;
			    }
			    finally 
			    {
			      try 
			      {
			        if (rs != null) 
			        {
			        	rs.close(); 
			        }
			        if (stmt != null) 
			        {
			        	stmt.close(); 
			        }
			        if (conn != null) 
			        { 
			        	conn.close(); 
			        }
			      }
			      catch (SQLException ex) 
			      {
			        ex.printStackTrace(System.err);
			      }
			    }
		}
		return bRet;
	 }
	 //////////////////////////////////////////////////////////////////////
	 // Получение параметров организации!!!
	 /////////////////////////////////////////////////////////////////////
	 static public ArrayList GetParam()
	 {
		 ArrayList alAllUsers = null;
		 CParamInfo ParamInfo = null;
		Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		if(conn != null)
		{
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
			    	
			      stmt = conn.createStatement();
			      rs = stmt.executeQuery("SELECT id,theater_name,director_name,address,phone,tiket,money_type FROM theater_param;");
	    		  alAllUsers = new ArrayList();
			      String stID = null;
			      while (rs.next()) 
			      {
			    	  ParamInfo = new CParamInfo();
			    	  ParamInfo.m_iId = rs.getInt("id");
			    	  ParamInfo.m_sttheater_name = rs.getString("theater_name");
			    	  ParamInfo.m_stdirector_name =  rs.getString("director_name");
			    	  ParamInfo.m_staddress = rs.getString("address");
			    	  ParamInfo.m_stphone = rs.getString("phone");
			    	  ParamInfo.m_itiket = rs.getInt("tiket");
			    	  ParamInfo.m_stmoney_type = rs.getString("money_type");
			    	  alAllUsers.add(ParamInfo);
			      }
			    } 
			    catch (Exception ex) 
			    {
			      ex.printStackTrace(System.err);
			    }
			    finally 
			    {
			      try 
			      {
			        if (rs != null) 
			        {
			        	rs.close(); 
			        }
			        if (stmt != null) 
			        {
			        	stmt.close(); 
			        }
			        if (conn != null) 
			        { 
			        	conn.close(); 
			        }
			      }
			      catch (SQLException ex) 
			      {
			        ex.printStackTrace(System.err);
			      }
			    }
		}
		return alAllUsers;
	 }
	 ////////////////////////////////////////////////////////////////////////////////////////////
	 // Создания пустой таблицы для заполнения(обновления) параметров!!!
	 ////////////////////////////////////////////////////////////////////////////////////////////
	 static public void CreateTableParam()
	 {
		 String[] columnNames = 
		 {
				 "Параметр",
			     "Значение"
		 };
		 ArrayList alUserInfoAll = GetParam();
		 
			String data[][] = new String[6][2];
			
			  for(int j = 0; j < 6; j++)
			  {
				  if(j == 0)
				  {
					  data[j][0] = "Название учреждения:";
					  data[j][1] = (String)(((CParamInfo)alUserInfoAll.get(j)).m_sttheater_name);
					  data[1][0] = "Ф.И.О. Директора:";
					  data[1][1] = (String)(((CParamInfo)alUserInfoAll.get(j)).m_stdirector_name);
					  data[2][0] = "Адрес:";
					  data[2][1] = (String)(((CParamInfo)alUserInfoAll.get(j)).m_staddress);
					  data[3][0] = "Телефон:";
					  data[3][1] = (String)(((CParamInfo)alUserInfoAll.get(j)).m_stphone);
					  data[4][0] = "Макет билета:";
					  data[4][1] = Integer.toString((((CParamInfo)alUserInfoAll.get(j)).m_itiket));
					  data[5][0] = "Валюта:";
					  data[5][1] = (String)(((CParamInfo)alUserInfoAll.get(j)).m_stmoney_type);
				  }
			  }
		 m_ModelParam = new DefaultTableModel(data,columnNames);
		 m_jTableParam = new JTable(m_ModelParam)
		 {
			    public boolean isCellEditable(int rowIndex, int vColIndex) {
			        return true;
			    }
			};
			m_ModelParam.fireTableDataChanged();
			m_jTableParam.setSelectionMode(m_jTableParam.getSelectionModel().SINGLE_SELECTION);
		CMain.m_Main.getJScrollPaneParam().setViewportView(m_jTableParam);
		
		m_jTableParam.getModel().addTableModelListener(
			    new TableModelListener() {
					
					public void tableChanged(TableModelEvent e) {
						UpdateParamCell(m_jTableParam.getSelectedRow(), (String)m_jTableParam.getValueAt(m_jTableParam.getSelectedRow(),
								m_jTableParam.getSelectedColumn()));
						System.out.println(m_jTableParam.getSelectedColumn()+ " -- " + m_jTableParam.getSelectedRow());
						System.out.println(m_jTableParam.getValueAt(m_jTableParam.getSelectedRow(),m_jTableParam.getSelectedColumn()));
					}
				});
			    
		
		m_jTableParam.addMouseListener(new java.awt.event.MouseAdapter()
		 {
		public void mouseClicked(java.awt.event.MouseEvent e)
			{
				if(e.getClickCount() == 2)
				{
					System.out.println("Mouse event, clickCount = " + e.getClickCount());
					
					int[] selectedRows = m_jTableParam.getSelectedRows();
	                for(int i = 0; i < selectedRows.length; i++) 
	                {
	                     int selIndex = selectedRows[i];
	                     //m_iIDType = Integer.parseInt(ConvertUtils.convert(m_jTableSelectedJobs.getValueAt(selIndex, 0)));
	                     //m_stNameType = m_jTableSelectedJobs.getValueAt(selIndex, 1).toString();
	                     //System.out.println(m_iIDType);
	                     //System.out.println(m_stNameType);
	                     //RemoveSelectedJobs(selIndex);
	                }
				}
				if(e.getClickCount() == 1)
				{
					///System.out.println("Mouse event, clickCount11111111 = " + e.getClickCount());
					
					int[] selectedRows = m_jTableParam.getSelectedRows();
					//int[] selectedRows = m_jTableUsers.getSelect
	                for(int i = 0; i < selectedRows.length; i++) 
	                {
/*	                     int selIndex = selectedRows[i];
	                     //m_iIDType = Integer.parseInt(ConvertUtils.convert(m_jTableSelectedJobs.getValueAt(selIndex, 0)));
	                     m_iSelectedIdUser = m_jTableUsers.getValueAt(selIndex, 0).toString();
	                     System.out.println(selIndex);
	                     //System.out.println(m_stNameType);
	                     //RemoveSelectedJobs(selIndex);
*/	                }
				}
			}
		});
		//m_jTableParam.add
	 }
	 //////////////////////////////////////////////////////////////////////////
	 // Обновление ячеек в БД параметров!!!
	 //////////////////////////////////////////////////////////////////////////
	 static public boolean UpdateParamCell(int iCurCell, String stResultText)
	 {
		 boolean bRet = true;
			// Соединение!!!
			Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
			if(conn != null)
			{
				    Statement stmt = null;
				    ResultSet rs = null;
				    try 
				    {
				    	String stMysqlOrder = null;
				    	// Смотрим какую ячейку выбрали!!!
				    	System.out.println(stResultText);
						System.out.println("Изменение в ячейке");
						//System.out.println((String)m_jTableParam.getValueAt(m_jTableParam.getSelectedColumn(),m_jTableParam.getSelectedRow()));
						System.out.println("Изменение в ячейке = " + iCurCell);
						if(iCurCell == 0)
						{
							stMysqlOrder = "UPDATE theater_param SET theater_name ='" + stResultText
							+ "' WHERE id ='1';";
							stmt = conn.createStatement();
						    stmt.executeUpdate(stMysqlOrder);
						}
						if(iCurCell == 1)
						{
							stMysqlOrder = "UPDATE theater_param SET director_name ='" + stResultText
							+ "' WHERE id ='1';";
							stmt = conn.createStatement();
						    stmt.executeUpdate(stMysqlOrder);
						}
						if(iCurCell == 2)
						{
							stMysqlOrder = "UPDATE theater_param SET address ='" + stResultText
							+ "' WHERE id ='1';";
							stmt = conn.createStatement();
						    stmt.executeUpdate(stMysqlOrder);
						}
						if(iCurCell == 3)
						{
							stMysqlOrder = "UPDATE theater_param SET phone ='" + stResultText
							+ "' WHERE id ='1';";
							stmt = conn.createStatement();
						    stmt.executeUpdate(stMysqlOrder);
						}
						if(iCurCell == 4)
						{
							stMysqlOrder = "UPDATE theater_param SET tiket ='" + stResultText
							+ "' WHERE id ='1';";
							stmt = conn.createStatement();
						    stmt.executeUpdate(stMysqlOrder);
						}
						if(iCurCell == 5)
						{
							stMysqlOrder = "UPDATE theater_param SET money_type ='" + stResultText
							+ "' WHERE id ='1';";
							stmt = conn.createStatement();
						    stmt.executeUpdate(stMysqlOrder);
						}
				    } 
				    catch (Exception ex) 
				    {
				      ex.printStackTrace(System.err);
				      bRet = false;
				    }
				    finally 
				    {
				      try 
				      {
				        if (rs != null) 
				        {
				        	rs.close(); 
				        }
				        if (stmt != null) 
				        {
				        	stmt.close(); 
				        }
				        if (conn != null) 
				        { 
				        	conn.close(); 
				        }
				      }
				      catch (SQLException ex) 
				      {
				        ex.printStackTrace(System.err);
				      }
				    }
			}
			return bRet;
	 }
	 ////////////////////////////////////////////////////////////////////////////////////////////
	 // Создания пустой таблицы для заполнения(обновления) жанра!!!
	 ////////////////////////////////////////////////////////////////////////////////////////////
	 static public void CreateTableGenre()
	 {
		 String[] columnNames = 
		 {
				 "id",
			     "Название жанра"
		 };
		 ArrayList alGenreInfoAll = GetGenreInfoAll();
		 
			String data[][] = new String[alGenreInfoAll.size()][2];
			
			  for(int j = 0; j < alGenreInfoAll.size(); j++)
			  {
				  data[j][0] = Integer.toString(((CGenreInfo)alGenreInfoAll.get(j)).m_iId);
				  data[j][1] = (String)(((CGenreInfo)alGenreInfoAll.get(j)).m_stName);
			  }
			  m_ModelGenre = new DefaultTableModel(data,columnNames);
		 m_jTableGenre = new JTable(m_ModelGenre)
		 {
			    public boolean isCellEditable(int rowIndex, int vColIndex) {
			        return false;
			    }
			};
			m_ModelGenre.fireTableDataChanged();
			m_jTableGenre.setSelectionMode(m_jTableGenre.getSelectionModel().SINGLE_SELECTION);
		CMain.m_Main.getJScrollPaneGenre().setViewportView(m_jTableGenre);
		
		m_jTableGenre.addMouseListener(new java.awt.event.MouseAdapter()
		 {
		public void mouseClicked(java.awt.event.MouseEvent e)
			{
				if(e.getClickCount() == 2)
				{
					System.out.println("Mouse event, clickCount = " + e.getClickCount());
					
					int[] selectedRows = m_jTableGenre.getSelectedRows();
	                for(int i = 0; i < selectedRows.length; i++) 
	                {
	                     int selIndex = selectedRows[i];
	                     //m_iIDType = Integer.parseInt(ConvertUtils.convert(m_jTableSelectedJobs.getValueAt(selIndex, 0)));
	                     m_iSelectedIdGenre = m_jTableGenre.getValueAt(selIndex, 0).toString();
	                     CDialogUpdateGenre DialogUpdateGenre = new CDialogUpdateGenre(null);
	                     DialogUpdateGenre.setVisible(true);
	                     //System.out.println(m_iIDType);
	                     //System.out.println(m_stNameType);
	                     //RemoveSelectedJobs(selIndex);
	                }
				}
				if(e.getClickCount() == 1)
				{
					///System.out.println("Mouse event, clickCount11111111 = " + e.getClickCount());
					
					int[] selectedRows = m_jTableGenre.getSelectedRows();
					//int[] selectedRows = m_jTableUsers.getSelect
	                for(int i = 0; i < selectedRows.length; i++) 
	                {
	                     int selIndex = selectedRows[i];
	                     //m_iIDType = Integer.parseInt(ConvertUtils.convert(m_jTableSelectedJobs.getValueAt(selIndex, 0)));
	                     m_iSelectedIdGenre = m_jTableGenre.getValueAt(selIndex, 0).toString();
	                     //System.out.println(selIndex);
	                     //System.out.println(m_stNameType);
	                     //RemoveSelectedJobs(selIndex);
	                }
				}
			}
		});
	 }
	 //////////////////////////////////////////////////////////////////////
	 // Получение инфы по всем жанрам!!!
	 /////////////////////////////////////////////////////////////////////
	 static public ArrayList GetGenreInfoAll()
	 {
		 ArrayList alGenreInfo = null;
		 CGenreInfo GenreInfo = null;
		Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		if(conn != null)
		{
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
			    	
			      stmt = conn.createStatement();
			      rs = stmt.executeQuery("SELECT id,name_genre FROM genre;");
			      alGenreInfo = new ArrayList();
			      String stID = null;
			      while (rs.next()) 
			      {
			    	  GenreInfo = new CGenreInfo();
			    	  GenreInfo.m_iId = rs.getInt("id");
			    	  GenreInfo.m_stName = rs.getString("name_genre");
			    	  alGenreInfo.add(GenreInfo);
			      }
			    } 
			    catch (Exception ex) 
			    {
			      ex.printStackTrace(System.err);
			    }
			    finally 
			    {
			      try 
			      {
			        if (rs != null) 
			        {
			        	rs.close(); 
			        }
			        if (stmt != null) 
			        {
			        	stmt.close(); 
			        }
			        if (conn != null) 
			        { 
			        	conn.close(); 
			        }
			      }
			      catch (SQLException ex) 
			      {
			        ex.printStackTrace(System.err);
			      }
			    }
		}
		return alGenreInfo;

	 }
	 /////////////////////////////////////////////////////////////////////////////
	 // Создание нового жанра!!!
	 ////////////////////////////////////////////////////////////////////////////
	 static public boolean CreateGenre( String stName)
	 {
		 boolean bRet = true;
		 Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		 if(conn != null)
		 {
			    try 
			    {
			      stmt = conn.createStatement();
			      stmt.executeUpdate("INSERT INTO genre(name_genre)" +
			      		" VALUES('" + stName + "');");
			    } 
			    catch (Exception ex) 
			    {
			      ex.printStackTrace(System.err);
			      bRet = false;
			    }
			    finally 
			    {
			      try 
			      {
			        if (rs != null) 
			        {
			        	rs.close(); 
			        }
			        if (stmt != null) 
			        {
			        	stmt.close(); 
			        }
			        if (conn != null) 
			        { 
			        	conn.close(); 
			        }
			      }
			      catch (SQLException ex) 
			      {
			        ex.printStackTrace(System.err);
			        bRet = false;
			      }
			    }
		 }
		 else
		 {
			 bRet = false;
		 }
			 return bRet;
	 }
	 ///////////////////////////////////////////////////////////////////////////
	 // Удаление выбранного жанра!!!
	 ///////////////////////////////////////////////////////////////////////////
	 static public boolean DeleteSelectedGenre(String stIdSelectedGenre)
	 {
		 boolean bRet = true;
		 if(stIdSelectedGenre == null)
		 {
			 JOptionPane.showMessageDialog(null, "Сперва выберите жанр!");
			 return false;
		 }
		 // Удаление наряда
	 	Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		
		if(conn != null)
		{
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
			      String stMysqlOrder = "DELETE FROM genre WHERE id='" + stIdSelectedGenre + "';";
			      stmt = conn.createStatement();
			      stmt.executeUpdate(stMysqlOrder);
			    } 
			    catch (Exception ex) 
			    {
			      ex.printStackTrace(System.err);
			      bRet = false;
			    }
			    finally 
			    {
			      try 
			      {
			        if (rs != null) 
			        {
			        	rs.close(); 
			        }
			        if (stmt != null) 
			        {
			        	stmt.close(); 
			        }
			        if (conn != null) 
			        { 
			        	conn.close(); 
			        }
			      }
			      catch (SQLException ex) 
			      {
			        ex.printStackTrace(System.err);
			      }
			    }
		}
		return bRet;
	 }
	 /////////////////////////////////////////////////////////////////////////////
	 // Обновление названия жанра!!!
	 /////////////////////////////////////////////////////////////////////////////
	 static public boolean UpdateNameGenre(String iSelectedIndex, String stNewName)
	 {
		 boolean bRet = true;
			// Соединение для UTM5
			Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);

			if(conn != null)
			{
				    Statement stmt = null;
				    ResultSet rs = null;
				    try 
				    {
				      String stMysqlOrder = "UPDATE genre SET name_genre = '" + stNewName + "' WHERE id ='" + iSelectedIndex + "';";
				      stmt = conn.createStatement();
				      stmt.executeUpdate(stMysqlOrder);
/*				      /////////////////////////////////////////////////////////////////////////
				      CreateLogString(CMain.m_iIDSysUserEdit,CMain.DELETE_OFFER, 1,iNumberClients,stMysqlOrder);
				      /////////////////////////////////////////////////////////////////////////
*/				    } 
				    catch (Exception ex) 
				    {
				      ex.printStackTrace(System.err);
				      bRet = false;
				    }
				    finally 
				    {
				      try 
				      {
				        if (rs != null) 
				        {
				        	rs.close(); 
				        }
				        if (stmt != null) 
				        {
				        	stmt.close(); 
				        }
				        if (conn != null) 
				        { 
				        	conn.close(); 
				        }
				      }
				      catch (SQLException ex) 
				      {
				        ex.printStackTrace(System.err);
				      }
				    }
			}
			return bRet;
	 }
	 ////////////////////////////////////////////////////////////////////////////////////////////
	 // Создания пустой таблицы для заполнения(обновления) Сеансов!!!
	 ////////////////////////////////////////////////////////////////////////////////////////////
	 static public void CreateTableSeanse()
	 {
		 String[] columnNames = 
		 {
				 	"id",
			        "Название мероприятия",
			        "Дата",
			        "Начало",
			        "Роли",
			        "Жанр",
			        "Цена1",
			        "Цена2",
			        "Цена3"
		 };
		 //ArrayList alGenreInfoAll = GetGenreInfoAll();
		 
			//String data[][] = new String[alGenreInfoAll.size()][2];
			
			  /*for(int j = 0; j < alGenreInfoAll.size(); j++)
			  {
				  data[j][0] = Integer.toString(((CGenreInfo)alGenreInfoAll.get(j)).m_iId);
				  data[j][1] = (String)(((CGenreInfo)alGenreInfoAll.get(j)).m_stName);
			  }*/
			  m_ModelSeanse = new DefaultTableModel(null,columnNames);
		 m_jTableSeanse = new JTable(m_ModelSeanse)
		 {
			    public boolean isCellEditable(int rowIndex, int vColIndex) {
			        return false;
			    }
			};
			m_ModelSeanse.fireTableDataChanged();

			m_jTableSeanse.setSelectionMode(m_jTableSeanse.getSelectionModel().SINGLE_SELECTION);
			CMain.m_Main.getJScrollPaneSeanse().setViewportView(m_jTableSeanse);
		
		m_jTableSeanse.addMouseListener(new java.awt.event.MouseAdapter()
		 {
		public void mouseClicked(java.awt.event.MouseEvent e)
			{
/*				if(e.getClickCount() == 2)
				{
					System.out.println("Mouse event, clickCount = " + e.getClickCount());
					
					int[] selectedRows = m_jTableSeanse.getSelectedRows();
	                for(int i = 0; i < selectedRows.length; i++) 
	                {
	                     int selIndex = selectedRows[i];
	                     //m_iIDType = Integer.parseInt(ConvertUtils.convert(m_jTableSelectedJobs.getValueAt(selIndex, 0)));
	                     m_iSelectedIdGenre = m_jTableSeanse.getValueAt(selIndex, 0).toString();
	                     CDialogUpdateGenre DialogUpdateGenre = new CDialogUpdateGenre(null);
	                     DialogUpdateGenre.setVisible(true);
	                     //System.out.println(m_iIDType);
	                     //System.out.println(m_stNameType);
	                     //RemoveSelectedJobs(selIndex);
	                }
				}
				if(e.getClickCount() == 1)
				{
					///System.out.println("Mouse event, clickCount11111111 = " + e.getClickCount());
					
					int[] selectedRows = m_jTableSeanse.getSelectedRows();
					//int[] selectedRows = m_jTableUsers.getSelect
	                for(int i = 0; i < selectedRows.length; i++) 
	                {
	                     int selIndex = selectedRows[i];
	                     //m_iIDType = Integer.parseInt(ConvertUtils.convert(m_jTableSelectedJobs.getValueAt(selIndex, 0)));
	                     m_iSelectedIdGenre = m_jTableSeanse.getValueAt(selIndex, 0).toString();
	                     //System.out.println(selIndex);
	                     //System.out.println(m_stNameType);
	                     //RemoveSelectedJobs(selIndex);
	                }
				}*/
			}
		});
	 }
}
