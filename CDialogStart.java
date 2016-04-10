package theater.com;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
//import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Frame;
//import java.awt.TrayIcon;
//import java.awt.BorderLayout;
import javax.swing.JDialog;
//import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
//import java.awt.event.ActionEvent;
import java.io.*;
import java.net.URL;
//import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
//import java.nio.*;


//import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
//import javax.swing.JScrollPane;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.KeyStroke;
import javax.swing.Timer;

//import sun.io.Converters;
//import java.awt.GridBagLayout;
import javax.swing.JPasswordField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
//import javax.swing.ImageIcon;
import java.awt.Dimension;

//import main.CSettings;

//





//import com.sun.org.apache.bcel.internal.generic.LoadClass;

//import java.awt.Dimension;


public class CDialogStart extends JDialog {

	public static boolean m_bClose; 
	public static boolean m_bOk;
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JLabel jLabel3 = null;

	private JTextField jTextFieldLogin = null;

	private JTextField jTextFieldServer = null;

	private JTextField jTextFieldPort = null;

	private JCheckBox jCheckBoxSaveParams = null;

	private JCheckBox jCheckBoxSavePass = null;

	public JButton jButtonOk = null;

	private File m_File;  //  @jve:decl-index=0:
	private JPanel jPanelUTM5 = null;
	private JLabel jLabel6 = null;
	private JPasswordField jPasswordFieldPass = null;
	private JLabel jLabel12 = null;
	private JTextField jTextFieldNameUTM5 = null;
	public static URL url = null;  //  @jve:decl-index=0:
	public static ClassLoader cl = null;  //  @jve:decl-index=0:
	//public Class<?> m_cClass = null;  //  @jve:decl-index=0:
	
	public static String m_stPathConnectCFG = "settings/Connect.cfg";
	private JButton jButtonCencel = null;
	
	
	/**
	 * @param owner
	 */
	public CDialogStart(Frame owner) 
	{
		super(owner);
		initialize();
		
		m_bClose = false;
		m_bOk = false;
		DataOutputStream OutStream;
		
		 /*byte[] salt = {
		            (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
		            (byte)0x56, (byte)0x34, (byte)0xE3, (byte)0x03
		        };*/
		
		try
		{
			CMain.m_Key = new SecretKeySpec(CMain.m_ByteSalt, "DES");//KeyGenerator.getInstance("DES").generateKey();
			CMain.m_Encrypter = new DesEncrypter(CMain.m_Key);
		}
		catch (Exception ex) 
		{
			System.out.println(ex.getMessage());
		}

		m_File = new File(CMain.m_stPathConnectCFG);
		
		// Файл настроек не существует!!!
		if(!m_File.exists())
		{
			JOptionPane.showMessageDialog(null, 
					"ВНИМАНИЕ!\nВы запустили программу либо впервый раз,\n" + 
					"либо файл настроек подключения - Connect.cfg - был удален...\n" +
					"Произведите первоначальные настройки подключения программы.\n");
			try
			{
				 // Соединение с базой данных!!!
			      OutStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(CMain.m_stPathConnectCFG)));
			      OutStream.writeBytes(CMain.m_Encrypter.encrypt("saveparams=0") + "\n");
			      OutStream.writeBytes(CMain.m_Encrypter.encrypt("savelogin=0\n") + "\n");
	    	      OutStream.writeBytes(CMain.m_Encrypter.encrypt("login=root\n") + "\n");
	    	      OutStream.writeBytes(CMain.m_Encrypter.encrypt("pass=root\n") + "\n");
	    	      OutStream.writeBytes(CMain.m_Encrypter.encrypt("server=localhost\n") + "\n");
	    	      OutStream.writeBytes(CMain.m_Encrypter.encrypt("port=3306\n") + "\n");
	    	      OutStream.writeBytes(CMain.m_Encrypter.encrypt("name_utm5=DATABASE_NAME\n") + "\n");
	    	      
	    	      // Р РЋР С•Р ВµР Т‘Р С‘Р Р…Р ВµР Р…Р С‘Р Вµ РЎРѓ Р В±Р В°Р В·Р С•Р в„– Р Т‘Р В°Р Р…Р Р…РЎвЂ№РЎвЂ¦ "Р вЂ”Р С’Р Р‡Р вЂ™Р С™РїС—Р…? РїС—Р…? Р СњР С’Р В Р Р‡Р вЂќР В«"(UTM5_OFFER)
	    	      OutStream.writeBytes(CMain.m_Encrypter.encrypt("login_offer=root") + "\n");
	    	      OutStream.writeBytes(CMain.m_Encrypter.encrypt("pass_offer=root\n") + "\n");
	    	      OutStream.writeBytes(CMain.m_Encrypter.encrypt("server_offer=localhost\n") + "\n");
	    	      OutStream.writeBytes(CMain.m_Encrypter.encrypt("port_offer=3306\n") + "\n");
	    	      OutStream.writeBytes(CMain.m_Encrypter.encrypt("name_utm_offer=DATABASE_NAME\n") + "\n");
	    	      
	    	      OutStream.close();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}

