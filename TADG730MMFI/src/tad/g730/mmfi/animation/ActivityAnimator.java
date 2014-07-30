package tad.g730.mmfi.animation;


import android.app.Activity;

import tad.g730.mmfi.R;

public class ActivityAnimator
{
	public void flipHorizontalAnimation(Activity a)
	{
		a.overridePendingTransition(R.anim.flip_horizontal_in, R.anim.flip_horizontal_out);
	}
	
	public void flipVerticalAnimation(Activity a)
	{
		a.overridePendingTransition(R.anim.flip_vertical_in, R.anim.flip_vertical_out);
	}
	
	public void appearRightAnimation(Activity a)
	{
		a.overridePendingTransition(R.anim.appear_right_in, R.anim.appear_right_out);
	}
	
	public void disappearRightAnimation(Activity a)
	{
		a.overridePendingTransition(R.anim.disappear_right_in, R.anim.disappear_right_out);
	}
	
	public void appearLeftAnimation(Activity a)
	{
		a.overridePendingTransition(R.anim.appear_left_in, R.anim.appear_left_out);
	}
	
	public void disappearLeftAnimation(Activity a)
	{
		a.overridePendingTransition(R.anim.disappear_left_in, R.anim.disappear_left_out);
	}
}
