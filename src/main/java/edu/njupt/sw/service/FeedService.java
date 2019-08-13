package edu.njupt.sw.service;

import edu.njupt.sw.dao.FeedDAO;
import edu.njupt.sw.model.Feed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedService {
    @Autowired
    FeedDAO feedDAO;


    /**
     * 拉模式
     *
     * @param maxId
     * @param userIds
     * @param count
     * @return
     */
    public List<Feed> getUserFeeds(int maxId, List<Integer> userIds, int count) {
        return feedDAO.selectUserFeeds(maxId, userIds, count);
    }

    /**
     * 添加feed
     *
     * @param feed
     * @return
     */
    public boolean addFeed(Feed feed) {

        feedDAO.addFeed(feed);
        return feed.getId() > 0;
    }


    /**
     * 推模式
     *
     * @param id
     * @return
     */
    public Feed getById(int id) {
        return feedDAO.getFeedById(id);
    }

}
