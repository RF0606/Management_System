package com.peter.controller;

import com.peter.dao.DepartmentDao;
import com.peter.dao.EmployeeDao;
import com.peter.pojo.Department;
import com.peter.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("employees", employees);
        return "emps/list";
    }

    //添加员工
    //拿到add页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emps/add";
    }

    //创建新的employee并且回到list页面
    @PostMapping("/emp")
    public String addEmployees(Employee employee){
        System.out.println(employee);
        employeeDao.save(employee);
        return"redirect:/emps";
    }

    //修改员工
    //拿到员工数据信息
    //用model把员工数据传回update页面并
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.getEmployeeByID(id);
        System.out.println(employee);
        model.addAttribute("emp",employee);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emps/update";
    }

    //修改好了以后直接回到list页面
    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        //回到员工列表页面
        return "redirect:/emps";
    }

    //删除员工
    @GetMapping("/delEmp/{id}")
    public String delEmp(@PathVariable("id") Integer id){
        employeeDao.deleteEmployeeByID(id);
        return "redirect:/emps";
    }

}
