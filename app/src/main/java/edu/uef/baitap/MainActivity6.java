package edu.uef.baitap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import edu.uef.baitap.databinding.ActivityMain6Binding;

public class MainActivity6 extends AppCompatActivity {

    Database db = null;
    ActivityMain6Binding binding;
    String id ;



    User user;
    BaiTap nopBai;
    int p;

    public static final int SELECT_PICTURE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain6Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db=new Database(this);

        Intent intent = getIntent();


        if (intent != null){
            nopBai= (BaiTap)  intent.getSerializableExtra("obj");
        }


        ActivityCompat.requestPermissions(MainActivity6.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, SELECT_PICTURE);


        binding.edtTenMonHoc.setText("Tên Môn Học:"+nopBai.getTenMonHoc());
        binding.edtTenBaiTap.setText("Tên Bài Tập:"+nopBai.getTenBaiTap());
        binding.edtThoiGianHan.setText("Thời hạn:"+nopBai.getThoiHan());
        binding.edtThoiGianNop.setText("Ngày nộp:"+nopBai.getDate());
        binding.chungloai.setText("Loại bài tập:"+nopBai.getChungLoai());


        binding.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteBaiTap1(nopBai);
                finish();
            }
        });

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            if (requestCode == SELECT_PICTURE) {
//                PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
//                try {
//                    photoView.setImageBitmap(BitmapFactory.decodeStream(getContentResolver().openInputStream(Uri.parse(id))));
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
}