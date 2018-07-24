package com.easytech.otc.service;

import com.easytech.otc.enums.NewsEnum;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.easytech.otc.mapper.model.News;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service
public class NewsService extends BaseService<News> {


    /**
     * 发布讯息
     * @param news
     */
    public void createNews(News news){
        saveOrUpdate(news);
    }

    /**
     * 撤销News
     * @param newsId
     */
    public void cancelNews(Integer newsId){
        News news = getById(newsId);
        if(news != null){
            news.setStatus(NewsEnum.Status.CANCELL.ordinal());
            news.setUpdateTime(new Date());
        }
        saveOrUpdate(news);
    }


    /**
     * 查找已发布的新闻信息
     * @param type
     * @return
     */
    public List<News> getAllPublishNewsByType(Integer type){
        News news = new News();
        news.setNewsType(type);
        news.setStatus(NewsEnum.Status.PUBLISH.ordinal());
        List<News> newsLists = select(news);
        if(!newsLists.isEmpty()){
            return newsLists;
        }
        return null;
    }


    /**
     * 根据类型查找讯息
     * @param type
     * @param status
     * @param page
     * @param rows
     * @return
     */
    public PageInfo<News> getNewsByTypeAndPageInfo(Integer type,Integer status,Integer page, Integer rows){
        News news = new News();
        news.setNewsType(type);
        news.setStatus(status);
        return queryPage(news,page,rows);
    }

    private void saveOrUpdate(News news){
        if(news == null){
            return;
        }
        if(null == news.getId()){
            news.setUpdateTime(new Date());
            news.setCreateTime(new Date());
            insert(news);
        }else{
            news.setUpdateTime(new Date());
            updateById(news);
        }
    }

}


