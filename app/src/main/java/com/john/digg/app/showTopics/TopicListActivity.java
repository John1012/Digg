package com.john.digg.app.showTopics;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.john.digg.R;
import com.john.digg.middle.model.TopicModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TopicListActivity extends AppCompatActivity implements TopicContract.TopicListView, TopicDialogFragment.TopicDialogListener {
    public static final int SORT_BY_VOTE = 1;
    public static final int SORT_BY_TIME = 2;
    private TopicAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private DividerItemDecoration mDividerItemDecoration;
    private TopicPresenter mPresenter;
    private int mSortType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.list_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(mDividerItemDecoration);
        mRecyclerView.setLayoutManager(layoutManager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        mPresenter = new TopicPresenter(this);
        mPresenter.start();
        mSortType = SORT_BY_VOTE;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sort_by_vote:
                mSortType = SORT_BY_VOTE;
                sortTopics();
                return true;
            case R.id.menu_sort_by_time:
                mSortType = SORT_BY_TIME;
                sortTopics();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPostClick() {
        mPresenter.listTopics();
    }

    @Override
    public void onCancellClick() {
    }

    @Override
    public void refreshTopicModels(List<TopicModel> topics) {
        mAdapter = new TopicAdapter(topics);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void sortTopics() {
        if(SORT_BY_VOTE == mSortType) {
            mPresenter.sortByVote();
        } else if (SORT_BY_TIME == mSortType) {
            mPresenter.sortByTime();
        }
    }

    private void showDialog() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        // Create and show the dialog.
        TopicDialogFragment newFragment = new TopicDialogFragment();
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {
        private List<TopicModel> mTopics;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTxtVote;
            public TextView mTxtContent;
            public ImageView mImgUpvote;
            public ImageView mImgDownvote;
            public TextView mTxtContentInfo;
            public ViewHolder(View v) {
                super(v);
                mTxtVote = (TextView) v.findViewById(R.id.txt_vote);
                mTxtContent = (TextView) v.findViewById(R.id.txt_content);
                mImgUpvote = (ImageView) v.findViewById(R.id.img_up);
                mImgDownvote = (ImageView) v.findViewById(R.id.img_down);
                mTxtContentInfo = (TextView) v.findViewById(R.id.txt_content_info);
            }
        }

        public TopicAdapter(List<TopicModel> topics) {
            mTopics = topics;
        }

        @Override
        public TopicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final TopicModel topic = mTopics.get(position);
            holder.mTxtVote.setText(Long.toString(topic.getVoted()));
            holder.mTxtContent.setText(mTopics.get(position).getContent());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            Date date = new Date(topic.getCreatedTimestamp());
            holder.mTxtContentInfo.setText(topic.getPublisher() + " reported at " + sdf.format(date));
            holder.mImgUpvote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    topic.like();
                    holder.mTxtVote.setText(Long.toString(topic.getVoted()));
                    mPresenter.likeTopic(topic.getId());
                    sortTopics();
                }
            });
            holder.mImgDownvote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    topic.dislike();
                    holder.mTxtVote.setText(Long.toString(topic.getVoted()));
                    mPresenter.dislikeTopic(topic.getId());
                    sortTopics();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mTopics.size();
        }
    }
}
