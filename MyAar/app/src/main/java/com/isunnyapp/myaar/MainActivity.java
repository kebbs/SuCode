package com.isunnyapp.myaar;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.isunnyapp.helper.HelperUtil;
import com.isunnyapp.helper.Tool;
import com.isunnyapp.midialog.MiDialog;

public class MainActivity extends Activity implements View.OnClickListener {
    TextView tv_title;

    @Override
    public void onClick(View v) {
        MiDialog dd = new MiDialog(this);
        dd.setTitle("11");
        dd.setNegativeButton("11", null);
        dd.show();
        if (v == tv_title) {
//            tv_title.setEnabled(false);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HelperUtil.initialize(this);

        tv_title = (TextView) findViewById(R.id.tv_title);

//        ColorStateList csl = Tool.createColorStateList(Color.RED, Color.BLACK);
        ColorStateList csl = Tool.createColorStateList("#FF0000", "#00FF00");
        tv_title.setTextColor(csl);
        tv_title.setSelected(true);
//        tv_title.setClickable(true);
        tv_title.setOnClickListener(this);

    }
}
