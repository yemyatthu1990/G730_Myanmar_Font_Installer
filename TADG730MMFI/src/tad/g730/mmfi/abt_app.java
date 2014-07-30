package tad.g730.mmfi;
/*About app onclicklistener
 * */


import tad.g730.mmfi.animation.ActivityAnimator;
import android.app.Activity;
import android.content.Intent;
import android.view.View;


public class abt_app extends better_onClickListerner{
	
	abt_app(main_activity paramMainActivity) {
		super(paramMainActivity);
	}

	@Override
	public void onClick(View v) {
		//start about app acitivity on click
		Intent i=new Intent(a,abt_app_activity.class);
		a.startActivity(i);
		 ActivityAnimator anim = new ActivityAnimator();
			try { 
				anim.getClass().getMethod("appearLeftAnimation", Activity.class).invoke(anim, a);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
}
