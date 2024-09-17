package com.spring.cruddemo.controller;

import com.spring.cruddemo.entity.Employee;
import com.spring.cruddemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String EmployeeLst(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "list-employees";
    }

    @GetMapping("/newEmployeeForm")
    public String newEmployeeForm(Model model){
        Employee employee= new Employee();
        model.addAttribute(employee);
        return "employee-form";
    }
    @GetMapping("/EmployeeFormUpdate")
    public String updateEmployee(@RequestParam("employeeId") int id, Model model){
       Employee employee = employeeService.findById(id);
       model.addAttribute(employee);

        return "employee-form";
    }
    @GetMapping("/EmployeeDelete")
    public String deleteEmployee(@RequestParam("employeeId") int id){
        employeeService.delete(id);
        return "redirect:/employees/list";
    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.save(employee);
        return "redirect:/employees/list";
    }
}
