package poemListTools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;

/**
 * 列表的适配器，这是favourite列表的适配器，没啥好说的，很简单，反正最后也要换掉
 */
public class FavouriteAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<SortModel> modelList;

    public FavouriteAdapter(Context context, ArrayList<SortModel> list){
        this.modelList = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.favourite_item, null);
        }
        SortModel sortModel = modelList.get(position);
        String name = sortModel.getPoemName();
        String writer = sortModel.getWriterName();
        TextView poemName = convertView.findViewById(R.id.favourite_item_name);
        TextView writerName = convertView.findViewById(R.id.favourite_item_writer);
        poemName.setText(name);
        writerName.setText(writer);

        Button deleteButton = convertView.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelList.remove(position);
                FavouriteAdapter.this.notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
