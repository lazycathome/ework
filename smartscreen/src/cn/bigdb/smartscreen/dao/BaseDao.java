package cn.bigdb.smartscreen.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author william
 * 1、批量、单个删除<br>
 * 2、批量、单个保存<br>
 * 3、批量、单个更新<br>
 * 4、查询记录总数<br>
 * 5、查询记录(单个 记录和记录集合)<br>
 *
 */
public interface BaseDao {

	/**
	 * 返回需要查询的业务对象集合
	 * @param sql 需要执行的sql语句<br>
	 * @param vList 主键值列表集合，必须跟sql中需要绑定的字段匹配<br>
	 * 				例如：select * from a where id=? and b=?; vList.size=2
	 * @return 返回业务对象List集合
	 * @throws SQLException
	 */
	List<?> queryRes(String sql, List<Object> vList) throws SQLException;

	
	/**
	 * 根据指定sql和vList条件值获取记录总数<br>
	 * 		vList的顺序、长度必须和sql中需要绑定的变量值顺序、长度一致，否则不会执行。
	 * @param sql 需要执行的sql语句
	 * @param vList 需要给sql语句绑定的值
	 * @return 返回记录总数
	 * @throws SQLException
	 */
	long queryCount(String sql, List<Object> vList) throws SQLException;
	
	/**
	 * 根据指定sql和vList条件值删除记录<br>
	 * 		1、支持批量删除<br>
	 * 		2、支持单个删除<br>
	 * 		3、如果是批量删除，vList中则包含多个List<String>，且List的顺序、长度必须和sql中需要绑定的变量值顺序、长度一致，否则不会执行。<br>
	 * 		4、如果是单个删除，vList中则包含一个List<String>，且List的顺序、长度必须和sql中需要绑定的变量值顺序、长度一致，否则不会执行。<br>
	 * @param sql 需要执行的sql语句
	 * @param vList vList中嵌套List<String>,为List<String>数组，且List的顺序、长度必须和sql中需要绑定的变量值顺序、长度一致，否则不会执行。<br>
	 * @return 返回执行结果：此结果为List数组，数据删除失败则在List<Boolean>中保存false，成功则保存true.
	 * @throws SQLException
	 */
	List<Boolean> delete(String sql,final List<List<Object>> vList) throws SQLException;
	
	/**
	 * 根据指定sql和vList条件值保存记录<br>
	 * 		1、支持批量保存<br>
	 * 		2、支持单个保存<br>
	 * 		3、如果是批量保存，vList中则包含多个List<String>，且List的顺序、长度必须和sql中需要绑定的变量值顺序、长度一致，否则不会执行。<br>
	 * 		4、如果是单个保存，vList中则包含一个List<String>，且List的顺序、长度必须和sql中需要绑定的变量值顺序、长度一致，否则不会执行。<br>
	 * @param sql 需要执行的sql语句
	 * @param vList vList中嵌套List<String>,为List<String>数组，且List的顺序、长度必须和sql中需要绑定的变量值顺序、长度一致，否则不会执行。<br>
	 * @return 返回执行结果：此结果为List数组，数据保存失败则在List<Boolean>中保存false，成功则保存true.
	 * @throws SQLException
	 */
	List<Boolean> save(String sql,final List<List<Object>> vList) throws SQLException;
	
	/**
	 * 根据指定sql和vList条件值保存或者更新记录<br>
	 * 		1、支持多个表的多个属性名称批量保存或者更新<br>
	 * @param sql 需要执行的sql语句
	 * @param vList vList中嵌套List<String>,为List<String>数组，且List的顺序、长度必须和sql中需要绑定的变量值顺序、长度一致，否则不会执行。<br>
	 * @return 返回执行结果：此结果为List数组，数据保存失败则在List<Boolean>中保存false，成功则保存true.
	 * @throws SQLException
	 */
	List<Boolean> saveOrUpdateBatchData(List<String> sql,final List<List<Object>> vList) throws SQLException;
	
	/**
	 * 根据指定sql和vList条件值删除记录<br>
	 * 		1、支持批量更新<br>
	 * 		2、支持单个更新<br>
	 * 		3、如果是批量更新，vList中则包含多个List<String>，且List的顺序、长度必须和sql中需要绑定的变量值顺序、长度一致，否则不会执行。<br>
	 * 		4、如果是单个更新，vList中则包含一个List<String>，且List的顺序、长度必须和sql中需要绑定的变量值顺序、长度一致，否则不会执行。<br>
	 * @param sql 需要执行的sql语句
	 * @param vList vList中嵌套List<String>,为List<String>数组，且List的顺序、长度必须和sql中需要绑定的变量值顺序、长度一致，否则不会执行。<br>
	 * @return 返回执行结果：此结果为List数组，数据更新失败则在List<Boolean>中保存false，成功则保存true.
	 * @throws SQLException
	 */
	List<Boolean> update(String sql,final List<List<Object>> vList) throws SQLException;
	
	/**
	 * 获取全局序列
	 * @return 返回库里的全局序列
	 * @throws SQLException
	 */
	String getNextVal() throws SQLException;
	
	/**
	 * 根据指定sql和vList条件值获取记录总数<br>
	 * 		vList的顺序、长度必须和sql中需要绑定的变量值顺序、长度一致，否则不会执行。
	 * @param sql 需要执行的sql语句
	 * @param vList 需要给sql语句绑定的值
	 * @return 返回记录总数
	 * @throws SQLException
	 */
	long queryDataCount(String sql, List<Object> vList) throws SQLException;

	/**
	 * @param sql
	 * @throws SQLException
	 */
	void exceuteSql(String sql) throws SQLException;
	
}
