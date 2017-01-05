package com.bloomp.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class Page<T> {
	
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	
	protected String orderBy = null;
	protected String order = null;
	
	/**
	 * 每页允许记录数
	 */
	protected int pageSize = 15;	//zhaoyz-默认15条，SB里的分页默认15条
	/**
	 * 总记录数
	 */
	protected long totalCount;
	/**
	 * 总页数
	 */
	protected long pageCount;
	/**
	 * 当前页数
	 */
	protected long pageNo = 1;
	
	protected long lastQueryTime;
	
	
	protected List<T> result =  new ArrayList<T>();
	
	protected List<T> results =  new ArrayList<T>();
	
	protected boolean autoCount = true;
	
	//是否需要分页
	private boolean pagination = true;
	
	public Page(int size){
		this.pageSize = 30;
		this.pageSize = size;
	}
	
	
	public int getFirst(){
		return (int)(((pageNo - 1) * pageSize) + 1);
	}
	
	/**
	 * 创建分页参数对象
	 * 
	 * @param pageSize
	 *            每页允许记录数 默认为30行，pageSize为负数时使用默认值
	 * @param pageNo
	 *            当前显示第几页
	 */
	public Page(int pageSize, int pageNo) {
		if (pageSize > 1) {
			this.pageSize = pageSize;
		}
		if (pageNo <= 0) {
			this.pageNo = 1;
		} else {
			this.pageNo = pageNo;
		}

	}

	public Page() {
		this.pageNo = 1;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}
	
	public Page<T> pageSize(final int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public long getPageNo() {
		return pageNo;
	}

	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}
	
	public Page<T> pageNo(final int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}


	public long getLastQueryTime() {
		return lastQueryTime;
	}

	public void setLastQueryTime(long lastQueryTime) {
		this.lastQueryTime = lastQueryTime;
	}

	

	public void setResults(List<T> results) {
		this.results = results;
	}
	
	public List<T> getResults() {
		return results;
	}

	public List<T> getResult() {
		return result;
	}
	
	public void setResult(List<T> result) {
		this.result = result;
	}

	public boolean isAutoCount() {
		return autoCount;
	}

	public void setAutoCount(boolean autoCount) {
		this.autoCount = autoCount;
	}


	public String getOrderBy() {
		return orderBy;
	}


	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}


	public String getOrder() {
		return order;
	}
	
	/**
	 * 是否已设置排序字段,无默认值.
	 */
	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(order));
	}

	public Page<T> orderBy(final String theOrderBy) {
		setOrderBy(theOrderBy);
		return this;
	}
	
	/**
	 * 设置排序方式向.
	 * 
	 * @param order 可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public void setOrder(final String order) {
		//检查order字符串的合法值
		String[] orders = StringUtils.split(StringUtils.lowerCase(order), ',');
		for (String orderStr : orders) {
			if (!StringUtils.equals(DESC, orderStr) && !StringUtils.equals(ASC, orderStr)) {
				throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
			}
		}

		this.order = StringUtils.lowerCase(order);
	}

	public Page<T> order(final String theOrder) {
		setOrder(theOrder);
		return this;
	}
	
	/**
	 * 取得总记录数, 默认值为-1.
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总记录数.
	 */
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 根据pageSize与totalCount计算总页数, 默认值为-1.
	 */
	public long getTotalPages() {
		if (totalCount < 0) {
			return -1;
		}

		long count = (totalCount + pageSize - 1) / pageSize;
		return count;
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * 取得下页的页号, 序号从1开始.
	 * 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (isHasNext()) {
			return (int)(pageNo - 1);
		} else {
			return (int)(pageNo);
		}
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 取得上页的页号, 序号从1开始.
	 * 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		if (isHasPre()) {
			return (int)(pageNo - 1);
		} else {
			return (int)(pageNo);
		}
	}


	public boolean isPagination() {
		return pagination;
	}


	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}

}
