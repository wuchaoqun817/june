package com.june.pmsys.dao;

import java.util.List;

public interface BaseDao<T> {
		/**
		 * 根据id获取单条数据
		 * @param id
		 * @return
		 */
		public T getById(String id);
		
		/**
		 * 获取entity获取单条数据
		 * @param entity
		 * @return
		 */
		public T getByEntity(T entity);
		
		/**
		 * 根据entity获取所有数据
		 * @param entity
		 * @return
		 */
		public List<T> findList(T entity);
		
		/**
		 * 分页查询
		 * @param entity
		 * @return
		 */
		public List<T> findByPage(T entity);
		
		/**
		 * 查询所有数据
		 * @param entity
		 * @return
		 */		
		public List<T> findAll();		
		
		/**
		 * 插入数据
		 * @param entity
		 * @return
		 */
		public int insert(T entity);
		
		/**
		 * 更新数据
		 * @param entity
		 * @return
		 */
		public int update(T entity);
				
		/**
		 * 删除数据（一般为逻辑删除，更新del_flag字段为1）
		 * @param entity
		 * @return
		 */
		public int delete(T entity);

}
