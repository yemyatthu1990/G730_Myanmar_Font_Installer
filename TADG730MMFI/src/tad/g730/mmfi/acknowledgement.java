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

//ackowledgement activity
import tad.g730.mmfi.animation.ActivityAnimator;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class acknowledgement extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.acknowledgement_list);

		View kyaw_swar_twin, nay_shane_oo, ye_lin_aung, phone_htet_naing, nyein_aye_zaw, han_zaw_oo, nyi_nyi_naing, ultema, nyan_thu_lin;

		kyaw_swar_twin = findViewById(R.id.Kyaw_Swar_Twin);
		kyaw_swar_twin.setOnClickListener(this);

		nay_shane_oo = findViewById(R.id.Nay_Shane_Oo);
		nay_shane_oo.setOnClickListener(this);

		ye_lin_aung = findViewById(R.id.Ye_Lin_Aung);
		ye_lin_aung.setOnClickListener(this);

		phone_htet_naing = findViewById(R.id.Phone_Htet_Naing);
		phone_htet_naing.setOnClickListener(this);

		nyein_aye_zaw = findViewById(R.id.Nyein_Aye_Zaw);
		nyein_aye_zaw.setOnClickListener(this);

		han_zaw_oo = findViewById(R.id.Han_Zaw_Oo);
		han_zaw_oo.setOnClickListener(this);

		nyi_nyi_naing = findViewById(R.id.Nyi_Nyi_Naing);
		nyi_nyi_naing.setOnClickListener(this);

		ultema = findViewById(R.id.Ultema);
		ultema.setOnClickListener(this);

		nyan_thu_lin = findViewById(R.id.Nyan_Thu_Lin);
		nyan_thu_lin.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		String name = "";
		String fbid = "";
		String fburl = "";
		switch (v.getId()) {
		case R.id.Kyaw_Swar_Twin:
			name = "Ko Kyaw Swar Twin";
			fbid = "100005753280868";
			fburl = "https://www.facebook.com/kyawswar.thwin.16";
			break;
		case R.id.Nay_Shane_Oo:
			name = "Ko Nay Shane Oo";
			fbid = "100000928296672";
			fburl = "https://www.facebook.com/mgkboy";
			break;
		case R.id.Ye_Lin_Aung:
			name = "Ko Ye` Lin Aung";
			fbid = "100003671456372";
			fburl = "https://www.facebook.com/kgkremix";
			break;
		case R.id.Phone_Htet_Naing:
			name = "Ko Phone Htet Naing";
			fbid = "100004404400806";
			fburl = "https://www.facebook.com/htet.naing.1276";
			break;
		case R.id.Nyein_Aye_Zaw:
			name = "Ko Nyein Aye Zaw";
			fbid = "100000028032639";
			fburl = "https://www.facebook.com/profile.php?id=100000028032639";
			break;
		case R.id.Han_Zaw_Oo:
			name = "Ko Han Zaw Oo";
			fbid = "100003115704699";
			fburl = "https://www.facebook.com/onmyway11";
			break;
		case R.id.Nyi_Nyi_Naing:
			name = "Ko Nyi Nyi Naing";
			fbid = "100001990938368";
			fburl = "https://www.facebook.com/nyi.naing.39";
			break;
		case R.id.Ultema:
			name = "Ultema";
			fbid = "100003357842310";
			fburl = "https://www.facebook.com/alex.ultema";
			break;
		case R.id.Nyan_Thu_Lin:
			name = "Ko Nyan Thu Lin";
			fbid = "100004991563194";
			fburl = "https://www.facebook.com/sky.thu.1";
			break;
		default:
			break;
		}
		Toast.makeText(getBaseContext(),
				"Redirecting To " + name + "'s Facebook Timeline",
				Toast.LENGTH_SHORT).show();
		try {
			getPackageManager().getPackageInfo("com.facebook.katana", 0);
			i = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/" + fbid));
			startActivity(i);
		} catch (Exception e) {
			i = new Intent(Intent.ACTION_VIEW, Uri.parse(fburl));
			startActivity(i);
		}

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
			anim.getClass().getMethod("flipHorizontalAnimation", Activity.class).invoke(anim, this);
		}
		catch (Exception e) {
		e.printStackTrace();
		}
	}
}
