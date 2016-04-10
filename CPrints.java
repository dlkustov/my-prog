package theater.com;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Date;
//import java.sql.ResultSet;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

//import javax.swing.JPanel;

//import sun.security.action.GetBooleanAction;

//import com.mysql.jdbc.Field;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JRField;
//import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
//import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


public class CPrints 
{
	static public Map parameters = null;
	private JasperDesign  jasperDesign = null;
	
	private URL url = null;  //  @jve:decl-index=0:
	private ClassLoader cl = null;  //  @jve:decl-index=0:
	//private Class<?> m_cClass = null;   //  @jve:decl-index=0:
	
	public CPrints()
	{
		//JOptionPane.showMessageDialog(null, "Косяк загрузки класса");
		/*CJarClassLoader jarClassLoader = new CJarClassLoader("lib/jasperreports-3.5.2.jar", "JasperDesign");
		// Загружаем класс
		try 
		{
			System.out.println("Start Load - m_cpPrints...");
			// Тестовая загрузка класса из jar - файла!!! 
			m_cClass = jarClassLoader.loadClass("net.sf.jasperreports.engine.design.JasperDesign");
			jasperDesign = (JasperDesign)m_cClass.newInstance();
			//sample.PrintMess("Наконец то я загружен!!!");
			//CSettings.PrintMess2("");
			System.out.println("End Load - m_cpPrints...");
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}*/
	}
	/*public void PrintOffers(String stTamplate,COffersInfo OffersInfo)
	{
		try
		{
		  // Загружаем дизайн отчета
        jasperDesign = JRXmlLoader.load(stTamplate);
        // Компилируем отчет
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        // Создаем карту параметров
        Map parameters = new HashMap();
        //JRField[] Fields = jasperReport.getFields();
        //String stName = Fields[0].getName();
        parameters.put("paramSecondName", OffersInfo.m_stSecond_name);
        parameters.put("paramName", OffersInfo.m_stName);
        parameters.put("paramName3", OffersInfo.m_stName3);
        parameters.put("paramLocAddress", OffersInfo.m_stLocationAddress);
        parameters.put("paramPassportSerial", OffersInfo.m_stPassportSerial);
        parameters.put("paramPassportNumber", OffersInfo.m_stPassportNumber);
        parameters.put("paramPassportGive", OffersInfo.m_stPassportGive);
        parameters.put("paramPassportDateOfGrant", OffersInfo.m_stDateOfGrant);
        parameters.put("paramPhoneCell", OffersInfo.m_stCellPhone);
        parameters.put("paramPhoneHome", OffersInfo.m_stHomePhone);
        parameters.put("paramAddressRef", OffersInfo.m_stReferenceAddress);
     	parameters.put("paramTypeOffers", "Заявка " + OffersInfo.m_stTypeOffers);
     	parameters.put("paramEntrance", OffersInfo.m_stEntrance);
     	parameters.put("paramFloor", OffersInfo.m_stFloor);
     	
     	System.out.println(stTamplate);
     	
     	if(stTamplate.equals("reports/offers_shutdown.jrxml"))
     	{
     		parameters.put("paramNumber", OffersInfo.m_stNumberClient);
//     	 Заполняем отчет. Т. к. н используем БД указываем ему пустой источник
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            // Экспорт в html
            //JasperExportManager.exportReportToHtmlFile(jasperPrint, "report.html");
            // Показываем что получилось в JasperViewer
            
            JasperViewer.viewReport(jasperPrint,false);
     	}
     	if(stTamplate.equals("reports/offers.jrxml"))
     	{
//        	 Заполняем отчет. Т. к. н используем БД указываем ему пустой источник
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            // Экспорт в html
            //JasperExportManager.exportReportToHtmlFile(jasperPrint, "report.html");
            // Показываем что получилось в JasperViewer
            JasperViewer.viewReport(jasperPrint,false);
           JasperViewer viewer = new JasperViewer(jasperPrint, false); 
           CMain.getJPanelLeft().add(viewer);
     	}
     	if(stTamplate.equals("reports/offers_tech_support.jrxml"))
     	{
//        	 Заполняем отчет. Т. к. н используем БД указываем ему пустой источник
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            // Экспорт в html
            //JasperExportManager.exportReportToHtmlFile(jasperPrint, "report.html");
            // Показываем что получилось в JasperViewer
            JasperViewer.viewReport(jasperPrint,false);
     	}
     	if(stTamplate.equals("reports/transfer.jrxml"))
     	{
     		Map parameterstransfer = new HashMap();
            //JRField[] Fields = jasperReport.getFields();
            //String stName = Fields[0].getName();
     		
     		parameterstransfer.put("paramNumberClient", OffersInfo.m_stNumberClient);
     		
     		parameterstransfer.put("paramSecondName", OffersInfo.m_stSecond_name);
     		parameterstransfer.put("paramName", OffersInfo.m_stName);
     		parameterstransfer.put("paramName3", OffersInfo.m_stName3);
          	parameterstransfer.put("paramStreetFrom", OffersInfo.m_stReferenceAddress);
     		parameterstransfer.put("paramPhoneFrom", "Дом.-" + OffersInfo.m_stHomePhone + ",Сот.-" + OffersInfo.m_stCellPhone);
     		parameterstransfer.put("paramHomeFrom", OffersInfo.m_stEntrance);
     		parameterstransfer.put("paramKvartiraFrom", OffersInfo.m_stFloor);
     		parameterstransfer.put("paramStreetTo", OffersInfo.m_stReferenceAddressToo);
     		parameterstransfer.put("paramHomeTo", OffersInfo.m_stEntranceToo);
     		parameterstransfer.put("paramKvartiraTo", OffersInfo.m_stFloorToo);
     		parameterstransfer.put("paramPhoneTo", OffersInfo.m_stPhoneContact);
//        	 Заполняем отчет. Т. к. н используем БД указываем ему пустой источник
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterstransfer, new JREmptyDataSource());
            JasperViewer.viewReport(jasperPrint,false);
     	}
     	
        
        
		}
		catch(JRException ex) 
		{
			ex.fillInStackTrace();
		}
	}*/
	
