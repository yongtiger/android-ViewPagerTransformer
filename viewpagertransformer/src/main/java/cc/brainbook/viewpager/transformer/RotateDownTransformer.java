package cc.brainbook.viewpager.transformer;

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
public class RotateDownTransformer extends BaseTransformer {
	private static final float ROT_MOD = -15f;

	/**
	 * Called each {@link #transformPage(android.view.View, float)}.
	 *
	 * @param page
	 *            Apply the transformation to this page
	 * @param position
	 *            Position of page relative to the current front-and-center position of the pager. 0 is front and
	 *            center. 1 is one full page position to the right, and -1 is one page position to the left.
	 */
	@Override
	protected void onTransform(@NonNull View page, float position) {
		if (position > -1.0f - mExcursionLeft && position < 1.0f + mExcursionRight) {
			showOffscreenPages(page);

			final float width = page.getWidth();
			final float height = page.getHeight();
			final float rotation = ROT_MOD * position * -1.25f;

			page.setPivotX(width * 0.5f);
			page.setPivotY(height);
			page.setRotation(rotation);

			if (null != mOnTransformListener) {
				mOnTransformListener.onTransform(page, position);
			}

		} else {
			hideOffscreenPages(page);
		}
	}

}
