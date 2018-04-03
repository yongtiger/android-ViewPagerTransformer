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
public class DepthPageTransformer extends BaseTransformer {

	private static final float MIN_SCALE = 0.75f;

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
		if (position > -1.0f - mExcursionLeft && position < 1.0f) {
			showOffscreenPages(page);

			if (position <= 0f) {
				page.setTranslationX(0f);
				page.setScaleX(1f);
				page.setScaleY(1f);
			} else if (position <= 1f) {
				final float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
				page.setAlpha(1 - position);
				page.setPivotY(0.5f * page.getHeight());
				page.setTranslationX(page.getWidth() * -position);
				page.setScaleX(scaleFactor);
				page.setScaleY(scaleFactor);
			}

			if (null != mOnTransformListener) {
				mOnTransformListener.onTransform(page, position);
			}

		} else {
			hideOffscreenPages(page);
		}
	}

}
