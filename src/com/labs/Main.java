package com.labs;
import java.util.*;

import java.util.stream.Collectors;
 public class Main{
        public static void main(String[] args) {
	    List<Employee> li= EmployeeRepository.getEmployees();
	    
//	   Q1.
	    double totalSalary=li.stream().collect(Collectors.summingDouble(e->e.getSalary()));
	    
	    System.out.println(totalSalary);
	    
//	   Q2.
	    Map<String,Long> dp = li.stream().filter(e->e.getDepartment()!=null).
	    		collect(Collectors.groupingBy(e->e.getDepartment().getDepartmentName(),TreeMap:: new ,Collectors.counting()));
	    
	    
	    		System.out.println(dp);
	    
	    Optional<Employee> seniorMostEmployee = li.stream().min(Comparator.comparing(Employee::getHireDate));
      if (seniorMostEmployee.isPresent()) {
       Employee seniorMost = seniorMostEmployee.get();
       System.out.println("Senior-most employee: " + seniorMost.getFirstName() + " hired on " + seniorMost.getHireDate());
      } else {
       System.out.println("No employees found.");
      }

// Q5.
      List<Employee> employeesWithoutDepartment = li.stream().filter(e -> e.getDepartment() == null).toList();
      System.out.println("Employees without department:");
      employeesWithoutDepartment.forEach(e -> System.out.println(e.getFirstName()));

// Q7.
      Map<Department, Long> deptEmployeeCounts = li.stream().filter(e -> e.getDepartment() != null).collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
      List<Department> topDepartments = deptEmployeeCounts.entrySet().stream().sorted(Map.Entry.<Department, Long>comparingByValue().reversed()).map(Map.Entry::getKey).collect(Collectors.toList());
      if (!topDepartments.isEmpty()) {
          Department topDept = topDepartments.get(0);
          System.out.println("department : " + topDept.getDepartmentName()+":  where highest count of employees");
          List<Employee> employeesInDept = li.stream()
                  .filter(emp -> Objects.equals(emp.getDepartment(), topDept))
                  .collect(Collectors.toList());
          employeesInDept.stream()
                  .map(emp -> emp.getFirstName() + " " + emp.getLastName())
                  .forEach(System.out::println);
      }
  }
      
      
   }
 
