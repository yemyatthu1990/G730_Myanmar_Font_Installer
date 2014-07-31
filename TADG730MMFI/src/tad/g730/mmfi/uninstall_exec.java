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

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import android.os.Handler;
import android.view.View;

public class uninstall_exec extends exec_onClickListener {
	public uninstall_exec(main_activity paramMainActivity) {
		super(paramMainActivity);
	}

	private byte ifSuccess;

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
										centreTipText.setText(R.string.uninstalling);
										YoYo.with(Techniques.FadeIn)
								        .duration(700)
								        .playOn(centreTipText);		
									}
								}, 700);
								
							}
						});
						Thread.sleep(1600);
						// prepare command
						String command = prepare_files_command() + "reboot";

						// start uninstalling
						// exec(String command) is in super class named
						// exec_onClickListener
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
	 * but codes in these method are different
	 */
	@Override
	public void fail() {
		this.stateText.setText(R.string.status_uninstallation_fail);
		this.funcBtn.setText(R.string.retry);
		this.stop_progress_scan();
	}

	
	//preparing for uninstallation
	String prepare_files_command(){
		//preparing command
		String command =
		// remount
		"mount -o rw,remount /system\n"
				+

				// framework.odex
				"rm /system/framework/framework.odex\n"
				+ "mv /system/framework/framework.odex.bak /system/framework/framework.odex\n"
				+ "chmod 644 /system/framework/framework.odex\n"
				+

				// Roboto-Regular.ttf
				"rm /system/fonts/Roboto-Regular.ttf"
				+ "mv /system/fonts/Roboto-Regular.ttf.bak /system/fonts/Roboto-Regular.ttf\n"
				+ "chmod 644 /system/fonts/Roboto-Regular.ttf\n"+
				
				//unmount system
				"mount -o ro,remount /system\n";
		return command;
	}

}
