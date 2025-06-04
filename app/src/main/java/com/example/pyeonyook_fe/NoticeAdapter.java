package com.example.pyeonyook_fe;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pyeonyook_fe.api.Notice;

import java.util.ArrayList;
import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {

    private List<Notice> noticeList = new ArrayList<>();

    public NoticeAdapter(List<Notice> noticeList) {
        if (noticeList != null) {
            this.noticeList = noticeList;
        }
    }

    public static class NoticeViewHolder extends RecyclerView.ViewHolder {
        Button btnNoticeType;
        TextView tvTitle, tvAuthor, tvDate;

        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            btnNoticeType = itemView.findViewById(R.id.btnNoticeType);
            tvTitle = itemView.findViewById(R.id.tvNoticeTitle);
            tvAuthor = itemView.findViewById(R.id.tvNoticeAuthor);
            tvDate = itemView.findViewById(R.id.tvNoticeDate);
        }
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notice, parent, false);
        return new NoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        Notice notice = noticeList.get(position);

        if(notice.getType() == 0) {holder.btnNoticeType.setText("학사");}
        else if(notice.getType() ==1){holder.btnNoticeType.setText("장학");}

        holder.tvTitle.setText(notice.getTitle());
        holder.tvAuthor.setText(notice.getAuthor());
        holder.tvDate.setText(notice.getPublishedAt());

        holder.tvTitle.setOnClickListener(v -> {
            String url = notice.getUrl();
            if (url != null && !url.isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                v.getContext().startActivity(intent);
            }else{
                Toast.makeText(v.getContext(),"URL이 없습니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public void updateNoticeList(List<Notice> newList) {
        this.noticeList = newList;
        notifyDataSetChanged();
    }
}
