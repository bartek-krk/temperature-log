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
import pl.bart.temperaturelog.utilities.ApiKeyAuth;

@Aspect
@Component
public class RestrictedAccessAdvice {

    @Autowired
    ApiKeyAuth apiKeyAuth;

    @Around("@annotation(pl.bart.temperaturelog.aop.annotations.RestrictedAccess)")
    public Object validateCredentials(ProceedingJoinPoint pjp) throws Throwable{
        Long id = 0L;
        String key = "";
        if(pjp.getSignature().getName() == "deleteStation"){
            id = (Long) pjp.getArgs()[0];
            key = pjp.getArgs()[1].toString();
        }
        else if(pjp.getSignature().getName() == "saveMeasurement"){
            MeasurementDTO m = (MeasurementDTO) pjp.getArgs()[1];
            id = m.getStationId();
            key = pjp.getArgs()[0].toString();
        }

        Object returnObject = new Object();

        boolean isAuthenticated = apiKeyAuth.authenticate(id,key);


        if (isAuthenticated) returnObject = pjp.proceed();
        else if(!isAuthenticated) throw new UnauthorizedException();

        return returnObject;
    }
}
