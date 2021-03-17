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
public class ZoomOutSlideTransformer extends BaseTransformer {

	private static final float MIN_SCALE = 0.85f;
	private static final float MIN_ALPHA = 0.5f;

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
//		if (position > -1.0f - mExcursionLeft && position < 1.0f + mExcursionRight) {
//			showOffscreenPages(page);

			final float height = page.getHeight();
			final float width = page.getWidth();
			final float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
			final float vertMargin = height * (1 - scaleFactor) / 2;
			final float horzMargin = width * (1 - scaleFactor) / 2;

			// Center vertically
			page.setPivotY(0.5f * height);
			page.setPivotX(0.5f * width);

			if (position < 0) {
				page.setTranslationX(horzMargin - vertMargin / 2);
			} else {
				page.setTranslationX(-horzMargin + vertMargin / 2);
			}

			// Scale the page down (between MIN_SCALE and 1)
			page.setScaleX(scaleFactor);
			page.setScaleY(scaleFactor);

			// Fade the page relative to its size.
			page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));

			if (null != mOnTransformListener) {
				mOnTransformListener.onTransform(page, position);
			}

//		} else {
//			hideOffscreenPages(page);
//		}
	}

}
