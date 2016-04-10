package theater.com;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.JOptionPane;

public class CLoaderJar 
{
	private static final Class[] parameters = new Class[]
	{
		URL.class
	};
	
	public static boolean addFile(String s) throws IOException 
	{
		boolean bRet  = true;
		File f = new File(s);
		
		if(!addFile(f))
		{
			bRet = false;
		}
		return bRet;
	}

	public static boolean addFile(File f) throws IOException 
	{
		return addURL(f.toURL());
	}

	public static boolean addURL(URL u) throws IOException 
	{
		boolean bRet = true;
		URLClassLoader sysloader = (URLClassLoader)ClassLoader.getSystemClassLoader();
		Class sysclass = URLClassLoader.class;
		try 
		{
			java.lang.reflect.Method method = sysclass.getDeclaredMethod("addURL",parameters);
			method.setAccessible(true);
			method.invoke(sysloader,new Object[]{ u });
		}
		catch (Throwable t)
		{
			bRet = false;
			t.printStackTrace();
			throw new IOException("Error, could not add URL to system classloader");
		}
		return bRet;
	}
}
