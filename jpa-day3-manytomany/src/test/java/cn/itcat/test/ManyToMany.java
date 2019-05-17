package cn.itcat.test;

import cn.itcast.dao.RoleDao;
import cn.itcast.dao.UserDao;
import cn.itcast.domain.Role;
import cn.itcast.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToMany {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    /**
     * 保存一个用户，保存一个角色
     *
     * 多对多放弃维护权：被动的一方放弃
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testAdd(){

        User user=new User();
        user.setUserName("小李");
        Role role=new Role();
        role.setRoleName("java程序员");

        user.getRoles().add(role);
        role.getUsers().add(user);


        userDao.save(user);
        roleDao.save(role);
    }

    /**
     * 测试级联添加（保存一个用户保存用户关联角色）
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testCaseAdd(){

        User user=new User();
        user.setUserName("小李");
        Role role=new Role();
        role.setRoleName("java程序员");

        user.getRoles().add(role);



        userDao.save(user);
        roleDao.save(role);
    }


    /**
     * 案例：删除id为1的用户，同时删除它的关联对象
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testCaseRemove(){
        //查询1号用户
        User user = userDao.findOne(1l);
        //删除1号用户
        userDao.delete(user);

    }
}
