package main;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.swing.*;

import sun.io.Converters;
//import javax.swing.undo.CannotUndoException;


//import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

//import org.apache.commons.beanutils.Converter;
//import org.apache.commons.beanutils.converters.IntegerConverter;

//import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;



public class CData
{
	// fokus	klonsoft
	//////////////////////////////////////////////////////////////////////
	// Данные для заполнения при конвертации для 5.2 из 91.203.208.5
	public static String m_stHostIP = "127.0.0.1";
	//public static String m_stHostIP = "91.203.208.130";
	//public static String m_stHostIP = "10.5.9.111";// Вместо белого IP
	public static String m_stPort = "3306";
	public static String m_stDataBaseName = "kul_db";
	public static String stUser = "root";
	//public static String stPass = "klon51222";
	public static String stPass = "761sex3110105";
	//////////////////////////////////////////////////////////////////////
	public static String stConnectionStringPath = "jdbc:mysql://" + m_stHostIP + ":" + m_stPort + "/" + m_stDataBaseName;
	public static String stDriver = "com.mysql.jdbc.Driver";
	public static ArrayList m_alEnumClients = null;
	public static ArrayList m_alEnumClientsAccounts = null;
	public static int[] m_iIndexGroups = null;
	public static ArrayList m_alIDClientsByGroups = null;
	
	public static ArrayList m_alConnectionNames = null; // Список названий соединений с БД
	public static String[] m_stArrayConnectionNames = null;
	
	// Два массива для занесения карт в базу данных!!!
	public static ArrayList m_alSerial = null;
	public static ArrayList m_alPin = null;
	
	// Для генерации случайного кода!!!
	private static Random random = new Random();
	
