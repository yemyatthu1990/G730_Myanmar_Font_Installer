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
package tad.g730.mmfi.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Internal base builder that holds common values for all dialog fragment builders.
 *
 * @author Tomas Vondracek
 */
abstract class BaseDialogBuilder<T extends BaseDialogBuilder<T>> {

	public final static String ARG_REQUEST_CODE = "request_code";
	public final static String ARG_CANCELABLE_ON_TOUCH_OUTSIDE = "cancelable_oto";
	public final static String DEFAULT_TAG = "simple_dialog";
	public final static int DEFAULT_REQUEST_CODE = -42;

	protected final Context mContext;
	protected final FragmentManager mFragmentManager;
	protected final Class<? extends BaseDialogFragment> mClass;

	private Fragment mTargetFragment;
	private boolean mCancelable = true;
	private boolean mCancelableOnTouchOutside = true;

	private String mTag = DEFAULT_TAG;
	private int mRequestCode = DEFAULT_REQUEST_CODE;

	public BaseDialogBuilder(Context context, FragmentManager fragmentManager, Class<? extends BaseDialogFragment> clazz) {
		mFragmentManager = fragmentManager;
		mContext = context.getApplicationContext();
		mClass = clazz;
	}

	protected abstract T self();

	protected abstract Bundle prepareArguments();

	public T setCancelable(boolean cancelable) {
		mCancelable = cancelable;
		return self();
	}
	
	
	public T setCancelableOnTouchOutside(boolean cancelable) {
		mCancelableOnTouchOutside = cancelable;
		if (cancelable) {
			mCancelable = cancelable;
		}
		return self();
	}

	public T setTargetFragment(Fragment fragment, int requestCode) {
		mTargetFragment = fragment;
		mRequestCode = requestCode;
		return self();
	}

	public T setRequestCode(int requestCode) {
		mRequestCode = requestCode;
		return self();
	}

	public T setTag(String tag) {
		mTag = tag;
		return self();
	}


	public DialogFragment show() {
		final Bundle args = prepareArguments();

		final BaseDialogFragment fragment = (BaseDialogFragment) Fragment.instantiate(mContext, mClass.getName(), args);
	
		args.putBoolean(ARG_CANCELABLE_ON_TOUCH_OUTSIDE, mCancelableOnTouchOutside);
		
		if (mTargetFragment != null) {
			fragment.setTargetFragment(mTargetFragment, mRequestCode);
		} else {
			args.putInt(ARG_REQUEST_CODE, mRequestCode);
		}
		fragment.setCancelable(mCancelable);
		fragment.show(mFragmentManager, mTag);
		
		return fragment;
	}
}
