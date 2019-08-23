/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Employee;

/**
 *
 * @author asgerhs
 */
public class EmployeeDTO {
    
    private long id;
    private String name;
    private String address;
    

    public EmployeeDTO(Employee employee) {
        id = employee.getId();
        name = employee.getName();
        address = employee.getAddress();
    }

    
    
    
    
}
