package theater.com;

import javax.swing.JButton;

// Класс отвечает за каждую кнопку!!! - их много!!!
public class CTheaterPlace extends JButton 
{
	private JButton m_Button = null;
	
	public CTheaterPlace() 
	{
		m_Button = new JButton();
	}
/*	public CTheaterPlace(String stNumber) 
	{
		m_Button = new JButton();
		m_Button.setText(stNumber);
	}*/
}
