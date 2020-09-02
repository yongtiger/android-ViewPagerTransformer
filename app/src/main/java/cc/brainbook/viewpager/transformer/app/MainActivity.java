package cc.brainbook.viewpager.transformer.app;

import android.os.Bundle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cc.brainbook.viewpager.transformer.CommonTransformer;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<View> viewList = new ArrayList<>();
        Field[] fields = R.drawable.class.getDeclaredFields();
        try {
            for (Field field : fields) {
                if (field.getName().startsWith("ic_test")) {
                    ImageView imageView = new ImageView(this);
//                    imageView.setImageResource(field.getInt(null));///Original image
                    imageView.setBackgroundResource(field.getInt(null));///fit center
                    viewList.add(imageView);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        MyPagerAdapter adapter = new MyPagerAdapter(viewList);
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        viewPager.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        ///main testcases
        viewPager.setPadding(50, 150,100, 150);
        viewPager.setClipToPadding(false);
//        viewPager.setClipChildren(false);

        ///other testcases
//        viewPager.setPageMargin(30);
//        viewPager.setCurrentItem(3);
//        viewPager.setOffscreenPageLimit(3);

        ///--------- Comment out for DefaultTransform ---------
        final String[][] params = {};
        ///--------- Comment out for ScaleInOutTransformer ---------
//        final String[][] params = {
//                ///Counteract the default slide transition
//                ///same as: page.setTranslationX( -page.getWidth() * position);
//                {"TranslationX", "-1", "0", "1", "0"},
//                {"TranslationX", "0", "0", "0", "0"},///optional
//                {"TranslationX", "0", "1", "0", "-1"},
//
//                {"PivotX", "-1", "0", "0", "0"},
////                {"PivotX", "0", "0", "1", "1"},///optional
//                {"PivotX", "0", "1", "1", "1"},
//
////                {"PivotY", "-1", "1", "0.5" ,"0.5"},///optional
//
//                {"ScaleX", "-1", "0", "0", "1"},
////                {"ScaleX", "0", "0", "1", "1"},///optional
//                {"ScaleX", "0", "1", "1", "0"},
//
//                {"ScaleY", "-1", "0", "0", "1"},
////                {"ScaleY", "0", "0", "1", "1"},///optional
//                {"ScaleY", "0", "1", "1", "0"},
//        };
        ///--------- Comment out for AccordionTransformer ---------
//        final String[][] params = {
//                ///Counteract the default slide transition
//                ///same as: page.setTranslationX( -page.getWidth() * position);
//                {"TranslationX", "-1", "0", "1", "0"},
//                {"TranslationX", "0", "0", "0", "0"},///optional
//                {"TranslationX", "0", "1", "0", "-1"},
//
//                {"PivotX", "-1", "0", "0", "0"},
////                {"PivotX", "0", "0", "1", "1"},///optional
//                {"PivotX", "0", "1", "1", "1"},
//
//                {"ScaleX", "-1", "0", "0", "1"},
////                {"ScaleX", "0", "0", "1", "1"},///optional
//                {"ScaleX", "0", "1", "1", "0"},
//        };
        ///--------- Comment out for BackgroundToForegroundTransformer ---------
//        final String[][] params = {
//                ///Counteract the default slide transition
//                ///same as: page.setTranslationX( -page.getWidth() * position);
//                {"TranslationX", "-1", "0", "-1", "0", "-1", "0"},
////                {"TranslationX", "0", "0", "0", "0"},///optional
//                {"TranslationX", "0", "1", "0", "-0.25", "0", "1"},
//
//                {"PivotX", "-1", "1", "0.5", "0.5", "-1", "1"},
//                {"PivotY", "-1", "1", "0.5", "0.5", "-1", "1"},
//
//                {"ScaleX", "-1", "0", "1", "1", "-1", "0"},
//                {"ScaleX", "0", "0.5", "1", "0.5"},
//                {"ScaleX", "0.5", "1", "0.5", "0.5", "0", "1.2"},///Note: set 1.2 to correct right-side segment and picture discontinuity
//
//                {"ScaleY", "-1", "0", "1", "1", "-1", "0"},
//                {"ScaleY", "0", "0.5", "1", "0.5"},
//                {"ScaleY", "0.5", "1", "0.5", "0.5", "0", "1.2"},///Note: set 1.2 to correct right-side segment and picture discontinuity
//        };
        ///--------- Comment out for ForegroundToBackgroundTransformer ---------
//        final String[][] params = {
//                {"TranslationX", "-1", "0", "0.25", "0", "-1", "0"},
////                {"TranslationX", "0", "0", "0", "0"},///optional
//                {"TranslationX", "0", "1", "0", "1", "0", "1"},
//
//                {"PivotX", "-1", "1", "0.5", "0.5", "-1", "1"},
//                {"PivotY", "-1", "1", "0.5", "0.5", "-1", "1"},
//
//                {"ScaleX", "-1", "-0.5", "0.5", "0.5", "-1", "0"},
//                {"ScaleX", "-0.5", "0", "0.5", "1"},
//                {"ScaleX", "0", "0", "1", "1"},
//                {"ScaleX", "0", "1", "1", "1", "0", "1"},
//
//                {"ScaleY", "-1", "-0.5", "0.5", "0.5", "-1", "0"},
//                {"ScaleY", "-0.5", "0", "0.5", "1"},
//                {"ScaleY", "0", "0", "1", "1"},
//                {"ScaleY", "0", "1", "1", "1", "0", "1"},
//        };
        ///--------- Comment out for CubeInTransformer ---------
//        final String[][] params = {
//                {"PivotX", "-1", "0", "1", "1"},
//                {"PivotX", "0", "1", "0", "0"},
//                {"PivotY", "-1", "1", "0", "0"},
//
//                {"RotationY", "-1", "0", "0.25", "0"},
//                {"RotationY", "0", "1", "0", "-0.25"},
//        };
        ///--------- Comment out for CubeOutTransformer ---------
//        final String[][] params = {
//                {"PivotX", "-1", "0", "1", "1"},
//                {"PivotX", "0", "1", "0", "0"},
//                {"PivotY", "-1", "1", "0.5", "0.5"},
//
//                {"RotationY", "-1", "0", "-0.25", "0"},
//                {"RotationY", "0", "1", "0", "0.25"},
//        };
        ///--------- Comment out for DepthPageTransformer ---------
//        final String[][] params = {
//                ///Counteract the default slide transition
//                ///same as: page.setTranslationX( -page.getWidth() * position);
//                {"TranslationX", "-1", "0", "0", "0", "-1", "0"},
////                {"TranslationX", "0", "0", "0", "0"},///optional
//                {"TranslationX", "0", "1", "0", "-1"},
//
//                {"PivotY", "0", "1", "0.5", "0.5"},
//
//                {"ScaleX", "-1", "0", "1", "1", "-1", "0"},
//                {"ScaleX", "0", "0", "1", "1"},
//                {"ScaleX", "0", "1", "1", "0.75"},
//
//                {"ScaleY", "-1", "0", "1", "1", "-1", "0"},
//                {"ScaleY", "0", "0", "1", "1"},
//                {"ScaleY", "0", "1", "1", "0.75"},
//
//                {"Alpha", "0", "1", "1", "0"},
//        };
        ///--------- Comment out for StackTransformer ---------
//        final String[][] params = {
//                {"TranslationX", "-1", "0", "0", "0", "-1", "0"},
//                {"TranslationX", "0", "0", "0", "0"},
//                {"TranslationX", "0", "1", "0", "-1", "0", "0"},
//
//                {"Z", "-1", "0", "1", "0", "-1", "0"},
//                {"Z", "0", "0", "0", "0"},
//                {"Z", "0", "1", "0", "-1", "0", "0"},
//        };
        ///--------- Comment out for DrawerTransformer ---------
//        final String[][] params = {
//                {"TranslationX", "-1", "0", "0", "0", "-1", "0"},
//                {"TranslationX", "0", "0", "0", "0"},///optional
//                {"TranslationX", "0", "1", "0", "-0.5"},
//
//                {"Alpha", "0", "1", "1", "0"},
//        };
        ///--------- Comment out for DrawFromBackTransformer ---------
//        final String[][] params = {
//                {"TranslationX", "-1", "0", "1", "0"},
//                {"TranslationX", "0", "0", "0", "0"},///optional
//                {"TranslationX", "0", "0.5", "0", "0.5"},
//                {"TranslationX", "0.5", "0.5", "0.5", "0.5"},
//                {"TranslationX", "0.5", "1", "-0.5", "-1"},
//
//                {"ScaleX", "-1", "0", "0.75", "1"},
//                {"ScaleX", "0", "0", "1", "1"},
//                {"ScaleX", "0", "0.3", "1.05", "1"},
//                {"ScaleX", "0.3", "0.3", "1", "1"},
//                {"ScaleX", "0.3", "0.5", "1", "0.75"},
//                {"ScaleX", "0.5", "0.5", "0.75", "0.75"},
//
//                {"ScaleY", "-1", "0", "0.75", "1"},
//                {"ScaleY", "0", "0", "1", "1"},
//                {"ScaleY", "0", "0.3", "1.05", "1"},
//                {"ScaleY", "0.3", "0.5", "1", "0.75"},
//                {"ScaleY", "0.5", "0.5", "0.75", "0.75"},
//
//                {"Alpha", "-1", "0", "0", "1"},
//                {"Alpha", "0", "0", "1", "1"},
//                {"Alpha", "0", "0.5", "1", "1"},
//                {"Alpha", "0.5", "0.5", "1", "1"},
//                {"Alpha", "0.5", "1", "0", "0"},
//        };
        ///--------- Comment out for FlipHorizontalTransformer ---------
//        final String[][] params = {
//                ///Counteract the default slide transition
//                ///same as: page.setTranslationX( -page.getWidth() * position);
//                {"TranslationX", "-1", "0", "1", "0"},
//                {"TranslationX", "0", "0", "0", "0"},///optional
//                {"TranslationX", "0", "1", "0", "-1"},
//
//                {"PivotX", "-1", "1", "0.5", "0.5"},
//                {"PivotY", "-1", "1", "0.5", "0.5"},
//
//                {"RotationY", "-1", "0", "-0.5", "0"},
//                {"RotationY", "0", "1", "0", "0.5"},
//
//                {"Alpha", "-1", "-0.5", "0", "0"},
//                {"Alpha", "-0.5", "0.5", "1", "1"},
//                {"Alpha", "0.5", "1", "0", "0"},
//        };
        ///--------- Comment out for FlipVerticalTransformer ---------
//        final String[][] params = {
//                ///Counteract the default slide transition
//                ///same as: page.setTranslationX( -page.getWidth() * position);
//                {"TranslationX", "-1", "0", "1", "0"},
//                {"TranslationX", "0", "0", "0", "0"},///optional
//                {"TranslationX", "0", "1", "0", "-1"},
//
//                {"PivotX", "-1", "1", "0.5", "0.5"},
//                {"PivotY", "-1", "1", "0.5", "0.5"},
//
//                {"RotationX", "-1", "0", "-0.5", "0"},
//                {"RotationX", "0", "1", "0", "0.5"},
//
//                {"Alpha", "-1", "-0.5", "0", "0"},
//                {"Alpha", "-0.5", "0.5", "1", "1"},
//                {"Alpha", "0.5", "1", "0", "0"},
//        };
        ///--------- Comment out for RotateDownTransformer ---------
//        final String[][] params = {
//                {"PivotX", "-1", "1", "0.5", "0.5", "-1", "1"},
//                {"PivotY", "-1", "1", "1", "1", "-1", "1"},
//
//                {"Rotation", "-1", "0", "-0.05", "0", "-1", "0"},
//                {"Rotation", "0", "0", "0", "0"},
//                {"Rotation", "0", "1", "0", "0.05", "0", "1"},
//        };
        ///--------- Comment out for RotateUpTransformer ---------
//        final String[][] params = {
//                {"TranslationX", "-1", "1", "0", "0", "-1", "1"},
//
//                {"PivotX", "-1", "1", "0.5", "0.5", "-1", "1"},
//                {"PivotY", "-1", "1", "0", "0", "-1", "1"},
//
//                {"Rotation", "-1", "0", "0.05", "0", "-1", "0"},
//                {"Rotation", "0", "0", "0", "0"},
//                {"Rotation", "0", "1", "0", "-0.05", "0", "1"},
//        };
        ///--------- Comment out for ZoomInTransformer ---------
//        final String[][] params = {
//                ///Counteract the default slide transition
//                ///same as: page.setTranslationX( -page.getWidth() * position);
//                {"TranslationX", "-1", "0", "1", "0"},
////                {"TranslationX", "0", "0", "0", "0"},///optional
//                {"TranslationX", "0", "1", "0", "-1"},
//
//                {"PivotX", "-1", "1", "0.5", "0.5"},
//                {"PivotY", "-1", "1", "0.5", "0.5"},
//
//                {"ScaleX", "-1", "0", "0", "1"},
////                {"ScaleX", "0", "0", "1", "1"},
//                {"ScaleX", "0", "1", "1", "0"},
//
//                {"ScaleY", "-1", "0", "0", "1"},
////                {"ScaleY", "0", "0", "1", "1"},
//                {"ScaleY", "0", "1", "1", "0"},
//
//                {"Alpha", "-1", "0", "0", "1"},
////                {"Alpha", "0", "0", "1", "1"},
//                {"Alpha", "0", "1", "1", "0"},
//        };
        ///--------- Comment out for ZoomOutTransformer ---------
//        final String[][] params = {
//                ///Counteract the default slide transition
//                ///same as: page.setTranslationX( -page.getWidth() * position);
//                {"TranslationX", "-1", "0", "1", "0"},
////                {"TranslationX", "0", "0", "0", "0"},///optional
//                {"TranslationX", "0", "1", "0", "-1"},
//
//                {"PivotX", "-1", "1", "0.5", "0.5"},
//                {"PivotY", "-1", "1", "0.5", "0.5"},
//
//                {"ScaleX", "-1", "0", "2", "1"},
////                {"ScaleX", "0", "0", "1", "1"},
//                {"ScaleX", "0", "1", "1", "2"},
//
//                {"ScaleY", "-1", "0", "2", "1"},
////                {"ScaleY", "0", "0", "1", "1"},
//                {"ScaleY", "0", "1", "1", "2"},
//
//                {"Alpha", "-1", "0", "0", "1"},
////                {"Alpha", "0", "0", "1", "1"},
//                {"Alpha", "0", "1", "1", "0"},
//        };

        final CommonTransformer transformer = new CommonTransformer(params);

        ///test for OnTransformListener
//        transformer.setOnTransformListener(new BaseTransformer.OnTransformListener() {
//            @Override
//            public void onTransform(View page, float position) {
//                page.setZ(-Math.abs(position));///example
//                page.setAlpha(Math.abs(1-position));///example
//            }
//        });

        viewPager.setPageTransformer(false, transformer);
    }

    class MyPagerAdapter extends PagerAdapter {
        private List<View> viewList;

        public MyPagerAdapter(List<View> viewList) {
            this.viewList = viewList;
        }

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;///https://developer.android.com/reference/android/support/v4/view/PagerAdapter.html
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position),0);///@param index the position at which to add the child or -1 to add last？？？？？0
            return viewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }
    }
}
