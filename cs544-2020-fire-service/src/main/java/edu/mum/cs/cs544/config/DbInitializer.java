package edu.mum.cs.cs544.config;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import edu.mum.cs.cs544.dao.*;
import edu.mum.cs.cs544.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class DbInitializer implements CommandLineRunner {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private AdminRepository adminRepository;
	
	@Autowired
    private StudentRepository studentRepository;
	
	@Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private CourseOfferingRepository courseOfferingRepository;

    
    @Override
    public void run(String... strings) throws Exception {
        userRepository.deleteAll();
        
        Admin admin = new Admin("admin", "admin", "Peter", "Yellow");
        Admin sys = new Admin("sys", "sys", "Mike", "Rooler");
        adminRepository.saveAll(Arrays.asList(admin, sys));

        /*
        * Session and Course offering
        * */

        Calendar calendar = Calendar.getInstance();

        CourseOffering courseOffering = new CourseOffering();
        courseOffering.setStartDate(calendar.getTime());
        calendar.add(Calendar.MONTH , 1);
        courseOffering.setEndDate(calendar.getTime());
        this.courseOfferingRepository.save(courseOffering);

        Session session = new Session();
        session.setDate(Calendar.getInstance().getTime());
        session.setCourseOffering(courseOffering);
        this.sessionRepository.save(session);


        /*
        * */

        
        System.out.println(" -- Database has been initialized");
    }
    
}