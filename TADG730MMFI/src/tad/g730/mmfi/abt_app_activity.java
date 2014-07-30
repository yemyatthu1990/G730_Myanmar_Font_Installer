package tad.g730.mmfi;
//about app activity
import tad.g730.mmfi.animation.ActivityAnimator;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class abt_app_activity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.abt_app_activity);
		View a;
		a=findViewById(R.id.github_g730);
		a.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getBaseContext(), "Redirecting To G730_Myanmar_Font_Installer Repository", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("https://github.com/TAD-Dev-Team/G730_Myanmar_Font_Installer"));              
                startActivity(i);
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		back(null);
	}
	
	public void back(View v)
	{
		this.finish();
		try
		{
			ActivityAnimator anim = new ActivityAnimator();
			anim.getClass().getMethod("disappearLeftAnimation", Activity.class).invoke(anim, this);
		}
		catch (Exception e) {
		e.printStackTrace();
		}
	}
}
