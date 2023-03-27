package org.ros.test4;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import org.ros.address.InetAddressFactory;
import org.ros.android.AppCompatRosActivity;
import org.ros.android.RosActivity;
import org.ros.android.view.DistanceView;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;
import org.ros.time.NtpTimeProvider;

import java.util.concurrent.TimeUnit;


public class MapActivity extends AppCompatRosActivity {

    private DistanceView rosMapView;
    private Toolbar toolbar;
    private Button RvizB,CamB;
    public MapActivity() {
        super("MapTest", "MapTest");
    }


    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rviz);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Map");


        rosMapView = (DistanceView) findViewById(R.id.Rviz);
        rosMapView.setTopicName("map");

        RvizB = findViewById(R.id.RvizB);
        RvizB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MapActivity.this ,RvizActivity.class);
                startActivity(intent);
            }
        });
        CamB = findViewById(R.id.CamB);
        CamB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MapActivity.this ,CameraActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_sng, menu);
        return true;
    }


    @Override
    protected  void init(NodeMainExecutor nodeMainExecutor){
        NodeConfiguration nodeConfiguration = NodeConfiguration.newPublic(getRosHostname());
        nodeConfiguration.setMasterUri(getMasterUri());
        nodeMainExecutor.execute(rosMapView, nodeConfiguration);
    }
}