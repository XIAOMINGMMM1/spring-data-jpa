package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)//声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器的配置信息
public class JpqlTest {
    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindJpql(){
        Customer customer = customerDao.findJpql("xiaomingmmm1");
        System.out.println(customer);
    }

    @Test
    public void testFindCustomerAndId(){
        Customer customer = customerDao.findCustNameAndId("xiaomingmmm1", 3l);
        System.out.println(customer);
    }

    /**
     * 测试jpql的更新操作   需要事务
     *      *springDataJpa使用jpql完成 更新/删除操作
     *          *需要手动添加事务的支持
     *          *默认执行结束之后，回滚事务
     * @Rollback(value = false)设置是否回滚
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdateCustomer(){
        customerDao.updateCustomer(2l,"黑马程序员");
    }

    //测试sql查询
    @Test
    public void testFindSql(){
        List<Object[]> list = customerDao.findSql("%x%");
        for (Object [] obj:list){
            System.out.println(Arrays.toString(obj));
        }

    }

    //测试方法命名规则的查询
    @Test
    public void testNaming(){
        Customer customer = customerDao.findByCustName("xiaomingmmm1");
        System.out.println(customer);
    }


    @Test
    public void testFindByNameLike(){
        List<Customer> list = customerDao.findByCustNameLike("%x%");
        for (Customer c:list){
            System.out.println(c);
        }
    }

    @Test
    public void testindByCustNameLikeAndCustIndustry(){
        List<Customer> list = customerDao.findByCustNameLikeAndCustIndustry("%x%","学生");
        for (Customer c:list){
            System.out.println(c);
        }
    }
}
