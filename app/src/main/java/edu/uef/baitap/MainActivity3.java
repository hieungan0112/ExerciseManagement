package edu.uef.baitap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import edu.uef.baitap.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {
    ActivityMain3Binding binding;
    Database db=null;
    List<BaiTap> contacts = new ArrayList<>();



    int id;
    layID a;




    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addControl();
        db=new Database(this);
        contacts.clear();
        contacts=db.getAllContactsBaiTap();

        FragmentGiaoBai fragmentGiaoBai=new FragmentGiaoBai();
        Intent intent=getIntent();
       id=intent.getIntExtra("id_user",0);
        a=new layID(id);







        binding.floatButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {


                Intent intent = new Intent(MainActivity3.this,MainActivity4.class);
                intent.putExtra("id_user",id);
                  startActivity(intent);


            }
        });

    }

    private void addControl() {
        FragmentManager manager = getSupportFragmentManager();
        PagerAdapter adapter = new PagerAdapter(manager);
        binding.viewPage.setAdapter(adapter);
        binding.tablayout.setupWithViewPager(binding.viewPage);
        binding.viewPage.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tablayout));
        binding.tablayout.setTabsFromPagerAdapter(adapter);//deprecated
        binding.tablayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(binding.viewPage));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_context,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.user:
            {

                Intent intent =new Intent(MainActivity3.this,MainActivity7.class);
                intent.putExtra("id_user",id);
                startActivity(intent);
            }
            break;
            case R.id.Dangxuat:
            {
                Intent intent =new Intent(MainActivity3.this,MainActivity.class);
                db.deletelayID(a);
                startActivity(intent);
            }
            break;
        }

        return super.onOptionsItemSelected(item);
    }
    public int getId() {
        return id;
    }


}
