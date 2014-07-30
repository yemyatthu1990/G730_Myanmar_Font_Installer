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
