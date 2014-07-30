package tad.g730.mmfi;
/*install_exec and uninstall_exec are the child class of this one.
 * exec_onClickListener, itself, is the child class of better_onClickListener.
*/
import java.io.File;
import java.io.IOException;

import tad.g730.mmfi.utils.extract_helper;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public abstract class exec_onClickListener extends better_onClickListerner{
	private ImageView ProgressBar;
	private Animation animation;
	protected Button funcBtn;
	protected TextView centreTipText,stateText,txtDeviceModel;
	public exec_onClickListener(main_activity paramMainActivity) {
		super(paramMainActivity);
		ProgressBar=(ImageView)a.findViewById(R.id.scan_progress_bar);
		stateText=(TextView)a.findViewById(R.id.state_text);
		funcBtn=(Button)a.findViewById(R.id.function_btn);
		centreTipText=(TextView)a.findViewById(R.id.centre_tip_txt);
		txtDeviceModel=(TextView)a.findViewById(R.id.device_model);		
	}
	
	
	protected abstract void fail();
	
	//ui state to requireroot
	protected void requireRoot() {
		this.stateText.setText(R.string.status_require_root);
		this.funcBtn.setText(R.string.retry);
		this.stop_progress_scan();		
	}

	/*
	 * run progress scan
	 *This method was reverse engineered from baidu root app
	 */
	protected void start_progress_scan(){
		YoYo.with(Techniques.SlideOutRight)
        .duration(700)
        .playOn(this.funcBtn);
		YoYo.with(Techniques.FadeOut)
        .duration(700)
        .playOn(stateText);	
		YoYo.with(Techniques.SlideOutLeft)
        .duration(700)
        .playOn(txtDeviceModel);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				funcBtn.setVisibility(8);
				stateText.setVisibility(8);
				txtDeviceModel.setVisibility(8);
				centreTipText.setVisibility(0);
				centreTipText.setText(R.string.checking_root_access);
				YoYo.with(Techniques.FadeIn)
		        .duration(700)
		        .playOn(centreTipText);
				
			}
		},700);
			
		
		int i2=a.getWallpaperDesiredMinimumWidth()/4;
		int i1=(a.getWallpaperDesiredMinimumWidth()/4)*3;
		this.ProgressBar.setVisibility(0);
	    
	    if (this.animation == null)
	      this.animation = new TranslateAnimation(0.0F, 0.0F, -i2, i1+i2);
	    this.animation.setDuration(1200L);
	    this.animation.setRepeatCount(-1);
	    this.animation.setRepeatMode(1);
	    this.ProgressBar.setAnimation(this.animation);
	    this.animation.startNow(); 		
	}

	//stop progress scan
	protected void stop_progress_scan(){
		YoYo.with(Techniques.FadeOut)
        .duration(700)
        .playOn(centreTipText);
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				centreTipText.setVisibility(8);
				funcBtn.setVisibility(0);
				stateText.setVisibility(0);
				txtDeviceModel.setVisibility(0);				
				YoYo.with(Techniques.SlideInRight)
		        .duration(700)
		        .playOn(funcBtn);
				YoYo.with(Techniques.FadeIn)
		        .duration(700)
		        .playOn(stateText);	
				YoYo.with(Techniques.SlideInLeft)
		        .duration(700)
		        .playOn(txtDeviceModel);
				
			}
		},700);
		this.animation.cancel();
		this.ProgressBar.setVisibility(8);
	}
	
	//used to execute
	protected void exec(String command) throws IOException, InterruptedException{
		Process p;
		p = Runtime.getRuntime().exec("su");
		p.getOutputStream().write(command.getBytes());
		p.getOutputStream().close();
		p.waitFor();
	}
	
	//used to push busybox to system
	protected boolean busybox(){
		File local_busybox;
		try {
			Thread.sleep(1600);
			local_busybox = extract_helper.extractAssets(a, "busybox");
			File system_busybox = new File("/system/xbin/busybox");
			File immutable = new File("/system/set_immutable.list");
			File local_restore=extract_helper.extractAssets(a, "restore");
			File system_restore=new File("/system/xbin/restore");
			local_busybox.setExecutable(true);
			String command = String.format("%s mount -o rw,remount /system\n",
					local_busybox.getAbsolutePath())
					+

					/*
					 * System will stay remained ro if immutable.list exist So it
					 * need to be removed to mount the system
					 */
					String.format("%s chattr -R -i /system/*\n",
							local_busybox.getAbsolutePath())
					+ String.format("rm %s\n", immutable.getAbsolutePath())
					+ "mkdir /system/xbin\n"
					+ String.format("rm %s\n", system_busybox.getAbsolutePath())
					+ String.format("rm %s\n", system_restore.getAbsolutePath())
					+
					
					//now add command to push busybox to system
					String.format("dd if=%s of=%s\n",
							local_busybox.getAbsoluteFile(),
							system_busybox.getAbsolutePath())
					+ String.format("chmod 755 %s\n",
							system_busybox.getAbsolutePath())
					 +
					 
					 //now add command to push dexopt-wrapper to system
					String.format("dd if=%s of=%s\n",
							local_restore.getAbsoluteFile(),
							system_restore.getAbsolutePath())
					+ String.format("chmod 755 %s\n",
							system_restore.getAbsolutePath())
					 +"exit";

			// execute to push busybox /system/xbin
			exec(command);
			
			if (system_busybox.exists() && system_restore.exists() && !immutable.exists())			
				return true;		
				
			return false;
		} catch (Exception e) {
			return false;
		}
		
	}

}
