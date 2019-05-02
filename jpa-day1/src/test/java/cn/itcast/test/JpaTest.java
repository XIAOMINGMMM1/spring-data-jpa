package cn.itcast.test;

import cn.itcast.domain.Customer;
import cn.itcast.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {
    /**
     * 测试jpa的保存
     *      案例：保存一个用户到数据库中
     *
     *  jpa的操作步骤
     *      1.加载配置文件创建工厂(实体管理类工厂)对象
     *      2.通过实体管理类工厂获取实体管理器
     *      3.获取事务对象,开启事务
     *      4，完成CRUD操作
     *      5，提交事务（回滚事务）
     *      6，释放资源
     */
    /**
     * 保存客户
     */
    @Test
    public void testSave(){
      /*  //1.加载配置文件创建工厂(实体管理类工厂)对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");

        //2.通过实体管理类工厂获取实体管理器
        EntityManager em = factory.createEntityManager();
*/
      EntityManager em= JpaUtils.getEntityManager();

        //3.获取事务对象,开启事务
        EntityTransaction transaction = em.getTransaction();//获取事务对象
        transaction.begin();//开启事务

        //完成CRUD操作    保存一个客户到数据库中
        Customer customer=new Customer();
        customer.setCustName("xiaomingmmm");
        customer.setCustIndustry("学生");
        customer.setCustAddress("武汉");
        //保存
        em.persist(customer);//保存操作

        //5，提交事务（回滚事务）
        transaction.commit();

        //6，释放资源  和创建的顺序相反
        em.close();
       // factory.close();



    }


    /**
     * 根据id查询客户
     */
    @Test
    public void findById(){
        //1.通过工具类获取EntityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.CRUD    根据id查询数据
        /**
         * find：根据id查询数据
         *      class：查询数据的结果需要包装的实体类类型的字节码
         *      id:主键取值
         */
       // Customer customer = em.find(Customer.class, 1L);
        //getReference延迟加载，什么时候用户什么时候发送sql语句查询数据库
        Customer customer = em.getReference(Customer.class, 1L);
        System.out.println(customer);
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 删除客户
     */
    @Test
    public void testRemove(){
        //1.通过工具类获取EntityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.CRUD    删除客户   根据id来删除的
        //根据id查询客户
        //调用remove方法删除客户
        em.remove(em.find(Customer.class,1L));

        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 更新客户
     */
    @Test
    public void testUpdate(){
        //1.通过工具类获取EntityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.CRUD    更新客户  根据id在更新

        //查询客户
        Customer customer = em.find(Customer.class, 2L);
        //更新客户
        customer.setCustIndustry("it教育");
        em.merge(customer);


        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }
}
