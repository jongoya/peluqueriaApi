package com.example.demo.Commons;

import com.example.demo.Models.Dispositivo;
import com.example.demo.services.IDispositivoService;

public class CommonFunctions {
	
	public static boolean hasAuthorization(IDispositivoService dispositivoService, String uniqueDeviceId) {
		Dispositivo dispositivo = dispositivoService.findByUniqueDeviceId(uniqueDeviceId);
		if (dispositivo == null) {
			return false;
		}
		
		return true;
	}
}
