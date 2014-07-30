package tad.g730.mmfi.utils;
/*used to check if already installed
 * */
import java.io.File;

public class check_state {
	public static boolean checkInstalled(){
		File fdexbak=new File("/system/framework/framework.odex.bak");
		File robotobak=new File("/system/fonts/Roboto-Regular.ttf.bak");
		if(fdexbak.exists() && robotobak.exists())
		return true;
		return false;
	}

}
