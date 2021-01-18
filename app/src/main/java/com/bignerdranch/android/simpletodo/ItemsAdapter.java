package com.bignerdranch.android.simpletodo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

//2.Responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    //14.create an interface to receive the information from main activity
    public interface OnClickListener{
        void onItemClicked(int position);
    }

    //12.create an interface to receive the information from main activity
    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;

    //3. generate the constructor
    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener clickListener) {
        this.items=items;
        this.longClickListener=longClickListener;
        this.clickListener=clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //4.Use layout inflator to inflate a view
        View todoView=LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        //5.wrap it inside a View Holder and return it
        return new ViewHolder(todoView);
    }

    //6.Responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //7.Grab the item at the positioon
        String item=items.get(position);
        //8.Bind the item intoo the specified view holder
        holder.bind(item);
    }

    //10.Tell the RV how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    //1.Coontainer to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem=itemView.findViewById(android.R.id.text1);
        }

        //9.Update the view inside of the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);

            //13.Make connection to the click on item
            tvItem.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });

            //10. Add long press event
            tvItem.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v){
                    //11.Remove the item from the recycler view
                    //== Notify the listener which position was being long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }

}
