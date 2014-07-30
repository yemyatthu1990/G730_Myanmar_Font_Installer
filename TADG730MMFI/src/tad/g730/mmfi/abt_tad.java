package tad.g730.mmfi;
/*about tad onclickListen
 * */


import tad.g730.mmfi.animation.ActivityAnimator;
import android.app.Activity;
import android.content.Intent;
import android.view.View;


public class abt_tad extends better_onClickListerner{
	
	abt_tad(main_activity paramMainActivity) {
		super(paramMainActivity);
	}

	@Override
	public void onClick(View v) {
		//start about tad activity
		Intent i=new Intent(a,abt_tad_activity.class);
		a.startActivity(i);
		 ActivityAnimator anim = new ActivityAnimator();
			try {
				anim.getClass().getMethod("appearRightAnimation", Activity.class).invoke(anim, a);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	

}
