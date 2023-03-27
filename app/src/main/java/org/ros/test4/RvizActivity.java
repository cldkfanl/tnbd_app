package org.ros.test4;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import org.ros.android.AppCompatRosActivity;
import org.ros.android.RosActivity;
import org.ros.android.view.DistanceView;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

public class RvizActivity extends AppCompatRosActivity {



    private DistanceView rosRvizView;
    private Toolbar toolbar;
    private Button CamB,MapB;
    public RvizActivity() {
        super("RvizTest", "RvizTest");
    }


    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rviz);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Rviz");


        rosRvizView = (DistanceView) findViewById(R.id.Rviz);
        rosRvizView.setTopicName("scan");

        MapB = findViewById(R.id.MapB);
        MapB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(RvizActivity.this ,MapActivity.class);
                startActivity(intent);
            }
        });
        CamB = findViewById(R.id.CamB);
        CamB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(RvizActivity.this ,CameraActivity.class);
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
        nodeMainExecutor.execute(rosRvizView, nodeConfiguration);
    }
}