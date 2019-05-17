package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.dao.LinkManDao;
import cn.itcast.domain.Customer;
import cn.itcast.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ObjectQueryTest {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    //测试对象导航查询（查询一个对象的时候，通过此对象查询所有关联的对象）
    @Test
    @Transactional //解决java代码中 no session 的问题
    public void testQuery1(){
        //查询id为2的客户
        Customer customer = customerDao.getOne(2L);
        //对象导航查询，此客户下的所有联系人
        Set<LinkMan> linkMans = customer.getLinkMans();
        System.out.println(linkMans);

    }

    /**
     * 对象导航查询：
     *  默认情况使用的是延迟加载的形式查询的
     *      调用get方法并不会立即发送查询，而是在使用关联对象的时候才会查询    延迟加载
     *
     *  修改设置，将延迟加载改为立即加载
     *      fetch,需要配置到夺标映射关系的注解上面
     *
     */
    @Test
    @Transactional //解决java代码中 no session 的问题
    public void testQuery2(){
        //查询id为2的客户
        Customer customer = customerDao.findOne(2L);

        //对象导航查询，此客户下的所有联系人
        Set<LinkMan> linkMans = customer.getLinkMans();
        System.out.println(linkMans);

    }

    /**
     * 从联系人对象导航查询他的所属客户
     *      *默认：立即加载
     *
     */
    @Test
    @Transactional //解决java代码中 no session 的问题
    public void testQuery3(){
        LinkMan linkMan = linkManDao.findOne(2l);
        //对象导航查询所属客户
        Customer customer = linkMan.getCustomer();
        System.out.println(customer);
    }
}