		else// Р В¤Р В°Р в„–Р В» Р Р…Р В°РЎРѓРЎвЂљРЎР‚Р С•Р ВµР С” РЎРѓРЎС“РЎвЂ°Р ВµРЎРѓРЎвЂљР Р†РЎС“Р ВµРЎвЂљ 
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
					 alSettings.add(CMain.m_Encrypter.decrypt(line));
				 }
		    }
		    catch(Exception ex)
		    {
		    	
		    }
		    String stTemp = null;
		    String stResult = null;
		    
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
		    			jCheckBoxSaveParams.setSelected(true);
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
		    			jCheckBoxSavePass.setSelected(false);
		    			i++;
		    		}
		    		else
		    		{
		    			this.jCheckBoxSavePass.setSelected(true);
		    			i++;
		    		}
		    	}
		    	stTemp = (String)alSettings.get(i);
		    	stResult = null;
		    	if(i == 2)
		    	{
		    		stResult = stTemp.replaceFirst("login=", "");
		    		jTextFieldLogin.setText(stResult);
		    		i++;
		    	}
		    	
		    	stTemp = (String)alSettings.get(i);
		    	if(i == 3 && jCheckBoxSavePass.isSelected())
		    	{
		    		stResult = stTemp.replaceFirst("pass=", "");
		    		//jTextFieldPass.setText(stResult);
		    		jPasswordFieldPass.setText(stResult);
		    		i++;
		    	}
		    	
		    	stTemp = (String)alSettings.get(i);
		    	if(i == 4)
		    	{
		    		stResult = stTemp.replaceFirst("server=", "");
		    		jTextFieldServer.setText(stResult);
		    		i++;
		    	}
		    	
		    	stTemp = (String)alSettings.get(i);
		    	if(i == 5)
		    	{
		    		stResult = stTemp.replaceFirst("port=", "");
		    		jTextFieldPort.setText(stResult);
		    		i++;
		    	}
		    	
		    	stTemp = (String)alSettings.get(i);
		    	if(i == 6)
		    	{
		    		stResult = stTemp.replaceFirst("name_utm5=", "");
		    		jTextFieldNameUTM5.setText(stResult);
		    		i++;
		    	}
		    	
		    	// Р вЂ”Р В°Р С—Р С•Р В»Р Р…Р ВµР Р…Р С‘Р Вµ Р С—Р С•Р В»Р ВµР в„– Р Т‘Р В»РЎРЏ UTM5_OFFER
		    	stTemp = (String)alSettings.get(i);
		    	if(i == 7)
		    	{
		    		stResult = stTemp.replaceFirst("login_offer=", "");
		    		//jTextFieldLoginOffer.setText(stResult);
		    		i++;
		    	}
		    	stTemp = (String)alSettings.get(i);
		    	if(i == 8)
		    	{
		    		stResult = stTemp.replaceFirst("pass_offer=", "");
		    		//jTextFieldPassOffer.setText(stResult);
		    		//jPasswordFieldPassOffer.setText(stResult);
		    		i++;
		    	}
		    	stTemp = (String)alSettings.get(i);
		    	if(i == 9)
		    	{
		    		stResult = stTemp.replaceFirst("server_offer=", "");
		    		//jTextFieldServerOffer.setText(stResult);
		    		i++;
		    	}
		    	stTemp = (String)alSettings.get(i);
		    	if(i == 10)
		    	{
		    		stResult = stTemp.replaceFirst("port_offer=", "");
		    		//jTextFieldPortOffer.setText(stResult);
		    		i++;
		    	}
		    	stTemp = (String)alSettings.get(i);
		    	if(i == 11)
		    	{
		    		stResult = stTemp.replaceFirst("name_utm_offer=", "");
		    		//jTextFieldNameUTMOFFER.setText(stResult);
		    		i++;
		    	}
		    	
		    }
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() 
	{
		this.setSize(350, 283);
		this.setResizable(false);
		this.setTitle("Настройки");
		this.setContentPane(getJContentPane());
		this.setLocationRelativeTo(null);
		
		//CMain.GetMain().addTrayIcon("Р вЂ”Р В°РЎРЏР Р†Р С”Р С‘ Р С‘ Р Р…Р В°РЎР‚РЎРЏР Т‘РЎвЂ№", "Р СњР ВµРЎвЂљ РЎРѓР С•Р ВµР Т‘Р С‘Р Р…Р ВµР Р…Р С‘РЎРЏ - Р Р†РЎвЂ№Р С—Р С•Р В»Р Р…Р С‘РЎвЂљР Вµ Р Р†РЎвЂ¦Р С•Р Т‘ Р Р† РЎРѓР С‘РЎРѓРЎвЂљР ВµР С?РЎС“","/resources/tray_report_no.gif");
		this.addWindowListener(new java.awt.event.WindowAdapter() 
		{   
			public void windowClosing(java.awt.event.WindowEvent e)
			{
				m_bClose = true; 
				//CMain.m_Main.setVisible(false);
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		Object actionKey = "action key";
		getJContentPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), actionKey);
		//getJContentPane().getActionMap().put(actionKey, new AbstractAction() {
/*			@Override
			public void actionPerformed(ActionEvent e)
			{
				// Р вЂ”Р В°Р С–РЎР‚РЎС“Р В·Р С”Р В° Р С•Р С”Р Р…Р В° Р Р†Р Р†Р С•Р Т‘Р В° Р В»Р С•Р С–Р С‘Р Р…Р В° Р С‘ Р С—Р В°РЎР‚Р С•Р В»РЎРЏ Р С—Р С•Р В»РЎРЉР В·Р С•Р Р†Р В°РЎвЂљР ВµР В»РЎРЏ!!!
				CMain.m_DialogEnterUser = new CDialogEnterUser();
				CMain.m_DialogEnterUser.setVisible(true);
				dispose();
				//StartMainFrame();
			}
		});*/
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() 
	{
		if (jContentPane == null) 
		{
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(8, 8, 329, 25));
			jLabel6.setText("Настройки подключения");
			jLabel3 = new JLabel();
			jLabel3.setText("Порт:");
			jLabel3.setBounds(new Rectangle(8, 105, 169, 25));
			jLabel2 = new JLabel();
			jLabel2.setText("Адрес сервера IP:");
			jLabel2.setBounds(new Rectangle(8, 73, 169, 25));
			jLabel1 = new JLabel();
			jLabel1.setText("Пароль:");
			jLabel1.setBounds(new Rectangle(8, 41, 169, 25));
			jLabel = new JLabel();
			jLabel.setText("Логин:");
			jLabel.setBounds(new Rectangle(8, 8, 169, 26));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJCheckBoxSaveParams(), null);
			jContentPane.add(getJCheckBoxSavePass(), null);
			jContentPane.add(getJButtonOk(), null);
			jContentPane.add(getJPanelUTM5(), null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(getJButtonCencel(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTextFieldLogin	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldLogin()
	{
		if (jTextFieldLogin == null) 
		{
			jTextFieldLogin = new JTextField();
			jTextFieldLogin.setBounds(new Rectangle(184, 8, 137, 25));
		}
		return jTextFieldLogin;
	}

	/**
	 * This method initializes jTextFieldServer	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldServer() 
	{
		if (jTextFieldServer == null)
		{
			jTextFieldServer = new JTextField();
			jTextFieldServer.setBounds(new Rectangle(184, 73, 137, 25));
		}
		return jTextFieldServer;
	}

	/**
	 * This method initializes jTextFieldPort	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldPort()
	{
		if (jTextFieldPort == null)
		{
			jTextFieldPort = new JTextField();
			jTextFieldPort.setBounds(new Rectangle(184, 105, 137, 25));
		}
		return jTextFieldPort;
	}

	/**
	 * This method initializes jCheckBoxSaveParams	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBoxSaveParams() 
	{
		if (jCheckBoxSaveParams == null) 
		{
			jCheckBoxSaveParams = new JCheckBox();
			jCheckBoxSaveParams.setBounds(new Rectangle(16, 216, 21, 21));
			jCheckBoxSaveParams.setMnemonic(KeyEvent.VK_UNDEFINED);
			jCheckBoxSaveParams.setSelected(true);
			jCheckBoxSaveParams.setVisible(false);
		}
		return jCheckBoxSaveParams;
	}

	/**
	 * This method initializes jCheckBoxSavePass	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBoxSavePass() 
	{
		if (jCheckBoxSavePass == null) 
		{
			jCheckBoxSavePass = new JCheckBox();
			jCheckBoxSavePass.setBounds(new Rectangle(408, 216, 21, 21));
			jCheckBoxSavePass.setSelected(true);
			jCheckBoxSavePass.setVisible(false);
		}
		return jCheckBoxSavePass;
	}

	/**
	 * This method initializes jButtonOk	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonOk()
	{
		if (jButtonOk == null)
		{
			jButtonOk = new JButton();
			jButtonOk.setBounds(new Rectangle(8, 216, 121, 25));
			jButtonOk.setSelected(true);
			jButtonOk.setMnemonic(KeyEvent.VK_UNDEFINED);
			jButtonOk.setText("Ок");
			
			jButtonOk.addKeyListener(new java.awt.event.KeyAdapter()
			{
				public void keyPressed(java.awt.event.KeyEvent e) 
				{
					m_bOk = true;
				}
			});
			jButtonOk.addActionListener(new java.awt.event.ActionListener() 
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					// Загрузка окна ввода логина и пароля пользователя!!!
					/*CMain.m_DialogEnterUser = new CDialogEnterUser();
					CMain.m_DialogEnterUser.setVisible(true);*/
					StartMainFrame();
					dispose();
					JOptionPane.showMessageDialog(null, "Произошло изменение настроек подключения!\n" + "" +
							"Программа будет закрыта!\n" + 
							"Зайдите в программу заново под своим логином и паролем.");
					setVisible(false);
					dispose();
					System.exit(0);
				}
			});
		}
		return jButtonOk;
	}
	

	public static boolean StartProgram(String stConnectionStringPath,String stPort,String stUser,String stPass,String stUTM5Name,
							String stConnectionStringPathOffer,String stPortOffer,String stUserOffer,String stPassOffer,String stUTM5_OFFERName)
	{
//		 
/*		if(CData.RegisterDriver() == false)
		{
			return false;
		}
		*/

		// Р РЋР С•Р ВµР Т‘Р С‘Р Р…Р ВµР Р…Р С‘Р Вµ Р Т‘Р В»РЎРЏ BGBilling
		/*CData.stConnectionStringPath = "jdbc:mysql://" + stConnectionStringPath + ":" + stPort + "/" + stUTM5Name;
		CData.stUser = stUser;
		CData.stPass = stPass;
		// Р РЋР С•Р ВµР Т‘Р С‘Р Р…Р ВµР Р…Р С‘Р Вµ Р Т‘Р В»РЎРЏ UTM5_OFFER("Р вЂ”Р С’Р Р‡Р вЂ™Р С™РїС—Р…? РїС—Р…? Р СњР С’Р В Р Р‡Р вЂќР В«")
		//String stConnectionStringPathOffer = "jdbc:mysql://" + DataOffer.stConnectionStringPath + ":" + stPort + "/UTM5_OFFER";
		CDataOffer.stConnectionStringPath = "jdbc:mysql://" + stConnectionStringPathOffer + ":" + stPortOffer + "/" + stUTM5_OFFERName;
		CDataOffer.stUser = stUserOffer;//"root";//stUser;
		CDataOffer.stPass = stPassOffer;//"root";//stPass;
*/		// Р РЋР С•Р ВµР Т‘Р С‘Р Р…Р ВµР Р…Р С‘Р Вµ Р Т‘Р В»РЎРЏ mysql - РЎР‚Р В°РЎРѓР С—Р С•Р В·Р Р…Р С•Р Р†Р В°Р Р…Р С‘Р Вµ Р С—РЎР‚Р В°Р Р†
		Connection conn = null;
		Connection connOffer = null;
		try
		{
			//conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
			//connOffer = CDataOffer.CreateConnection(CDataOffer.stConnectionStringPath, CDataOffer.stUser, CDataOffer.stPass);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			return false;
		}
		
		if(conn == null)
		{
			JOptionPane.showMessageDialog(null, "Р СњР ВµРЎвЂљ РЎРѓР С•Р ВµР Т‘Р С‘Р Р…Р ВµР Р…Р С‘РЎРЏ РЎРѓ  BGBilling!!!\nР СџРЎР‚Р С•Р С–РЎР‚Р В°Р С?Р С?Р В° Р В±РЎС“Р Т‘Р ВµРЎвЂљ Р В·Р В°Р С”РЎР‚РЎвЂ№РЎвЂљР В°!!!");
			System.exit(0);
			return false;
		}
		if(connOffer == null)
		{
			JOptionPane.showMessageDialog(null, "Р СњР ВµРЎвЂљ РЎРѓР С•Р ВµР Т‘Р С‘Р Р…Р ВµР Р…Р С‘РЎРЏ РЎРѓ  UTM5_OFFER (Р вЂ?Р В°Р В·Р В° Р В·Р В°РЎРЏР Р†Р С•Р С” Р С‘ Р Р…Р В°РЎР‚РЎРЏР Т‘Р С•Р Р†)!!!\nР СџРЎР‚Р С•Р С–РЎР‚Р В°Р С?Р С?Р В° Р В±РЎС“Р Т‘Р ВµРЎвЂљ Р В·Р В°Р С”РЎР‚РЎвЂ№РЎвЂљР В°!!!");
			System.exit(0);
			return false;
		}
	/*	if(connUser == null)
		{
			JOptionPane.showMessageDialog(null, "Р СњР ВµРЎвЂљ РЎРѓР С•Р ВµР Т‘Р С‘Р Р…Р ВµР Р…Р С‘РЎРЏ РЎРѓ  mysql (Р вЂ?Р В°Р В·Р В° Р В·Р В°РЎРЏР Р†Р С•Р С” Р С‘ Р Р…Р В°РЎР‚РЎРЏР Т‘Р С•Р Р†)!");
			return true;
		}*/
		if(conn != null && connOffer != null)
		{
			    Statement stmt = null;
			    ResultSet rs = null;
			    try 
			    {
/*			    		CMain.GetMain().timer=new Timer(60000,new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e)
						{
							CData.SelectCurrDate();
						}
					});
					CMain.GetMain().timer.start();
			    	if(CDataOffer.GetRight() == 0)
			    	{
			    		JOptionPane.showMessageDialog(null,"Р С™Р С•РЎРѓРЎРЏР С” Р С”Р В°Р С”Р С•Р в„– - РЎвЂљР С•!!\nРїС—Р…?Р В»Р С‘ Р В»Р С•Р С–Р С‘Р Р… Р С‘Р В»Р С‘ Р С—Р В°РЎР‚Р С•Р В»РЎРЉ Р С‘Р В»Р С‘ Р Р†РЎРѓР Вµ Р Р†Р С?Р ВµРЎРѓРЎвЂљР Вµ Р Р…Р ВµР Р†Р ВµРЎР‚Р Р…Р С•!!!\nР СџРЎР‚Р С•Р С–РЎР‚Р В°Р С?Р С?Р В° Р В±РЎС“Р Т‘Р ВµРЎвЂљ Р В·Р В°Р С”РЎР‚РЎвЂ№РЎвЂљР В°!!!" );
			    		System.exit(0);
			    	}
			    	if(CDataOffer.GetRight() == 1)
			    	{
			    		
			    	}
			    	if(CDataOffer.GetRight() == -1)
			    	{
			    		JOptionPane.showMessageDialog(null,"Р С›РЎв‚¬Р С‘Р В±Р С”Р В° РЎРѓР С•Р ВµР Т‘Р С‘Р Р…Р ВµР Р…Р С‘РЎРЏ РЎРѓ Р В±Р В°Р В·Р С•Р в„– Р Т‘Р В°Р Р…Р Р…РЎвЂ№РЎвЂ¦!!!\nР СџРЎР‚Р С•Р С–РЎР‚Р В°Р С?Р С?Р В° Р В±РЎС“Р Т‘Р ВµРЎвЂљ Р В·Р В°Р С”РЎР‚РЎвЂ№РЎвЂљР В°!!!" );
			    	}*/
 			    } 
			    catch (Exception e) 
			    {
			      e.printStackTrace(System.err);
			      return false;
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
			      catch (SQLException e) 
			      {
			        e.printStackTrace(System.err);
			        return false;
			      }
			    }
			    
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Р СњР ВµРЎвЂљ РЎРѓР С•Р ВµР Т‘Р С‘Р Р…Р ВµР Р…Р С‘РЎРЏ РЎРѓ  BGBilling!!!\nР СџРЎР‚Р С•Р С–РЎР‚Р В°Р С?Р С?Р В° Р В±РЎС“Р Т‘Р ВµРЎвЂљ Р В·Р В°Р С”РЎР‚РЎвЂ№РЎвЂљР В°!!!");
			return false;
		}
		return true;
	}

	/**
	 * This method initializes jPanelUTM5	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelUTM5() {
		if (jPanelUTM5 == null) {
			jLabel12 = new JLabel();
			jLabel12.setBounds(new Rectangle(8, 136, 169, 25));
			jLabel12.setText("Название базы данных:");
			jPanelUTM5 = new JPanel();
			jPanelUTM5.setLayout(null);
			jPanelUTM5.setBounds(new Rectangle(8, 40, 329, 169));
			jPanelUTM5.setBorder(BorderFactory.createLineBorder(Color.red, 1));
			jPanelUTM5.add(jLabel, null);
			jPanelUTM5.add(getJTextFieldLogin(), null);
			jPanelUTM5.add(jLabel1, null);
			jPanelUTM5.add(jLabel2, null);
			jPanelUTM5.add(getJTextFieldServer(), null);
			jPanelUTM5.add(jLabel3, null);
			jPanelUTM5.add(getJTextFieldPort(), null);
			jPanelUTM5.add(getJPasswordFieldPass(), null);
			jPanelUTM5.add(jLabel12, null);
			jPanelUTM5.add(getJTextFieldNameUTM5(), null);
		}
		return jPanelUTM5;
	}

	/**
	 * This method initializes jPasswordFieldPass	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getJPasswordFieldPass() {
		if (jPasswordFieldPass == null) {
			jPasswordFieldPass = new JPasswordField();
			jPasswordFieldPass.setEchoChar('*');
			jPasswordFieldPass.setBounds(new Rectangle(184, 40, 137, 25));
		}
		return jPasswordFieldPass;
	}

	/**
	 * This method initializes jTextFieldNameUTM5	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldNameUTM5() {
		if (jTextFieldNameUTM5 == null) {
			jTextFieldNameUTM5 = new JTextField();
			jTextFieldNameUTM5.setBounds(new Rectangle(184, 136, 137, 25));
		}
		return jTextFieldNameUTM5;
	}

	private void StartMainFrame()
	{
		m_bOk = true;
		//CMain.m_Main.setVisible(true);
		setVisible(false);
		dispose();
		
		DataOutputStream OutStream;

		m_File = new File(CMain.m_stPathConnectCFG);
		
		//  
		if(m_File.exists())
		{
			try
			{
			      OutStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(CMain.m_stPathConnectCFG)));
		    	  OutStream.writeBytes(CMain.m_Encrypter.encrypt("saveparams=1") + "\n");
		    	  OutStream.writeBytes(CMain.m_Encrypter.encrypt("savelogin=0") + "\n");
		    	  OutStream.writeBytes(CMain.m_Encrypter.encrypt("login=" + jTextFieldLogin.getText()) + "\n");
		   	      OutStream.writeBytes(CMain.m_Encrypter.encrypt("pass=" + jPasswordFieldPass.getText()) + "\n");
	    	      OutStream.writeBytes(CMain.m_Encrypter.encrypt("server=" + jTextFieldServer.getText()) + "\n");
	    	      OutStream.writeBytes(CMain.m_Encrypter.encrypt("port=" + jTextFieldPort.getText()) + "\n");
	    	      OutStream.writeBytes(CMain.m_Encrypter.encrypt("name_utm5=" + jTextFieldNameUTM5.getText()) + "\n");
	    	     // OutStream.writeBytes(CMain.m_Encrypter.encrypt("login_offer=" + jTextFieldLoginOffer.getText()) + "\n");
	    	     // OutStream.writeBytes(CMain.m_Encrypter.encrypt("pass_offer=" + jPasswordFieldPassOffer.getText()) + "\n");
	    	     // OutStream.writeBytes(CMain.m_Encrypter.encrypt("server_offer=" + jTextFieldServerOffer.getText()) + "\n");
	    	     // OutStream.writeBytes(CMain.m_Encrypter.encrypt("port_offer=" + jTextFieldPortOffer.getText()) + "\n");
	    	     // OutStream.writeBytes(CMain.m_Encrypter.encrypt("name_utm_offer=" + jTextFieldNameUTMOFFER.getText()) + "\n");
	    	      OutStream.close();
			}
			catch(Exception ex)
			{
				
			}
			
/*			if(StartProgram(jTextFieldServer.getText(),jTextFieldPort.getText(),
					jTextFieldLogin.getText(),jPasswordFieldPass.getText(),jTextFieldNameUTM5.getText(),
					jTextFieldServerOffer.getText(),jTextFieldPortOffer.getText(),
					jTextFieldLoginOffer.getText(),jPasswordFieldPassOffer.getText(),jTextFieldNameUTMOFFER.getText()))
					//"manager","123qqpizdec",jTextFieldNameUTMOFFER.getText()))
					//"kusto","root",jTextFieldNameUTMOFFER.getText()))
			{
				CMain.m_stNameSystemAccount = jTextFieldLoginOffer.getText();
				CMain.GetMain().setTitle(CMain.m_stNameMainFrame + " - Р Р†РЎвЂ¦Р С•Р Т‘ Р С•РЎРѓРЎС“РЎвЂ°Р ВµРЎРѓРЎвЂљР Р†Р В»Р ВµР Р… Р С—Р С•Р В»РЎРЉР В·Р С•Р Р†Р В°РЎвЂљР ВµР В»Р ВµР С? << "
						+ CMain.m_stNameSystemAccount + " >>" + "  Р В¤РїС—Р…?Р С› - " + CMain.m_stNameSystLeader + 
						" | РїС—Р…?Р Т‘Р ВµР Р…РЎвЂљР С‘РЎвЂћР С‘Р С”Р В°РЎвЂљР С•РЎР‚ Р С—Р С•Р В»РЎРЉР В·Р С•Р Р†Р В°РЎвЂљР ВµР В»РЎРЏ = " + CMain.m_iIDSysUserEdit);
				//JOptionPane.showMessageDialog(null,Integer.toString(CMain.m_iRightStatus ));
				if(CMain.m_iRightStatus == -2)// РїС—Р…?РЎРѓР С”Р В»РЎР‹РЎвЂЎР С‘РЎвЂљР ВµР В»РЎРЉР Р…РЎвЂ№Р Вµ Р С—РЎР‚Р В°Р Р†Р В°  Р В°Р Т‘Р С?Р С‘Р Р…Р В°(РЎвЂљ.Р Вµ. Р С?Р ВµР Р…РЎРЏ)))))
				{
					CMain.jTabbedPaneMain.remove(3);
				}
				if(CMain.m_iRightStatus == -1)// Р Р€Р Т‘Р В°Р В»Р С‘Р С? Р Р†РЎРѓР Вµ Р Р†Р С”Р В»Р В°Р Т‘Р С”Р С‘ Р Т‘Р В°Р В±РЎвЂ№ Р Р…Р С‘Р С”РЎвЂљР С• Р Р…Р Вµ Р С‘РЎРѓР С—Р С•РЎР‚РЎвЂљР С‘Р В»!!!
				{
					int i = 0;
					while(CMain.jTabbedPaneMain.getTabCount() != 0)
					{
						CMain.jTabbedPaneMain.remove(i);
					}
				}
				if(CMain.m_iRightStatus == 0)
				{
					CMain.jTabbedPaneMain.remove(3);
					CMain.getJComboBoxTypeOffers().removeAllItems();
					CMain.getJComboBoxTypeOffers().addItem("Р Р…Р В° Р С—Р С•Р Т‘Р С”Р В»РЎР‹РЎвЂЎР ВµР Р…Р С‘Р Вµ");
					CMain.getJComboBoxTypeOffers().addItem("Р Р…Р В° Р С•РЎвЂљР С”Р В»РЎР‹РЎвЂЎР ВµР Р…Р С‘Р Вµ");
					CMain.getJComboBoxTypeOffers().addItem("Р Р…Р В° Р С—Р ВµРЎР‚Р ВµР С—Р С•Р Т‘Р С”Р В»РЎР‹РЎвЂЎР ВµР Р…Р С‘Р Вµ");
					CMain.getJComboBoxTypeOffers().addItem("Р Р…Р В° Р С—Р ВµРЎР‚Р ВµР Р…Р С•РЎРѓ РЎРѓР ВµРЎвЂљР С‘");
				}
				if(CMain.m_iRightStatus == 1)
				{
					CMain.jTabbedPaneMain.remove(0);
					CMain.jTabbedPaneMain.remove(2);
				}
				if(CMain.m_iRightStatus == 2)
				{
					CMain.getJComboBoxTypeOffers().removeAllItems();
					CMain.getJComboBoxTypeOffers().addItem("Р Р…Р В° РЎвЂљР ВµРЎвЂ¦Р Р…Р С‘РЎвЂЎР ВµРЎРѓР С”РЎС“РЎР‹ Р С—Р С•Р Т‘Р Т‘Р ВµРЎР‚Р В¶Р С”РЎС“");
					CMain.jTabbedPaneMain.remove(3);
				}
				if(CMain.m_iRightStatus == 3)// Р СџР С•Р В»РЎРЉР В·Р С•Р Р†Р В°РЎвЂљР ВµР В»РЎРЉ
				{
					CMain.jTabbedPaneMain.remove(0);
					CMain.jTabbedPaneMain.remove(2);
					CMain.jTabbedPaneMain.remove(4);
				}
				if(CMain.m_iRightStatus == 4)// Р СљР С•Р Р…РЎвЂљР В°Р В¶Р Р…Р С‘Р С”
				{
					CMain.jTabbedPaneMain.remove(0);
					CMain.jTabbedPaneMain.remove(2);
					CMain.jTabbedPaneMain.remove(2);
					CMain.jTabbedPaneMain.remove(2);
					CMain.jTabbedPaneMain.remove(2);
				}
				CMain.GetMain().removeTrayIcon();
				CMain.GetMain().addTrayIcon("Р вЂ”Р В°РЎРЏР Р†Р С”Р С‘ Р С‘ Р Р…Р В°РЎР‚РЎРЏР Т‘РЎвЂ№", "Р вЂ™РЎвЂ¦Р С•Р Т‘ Р С•РЎРѓРЎС“РЎвЂ°Р ВµРЎРѓРЎвЂљР Р†Р В»Р ВµР Р…","/resources/tray_report_yes.gif");
			}
			else
			{
				//CMain.m_bIsCloseProgramm = true;
				setVisible(false);
				dispose();
			}*/
		}
	}

	/**
	 * This method initializes jButtonCencel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonCencel() {
		if (jButtonCencel == null) {
			jButtonCencel = new JButton();
			jButtonCencel.setBounds(new Rectangle(224, 216, 113, 25));
			jButtonCencel.setText("Отмена");
			jButtonCencel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()");
					setVisible(false);
					dispose();
					System.exit(0);
				}
			});
		}
		return jButtonCencel;
	}
	public static boolean StartProgram(String stConnectionStringPath,String stPort,String stUser,String stPass,String stUTM5Name)
{
	//
	if(CData.RegisterDriver() == false)
	{
	return false;
	}
	
	
	// Соединение для BGBilling
	CData.stConnectionStringPath = "jdbc:mysql://" + stConnectionStringPath + ":" + stPort + "/" + stUTM5Name;
	CData.stUser = stUser;
	CData.stPass = stPass;
	// Соединение для UTM5_OFFER("ЗАЯВКИ И НАРЯДЫ")
	//String stConnectionStringPathOffer = "jdbc:mysql://" + DataOffer.stConnectionStringPath + ":" + stPort + "/UTM5_OFFER";
	//CDataOffer.stConnectionStringPath = "jdbc:mysql://" + stConnectionStringPathOffer + ":" + stPortOffer + "/" + stUTM5_OFFERName;
	//CDataOffer.stUser = stUserOffer;//"root";//stUser;
	//CDataOffer.stPass = stPassOffer;//"root";//stPass;
	// Соединение для mysql - распознование прав
	Connection conn = null;
	//Connection connOffer = null;
	try
	{
	conn = CData.CreateConnection(CData.stConnectionStringPath, CData.stUser, CData.stPass);
	//connOffer = CDataOffer.CreateConnection(CDataOffer.stConnectionStringPath, CDataOffer.stUser, CDataOffer.stPass);
	}
	catch(Exception ex)
	{
	System.out.println(ex.getMessage());
	return false;
	}
	
	if(conn == null)
	{
	JOptionPane.showMessageDialog(null, "Нет соединения с БД!!!\nПрограмма будет закрыта!!!");
	System.exit(0);
	return false;
	}
/*	if(connOffer == null)
	{
	JOptionPane.showMessageDialog(null, "Нет соединения с  UTM5_OFFER (База заявок и нарядов)!!!\nПрограмма будет закрыта!!!");
	System.exit(0);
	return false;
	}*/
	/*	if(connUser == null)
	{
	JOptionPane.showMessageDialog(null, "Нет соединения с  mysql (База заявок и нарядов)!");
	return true;
	}*/
	if(conn != null/* && connOffer != null*/)
	{
	Statement stmt = null;
	ResultSet rs = null;
	try 
	{
		JOptionPane.showMessageDialog(null,"В базу попали!!!" );
/*			CMain.GetMain().timer=new Timer(60000,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				CData.SelectCurrDate();
			}
		});
		CMain.GetMain().timer.start();
		if(CDataOffer.GetRight() == 0)
		{
			JOptionPane.showMessageDialog(null,"Косяк какой - то!!\nИли логин или пароль или все вместе неверно!!!\nПрограмма будет закрыта!!!" );
			System.exit(0);
		}
		if(CDataOffer.GetRight() == 1)
		{
			
		}
		if(CDataOffer.GetRight() == -1)
		{
			JOptionPane.showMessageDialog(null,"Ошибка соединения с базой данных!!!\nПрограмма будет закрыта!!!" );
		}*/
	 } 
	catch (Exception e) 
	{
	  e.printStackTrace(System.err);
	  return false;
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
	  catch (SQLException e) 
	  {
	    e.printStackTrace(System.err);
	    return false;
	  }
	}
	
	}
	else
	{
	JOptionPane.showMessageDialog(null, "Нет соединения с  BGBilling!!!\nПрограмма будет закрыта!!!");
	return false;
	}
	return true;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
