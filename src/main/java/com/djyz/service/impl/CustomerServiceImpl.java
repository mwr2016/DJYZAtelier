package com.djyz.service.impl;

import com.djyz.domain.Customer;
import com.djyz.mapper.CustomerMapper;
import com.djyz.service.CustomerService;
import com.djyz.util.AjaxRes;
import com.djyz.util.PageList;
import com.djyz.util.QueryVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;


    @Override
    public AjaxRes addCustomer(Customer customer) {
        AjaxRes ajaxRes = new AjaxRes();
        try{
            customerMapper.insert(customer);
            ajaxRes.setCustomer(customer);
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    /*查询所有客户---分页*/
    @Override
    public PageList getAllCustomer(QueryVo vo) {
        Page<Object> page = PageHelper.startPage(vo.getPageNum(), vo.getRows());

        List<Customer> customers = customerMapper.selectAll();

        /*分装pageList*/
        PageList pageList = new PageList();
        pageList.setTotal(page.getTotal());
        pageList.setRows(customers);

        return pageList;

    }

    /*登录*/
    @Override
    public AjaxRes customerLogin(Customer customer) {
        AjaxRes ajaxRes = new AjaxRes();
        //登录验证1
        try{
            Customer customer1 = customerMapper.customerLogin(customer);
            if(customer1 != null){
                ajaxRes.setSuccess(true);
                ajaxRes.setMsg("登录成功");
                ajaxRes.setCustomer(customer1);
            }
            else {
                ajaxRes.setSuccess(false);
                ajaxRes.setMsg("用户名或密码不正确");
            }
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("用户名或密码不正确");
        }
        return ajaxRes;
    }


    /*根据id查询客户*/
    @Override
    public Customer getCustomerWithId(Long custId) {
        return customerMapper.selectByPrimaryKey(custId);
    }

    /*上传照片*/
    @Override
    public AjaxRes addHeader(String headerPic) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            Customer customer = new Customer();
            customer.setHeaderPic(headerPic);
            customerMapper.updateByPrimaryKey(customer);
            ajaxRes.setSuccess(true);
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;

    }

    /*修改个人资料*/
    @Override
    public AjaxRes editCustomer(Customer customer) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            customerMapper.updateByPrimaryKey(customer);
            ajaxRes.setSuccess(true);
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    /*根据id删除客户*/
    @Override
    public AjaxRes deleteCustomer(Long custId) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            customerMapper.deleteByPrimaryKey(custId);
            ajaxRes.setSuccess(true);
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }


}
