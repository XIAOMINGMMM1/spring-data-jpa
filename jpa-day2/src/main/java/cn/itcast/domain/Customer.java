package cn.itcast.domain;

import javax.persistence.*;

/**
 * 1.实体类和表的映射关系
 * @Eitity
 * @Table
 *2.类中属性和表中字段的映射关系
 * @Id
 * @GeneratedValue
 * @Column
 */
@Entity
@Table(name = "cst_customer")
public class Customer {
    /**
     * @Id:声明主键的配置      javax.persistence.Id;
     * @GeneratedValue :配置主键的生成策略
     *      GenerationType.IDENTITY：自增  mtsql
     *              *底层数据库必须支持自动增长（底层数据支持的自动增长方式，对id自增）
     *      GenerationType.SEQUENCE  :序列 oracle（不支持自增）
     *              *底层数据库支持序列
     *      GenerationType.TABLE ：jpa提供的一种机制，通过一张数据库表的形式帮助我们完成主键的自增
     *      GenerationType.AUTO：程序自动的帮助我们选择主键生成策略
     * @Column:配置属性和字段的映射关系
     *      name：数据库中的字段的名称
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId;            //客户主键

    @Column(name = "cust_name")
    private String custName;        //客户名称

    @Column(name = "cust_source")
    private String custSource;      //客户来源

    @Column(name = "cust_industry")
    private String custIndustry;    //客户所属行业

    @Column(name = "cust_level")
    private String custLevel;       //客户级别

    @Column(name = "cust_address")
    private String custAddress;     //客户地址

    @Column(name = "cust_phone")
    private String custPhone;       //客户电话

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custPhone='" + custPhone + '\'' +
                '}';
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }
}
