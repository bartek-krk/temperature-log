package pl.bart.temperaturelog.aop.advices;

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

@Aspect
@Component
public class RestrictedAccessAdvice {

    @Autowired
    ApiKeyAuth apiKeyAuth;

    @Around("@annotation(pl.bart.temperaturelog.aop.annotations.RestrictedAccess)")
    public Object validateCredentials(ProceedingJoinPoint pjp) throws Throwable{
        Credentials credentials = new Credentials();
        if(pjp.getSignature().getName() == "deleteStation"){
            credentials = (Credentials) pjp.getArgs()[0];
        }
        else if(pjp.getSignature().getName() == "saveMeasurement"){
            MeasurementDTO m = (MeasurementDTO) pjp.getArgs()[0];
            credentials = m.getCredentials();
        }

        Object returnObject = new Object();

        boolean isAuthenticated = apiKeyAuth.authenticate(credentials);


        if (isAuthenticated) returnObject = pjp.proceed();
        else if(!isAuthenticated) throw new UnauthorizedException();

        return returnObject;
    }
}
