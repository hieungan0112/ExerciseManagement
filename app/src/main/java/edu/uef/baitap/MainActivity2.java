package edu.uef.baitap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.List;

import edu.uef.baitap.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {
Database db;
    Button chonHinh,btnDK;
    ImageView avatar;


    EditText edtUser,edtPass,editHoten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        db=new Database(this);
        List<User> contacts = db.getAllContactsUser();

        btnDK=(Button)findViewById(R.id.btnDangKy);
        edtUser=(EditText) findViewById(R.id.edtUserName);
        edtPass=(EditText) findViewById(R.id.edtPassword);

        editHoten=(EditText)findViewById(R.id.edtHoten);
        avatar=(ImageView)findViewById(R.id.imageView);

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent maychuphinh= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(maychuphinh,888);
            }
        });
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int dem = 0;

                for (User cn : contacts) {
                    String log = "Id: " + cn.getId() + " ,Name: " + cn.getUsername() + " ,pass: " +
                            cn.getPassword()+" ten: "+cn.getHoTen();
                    // Writing Contacts to log

                    if (cn.getUsername().equals(edtUser.getText().toString())
                            && cn.getPassword().equals(edtPass.getText().toString())) {
                        Toast.makeText(MainActivity2.this, "Tên Đăng Nhập Đã Tồn tại", Toast.LENGTH_SHORT).show();
                        break;
                    } else {
                        dem++;
                    }
                    Log.d("Name: ", log);

                }
                Log.d("TAG", "onClick: " + dem);

                if (dem == contacts.size()) {
                    db.InsertUser(edtUser.getText().toString(),
                            edtPass.getText().toString(),
                            editHoten.getText().toString()
                            ,image(avatar)

                    );
                    Toast.makeText(MainActivity2.this,"Đăng Kí Thành Công",Toast.LENGTH_LONG).show();
                    finish();

                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 888 && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            avatar.setImageBitmap(photo);
        }
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