	// Здесь три массив для отчета по продажам карт
	public static ArrayList m_alSellingCards500 = null;
	public static ArrayList m_alSellingCards700 = null;
	public static ArrayList m_alSellingCards1000 = null;
	///////////////////////////////////////////////////
	
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
	 static public boolean GetClientInfoTestConnect()// Так просто для проверки коннекта!!!
	 {
		Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		boolean bIsRes = false;
		if(conn != null)
		{
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
			    	boolean bRet = false;
			    	stmt = conn.createStatement();
			    	rs = stmt.executeQuery("SELECT * FROM user_admin WHERE login = '" + CDialogStart.jTextFieldLoginInput.getText() + "' AND pass = '" + CDialogStart.jPasswordFieldInput.getText() + "';");
			    	while (rs.next()) 
			    	{
			    		bRet = true;
			    		CFrameMain.m_stLogin = rs.getString("login");// Получаем логин!!!
			    		System.out.println(CFrameMain.m_stLogin);
			    		
			    		CFrameMain.m_stPass = rs.getString("pass");// Получаем пароль!!!
			    		System.out.println(rs.getString("pass"));
			    		
			    		CFrameMain.m_stRules = rs.getString("rules");// Получаем номер прав(0 - это админ!!!)
			    		System.out.println(rs.getString("rules"));
			    		
			    		CFrameMain.m_stName = rs.getString("name");// Получаем ФИО!!!
			    		System.out.println(rs.getString("name"));
			    		
			    		CFrameMain.m_stGroupUser = rs.getString("group_id");// Получаем группу!!!
			    		System.out.println(rs.getString("group_id"));
			    		bIsRes = true;
			    	}
			    	if(!bRet)
			    	{
			    		JOptionPane.showMessageDialog(null, "Ошибка логина или пароля\nПрограмма будет закрыта.");
			    		bIsRes = false;
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

		return bIsRes;// Если правда , то пускаем в программу!!!
	 }
	 
	 //////////////////////////////////////////////////////////////////////////////////////////////////////////
	 // Добавление данных карточки (серия  и пинкод)
	 //////////////////////////////////////////////////////////////////////////////////////////////////////////
	 static public void AddNewCards(ArrayList alSerialCards, ArrayList alPinCards, String stPrice )
	 {
		 Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		 if(conn != null)
		 {
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
			    	int iSize = alSerialCards.size();// Кол-во карточек!!!
			    	for(int i = 0; i < iSize; i++)
			    	{
					   String stMysqlString = "INSERT INTO cards(serial, pin, is_using, price,group_name,user_sales,date_sales)" +
			      		 "VALUES('"+ alSerialCards.get(i) + "','" + alPinCards.get(i) + "','0','" + stPrice + "','','','0000-00-00 00:00:00');";
				      stmt = conn.createStatement();
				      stmt.executeUpdate(stMysqlString);
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
		 else
		 {
			 JOptionPane.showMessageDialog(null, "Ошибка соединения с БД при заносе карточек!!!");
		 }
	 }
	 // Генерация случайного кода для снятия денег!!!
	 static public int GenerationCode()
	 {
		 int iRes = 0;
		 
		 iRes = Math.abs(random.nextInt());
		 System.out.println("Случайное число = " + iRes);
		 
		 return iRes;
	 }
	 
	 // Выбор карты из серии (500, 700, 1000) для продажи
	 // Берем первую свободную т.е. is_using = 0
	 static public CStructCard SelectCardForSales(String stPriceArrtibute)
	 {
		Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		
		CStructCard StructCard = null; // Структура для данных карточки!!!
		
		if(conn != null)
		{
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
			    	stmt = conn.createStatement();
			    	rs = stmt.executeQuery("SELECT * FROM cards WHERE price = '" + stPriceArrtibute + "' AND " +
			    			"is_using = '0'");	 
			    	while (rs.next())
			    	{
			    		StructCard = new CStructCard();
				    	StructCard.m_stID = rs.getString("id");
				    	StructCard.m_stSerial = rs.getString("serial");
				    	StructCard.m_stPin = rs.getString("pin");
				    	StructCard.m_stIsUsing = rs.getString("is_using");
				    	StructCard.m_stPrice = rs.getString("price");
				    	break;// Выходим из цикла т.к. нужна только первая свободная карта!!!
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
		return StructCard;
	 }
	 // Оплата карты т.е. меняем is_using с 0 на 1
	 static public void SeillingCurrentCard(CStructCard StructCard, String stDate, String stGroup, String stUser )
	 {
		 Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		 if(conn != null)
		 {
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
					  String stMysqlString = "UPDATE cards SET is_using = '1', group_name = '" + stGroup +
					  "', user_sales = '" + stUser + "', date_sales = '" + stDate + "' WHERE id = '" + 
					  StructCard.m_stID + "';";
				      stmt = conn.createStatement();
				      stmt.executeUpdate(stMysqlString);
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
		 else
		 {
			 JOptionPane.showMessageDialog(null, "Ошибка соединения с БД при заносе карточек!!!");
		 }
	 }
	 // Выберем кол-во проданных карт!!!
	 static public void AllSeillingCards(String stGroupUser, boolean bIsAdmin)
	 {
		Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		m_alSellingCards500  = new ArrayList();
		m_alSellingCards700  = new ArrayList();
		m_alSellingCards1000 = new ArrayList();
		
		if(conn != null)
		{
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
			    	stmt = conn.createStatement();
			    	if(bIsAdmin)
			    	{	
			    		rs = stmt.executeQuery("SELECT * FROM cards WHERE is_using = '1';");
			    	}
			    	else
			    	{
			    		rs = stmt.executeQuery("SELECT * FROM cards WHERE is_using = '1' " +
			    				"AND group_name = '" + stGroupUser + "';");
			    	}
			    	while (rs.next())
			    	{
			    		if(rs.getInt("price") == 500)
			    		{
			    			m_alSellingCards500.add(rs.getString("price"));	
			    		}
			    		if(rs.getInt("price") == 700)
			    		{
			    			m_alSellingCards700.add(rs.getString("price"));	
			    		}
			    		if(rs.getInt("price") == 1000)
			    		{
			    			m_alSellingCards1000.add(rs.getString("price"));	
			    		}
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
	 }
	 
	 // Выберем всех пользлвателей для изменения или удаления
	 static public ArrayList GetUsers()
	 {
		Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		
		ArrayList alUserName = new ArrayList();
		
		if(conn != null)
		{
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
			    	alUserName.add("Выбор пользователя...");
			    	stmt = conn.createStatement();
			    	rs = stmt.executeQuery("SELECT * FROM user_admin");	 
			    	while (rs.next())
			    	{
			    		alUserName.add(rs.getString("name"));
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
		return alUserName;
	 }
	 // Выборка всех данных пользователей для заполнения формы редактирования пользоватеолей
	 static public CStructUserAdmin GetUsersAllInfo(String stName)
	 {
		Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		
		CStructUserAdmin StructUserAdmin = null;  
		
		if(conn != null)
		{
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
			    	stmt = conn.createStatement();
			    	rs = stmt.executeQuery("SELECT * FROM user_admin WHERE name = '" + stName + "';");	 
			    	while (rs.next())
			    	{
			    		StructUserAdmin = new CStructUserAdmin();
			    		StructUserAdmin.m_stID = rs.getString("id");
			    		StructUserAdmin.m_stLogin = rs.getString("login");
			    		StructUserAdmin.m_stPass = rs.getString("pass");
			    		StructUserAdmin.m_stRules = rs.getString("rules");
			    		StructUserAdmin.m_stName = rs.getString("name");
			    		StructUserAdmin.m_stGroup = rs.getString("group_id");
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
		return StructUserAdmin;
	 }
	 // Обновляем данные пользователя
	 static public void UpdateUsersData(String stID, String stLogin, String stPass,
			 String stRules, String stName, String stGroup )
	 {
		 Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		 if(conn != null)
		 {
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
					  String stMysqlString = "UPDATE user_admin SET login = '" + stLogin + "', " +
					  		"pass = '" + stPass + "', rules = '" + stRules
					  		+ "', name = '" + stName + "', group_id = '" + stGroup + "' WHERE id = '" + stID +  "';";
				      stmt = conn.createStatement();
				      stmt.executeUpdate(stMysqlString);
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
		 else
		 {
			 JOptionPane.showMessageDialog(null, "Ошибка соединения с БД при заносе карточек!!!");
		 }
	 }
	 
	 // Добавление нового пользователя
	 static public void AddNewUser(String stLogin, String stPass, String stRules, String stName, String stNameGroup )
	 {
		if(CData.GetUserNameByUserName(stName) != null)
		{
			if(CData.GetUserNameByUserName(stName).equals(stName))
			{
				JOptionPane.showMessageDialog(null, "Пользователь с таким именем уже существует");
				return;
			}
		}
		 
		 CStructGroup StructGroup = GetGroupByName(stNameGroup);
		 Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		 if(conn != null)
		 {
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
					   String stMysqlString = "INSERT INTO user_admin(login, pass, rules, name, group_id)" +
			      		 "VALUES('"+ stLogin + "','" + stPass + "','" 
			      		 + stRules + "','" + stName + "','" + StructGroup.m_stNameGroup + "');";
				      stmt = conn.createStatement();
				      stmt.executeUpdate(stMysqlString);
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
			    JOptionPane.showMessageDialog(null, "Пользователь добавлен!");
		 }
		 else
		 {
			 JOptionPane.showMessageDialog(null, "Ошибка соединения с БД при добавлении пользователя!!!");
		 }
	 }
	 // Занесение в базу нового кода обнуления
	 static public void AddNewCodeEraze(String stNewCode)
	 {
		 Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		 if(conn != null)
		 {
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
					  String stMysqlString = "DELETE FROM generate_pass;";
				      stmt = conn.createStatement();
				      stmt.executeUpdate(stMysqlString);
				      

					  stMysqlString = "INSERT INTO generate_pass(pass) VALUES('"+ stNewCode + "');";
				      stmt = conn.createStatement();
				      stmt.executeUpdate(stMysqlString);
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
		 else
		 {
			 JOptionPane.showMessageDialog(null, "Ошибка соединения с БД при заносе карточек!!!");
		 }
	 }
	 // Получить код обнуления из базы данных
	 static public String GetCodeEraze(String stInputCode)
	 {
		Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		String stPass = null;

		if(conn != null)
		{
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
			    	stmt = conn.createStatement();
			    	rs = stmt.executeQuery("SELECT pass FROM generate_pass WHERE pass = '" + stInputCode + "';");	 
			    	while (rs.next())
			    	{
			    		stPass = rs.getString("pass");
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
		return stPass;
	 }
	 // Получить назание группы!!!
	 static public String GetGroupNameByName(String stGroupName)
	 {
		Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		String stRes = null;

		if(conn != null)
		{
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
			    	stmt = conn.createStatement();
			    	rs = stmt.executeQuery("SELECT * FROM group_users WHERE group_name = '" + stGroupName + "';");	 
			    	while (rs.next())
			    	{
			    		stRes = rs.getString("group_name");
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
		return stRes;
	 }
	 ////////////////////////////////////////////////////////////////////////////////////////////////////
	 // Добавление групп пользователей
	 static public void AddNewGroup(String stNewGroup)
	 {
		 if(stNewGroup.equals(GetGroupNameByName(stNewGroup)))
		 {
			 JOptionPane.showMessageDialog(null, "Группа с таким названием уже существует!");
			 return;
		 }
		 Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		 if(conn != null)
		 {
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
					  String stMysqlString = "INSERT INTO group_users(group_name) VALUES('" + stNewGroup + "');";
					  //String stMysqlString = "INSERT INTO `group` (`id`, `group_name`) VALUES (NULL, '" + stNewGroup + "');";
				      stmt = conn.createStatement();
				      stmt.executeUpdate(stMysqlString);
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
			    JOptionPane.showMessageDialog(null, "Группа " + CDialogAddGroup.jTextFieldGroupName.getText() + " успешно добавлена!!!");
		 }
		 else
		 {
			 JOptionPane.showMessageDialog(null, "Ошибка соединения с БД при заносе карточек!!!");
		 }
	 }
	 // Выбирем группу по имени
	 static public CStructGroup GetGroupByName(String stName)
	 {
		Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		
		CStructGroup StructGroup = null;  
		
		if(conn != null)
		{
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
			    	stmt = conn.createStatement();
			    	rs = stmt.executeQuery("SELECT * FROM group_users WHERE group_name = '" + stName + "';");	 
			    	while (rs.next())
			    	{
			    		StructGroup = new CStructGroup();
			    		StructGroup.m_iID = rs.getInt("id");
			    		StructGroup.m_stNameGroup = rs.getString("group_name");
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
		return StructGroup;
	 }
	 // Выбираем имена групп
	 static public ArrayList GetGroupNames()
	 {
		Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		
		ArrayList alGroupName = new ArrayList();
		
		if(conn != null)
		{
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
			    	alGroupName.add("Выбор группы...");
			    	stmt = conn.createStatement();
			    	rs = stmt.executeQuery("SELECT * FROM group_users");	 
			    	while (rs.next())
			    	{
			    		alGroupName.add(rs.getString("group_name"));
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
		return alGroupName;
	 }
	 // Удалить пользователя
	 static public void DeleteUser(String stIdUser)
	 {
		 Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		 if(conn != null)
		 {
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
					  String stMysqlString = "DELETE FROM user_admin WHERE id = '"+ stIdUser + "';";
				      stmt = conn.createStatement();
				      stmt.executeUpdate(stMysqlString);
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
		 else
		 {
			 JOptionPane.showMessageDialog(null, "Ошибка соединения с БД при заносе карточек!!!");
		 }
	 }
	 // Удалить группу
	 static public void DeleteGroups(String stIdGroup)
	 {
		 Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		 if(conn != null)
		 {
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
					  String stMysqlString = "DELETE FROM group_users WHERE id = '"+ stIdGroup + "';";
				      stmt = conn.createStatement();
				      stmt.executeUpdate(stMysqlString);
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
		 else
		 {
			 JOptionPane.showMessageDialog(null, "Ошибка соединения с БД при заносе карточек!!!");
		 }
	 }
	 // Обновляем данные группы
	 static public void UpdateGroup(String stID, String stGroupName )
	 {
		 if(stGroupName.equals(GetGroupNameByName(stGroupName)))
		 {
			 JOptionPane.showMessageDialog(null, "Группа с таким названием уже существует!");
			 return;
		 }
		 Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		 if(conn != null)
		 {
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
					  String stMysqlString = "UPDATE group_users SET group_name = '" + stGroupName + "' WHERE id = '" + stID +  "';";
				      stmt = conn.createStatement();
				      stmt.executeUpdate(stMysqlString);
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
			    JOptionPane.showMessageDialog(null, "Изменения применены!");
		 }
		 else
		 {
			 JOptionPane.showMessageDialog(null, "Ошибка соединения с БД при заносе карточек!!!");
		 }
	 }
	 // Создаем глобальный отчет!!!
	 static public ArrayList GetGReports()
	 {
		Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		
		ArrayList alReports = new ArrayList();
		
		if(conn != null)
		{
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
			    	stmt = conn.createStatement();
			    	if(CFrameMain.m_stRules.equals("0"))
			    	{
			    		rs = stmt.executeQuery("SELECT * FROM cards WHERE is_using = '1' AND " +
				    			"date_sales != '0000-00-00 00:00:00'");	 
			    	}
			    	else
			    	{
			    		rs = stmt.executeQuery("SELECT * FROM cards WHERE is_using = '1' AND " +
				    			"date_sales != '0000-00-00 00:00:00' AND group_name = '" + CFrameMain.m_stGroupUser + "'");	 
					}
			    	
			    	while (rs.next())
			    	{
			    		CStructReport StructReport = new CStructReport();
			    		
			    		StructReport.m_stDate = rs.getString("date_sales");
			    		StructReport.m_stSerial = rs.getString("serial");
			    		StructReport.m_stPIN = rs.getString("pin");
			    		StructReport.m_stPrice = rs.getString("price");
			    		StructReport.m_stGroup = rs.getString("group_name");
			    		StructReport.m_stUserSales = rs.getString("user_sales");
			    		
			    		alReports.add(StructReport);
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
		return alReports;
	 }
	 ///////////////////////////////////////////////////////////////////////////
	 // Поиск карты по серии
	 ///////////////////////////////////////////////////////////////////////////
	 static public ArrayList GetCardBySerial(String stSerialCard)
{
		 ArrayList alReports = new ArrayList(); 
		 Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
			if(conn != null)
			{
				    Statement stmt = null;
				    ResultSet rs = null;
				    try 
				    {
				    	stmt = conn.createStatement();

						    		System.out.println("Попали в первый запрос!!");
						    		rs = stmt.executeQuery("SELECT * FROM cards WHERE serial = '"
						    				+ stSerialCard + "';");	 
		
			    	
				    	while (rs.next())
				    	{
				    		CStructReport StructReport = new CStructReport();
				    		
//0000-00-00 00:00:00
				    		//StructReport.m_stDate = rs.getString("date_sales");
				    		StructReport.m_stDate = "0000-00-00 00:00:00";
				    		StructReport.m_stSerial = rs.getString("serial");
				    		StructReport.m_stPIN = rs.getString("pin");
				    		StructReport.m_stPrice = rs.getString("price");
				    		StructReport.m_stGroup = rs.getString("group_name");
				    		StructReport.m_stUserSales = rs.getString("user_sales");
				    		StructReport.m_stIsUsing = rs.getString("is_using");
				    		
				    		alReports.add(StructReport);
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
		 return alReports;
}
	 
	 
	 // Создаем отчеты за период
	 static public ArrayList GetReportByPeriod(String stGroup, String stUser, 
			 									String stStartDate, String stEndDate, boolean bIsPeriodSelected)
	 {
		Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		
		ArrayList alReports = new ArrayList();
		
		if(conn != null)
		{
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
			    	stmt = conn.createStatement();
			    	if(CFrameMain.m_stRules.equals("0"))
			    	{
			    		// Здесь учитываем период(галочка не поставлена!!!)
			    		if(!bIsPeriodSelected)
			    		{
					    	// 1. Вариант только за период
					    	if(stGroup.equals("Выбор группы...") && stUser.equals("Выбор пользователя..."))
					    	//if(stGroup == "Выбор группы..." && stGroup == "Выбор пользователя...")
					    	{
					    		System.out.println("Попали в первый запрос!!");
					    		rs = stmt.executeQuery("SELECT * FROM cards WHERE DATE(date_sales) BETWEEN '"
					    				+ stStartDate + "' AND '" + stEndDate + "' AND (is_using = '1' OR is_using = '2');");	 
					    	}
					    	// 2. Вариант если выбрана только группа
					    	if(!stGroup.equals("Выбор группы...") && stUser.equals("Выбор пользователя..."))
					    	{
					    		System.out.println("Попали во второй запрос!!");
					    		rs = stmt.executeQuery("SELECT * FROM cards WHERE DATE(date_sales) BETWEEN '"
					    				+ stStartDate + "' AND '" + stEndDate + 
					    				"' AND group_name = '" + stGroup + "' AND (is_using = '1' OR is_using = '2');");
					    	}
					    	// 3. Вариант если выбран только кассир
					    	if(stGroup.equals("Выбор группы...") && !stUser.equals("Выбор пользователя..."))
					    	{
					    		System.out.println("Попали в третий запрос!!");
					    		rs = stmt.executeQuery("SELECT * FROM cards WHERE DATE(date_sales) BETWEEN '"
					    				+ stStartDate + "' AND '" + stEndDate + 
					    				"' AND user_sales = '" + stUser + "' AND (is_using = '1' OR is_using = '2');");
					    	}
			    		}
			    		else// Здесь период неучитываем!!!
			    		{
			    			// 1. Вариант общий (is_using = 1)
					    	if(stGroup.equals("Выбор группы...") && stUser.equals("Выбор пользователя..."))
					    	{
					    		System.out.println("Попали в первый запрос без диапазона!!");
					    		rs = stmt.executeQuery("SELECT * FROM cards WHERE is_using = '1';");	 
					    	}
			    			// 2. Вариант выбор по группе (is_using = 1)
					    	if(!stGroup.equals("Выбор группы...") && stUser.equals("Выбор пользователя..."))
					    	{
					    		System.out.println("Попали во второй запрос без диапазона!!");
					    		rs = stmt.executeQuery("SELECT * FROM cards WHERE group_name = '"
					    				+ stGroup + "' AND is_using = '1';");	 
					    	}
			    			// 2. Вариант выбор по кассиру (is_using = 1)
					    	if(stGroup.equals("Выбор группы...") && !stUser.equals("Выбор пользователя..."))
					    	{
					    		System.out.println("Попали в третий запрос без диапазона!!");
					    		rs = stmt.executeQuery("SELECT * FROM cards WHERE user_sales = '"
					    				+ stUser + "' AND is_using = '1';");	 
					    	}
			    		}
			    	}
			    	else
			    	{
			    		rs = stmt.executeQuery("SELECT * FROM cards WHERE DATE(date_sales) BETWEEN '"
			    				+ stStartDate + "' AND '" + stEndDate + 
			    				"' AND user_sales = '" + CFrameMain.m_stName + "' AND is_using = '1';");
			    	}
			    	
			    	/*if(CFrameMain.m_stRules.equals("0"))
			    	{
			    		rs = stmt.executeQuery("SELECT * FROM cards WHERE is_using = '1' AND " +
				    			"date_sales != '0000-00-00 00:00:00'");	 
			    	}
			    	else
			    	{
			    		rs = stmt.executeQuery("SELECT * FROM cards WHERE is_using = '1' AND " +
				    			"date_sales != '0000-00-00 00:00:00' AND group_name = '" + CFrameMain.m_stGroupUser + "'");	 
					}*/
			    	
			    	while (rs.next())
			    	{
			    		CStructReport StructReport = new CStructReport();
			    		
			    		StructReport.m_stDate = rs.getString("date_sales");
			    		StructReport.m_stSerial = rs.getString("serial");
			    		StructReport.m_stPIN = rs.getString("pin");
			    		StructReport.m_stPrice = rs.getString("price");
			    		StructReport.m_stGroup = rs.getString("group_name");
			    		StructReport.m_stUserSales = rs.getString("user_sales");
			    		
			    		alReports.add(StructReport);
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
		return alReports;
	 }
	 
	 // Создаем отчеты за период с учетом непроданных карт
	 static public ArrayList GetReportByPeriodWithNoSales(String stGroup, String stUser, 
			 									String stStartDate, String stEndDate, boolean bIsPeriodSelected)
	 {
		Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		
		ArrayList alReports = new ArrayList();
		
		if(conn != null)
		{
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
			    	stmt = conn.createStatement();
			    	if(CFrameMain.m_stRules.equals("0"))
			    	{
			    		// Здесь учитываем период(галочка не поставлена!!!)
			    		if(!bIsPeriodSelected)
			    		{
					    	// 1. Вариант только за период
					    	if(stGroup.equals("Выбор группы...") && stUser.equals("Выбор пользователя..."))
					    	//if(stGroup == "Выбор группы..." && stGroup == "Выбор пользователя...")
					    	{
					    		System.out.println("Попали в первый запрос!!");
					    		rs = stmt.executeQuery("SELECT * FROM cards WHERE DATE(date_sales) BETWEEN '"
					    				+ stStartDate + "' AND '" + stEndDate + "' AND is_using = '0';");	 
					    	}
					    	// 2. Вариант если выбрана только группа
					    	if(!stGroup.equals("Выбор группы...") && stUser.equals("Выбор пользователя..."))
					    	{
					    		System.out.println("Попали во второй запрос!!");
					    		rs = stmt.executeQuery("SELECT * FROM cards WHERE DATE(date_sales) BETWEEN '"
					    				+ stStartDate + "' AND '" + stEndDate + 
					    				"' AND group_name = '" + stGroup + "' AND is_using = '0';");
					    	}
					    	// 3. Вариант если выбран только кассир
					    	if(stGroup.equals("Выбор группы...") && !stUser.equals("Выбор пользователя..."))
					    	{
					    		System.out.println("Попали в третий запрос!!");
					    		rs = stmt.executeQuery("SELECT * FROM cards WHERE DATE(date_sales) BETWEEN '"
					    				+ stStartDate + "' AND '" + stEndDate + 
					    				"' AND user_sales = '" + stUser + "' AND is_using = '0';");
					    	}
			    		}
			    		else// Здесь период неучитываем!!!
			    		{
			    			// 1. Вариант общий (is_using = 1)
					    	if(stGroup.equals("Выбор группы...") && stUser.equals("Выбор пользователя..."))
					    	{
					    		System.out.println("Попали в первый запрос без диапазона!!");
					    		rs = stmt.executeQuery("SELECT * FROM cards WHERE is_using = '0';");	 
					    	}
			    			// 2. Вариант выбор по группе (is_using = 1)
					    	if(!stGroup.equals("Выбор группы...") && stUser.equals("Выбор пользователя..."))
					    	{
					    		System.out.println("Попали во второй запрос без диапазона!!");
					    		rs = stmt.executeQuery("SELECT * FROM cards WHERE group_name = '"
					    				+ stGroup + "' AND is_using = '0';");	 
					    	}
			    			// 2. Вариант выбор по кассиру (is_using = 1)
					    	if(stGroup.equals("Выбор группы...") && !stUser.equals("Выбор пользователя..."))
					    	{
					    		System.out.println("Попали в третий запрос без диапазона!!");
					    		rs = stmt.executeQuery("SELECT * FROM cards WHERE user_sales = '"
					    				+ stUser + "' AND is_using = '0';");	 
					    	}
			    		}
			    	}
			    	else
			    	{
			    		rs = stmt.executeQuery("SELECT * FROM cards WHERE DATE(date_sales) BETWEEN '"
			    				+ stStartDate + "' AND '" + stEndDate + 
			    				"' AND user_sales = '" + CFrameMain.m_stName + "' AND is_using = '0';");
			    	}
			    	
			    	/*if(CFrameMain.m_stRules.equals("0"))
			    	{
			    		rs = stmt.executeQuery("SELECT * FROM cards WHERE is_using = '1' AND " +
				    			"date_sales != '0000-00-00 00:00:00'");	 
			    	}
			    	else
			    	{
			    		rs = stmt.executeQuery("SELECT * FROM cards WHERE is_using = '1' AND " +
				    			"date_sales != '0000-00-00 00:00:00' AND group_name = '" + CFrameMain.m_stGroupUser + "'");	 
					}*/
			    	
			    	while (rs.next())
			    	{
			    		CStructReport StructReport = new CStructReport();
			    		//0000-00-00 00:00:00
			    		//StructReport.m_stDate = rs.getString("date_sales");
			    		StructReport.m_stDate = "0000-00-00 00:00:00";
			    		StructReport.m_stSerial = rs.getString("serial");
			    		StructReport.m_stPIN = rs.getString("pin");
			    		StructReport.m_stPrice = rs.getString("price");
			    		StructReport.m_stGroup = rs.getString("group_name");
			    		StructReport.m_stUserSales = rs.getString("user_sales");
			    		
			    		alReports.add(StructReport);
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
		return alReports;
	 }
	 
	 
	 // Функция обнуления для вывода денег 
	 static public void UpdateBalance()
	 {
		 Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		 if(conn != null)
		 {
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
					  String stMysqlString = "UPDATE cards SET is_using = '2' WHERE " +
					  		"is_using = '1' AND group_name = '" + CFrameMain.m_stGroupUser + "';";
				      stmt = conn.createStatement();
				      stmt.executeUpdate(stMysqlString);
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
		 else
		 {
			 JOptionPane.showMessageDialog(null, "Ошибка соединения с БД при заносе карточек!!!");
		 }
	 }
	 // Общий отчет по платежам с атрибутом '1'
	 static public ArrayList GetAllReports()
	 {
		Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		
		ArrayList alReports = new ArrayList();
		
		if(conn != null)
		{
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
			    	stmt = conn.createStatement();
			    	if(CFrameMain.m_stRules.equals("0"))
			    	{
			    		rs = stmt.executeQuery("SELECT * FROM cards WHERE is_using = '1' AND " +
				    			"date_sales != '0000-00-00 00:00:00'");	 
			    	}
			    	else
			    	{
			    		rs = stmt.executeQuery("SELECT * FROM cards WHERE is_using = '1' AND " +
				    			"date_sales != '0000-00-00 00:00:00' AND group_name = '" + CFrameMain.m_stGroupUser + "'");	 
					}
			    	
			    	while (rs.next())
			    	{
			    		CStructReport StructReport = new CStructReport();
			    		
			    		StructReport.m_stDate = rs.getString("date_sales");
			    		StructReport.m_stSerial = rs.getString("serial");
			    		StructReport.m_stPIN = rs.getString("pin");
			    		StructReport.m_stPrice = rs.getString("price");
			    		StructReport.m_stGroup = rs.getString("group_name");
			    		StructReport.m_stUserSales = rs.getString("user_sales");
			    		
			    		alReports.add(StructReport);
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
		return alReports;
	 }
	 ////////////////////////////////////////////////////////////////////////////////////////////
	 // Получить назание пользователя!!!
	 ////////////////////////////////////////////////////////////////////////////////////////////
	 static public String GetUserNameByUserName(String stUserName)
	 {
		Connection conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
		String stRes = null;

		if(conn != null)
		{
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
			    	stmt = conn.createStatement();
			    	rs = stmt.executeQuery("SELECT * FROM user_admin WHERE name = '" + stUserName + "';");	 
			    	while (rs.next())
			    	{
			    		stRes = rs.getString("name");
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
		return stRes;
	 }
}

