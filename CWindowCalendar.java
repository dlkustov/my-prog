package theater.com;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;

import com.toedter.calendar.JCalendar;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.sql.Date;
import java.text.SimpleDateFormat;

import sun.util.BuddhistCalendar;
import java.util.*;

public class CWindowCalendar extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JCalendar m_jCalendar = null;

	private JButton jButtonOk = null;

	private JCalendar jCalendar = null;

	/**
	 * @param owner
	 */
	public CWindowCalendar(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(405, 292);
		this.setModal(true);
		m_jCalendar = new JCalendar();
		m_jCalendar.setSize(369, 201);
		m_jCalendar.setCalendar(new GregorianCalendar());

		this.setContentPane(getJContentPane());
		
		
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
			jContentPane.add(m_jCalendar, null);
			jContentPane.add(getJButtonOk(), null);
		}
		return jContentPane;
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
			jButtonOk.setBounds(new Rectangle(272, 208, 100, 29));
			jButtonOk.setText("Выбрать");
			jButtonOk.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					java.util.Date aDate = m_jCalendar.getDate();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.d");
					CMain.m_stDateCreate = sdf.format(aDate);
					SimpleDateFormat sdf2 = new SimpleDateFormat("d.MM.yyyy");
					if(CMain.m_iStartEndDateCurrentSelect == 0)
					{
						CMain.jTextFieldStartDate.setText(sdf2.format(aDate));
					}
					if(CMain.m_iStartEndDateCurrentSelect == 1)
					{
						CMain.jTextFieldEndDate.setText(sdf2.format(aDate));
					}
					
					dispose();
				}
			});
		}
		return jButtonOk;
	}

	/**
	 * This method initializes jCalendar	
	 * 	
	 * @return com.toedter.calendar.JCalendar	
	 */
	private JCalendar getJCalendar()
	{
		if (jCalendar == null) {
			jCalendar = new JCalendar();
			jCalendar.setBounds(new Rectangle(0, 0, 0, 0));
		}
		return jCalendar;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
