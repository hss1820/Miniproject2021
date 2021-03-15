package kr.co.softsoldesk.database;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.co.softsoldesk.beans.DataBean;

public interface MapperInterface {
	
	@Insert("insert into spring_mvc_table (data1, data2, data3) values (#{data1}, #{data2}, #{data3})") //인설트문 작동 //#{data1}는 전달
	void insert_data(DataBean dataBean);  //모델어트리프트 생략된 것 DataBean dataBean 앞에
	
	@Select("select data1, data2, data3 from spring_mvc_table")
	List<DataBean> select_data();

}
