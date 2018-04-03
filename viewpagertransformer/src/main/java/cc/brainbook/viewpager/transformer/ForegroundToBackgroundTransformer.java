package cc.brainbook.viewpager.transformer;

import android.view.View;

/**
 * Class extends {@link BaseTransformer}.
 *
 * @author Robert Han
 * @email brainbook.cc@outlook.com
 * @website www.brainbook.cc
 * @time 2016/5/28 13:00
 */
public class ForegroundToBackgroundTransformer extends BaseTransformer {

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
	protected void onTransform(View page, float position) {
		///Hides the off-screen pages caused by rapid slide
		if (position > -1.0f - mExcursionLeft && position < 1.0f + mExcursionRight) {
			showOffscreenPages(page);

			final float height = page.getHeight();
			final float width = page.getWidth();
			final float scale = Math.max(position > 0 ? 1f : Math.abs(1f + position), 0.5f);

			page.setScaleX(scale);
			page.setScaleY(scale);
			page.setPivotX(width * 0.5f);
			page.setPivotY(height * 0.5f);
			page.setTranslationX(position > 0 ? width * position : -width * position * 0.25f);

			if (null != mOnTransformListener) {
				mOnTransformListener.onTransform(page, position);
			}

		} else {
			hideOffscreenPages(page);
		}
	}

}
