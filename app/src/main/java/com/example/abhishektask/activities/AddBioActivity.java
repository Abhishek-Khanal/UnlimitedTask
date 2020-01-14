package com.example.abhishektask.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abhishektask.DataConverter;
import com.example.abhishektask.R;

import java.io.IOException;
import java.util.Calendar;

public class AddBioActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    public static final String EXTRA_NAME = "com.example.abhishektask.EXTRA_NAME";
    public static final String EXTRA_ADDRESS = "com.example.abhishektask.EXTRA_ADDRESS";
    public static final String EXTRA_GENDER = "com.example.abhishektask.EXTRA_GENDER";
    public static final String EXTRA_DOB = "com.example.abhishektask.EXTRA_DOB";
    public static final String EXTRA_SALARY = "com.example.abhishektask.EXTRA_SALARY";
    public static final String EXTRA_PHONE = "com.example.abhishektask.EXTRA_PHONE";
    public static final String EXTRA_PHOTO = "com.example.abhishektask.EXTRA_PHOTO";

    private EditText name, address, salary, phoneNo;
    private TextView dob;
    private Button setDate, addImage;
    private ImageView imageView;
    private RadioGroup gender;
    private RadioButton radioButton;
    private Bitmap bitmap;
    private byte[] image;
    private boolean imageAdded;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bio);
        name = findViewById(R.id.editName);
        address = findViewById(R.id.editAddress);
        gender = findViewById(R.id.gender_radio);
        dob = findViewById(R.id.dob);
        salary = findViewById(R.id.editSalary);
        phoneNo = findViewById(R.id.editPhone);
        setDate = findViewById(R.id.set_dob_btn);
        addImage = findViewById(R.id.add_image_btn);
        imageView = findViewById(R.id.image);
        imageAdded = false;

        if (savedInstanceState != null) {
            dob.setText(savedInstanceState.getString("date"));
            if (savedInstanceState.getByteArray("image")!= null) {
                image=savedInstanceState.getByteArray("image");
                Bitmap imageBitmap = DataConverter.convertByteArray2Image(image);
                imageView.setImageBitmap(imageBitmap);
            }
        }

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        setTitle("Add Bio");

        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 100);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case 100:
                    Uri selectedImage = data.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        imageView.setImageBitmap(bitmap);
                        image = DataConverter.convertImage2ByteArray(bitmap);
                        imageAdded = true;
                    } catch (IOException e) {
                        imageAdded = false;
                        Log.i("TAG", "Some exception " + e);
                    }
                    break;
            }
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                2000,
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_bio_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.save_bio):
                saveBio();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveBio() {
        String Name = name.getText().toString();
        String Address = address.getText().toString();
        int radioId = gender.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        String Gender = radioButton.getText().toString();
        String DOB = dob.getText().toString();
        String Salary = salary.getText().toString();
        String Phone = phoneNo.getText().toString();
        if (Phone.length()!=10){
            phoneNo.setError("Number should be 10 digits");
            return;
        }
        if (Name.trim().isEmpty() || Address.trim().isEmpty() || DOB.trim().isEmpty() || Salary.trim().isEmpty() || Phone.trim().isEmpty() || !imageAdded) {
            Toast.makeText(this, "Please insert the data in the empty field", Toast.LENGTH_SHORT).show();
            return;
        }


        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, Name);
        data.putExtra(EXTRA_ADDRESS, Address);
        data.putExtra(EXTRA_GENDER, Gender);
        data.putExtra(EXTRA_DOB, DOB);
        data.putExtra(EXTRA_SALARY, Salary);
        data.putExtra(EXTRA_PHONE, Phone);
        data.putExtra(EXTRA_PHOTO, image);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + (month+1) + "/" + year;
        dob.setText(date);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("date", dob.getText().toString());
        outState.putByteArray("image", image);
    }
}
