package poemListTools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * 诗歌列表的适配器，实现了SectionIndexer接口，用来实现侧边滑块的快速查找
 */
public class SortAdapter extends BaseAdapter implements SectionIndexer{
    private LayoutInflater layoutInflater;
    private ArrayList<Object> modelList;
    private static final int ITEM = 1;
    private static final int HEADER = 0;
    //这个变量很关键
    private HashMap<String, Integer> alphaIndexer;
    //用于记录首字母，因为不是每个首字母都一定会出现，不能hard code
    private static String[] section;

    public SortAdapter(Context context, ArrayList<Object> list){
        this.modelList = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        alphaIndexer = new HashMap<>();
        int size = modelList.size();

        for (int i = 0; i < size; i++){
            //因为item和header的布局不一样，要用不一样的布局文件，所以用了一个分支
            switch (getItemViewType(i)){
                //获取每个项的首字母，如果他不被包括，说明该字母第一次出现，等于记录了每个首字母第一次出现的位置
                case ITEM:
                    SortModel sortModel = (SortModel) modelList.get(i);
                    String item = sortModel.getSortLetter();
                    if (!alphaIndexer.containsKey(item)){
                        alphaIndexer.put(item, i);
                    }
                    break;
                case HEADER:
                    String header = (String) modelList.get(i);
                    if (!alphaIndexer.containsKey(header)){
                        alphaIndexer.put(header, i);
                    }
                    break;
            }
        }

        //整理的过程，记录出现的首字母
        Set<String> sectionLetters = alphaIndexer.keySet();
        ArrayList<String> sectionList = new ArrayList<>(sectionLetters);
        section = new String[sectionList.size()];
        sectionList.toArray(section);
        for (int i = 0; i < sectionList.size(); i++){
            System.out.println(section[i]);
        }
    }

    /**
     * 用于判断是header还是item
     */
    @Override
    public int getItemViewType(int position) {
        if(modelList.get(position) instanceof SortModel){
            return ITEM;
        }else {
            return HEADER;
        }
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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            switch (getItemViewType(position)){
                case ITEM:
                    convertView = layoutInflater.inflate(R.layout.list_item, null);
                    break;
                case HEADER:
                    convertView = layoutInflater.inflate(R.layout.list_header, null);
                    break;
            }
        }

        switch (getItemViewType(position)){
            case ITEM:
                SortModel sortModel = (SortModel) modelList.get(position);
                String name = sortModel.getPoemName();
                String writer = sortModel.getWriterName();
                TextView poemName = convertView.findViewById(R.id.item_name);
                TextView writerName = convertView.findViewById(R.id.item_writer);
                poemName.setText(name);
                writerName.setText(writer);
                break;
            case HEADER:
                convertView = layoutInflater.inflate(R.layout.list_header, null);
                String section = (String) modelList.get(position);
                TextView header = convertView.findViewById(R.id.item_header);
                header.setText(section);
                break;
        }
        return convertView;
    }

    @Override
    public Object[] getSections() {
        return section;
    }
    /**
     * xml文件里设置了列表的快速滑动，可以查看一下，这里就是实现接口方法从而实现功能
     */
    @Override
    public int getPositionForSection(int sectionIndex) {
        return alphaIndexer.get(section[sectionIndex]);
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }

}
