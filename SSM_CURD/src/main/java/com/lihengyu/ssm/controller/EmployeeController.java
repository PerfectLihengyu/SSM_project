package com.lihengyu.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.lihengyu.ssm.pojo.Employee;
import com.lihengyu.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author shkstart
 * @create 2022-07-26-15:59
 */

/**
 * 查询所有的员工信息    /employee      get         √
 * 查询员工的分页信息    /employee/page/1      get    √
 * 根据id查询员工信息    /employee/1      get     √
 * 跳转到添加页面        /to/add      get        ↓
 * 添加员工信息          /employee      post     ↑  合二为一    √
 * 修改员工信息          /employee     put
 * 删除员工信息          /employee/1    delete
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //唉
    @RequestMapping("/os")
    public String os(){
        return "os";
    }

    //返回string类型是因为要返回某个页面
    //获取全部的员工
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String getAllEmployees(Model model){
        //查询所有的员工信息
        List<Employee> list = employeeService.getAllEmployees();
        //将员工信息在请求域中共享
        model.addAttribute("list", list);
        return "employee_list";
    }

    //按页数返回某些员工列表
    @RequestMapping(value = "/employee/page/{pageNum}", method = RequestMethod.GET)
    public String getEmployeesByPage(@PathVariable("pageNum") Integer pageNum, Model model){
        //获取员工的分页信息
        PageInfo<Employee> page = employeeService.getEmployeeByPage(pageNum);
        model.addAttribute("page", page);
        return "employee_list";
    }

    //按ID返回员工
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public String getEmployeeById(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employee_single";
    }

    //跳转到添加员工信息界面
    @RequestMapping(value = "/to/add", method = RequestMethod.GET)
    public String toAddEmployee(){
        return "add_employee";
    }
    //添加员工信息
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public String insertEmployee(Employee employee){
        employeeService.addEmployee(employee);
        return "redirect:/employee/page/1";
    }

    //删除员工信息
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeService.deleteEmployee(id);
        return "redirect:/employee/page/1";
    }

    //跳转到更新员工信息界面
    @RequestMapping(value = "/employee/update/{id}", method = RequestMethod.GET)
    public String toUpdateEmployee(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update_employee";
    }
    //执行更新操作
    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public String updateEmployee(Employee employee){
        employeeService.updateEmployee(employee);
        return "redirect:/employee/page/1";
    }
}
