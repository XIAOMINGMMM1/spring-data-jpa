package cn.itcast.domain;


import javax.persistence.*;

@Entity
@Table(name = "cst_linkman")
public class LinkMan {
   /* CREATE TABLE cst_linkman (
            lkm_id bigint(32) NOT NULL AUTO_INCREMENT COMMENT '联系人编号(主键)',
    lkm_name varchar(16) DEFAULT NULL COMMENT '联系人姓名',
    lkm_gender char(1) DEFAULT NULL COMMENT '联系人性别',
    lkm_phone varchar(16) DEFAULT NULL COMMENT '联系人办公电话',
    lkm_mobile varchar(16) DEFAULT NULL COMMENT '联系人手机',
    lkm_email varchar(64) DEFAULT NULL COMMENT '联系人邮箱',
    lkm_position varchar(16) DEFAULT NULL COMMENT '联系人职位',
    lkm_memo varchar(512) DEFAULT NULL COMMENT '联系人备注',
    lkm_cust_id bigint(32) NOT NULL COMMENT '客户id(外键)',
    PRIMARY KEY (`lkm_id`),
    KEY `FK_cst_linkman_lkm_cust_id` (`lkm_cust_id`),
    CONSTRAINT `FK_cst_linkman_lkm_cust_id` FOREIGN KEY (`lkm_cust_id`) REFERENCES `cst_customer` (`cust_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lkm_id")
    private Long lkmId;//联系人编号(主键)
    @Column(name = "lkm_name")
    private String lkmName;//联系人姓名
    @Column(name = "lkm_gender")
    private String lkmGender;//联系人性别
    @Column(name = "lkm_phone")
    private String lkmPhone;//联系人办公电话
    @Column(name = "lkm_mobile")
    private String lkmMobile;//联系人手机
    @Column(name = "lkm_email")
    private String lkmEmail;//联系人邮箱
    @Column(name = "lkm_position")
    private String lkmPosition;//联系人职位
    @Column(name = "lkm_memo")
    private String lkmMemo;//联系人备注

    /**
     * 配置联系人到客户的多对一关系
     *      使用注解的形式配置多对一关系
     *      1.配置表关系
     *           @ManyToOne:配置多对一关系
     *           targetEntity：对方类实体类的字节码
     *      2.配置外键（中间表）
     *        @JoinColumn:配置外键
     *          name:外键的字段名 （在多的一方的建表语句里面说明的）
     *          referencedColumnName：参照的主表的主键字段名称
     *
     *   *配置外键的过程，配置多的一方，就会在多的一方维护外键
     *
     */
    @ManyToOne(targetEntity = Customer.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
    private Customer customer;

    public Long getLkmId() {
        return lkmId;
    }

    public void setLkmId(Long lkmId) {
        this.lkmId = lkmId;
    }

    public String getLkmName() {
        return lkmName;
    }

    public void setLkmName(String lkmName) {
        this.lkmName = lkmName;
    }

    public String getLkmGender() {
        return lkmGender;
    }

    public void setLkmGender(String lkmGender) {
        this.lkmGender = lkmGender;
    }

    public String getLkmPhone() {
        return lkmPhone;
    }

    public void setLkmPhone(String lkmPhone) {
        this.lkmPhone = lkmPhone;
    }

    public String getLkmMobile() {
        return lkmMobile;
    }

    public void setLkmMobile(String lkmMobile) {
        this.lkmMobile = lkmMobile;
    }

    public String getLkmEmail() {
        return lkmEmail;
    }

    public void setLkmEmail(String lkmEmail) {
        this.lkmEmail = lkmEmail;
    }

    public String getLkmPosition() {
        return lkmPosition;
    }

    public void setLkmPosition(String lkmPosition) {
        this.lkmPosition = lkmPosition;
    }

    public String getLkmMemo() {
        return lkmMemo;
    }

    public void setLkmMemo(String lkmMemo) {
        this.lkmMemo = lkmMemo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "LinkMan{" +
                "lkmId=" + lkmId +
                ", lkmName='" + lkmName + '\'' +
                ", lkmGender='" + lkmGender + '\'' +
                ", lkmPhone='" + lkmPhone + '\'' +
                ", lkmMobile='" + lkmMobile + '\'' +
                ", lkmEmail='" + lkmEmail + '\'' +
                ", lkmPosition='" + lkmPosition + '\'' +
                ", lkmMemo='" + lkmMemo + '\'' +
                '}';
    }
}
