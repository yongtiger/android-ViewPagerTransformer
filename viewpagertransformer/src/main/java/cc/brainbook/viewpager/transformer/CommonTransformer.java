package cc.brainbook.viewpager.transformer;

import android.view.View;
import java.util.ArrayList;

/**
 *  The common transformer class extends {@link BaseTransformer}.
 *
 * @author Robert Han
 * @email brainbook.cc@outlook.com
 * @website www.brainbook.cc
 * @time 2016/5/28 13:00
 *
 * <p>You can specify parameters to construct a variety of different transformers.
 *
 * For example:
 * <pre class="prettyprint">
 * final String[][] params = {
 *      // Counteract the default slide transition
 *      {"TranslationX", "-1", "0", "1", "0"},
 *      {"TranslationX", "0", "0", "0", "0"},
 *      {"TranslationX", "0", "1", "0", "-1"},
 *
 *      {"PivotX", "-1", "0", "0", "0"},
 *      {"PivotX", "0", "1", "1", "1"},
 *      {"ScaleX", "-1", "0", "0", "1"},
 *      {"ScaleX", "0", "1", "1", "0"},
 * };
 * final CommonTransformer transformer = new CommonTransformer(params);
 * </pre>
 * </p>
 *
 */
public class CommonTransformer extends BaseTransformer {

    protected ArrayList<Param> mParams = new ArrayList<Param>();

    /**
     * Constructor to use when creating a transformer from code.
     *
     * @param params
     *            Two-dimensional array of transformer parameters.
     */
    public CommonTransformer(String[][] params){
        for (int i = 0; i < params.length; i++) {
            Param param = new Param();
            param.name = params[i][0];
            param.p1 = Float.parseFloat(params[i][1]);
            param.p2 = Float.parseFloat(params[i][2]);
            param.v1 = Float.parseFloat(params[i][3]);
            param.v2 = Float.parseFloat(params[i][4]);
            param.el = params[i].length <= 5 ? 0 : Float.parseFloat(params[i][5]);
            param.er = params[i].length <= 5 ? 0 : Float.parseFloat(params[i][6]);
            mParams.add(param);
        }
    }

    /**
     * Class of the transformer parameters.
     */
    protected class Param {
        String name;
        float p1, p2, v1, v2, el, er;
    }

    /**
     * Calculate the factor.
     *
     * @param position
     *            Position of page relative to the current front-and-center position of the pager. 0 is front and
     *            center. 1 is one full page position to the right, and -1 is one page position to the left.
     * @param param
     *            Object of transformer parameter class.
     */
    protected float calculateFactor(float position, Param param) {
        final float b = (param.p1 == param.p2 || param.v1 == param.v2) ?
                0 : (param.v2 - param.v1) / (param.p2 - param.p1);
        final float a = param.v1 - b * param.p1;
        return  a + b * position;
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
    @Override
    protected void onTransform(View page, float position) {

        boolean isShowOffscreenPages = false;

        for (Param param : mParams) {
            if (position > param.p1 + param.el * mExcursionLeft
                    && position < param.p2 + param.er * mExcursionRight
                    || position == param.p1
                    && position == param.p2) {

                if(!isShowOffscreenPages){
                    showOffscreenPages(page);
                    isShowOffscreenPages = true;
                }

                switch (param.name) {
                    case "TranslationX":
                        page.setTranslationX(calculateFactor(position, param) * page.getWidth());
                        break;
                    case "TranslationY":
                        page.setTranslationY(calculateFactor(position, param) * page.getHeight());
                        break;
                    case "PivotX":
                        page.setPivotX(calculateFactor(position, param) * page.getWidth());
                        break;
                    case "PivotY":
                        page.setPivotY(calculateFactor(position, param) * page.getHeight());
                        break;
                    case "ScaleX":
                        page.setScaleX(calculateFactor(position, param));
                        break;
                    case "ScaleY":
                        page.setScaleY(calculateFactor(position, param));
                        break;
                    case "RotationX":
                        page.setRotationX(calculateFactor(position, param) * 360);
                        break;
                    case "RotationY":
                        page.setRotationY(calculateFactor(position, param) * 360);
                        break;
                    case "Rotation":
                        page.setRotation(calculateFactor(position, param) * 360);
                        break;
                    case "Alpha":
                        page.setAlpha(calculateFactor(position, param));
                        break;
                    case "Z":
                        page.setZ(calculateFactor(position, param));
                        break;
                }
            }
        }

        if (!isShowOffscreenPages && !mParams.isEmpty()) {
            hideOffscreenPages(page);
        }

        if (null != mOnTransformListener) {
            mOnTransformListener.onTransform(page, position);
        }
    }

}
