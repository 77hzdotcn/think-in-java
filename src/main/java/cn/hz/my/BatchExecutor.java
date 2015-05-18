package cn.hz.my;

import java.util.List;

/**
 * 批量处理
 * 
 * @author wangxf
 *
 */
public class BatchExecutor<T> {
	
	private List<T> datas;
	private int page = 5;	//分页执行
	
	public BatchExecutor(List<T> datas){
		this.datas = datas;
	}
	
	public void batchExecute(){
		for(int i = 0, length = datas.size(); i < length; i++){
			
		}
	}
	
	public void getResult(){
		
	}

}
