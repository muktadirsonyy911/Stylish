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

import com.example.onlinedatabasecrud.Adapter.BeautyParlourAdapter;
import com.example.onlinedatabasecrud.Adapter.RecycleViewAdapter;
import com.example.onlinedatabasecrud.ModelClass.ShopsInfo;

import java.util.ArrayList;
import java.util.List;

public class BeautyParlour extends AppCompatActivity {

    RecyclerView rv;
    List<ShopsInfo> list = new ArrayList();
    BeautyParlourAdapter adapter;
    TextView textView, textView1;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty_parlour);

        list.add(new ShopsInfo("Woman's World", "Rangs Panorama, 80, Satmasjid Road, Dhanmondi, Dhaka 1209", "02-8144529"));
        list.add(new ShopsInfo("Persona", " House # 121-D, Road # 44, Gulshan Avenue,Gulshan-2, Dhaka, Dhaka 1212", "01847-067909"));
        list.add(new ShopsInfo("Red Beauty Parlour And Salon", "House No 39, Block G Rd No 7, Banani, Dhaka 1212", "02-9872293"));
        list.add(new ShopsInfo("Dream Beauty Parlour", "House No: 1, Sector- 4, Uttara, Dhaka 1230", "01774-876118"));
        list.add(new ShopsInfo("Rupchaya Beauty Parlour", "19/D/6, Tolarbagh, Mirpur 1, Dhaka 1216", "01823-063516"));
        list.add(new ShopsInfo("Annyakonna", "Ka-3/1/C Bashundhara Rd, Dhaka 1229 Bashundhara Rd, Dhaka 1229", "01721322107"));
        list.add(new ShopsInfo("Beauty Green Room Parlour", "House No.22 (1st floor), Road 8, Shekhertek), Adabor, Dhaka 1207", "01772-497393"));


        rv = findViewById(R.id.recycleView2);
        adapter = new BeautyParlourAdapter(list);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new BeautyParlourAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(BeautyParlour.this, "Going", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(BeautyParlour.this, DateAndTime.class);
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

