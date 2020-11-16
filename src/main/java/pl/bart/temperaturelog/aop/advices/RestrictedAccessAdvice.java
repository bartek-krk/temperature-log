package pl.bart.temperaturelog.aop.advices;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.bart.temperaturelog.commands.MeasurementDTO;
import pl.bart.temperaturelog.controllers.rest.StationRestController;
import pl.bart.temperaturelog.exceptions.StationNotExistingException;
import pl.bart.temperaturelog.exceptions.UnauthorizedException;
import pl.bart.temperaturelog.security.Credentials;
import pl.bart.temperaturelog.utilities.ApiKeyAuth;

import java.util.Map;

@Aspect
@Component
public class RestrictedAccessAdvice {

    @Autowired
    ApiKeyAuth apiKeyAuth;

    @Around("@annotation(pl.bart.temperaturelog.aop.annotations.RestrictedAccess)")
    public Object validateCredentials(ProceedingJoinPoint pjp) throws Throwable{
        Map<String,Object> map = (Map<String, Object>) pjp.getArgs()[0];

        ObjectMapper om = new ObjectMapper();

        Credentials credentials = om.convertValue(map.get("credentials"),Credentials.class);

        Object returnObject = new Object();

        boolean isAuthenticated = apiKeyAuth.authenticate(credentials);

        if (isAuthenticated) returnObject = pjp.proceed();
        else if(!isAuthenticated) throw new UnauthorizedException();

        return returnObject;
    }
}
