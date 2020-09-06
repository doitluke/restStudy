package soo.ram.restapi.demo.events;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

@Component
public class EventValidator {

    public void validate(EventDto eventDto, Errors errors){
        if(eventDto.getBasePrice() > eventDto.getMaxPrice() && eventDto.getMaxPrice() != 0){
            errors.reject("wrongPrices", "Values for prices Wrong");
        }

        LocalDateTime endEvnetDateTime = eventDto.getEndEventDateTime();
        if(endEvnetDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())||
            endEvnetDateTime.isBefore(eventDto.getCloseEnrollmentDateTime())||
            endEvnetDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())){
            errors.rejectValue("endEventDateTime", "wrongValue", "eventTime is wrong");
        }
    }
}
