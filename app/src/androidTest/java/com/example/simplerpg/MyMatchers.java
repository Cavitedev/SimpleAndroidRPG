package com.example.simplerpg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Some additional matchers
 */
public class MyMatchers {

    // Obscure code from StackOverflow https://stackoverflow.com/a/39756832/14559140

    /**
     * Matcher that returns the index-element that matches the condition
     * @param matcher Matcher to retrieve multiple elements
     * @param index which of those elements to return, first is 0
     * @return The view that matches with the filter and the position
     */
    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }
    public static Matcher<View> withDrawable(final int drawableId) {
        return new BoundedMatcher<View, ImageView>(ImageView.class) {

            @Override
            public boolean matchesSafely(ImageView iv) {
                if (drawableId == 0) {
                    return iv.getDrawable() == null;
                } else if (iv.getDrawable() == null) {
                    return false;
                }
                Drawable d = ApplicationProvider.getApplicationContext().getDrawable(drawableId);
                if (d instanceof BitmapDrawable && iv.getDrawable() instanceof BitmapDrawable) {
                    BitmapDrawable d1 = (BitmapDrawable) d;
                    BitmapDrawable d2 = (BitmapDrawable) iv.getDrawable();

                    Bitmap b1 = d1.getBitmap();
                    Bitmap b2 = d2.getBitmap();

                    return b1.sameAs(b2);
                }

                return iv.getDrawable().getConstantState().equals(d.getConstantState());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with drawable: ");
            }
        };
    }
}
