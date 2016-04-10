package theater.com;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.Dimension;

public class CDialogEnterText extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTextField jTextFieldEnterText = null;
	private JButton jButtonOk = null;
	//private JButton m_jBtn = null;

	/**
	 * @param owner
	 */
	public CDialogEnterText(Frame owner) {
		super(owner);
		initialize();
	}
	
	public CDialogEnterText(Frame owner, JButton jBtn) {
		super(owner);
		initialize();
		//m_jBtn = jBtn;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(138, 111);
		this.setTitle("Текст");
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
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJTextFieldEnterText(), null);
			jContentPane.add(getJButtonOk(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTextFieldEnterText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldEnterText() {
		if (jTextFieldEnterText == null) {
			jTextFieldEnterText = new JTextField();
			jTextFieldEnterText.setBounds(new Rectangle(8, 8, 105, 25));
		}
		return jTextFieldEnterText;
	}

	/**
	 * This method initializes jButtonOk	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonOk() {
		if (jButtonOk == null) {
			jButtonOk = new JButton();
			jButtonOk.setBounds(new Rectangle(8, 40, 105, 25));
			jButtonOk.setText("Ввести");
			jButtonOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					CMain.jBtnTemp.setText(getJTextFieldEnterText().getText());
					dispose();
				}
			});
		}
		return jButtonOk;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
