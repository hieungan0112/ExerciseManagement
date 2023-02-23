package edu.uef.baitap;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterNopBai extends RecyclerView.Adapter<AdapterNopBai.Viewholider> {

    Context context;
    List<BaiTap> list;

    public AdapterNopBai(Context context, List<BaiTap> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_baitap, parent, false);
        Viewholider viewHolder = new Viewholider(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholider holder, int position) {
        BaiTap baiTap = list.get(position);

        holder.txtTenMon.setText(baiTap.getTenMonHoc());
        holder.txtTenBaiTap.setText(baiTap.getTenBaiTap());
        holder.txtHan.setText(baiTap.getThoiHan());
        holder.txtLoai.setText(baiTap.getChungLoai());
        holder.txtDate.setText("Ngày nộp:"+baiTap.getDate());
        holder.txtNguoiNop.setText("ID Người nộp:"+baiTap.getIdUser());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MainActivity6.class);
                intent.putExtra("obj", baiTap);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholider extends RecyclerView.ViewHolder {
        TextView txtTenMon, txtTenBaiTap, txtDate, txtHan, txtLoai,txtNguoiNop;

        public Viewholider(@NonNull View itemView) {
            super(itemView);
            txtTenMon = itemView.findViewById(R.id.txtTenMonHoc);
            txtTenBaiTap = itemView.findViewById(R.id.txtTenBaiTap);
            txtDate = itemView.findViewById(R.id.txtThoiGianNop);
            txtHan = itemView.findViewById(R.id.txtThoiHan);
            txtLoai = itemView.findViewById(R.id.txtLoai);
            txtNguoiNop=itemView.findViewById(R.id.nguoinop);
        }
    }
}