import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// My solution is based on the following video:
// https://youtu.be/pNichitDD2E?si=s85bINyDmNKAnOZA

class TweetInfo {
    int tweetId;
    int timeStamp;

    TweetInfo(int tweetId, int timeStamp) {
        this.tweetId = tweetId;
        this.timeStamp = timeStamp;
    }
}

class TweetInfoComparator implements Comparator<TweetInfo> {
    @Override
    public int compare(TweetInfo t1, TweetInfo t2) {
        return Integer.compare(t1.timeStamp, t2.timeStamp);
    }

}

class Twitter {

    HashMap<Integer, HashSet<Integer>> followMap;
    HashMap<Integer, ArrayList<TweetInfo>> tweetMap;
    int time = 0;

    public Twitter() {
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
        time = 0;
    }

    public void postTweet(int userId, int tweetId) {
        time++;

        TweetInfo tweet = new TweetInfo(tweetId, time);

        if (tweetMap.containsKey(userId)) {
            tweetMap.get(userId).add(tweet);
        } else {
            ArrayList<TweetInfo> list = new ArrayList<>();
            list.add(tweet);
            tweetMap.put(userId, list);
        }
    }

    public List<Integer> getNewsFeed(int userId) {

        List<Integer> ansTweets = new ArrayList<>();

        HashSet<Integer> followingSet = followMap.get(userId);
        if (followingSet == null) {
            followingSet = new HashSet<>();
            followingSet.add(userId);
        }

        PriorityQueue<TweetInfo> pq = new PriorityQueue<>(new TweetInfoComparator());

        for (Integer followee : followingSet) {
            if (tweetMap.containsKey(followee)) {

                List<TweetInfo> tweets = tweetMap.get(followee);

                for (TweetInfo tweet : tweets) {

                    pq.add(tweet);
                    if (pq.size() > 10) {
                        pq.poll(); // pq is min heap so remove smallest ID(ie oldest ID)
                    }

                }
            }
        }

        while (!pq.isEmpty()) {
            ansTweets.add(pq.poll().tweetId);
        }

        Collections.reverse(ansTweets);
        return ansTweets;
    }

    public void follow(int followerId, int followeeId) {

        if (followMap.containsKey(followerId)) {
            followMap.get(followerId).add(followeeId);
        } else {
            HashSet<Integer> hs = new HashSet<>();
            hs.add(followeeId);
            hs.add(followerId); // so that user can see their own posts too
            followMap.put(followerId, hs);
        }

    }

    public void unfollow(int followerId, int followeeId) {

        HashSet<Integer> followingSet = followMap.get(followerId);
        if (followingSet != null) {
            followingSet.remove(followeeId);
        }

    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
