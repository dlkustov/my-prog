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
import javax.swing.JButton;

public class CDialogAddGenre extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JTextField jTextFieldNewGenreName = null;
	private JButton jButtonOk = null;

	/**
	 * @param owner
	 */
	public CDialogAddGenre(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(424, 148);
		this.setModal(true);
		this.setTitle("Добавление жанра");
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
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(8, 8, 385, 25));
			jLabel.setText("Введите название жанра");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJTextFieldNewGenreName(), null);
			jContentPane.add(getJButtonOk(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTextFieldNewGenreName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldNewGenreName() {
		if (jTextFieldNewGenreName == null) {
			jTextFieldNewGenreName = new JTextField();
			jTextFieldNewGenreName.setBounds(new Rectangle(8, 40, 385, 25));
		}
		return jTextFieldNewGenreName;
	}

	/**
	 * This method initializes jButtonOk	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonOk() {
		if (jButtonOk == null) {
			jButtonOk = new JButton();
			jButtonOk.setBounds(new Rectangle(240, 72, 153, 25));
			jButtonOk.setText("Применить");
			jButtonOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(CData.CreateGenre(jTextFieldNewGenreName.getText()))
					{
						JOptionPane.showMessageDialog(null, "Жанр успешно создан!");
						CData.CreateTableGenre();
						setVisible(false);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Ошибка создания жанра!");
					}
				}
			});
		}
		return jButtonOk;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
