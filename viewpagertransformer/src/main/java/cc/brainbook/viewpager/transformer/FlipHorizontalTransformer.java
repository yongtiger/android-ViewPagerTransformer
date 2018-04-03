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
public class FlipHorizontalTransformer extends BaseTransformer {

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
//        if (position > -1.0f - mExcursionLeft && position < 1.0f + mExcursionRight) {
//            showOffscreenPages(page);

            page.setTranslationX( -page.getWidth() * position); ///Counteract the default slide transition

            final float rotation = 180f * position;

            page.setAlpha(rotation > 90f || rotation < -90f ? 0 : 1);
            page.setPivotX(page.getWidth() * 0.5f);
            page.setPivotY(page.getHeight() * 0.5f);
            page.setRotationY(rotation);

            if (null != mOnTransformListener) {
                mOnTransformListener.onTransform(page, position);
            }

//        } else {
//            hideOffscreenPages(page);
//        }
    }

}