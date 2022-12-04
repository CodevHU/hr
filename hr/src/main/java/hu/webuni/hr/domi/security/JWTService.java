package hu.webuni.hr.domi.security;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import hu.webuni.hr.domi.config.HrConfigProperties;

@Service
public class JWTService {
	
	@Autowired
	HrConfigProperties config;

	public String createToken(EmployeeUser principal) {
		
		return JWT.create()
			.withSubject(principal.getUsername())
			.withClaim("employee_name", principal.getEmployee().getName())
			.withClaim("employee_id", principal.getEmployee().getId())
//			.withClaim("employees", )
			.withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(config.getSecurity().getExpiresMinutes())))
			.withIssuer(config.getSecurity().getIssuer())
			.sign(Algorithm.HMAC256(config.getSecurity().getSecret()));
	}

}
