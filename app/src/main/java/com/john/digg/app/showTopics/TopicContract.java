package com.john.digg.app.showTopics;

import com.john.digg.middle.model.TopicModel;

import java.util.List;

/**
 * Created by changjohn on 2017/5/5.
 */

public interface TopicContract {
    interface Presenter {

        void start();

        void listTopics();

        void likeTopic(String id);

        void dislikeTopic(String id);

        void sortByVote();

        void sortByTime();

        void addTopic(String publish, String content);
    }

    interface TopicListView {
        void refreshTopicModels(List<TopicModel> topics);
    }

    interface TopicDialogView {
        void notifyCreation();
        void notifyError();
    }
}
