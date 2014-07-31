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
	
