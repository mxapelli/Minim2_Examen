package edu.upc.dsa.minim2_github;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Repo> repos;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;
        public TextView language;
        public View layout;
        //public ImageView avatar;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            name = (TextView) v.findViewById(R.id.rowNameText);
            language = (TextView) v.findViewById(R.id.languageText);
            //avatar = (ImageView) v.findViewById(R.id.iconImage);
        }
    }

    public void add(int position, Repo item) {
        repos.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        repos.remove(position);
        notifyItemRemoved(position);
    }

    public RecyclerAdapter (List<Repo> myDataset) {
        repos = myDataset;
        LayoutInflater inflater;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.recycler_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = repos.get(position).getName();
        final String language = repos.get(position).getLanguage();
        holder.name.setText(name);
        holder.language.setText(language);
        //Context context = holder.avatar.getContext();
        //Picasso.with(context).load(values.get(position).getAvatar_url()).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }
}
