package com.example.pyeonyook_fe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import com.example.pyeonyook_fe.api.Notice;

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
        holder.btnNoticeType.setText(notice.getType());
        holder.tvTitle.setText(notice.getTitle());
        holder.tvAuthor.setText(notice.getAuthor());
        holder.tvDate.setText(notice.getPublishedAt());
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    // ❗ 데이터 갱신용 메서드 (네트워크로 데이터 받아올 때 호출)
    public void updateNoticeList(List<Notice> newList) {
        this.noticeList = newList;
        notifyDataSetChanged();
    }
}
