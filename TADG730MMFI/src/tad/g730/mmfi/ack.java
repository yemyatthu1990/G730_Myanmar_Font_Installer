package tad.g730.mmfi;
/*acknowledgement onclicklistener
 * */


import tad.g730.mmfi.animation.ActivityAnimator;
import android.app.Activity;
import android.content.Intent;
import android.view.View;


public class ack extends better_onClickListerner{
	
	ack(main_activity paramMainActivity) {
		super(paramMainActivity);
	}

	@Override
	public void onClick(View v) {
		//start acknowledgement activity on click
		Intent i=new Intent(a,acknowledgement.class);
		a.startActivity(i);
		 ActivityAnimator anim = new ActivityAnimator();
			try {
				anim.getClass().getMethod("flipHorizontalAnimation", Activity.class).invoke(anim, a);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
}
	
