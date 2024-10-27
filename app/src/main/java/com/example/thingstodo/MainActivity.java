package com.example.thingstodo;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);

        items.add("First Item");
        items.add("Second Item");
        setupListViewListener();
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener((adapter, view, pos, id) -> {
            // Remove the item within the array at the specified position
            items.remove(pos);
            // Refresh the adapter
            itemsAdapter.notifyDataSetChanged();
            // Return true consumes the long click event (marks it handled)
            return true;
        });
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        if (!itemText.isEmpty()) {
            itemsAdapter.add(itemText);
            etNewItem.setText("");
        }
    }
}
