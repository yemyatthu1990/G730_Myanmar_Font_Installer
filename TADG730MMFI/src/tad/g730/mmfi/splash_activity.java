package tad.g730.mmfi;

import tad.g730.mmfi.dialogs.ISimpleDialogListener;
import tad.g730.mmfi.dialogs.SimpleDialogFragment;
import tad.g730.mmfi.utils.check_state;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


public class splash_activity extends FragmentActivity implements ISimpleDialogListener{
	private SharedPreferences sp;
	private Editor edit;
	private ImageView splash_icon,splash_text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setTheme(R.style.CustomLightTheme);
		sp=PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    	edit=sp.edit();	
		setContentView(R.layout.splash_screen);
		splash_icon=(ImageView)findViewById(R.id.splash_icon);
    	splash_text=(ImageView)findViewById(R.id.splash_text);
    	splash_text.setVisibility(8);
    	splash_icon.setVisibility(8);
    	new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				splash_icon.setVisibility(0);
				YoYo.with(Techniques.BounceInDown)
		        .duration(700)
		        .playOn(splash_icon);				
			}
		}, 200);	
		
		new Handler().postDelayed(new Runnable() {		
			@Override
			public void run() {
				splash_text.setVisibility(0);
				boolean is_installed;
            	is_installed=check_state.checkInstalled();
            	
            	/*save is_installed to default shared prefrences in
            	in order to reduce main_acitivity loading time*/
            	edit.putBoolean("is_installed", is_installed);
    			edit.commit();
    			YoYo.with(Techniques.Pulse)
		        .duration(700)
		        .playOn(splash_icon);
    			
				YoYo.with(Techniques.BounceInUp)
		        .duration(700)
		        .playOn(splash_text);
				
			}
		},700);
		if(!Build.MODEL.contains("G730-U00") || Build.VERSION.SDK_INT!=17 ){
			new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
					SimpleDialogFragment.createBuilder(getBaseContext(), getSupportFragmentManager())
		             .setMessage(R.string.device_not_compatible)
		             .setTitle(R.string.app_name)
		             .setCancelable(false)
		             .setCancelableOnTouchOutside(false)
		             .show();					
				}
			}, 1600);
			 
           //AlertDialog.Builder builder=new AlertDialog.Builder(this);
           //builder.setTitle(R.string.app_name).setMessage(R.string.device_not_compatible).show();
		}else{
		
		new Handler().postDelayed(new Runnable() {
            
            // Using handler with postDelayed called runnable run method
  
            @Override
            public void run() {
            	Intent i=new Intent();
    			//the value of is_agreed is assigned in terms and condition activity
            	if(sp.getBoolean("is_agreed", false)){
                i = new Intent(getBaseContext(), main_activity.class);                
                }else{
                i=new Intent(getBaseContext(),terms_condition.class);
                }
            	startActivity(i);
                overridePendingTransition(R.anim.flip_vertical_in, R.anim.flip_vertical_out);
            	//appearBottomRightAnimation
            	
                // close this activity
                finish();
            }
        }, 1600); // wait for 5 seconds
	}
	}

	@Override
	public void onPositiveButtonClicked(int requestCode) {
		System.exit(-1);
		
	}
	
	


}
