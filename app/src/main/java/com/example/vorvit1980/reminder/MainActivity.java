package com.example.vorvit1980.reminder;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.pm.ActivityInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.vorvit1980.reminder.adapter.TabAdapter;
import com.example.vorvit1980.reminder.dialog.AddingTaskDialogFragment;
import com.example.vorvit1980.reminder.fragment.CurrentTaskFragment;
import com.example.vorvit1980.reminder.fragment.DoneTaskFragment;
import com.example.vorvit1980.reminder.fragment.SplashFragment;
import com.example.vorvit1980.reminder.model.ModelTask;

public class MainActivity extends AppCompatActivity implements AddingTaskDialogFragment.AddingTaskListener {

    FragmentManager fragmentManager;

    PreferenceHelper preferenceHelper;

    TabAdapter tabadapter;

    CurrentTaskFragment currentTaskFragment;
    DoneTaskFragment doneTaskFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// только portrait

        PreferenceHelper.getInstance().init(getApplicationContext());
        preferenceHelper = PreferenceHelper.getInstance();

        fragmentManager = getFragmentManager();
        runSplash();
        setUI();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem splashItem = menu.findItem(R.id.action_splash);
        splashItem.setChecked(preferenceHelper.getBoolean(PreferenceHelper.SPLASH_IS_INVISIBLE));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_splash) {
            item.setChecked(!item.isChecked());
            preferenceHelper.putBoolean(PreferenceHelper.SPLASH_IS_INVISIBLE, item.isChecked());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void runSplash() {
        if (!preferenceHelper.getBoolean(PreferenceHelper.SPLASH_IS_INVISIBLE)) {


            SplashFragment splashFragment = new SplashFragment();

            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, splashFragment)
                    .addToBackStack(null)
                    .commit();

        }
    }

    private void setUI(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar!=null){
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
            setSupportActionBar(toolbar);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.current_task));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.done_task));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        tabadapter = new TabAdapter(fragmentManager, 2);

        viewPager.setAdapter(tabadapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        currentTaskFragment= (CurrentTaskFragment) tabadapter.getItem(TabAdapter.CURRENT_TASK_FRAGMENT_POSITION);
        doneTaskFragment = (DoneTaskFragment) tabadapter.getItem(TabAdapter.DONE_TASK_FRAGMENT_POSITION);

        FloatingActionButton fab= (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment addingTaskDialogFragment = new AddingTaskDialogFragment();
                addingTaskDialogFragment.show(fragmentManager,"AddingTaskDialogFragment");
            }
        });

    }

    @Override
    public void onTaskAdded(ModelTask newTask) {

        currentTaskFragment.addTask(newTask);

    }

    @Override
    public void onTaskAddingCancel() {
        Toast.makeText(this,"Task adding canceled.", Toast.LENGTH_LONG).show();
    }
}
