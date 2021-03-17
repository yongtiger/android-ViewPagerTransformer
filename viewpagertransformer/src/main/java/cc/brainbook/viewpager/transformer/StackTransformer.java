package cc.brainbook.viewpager.transformer;

import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;

/**
 * Class extends {@link BaseTransformer}.
 *
 * @author Robert Han
 * @email brainbook.cc@outlook.com
 * @website www.brainbook.cc
 * @time 2016/5/28 13:00
 */
public class StackTransformer extends BaseTransformer {

	/**
	 * Called each {@link #transformPage(View, float)}.
	 *
	 * @param page
	 *            Apply the transformation to this page
	 * @param position
	 *            Position of page relative to the current front-and-center position of the pager. 0 is front and
	 *            center. 1 is one full page position to the right, and -1 is one page position to the left.
	 */
	@Override
	protected void onTransform(@NonNull View page, float position) {
		if (position > -1.0f - mExcursionLeft && position < 1.0f + 0) {
			showOffscreenPages(page);

			page.setTranslationX(position < 0 ? 0f : -page.getWidth() * position);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				page.setZ(-position);
			}

			if (null != mOnTransformListener) {
				mOnTransformListener.onTransform(page, position);
			}

		} else {
			hideOffscreenPages(page);
		}
	}

}
