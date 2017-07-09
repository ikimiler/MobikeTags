package com.mobike.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mobike.library.Mobike;
import com.mobike.library.MobikeView;

public class MainActivity extends AppCompatActivity {

    private MobikeView mobikeView;
    private Button btnTest;
    private  int[] imgs = {
            R.drawable.share_fb,
            R.drawable.share_kongjian,
            R.drawable.share_pyq,
            R.drawable.share_qq,
            R.drawable.share_tw,
            R.drawable.share_wechat,
            R.drawable.share_weibo,
            R.drawable.thumb_down_press,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mobikeView = (MobikeView) findViewById(R.id.mobike_view);
        btnTest = (Button) findViewById(R.id.test);

        initViews();

        btnTest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mobikeView.getmMobike().random();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mobikeView.getmMobike().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mobikeView.getmMobike().onStop();
    }

    private void initViews() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        for(int i = 0; i < imgs.length  ; i ++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imgs[i]);
            if(i == imgs.length - 1){
                imageView.setTag(R.id.mobike_view_circle_tag,false);
            }else{
                imageView.setTag(R.id.mobike_view_circle_tag,true);
            }
            mobikeView.addView(imageView,layoutParams);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 110 && data != null){
            float density = data.getFloatExtra("density",-1);
            float friction = data.getFloatExtra("friction",-1);
            float restitution = data.getFloatExtra("restitution",-1);
            float ratio = data.getFloatExtra("ratio",-1);
            mobikeView.getmMobike().setDensity(density);
            mobikeView.getmMobike().setFriction(friction);
            mobikeView.getmMobike().setRestitution(restitution);
            mobikeView.getmMobike().setRatio(ratio);
            mobikeView.getmMobike().update();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.editParams){
            Intent intent = new Intent(this, ParamsActivity.class);
            intent.putExtra("density",mobikeView.getmMobike().getDensity());
            intent.putExtra("friction",mobikeView.getmMobike().getFriction());
            intent.putExtra("restitution",mobikeView.getmMobike().getRestitution());
            intent.putExtra("ratio",mobikeView.getmMobike().getRatio());
            startActivityForResult(intent,110);
        }else if(item.getItemId() == R.id.enable){
            Mobike mobike = mobikeView.getmMobike();
            boolean enable = mobike.getEnable();
            item.setTitle(enable ? "开启" : "停止");
            mobike.setEnable(!enable);
        }else if(item.getItemId() == R.id.mobikedemo){
            startActivity(new Intent(this,MobikeDemo.class));
        }else if(item.getItemId() == R.id.about){
            startActivity(new Intent(this,AboutActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
