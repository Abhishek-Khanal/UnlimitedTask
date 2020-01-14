package com.example.abhishektask.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.abhishektask.adapters.BioAdapter;
import com.example.abhishektask.viewmodels.BioViewModel;
import com.example.abhishektask.R;
import com.example.abhishektask.database.Bio;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_BIOS_REQUEST = 1;

    private BioViewModel bioViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        FloatingActionButton addBtn = findViewById(R.id.add_bios);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddBioActivity.class);
                startActivityForResult(intent, ADD_BIOS_REQUEST);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final BioAdapter bioAdapter = new BioAdapter();
        recyclerView.setAdapter(bioAdapter);

        bioViewModel = ViewModelProviders.of(this).get(BioViewModel.class);

        bioViewModel.getAllBios().observe(this, new Observer<List<Bio>>() {
            @Override
            public void onChanged(List<Bio> bios) {
                ///update RecyclerView
                bioAdapter.setBios(bios);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_BIOS_REQUEST && resultCode == RESULT_OK) {

            String name = data.getStringExtra(AddBioActivity.EXTRA_NAME);
            String address = data.getStringExtra(AddBioActivity.EXTRA_ADDRESS);
            String gender = data.getStringExtra(AddBioActivity.EXTRA_GENDER);
            String salary = data.getStringExtra(AddBioActivity.EXTRA_SALARY);
            String phone = data.getStringExtra(AddBioActivity.EXTRA_PHONE);
            String dob = data.getStringExtra(AddBioActivity.EXTRA_DOB);
            byte[] image=data.getByteArrayExtra(AddBioActivity.EXTRA_PHOTO);
            Date date = null;
            try {
                 date= new SimpleDateFormat("dd/mm/YYYY").parse(dob);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            Bio bio = new Bio(name,address,gender,salary,phone,date,image);
            bioViewModel.insert(bio);

            Toast.makeText(this, "Bio Saved", Toast.LENGTH_SHORT).show();


        }else {
            Toast.makeText(this, "Bio not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
