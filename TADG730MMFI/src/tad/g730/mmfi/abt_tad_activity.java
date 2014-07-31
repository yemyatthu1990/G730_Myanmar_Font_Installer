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
//about tad activity
import tad.g730.mmfi.animation.ActivityAnimator;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class abt_tad_activity extends Activity
implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View a,b,c;
		
		//about tad layout
		setContentView(R.layout.abt_tad_acitivty);
		
		//tad facebook page button
		a=findViewById(R.id.facebook_tad);
		a.setOnClickListener(this);
		
		//tad twitter account button
		b=findViewById(R.id.twitter_tad);
		b.setOnClickListener(this);
		
		//tad github account button
		c=findViewById(R.id.github_tad);
		c.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		switch (v.getId()) {
		//when tad facebook button clicked
		case R.id.facebook_tad:
			Toast.makeText(getBaseContext(), "Redirecting To TAD Facebook Page", Toast.LENGTH_SHORT).show();
		        try {
		            getPackageManager().getPackageInfo("com.facebook.katana", 0);
		           i=new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1433017530315075"));
		           startActivity(i);
		           } catch (Exception e) {
		            i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/talentambitiondestiny"));
		            startActivity(i);
		           }
		        
		        
			break;
		//when tad twitter button is clicked
		case R.id.twitter_tad:
			Toast.makeText(getBaseContext(), "Redirecting To TAD Twitter Profile", Toast.LENGTH_SHORT).show();
			try{
	        i.setClassName("com.twitter.android", "com.twitter.android.ProfileActivity");
	        i.putExtra("screen_name", "TAD_Dev_Team");
	        startActivity(i);
			} catch (Exception e){
				i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/TAD_Dev_Team"));
				startActivity(i);
			}
			break;
			//when tad github button is clicked
		case R.id.github_tad:
			Toast.makeText(getBaseContext(), "Redirecting To TAD Github Profile", Toast.LENGTH_SHORT).show();
			i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/TAD-Dev-Team"));
			startActivity(i);
		default:
			break;
		}	
			
	}
	
	@Override
	public void onBackPressed() {
		back(null);
	}
	
	public void back(View v)
	{
		this.finish();
		try
		{
			ActivityAnimator anim = new ActivityAnimator();
			anim.getClass().getMethod("disappearRightAnimation", Activity.class).invoke(anim, this);
		}
		catch (Exception e) {
		e.printStackTrace();
		}
	}
}
