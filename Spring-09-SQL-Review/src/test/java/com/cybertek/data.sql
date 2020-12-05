select *
from departments;

select *
from employees;

select *
from regions;

select employee_id, first_name, department
from employees;

select *
from employees
where department = 'Sports';

select *
from employees
where department like '%nit%';

select *
from employees
where department like 'F%nit%';

select *
from employees
where salary>100000;

select *
from employees
where salary<100000;

select *
from employees
where department='Clothing'
and salary>90000
and region_id=2;

select *
from employees
where department='Clothing'
or salary>90000;

select *
from employees
where salary<40000
and (department='Clothing' or department='Pharmacy');

select *
from employees
where department != 'Sports';

select *
from employees
where department <> 'Sports';

select *
from employees
where NOT department <> 'Sports';

select *
from employees
where email is not null;

select *
from employees
where department='Sports'
or department='First Aid'
or department='Toys'
or department='Garden';

select *
from employees
where department in ('Sports','First Aid','Toys','Garden');

select *
from employees
where salary between 80000 and 100000;

--Write a query that returns the first name and email of females that work in the tools
--departments having a salary greater than 110,000

select first_name,last_name,email
from employees
where gender='F'
and department='Tools'
and salary>110000;

--write a query that returns the first_name and hire date of those employees who earn
--more than 165,000 as well as those employees that work in sports department and also
--happen to be men

select first_name, hire_date
from employees
where salary>165000
or (department='Sports' and gender='M');

select *
from employees
order by employee_id desc;

select distinct department
from employees;

select distinct department
from employees
fetch first 3 rows only;

select salary as yearly_salary
from employees;

select * from students;

select student_name ,age
from students
order by age desc
fetch first 4 rows only;

select *
from students
where (
    (age<=20 and student_no between 3 and 5)
    or student_no =7
    )
    or (age>20 and student_no>=4);

select UPPER(first_name), LOWER(department)
from employees;

select length(first_name)
from employees;

select trim('   Hello There   ');

select length('   Hello There   ');
select length(trim('   Hello There   '));

select first_name || ' ' || employees.last_name as full_name
from employees;

select (salary<140000)
from employees;

select (salary>140000) as higly_paid
from employees
order by salary desc;

select department,(department like '%oth%')
from employees;

select 'This test data' test_data;

select substring('this is test data' from 1 for 4) as test_data;

select substring('This is test data' FROM 9) as test_data;

select department, replace(department,'Clothing','Clothes')
from departments;

select MAX(salary)
from employees;

select MIN(salary)
from employees;

SELECT ROUND(AVG(salary))
from employees;

SELECT COUNT(employee_id)
from employees;

SELECT COUNT(email)
FROM employees;

SELECT SUM(salary)
FROM employees
WHERE department = 'Clothing';

--write the query that returns all og the records and columns from the professors
-- table but shortens the department names to only the first three chars in upper case

select last_name,upper(substring(department,1,3)) as departments,salary,hire_date
from professors;

select max(salary) as max_salary, min(salary) as min_salary
from employees;

select count(*),department
from employees
group by department;

select sum(salary) as total_salary, department
from employees
group by department;

select MIN(salary) min_salary,MAX(salary) max_salary,AVG(salary) average_salary,
       count(*) total_number_employees,department
from employees
group by department;

select sum(salary) as total_salary,department, region_id
from employees
where region_id in (4,5,6,7)
group by department, region_id;

select department,count(*)
from employees
group by department
order by department;

select department,count(*)
from employees
group by department
having count(*)<36
order by department;

--show all unique domain and number of employees

select *
from employees;

select count(*),
       substring(email,position('@' in email)+1,length(email)) email_domain
from employees
where email is not null
group by email_domain;

select d.department
from employees e ,departments d;

select department
from employees;

select department
from departments;

select *
from employees
where department not in(select department
                        from departments) ;

select *
from (select * from employees where salary>150000) as a;

select first_name,last_name,(select first_name from employees)
from employees;

--return al employees that work in electronic division

select * from employees
where department in(select department from departments where division = 'Electronics');

--Return all employees work in ASIA or Canada and make more 130,000

SELECT *
from employees
where region_id in(select region_id
                   from regions
                   where country ='Asia' or country='Canada');

select region_id
from regions
where country ='Asia' or country='Canada';

SELECT first_name, (select max(salary) from employees) - salary
from employees
where region_id in(select region_id
                   from regions
                   where country ='Asia' or country='Canada');

--Write a query that returns all of those employees that work in the kids division
--and the dates at which those employees were hired is greater than all of the hire_dates
--of employees who work in the maintenace department

select * from employees
where department =
    ANY (select departments.department from departments where departments.division='Kids')
and hire_date > All (select hire_date from employees where department='Maintenance');

--Write a query that returns the names of those students that are taking the
-- courses Physics and US History

select student_name
from students
where student_no in (select student_enrollment.student_no from student_enrollment
                    where course_no in (select course_no from courses
                                        where course_title in('Physics','US History')));


select first_name, salary,
CASE
    when salary<100000 then 'UNDER PAID'
    when salary>100000 then 'PAID WELL'
    else 'UNPAID'
END as category
from employees;

SELECT category, count(*)
from (select first_name,salary,
             CASE
                 when salary<100000 then 'UNDER PAID'
                 when salary>100000 and salary <160000 then 'PAID WELL'
                 else 'Executive'
                 END as category
    from employees) a
group by category;

select first_name, country, department
from employees e, regions r
where r.region_id = e.region_id;

select first_name,email,d.division
from employees e , departments d, regions r
where e.department = d.department
and e.region_id = r.region_id;

select first_name, country
from employees e INNER JOIN regions r
on e.region_id = r.region_id;

select first_name, email, division
from employees e INNER JOIN departments d
on e.department = d.department
where email IS NOT NULL;

select first_name,email,division,country
from employees e INNER JOIN departments d
on e.department = d.department INNER JOIN regions r
on e.region_id = r.region_id
where email IS NOT NULL;

SELECT distinct department from employees;--27
SELECT distinct department from departments;--24

select distinct e.department, d.department
from employees e full join departments d
    on e.department = d.department;

select department
from employees
UNION
Select department
from departments;

select department
from employees
UNION
Select department
from departments
UNION
select country
from regions;

select department
from employees
EXCEPT
Select department
from departments;

