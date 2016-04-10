package theater.com;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class CDialogNewEventAdd extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JComboBox jComboBoxSelectPlan = null;
	private JLabel jLabel1 = null;
	private JTextField jTextFieldNameEvent = null;
	private JLabel jLabel2 = null;
	private JComboBox jComboBoxGenre = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JComboBox jComboBoxSelectAge = null;
	private JTextField jTextFieldHours = null;
	private JLabel jLabel5 = null;
	private JTextField jTextFieldMin = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JLabel jLabel8 = null;
	private JTextField jTextFieldCost1 = null;
	private JLabel jLabel9 = null;
	private JTextField jTextFieldCost2 = null;
	private JLabel jLabel10 = null;
	private JTextField jTextFieldCost3 = null;
	private JButton jButtonOk = null;
	private JButton jButtonAddDate = null;
	private JButton jButtonDeleteDate = null;
	private JScrollPane jScrollPaneDate = null;
	private JList jListDate = null;
	private JButton jButtonAddSeans = null;
	private JButton jButtonDeleteSeanse = null;
	private JScrollPane jScrollPaneSeanse = null;
	private JList jListSeanse = null;
	private CWindowCalendar m_WindowCalendar = null;
	/**
	 * @param owner
	 */
	public CDialogNewEventAdd(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(676, 399);
		this.setModal(true);
		this.setTitle("Мероприятие - (Новое)");
		this.setLocationRelativeTo(null);
		
		this.setContentPane(getJContentPane());
	}


	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(248, 296, 33, 25));
			jLabel10.setBackground(Color.orange);
			jLabel10.setOpaque(true);
			jLabel10.setText("");
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(128, 296, 33, 25));
			jLabel9.setBackground(Color.green);
			jLabel9.setOpaque(true);
			jLabel9.setText("");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(8, 296, 33, 25));
			jLabel8.setBackground(Color.BLUE);
			jLabel8.setOpaque(true);
			jLabel8.setText("");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(8, 264, 209, 25));
			jLabel7.setText("Стоимость билетов:");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(136, 200, 33, 25));
			jLabel6.setText("Мин");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(48, 200, 33, 25));
			jLabel5.setText("Ч");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(184, 168, 177, 25));
			jLabel4.setText("Возрастные ограничения");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(8, 168, 145, 25));
			jLabel3.setText("Продолжительность");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(8, 104, 353, 25));
			jLabel2.setText("Жанр");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(8, 40, 353, 25));
			jLabel1.setText("Мероприятие");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(8, 8, 129, 25));
			jLabel.setText("Зрительный зал");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJComboBoxSelectPlan(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJTextFieldNameEvent(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getJComboBoxGenre(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(getJComboBoxSelectAge(), null);
			jContentPane.add(getJTextFieldHours(), null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(getJTextFieldMin(), null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(jLabel7, null);
			jContentPane.add(jLabel8, null);
			jContentPane.add(getJTextFieldCost1(), null);
			jContentPane.add(jLabel9, null);
			jContentPane.add(getJTextFieldCost2(), null);
			jContentPane.add(jLabel10, null);
			jContentPane.add(getJTextFieldCost3(), null);
			jContentPane.add(getJButtonOk(), null);
			jContentPane.add(getJButtonAddDate(), null);
			jContentPane.add(getJButtonDeleteDate(), null);
			jContentPane.add(getJScrollPaneDate(), null);
			jContentPane.add(getJButtonAddSeans(), null);
			jContentPane.add(getJButtonDeleteSeanse(), null);
			jContentPane.add(getJScrollPaneSeanse(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jComboBoxSelectPlan	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxSelectPlan() {
		String[] petStrings = { 
				"Главный зал(Детский)", 
				"Главный зал(Обычный)",
				"Главный зал(Премьера)"};
		if (jComboBoxSelectPlan == null) {
			jComboBoxSelectPlan = new JComboBox(petStrings);
			jComboBoxSelectPlan.setBounds(new Rectangle(144, 8, 249, 25));
		}
		return jComboBoxSelectPlan;
	}

	/**
	 * This method initializes jTextFieldNameEvent	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldNameEvent() {
		if (jTextFieldNameEvent == null) {
			jTextFieldNameEvent = new JTextField();
			jTextFieldNameEvent.setBounds(new Rectangle(8, 72, 353, 25));
		}
		return jTextFieldNameEvent;
	}

	/**
	 * This method initializes jComboBoxGenre	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxGenre() {
		ArrayList alGenreInfoAll = CData.GetGenreInfoAll();
		String[] stGenreName = new String[alGenreInfoAll.size()];
		for(int i = 0; i < alGenreInfoAll.size(); i++)
		{
			stGenreName[i] = ((CGenreInfo)alGenreInfoAll.get(i)).m_stName;
		}
		if (jComboBoxGenre == null) {
			jComboBoxGenre = new JComboBox(stGenreName);
			jComboBoxGenre.setBounds(new Rectangle(8, 136, 353, 25));
		}
		return jComboBoxGenre;
	}

	/**
	 * This method initializes jComboBoxSelectAge	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxSelectAge() {
		String[] petStrings = 
		{ 
				"0",
				"3+",
				"4+",
				"6+",
				"12+",
				"14+",
				"16+",
				"18+"
				};
		if (jComboBoxSelectAge == null) {
			jComboBoxSelectAge = new JComboBox(petStrings);
			jComboBoxSelectAge.setBounds(new Rectangle(184, 200, 177, 25));
		}
		return jComboBoxSelectAge;
	}

	/**
	 * This method initializes jTextFieldHours	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldHours() {
		if (jTextFieldHours == null) {
			jTextFieldHours = new JTextField();
			jTextFieldHours.setBounds(new Rectangle(8, 200, 33, 25));
		}
		return jTextFieldHours;
	}

	/**
	 * This method initializes jTextFieldMin	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldMin() {
		if (jTextFieldMin == null) {
			jTextFieldMin = new JTextField();
			jTextFieldMin.setBounds(new Rectangle(88, 200, 41, 25));
		}
		return jTextFieldMin;
	}

	/**
	 * This method initializes jTextFieldCost1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldCost1() {
		if (jTextFieldCost1 == null) {
			jTextFieldCost1 = new JTextField();
			jTextFieldCost1.setBounds(new Rectangle(45, 296, 76, 25));
		}
		return jTextFieldCost1;
	}

	/**
	 * This method initializes jTextFieldCost2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldCost2() {
		if (jTextFieldCost2 == null) {
			jTextFieldCost2 = new JTextField();
			jTextFieldCost2.setBounds(new Rectangle(168, 296, 73, 25));
		}
		return jTextFieldCost2;
	}

	/**
	 * This method initializes jTextFieldCost3	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldCost3() {
		if (jTextFieldCost3 == null) {
			jTextFieldCost3 = new JTextField();
			jTextFieldCost3.setBounds(new Rectangle(288, 296, 73, 25));
		}
		return jTextFieldCost3;
	}

	/**
	 * This method initializes jButtonOk	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonOk() {
		if (jButtonOk == null) {
			jButtonOk = new JButton();
			jButtonOk.setBounds(new Rectangle(480, 328, 169, 25));
			jButtonOk.setText("Создать");
		}
		return jButtonOk;
	}

	/**
	 * This method initializes jButtonAddDate	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonAddDate() {
		if (jButtonAddDate == null) {
			jButtonAddDate = new JButton();
			jButtonAddDate.setBounds(new Rectangle(480, 8, 169, 17));
			jButtonAddDate.setText("Добавить дату показа");
			jButtonAddDate.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					m_WindowCalendar = new CWindowCalendar(null);
					m_WindowCalendar.setVisible(true);
				}
			});
		}
		return jButtonAddDate;
	}

	/**
	 * This method initializes jButtonDeleteDate	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonDeleteDate() {
		if (jButtonDeleteDate == null) {
			jButtonDeleteDate = new JButton();
			jButtonDeleteDate.setBounds(new Rectangle(480, 32, 169, 17));
			jButtonDeleteDate.setText("Удалить дату показа");
		}
		return jButtonDeleteDate;
	}

	/**
	 * This method initializes jScrollPaneDate	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPaneDate() {
		if (jScrollPaneDate == null) {
			jScrollPaneDate = new JScrollPane();
			jScrollPaneDate.setBounds(new Rectangle(480, 56, 169, 89));
			jScrollPaneDate.setViewportView(getJListDate());
		}
		return jScrollPaneDate;
	}

	/**
	 * This method initializes jListDate	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJListDate() {
		if (jListDate == null) {
			jListDate = new JList();
		}
		return jListDate;
	}

	/**
	 * This method initializes jButtonAddSeans	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonAddSeans() {
		if (jButtonAddSeans == null) {
			jButtonAddSeans = new JButton();
			jButtonAddSeans.setBounds(new Rectangle(480, 152, 169, 17));
			jButtonAddSeans.setText("Добавить сеанс");
		}
		return jButtonAddSeans;
	}

	/**
	 * This method initializes jButtonDeleteSeanse	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonDeleteSeanse() {
		if (jButtonDeleteSeanse == null) {
			jButtonDeleteSeanse = new JButton();
			jButtonDeleteSeanse.setBounds(new Rectangle(480, 176, 169, 17));
			jButtonDeleteSeanse.setText("Удалить сеанс");
		}
		return jButtonDeleteSeanse;
	}

	/**
	 * This method initializes jScrollPaneSeanse	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPaneSeanse() {
		if (jScrollPaneSeanse == null) {
			jScrollPaneSeanse = new JScrollPane();
			jScrollPaneSeanse.setBounds(new Rectangle(480, 200, 169, 121));
			jScrollPaneSeanse.setViewportView(getJListSeanse());
		}
		return jScrollPaneSeanse;
	}

	/**
	 * This method initializes jListSeanse	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJListSeanse() {
		if (jListSeanse == null) {
			jListSeanse = new JList();
		}
		return jListSeanse;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
