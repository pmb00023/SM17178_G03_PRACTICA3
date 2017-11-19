package es.ujaen.git.sm17178_g03_practica1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {

    public static final String PARAM_USER = "param_user";
    public static final String PARAM_PASS = "param_pass";

    Button siguiente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        siguiente = (Button)findViewById(R.id.initial_button);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siguiente = new Intent(ServiceActivity.this, base_aplication.class);
                startActivity(siguiente);
            }
        });



        String user=getIntent().getStringExtra(PARAM_USER);
        String pass=getIntent().getStringExtra(PARAM_PASS);


        TextView title = (TextView) findViewById(R.id.textView);
        Toast.makeText(this,"Hola, esto proviene del service activity"+user, Toast.LENGTH_SHORT).show();
    }
}
