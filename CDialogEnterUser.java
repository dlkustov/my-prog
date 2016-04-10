package theater.com;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.Rectangle;
import java.sql.Connection;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JButton;






public class CDialogEnterUser extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTextField jTextFieldLogin = null;
	private JPasswordField jPasswordField = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JComboBox jComboBox = null;
	private JButton jButtonOk = null;
	private JButton jButtonNo = null;
	/**
	 * @param owner
	 */
	public CDialogEnterUser(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(260, 263);
		this.setTitle("Вход в программу");
		this.setContentPane(getJContentPane());
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(8, 120, 225, 25));
			jLabel1.setText("Введите пароль:");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(8, 56, 225, 25));
			jLabel.setText("Введите логин:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJTextFieldLogin(), null);
			jContentPane.add(getJPasswordField(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJComboBox(), null);
			jContentPane.add(getJButtonOk(), null);
			jContentPane.add(getJButtonNo(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTextFieldLogin	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldLogin() {
		if (jTextFieldLogin == null) {
			jTextFieldLogin = new JTextField();
			jTextFieldLogin.setBounds(new Rectangle(8, 88, 225, 25));
			jTextFieldLogin.setText("111");
		}
		return jTextFieldLogin;
	}

	/**
	 * This method initializes jPasswordField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getJPasswordField() {
		if (jPasswordField == null) {
			jPasswordField = new JPasswordField();
			jPasswordField.setBounds(new Rectangle(8, 152, 225, 25));
			jPasswordField.setText("222");

		}
		return jPasswordField;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		String[] petStrings = { "Администратор", "Кассир"};
		if (jComboBox == null) {
			jComboBox = new JComboBox(petStrings);
			jComboBox.setBounds(new Rectangle(8, 16, 225, 25));
			jComboBox.disable();
		}
		return jComboBox;
	}

	/**
	 * This method initializes jButtonOk	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonOk() {
		if (jButtonOk == null) {
			jButtonOk = new JButton();
			jButtonOk.setBounds(new Rectangle(8, 184, 113, 25));
			jButtonOk.setText("Войти");
			jButtonOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					CMain.m_UserInfo = CData.GetUserInfo(getJTextFieldLogin().getText(), getJPasswordField().getText());
					try 
					{
						if(CMain.m_UserInfo.m_iRules == 0)
						{
							CMain.m_iRulesType = CMain.m_UserInfo.m_iRules;
							CMain.m_stTitle = "Вход пользователя (Администратор) - " + CMain.m_UserInfo.m_stName;
							CMain.m_Main.setTitle(CMain.m_stTitle);
						}
						else
						{
							CMain.m_iRulesType = CMain.m_UserInfo.m_iRules;
							CMain.m_stTitle = "Вход пользователя (Кассир) - " + CMain.m_UserInfo.m_stName;
							CMain.m_Main.setTitle(CMain.m_stTitle);
						}
						CData.CreateTableUsers();
						CData.CreateTableParam();
						CData.CreateTableGenre();
						CData.CreateTableSeanse();
						
					} 
					catch (Exception e2)
					{
						// TODO: handle exception
					}
					
					if(CMain.m_UserInfo != null)
					{
						if(CMain.m_iRulesType == 0)
						{
							System.out.println("Значит Админ!!!");	
						}
						if(CMain.m_iRulesType == 1)
						{
							System.out.println("Значит Кассир!!!");	
						}
						//CMain.m_Main.setTitle("Театр, вход пользователя: " + CMain.m_UserInfo.m_stName);
						setVisible(false);
						dispose();
					}
					else
					{
						getJTextFieldLogin().setText("");
						getJPasswordField().setText("");
						JOptionPane.showMessageDialog(null, "Не правильный логин или пароль!");
					}
						
				}
			});
			this.addWindowListener(new java.awt.event.WindowAdapter() 
			{   
				public void windowClosing(java.awt.event.WindowEvent e)
				{
					setVisible(false);
					dispose();
					System.exit(0);
				}
			});
		}
		return jButtonOk;
	}

	/**
	 * This method initializes jButtonNo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonNo() {
		if (jButtonNo == null) {
			jButtonNo = new JButton();
			jButtonNo.setBounds(new Rectangle(136, 184, 97, 25));
			jButtonNo.setText("Отмена");
			jButtonNo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); 
					setVisible(false);
					dispose();
					System.exit(0);
				}
			});
		}
		return jButtonNo;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
