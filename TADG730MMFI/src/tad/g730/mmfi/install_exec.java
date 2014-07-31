/*
 * Copyright © 2014 TAD
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package tad.g730.mmfi;
//this is where installation starts when the install button is clicked
import java.io.File;

import tad.g730.mmfi.utils.extract_helper;
import android.os.Handler;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class install_exec extends exec_onClickListener {
	private byte ifSuccess;

	public install_exec(main_activity paramMainActivity) {
		super(paramMainActivity);
	}

	@Override
	public void onClick(View v) {

		// start the animation
		start_progress_scan();


		// start a new thread to prevent animation pause
		new Thread() {
			@Override
			public void run() {
				try {
					//busybox() is in the parent class
					if (busybox()) {
						a.runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								YoYo.with(Techniques.FadeOut)
						        .duration(700)
						        .playOn(centreTipText);
								new Handler().postDelayed(new Runnable() {
									
									@Override
									public void run() {
										centreTipText.setText(R.string.installing);
										YoYo.with(Techniques.FadeIn)
								        .duration(700)
								        .playOn(centreTipText);		
									}
								}, 700);
								
							}
						});
						// prepare files and command
						String command = prepare_files_command() + "reboot";

						// start installing
						// exec(String command) is in the parent class
						exec(command);

					} else {
						ifSuccess = 1;
						// requireRoot();
					}
				} catch (Exception ex) {
					ifSuccess = 2;
					// installation fail
				}

				/*
				 * can't manipulate the ui from different thread. so the
				 * original thread that started the animation and ui
				 * manipulation is needed to manipulate the ui again.
				 */

				a.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (ifSuccess == 2)
							fail();
						else if (ifSuccess == 1)
							requireRoot();

					}
				});
			};
		}.start();
		

	}

	

	/*override the abstract method from parent class
	 * It exist in both install_exec and uninstall_exec
	 * but codes in these methods are different
	 */
	@Override
	public void fail() {
		this.stateText.setText(R.string.status_installation_fail);
		this.funcBtn.setText(R.string.retry);
		// stop_progress_scan() is in parent class
		this.stop_progress_scan();
	}
	
	
	public String prepare_files_command() throws Exception {
		//sign framework.odex
		File odexfile=signOdex();
		
		//extract files from assets
		File smartzawgyipro = extract_helper.extractAssets(a,
				"SmartZawgyiPro.ttf");
		File libharf=extract_helper.extractAssets(a, "libharfbuzz.so");
		File fallback=extract_helper.extractAssets(a, "fallback_fonts.xml");
		File sys=extract_helper.extractAssets(a, "system_fonts.xml");
		File browser=extract_helper.extractAssets(a, "MaxthonBrowser.apk");
		String datafolderpath=a.getFilesDir()+"/*";
		//preparing command
		String command =
		// remount
		"mount -o rw,remount /system\n"
				+
				
				/*remove files which may be installed by other tools
				 * 
				it starts from here*/
				
				//vendor/etc/fallback_fonts.xml				 
				"rm /system/vendor/etc/fallback_fonts.xml\n"+
				
				//Padauk and SmartZawgyiPro
				"rm /system/fonts/SmartZawgyiPro.ttf\n"+
				"rm /system/fonts/Padauk.ttf\n"+
				
				//fallback_fonts.xml
				"rm /system/etc/fallback_fonts.xml\n"+
				"rm /system/etc/fallback_fonts.xml.bak\n"+
				String.format("dd if=%s of=/system/etc/fallback_fonts.xml\n", fallback.getAbsolutePath())+
				"chmod 644 /system/etc/fallback_fonts.xml\n"+
				
				//system_fonts.xml
				"rm /system/etc/system_fonts.xml\n"+
				"rm /system/etc/system_fonts.xml.bak\n"+
				String.format("dd if=%s of=/system/etc/system_fonts.xml\n", sys.getAbsolutePath())+
				"chmod 644 /system/etc/system_fonts.xml\n"+
				
				//libharfbuzz.so
				"rm /system/lib/libharfbuzz.so\n"+
				"rm /system/lib/libharfbuzz.so.bak\n"+
				String.format("dd if=%s of=/system/lib/libharfbuzz.so\n", libharf.getAbsolutePath())+
				"chmod 644 /system/lib/libharfbuzz.so\n"+
				
				/*remove files which may be installed by other tools
				 * 
				it ends here*/
				
				/*now preparing for installation command
				 * */
				
				// RobotoRegular.ttf
				"mv /system/fonts/Roboto-Regular.ttf /system/fonts/Roboto-Regular.ttf.bak\n"
				+ String.format("dd if=%s of=/system/fonts/Roboto-Regular.ttf\n",
						smartzawgyipro.getAbsolutePath())
				+ "chmod 644 /system/fonts/Roboto-Regular.ttf\n"
				+

				// framework.odex
				"mv /system/framework/framework.odex /system/framework/framework.odex.bak\n"
				+ String.format("dd if=%s of=/system/framework/framework.odex\n",
						odexfile.getAbsolutePath())
				+ "chmod 644 /system/framework/framework.odex\n" +

				//framework.odex
				"rm /system/app/Browser.apk\n"
				+ "rm /system/app/Browser.odex\n"
				+ String.format("dd if=%s of=/system/app/MaxthonBrowser.apk\n",
				browser.getAbsolutePath())
				+ "chmod 644 /system/app/MaxthonBrowser.apk\n" +
		
				//remove files and unmount
				String.format("rm -R %s\n", datafolderpath)+
				"mount -o ro,remount /system\n";
		return command;

	}

	/*
	// create framework.odex file and sign it
	private File createodex() throws Exception {
		String bootclasspath = get_bootclasspath_value();
		
		File newodex =a.getFileStreamPath("unsigned.odex");
		newodex.delete();
		File installsh= a.getFileStreamPath("install.sh");
		installsh.setReadable(true, false);
		installsh.setWritable(true, false);
		File frameworkjar = extract_helper.extractAssets(a,
				"framework.jar");
		String command = String.format("dexopt-wrapper %s %s %s",
				frameworkjar.getAbsolutePath(), newodex.getAbsolutePath(), bootclasspath);

		PrintWriter pw=new PrintWriter(new FileWriter(installsh));
		pw.append(command);
		pw.close();
		installsh.setExecutable(true, false);
		command = String.format("su -c \"sh %s\"", installsh.getAbsolutePath());
		long_exec(command);
		
		if (!newodex.exists()){
			Log.v("File Not Exist", bootclasspath);
			throw new Exception("can't create new.odex");
		}
		
		command=String.format("busybox dd if=/system/framework/framework.odex of=%s bs=1 count=20 skip=52 seek=52 conv=notrunc\n"+"exit",
				newodex.getAbsolutePath());
		exec(command);
		
		return newodex;
	}
	*/
	
	//sign framework.odex
	private File signOdex() throws Exception {
		File newodex = extract_helper.extractAssets(a, "unsigned.odex");		
		String command=String.format("busybox dd if=/system/framework/framework.odex of=%s bs=1 count=20 skip=52 seek=52 conv=notrunc\n"+"exit",
						newodex.getAbsolutePath());
		exec(command);
		if (!newodex.exists())
			throw new Exception("can't create new.odex");
		return newodex;
	}
	

	/*
	private static String long_exec(String command) {
		try {
			Process process = Runtime.getRuntime().exec(command);
			//process.getOutputStream().write(command.getBytes());
			//process.getOutputStream().close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			int read;
			char[] buffer = new char[4096];
			StringBuffer output = new StringBuffer();
			while ((read = reader.read(buffer)) > 0) {
				output.append(buffer, 0, read);
			}
			reader.close();
			process.waitFor();
			return output.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	*/

	/*
	 * copy init.rc in order to set readable easily
	 * then this method will read and return BOOTCLASSPATH
	 */
	/*
	private String get_bootclasspath_value() throws Exception {
		// declare a file that allocate init.rc in data folder
		File local_initrc = a.getFileStreamPath("init.txt");

		// copy init.rc from system in order setReadable easily
		String command = String.format("dd if=/init.rc of=%s\n",
				local_initrc.getAbsolutePath())
				+ String.format("chmod 666 %s\n",
						local_initrc.getAbsolutePath())+"exit";
		exec(command);

		if (!local_initrc.exists())
			throw new Exception("Can't get init.rc");

		// read BOOTCLASSPATH from init.rc
		BufferedReader br = new BufferedReader(new FileReader(local_initrc));
		 String result=br.readLine();
         String bootclasspathline="";
         while(result!=null){
             result=br.readLine();
             if(result.contains("BOOTCLASSPATH"))
                 bootclasspathline=result;
             if(result.contains("on early_property:ro.build.type=eng")){
                 bootclasspathline=result;
                 while(!bootclasspathline.contains("BOOTCLASSPATH")){
                     bootclasspathline=br.readLine();
                 }
                 break;
             }
         }
		br.close();
		
		
		String parts[] = bootclasspathline.split("BOOTCLASSPATH ");
		/**parts[1] is the BOOTCLASSPATH value get from init.rc
		but sometimes all boot class paths are not exist in system
		so it's needed to make sure all the boot class paths actually exists
		if not those which do not actually exists needed to be removed
		*//*
		String value[] = parts[1].split(":");
		bootclasspathline = "";
		for(int i=0;i<value.length;i++){
			if(check_file(value[i])){
				String addvalue=value[i]+":";
				if(i==(value.length-1))
					addvalue = value[i];
				bootclasspathline+=addvalue;
			}
		}
		return bootclasspathline;
	}
	
	//used to check if file exists
	private boolean check_file(String path){
		File file=new File(path);
		return file.exists();
	} */


}
