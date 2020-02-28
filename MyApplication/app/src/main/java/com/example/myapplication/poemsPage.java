package com.example.myapplication;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.greendao.DaoSession;
import com.example.greendao.Poem;
import com.example.greendao.PoemDao;
import com.example.sortrecyclerview.ClearEditText;
import com.example.sortrecyclerview.SideBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication.poemPage.EnglishVersion_pp;
import static com.example.myapplication.poemPage.chineseVersion_pp;
import static com.example.myapplication.poemPage.poemName_pp;
import static com.example.myapplication.poemPage.authorName_pp;
import static com.example.myapplication.poemPage.kindOfPoem_pp;
import static com.example.myapplication.poemPage.poemNameEnglish_pp;
import static com.example.myapplication.poemPage.authorNameEnglish_pp;


public class poemsPage extends AppCompatActivity {
    private PoemDao poemDao;
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private LinearLayoutManager manager;
    public static List<Poem> Poems;
    private List<Poem> Poems_middle;//存poem根据orderWay和kind改的中间值
    private int orderWay = 1;//排列方式 1:根据诗名 2:作者名
    private String kind = "-1";
    private ClearEditText mClearEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poems);

        DaoSession daoSession = PoemList.getDaoSession();
        poemDao = daoSession.getPoemDao();

        initData();
        initView();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());

        Poems = poemDao.queryBuilder().orderAsc(PoemDao.Properties.PoemNameEnglish).build().list();
        Poems_middle = poemDao.queryBuilder().orderAsc(PoemDao.Properties.PoemNameEnglish).build().list();

        Button btn_orderByPoem = (Button)findViewById(R.id.orderByPoem);
        btn_orderByPoem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderWay = 1;
                if(kind.equals("-1")){
                    Poems_middle = poemDao.queryBuilder().orderAsc(PoemDao.Properties.PoemNameEnglish).build().list();
                }else{
                    Poems_middle = poemDao.queryBuilder().orderAsc(PoemDao.Properties.PoemNameEnglish).where(PoemDao.Properties.KindOfPoem.eq(kind)).build().list();
                }
                mAdapter.updateList(Poems_middle);
            }
        });

        Button btn_orderByAuthor = (Button)findViewById(R.id.orderByAuthor);
        btn_orderByAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderWay = 2;
                if(kind.equals("-1")){
                    Poems_middle = poemDao.queryBuilder().orderAsc(PoemDao.Properties.AuthorNameEnglish).build().list();
                }else{
                    Poems_middle = poemDao.queryBuilder().orderAsc(PoemDao.Properties.AuthorNameEnglish).where(PoemDao.Properties.KindOfPoem.eq(kind)).build().list();
                }
                mAdapter.updateList(Poems_middle);
            }
        });

        Button btn_farewell = (Button)findViewById(R.id.farewell);
        btn_farewell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind = "1";
                changeConditionInPoems(kind);
            }
        });

        Button btn_Frontier = (Button)findViewById(R.id.Frontier);
        btn_Frontier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind = "2";
                changeConditionInPoems(kind);
            }
        });

        Button btn_scenery = (Button)findViewById(R.id.scenery);
        btn_scenery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind = "3";
                changeConditionInPoems(kind);
            }
        });

        Button btn_history = (Button)findViewById(R.id.history);
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind = "4";
                changeConditionInPoems(kind);
            }
        });

        Button btn_other = (Button)findViewById(R.id.other);
        btn_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind = "0";
                changeConditionInPoems(kind);
            }
        });

        Button btn_showAll = (Button)findViewById(R.id.showAll);
        btn_showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind = "-1";
                orderWay = 1;
                Poems_middle = poemDao.queryBuilder().orderAsc(PoemDao.Properties.PoemNameEnglish).build().list();
                mAdapter.updateList(Poems_middle);
            }
        });

    }

    protected void initData() {
        poemDao.deleteAll();
        readFromFile();
    }

    private void readFromFile() {
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("a.txt");
            if (inputStream != null) {
                InputStreamReader inputReader = new InputStreamReader(inputStream);
                BufferedReader buffReader = new BufferedReader(inputReader);
                String line;
                //分行读取
                buffReader.readLine();
                while ((line = buffReader.readLine()) != null) {
                    String[] strArr = line.split(":");
                    Poem poem = new Poem(null,strArr[0],strArr[1],strArr[2],strArr[3],strArr[4],strArr[5],strArr[6],null,null);
                    poemDao.insert(poem);
                }
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        SideBar sideBar = (SideBar) findViewById(R.id.sideBar);
        TextView dialog = (TextView) findViewById(R.id.dialog);
        sideBar.setTextView(dialog);

        //设置右侧SideBar触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = mAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    manager.scrollToPositionWithOffset(position, 0);
                }
            }
        });

        mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
        final ImageView search = (ImageView)findViewById(R.id.search);
        //根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(final Editable s) {
                if(TextUtils.isEmpty(s.toString())) {
                    for (Poem poem : Poems_middle) {
                        if (poem.getAuthorNameHtml() != null || poem.getPoemNameHtml() != null) {
                            poem.setAuthorNameHtml(null);
                            poem.setPoemNameHtml(null);
                        }
                    }
                }
                mAdapter.updateList(Poems_middle);

                search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        filterData(s.toString());
                    }
                });

            }
        });

    }

    private void filterData(String filterStr) {
        List<Poem> filterDateList = new ArrayList<>();
        List<Poem> filter = Poems_middle;

        System.out.println(filterStr);

        //按搜索键之后若为空 则重置
        if(TextUtils.isEmpty(filterStr)) {
            filterDateList = filter;
            for (Poem poem : filter) {
                if (poem.getAuthorNameHtml() != null || poem.getPoemNameHtml() != null) {
                    poem.setAuthorNameHtml(null);
                    poem.setPoemNameHtml(null);
                }
            }
        }else{
            //若不为空，则按照列表搜索
            filterDateList.clear();
            for(Poem poem:filter){
                String poemName = poem.getPoemNameEnglish();
                String authorName = poem.getAuthorNameEnglish();
                /**
                 * ToDo... 优化
                 */
                //只在诗名中包含
                if(poemName.toLowerCase().contains(filterStr.toLowerCase()) && !authorName.toLowerCase().contains(filterStr.toLowerCase())){
                    int index = poemName.toLowerCase().indexOf(filterStr.toLowerCase());
                    int length = filterStr.length();
                    poem.setPoemNameHtml(poemName.substring(0,index) + "<font color='#f08519'><b>" + poemName.substring(index,index+length) + "</b></font>" + poemName.substring(index+length));
                    filterDateList.add(poem);
                    //只在作者名中包含
                }else if(authorName.toLowerCase().contains(filterStr.toLowerCase()) && !poemName.toLowerCase().contains(filterStr.toLowerCase())){
                    int index = authorName.toLowerCase().indexOf(filterStr.toLowerCase());
                    int length = filterStr.length();
                    poem.setAuthorNameHtml(authorName.substring(0,index) + "<font color='#f08519'><b>" + authorName.substring(index,index+length) + "</b></font>" + authorName.substring(index+length));
                    filterDateList.add(poem);
                }else if(authorName.toLowerCase().contains(filterStr.toLowerCase()) && poemName.toLowerCase().contains(filterStr.toLowerCase())){
                    int length = filterStr.length();
                    int index1 = poemName.toLowerCase().indexOf(filterStr.toLowerCase());
                    int index2 = authorName.toLowerCase().indexOf(filterStr.toLowerCase());
                    poem.setPoemNameHtml(poemName.substring(0,index1) + "<font color='#f08519'><b>" + poemName.substring(index1,index1+length) + "</b></font>" + poemName.substring(index1+length));
                    poem.setAuthorNameHtml(authorName.substring(0,index2) + "<font color='#f08519'><b>" + authorName.substring(index2,index2+length) + "</b></font>" + authorName.substring(index2+length));
                    filterDateList.add(poem);
                }
            }
        }


        mAdapter.updateList(filterDateList);

    }

    private void changeConditionInPoems(String kind) {
        if(orderWay == 1){
            Poems_middle = poemDao.queryBuilder().where(PoemDao.Properties.KindOfPoem.eq(kind)).orderAsc(PoemDao.Properties.PoemNameEnglish).build().list();
        }else if(orderWay == 2){
            Poems_middle = poemDao.queryBuilder().where(PoemDao.Properties.KindOfPoem.eq(kind)).orderAsc(PoemDao.Properties.AuthorNameEnglish).build().list();
        }else{
            Poems_middle = poemDao.queryBuilder().where(PoemDao.Properties.KindOfPoem.eq(kind)).build().list();
        }
        mAdapter.updateList(Poems_middle);
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @NonNull
        @Override
        public HomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(poemsPage.this).
                    inflate(R.layout.recycler_view_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull HomeAdapter.MyViewHolder holder, final int position) {
            int section = getSectionForPosition(position);
            //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
            if (position == getPositionForSection(section)) {
                holder.tvTag.setVisibility(View.VISIBLE);
                String s;
                if(orderWay == 1){
                    s = Poems.get(position).getPoemNameEnglish().substring(0,1);
                }else{
                    s = Poems.get(position).getAuthorNameEnglish().substring(0,1);
                }
                holder.tvTag.setText(s);
            } else {
                holder.tvTag.setVisibility(View.GONE);
            }

            //匹配成功则高亮
            if(Poems.get(position).getAuthorNameHtml()!=null){
                holder.authorName.setText(Html.fromHtml(Poems.get(position).getAuthorNameHtml()));
            }else{
                holder.authorName.setText(Poems.get(position).getAuthorNameEnglish());
            }

            //匹配成功则高亮
            if(Poems.get(position).getPoemNameHtml()!=null){
                holder.poemName.setText(Html.fromHtml(Poems.get(position).getPoemNameHtml()));
            }else{
                holder.poemName.setText(Poems.get(position).getPoemNameEnglish());
            }


            holder.btn_go_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(poemsPage.this,poemPage.class);
                    startActivity(intent);
                    //传值到poem.xml
                    poemName_pp = Poems.get(position).getPoemName();
                    poemNameEnglish_pp = Poems.get(position).getPoemNameEnglish();
                    authorName_pp = Poems.get(position).getAuthorName();
                    authorNameEnglish_pp = Poems.get(position).getAuthorNameEnglish();
                    kindOfPoem_pp = Poems.get(position).getKindOfPoem();
                    chineseVersion_pp = Poems.get(position).getChineseVersion();
                    EnglishVersion_pp = Poems.get(position).getEnglishVersion();
                }
            });
        }

        @Override
        public int getItemCount() {
            return Poems.size();
        }


        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tvTag;
            TextView authorName;
            TextView poemName;
            Button btn_go_in;


            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTag = (TextView)itemView.findViewById(R.id.tag);
                authorName = (TextView)itemView.findViewById(R.id.authorName1);
                poemName = (TextView)itemView.findViewById(R.id.poemName1);
                btn_go_in = (Button)itemView.findViewById(R.id.go_in1);
            }
        }

        /**
         * 提供给Activity刷新数据
         * @param list
         */
        public void updateList(List<Poem> list){
            Poems = list;
            notifyDataSetChanged();
        }

        public Object getItem(int position) {
            return Poems.get(position);
        }

        /**
         * 根据ListView的当前位置获取分类的首字母的char ascii值
         */
        public int getSectionForPosition(int position) {
            if(orderWay == 1){
                return Poems.get(position).getPoemNameEnglish().charAt(0);
            }else{
                return Poems.get(position).getAuthorNameEnglish().charAt(0);
            }

        }

        /**
         * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
         */
        public int getPositionForSection(int section) {
            for (int i = 0; i < getItemCount(); i++) {
                String sortStr;
                if(orderWay == 1){
                    sortStr= Poems.get(i).getPoemNameEnglish();
                }else{
                    sortStr= Poems.get(i).getAuthorNameEnglish();
                }
                char firstChar = sortStr.toUpperCase().charAt(0);
                if (firstChar == section) {
                    return i;
                }
            }
            return -1;
        }


    }

    public void back_to_main(View view) {
        poemsPage.this.finish();
    }

}