package edu.uef.baitap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.uef.baitap.databinding.ActivityMain4Binding;
import edu.uef.baitap.databinding.ActivityMainBinding;

public class MainActivity4 extends AppCompatActivity {

    ActivityMain4Binding binding;
    ActivityMainBinding binding1;
    Database db = null;
    int p = 0;


    List<BaiTap> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new Database(this);

        Intent intent=getIntent();
        int id_User=intent.getIntExtra("id_user",0);


        String[] arraySpinner = new String[]{
                "essay", "homework"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);


        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                p = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        binding.image.setVisibility(View.INVISIBLE);

        binding.btnTaoBaiTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contacts.clear();
                contacts=db.getAllContactsBaiTap();
                String timeStamp = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(Calendar.getInstance().getTime());
                db.addBaiTap(new BaiTap(binding.edtTenMonHoc.getText().toString(), timeStamp,
                        binding.edtTenBaiTap.getText().toString(),binding.edtThoiGianHan.getText().toString(), arraySpinner[p],0,id_User));
                        finish();
            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
    public byte[] image(ImageView h)
    {
        BitmapDrawable drawable=(BitmapDrawable) h.getDrawable();
        Bitmap bmp=drawable.getBitmap();
        ByteArrayOutputStream stream =new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] b=stream.toByteArray();
        return  b;
    }

}