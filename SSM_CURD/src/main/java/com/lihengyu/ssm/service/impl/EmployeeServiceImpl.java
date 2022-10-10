package com.lihengyu.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lihengyu.ssm.mapper.EmployeeMapper;
import com.lihengyu.ssm.pojo.Employee;
import com.lihengyu.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author shkstart
 * @create 2022-07-26-16:44
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 查询所有的员工信息
     * @return
     */
    @Override
    public List<Employee> getAllEmployees() {
        return employeeMapper.getAllEmployees();
    }

    /**
     * 查询某页的某些员工信息
     * @param pageNum
     * @return
     */
    @Override
    public PageInfo<Employee> getEmployeeByPage(Integer pageNum) {
        //因为mybatis和数据库sql语句不会简单的实现分页功能，我们是根据一些插件实现的，所以我们要在逻辑层实现这个功能
        //首先，开启分页功能
        PageHelper.startPage(pageNum, 4);

        /**
         * 然后再查询所有的员工信息，注意，没写错，就是查询所有的
         * 我们的分页插件其实是一个interceptor拦截器，它会自动拦截我们的执行的查询功能，在sql语句中加入limit，来实现分页功能
         */
        List<Employee> list = employeeMapper.getAllEmployees();
        PageInfo<Employee> page = new PageInfo<>(list, 3);
        return page;
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeMapper.getEmployeeById(id);
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeMapper.addEmployee(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeMapper.deleteEmployee(id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeMapper.updateEmployee(employee);
    }
}
