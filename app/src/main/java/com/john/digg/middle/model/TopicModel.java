package com.john.digg.middle.model;

/**
 * Created by changjohn on 2017/5/3.
 */

public class TopicModel {
    protected String mId;
    protected String mPublisher;
    protected String mContent;
    protected long mCreatedTimestamp;
    protected long mVoted;

    protected TopicModel(String id,String publisher,String content,long createdTimestamp, long voted) {
        mId = id;
        mPublisher = publisher;
        mContent = content;
        mCreatedTimestamp = createdTimestamp;
        mVoted = voted;
    }

    public String getPublisher() {
        return mPublisher;
    }

    public String getContent() {
        return mContent;
    }

    public long getCreatedTimestamp() {
        return mCreatedTimestamp;
    }

    public long getVoted() {
        return mVoted;
    }

    public String getId() {
        return mId;
    }

    public void like() {
        mVoted++;
    }

    public void dislike() {
        mVoted--;
    }

    public static class Builder {
        private String mId;
        private String mPublisher;
        private String mContent;
        private long mCreatedTimestamp;
        private long mVoted;

        public Builder setId(String id) {
            mId = id;
            return this;
        }

        public Builder setPublisher(String publisher) {
            mPublisher = publisher;
            return this;
        }

        public Builder setContent(String content) {
            mContent = content;
            return this;
        }

        public Builder setCreatedTimestamp(long createdTimestamp) {
            mCreatedTimestamp = createdTimestamp;
            return this;
        }

        public Builder setVoted(long voted) {
            mVoted = voted;
            return this;
        }

        public TopicModel create() {
            TopicModel model = new TopicModel(mId,mPublisher,mContent,mCreatedTimestamp,mVoted);
            return model;
        }
    }
}
