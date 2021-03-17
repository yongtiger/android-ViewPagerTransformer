package cc.brainbook.viewpager.transformer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import android.view.View;

/**
 * The abstract base class that implements {@link ViewPager.PageTransformer}.
 *
 * @author Robert Han
 * @email brainbook.cc@outlook.com
 * @website www.brainbook.cc
 * @time 2016/5/28 13:00
 */
public abstract class BaseTransformer implements ViewPager.PageTransformer {

    /**
     * The left and right excursions.
     */
    protected float mExcursionLeft;
    protected float mExcursionRight;

    /**
     * Listener used to dispatch transform events.
     */
    public OnTransformListener mOnTransformListener;

    /**
     * Interface definition for a callback to be invoked when a view pager is transformed.
     */
    public interface OnTransformListener {
        void onTransform(View page, float position);
    }

    /**
     * Register a callback to be invoked when this view pager is transformed.
     *
     * @param l     The callback that will run
     */
    public void setOnTransformListener(@Nullable OnTransformListener l) {
        mOnTransformListener = l;
    }

    /**
     * Apply a property transformation to the given page. For most use cases, this method should not be overridden.
     * Instead use {@link #transformPage(android.view.View, float)} to perform typical transformations.
     *
     * @param page
     *            Apply the transformation to this page
     * @param position
     *            Position of page relative to the current front-and-center position of the pager. 0 is front and
     *            center. 1 is one full page position to the right, and -1 is one page position to the left.
     */
    @Override
    public void transformPage(@NonNull View page, float position){
        setExcursion(page);
        position -= mExcursionLeft; //counteract the default page's position to Zero

        onTransform(page, position);

        if (null != mOnTransformListener) {
            mOnTransformListener.onTransform(page, position);
        }
    }

    /**
     * Called each {@link #transformPage(android.view.View, float)}.
     *
     * @param page
     *            Apply the transformation to this page
     * @param position
     *            Position of page relative to the current front-and-center position of the pager. 0 is front and
     *            center. 1 is one full page position to the right, and -1 is one page position to the left.
     */
    protected abstract void onTransform(@NonNull View page, float position);

    /**
     * Sets the left and right excursions.
     *
     * <p>When a view pager has been set left padding, the page's position will has a left excursion.
     * That means the position of first display page is no longer than Zero!
     * We have to counteract the default page's position to Zero by using the left excursion.</p>
     *
     * <p>In addition, we usually set an interval to determine whether the page is displayed or not.
     * The boundary of the interval is usually plus or minus 1, but in special cases, it needs to be extended
     * to excursion to ensure that the transition is smooth and continuous animated without fragmentation.</p>
     *
     * <p>For example:
     * <pre class="prettyprint">
     * if (position > -1.0f - mExcursionLeft && position < 1.0f + mExcursionRight) {
     *     // TODO ...
     * } else {
     *     // This page is way off-screen to the left and right.
     *     view.setAlpha(0);
     * }
     * </pre>
     * </p>
     *
     * @param page    Apply the transformation to this page
     */
    public void setExcursion(@NonNull View page) {
        ViewPager viewPager = (ViewPager) page.getParent();
        mExcursionLeft = (float) viewPager.getPaddingLeft() / page.getWidth();
        mExcursionRight = (float) viewPager.getPaddingRight() / page.getWidth();
    }

    /**
     * Gets the left excursion by a given view pager.
     *
     * @param viewPager
     * @return The left excursion
     */
    public static float getExcursionLeft(@NonNull ViewPager viewPager) {
        return (float) viewPager.getPaddingLeft() / (viewPager.getMeasuredWidth() - viewPager.getPaddingLeft() - viewPager.getPaddingRight());
    }

    /**
     * Gets the right excursion by a given view pager.
     *
     * @param viewPager
     * @return The right excursion
     */
    public static float getExcursionRight(@NonNull ViewPager viewPager) {
        return (float) viewPager.getPaddingRight() / (viewPager.getMeasuredWidth() - viewPager.getPaddingLeft() - viewPager.getPaddingRight());
    }

    /**
     * Hides off-screen pages.
     *
     * {@code setAlpha(0f)} is ALMOST equivalent to setVisibility(View.INVISIBLE) that only hides the view and still takes the space in your layout.
     * Note: setting alpha to a translucent value (0 < alpha < 1) can have significant performance implications, especially for large views.
     * It is best to use the alpha property sparingly and transiently, as in the case of fading animations.
     * {@code setVisibility(View.GONE)} will not only hide your view but it will also recycle the space occupied by this view.
     *
     * @see <a href="https://developer.android.com/reference/android/view/View.html#setAlpha(float)">https://developer.android.com/reference/android/view/View.html#setAlpha(float)</a>
     * @see <a href="https://stackoverflow.com/questions/16594809/difference-between-setvisibility-and-setalpha">https://stackoverflow.com/questions/16594809/difference-between-setvisibility-and-setalpha</a>
     *
     * @param page    Apply the transformation to this page
     */
    public void hideOffscreenPages(@NonNull View page) {
        page.setAlpha(0);
//        page.setVisibility(View.INVISIBLE);
    }

    /**
     * Shows off-screen pages.
     *
     * @param page    Apply the transformation to this page
     */
    public void showOffscreenPages(@NonNull View page) {
        page.setAlpha(1);
//        page.setVisibility(View.VISIBLE);
    }

}
