package u.in.ac.bmsce.rjfoundation.Donate;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import u.in.ac.bmsce.rjfoundation.DonorActivity;
import u.in.ac.bmsce.rjfoundation.R;

public class DonateOnSpecialOccassion extends AppCompatActivity {

    FloatingActionButton btnDonate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_on_special_occassion);

        btnDonate = findViewById(R.id.btnDonate);

        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DonateOnSpecialOccassion.this, DonorActivity.class);
                startActivity(intent);

            }
        });
    }
}
