package theater.com;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;


public class CDialogAddUser extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JTextField jTextFieldNameUser = null;
	private JTextField jTextFieldLogin = null;
	private JPasswordField jPasswordFieldUser = null;
	private JComboBox jComboBoxRuleUser = null;
	private JButton jButtonOk = null;
	private JButton jButtonCansel = null;
	private int m_iSelectedTypeRules = 0;

	/**
	 * @param owner
	 */
	public CDialogAddUser(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(436, 225);
		this.setModal(true);
		this.setTitle("���������� ������������");
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
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(8, 72, 145, 25));
			jLabel3.setText("�����:");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(8, 104, 145, 25));
			jLabel2.setText("������:");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(8, 40, 145, 25));
			jLabel1.setText("�����:");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(8, 8, 145, 25));
			jLabel.setText("������������ ���:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(getJTextFieldNameUser(), null);
			jContentPane.add(getJTextFieldLogin(), null);
			jContentPane.add(getJPasswordFieldUser(), null);
			jContentPane.add(getJComboBoxRuleUser(), null);
			jContentPane.add(getJButtonOk(), null);
			jContentPane.add(getJButtonCansel(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTextFieldNameUser	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldNameUser() {
		if (jTextFieldNameUser == null) {
			jTextFieldNameUser = new JTextField();
			jTextFieldNameUser.setBounds(new Rectangle(160, 8, 249, 25));
		}
		return jTextFieldNameUser;
	}

	/**
	 * This method initializes jTextFieldLogin	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldLogin() {
		if (jTextFieldLogin == null) {
			jTextFieldLogin = new JTextField();
			jTextFieldLogin.setBounds(new Rectangle(160, 72, 249, 25));
		}
		return jTextFieldLogin;
	}

	/**
	 * This method initializes jPasswordFieldUser	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getJPasswordFieldUser() {
		if (jPasswordFieldUser == null) {
			jPasswordFieldUser = new JPasswordField();
			jPasswordFieldUser.setBounds(new Rectangle(160, 104, 249, 25));
		}
		return jPasswordFieldUser;
	}

	/**
	 * This method initializes jComboBoxRuleUser	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxRuleUser() {
		String[] petStrings = { "�������������", "������"};
		if (jComboBoxRuleUser == null) {
			jComboBoxRuleUser = new JComboBox(petStrings);
			jComboBoxRuleUser.setBounds(new Rectangle(160, 40, 249, 25));
			jComboBoxRuleUser.addActionListener(new java.awt.event.ActionListener() 
			{
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{

						if((String)jComboBoxRuleUser.getSelectedItem() == "�������������")
						{
							m_iSelectedTypeRules = 0;
						}
						if((String)jComboBoxRuleUser.getSelectedItem() == "������")
						{
							m_iSelectedTypeRules = 1;
						}
				}
			});
		}
		return jComboBoxRuleUser;
	}

	/**
	 * This method initializes jButtonOk	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonOk() {
		if (jButtonOk == null) {
			jButtonOk = new JButton();
			jButtonOk.setBounds(new Rectangle(200, 152, 97, 25));
			jButtonOk.setText("��");
			jButtonOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(CData.CreateUser(jTextFieldLogin.getText(),
							jPasswordFieldUser.getText(),
							jTextFieldNameUser.getText(),
							Integer.toString(m_iSelectedTypeRules)))
					{
						JOptionPane.showMessageDialog(null, "������������ ������� ������!");
						CData.CreateTableUsers();
						setVisible(false);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "������ �������� ������������!");
					}
				}
			});
		}
		return jButtonOk;
	}

	/**
	 * This method initializes jButtonCansel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonCansel() {
		if (jButtonCansel == null) {
			jButtonCansel = new JButton();
			jButtonCansel.setBounds(new Rectangle(304, 152, 105, 25));
			jButtonCansel.setText("������");
			jButtonCansel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setVisible(false);
					dispose();
				}
			});
		}
		return jButtonCansel;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
