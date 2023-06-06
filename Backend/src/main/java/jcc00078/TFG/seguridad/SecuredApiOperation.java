package jcc00078.TFG.seguridad;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mejora de marcado con anotaciones
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
public @interface SecuredApiOperation {
}
