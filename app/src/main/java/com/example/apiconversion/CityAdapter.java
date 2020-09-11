package com.example.apiconversion;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.apiconversion.Retrofit.CityName;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> implements Filterable {

    private List<CityName> cityName;
    private List<CityName> getCityNameFiltered;
    private Context context;


    public CityAdapter(List<CityName> cityName, Context context) {
        this.context = context;
        this.cityName = cityName;
        this.getCityNameFiltered = cityName;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_name.setText(getCityNameFiltered.get(position).name);

    }

    @Override
    public int getItemCount() {
        return getCityNameFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charcater = constraint.toString();
                if (charcater.isEmpty()){
                    getCityNameFiltered = cityName ;
                }else {
                    List<CityName> filterList = new ArrayList<>();
                    for (CityName row: cityName){
                        if (row.getName().toLowerCase().contains(charcater.toLowerCase())){
                            filterList.add(row);
                        }
                    }

                    getCityNameFiltered = filterList ;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = getCityNameFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                getCityNameFiltered = (ArrayList<CityName>) results.values ;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements  View.OnClickListener {
        public TextView tv_name;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int postion = getAdapterPosition();
            Toast.makeText(context, getCityNameFiltered.get(postion).getName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context , WeatherActivity.class);
            intent.putExtra("name" , getCityNameFiltered.get(postion).getName());
            context.startActivity(intent);
        }
    }



//    public void filterList(ArrayList<CityName> filteredList) {
//        cityName = filteredList;
//        notifyDataSetChanged();
//      }
}
