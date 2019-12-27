package com.example.onlinedatabasecrud;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import com.example.onlinedatabasecrud.Adapter.RecycleViewAdapter;
import com.example.onlinedatabasecrud.ModelClass.ShopsInfo;

import java.util.ArrayList;
import java.util.List;
public class RecycleView extends AppCompatActivity {

    RecyclerView rv;
    List<ShopsInfo> list = new ArrayList();
    RecycleViewAdapter adapter;
    TextView textView, textView1;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        list.add(new ShopsInfo("Super Cut Salon", "54/1, North Adabor, Mehedi Bag Road, Mohammadpur, Dhaka 1207", "01680-723803"));
        list.add(new ShopsInfo("MENZ OPTION SALON", "Dhanmondi", "01766-999984"));
        list.add(new ShopsInfo("The Jawed Habib Salon", "Rd-44, Gulshan 02", "01908-888444"));
        list.add(new ShopsInfo("Scissor Hand", "Plot#11, 4th Floor, Paradise Tower, Uttara, Sector#3, Dhaka 1230", "01777-799093"));
        list.add(new ShopsInfo("Signature Salon", " Ka/11, Bashundhara Road, Main Gate, Dhaka 1229", "01707-072734"));
        list.add(new ShopsInfo("Khami Saloon ", "Shekhertek 09, Adabor", "01721322107"));
        list.add(new ShopsInfo("Mehedi Ayat Saloon", "Shekhertek 06, adabor, Mohammadpur", "01790-222580"));


        rv = findViewById(R.id.recycleView);
        adapter = new RecycleViewAdapter(list);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(RecycleView.this, "Going", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(RecycleView.this, DateAndTime.class);
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

