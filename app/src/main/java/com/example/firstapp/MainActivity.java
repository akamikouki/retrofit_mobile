package com.example.firstapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import android.os.Bundle;
import com.example.firstapp.fragments.Register;
import com.example.firstapp.fragments.login;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "http://192.168.1.35:3001";
    private login l;
    private Register r;
    Toolbar toolbar;
    TabLayout tab;
    androidx.viewpager.widget.ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        l = new login();
        r = new Register();

        toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);

        tab = findViewById(R.id.tab);
        viewPager = findViewById(R.id.vpager);
        tab.setupWithViewPager(viewPager);
        ViewPagr viewPagr =  new ViewPagr(getSupportFragmentManager(),0);
        viewPagr.addFragment(l,"Login");
        viewPagr.addFragment(r,"Register");
        viewPager.setAdapter(viewPagr);

    }
}
    class ViewPagr extends FragmentPagerAdapter{

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> fragmentTitle = new ArrayList<>();

    public ViewPagr(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void addFragment(Fragment fragment, String title) {
        fragments.add(fragment);
        fragmentTitle.add(title);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
    @Override
    public int getCount() {
        return fragments.size();
    }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);

        }
    }













