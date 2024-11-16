package com.example.asm_and103.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_and103.APIService;
import com.example.asm_and103.HomeAcitvity;
import com.example.asm_and103.Models.ProductModel;
import com.example.asm_and103.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterProductRecy extends RecyclerView.Adapter<AdapterProductRecy.ProductHolder> {

    Context context;
    List<ProductModel> list;
    APIService apiService;

    public AdapterProductRecy(Context context, List<ProductModel> list) {
        this.context = context;
        this.list = list;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(APIService.class);
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.item_cake,parent,false);

        return new ProductHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        ProductModel product = list.get(position);
        holder.tvCakeName.setText(list.get(position).getProductName());
        holder.tvCakePrice.setText(String.valueOf(list.get(position).getPrice()) + " đ");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogUpdate(apiService,product,list,position);
            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa người dùng")
                        .setMessage("Bạn có chắc chắn muốn xóa người dùng này không?")
                        .setPositiveButton("Xóa", (dialog, which) -> {
                            Call<List<ProductModel>> xoaXe = apiService.deleteProduct(product.get_id());
                            Log.d("zzzz", "onBindViewHolder: " + product.get_id());

                            xoaXe.enqueue(new Callback<List<ProductModel>>() {
                                @Override
                                public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                                    if (response.isSuccessful() && response.body() != null) {
                                        // Xóa sản phẩm khỏi danh sách hiện tại
                                        list.clear();
                                        // Cập nhật lại RecyclerView
                                        list.addAll(response.body());
                                        notifyDataSetChanged();
                                        Toast.makeText(context, "Xóa Thành Công !!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(context, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                                    Toast.makeText(context, "Không thể kết nối API: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.e("API Error", "Error message: " + t.getMessage(), t);
                                }
                            });
                        })
                        .setNegativeButton("Hủy", (dialog, which) -> {
                            dialog.dismiss();
                        });
                builder.create().show();


                return false;
            }
        });


        // Xử lý sự kiện khi người dùng nhấn vào item để xóa
//        holder.itemView.setOnClickListener(v -> {
//            // Tạo API call để xóa sản phẩm
//            Call<List<ProductModel>> xoaXe = apiService.deleteProduct(product.get_id());
//            Log.d("zzzz", "onBindViewHolder: " + product.get_id());
//
//            xoaXe.enqueue(new Callback<List<ProductModel>>() {
//                @Override
//                public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
//                    if (response.isSuccessful() && response.body() != null) {
//                        // Xóa sản phẩm khỏi danh sách hiện tại
//                        list.remove(position);
//                        // Cập nhật lại RecyclerView
//                        notifyItemRemoved(position);
//                        Toast.makeText(context, "Xóa Thành Công !!", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(context, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<List<ProductModel>> call, Throwable t) {
//                    Toast.makeText(context, "Không thể kết nối API: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                    Log.e("API Error", "Error message: " + t.getMessage(), t);
//                }
//            });
//        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ProductModel product = list.get(position);
//
//                Call<List<ProductModel>> xoaXe = apiService.deleteProduct(product.get_id());
//
//                xoaXe.enqueue(new Callback<List<ProductModel>>() {
//                    @Override
//                    public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
//                        if(response.isSuccessful()){
//                            list.clear();
//                            list.addAll(response.body());
//                            notifyDataSetChanged();
//                            Toast.makeText(context, "Xóa Thành Công !!", Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            Toast.makeText(context, "Xoá Fail", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<ProductModel>> call, Throwable t) {
//
//                    }
//                });
//            }
//        });

    }

    void openDialogUpdate(APIService apiService,ProductModel productModel,List<ProductModel> list,int positon){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_update_product,null);
        builder.setView(v);
        AlertDialog dialog = builder.create();
        dialog.show();

        //anh xa
        EditText edtNameProduct = v.findViewById(R.id.edtNameProduct);
        EditText edtCanNang = v.findViewById(R.id.edtCanNang);
        Button btnThem = v.findViewById(R.id.btnThem);

        edtNameProduct.setText(productModel.getProductName());
        edtCanNang.setText(productModel.getPrice()+"");

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtNameProduct.getText().toString();
                String canNang = edtCanNang.getText().toString();
                if(name.isEmpty() || canNang.isEmpty()){
                    Toast.makeText(context, "Vui lòng không để trống !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(name.length() > 20 || canNang.length() > 10){
                    Toast.makeText(context, "Vui lòng không nhập Tên và Giá quá dài !!", Toast.LENGTH_SHORT).show();
                    return;
                }

                productModel.setProductName(edtNameProduct.getText().toString());
                productModel.setPrice(Double.parseDouble(String.valueOf(edtCanNang.getText().toString())));

                Call<List<ProductModel>> callAddProduct = apiService.updateProduct(list.get(positon).get_id(),productModel);

                callAddProduct.enqueue(new Callback<List<ProductModel>>() {
                    @Override
                    public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                        if(response.isSuccessful()){
                            list.clear();
                            list.addAll(response.body());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Update Thành Công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                        else{
                            Toast.makeText(context, "Update Không Thành Công", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProductModel>> call, Throwable t) {

                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        ImageView ivCakeImage;
        TextView tvCakeName,tvCakePrice;
        ImageButton ImageButton;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            ivCakeImage = itemView.findViewById(R.id.ivCakeImage);
            tvCakeName = itemView.findViewById(R.id.tvCakeName);
            tvCakePrice = itemView.findViewById(R.id.tvCakePrice);
            ImageButton = itemView.findViewById(R.id.addButton);
        }
    }
}
