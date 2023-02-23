package edu.uef.baitap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Database db=null;

    layID id_user=new layID();


    int dem = 0;
    List<User> contacts = new ArrayList<>();
    Button btnDN,btnDK;
    EditText edtUser,edtPass;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=new Database(this);
        contacts.clear();
        contacts=db.getAllContactsUser();

        setContentView(R.layout.activity_main);
        btnDN=(Button)findViewById(R.id.btnDangNhap);
        btnDK=(Button)findViewById(R.id.btnDangKy);
        edtUser=(EditText) findViewById(R.id.edtUserName);
        edtPass=(EditText) findViewById(R.id.edtPassword);
        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (User cn : contacts) {
                    String log = "Id: " + cn.getId() + " ,Name: " + cn.getUsername() + " ,Phone: " +
                            cn.getPassword();
                    // Writing Contacts to log
                    if (cn.getUsername().equals(edtUser.getText().toString())
                            && cn.getPassword().equals(edtPass.getText().toString()))
                    {
                        id=cn.getId();
                          id_user.setId(cn.getId());
                          db.addLayID(id_user);
                        Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                        intent.putExtra("id_user",id);
                        startActivity(intent);
                        break;
                    } else {
                        dem++;
                    }


                    Log.d("Name: ", log);
                }

                if (dem == contacts.size()) {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }


        });
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

    }


    protected void onResume() {
        super.onResume();
        contacts.clear();
        contacts.addAll(db.getAllContactsUser());
    }


    public int getId() {
        return id;
    }
}