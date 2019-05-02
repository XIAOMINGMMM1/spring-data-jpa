package cn.itcast.test;

import cn.itcast.domain.Customer;
import cn.itcast.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * 测试jpql查询
 */
public class JpqlTest {
    /**
     * 查询全部
     *      jpql：from cn.itcast.domain.Customer
     *      sql：SELECT * FROM cst_customer
     */
    @Test
    public void testFindAll(){
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        //3.CRUD  查询全部
       // String jpql="from cn.itcast.domain.Customer ";
        String jpql="from Customer ";
        Query query = em.createQuery(jpql);//创建Query查询对象   query才是执行键盘jpql的对象

        //发送查询并封装结果集
        List list = query.getResultList();
        for (Object obj:list ){
            System.out.println(obj);
        }


        //4.提交事务
        ts.commit();
        //5.释放资源
        em.close();

    }

    /**
     * 排序查询：倒叙查询全部客户（根据id倒序）
     *      sql：select * from cst_customer order by cust_id desc;
     *      jpql:from Customer order by custId;
     *
     * 进行jpql查询
     *      1.穿件query查询对象
     *      2.对参数进行赋值
     *      3.查询，并返回结果集
     */
    @Test
    public void testOrders(){
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        //3.CRUD  查询全部  根据id倒叙查询
        // String jpql="from cn.itcast.domain.Customer ";
        String jpql="from Customer order by custId desc ";
        Query query = em.createQuery(jpql);

        //发送查询并封装结果集
        List list = query.getResultList();
        for (Object obj:list ){
            System.out.println(obj);
        }


        //4.提交事务
        ts.commit();
        //5.释放资源
        em.close();

    }

    /**
     * 使用jpql查询统计客户的总数
     *      sql：select count(cust_id) from cst_customer;
     *      jpql:select count(custId) from Customer;
     */
    @Test
    public void testCount(){
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        //3.CRUD

        //根据jpql语句创建query查询对象
        String jpql="select count(custId) from Customer";
        Query query = em.createQuery(jpql);

        //对参数赋值(这里没有进行赋值)

        //发送查询并封装结果  getSingleResult  得到唯一的结果集
        Object result = query.getSingleResult();
        System.out.println(result);


        //4.提交事务
        ts.commit();
        //5.释放资源
        em.close();

    }

    /**
     * 分页查询
     *      sql：SELECT * FROM cst_customer limit ?,?;
     *      jpql:from Customer
     */
    @Test
    public void testPaged(){
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        //3.CRUD  分页查询

        //根据jpql语句创建query查询对象
        String jpql="from Customer";
        Query query = em.createQuery(jpql);

        //对参数赋值   分页参数
        //其实索引
        query.setFirstResult(0);
        //每页查询的条数
        query.setMaxResults(2);

        //发送查询并封装结果
        List list = query.getResultList();
        for (Object obj:list){
            System.out.println(obj);
        }


        //4.提交事务
        ts.commit();
        //5.释放资源
        em.close();

    }

    /**
     * 条件查询
     *      案例：查询客户名称以“xiao”开头的客户
     *      sql:select * from cst_customer where cust_name like ？;
     *      jpql:from Customer where custName like ？;
     */
    @Test
    public void testCondition(){
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        //3.CRUD  分页查询

        //根据jpql语句创建query查询对象
        String jpql="from Customer where custName like ?";
        Query query = em.createQuery(jpql);

        //对参数赋值   占位符参数
        //第一个参数：占位符索引的位置（从1开始），第二个参数：取值
       query.setParameter(1,"xiao%");

        //发送查询并封装结果
        List list = query.getResultList();
        for (Object obj:list){
            System.out.println(obj);
        }


        //4.提交事务
        ts.commit();
        //5.释放资源
        em.close();

    }
}
