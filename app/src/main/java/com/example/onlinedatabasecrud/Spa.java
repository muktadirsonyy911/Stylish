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
import com.example.onlinedatabasecrud.Adapter.SpaAdapter;
import com.example.onlinedatabasecrud.ModelClass.ShopsInfo;

import java.util.ArrayList;
import java.util.List;

public class Spa extends AppCompatActivity {

    RecyclerView rv;
    List<ShopsInfo> list = new ArrayList();
    SpaAdapter adapter;
    TextView textView, textView1;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spa);

        list.add(new ShopsInfo("Body massages only girls", "House 127 road 9a west Dhanmondi, 1209", "01648-464242"));
        list.add(new ShopsInfo("Evergreen Adams & SPA", "House-38, Road-01, Sector-03, Uttara,Dhaka 1230", "01787-681526"));
        list.add(new ShopsInfo("Saron Rom Spa", " Road 96, House 19, Gulshan 2, Dhaka 1212", "09604-666666"));
        list.add(new ShopsInfo("TULIP nails & spa", "Green Grandeur, 58/E, 3rd Floor, Kemal Ataturk Avenue, Banani, Dhaka 1213", "01971-592599"));
        list.add(new ShopsInfo("Style N Smile", "Avenue 10, Mirpur DOHS, Dhaka", "01969-660296"));
        
        rv = findViewById(R.id.recycleView3);
        adapter = new SpaAdapter(list);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new SpaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(Spa.this, "Going", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Spa.this, DateAndTime.class);
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

