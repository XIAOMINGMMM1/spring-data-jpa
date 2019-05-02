package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)//声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器的配置信息
public class CustomerDaoTest {
    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据id查询
     */
    @Test
    public void testFindOne(){
        Customer customer = customerDao.findOne(2l);
        System.out.println(customer);
    }

    /**
     * 根据id从数据库查询   使用getOne需要加上Transactional 事务的支持
     * @Transactional:保证getOne的正常运行
     *
     * findOne:
     *      em.find()       ：立即加载
     * getOne：
     *      em.getReference         :延迟加载
     */
    @Test
    @Transactional
    public void testGetOne(){
        Customer customer = customerDao.getOne(2l);
        System.out.println(customer);
    }

    /**
     * save:保存或者更新
     *      根据传递的对象是否纯在主键id，如果没有id主键属性：保存
     *      纯在id主键属性，根据id查询数据库，更新数据    先发送一条select语句，
     */
    @Test
    public void testSave(){
        Customer customer=new Customer();

        customer.setCustName("黑马程序员1");
        customer.setCustLevel("vip");
        customer.setCustIndustry("it教育");
        customerDao.save(customer);

    }

    @Test
    public void testUpdata(){
        Customer customer=new Customer();
        customer.setCustId(2l);
        customer.setCustName("黑马程序员3liubi");
        customer.setCustLevel("vip");
        customer.setCustIndustry("it教育");
        customerDao.save(customer);

    }

    /**
     * 先查询在删除
     */
    @Test
    public void  testDelete(){
        customerDao.delete(4l);
    }

    /**
     * 查询所有
     */
    @Test
    public void testfindAll(){
        List<Customer> list = customerDao.findAll();
        for (Object obj:list){
            System.out.println(obj);
        }
    }

    /**
     * 测试统计查询：查询客户的总数量
     *      count：统计总条数
     */
    @Test
    public void testCount(){
        long count = customerDao.count();//查询全部的客户数量
        System.out.println(count);
    }

    /**
     * 测试：判断id为4的客户是否纯在
     *      发送的sql语句为查询id为4 的客户的数量，以数量进行判断
     */
    @Test
    public void testExists(){
        boolean b = customerDao.exists(4l);
        System.out.println(b);
    }



}
