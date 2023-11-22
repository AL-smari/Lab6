package com.example.lab6employees.Services;

import com.example.lab6employees.Model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmployeeService {

    ArrayList<Employee>employees=new ArrayList<>();

    public ArrayList<Employee> getEmployees(){

        return employees;
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public boolean updateEmployee(String id , Employee employee){
        for (int i = 0; i < employees.size(); i++) {
            if(employees.get(i).getId().equals(id)){
                employees.set(i,employee);
                return true;
            }

        }
        return false;
    }

    public boolean deleteEmployee(String id){
        for (int i = 0; i < employees.size(); i++) {
            if(employees.get(i).getId().equals(id)){
                employees.remove(i);
                return true;
            }

        }
        return false;
    }

    public ArrayList<Employee> getEmployeeByPosition(String position){
        ArrayList<Employee> match = new ArrayList<>();

        for (int i = 0; i < employees.size(); i++) {
            if(employees.get(i).getPosition().equals(position)){
                match.add(employees.get(i));
            }

        }

        return match;
    }

    public ArrayList<Employee> getEmployeeByAge(int minAge,int maxAge){
        ArrayList<Employee> match =new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            if(employees.get(i).getAge()>=minAge && employees.get(i).getAge()<=maxAge){
                match.add(employees.get(i));
            }

        }
        return match;
    }

    public boolean applyForAnnualLeave(String id){
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(id)&&!employees.get(i).isOnLeave()&&employees.get(i).getAnnualLeave()>=1){
                    employees.get(i).setAnnualLeave(employees.get(i).getAnnualLeave()-1);
                    employees.get(i).setOnLeave(true);
                    return true;


            }

        }
        return false;
    }

    public ArrayList<Employee> getWithNoAnnualLeave(){
        ArrayList<Employee> noAnnual = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            if(employees.get(i).getAnnualLeave()==0){
                noAnnual.add(employees.get(i));
            }

        }
        return noAnnual;
    }
    public boolean promoteEmployee(String userId,String empId){
        for (int i = 0; i < employees.size(); i++) {
            if(employees.get(i).getId().equals(userId))
                if(employees.get(i).getPosition().equals("supervisor")){
                    for (int j = 0; j < employees.size(); j++) {
                        if(employees.get(j).getId().equals(empId)){
                            if(employees.get(j).getPosition().equals("coordinator")&&employees.get(j).getAge()>=30&&!employees.get(j).isOnLeave()){
                                employees.get(j).setPosition("supervisor");
                                return true;
                            }
                        }

                    }
                }

        }
        return false;
    }
}
