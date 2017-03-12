package moli.star.com.molidemo;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import moli.star.com.molidemo.utils.ConvertUtils;
import moli.star.com.molidemo.weidget.GridLayoutManager;
import moli.star.com.molidemo.weidget.GridSpacingItemDecoration;

public class MainActivity extends AppCompatActivity {
    private static final int GALLERY_COLUMN_NUMBER = 4;
    private RecyclerView mLeftRecyclerView;
    private RecyclerView mContentRecyclerView;
    private ControlAdapter mControlAdapter;
    private ContentAdapter mContentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLeftRecyclerView = (RecyclerView) findViewById(R.id.left_recycler_view);
        mContentRecyclerView = (RecyclerView) findViewById(R.id.content_recycler_view);

        initLeftPanel();
        initContentPanel();
    }

    private void initContentPanel() {
        mContentAdapter = new ContentAdapter();
        mContentAdapter.setDatas(generateDatas2());
        mContentRecyclerView.setLayoutManager(new GridLayoutManager(this, GALLERY_COLUMN_NUMBER));
        GridSpacingItemDecoration decoration = new GridSpacingItemDecoration(GALLERY_COLUMN_NUMBER, ConvertUtils.dipToPX(this, 20), true, 0);
        mContentRecyclerView.addItemDecoration(decoration);
        mContentRecyclerView.setAdapter(mContentAdapter);
    }

    private void initLeftPanel() {
        mControlAdapter = new ControlAdapter();
        mControlAdapter.setDatas(generateDatas());
        mLeftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mLeftRecyclerView.setAdapter(mControlAdapter);
    }

    private List<String> generateDatas() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("Android " + (i + 1));
        }
        return datas;
    }

    private List<String> generateDatas2() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            datas.add("Android " + (i + 1));
        }
        return datas;
    }

    class ControlAdapter extends RecyclerView.Adapter {
        private List<String> datas;

        public ControlAdapter() {
        }

        public void setDatas(List<String> datas) {
            this.datas = datas;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_control_item, parent, false);
            return new ControlViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ControlViewHolder controlViewHolder = (ControlViewHolder) holder;
            controlViewHolder.controlView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Hello World", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }

    static class ControlViewHolder extends RecyclerView.ViewHolder {
        TextView controlView;

        public ControlViewHolder(View itemView) {
            super(itemView);
            controlView = (TextView) itemView.findViewById(R.id.control_view);
        }
    }

    class ContentAdapter extends RecyclerView.Adapter {
        List<String> datas;
        private Point size = new Point();

        public ContentAdapter() {
            WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getSize(size);
            Log.e("TEST", "size.x == " + size.x + ", size.y == " + size.y);
            Log.e("TEST", "result == " + 1.0f * (size.x - (GALLERY_COLUMN_NUMBER + 1) * ConvertUtils.dipToPX(MainActivity.this, 20)
                    - ConvertUtils.dipToPX(MainActivity.this, 100)) );
        }

        public void setDatas(List<String> datas) {
            this.datas = datas;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_content_item, parent, false);
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params != null) {
                params.width = (int) (1.0f * (size.x - (GALLERY_COLUMN_NUMBER + 1) * ConvertUtils.dipToPX(parent.getContext(), 20)
                                        - ConvertUtils.dipToPX(parent.getContext(), 200)) / GALLERY_COLUMN_NUMBER);
                params.height = (size.y - ConvertUtils.dipToPX(parent.getContext(), 20)) / 2;
            }
            return new ContentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }

    static class ContentViewHolder extends RecyclerView.ViewHolder {

        public ContentViewHolder(View itemView) {
            super(itemView);
        }
    }
}
