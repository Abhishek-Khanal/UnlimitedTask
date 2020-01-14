package com.example.abhishektask.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abhishektask.DataConverter;
import com.example.abhishektask.R;
import com.example.abhishektask.database.Bio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BioAdapter extends RecyclerView.Adapter<BioAdapter.ViewHolder> {
    private List<Bio> bios = new ArrayList<>();

    @NonNull
    @Override
    public BioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bio_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BioAdapter.ViewHolder holder, int position) {
        Bio currentBio = bios.get(position);
        holder.name.setText("Name: " + currentBio.getName());
        holder.address.setText("Address: " + currentBio.getAddress());
        holder.gender.setText("Gender: " + currentBio.getGender());
        holder.salary.setText("Salary: " + currentBio.getSalary());
        holder.phoneNo.setText("Phone No: " + currentBio.getPhoneNumber());
        holder.photo.setImageBitmap(DataConverter.convertByteArray2Image(currentBio.getImage()));
        Date currentDate = Calendar.getInstance().getTime();
        int age= ((int) DataConverter.getDateDiff(currentBio.getDob(),currentDate, TimeUnit.DAYS))/365;
        holder.age.setText("Age: " + age);
    }

    public void setBios(List<Bio> bios) {
        this.bios = bios;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return bios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView address;
        private TextView gender;
        private TextView age;
        private TextView salary;
        private TextView phoneNo;
        private ImageView photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            gender = itemView.findViewById(R.id.gender);
            age = itemView.findViewById(R.id.age);
            salary = itemView.findViewById(R.id.salary);
            phoneNo = itemView.findViewById(R.id.phoneNo);
            photo = itemView.findViewById(R.id.profile_image);

        }
    }
}
