package com.john.digg.data;

import java.util.UUID;

/**
 * Created by changjohn on 2017/4/23.
 */
public class Topic {
    public static final int MAX_CONTENT_CHAR = 255;
    protected String mUniqueID;
    protected String mPublisher;
    protected String mContent;
    protected long mCreatedTimestamp;
    protected long mUpvotes;
    protected long mDownvotes;

    static public class CREATOR {
        public static Topic newInstance(String publisher, String content) throws CreatedException {
            if(publisher == null || content == null) {
                throw new CreatedException("some arguments are null");
            } else if(content.length() > 255) {
                throw new CreatedException("content is over 255");
            } else {
                return new Topic(publisher, content);
            }
        }
    }
    protected Topic(String publisher, String content) {
        mUniqueID = UUID.randomUUID().toString();
        mPublisher = publisher;
        mContent = content;
        mCreatedTimestamp = System.currentTimeMillis();
        mUpvotes = 0;
        mDownvotes = 0;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Topic)) return false;
        Topic o = (Topic) other;
        return mContent.equals(o.mContent);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{publisher=").append(mPublisher).append(",");
        builder.append("content=").append(mContent).append(",");
        builder.append("createdTimestamp=").append(mCreatedTimestamp).append(",");
        builder.append("upvotes=").append(mUpvotes).append(",");
        builder.append("downvotes=").append(mDownvotes).append("}");

        return builder.toString();
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

    public long getVotes() {
        return mUpvotes - mDownvotes;
    }

    public void upvotes() {
        mUpvotes++;
    }

    public void downvotes() {
        mDownvotes++;
    }

    public String getId() {
        return mUniqueID;
    }
}
