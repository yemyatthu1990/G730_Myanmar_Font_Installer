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
