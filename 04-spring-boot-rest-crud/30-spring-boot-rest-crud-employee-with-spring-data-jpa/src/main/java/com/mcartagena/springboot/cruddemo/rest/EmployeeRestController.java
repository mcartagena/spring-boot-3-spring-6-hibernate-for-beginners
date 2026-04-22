package com.mcartagena.springboot.cruddemo.rest;

import com.mcartagena.springboot.cruddemo.entity.Employee;
import com.mcartagena.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    EmployeeService employeeService;
    JsonMapper jsonMapper;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, JsonMapper jsonMapper) {
        this.employeeService = employeeService;
        this.jsonMapper = jsonMapper;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee findEmployeeById(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if (Objects.isNull(employee)) {
            throw new RuntimeException("Employee not found with id " + employeeId);
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(0);
        return employeeService.save(employee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PatchMapping("/employees/{employeeId}")
    public Employee updateEmployee(@PathVariable int employeeId,
                                   @RequestBody Map<String, Object> dataToUpdate) {
        Employee employee = employeeService.findById(employeeId);
        if(Objects.isNull(employee)){
            throw new RuntimeException("Employee not found with id " + employeeId);
        }

        if(dataToUpdate.containsKey("id")){
            throw new RuntimeException("Id is not allowed to update " + employeeId);
        }

        Employee employeeToUpdate = jsonMapper.updateValue(employee, dataToUpdate);

        return employeeService.save(employeeToUpdate);
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployeeById(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if (Objects.isNull(employee)) {
            throw new RuntimeException("Employee not found with id " + employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted Employee with id " + employeeId;
    }
}



