package com.vinayakpatilec5.admin.gyanappdemo;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    AppBarLayout appBarLayout;
    boolean appBarVisible = true;
    NestedScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbarVisibility();
        hideAppBarOnScroll();
    }

    private void setToolbarVisibility(){
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        final Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int  scrollRange = appBarLayout.getTotalScrollRange();
                if (scrollRange + verticalOffset == 0) {
                    toolbar.setVisibility(View.VISIBLE);
                } else {
                    toolbar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void hideAppBarOnScroll(){
        scrollView = (NestedScrollView) findViewById(R.id.scrollView);
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
                float scroll = nestedScrollView.getScrollY();
                if(scroll>1000){
                    setAppBarPosition(false);
                }else {
                    setAppBarPosition(true);
                }
            }
        });
    }

    private void setAppBarPosition(boolean showAppbar){
        int  h1 = appBarLayout.getHeight();
        if(appBarVisible&&(!showAppbar)){
            appBarVisible = false;
            appBarLayout.animate().translationY(-h1).setDuration(400);
            scrollView.animate().translationY(-h1).setDuration(400);
        }else if((!appBarVisible)&&showAppbar) {
            appBarLayout.animate().translationY(0).setDuration(400);
            scrollView.animate().translationY(0).setDuration(400);
            appBarVisible = true;
        }
    }

    public void openDrawer(View view){
        showMessage("Drawer icon tapped");
    }

    public void tappedHomeButton(View view){
        showMessage("Home icon tapped");
    }

    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
