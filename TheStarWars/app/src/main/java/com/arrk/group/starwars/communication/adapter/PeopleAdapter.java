package com.arrk.group.starwars.communication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arrk.group.starwars.R;
import com.arrk.group.starwars.communication.models.PeopleModel;
import com.arrk.group.starwars.custom.STextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author SANDY
 */
public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {

    private List<PeopleModel> mValues;
    private Context context;
    private final OnItemClickListener listener;

    public PeopleAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setValues(List<PeopleModel> mValues) {
        if (this.mValues == null) {
            this.mValues = new ArrayList<>();
        }
        this.mValues.addAll(mValues);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.people_name_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        PeopleModel model = mValues.get(position);
        viewHolder.mTextPeopleName.setText(model.getName());
        viewHolder.bind(model, listener);
    }

    @Override
    public int getItemCount() {
        return mValues != null ? mValues.size() : 0;
    }

    static

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_people_name)
        STextView mTextPeopleName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(final PeopleModel item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
