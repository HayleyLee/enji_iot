package com.enji_iot.util.Util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageBean {
	private Collection<?> data;// 数据库中读取的记录集合
	private int totalCount;// 总记录
	private int paged = 1;// 当前页码
	private int pageSize = CommonUtil.parseInt(PropertiesUtil.getProperty("page.size"));// 实际每页条数
	private int totalPage;// 总页数

	public PageBean() {

	}

	// 该分页模板针对获取到的集合，需要进一步判断权限 的情况。
	public PageBean(List<Object> resources, int paged, int pageSize) {
		if (paged < 1)
			paged = 1;
		if (pageSize < 1)
			pageSize = 10;

		int size = resources.size();
		if (size < 1)
			return;

		this.totalCount = size;
		this.pageSize = pageSize;
		this.paged = paged;
		this.totalPage = getTotalPageMethod();
		if ((paged - 1) * pageSize >= size) {
			this.pageSize = 0;
			return;
		}

		if (pageSize > size) {
			pageSize = size;
			this.pageSize = pageSize;
		}

		List<Object> list = new ArrayList<Object>();
		int start = (paged - 1) * pageSize;
		int end = paged * pageSize > size ? size : paged * pageSize;
		for (int i = start; i < end; i++)
			list.add(resources.get(i));

		this.data = list;
	}

	// 该分页模板针对获取到的集合， 不需要进一步判断权限 的情况。
	public PageBean(Collection<?> data, int totalCount, int paged, int pageSize) {
		this.paged = paged;
		this.data = data;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.totalPage = getTotalPageMethod();
		if (data.size() < pageSize)
			this.pageSize = data.size();
	}

	/**
	 * 为oracle添加分页检索条件
	 * 
	 * @param param
	 * @return
	 */
	public Map<String, Object> setPageParam4Oracle(Map<String, Object> param) {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		param.put("begin", (this.getPaged() - 1) * this.getPageSize());
		param.put("end", this.getPaged() * this.getPageSize());
		return param;
	}

	/**
	 * 为mysql添加分页检索条件
	 * 
	 * @param param
	 * @return
	 */
	public Map<String, Object> setPageParam4mysql(Map<String, Object> param) {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		param.put("limit", this.getPageSize());
		param.put("offset", (this.getPaged() - 1) * this.getPageSize());
		return param;
	}

	/**
	 * 为mysql添加分页检索条件
	 * 
	 * @param param
	 * @return
	 */
	public void setPageParam4Mysql(Object o) throws Exception {
		if (o == null) {
			return;
		}
		Class<? extends Object> oc = o.getClass();
		Field limit= oc.getField("limit");
		limit.setAccessible(true);
		limit.set(o, this.getPageSize());
		Field offset= oc.getField("offset");
		offset.setAccessible(true);
		offset.set(o, (this.getPaged() - 1) * this.getPageSize());
	}

	// 总页数
	private int getTotalPageMethod() {
		if (totalCount < 1)
			return 0;
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		else
			return totalCount / pageSize + 1;
	}

	// 是否还有下一页
	@SuppressWarnings("unused")
	private boolean isNext() {
		return paged < totalPage;
	}

	// 是否还有上一页
	@SuppressWarnings("unused")
	private boolean isPrevious() {
		return paged > 1;
	}

	public Collection<?> getData() {
		return data;
	}

	public void setData(Collection<?> data) {
		this.data = data;
//		if (data.size() < pageSize)
//		this.pageSize = data.size();
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.totalPage = getTotalPageMethod();

	}

	public int getPaged() {
		return paged;
	}

	public void setPaged(int paged) {
		this.paged = paged;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalPage() {
		return totalPage;
	}
}
