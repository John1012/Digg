package com.john.digg.middle.sort;

import com.john.digg.middle.model.TopicModel;

import java.util.Comparator;

/**
 * Created by changjohn on 2017/5/5.
 */

public class CreatedTimestampComparator implements Comparator<TopicModel> {

    @Override
    public int compare(TopicModel lhs, TopicModel rhs) {
        long lhsTimestamp = lhs.getCreatedTimestamp();
        long rhsTimestamp = rhs.getCreatedTimestamp();
        if (lhsTimestamp > rhsTimestamp) {
            return -1;
        } else if (lhsTimestamp < rhsTimestamp) {
            return 1;
        } else {
            return 0;
        }
    }
}
