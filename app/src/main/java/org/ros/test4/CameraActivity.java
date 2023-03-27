package org.ros.test4;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.ros.android.AppCompatRosActivity;
import org.ros.android.BitmapFromCompressedImage;
import org.ros.android.BitmapFromImage;
import org.ros.android.MasterChooser;
import org.ros.android.RosActivity;
import org.ros.android.view.RosImageView;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

import sensor_msgs.CompressedImage;
//import sensor_msgs.Image;
public class CameraActivity extends AppCompatRosActivity {

    private RosImageView<CompressedImage> rosImageView;
    private Toolbar toolbar;
    private Button RvizB,MapB;
    public CameraActivity() {
        super("CameraTest", "CameraTest");
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cmaera");

        rosImageView = (RosImageView<CompressedImage>) findViewById(R.id.image);
        rosImageView.setTopicName("image_raw/compressed");
        rosImageView.setMessageType(sensor_msgs.CompressedImage._TYPE);
        rosImageView.setMessageToBitmapCallable(new BitmapFromCompressedImage());

        RvizB = findViewById(R.id.RvizB);
        RvizB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CameraActivity.this ,RvizActivity.class);
                startActivity(intent);
            }
        });
        MapB = findViewById(R.id.MapB);
        MapB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CameraActivity.this ,MapActivity.class);
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
    protected void init(NodeMainExecutor nodeMainExecutor){
        NodeConfiguration nodeConfiguration = NodeConfiguration.newPublic(getRosHostname());
        nodeConfiguration.setMasterUri(getMasterUri());
        nodeMainExecutor.execute(rosImageView, nodeConfiguration);

    }

}