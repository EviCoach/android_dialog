package com.columnhack.complexdialogs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Date;

public class DetailFragment extends Fragment {
    public static final String DIALOG_DATE = "dialogData";
    public static final int REQUEST_DATE = 0;
    private Button mDateBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        mDateBtn = view.findViewById(R.id.date_btn);
        mDateBtn.setText("Choose Date");

        mDateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FragmentManager manager = getFragmentManager();
                DisplayDialogs dialog = DisplayDialogs.newInstance(new Date());
                dialog.setTargetFragment(DetailFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode != Activity.RESULT_OK) return;

        if(requestCode == REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DisplayDialogs.EXTRA_DATE);
            mDateBtn.setText(DateFormat.format("yyyy-MM-dd", date));
        }
    }
}
