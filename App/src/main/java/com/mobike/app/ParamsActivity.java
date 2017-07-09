package com.mobike.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ParamsActivity extends AppCompatActivity {

    private Button btnOk;
    private EditText editFriction,editRestitution,editDensity,editRatio;
    private TextView tvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_params);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("配置参数");

        btnOk = (Button) findViewById(R.id.ok);
        editDensity = (EditText) findViewById(R.id.density);
        editFriction = (EditText) findViewById(R.id.friction);
        editRestitution = (EditText) findViewById(R.id.restitution);
        editRatio = (EditText) findViewById(R.id.ratio);
        tvMsg = (TextView) findViewById(R.id.msg);

        Intent intent = getIntent();
        float density = intent.getFloatExtra("density",0);
        float friction = intent.getFloatExtra("friction",0);
        float restitution = intent.getFloatExtra("restitution",0);
        float ratio = intent.getFloatExtra("ratio",0);
        String msg = new StringBuffer().append("当前的密度是:" + density + ",").append("能量损失率是:" + restitution + ",").append("摩擦系数是:" + friction + ",").append("世界与屏幕的比率是:" + ratio).toString();
        tvMsg.setText(msg);

        btnOk.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                float density = getEditContentToFloat(editDensity);
                float friction = getEditContentToFloat(editFriction);
                float restitution = getEditContentToFloat(editRestitution);
                float ratio = getEditContentToFloat(editRatio);
                Intent result = new Intent();
                result.putExtra("density",density);
                result.putExtra("friction",friction);
                result.putExtra("restitution",restitution);
                result.putExtra("ratio",ratio);
                setResult(110,result);
                finish();
            }
        });
    }

    private float getEditContentToFloat(EditText edt){
        String text = edt.getText().toString().trim();
        if(TextUtils.isEmpty(text)){
            return -1f;
        }
        return Float.parseFloat(text);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
