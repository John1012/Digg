package com.john.digg.app.showTopics;

import com.john.digg.command.Create3Topics;
import com.john.digg.command.CreateNewTopic;
import com.john.digg.command.DownvoteTopic;
import com.john.digg.command.GetTopicByVotes;
import com.john.digg.command.GetTopicSize;
import com.john.digg.command.UpvoteTopic;
import com.john.digg.middle.mapper.TopicMapper;
import com.john.digg.middle.model.TopicModel;
import com.john.digg.middle.sort.CreatedTimestampComparator;
import com.john.digg.middle.sort.VotedComparator;
import com.john.digg.source.TopicRepository;
import com.john.digg.usecase.UseCase;
import com.john.digg.usecase.UseCaseHandler;

import java.util.Comparator;
import java.util.List;

/**
 * Created by changjohn on 2017/5/5.
 */

public class TopicPresenter implements TopicContract.Presenter{
    private int mTopicSize = 0;
    private List<TopicModel> mModels;
    private Comparator<TopicModel> mCurComparator;

    private TopicContract.TopicListView mListView;
    private TopicContract.TopicDialogView mDialogView;
    private VotedComparator mVotedComparator;
    private CreatedTimestampComparator mCreatedTimestampComparator;
    private UseCaseHandler mHandler = UseCaseHandler.getInstance();
    private GetTopicSizeCallback mGetTopicSizeCallback = new GetTopicSizeCallback();
    private GetTopicsCallback mGetTopicsCallback = new GetTopicsCallback();

    public TopicPresenter(TopicContract.TopicListView view) {
        mListView = view;
        mVotedComparator = new VotedComparator();
        mCreatedTimestampComparator = new CreatedTimestampComparator();
    }

    public TopicPresenter(TopicContract.TopicDialogView view) {
        mDialogView = view;
    }

    @Override
    public void start() {
        UseCase useCase1 = new Create3Topics();
        UseCase useCase2 = new GetTopicSize();
        UseCase useCase3 = new GetTopicByVotes(TopicRepository.getInstance());

        mHandler.execute(useCase1, new Create3Topics.RequestValues(), null);
        mHandler.execute(useCase2, new GetTopicSize.RequestValues(), mGetTopicSizeCallback);
        mHandler.execute(useCase3, new GetTopicByVotes.RequestValues(0, mTopicSize), mGetTopicsCallback);
    }

    @Override
    public void listTopics() {
        UseCase useCase1 = new GetTopicSize();
        UseCase useCase2 = new GetTopicByVotes(TopicRepository.getInstance());
        mHandler.execute(useCase1, new GetTopicSize.RequestValues(), mGetTopicSizeCallback);
        mHandler.execute(useCase2, new GetTopicByVotes.RequestValues(0, mTopicSize), mGetTopicsCallback);
    }

    @Override
    public void likeTopic(String id) {
        UseCase usecase = new UpvoteTopic();
        mHandler.execute(usecase, new UpvoteTopic.RequestValues(id), null);
    }

    @Override
    public void dislikeTopic(String id) {
        UseCase usecase = new DownvoteTopic();
        mHandler.execute(usecase, new DownvoteTopic.RequestValues(id), null);
    }

    @Override
    public void sortByVote() {
        mModels.sort(mVotedComparator);
        mListView.refreshTopicModels(mModels);
    }

    @Override
    public void sortByTime() {
        mModels.sort(mCreatedTimestampComparator);
        mListView.refreshTopicModels(mModels);
    }

    @Override
    public void addTopic(String publish, String content) {
        UseCase usecase = new CreateNewTopic(TopicRepository.getInstance());
        mHandler.execute(usecase, new CreateNewTopic.RequestValues("John", content), new UseCase.UseCaseCallback() {
            @Override
            public void onSuccess(Object response) {
                mDialogView.notifyCreation();
            }

            @Override
            public void onError() {
                mDialogView.notifyError();
            }
        });
    }

    public class GetTopicSizeCallback implements UseCase.UseCaseCallback<GetTopicSize.ResponseValue> {

        @Override
        public void onSuccess(GetTopicSize.ResponseValue response) {
            mTopicSize = response.getTopicSize();
        }

        @Override
        public void onError() {

        }
    }

    public class GetTopicsCallback implements UseCase.UseCaseCallback<GetTopicByVotes.ResponseValue> {

        @Override
        public void onSuccess(GetTopicByVotes.ResponseValue response) {
            mModels = TopicMapper.transform(response.getTopics());
            sortByVote();
        }

        @Override
        public void onError() {

        }
    }
}
