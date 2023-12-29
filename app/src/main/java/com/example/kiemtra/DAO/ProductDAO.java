package com.example.kiemtra.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.kiemtra.database.DatabaseHelper;
import com.example.kiemtra.models.SanPham;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    DatabaseHelper dbHelper;
    public ProductDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }
    public List<SanPham> GetAll()
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<SanPham> listProduct = new ArrayList<>();
        String query = "SELECT * FROM product";
        Cursor c = db.rawQuery(query, null);
        while (c.moveToNext())
        {
            SanPham temp = new SanPham();
            temp.setId(c.getInt(0));
            temp.setName(c.getString(1));
            temp.setPrice(c.getFloat(2));
            temp.setImage(c.getString(3));
            listProduct.add(temp);
        }
        c.close(); // Đóng Cursor
        db.close(); // Đóng kết nối với cơ sở dữ liệu

        return listProduct;
    }
    public void add(SanPham p) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", p.getName());
        values.put("price", p.getPrice());
        values.put("image", p.getImage());
        db.insert("product", null, values);
        db.close(); // Đóng kết nối với cơ sở dữ liệu

    }
    public void Delete(int productId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
//c1: sử dụng delete
        db.delete("product", "id=?", new String[] { String.valueOf(productId) });
    }
}
