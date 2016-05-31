package com.yw.tab03_fragment;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener, HomeFragment.OnFragmentInteractionListener, NearbyFragment.OnFragmentInteractionListener, RelexFragment.OnFragmentInteractionListener, MineFragment.OnFragmentInteractionListener, ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private PagerAdapter mAdapter;
    private List<Fragment> mFragments;

    private LinearLayout mTabHome;
    private LinearLayout mTabNearby;
    private LinearLayout mTabRelex;
    private LinearLayout mTabMine;

    private ImageButton mImgHome;
    private ImageButton mImgNearby;
    private ImageButton mImgRelex;
    private ImageButton mImgMine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
        setSelect(1); //没有联动
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        mTabHome = (LinearLayout)findViewById(R.id.id_tab_home);
        mTabNearby = (LinearLayout)findViewById(R.id.id_tab_nearby);
        mTabRelex = (LinearLayout)findViewById(R.id.id_tab_relex);
        mTabMine = (LinearLayout)findViewById(R.id.id_tab_mine);

        mImgHome = (ImageButton)findViewById(R.id.id_tab_home_img);
        mImgNearby = (ImageButton)findViewById(R.id.id_tab_nearby_img);
        mImgRelex = (ImageButton)findViewById(R.id.id_tab_relex_img);
        mImgMine = (ImageButton)findViewById(R.id.id_tab_mine_img);

        mFragments = new ArrayList<>();
        Fragment tab01 = HomeFragment.newInstance(null, null);
        Fragment tab02 = NearbyFragment.newInstance(null, null);
        Fragment tab03 = RelexFragment.newInstance(null, null);
        Fragment tab04 = MineFragment.newInstance(null, null);
        mFragments.add(tab01);
        mFragments.add(tab02);
        mFragments.add(tab03);
        mFragments.add(tab04);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mViewPager.setAdapter(mAdapter);
    }

    private void initEvent() {
        mTabHome.setOnClickListener(this);
        mTabNearby.setOnClickListener(this);
        mTabRelex.setOnClickListener(this);
        mTabMine.setOnClickListener(this);

        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_tab_home:
                setSelect(0);
                break;
            case R.id.id_tab_nearby:
                setSelect(1);
                break;
            case R.id.id_tab_relex:
                setSelect(2);
                break;
            case R.id.id_tab_mine:
                setSelect(3);
                break;

        }
    }

    /**
     * 将图片设置为亮色，并切换内容区域
     * @param i
     */
    private void setSelect(int i) {
        setTab(i);
        mViewPager.setCurrentItem(i);
    }

    private void setTab(int i) {
        resetImgs();
        switch (i){
            case 0:
                mImgHome.setImageResource(R.mipmap.tab_bar_home_selected);
                break;
            case 1:
                mImgNearby.setImageResource(R.mipmap.tab_bar_nearby_selected);
                break;
            case 2:
                mImgRelex.setImageResource(R.mipmap.tab_bar_relex_selected);
                break;
            case 3:
                mImgMine.setImageResource(R.mipmap.tab_bar_mine_selected);
                break;
        }
    }

    /**
     * 切换图片至暗色
     */
    private void resetImgs() {
        mImgHome.setImageResource(R.mipmap.tab_bar_home_un_selected);
        mImgNearby.setImageResource(R.mipmap.tab_bar_nearby_un_selected);
        mImgRelex.setImageResource(R.mipmap.tab_bar_relex_un_selected);
        mImgMine.setImageResource(R.mipmap.tab_bar_mine_un_selected);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int current = mViewPager.getCurrentItem();
        setTab(current);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
