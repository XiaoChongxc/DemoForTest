package demo.test.xc.com.demo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import demo.test.xc.com.demo.R;

public class HomeListFragment  extends RecyclerView.Adapter<HomeListFragment.ViewHolder> {

    List<String> titles;

    public HomeListFragment(List<String> titles) {
        this.titles = titles;
    }

    @NonNull
    @Override
    public HomeListFragment.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View  view=  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeListFragment.ViewHolder holder, int position) {
        String  title=titles.get(position);
        holder.mTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }


    public   class ViewHolder extends RecyclerView.ViewHolder{
        TextView  mTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle=itemView.findViewById(R.id.content);
        }
    }

}