	/*static public void PrintOrders(String stTamplate,String stType,String stNumberClients,
			String stEntrance, String stFloor, String stComment)
	{
		try
		{
			COffersInfo OffersInfo = CDataOffer.GetOfferInfo(stNumberClients);
			
			  // Загружаем дизайн отчета
	        JasperDesign  jasperDesign = JRXmlLoader.load(stTamplate);
	        //JasperDesign jasperDesign = JRXmlLoader.load(CMain.class.getResourceAsStream(stTamplate));
	        //JasperDesign jasperDesign = JRXmlLoader.load(getResourceAsStream(stTamplate));
	        // Компилируем отчет
	        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
	       

	        
	        
	        // Создаем карту параметров
	        Map parameters = new HashMap();
	        
	        parameters.put("paramComment", stComment);
	        
	        parameters.put("paramTypeOrders", stType);
	        parameters.put("paramOffersNumber", stNumberClients);
	        parameters.put("paramDateOfOffers", OffersInfo.m_stDateCreateOffers);
	        
	        parameters.put("paramOffersNumber2", stNumberClients);
	        parameters.put("paramDateOfOffers2", OffersInfo.m_stDateCreateOffers);
	        
	        parameters.put("paramSecondName", OffersInfo.m_stSecond_name);
	        parameters.put("paramName", OffersInfo.m_stName);
	        parameters.put("paramName3", OffersInfo.m_stName3);
	        parameters.put("paramPhone", "Тел. " + OffersInfo.m_stHomePhone);
	        if(stType.equals("на перенос сети"))
	        {
	        	parameters.put("paramAddress", OffersInfo.m_stReferenceAddressToo);
	        	parameters.put("paramEntrance", OffersInfo.m_stEntranceToo);
	 	        parameters.put("paramFloor", OffersInfo.m_stFloorToo);
	 	        parameters.put("paramPhone", OffersInfo.m_stPhoneContact);
	        }
	        else
	        {
	        	parameters.put("paramAddress", OffersInfo.m_stReferenceAddress);
	        	parameters.put("paramEntrance", stEntrance);
	 	        parameters.put("paramFloor", stFloor);
	        }
	        
	        parameters.put("paramCellPhone", "Тел. " + OffersInfo.m_stCellPhone );
	        parameters.put("paramNumberContract", OffersInfo.m_stNumberClient);
	        parameters.put("paramNumberContract2", OffersInfo.m_stNumberClient);
	        
	        parameters.put("paramNumberOrders", OffersInfo.m_stNumberOrder);
	        // Вносим значения свича, порта и длину кабеля!!!
	        CStructSwitchPortCabel StructSwitchPortCabel = CDataOffer.GetSwitchPortCabelByOfferIdToOrders(stNumberClients);
	        
	        if(StructSwitchPortCabel.m_stSwitch.equals(""))
	        {
	        	parameters.put("paramSwich", "нет");
	        }
	        else
	        {
	        	parameters.put("paramSwich", StructSwitchPortCabel.m_stSwitch);
	        }
	        
	        if(StructSwitchPortCabel.m_stPort.equals(""))
	        {
	        	parameters.put("paramPort", "нет");
	        }
	        else
	        {
	        	parameters.put("paramPort", StructSwitchPortCabel.m_stPort);
	        }
	        if(StructSwitchPortCabel.m_stCabel.equals(""))
	        {
	        	parameters.put("paramCabel", "нет");
	        }
	        else
	        {
	        	parameters.put("paramCabel", StructSwitchPortCabel.m_stCabel);
	        }
	        
	        parameters.put("paramPort", StructSwitchPortCabel.m_stPort);
	        parameters.put("paramCabel", StructSwitchPortCabel.m_stCabel);
	        
	       
	        
	        // Заполняем отчет. Т. к. н используем БД указываем ему пустой источник
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
	        // Экспорт в html
	        //JasperExportManager.exportReportToHtmlFile(jasperPrint, "report.html");
	        // Показываем что получилось в JasperViewer
	        JasperViewer.viewReport(jasperPrint,false);
		}
		catch(JRException ex) 
		{
			JOptionPane.showMessageDialog(null, ex.getStackTrace());
		}
	}
	
	static public void PrintAccounts(String stTamplate)
	{
		if(CDataAccounts.m_AccountsInfoResault == null)
		{
			JOptionPane.showMessageDialog(null, "Выбирите пожалуйста услугу для формирования счета!");
			return;
		}
		
		try
		{
//			 Данные организации поставщика - структура!!!
			CSupplierInfo SupplierInfo = null;
//			 Заполнение данных о банке - структура!!!
			CBankInfo BankInfo = null;
			SupplierInfo = CData.GetSupplierInfo();
			
			if(SupplierInfo != null)
			{
				BankInfo = CData.GetBankInfo(SupplierInfo.m_iBankID);
			}
			//BankInfo = CData.GetBankInfo(SupplierInfo.m_iBankID);
			  // Загружаем дизайн отчета
	        JasperDesign  jasperDesign = JRXmlLoader.load(stTamplate);
	        // Компилируем отчет
	        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
	        // Создаем карту параметров
	        parameters = new HashMap();

	        // Данные организации поставщика
	        parameters.put("paramNameCompany", SupplierInfo.m_stName);
	        parameters.put("paramUrAddress", SupplierInfo.m_stUrAdress);
	        parameters.put("paramINN", SupplierInfo.m_stINN);
	        parameters.put("paramKPP", SupplierInfo.m_stKPP);
	        parameters.put("paramCompanyName", SupplierInfo.m_stName);
	        parameters.put("paramCompanyNumber", SupplierInfo.m_stAccount);
	        
	        // Заполнение данных о банке
	        parameters.put("paramBankName", BankInfo.m_stName);
	        parameters.put("paramBankBIK", BankInfo.m_stBIC);
	        parameters.put("paramBankNumber", BankInfo.m_stKSchet);
	        
	        // Формирование данных в счете об услуге  - самый важный этап!!!
	        // Вообщем началось!!!
	        
	        CAccountsInfo AccountsInfo = CData.GetAllInfoByAccounts(CDataAccounts.m_AccountsInfoResault);
	        // Заносим Номер счета
	        parameters.put("paramNumber", Integer.toString(AccountsInfo.m_iIDService));
	        
	        //Перевод UNIX TIME в нормальный формат!!!!!!!!!!!!!!!!
	    	  String stTimeDateUNIX =AccountsInfo.m_stInvoiceDate;
	    	  stTimeDateUNIX = stTimeDateUNIX + "000";
	    	  Date y = new Date(Long.parseLong(stTimeDateUNIX));
	    	  //-----вот  сюда -------,,,,
	    	  String stTestDate = y.toLocaleString();
	    	  // Отделяем время отсавляем только дату!!!
	    	  parameters.put("paramNumberDate", stTestDate.split(" ")[0]);
	    	  
	    	  // Убираем из денег лишние знаки 
	    	  double newDouble = new BigDecimal(AccountsInfo.m_dBalanseOnSet).setScale(2, RoundingMode.UP).doubleValue();
	    	  parameters.put("paramRests", Double.toString(newDouble));
	    	  
	    	  // Устанавливаем номер лицевого счета
	    	  parameters.put("paramClientNumber", Integer.toString(AccountsInfo.m_iAccountID));
	    	  
	    	  // Устанавливаем Ф�?О(Наз. организации) 
	    	  parameters.put("paramPayer", AccountsInfo.m_stFullName);
	       // Прописываем в счете интервал дат выставки счета
	    	  
	    	  parameters.put("paramStartDate", CMain.GetMain().m_stStartDateCreateService);
	    	  parameters.put("paramEndDate", CMain.GetMain().m_stEndDateCreateService);
	    	  
	    	// Здесь непосредственно сами услуги и их стоимость за расч. период
	    	  
	    	  // кол-во записей о тарифах
	    	  //int iCountService = CMain.GetMain().m_alAccountsInfoPrint.size();
	    	 
	    	  
	        // Последние данные для подписи директора и бухгалтера!
	        parameters.put("paramFOIHeadMan_sh", SupplierInfo.m_stFIOHeadMan_sh);
	        parameters.put("paramFIOBookeeper_sh", SupplierInfo.m_stFIOBookeeper_sh);
	        
	        JRBeanArrayDataSource dataSource = CData.SelectSumNameServiceOnPrint(CDataAccounts.m_AccountsInfoResault.m_iIDService,AccountsInfo.m_iAccountID);
	        
	        СDataInvoice[] reportRows = initializeBeanArray(CMain.GetMain().m_alAccountsInfoPrint);
	        dataSource = new JRBeanArrayDataSource(reportRows);
	        // Заполняем отчет. Т. к. н используем БД указываем ему пустой источник
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	        //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrResData);
	        // Экспорт в html
	        //JasperExportManager.exportReportToHtmlFile(jasperPrint, "report.html");
	        // Показываем что получилось в JasperViewer
	        JasperViewer.viewReport(jasperPrint,false);
		}
		catch(JRException ex) 
		{
			
		}
	}*/
	/* static public СDataInvoice[] initializeBeanArray( ArrayList alList )
	  {
		 	int iCount = alList.size();
		 	СDataInvoice[] reportRows = new СDataInvoice[iCount];
		 
		 	for(int i = 0; i < iCount; i++)
		 	{
		 		
		 		reportRows[i] = new СDataInvoice("N263Y", "T-11", "39 ROSCOE TRNRRACER", "R1830 SERIES");
		 	}
		 СDataInvoice[] reportRows = new СDataInvoice[4];
		    reportRows[0] = new СDataInvoice("N263Y", "T-11", "39 ROSCOE TRNRRACER", "R1830 SERIES");
		    reportRows[1] = new СDataInvoice("N4087X", "BA100-163", "BRADLEYAEROBAT", "R2800 SERIES");
		    reportRows[2] = new СDataInvoice("N43JE", "HAYABUSA 1", "NAKAJIMA KI-43 IIIA", "R1830 SERIES");
		    reportRows[3] = new СDataInvoice("N912S", "9973CC", "PA18-150","R-1820 SER");
		    return reportRows;
	  }*/
}
