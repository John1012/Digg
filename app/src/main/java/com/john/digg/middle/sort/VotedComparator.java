package com.john.digg.middle.sort;

import com.john.digg.middle.model.TopicModel;

import java.util.Comparator;

/**
 * Created by changjohn on 2017/5/5.
 */

public class VotedComparator implements Comparator<TopicModel> {

    @Override
    public int compare(TopicModel lhs, TopicModel rhs) {
        long lhsVotes = lhs.getVoted();
        long rhsVotes = rhs.getVoted();
        if (lhsVotes > rhsVotes) {
            return -1;
        } else if (lhsVotes < rhsVotes) {
            return 1;
        } else {
            return 0;
        }
    }
}
