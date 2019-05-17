package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)//声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器的配置信息
public class SpecTest {
    @Autowired
    private CustomerDao customerDao;

    /**
     *根据条件： 查询单个对象
     */
    @Test
    public void testSpec(){
        //匿名内部类
        /**
         * 自定义查询条件
         *      1.实现Specification接口（提供泛型：查询的对象类型）
         *      2.实现toPredicate方法（构造查询条件）
         *      3.需要借助方法参数中的两个参数（
         *      root：获取需要查询的对象属性
         *      CriteriaBuilder:构造查询条件的，内部封装了很多的查询条件（模糊匹配，精准匹配...）
         *      ）
         *
         * 案例：根据客户名称查询，查询客户名为传智播客的客户
         *      查询条件
         *          1.查询方式
         *              cb对象中
         *          2.比较的属性名称
         *              root对象中
         */
        Specification<Customer> spec=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1.获取比较的属性
                Path<Object> custName = root.get("custName");
                //2.构造查询条件  select * from cst_customer where cust_name = ?
                /**
                 * 第一个参数：需要比较的属性（Path）
                 * 第二个参数：需要比价的取值
                 */
                Predicate predicate = cb.equal(custName, "xiaomingmmm1");//进行精准的匹配（比较属性，比较属性的取值）
                return predicate;
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }


    /**
     * 多条件查询
     *      案例：客户名和客户所属行业查询
     */
    @Test
    public void testSpec1(){
        /**
         * root:获取属性
         *      客户名
         *      所属行业
         * cb：构造查询
         *      1.构造客户名的精准匹配查询
         *      2.构造所属行业的精准匹配查询
         *      3.将以上2个查询联系起来
         */
        Specification<Customer> spec=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");//客户名
                Path<Object> custIndustry = root.get("custIndustry");//所属行业
                //构造查询
                //1.构造客户名的精准匹配查询
                Predicate p1 = cb.equal(custName, "xiaomingmmm1");//第一个参数，path(属性)面第二个参数，属性的取值
                Predicate p2 = cb.equal(custIndustry, "学生");
                //将多个查询条件组合到一起，组合（与关系；或关系）
                Predicate and = cb.and(p1, p2);


                return and;
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);

    }


    /**
     * 案例；完成根据客户名称的模糊匹配
     *
     * equal:直接的到path对象（属性），然后直接进行比较即可
     * gt,lt,ge,le,like:得到path对象，根据path对象指定比较的参数类型，然后再去进行比较
     *      指定参数类型：path.as(类型的字节码对象);
     */
    @Test
    public void testSpec3(){
        //构造查询条件
        Specification<Customer> spec=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //查询属性
                Path<Object> custName = root.get("custName");
                //查询方式：模糊查询
                Predicate predicate = cb.like(custName.as(String.class), "x%");
                return predicate;
            }
        };
       /* List<Customer> list = customerDao.findAll(spec);
        for (Object o:list){
            System.out.println(o);
        }*/

       //添加排序
        //创建排序对象,需要调用构造方法实例化sort对象
        //第一个参数：排序的顺序
        //  desc：倒叙  asc升序
        //第二个参数：排序的属性名称
        Sort sort=new Sort(Sort.Direction.DESC,"custId");
        List<Customer> list = customerDao.findAll(spec, sort);
        for (Object o:list){
            System.out.println(o);
        }
    }


    /**
     * 分页查询
     *      Specification：查询条件
     *      Pageable:分页参数
     *          分页参数：查询页码，每页查询条数
     *      findAll(Specification,Pageable) 带有条件的分页
     *      findAll（Pageable）没有条件的分页
     *
     * 返回：Page(springDatejpa为我们封装号的pageBean对象，数据列表，总条数)
     */
    @Test
    public void testSpec4(){
        Specification<Customer> spec=null;
        //PageRequest对象是Pageable接口的实现类
        /**
         * 参数1：当前查询的页数：0开始
         * 参数2：每页查询的数量
         */
        Pageable pageable=new PageRequest(0,2);
        //分页查询
        Page<Customer> page = customerDao.findAll(spec, pageable);
        System.out.println(page.getContent());//得到数据列表
        System.out.println(page.getTotalPages());//得到总页数
        System.out.println(page.getTotalElements());//得到总条数

    }
}
