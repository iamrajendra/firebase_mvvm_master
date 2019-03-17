package com.ravan.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ravan.R;

public class MedicineInputCard extends RelativeLayout implements CompoundButton.OnCheckedChangeListener {
    private CheckBox mCheckBox;
    public MedicineInputCard(Context context) {
        super(context);
        init();

    }



    public MedicineInputCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MedicineInputCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MedicineInputCard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
    View view  = LayoutInflater.from(getContext()).inflate(R.layout.medicine_input_card,null);
    addView(view);
    mCheckBox = findViewById(R.id.show_hide);
    mCheckBox.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
         findViewById(R.id.input_text).setVisibility(isChecked?VISIBLE:GONE);
        findViewById(R.id.lable_tv).setVisibility(!isChecked?VISIBLE:GONE);
        if (!((EditText)findViewById(R.id.input_text)).getText().toString().isEmpty()) {
            ((TextView) findViewById(R.id.lable_tv)).setText(((EditText) findViewById(R.id.input_text)).getText().toString());
        }

    }
}
