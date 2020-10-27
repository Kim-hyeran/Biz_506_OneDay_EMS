package com.biz.ems.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.ems.model.EmsVO;
import com.biz.ems.sql.EmsSQL;

public interface EmsDao {
	
	@Select("SELECT * FROM tbl_ems ORDER BY ems_id DESC")
	public List<EmsVO> selectAll();
	
	@Select("SELECT * FROM tbl_ems WHERE ems_id = #{ems_id}")
	public EmsVO findById(long ems_id);
	
	@InsertProvider(type=EmsSQL.class, method="ems_insert")
	@SelectKey(keyProperty = "ems_id", statement="SELECT ems_id.NEXTVAL FROM DUAL", resultType=Long.class, before=true)
	public int insert(EmsVO emsVO);
	
	@UpdateProvider(type=EmsSQL.class, method="ems_update")
	public int update(EmsVO emsVO);
	
	@Delete("DELETE FROM tbl_ems WHERE ems_id = #{ems_id}")
	public int delete(long ems_id);

}
