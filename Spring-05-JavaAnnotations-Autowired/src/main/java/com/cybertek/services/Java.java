package com.cybertek.services;

import com.cybertek.interfaces.Course;
import com.cybertek.interfaces.ExtraSessions;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Java implements Course {

    @Autowired
    private ExtraSessions extraSessions;

/*
    //Constructor Injection
    @Autowired
    public Java(OfficeHours officeHours) {
        this.officeHours = officeHours;
    }

 */

    public ExtraSessions getOfficeHours() {
        return extraSessions;
    }

/*
    //Setter Injection
    @Autowired
    public void setOfficeHours(OfficeHours officeHours) {
        this.officeHours = officeHours;
    }

 */

    @Override
    public void getTeachingHours() {
        System.out.println("Weekly teaching hours : " + (30 + extraSessions.getHours()));
    }
}
