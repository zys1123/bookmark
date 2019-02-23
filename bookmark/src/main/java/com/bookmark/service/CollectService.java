package com.bookmark.service;

import com.bookmark.entity.*;
import com.bookmark.entity.view.CollectView;
import com.bookmark.mapper.CollectMapper;
import com.bookmark.mapper.CommentMapper;
import com.bookmark.mapper.PraiseMapper;
import com.bookmark.utils.DateFormatUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 12425 on 2018/8/9.
 */
@Service
public class CollectService extends  BaseService{

    @Autowired
    CollectMapper collectMapper;

    @Autowired
    PraiseMapper praiseMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    FavoritesService favoritesService;

    @Transactional
    public void insertCollect(Collect collect,String newFavorites) {
        if(newFavorites!=null && !"".equals(newFavorites.trim())){
            collect.setFavoritesId(favoritesService.createDefaultFavorites(newFavorites,getUser().getId()).getId());
        }
        collect.setUserId(getUser().getId());
        collect.setIsDelete("No");
        if(collect.getType() == null || "".equals(collect.getType().trim())){
            collect.setType("PUBLIC");
        }
        collect.setCreateTime(DateFormatUtils.getCurrentDateLong());
        collect.setLastModifyTime(DateFormatUtils.getCurrentDateLong());
        collectMapper.insert(collect);
    }

    public int getUserCollectCount(Long userId) {
        return collectMapper.getUserCollectCount(userId);
    }

    public PageInfo<Collect> getCollectPageInfoByUserIdAndFavoritesId(Long userId, Long favoritesId, int page, int size) {
        PageHelper.startPage(page, size);
        List<Collect> collects = collectMapper.getCollectByUserIdAndFavoritesId(userId, favoritesId);
        PageInfo<Collect> pageInfo = new PageInfo<>(collects);
        return pageInfo;
    }

    //拼接
    public List<CollectView> getCollectViews(List<Collect> collects, User user, Favorites favorites) {
        List<CollectView> collectViewList = new LinkedList<>();
        CollectView collectView = null;
        for (Collect collect : collects) {
           // int praiseCount = praiseMapper.getPraiseCountByCollectId(collect.getId());
            boolean isPraise = false;
            List<Praise> praiseList = praiseMapper.getPraisesByCollectId(collect.getId());
            int commentCount = commentMapper.getCommentCountByCollectId(collect.getId());
            if(isPraise(praiseList,user.getId())){
                isPraise = true;
            }
            collectView = new CollectView(collect.getId(), user.getId(), user.getUserName(), user.getProfilePicture(),
                    collect.getTitle(), collect.getUrl(), collect.getDescription(),collect.getType(),collect.getCreateTime(),
                    collect.getRemark(), favorites.getId(), favorites.getName(), praiseList.size(), commentCount, isPraise);
            collectViewList.add(collectView);
        }
        return collectViewList;
    }

    public List<CollectView> getCollectViews(List<DatabaseCollect> databaseCollects){
        List<CollectView> collectViewList = new LinkedList<>();
        CollectView collectView = null;
        for (DatabaseCollect databaseCollect : databaseCollects) {
            boolean isPraise = false;
            List<Praise> praiseList = praiseMapper.getPraisesByCollectId(databaseCollect.getId());
            int commentCount = commentMapper.getCommentCountByCollectId(databaseCollect.getId());
            if(isPraise(praiseList,databaseCollect.getUser().getId())){
                isPraise = true;
            }
            collectView = new CollectView(databaseCollect, praiseList.size(), commentCount, isPraise);
            collectViewList.add(collectView);
        }
        return collectViewList;
    }

    private boolean isPraise(List<Praise> praiseList, Long userId){
        for(Praise p :praiseList){
            if(p.getUserId().equals(userId)){
                return true;
            }
        }
        return  false;
    }

    public Collect getCollectById(Long id){
        return collectMapper.selectByPrimaryKey(id);
    }

    public void updateCollect(Collect collect) throws Exception{
        Collect rcollect = collectMapper.selectByPrimaryKey(collect.getId());
        if(!isMyselfOperation(rcollect.getUserId())){
            throw new Exception("您没有权限删除！");
        }
        collect.setCharset(rcollect.getCharset());
        collect.setCreateTime(rcollect.getCreateTime());
        collect.setIsDelete(rcollect.getIsDelete());
        collect.setLastModifyTime(DateFormatUtils.getCurrentDateLong());
        if(collect.getDescription()==null || "".equals(collect.getDescription().trim())){
            collect.setDescription(rcollect.getDescription());
        }
        if(collect.getRemark()==null || "".equals(collect.getRemark().trim())){
            collect.setRemark(rcollect.getRemark());
        }
        if(collect.getType() == null || "".equals(collect.getType().trim())){
            collect.setType(rcollect.getType());
        }
        if(collect.getCategory() == null || "".equals(collect.getCategory().trim())){
            collect.setCategory(rcollect.getCategory());
        }
        collectMapper.updateByPrimaryKey(collect);
    }

    public void deleteCollect(Collect collect) throws Exception{
        Collect collect1 =collectMapper.selectByPrimaryKey(collect.getId());
        if(!isMyselfOperation(collect.getUserId())){
            throw new Exception("您没有权限删除！");
        }
        collectMapper.deleteByPrimaryKey(collect1.getId());
    }

    public PageInfo<DatabaseCollect> getCollectFavoritesByUserId(Long userId,Long favorites,int page,int size){
        PageHelper.startPage(page, size);
        List<DatabaseCollect> databaseCollects = collectMapper.getCollectFavoritesByUserId(userId,favorites);
        PageInfo<DatabaseCollect> pageInfo = new PageInfo<>(databaseCollects);
        return pageInfo;
    }

}
