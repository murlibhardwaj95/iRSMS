package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.syon.isrms.Beans.Library_Status_Data;
import com.syon.isrms.R;

import java.util.List;

public class LibraryStatus_Recycle_Adapter extends RecyclerView.Adapter<LibraryStatus_Recycle_Adapter.ViewHolder> {
    List<Library_Status_Data> data;
    Context context;

    public LibraryStatus_Recycle_Adapter(Context context, List<Library_Status_Data> data) {
        this.context = context;
        this.data = data;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.teachercustomviewlibrary, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.Accession_No.setText(data.get(position).getNSrNo().toString());
        holder.Book_Title.setText(data.get(position).getSBookTitle().toString());
        holder.Issue_Date.setText(data.get(position).getSIssueDate().toString());
        holder.Return_Date.setText(data.get(position).getSReturnDate().toString());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Accession_No, Book_Title, Issue_Date,Return_Date;
        ImageView download, circle_color;

        public ViewHolder(View itemView) {
            super(itemView);

            Accession_No = itemView.findViewById(R.id.sBookCode);
            Book_Title = itemView.findViewById(R.id.sBookTitle);
            Issue_Date = itemView.findViewById(R.id.sIssueDate);
           Return_Date = itemView.findViewById(R.id.sReturnDate);




        }
    }
}
