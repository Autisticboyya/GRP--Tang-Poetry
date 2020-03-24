package commentTools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

import dataBaseTools.SharedPrefManager;


public class CommentAdapter extends BaseAdapter {

    Context context;
    List<Comment> data;

    public CommentAdapter(Context c, List<Comment> data){
        this.context = c;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        // 重用convertView
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_comment, null);
            holder.username = convertView.findViewById(R.id.comment_name);
            holder.comment_content = convertView.findViewById(R.id.comment_content);
            holder.date = convertView.findViewById(R.id.comment_time);
            //holder.user_pic = convertView.findViewById(R.id.user_picture);
            holder.like = convertView.findViewById(R.id.comment_like);
            holder.delete = convertView.findViewById(R.id.comment_delete);
            holder.reply = convertView.findViewById(R.id.comment_reply);
            holder.floor_num = convertView.findViewById(R.id.comment_floor);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        // 适配数据
        holder.username.setText(data.get(i).getName());
        holder.comment_content.setText(data.get(i).getContent());
        holder.date.setText(data.get(i).getDate());
        holder.like.setText("like (" + data.get(i).getLike_num() + ")");
        holder.floor_num.setText("#" + i + 1);

        //如果是自己的评论 可以删除
        if (data.get(i).getName().equals(SharedPrefManager.getInstance().getUsername())){
            holder.delete.setVisibility(View.VISIBLE);
        }

        //绑定动作
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//        //获取imageview的bitmap.
//        Bitmap bitmap = ((BitmapDrawable)data.get(i).getImage().getDrawable()).getBitmap();
//        holder.user_pic.setImageBitmap(bitmap);
//        holder.like.setText(data.get(i).getDate());

        return convertView;
    }

    /**
     * 添加一条评论,刷新列表
     * @param comment
     */
    public void addComment(Comment comment){
        data.add(comment);
        notifyDataSetChanged();
    }

    public void clearComment(){
        data.clear();
        notifyDataSetChanged();
    }

    /**
     * 静态类，便于GC回收
     */
    public static class ViewHolder{
        TextView username;
        TextView floor_num;
        ImageView user_pic;
        TextView comment_content;
        TextView date;
        TextView like;
        TextView delete;
        TextView reply;
    }

}