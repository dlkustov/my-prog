package theater.com;
// Класс системных пользователей кассиров и админов!!!
public class CUserInfo 
{
	public int m_iID = -1;
	public String m_stLogin;
	public String m_stPass;
	public String m_stName;
	public int m_iRules;
	
	public CUserInfo() {
		m_iID = -1;
		m_stLogin = null;
		m_stPass = null;
		m_stName = null;
		m_iRules = -1;
	}
}
