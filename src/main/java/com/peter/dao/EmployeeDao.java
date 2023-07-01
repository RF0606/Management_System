package com.peter.dao;

import com.peter.pojo.Department;
import com.peter.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    //模拟数据库中的数据
    private static Map<Integer, Employee> Employees = null;
    @Autowired
    private DepartmentDao departmentDao;
    static {
        Employees = new HashMap<Integer, Employee>();
        Employees.put(1001,new Employee(1001,"AA","1060218144@QQ.COM",0,new Department(101,"教学部")));
        Employees.put(1002,new Employee(1002,"BB","2060218144@QQ.COM",1,new Department(102,"市场部")));
        Employees.put(1003,new Employee(1003,"CC","3060218144@QQ.COM",0,new Department(103,"教研部")));
        Employees.put(1004,new Employee(1004,"DD","4060218144@QQ.COM",1,new Department(104,"运营部")));
        Employees.put(1005,new Employee(1005,"EE","5060218144@QQ.COM",0,new Department(105,"后勤部")));
    }
    //主键自增
    private static Integer initId = 1006;
    //增加一个员工
    public void save(Employee employee){
        if(employee.getId() == null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentByID(employee.getDepartment().getId()));
        Employees.put(employee.getId(),employee);
    }

    //查询全部员工信息
    public Collection<Employee> getAll(){
        return Employees.values();
    }

    //同过ID查询员工(根据公部门查员工了)
    public Employee getEmployeeByID(Integer id){
        return Employees.get(id);
    }

    //通过ID删除员工
    public void deleteEmployeeByID(Integer id){
        Employees.remove(id);
    }
}
