package com.tiandu.comment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.comment.entity.TdProductComment;
import com.tiandu.comment.entity.mapper.TdProductCommentMapper;
import com.tiandu.comment.search.TdProductCommentCrateria;
import com.tiandu.comment.service.TdProductCommentService;
import com.tiandu.product.entity.TdProductStat;
import com.tiandu.product.service.TdProductStatService;

@Service("tdProductCommentService")
public class TdProductCommentServiceImpl implements TdProductCommentService{

	@Autowired
	private TdProductCommentMapper tdProductCommentMapper;
	
	@Autowired
	private TdProductStatService tdProductStatService;
	
	@Override
	public Integer deleteByPrimaryKey(Integer id) {
		TdProductComment comment = this.findOne(id);
		
		TdProductStat stat = tdProductStatService.findOne(comment.getProductId());
		// 总评论数
		stat.setReviewCount(stat.getReviewCount()-1);
		
		// 1、2 为差评  3、4为中评   5为好评
		switch (comment.getScore()) {
		case 1:
			stat.setNegativeRate(stat.getNegativeRate()-1);
			break;
		case 2:
			stat.setNegativeRate(stat.getNegativeRate()-1);
			break;
		case 3:
			stat.setNeutralRate(stat.getNeutralRate()-1);
			break;
		case 4:
			stat.setNeutralRate(stat.getNeutralRate()-1);
			break;
		case 5:
			stat.setPositiveRate(stat.getPositiveRate()-1);
			break;
		default:
			break;
		}
		// 可见评论数
		if(comment.getStatus() ==1){
			stat.setShowreviewCount(stat.getShowreviewCount()-1);
		}
		// 更新商品统计表
		tdProductStatService.updateByPrimaryKey(stat);
		
		return tdProductCommentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public TdProductComment findOne(Integer id) {
		return tdProductCommentMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer save(TdProductComment comment) {
		if(null != comment)
		{
			if(null != comment.getId()){
				tdProductCommentMapper.updateByPrimaryKey(comment);
			}else{
				tdProductCommentMapper.insert(comment);
			}
			
			// 查找评论商品的评论统计表
			TdProductStat stat = tdProductStatService.findOne(comment.getProductId());
			// 总评论数
			stat.setReviewCount(stat.getReviewCount()+1);
			
			// 1、2 为差评  3、4为中评   5为好评
			switch (comment.getScore()) {
			case 1:
				stat.setNegativeRate(stat.getNegativeRate()+1);
				break;
			case 2:
				stat.setNegativeRate(stat.getNegativeRate()+1);
				break;
			case 3:
				stat.setNeutralRate(stat.getNeutralRate()+1);
				break;
			case 4:
				stat.setNeutralRate(stat.getNeutralRate()+1);
				break;
			case 5:
				stat.setPositiveRate(stat.getPositiveRate()+1);
				break;
			default:
				break;
			}
			// 可见评论数
			if(comment.getStatus() ==1){
				stat.setShowreviewCount(stat.getShowreviewCount()+1);
			}else{
				stat.setShowreviewCount(stat.getShowreviewCount()-1);
			}
			// 更新商品统计表
			tdProductStatService.updateByPrimaryKey(stat);
			
		}
		return null;
	}

	@Override
	public List<TdProductComment> findBySearchCriteria(TdProductCommentCrateria sc) {
		Integer count = tdProductCommentMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		return tdProductCommentMapper.findBySearchCriteria(sc);
	}

	@Override
	public Integer deleteByProductId(Integer proId) {
		return tdProductCommentMapper.deleteByProductId(proId);
	}

	@Override
	public int getTotalPageCount(TdProductCommentCrateria sc) {
		Integer count = tdProductCommentMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		return sc.getTotalPageCount();
	}
	
}
