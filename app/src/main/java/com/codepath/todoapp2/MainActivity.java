package com.codepath.todoapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 20;
    EditText etItems;
    ListView lvItems;
    ArrayList<String> arrayItems;
    ArrayAdapter itemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etItems = (EditText) findViewById(R.id.etNewItem);
        lvItems = (ListView) findViewById(R.id.lvItem);
        lvItems.setAdapter(itemsAdapter);
        populateArrayItems();
        itemsAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, arrayItems);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
        launchComposeView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK ){
          String name =  data.getExtras().getString("result");
            int pos = Integer.valueOf(data.getExtras().getString("pos"));
            //`arrayItems.add(name);
            arrayItems.set(pos, name);

        }
    }

    public void populateArrayItems(){
        arrayItems = new ArrayList<String>();
        arrayItems.add("First Item");
        arrayItems.add("Second Item");
        arrayItems.add("Third Item");
    }
    public void onAddItems(View view) {
       itemsAdapter.add(etItems.getText().toString());
        etItems.setText("");
    }

    private void setupListViewListener(){
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayItems.remove(position);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }


    private void  launchComposeView(){
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), EditItemActivity.class);
                intent.putExtra("key", arrayItems.get(position).toString());
                intent.putExtra("position", position);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }
}
