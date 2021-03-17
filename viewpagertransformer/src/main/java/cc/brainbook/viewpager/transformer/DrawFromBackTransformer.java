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
public class DrawFromBackTransformer extends BaseTransformer {
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
        if (position > -1.0f - 0 && position < 1.0f + 0) {
            showOffscreenPages(page);

            int pageWidth = page.getWidth();
            if(position >= -1.0F && position <= 1.0F) {
                float v;
                if (position <= 0.0F) {
                    page.setAlpha(1.0F + position);
                    page.setTranslationX((float) pageWidth * -position);
                    v = 0.75F + 0.25F * (1.0F - Math.abs(position));
                    page.setScaleX(v);
                    page.setScaleY(v);
                } else if ((double) position > 0.5D && position <= 1.0F) {
                    page.setAlpha(0.0F);
                    page.setTranslationX((float) pageWidth * -position);
                } else if ((double) position > 0.3D && (double) position <= 0.5D) {
                    page.setAlpha(1.0F);
                    page.setTranslationX((float) pageWidth * position);
                    v = 0.75F;
                    page.setScaleX(v);
                    page.setScaleY(v);
                } else {
                    if ((double) position <= 0.3D) {
                        page.setAlpha(1.0F);
                        page.setTranslationX((float) pageWidth * position);
                        v = (float) (0.3D - (double) position);
                        v = Math.min(v, 0.25F);
                        float scaleFactor = 0.75F + v;
                        page.setScaleX(scaleFactor);
                        page.setScaleY(scaleFactor);
                    }

                }
            }

            if (null != mOnTransformListener) {
                mOnTransformListener.onTransform(page, position);
            }

        } else {
            hideOffscreenPages(page);
        }
    }

}
