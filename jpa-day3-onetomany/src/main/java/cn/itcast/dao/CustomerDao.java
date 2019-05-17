package cn.itcast.dao;

import cn.itcast.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 符合springDataJpa的dao层接口的规范
 *      JpaRepository <操作的实体类类型，实体中主键的属性的类型>
 *          *封装l基本的CRUD操作
 *      JpaSpecificationExecutor<操作的实体类类型>
 *          *封装了复杂查询（eg：分页）
 */
public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {



}
