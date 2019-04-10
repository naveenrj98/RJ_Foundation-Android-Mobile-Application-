package u.in.ac.bmsce.rjfoundation;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import instamojo.library.InstapayListener;
import instamojo.library.InstamojoPay;
import instamojo.library.Config;
import org.json.JSONObject;
import org.json.JSONException;
import android.content.IntentFilter;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

public class DonorActivity extends AppCompatActivity {



    private EditText input_pay;
    private String pay1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);
        // Call the function callInstamojo to start payment here




        Button btn_pay = findViewById(R.id.btn_pay);

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_pay = (EditText) findViewById(R.id.input_pay);
                pay1 = input_pay.getText().toString();

                callInstamojoPay("naveekumarr97@gmail.com", "8073141525",pay1, "RJ FOUNDATION", "Lucky");

            }
        });

    }
    
    private void callInstamojoPay(String email, String phone, String amount, String purpose, String buyername) {
        final Activity activity = this;
        InstamojoPay instamojoPay = new InstamojoPay();
        IntentFilter filter = new IntentFilter("ai.devsupport.instamojo");
        registerReceiver(instamojoPay, filter);
        JSONObject pay = new JSONObject();
        try {

            pay.put("email", email);
            pay.put("phone", phone);
            pay.put("purpose", purpose);
            pay.put("amount", amount);
            pay.put("name", buyername);
            pay.put("send_sms", true);
            pay.put("send_email", true);

 } catch (JSONException e) {
            e.printStackTrace();
        }

        initListener();
        instamojoPay.start(activity, pay, listener);
    }
    
    InstapayListener listener;

    
    private void initListener() {
        listener = new InstapayListener() {
            @Override
            public void onSuccess(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onFailure(int code, String reason) {
                Toast.makeText(getApplicationContext(), "Failed: " + reason, Toast.LENGTH_LONG)
                        .show();
            }
        };
    }

}
