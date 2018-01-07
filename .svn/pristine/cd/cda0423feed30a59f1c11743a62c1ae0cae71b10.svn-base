package com.ccx.models.util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
/**
 * 对分页的基本数据进行一个简单的封装
 */
public class Page<T> {
 
    private int pageNo = 1;//页码，默认是第一页
    private int pageSize = 5;//每页显示的记录数，默认是10
    private int total;//总记录数
    private int totalPage;//总页数
    private String sort;//排序列名
    private String order;//方向asc/desc
    private List<T> rows;//对应的当前页记录
    private Map<String, Object> params = new HashMap<String, Object>();//其他的参数我们把它分装成一个Map对象
 
    public int getPageNo() {
       return pageNo;
    }
 
    public void setPageNo(int pageNo) {
       this.pageNo = pageNo;
    }
 
    public int getPageSize() {
       return pageSize;
    }
 
    public void setPageSize(int pageSize) {
       this.pageSize = pageSize;
    }
 
    public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		//在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
		int totalPage = total%pageSize==0 ? total/pageSize : total/pageSize + 1;
	       this.setTotalPage(totalPage);
	}
 
    public int getTotalPage() {
       return totalPage;
    }
 
    public void setTotalPage(int totalPage) {
       this.totalPage = totalPage;
    }
 
    public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Map<String, Object> getParams() {
       return params;
    }
   
    public void setParams(Map<String, Object> params) {
       this.params = params;
    }
 
 
    
    
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [pageNo=");
		builder.append(pageNo);
		builder.append(", pageSize=");
		builder.append(pageSize);
		builder.append(", total=");
		builder.append(total);
		builder.append(", totalPage=");
		builder.append(totalPage);
		builder.append(", sort=");
		builder.append(sort);
		builder.append(", order=");
		builder.append(order);
		builder.append(", rows=");
		builder.append(rows);
		builder.append("]");
		return builder.toString();
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
 
}