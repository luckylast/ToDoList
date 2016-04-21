package com.ozwhver.eight.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText item;
    private Button add;
    private ListView dynamicListView;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = (EditText) findViewById(R.id.itemEditText);
        add = (Button) findViewById(R.id.addItemButton);
        dynamicListView = (ListView) findViewById(R.id.itemsListView);

        list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
        dynamicListView.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem = item.getText().toString();
                if(todoItem.length() > 0){
                    // add editText Value to the list
                    list.add(item.getText().toString());
                    // refresh the listView
                    adapter.notifyDataSetChanged();
                    //clear the edit Text for the new Item
                    item.setText("");
                }
            }
        });

        dynamicListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //remove the item from list
                list.remove(position);

                //apply changes on the adapter to refresh the listView
                adapter.notifyDataSetChanged();
                return true;
            }
        });

    }
}
