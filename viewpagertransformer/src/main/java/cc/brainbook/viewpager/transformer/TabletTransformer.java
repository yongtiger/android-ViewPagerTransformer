package cc.brainbook.viewpager.transformer;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;

/**
 * Class extends {@link BaseTransformer}.
 *
 * @author Robert Han
 * @email brainbook.cc@outlook.com
 * @website www.brainbook.cc
 * @time 2016/5/28 13:00
 */
public class TabletTransformer extends BaseTransformer {

	private static final Matrix OFFSET_MATRIX = new Matrix();
	private static final Camera OFFSET_CAMERA = new Camera();
	private static final float[] OFFSET_TEMP_FLOAT = new float[2];

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
//		if (position > -1.0f - mExcursionLeft && position < 1.0f + mExcursionRight) {
//			showOffscreenPages(page);

			final float rotation = (position < 0 ? 30f : -30f) * Math.abs(position);

			page.setTranslationX(getOffsetXForRotation(rotation, page.getWidth(), page.getHeight()));
			page.setPivotX(page.getWidth() * 0.5f);
			page.setPivotY(0);
			page.setRotationY(rotation);

			if (null != mOnTransformListener) {
				mOnTransformListener.onTransform(page, position);
			}

//		} else {
//			hideOffscreenPages(page);
//		}
	}

	protected static final float getOffsetXForRotation(float degrees, int width, int height) {
		OFFSET_MATRIX.reset();
		OFFSET_CAMERA.save();
		OFFSET_CAMERA.rotateY(Math.abs(degrees));
		OFFSET_CAMERA.getMatrix(OFFSET_MATRIX);
		OFFSET_CAMERA.restore();

		OFFSET_MATRIX.preTranslate(-width * 0.5f, -height * 0.5f);
		OFFSET_MATRIX.postTranslate(width * 0.5f, height * 0.5f);
		OFFSET_TEMP_FLOAT[0] = width;
		OFFSET_TEMP_FLOAT[1] = height;
		OFFSET_MATRIX.mapPoints(OFFSET_TEMP_FLOAT);
		return (width - OFFSET_TEMP_FLOAT[0]) * (degrees > 0.0f ? 1.0f : -1.0f);
	}

}
