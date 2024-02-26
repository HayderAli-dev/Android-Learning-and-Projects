package com.example.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.databinding.WeatherRvItemBinding;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Weather_rv_Adapter extends RecyclerView.Adapter<Weather_rv_Adapter.ViewHolder> {

    private Context context;
    private ArrayList<WeatherModel> modelArrayList;

    public Weather_rv_Adapter(Context context, ArrayList<WeatherModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.weather_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherModel model=modelArrayList.get(position);
        holder.binding.idTVTemperature.setText(model.getTemperature()+"°C");
        Picasso.get().load("https://".concat(model.getIcon())).into(holder.binding.idIVCondition);
        holder.binding.idTVWindSpeed.setText(model.getWindSpeed()+"Km/h");
        SimpleDateFormat input=new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat output=new SimpleDateFormat("hh:mm aa");
        try {
            Date t=input.parse(model.getTime());
            holder.binding.idTVTime.setText(output.format(t));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
   }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
WeatherRvItemBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=WeatherRvItemBinding.bind(itemView);
        }
    }
}
