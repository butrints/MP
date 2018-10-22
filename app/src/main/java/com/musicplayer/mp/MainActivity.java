package com.musicplayer.mp;


import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    private SectionsPageAdapter msectionsPageAdapter;
    private ViewPager mviewPager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mviewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mviewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mviewPager);


    }
    private void setupViewPager(ViewPager viewPager)
    {
        SectionsPageAdapter view = new SectionsPageAdapter(getSupportFragmentManager());
        view.addFragment(new MusicListFragment(),"Music List");
        view.addFragment(new Tab2Fragment(),"Playlist");
        view.addFragment(new Tab3Fragment(),"Albums");
        viewPager.setAdapter(view);
    }





}
