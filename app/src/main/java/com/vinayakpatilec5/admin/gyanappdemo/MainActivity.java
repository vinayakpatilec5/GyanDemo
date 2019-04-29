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
        NestedScrollView scrollView = (NestedScrollView) findViewById(R.id.scrollView);
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
                float scroll = nestedScrollView.getScrollY();
                if(scroll>600){
                    setAppBarLayoutHeight(0);
                }else {
                    setAppBarLayoutHeight(AppBarLayout.LayoutParams.WRAP_CONTENT);
                }
            }
        });
    }

    private void setAppBarLayoutHeight(int height){
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)appBarLayout.getLayoutParams();
        int  h1 = appBarLayout.getHeight();
        if(((appBarLayout.getHeight() > 0)&&(height==0))||((appBarLayout.getHeight()==0)&&(height == AppBarLayout.LayoutParams.WRAP_CONTENT))){
            lp.height = height;
            appBarLayout.setLayoutParams(lp);
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
