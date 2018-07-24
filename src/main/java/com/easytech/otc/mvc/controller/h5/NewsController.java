package com.easytech.otc.mvc.controller.h5;

import com.easytech.otc.enums.NewsEnum;
import com.easytech.otc.exception.BizException;
import com.easytech.otc.mapper.model.News;
import com.easytech.otc.mvc.controller.WebConst;
import com.easytech.otc.mvc.protocol.ACL;
import com.easytech.otc.mvc.protocol.Resp;
import com.easytech.otc.mvc.vo.NewsVo;
import com.easytech.otc.service.NewsService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * 讯息的发布与展示
 * author:xiujiang.liu
 * Date:2018/7/21
 * Time:15:13
 */
@RestController
@RequestMapping(WebConst.H5_V1_PREFIX + "/news/")
public class NewsController {

    @Autowired
    NewsService newsService;

    /**
     * 生成一条新的新闻
     * @param author
     * @param type
     * @param content
     * @param title
     * @return
     */
    @PostMapping(value="createNews")
    @ACL(authControl = false)
    public Resp createNews(@PathVariable String author,@PathVariable String type,@PathVariable String content,@PathVariable String title) {
        if(StringUtils.isBlank(type)){
            throw new BizException("新闻类型不可为空");
        }
        Integer newsType = Integer.parseInt(type);
        News news = new News(newsType,title,author,NewsEnum.Status.PUBLISH.ordinal(),content);
        checkNewsInfo(news);
        newsService.createNews(news);
        return  Resp.newSuccessResult();
    }

    /**
     * 更新news
     * @param news
     * @return
     */
    @PostMapping(value="updateNews")
    @ACL(authControl = false)
    public Resp<News> updateNews(@PathVariable News news){
        if(news == null){
            throw new BizException("没有需要更新的讯息");
        }
        News oldnews = newsService.getById(news.getId());
        if(oldnews == null){
            throw new BizException("该讯息不存在,无法做更新操作");
        }
        newsService.updateByIdSelective(news);
        return Resp.newSuccessResult();
    }


    /**
     * 取消显示新闻信息
     * @param id
     * @return
     */
    @PostMapping(value="cancelNews")
    @ACL(authControl = false)
    public Resp cancelNews(@PathVariable String id){
        Assert.hasText(id,"参数id不可为空");
        newsService.cancelNews(Integer.parseInt(id));
        return Resp.newSuccessResult();
    }

    /**
     * 获取讯息
     * @param newType
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Resp<PageInfo<News>> getPublishNewsByTypeAndPageInfo(@PathVariable String newType, String pageNum, String pageSize){
        Assert.hasText(newType,"新闻类型不可为空");
        Assert.hasText(pageNum,"请求页数不可为空");
        if(StringUtils.isBlank(pageSize)){
            pageSize = "10";
        }
        if(StringUtils.isBlank(pageNum)){
            pageNum = "0";
        }
        PageInfo<News> news = newsService.getNewsByTypeAndPageInfo(Integer.parseInt(newType),NewsEnum.Status.PUBLISH.ordinal(),Integer.parseInt(pageNum),Integer.parseInt(pageSize));
        return Resp.newSuccessResult(news);
    }

    /**
     * 获取具体news信息
     * @param id
     * @return
     */
    public Resp<News> getPublishNewsById(@PathVariable String id ){
        Assert.hasText(id,"信息id不可为空");
        News news = newsService.getById(Integer.parseInt(id));
        return Resp.newSuccessResult(news);
    }


   private void checkNewsInfo(News news){
       Assert.hasText(news.getAuthor(),"作者不可为空");
       Assert.hasText(news.getContent(),"内容不可为空");
       Assert.hasText(news.getTitle(),"新闻标题不可为空");

       if(news.getTitle().length() > 1000){
           throw new BizException("新闻标题过长，请重新输入");
       }

    }

}
