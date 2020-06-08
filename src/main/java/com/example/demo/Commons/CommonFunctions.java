package com.example.demo.Commons;

import java.util.ArrayList;

import com.example.demo.Models.Dispositivo;
import com.example.demo.Models.JwtUser;
import com.example.demo.Models.Login;
import com.example.demo.Models.Notification;
import com.example.demo.Models.Servicio;
import com.example.demo.security.JwtValidator;
import com.example.demo.services.IDispositivoService;
import com.example.demo.services.ILoginService;

public class CommonFunctions {
	
	public static boolean hasAuthorization(IDispositivoService dispositivoService, String uniqueDeviceId) {
		Dispositivo dispositivo = dispositivoService.findByUniqueDeviceId(uniqueDeviceId);
		if (dispositivo == null) {
			return false;
		}
		
		return true;
	}
	
	public static ArrayList<Notification> filterNotificationsByComercioId(ArrayList<Notification> notifications, Long comercioId) {
		ArrayList<Notification> filteredNotifications = new ArrayList<>();
		for (Notification notification : notifications) {
			if (notification.getComercioId() == comercioId) {
				filteredNotifications.add(notification);
			}
		}
		
		return filteredNotifications;
	}
	
	public static ArrayList<Servicio> filterServicesByComercioId(ArrayList<Servicio> servicios, Long comercioId) {
		ArrayList<Servicio> filteredServices = new ArrayList<>();
		for (Servicio servicio : servicios) {
			if (servicio.getComercioId() == comercioId) {
				filteredServices.add(servicio);
			}
		}
		
		return filteredServices;
	}
	
	public static boolean hasTokenAuthorization(String headerToken, JwtValidator validator, ILoginService loginService) {
		if (headerToken == null || !headerToken.startsWith(Constants.BEARER_TOKEN)) {
			return false;
		}
		
		String token = headerToken.substring(7);
		
		JwtUser jwtUser = validator.validate(token);
		
		if (jwtUser == null) {
			return false;
		}
		
		Long comercioId = jwtUser.getId();
		Login login = loginService.findByComercioId(comercioId);
		System.out.println(comercioId);
		if (login == null) {
			return false;
		}
		
		/*if (login.getNombre().compareTo(jwtUser.getUsername()) != 0) {
			return false;
		}*/
		
		return true;
	}
}
