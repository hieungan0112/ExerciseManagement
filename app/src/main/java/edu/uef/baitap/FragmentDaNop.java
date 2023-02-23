package edu.uef.baitap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import edu.uef.baitap.databinding.FragmentDaNopBinding;


public class FragmentDaNop extends Fragment {

    FragmentDaNopBinding binding;
    Database db = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDaNopBinding.inflate(inflater, container, false);

        db = new Database(getContext());
        List<BaiTap> contacts = db.getAllContactsBaiTap1();
        AdapterNopBai adapterbaiTap = new AdapterNopBai(getContext(), contacts);


        binding.recyclerView.setAdapter(adapterbaiTap);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setHasFixedSize(true);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        int[] i={0,0,0};
        List<BaiTap> contacts = db.getAllContactsBaiTap1();
        AdapterNopBai adapterbaiTap = new AdapterNopBai(getContext(), contacts);
        binding.btnsapxep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i[1]!=0)
                {
                    i[2]++;
                    if(i[2]%2!=0)
                    {
                        String selectQuery = "SELECT  * FROM NopBai WHERE tenmonhoc LIKE '%" +binding.edtTimKiem.getText().toString()+ "%'" +
                                "OR tenbaitap LIKE '%" +binding.edtTimKiem.getText().toString()+ "%'OR date LIKE '%" +binding.edtTimKiem.getText().toString()+ "%'" +
                                "OR chungLoai LIKE '%" +binding.edtTimKiem.getText().toString()+ "%'ORDER BY chungLoai";
                        List<BaiTap> contacts1 = db.getSapxepBaiTap(selectQuery);
                        AdapterbaiTap adapterbaiTap = new AdapterbaiTap(getContext(), contacts1);
                        binding.recyclerView.setAdapter(adapterbaiTap);
                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.recyclerView.setHasFixedSize(true);
                    }
                    else {
                        String selectQuery = "SELECT  * FROM NopBai WHERE tenmonhoc LIKE '%" +binding.edtTimKiem.getText().toString()+ "%'" +
                                "OR tenbaitap LIKE '%" +binding.edtTimKiem.getText().toString()+ "%'OR date LIKE '%" +binding.edtTimKiem.getText().toString()+ "%'" +
                                "OR chungLoai LIKE '%" +binding.edtTimKiem.getText().toString()+ "%'ORDER BY date";
                        List<BaiTap> contacts1 = db.getSapxepBaiTap(selectQuery);
                        AdapterbaiTap adapterbaiTap = new AdapterbaiTap(getContext(), contacts1);
                        binding.recyclerView.setAdapter(adapterbaiTap);
                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.recyclerView.setHasFixedSize(true);
                    }
                }
                else{
                    i[0]++;
                    if(i[0] %2!=0)
                    {
                        String selectQuery = "SELECT  * FROM NopBai ORDER BY chungLoai";
                        List<BaiTap>contacts1=db.getSapxepBaiTap(selectQuery);
                        AdapterbaiTap adapterbaiTap = new AdapterbaiTap(getContext(), contacts1);
                        binding.recyclerView.setAdapter(adapterbaiTap);
                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.recyclerView.setHasFixedSize(true);
                    }
                    else{
                        String selectQuery = "SELECT  * FROM NopBai ORDER BY date";
                        List<BaiTap>contacts1=db.getSapxepBaiTap(selectQuery);
                        AdapterbaiTap adapterbaiTap = new AdapterbaiTap(getContext(), contacts1);
                        binding.recyclerView.setAdapter(adapterbaiTap);
                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.recyclerView.setHasFixedSize(true);
                    }



                }}
        });

        binding.btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i[1]++;
                if(binding.edtTimKiem.getText()!=null) {
                    String selectQuery = "SELECT  * FROM BaiTap WHERE tenmonhoc LIKE '%" +binding.edtTimKiem.getText().toString()+ "%'" +
                            "OR tenbaitap LIKE '%" +binding.edtTimKiem.getText().toString()+ "%'OR date LIKE '%" +binding.edtTimKiem.getText().toString()+ "%'" +
                            "OR chungLoai LIKE '%" +binding.edtTimKiem.getText().toString()+ "%'";
                    List<BaiTap> contacts1 = db.getSapxepBaiTap(selectQuery);
                    AdapterbaiTap adapterbaiTap = new AdapterbaiTap(getContext(), contacts1);
                    binding.recyclerView.setAdapter(adapterbaiTap);
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.recyclerView.setHasFixedSize(true);
                }else
                    Toast.makeText(getContext(),"Nhập thông tin để tìm kiếm",Toast.LENGTH_LONG).show();
            }
        });

        binding.recyclerView.setAdapter(adapterbaiTap);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setHasFixedSize(true);
    }

}
