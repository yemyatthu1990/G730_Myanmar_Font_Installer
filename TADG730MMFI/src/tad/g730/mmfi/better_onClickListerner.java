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
/*Every Onclicklistener class in this app extends this class.
 *  So that it will help not to create an overloaded constructor again and again
 * */

import android.view.View;

public class better_onClickListerner implements View.OnClickListener{
	protected main_activity a;
	public better_onClickListerner(main_activity paramMainActivity){
		a=paramMainActivity;
	}
	
	@Override
	public void onClick(View v) {
		
	}
	
}
