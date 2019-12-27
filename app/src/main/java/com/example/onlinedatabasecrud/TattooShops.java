package com.example.onlinedatabasecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinedatabasecrud.Adapter.RecycleViewAdapter;
import com.example.onlinedatabasecrud.Adapter.TattooShopAdapter;
import com.example.onlinedatabasecrud.ModelClass.ShopsInfo;

import java.util.ArrayList;
import java.util.List;

public class TattooShops extends AppCompatActivity {

    RecyclerView rv;
    List<ShopsInfo> list = new ArrayList();
    TattooShopAdapter adapter;
    TextView textView, textView1;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tattoo_shops);

        list.add(new ShopsInfo("InkDhanmondi Tattoo Studio", " Ambala Complex, Road# 02, Dhanmondi Dhaka, Bangladesh, 1205", " 01683-527421"));
        list.add(new ShopsInfo("Dhaka Tattoo Studio", "167/40 Matikata Kalshi Road Next To ECB Circle, Mirpur, Dhaka, 1206", "01933-999933"));
        list.add(new ShopsInfo("Arj Tattoo Studio", " Bank asia, Venus Complex, Gulshan, Dhaka 1212", "01849-245588"));
        list.add(new ShopsInfo("Olins Tattoo Studio", "72 Bir Uttam Kazi Nuruzzaman Road, Green Road, Dhaka 1205", "01670-975816"));



        rv = findViewById(R.id.recycleView1);
        adapter = new TattooShopAdapter(list);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new TattooShopAdapter.OnItemClickListener()
    {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(TattooShops.this, "Going", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(TattooShops.this, DateAndTime.class);
                intent.putExtra("Example Item", list.get(position));

                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.list_menu, menu);

        MenuItem searchItem=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
}


