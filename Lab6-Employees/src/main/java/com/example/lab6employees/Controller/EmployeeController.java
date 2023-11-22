package com.example.lab6employees.Controller;

import com.example.lab6employees.Model.Employee;
import com.example.lab6employees.Services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {


private final EmployeeService employeeService;

@GetMapping("/get")
public ResponseEntity getEmployees(){

    return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployees());
}
@PostMapping("/add")
public ResponseEntity addEmployees(@Valid @RequestBody Employee employee, Errors errors){
    if(errors.hasErrors()){
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    employeeService.addEmployee(employee);
    return ResponseEntity.status(HttpStatus.OK).body("Employee added");

}
@PutMapping("/update/{id}")
public ResponseEntity updateEmployee(@PathVariable String id , @Valid@RequestBody Employee employee,Errors errors){
    if(errors.hasErrors()){
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    if(employeeService.updateEmployee(id,employee)){
        return ResponseEntity.status(HttpStatus.OK).body("Employee updated");
    }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");

}

@DeleteMapping("/delete/{id}")
public ResponseEntity deleteEmployee(@PathVariable String id){
if(employeeService.deleteEmployee(id)){
    return ResponseEntity.status(HttpStatus.OK).body("Employee deleted");
}return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");

}
@GetMapping("/getByPosition/{position}")
public ResponseEntity getEmployeeByPosition(@PathVariable String position){
    if (position.equals("supervisor") || position.equals("coordinator")){
        if(employeeService.getEmployeeByPosition(position).size()>0){
            return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeByPosition(position));

        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("there is no Employee in this position");
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("position must be supervisor or coordinator");

}
@GetMapping("/getByAge/{minAge}/{maxAge}")
public ResponseEntity getEmployeeByAge(@PathVariable int minAge,@PathVariable int maxAge){
    if(minAge>=25 && maxAge>minAge){
        if(employeeService.getEmployeeByAge(minAge,maxAge).size()>0){
            return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeByAge(minAge,maxAge));
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("there is no Employees with these age range");


    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("the age most be more than 25");

}
@PutMapping("/applyForAnnual/{id}")
public ResponseEntity ApplyForAnnualLeave(@PathVariable String id){

        if(employeeService.applyForAnnualLeave(id)){
            return ResponseEntity.status(HttpStatus.OK).body("Employee apply for annual leave");
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found or the Employee not meet the conditions");

}
@GetMapping("/getWithNoAnnual")
public ResponseEntity getWithNoAnnualLeave(){
    if(employeeService.getWithNoAnnualLeave().size()>0){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getWithNoAnnualLeave());
    }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no Employee have no annual leave");

}

@PutMapping("/promote/{userId}/{empId}")
public ResponseEntity promoteEmployee(@PathVariable String userId , @PathVariable String empId){

        if(employeeService.promoteEmployee(userId,empId)){
            return ResponseEntity.status(HttpStatus.OK).body("Employee promoted");
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("there is no employees with these ids or you not meet the conditions ");

}


}
