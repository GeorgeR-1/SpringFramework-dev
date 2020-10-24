package com.cybertek.services;

import com.cybertek.interfaces.Course;
import com.cybertek.interfaces.ExtraSessions;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Java implements Course {

    private ExtraSessions extraSessions;

    /*
    ***** Field Injection with @Qualifier *****
    @Autowired
    @Qualifier("officeHours")
    private ExtraSessions extraSessions;
    */

    //***** Constructor injection with @Qualifier *****
    @Autowired
    public Java(@Qualifier("officeHours") ExtraSessions extraSessions) {
        this.extraSessions = extraSessions;
    }

    @Override
    public void getTeachingHours() {

        System.out.println("Weekly teaching hours : " + (20 + extraSessions.getHours()));
    }

}
