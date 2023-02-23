package edu.uef.baitap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.uef.baitap.databinding.ActivityMain7Binding;
import edu.uef.baitap.databinding.ActivityMainBinding;

public class MainActivity7 extends AppCompatActivity {
ActivityMain7Binding binding;
MainActivity mainActivity;
    List<User> contacts = new ArrayList<>();
    Database db=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain7Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent =getIntent();
        mainActivity=new MainActivity();
        int id =intent.getIntExtra("id_user",-1);

        db=new Database(this);
        contacts.clear();
        contacts=db.getAllContactsUser();
        User cn1=new User();
        for (User cn : contacts) {
            if(cn.getId()==id)
                cn1=new User(id,cn.getUsername(),cn.getPassword(),cn.getHoTen(),cn.getImage());}

        binding.edtTK.setText(cn1.getUsername());
        binding.edtMK.setText(cn1.getPassword());
        binding.edtHoten.setText(cn1.getHoTen());
        Bitmap bitmap= BitmapFactory.decodeByteArray(cn1.getImage(),0,cn1.getImage().length);
        binding.avatar.setImageBitmap(bitmap);
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            User cn2=new User(id,binding.edtTK.getText().toString(),binding.edtMK.getText().toString(),binding.edtHoten.getText().toString(),image(binding.avatar));
                db.uppdateUser(cn2);
                onBackPressed();
            }
        });
        binding.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent maychuphinh= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(maychuphinh,888);

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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 888 && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            binding.avatar.setImageBitmap(photo);
        }
    }
}