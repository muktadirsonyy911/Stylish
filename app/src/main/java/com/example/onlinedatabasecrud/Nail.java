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

import com.example.onlinedatabasecrud.Adapter.NailAdapter;
import com.example.onlinedatabasecrud.Adapter.RecycleViewAdapter;
import com.example.onlinedatabasecrud.ModelClass.ShopsInfo;

import java.util.ArrayList;
import java.util.List;

public class Nail extends AppCompatActivity {

    RecyclerView rv;
    List<ShopsInfo> list = new ArrayList();
    NailAdapter adapter;
    TextView textView, textView1;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nail);

        list.add(new ShopsInfo("Sunvee's", "House No-1, Alta Plaza, Road No-10, Dhanmondi, Near Kalabagan Bus Stand, Dhaka 1205", "01677-486554"));
        list.add(new ShopsInfo("Nail Art Express Bangladesh", "House # 36, Road # 12, Block # E, Andalusia, Apt-A1, Banani, Dhaka 1213", "01866-444111"));
        list.add(new ShopsInfo("Touch and Glow Beauty Parlour", "House-04, Road No-12, 1st Floor, Sector-06, Uttara, Dhaka 1230", "01857-897551"));
        list.add(new ShopsInfo("Miko Nail Art Studio", " P A S Plaza, Ka-11/2, 8th Floor, Bashundhara Main Gate, Jagannathpur, Dhaka, 1212", "01840-057293"));
        list.add(new ShopsInfo("La Femme", " 2nd Floor, House 12B, Mehar Nivas Road 55, Gulshan 2, Dhaka 1212", " 01714-110066"));

        rv = findViewById(R.id.recycleView4);
        adapter = new NailAdapter(list);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new NailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(Nail.this, "Going", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Nail.this, DateAndTime.class);
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

