package edu.uef.baitap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.uef.baitap.databinding.ActivityMain5Binding;

public class MainActivity5 extends AppCompatActivity {
    ActivityMain5Binding binding;

    final  int REQUET_CHOOSE_PHOTO=321;
    Database db = null;
    List<layID> contacts = new ArrayList<>();
    layID id_user=new layID();
    BaiTap baiTap;
    int p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new Database(this);

        contacts=db.getallID();
        for(layID id:contacts)
        {
            id_user.setId(id.getId());
        }



        String[] arraySpinner = new String[]{
                "essay", "homework"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);
        Intent intent = getIntent();
        if (intent != null) {
            baiTap = (BaiTap) intent.getSerializableExtra("obj");
        }

        binding.edtTenMonHoc.setText(baiTap.getTenMonHoc());
        binding.edtTenBaiTap.setText(baiTap.getTenBaiTap());
        binding.edtThoiGianHan.setText(baiTap.getThoiHan());
        int position;

        if (baiTap.getChungLoai().equals("essay")) {
            position = 0;
        } else {
            position = 1;
        }


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


        binding.spinner.setSelection(position);

        binding.btnEditBaiTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BaiTap baiTapNew = new BaiTap(baiTap.getId(),binding.edtTenMonHoc.getText().toString(), baiTap.getDate(),
                        binding.edtTenBaiTap.getText().toString(), binding.edtThoiGianHan.getText().toString(), arraySpinner[p],0,baiTap.getIdUser());

                db.updateBaiTap(baiTapNew);
                onBackPressed();


            }
        });
        binding.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(baiTap.getIdUser()!=id_user.getId())
                    Toast.makeText(MainActivity5.this,"Bạn Không thể xóa bài của người khác!!",Toast.LENGTH_LONG).show();
                else{
                db.deleteBaiTap(baiTap);
                onBackPressed();
                }

            }
        });

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.imgHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUET_CHOOSE_PHOTO);

            }
        });
        if(baiTap.getIdUser()!=id_user.getId())
        {
            binding.btnEditBaiTap.setEnabled(false);

            binding.edtTenBaiTap.setEnabled(false);
            binding.edtTenMonHoc.setEnabled(false);
            binding.edtThoiGianHan.setEnabled(false);
            binding.spinner.setEnabled(false);
        }
        binding.btnActack.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View v) {
            String timeStamp = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(Calendar.getInstance().getTime());
            BaiTap baiTapNew = new BaiTap(binding.edtTenMonHoc.getText().toString(), timeStamp,
                    binding.edtTenBaiTap.getText().toString(), binding.edtThoiGianHan.getText().toString(), arraySpinner[p],1,id_user.getId());

            db.addBaiTap1(baiTapNew);

            onBackPressed();
        }
        });

    }

    String base64 = "";

    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUET_CHOOSE_PHOTO) {

                try {
                    Uri imageUri=data.getData();

                    InputStream is=getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap= BitmapFactory.decodeStream(is);
                    binding.imgHinh.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


//                Bitmap bitmap = null;
//                try {
//                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                base64 = ImageUtil.convert(bitmap);
            }
        }

    }


    private static final int REQUEST_PHOTO_GALLERY = 100;

    public void callGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_PHOTO_GALLERY);
    }

    public static final int SELECT_PICTURE = 123;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == SELECT_PICTURE) {
                callGallery();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private String selectedImagePath;

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
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