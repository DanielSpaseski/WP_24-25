package mk.ukim.finki.wp.kol2022.g1.service.impl;

import mk.ukim.finki.wp.kol2022.g1.model.Employee;
import mk.ukim.finki.wp.kol2022.g1.model.EmployeeType;
import mk.ukim.finki.wp.kol2022.g1.model.Skill;
import mk.ukim.finki.wp.kol2022.g1.model.exceptions.InvalidEmployeeIdException;
import mk.ukim.finki.wp.kol2022.g1.repository.EmployeeRepository;
import mk.ukim.finki.wp.kol2022.g1.service.EmployeeService;
import mk.ukim.finki.wp.kol2022.g1.service.SkillService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static mk.ukim.finki.wp.kol2022.g1.service.FieldFilterSpecification.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final SkillService skillService;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, SkillService skillService){
        this.employeeRepository=employeeRepository;
        this.skillService=skillService;
    }
    @Override
    public List<Employee> listAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return this.employeeRepository.findById(id).orElseThrow(InvalidEmployeeIdException::new);
    }

    @Override
    public Employee create(String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate) {
        List<Skill> skills = skillId.stream().map(skillService::findById).collect(Collectors.toList());
        Employee employee = new Employee(name,email,password,type,skills,employmentDate);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Long id, String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate) {
        Employee employee = this.employeeRepository.findById(id).orElseThrow(InvalidEmployeeIdException::new);
        List<Skill> skills = skillId.stream().map(skillService::findById).collect(Collectors.toList());
        employee.setEmail(email);
        employee.setName(name);
        employee.setPassword(password);
        employee.setEmploymentDate(employmentDate);
        employee.setSkills(skills);
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee delete(Long id) {
        Employee employee = this.employeeRepository.findById(id).orElseThrow(InvalidEmployeeIdException::new);
        this.employeeRepository.delete(employee);
        return employee;
    }

    @Override
    public Page<Employee> findPage(Long skillId, Integer yearsOfService, Integer pageNum, Integer pageSize) {
        Specification<Employee> specification = Specification
                .where(filterEqualsV(Employee.class, "yearsOfService", yearsOfService))
                .and(filterEquals(Employee.class, "skill.id", skillId));

        return this.employeeRepository.findAll(
                specification,
                PageRequest.of(pageNum, pageSize)
        );
    }

    @Override
    public List<Employee> filter(Long skillId, Integer yearsOfService) {
        return null;
    }
}
