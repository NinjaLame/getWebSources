package com.example.bayu.getresources;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ConnectInternetTask c1;
    static  TextView myText;
    EditText getUrl;
    Spinner dropdown;
    Context ct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getResourcesOnClickHandler();
        clearSourcesOnClickHancler();
        myText = (TextView)findViewById(R.id.res_resource);

    }

    public void getResourcesOnClickHandler (){
        ct = this;
        Button getButton = (Button)findViewById(R.id.req_resources);
        getUrl = (EditText)findViewById(R.id.in_tml);
        dropdown = (Spinner)findViewById(R.id.spinner1);
        String[] items= {"http://","https://"};
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.url_head,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c1 = new ConnectInternetTask(ct);
                final String stringUrl = getUrl.getText().toString();
                final String selectedSpinner = dropdown.getSelectedItem().toString();

                Toast.makeText(ct, "get your Sources", Toast.LENGTH_SHORT).show();
                c1.execute(selectedSpinner+stringUrl);
//                TextView resResource = (TextView)findViewById(R.id.res_resource);
//                resResource.setText("Hallo");
            }
        });
    }

    public  void clearSourcesOnClickHancler(){
        ct = this;
        Button clearButton= (Button)findViewById(R.id.clearTxt);
        myText = (TextView)findViewById(R.id.res_resource);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ct, "Cleaning your Sources", Toast.LENGTH_SHORT).show();
                myText.setText("");
            }
        });
    }
}
