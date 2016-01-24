package com.codepath.todoapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

   Button btnSave;
    EditText etEditText;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        etEditText = (EditText) findViewById(R.id.etEditItem);
        btnSave = (Button) findViewById(R.id.btnSave);
        final Bundle extras = getIntent().getExtras();
        btnSave.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result", etEditText.getText().toString());
                intent.putExtra("pos", extras.get("position").toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });


        data =  extras.get("key").toString();
        etEditText.setText(data);


    }


}
