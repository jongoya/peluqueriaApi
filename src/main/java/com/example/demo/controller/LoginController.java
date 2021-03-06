package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Commons.CommonFunctions;
import com.example.demo.Commons.Constants;
import com.example.demo.Models.Cesta;
import com.example.demo.Models.CierreCaja;
import com.example.demo.Models.Cliente;
import com.example.demo.Models.ComercioModel;
import com.example.demo.Models.Dispositivo;
import com.example.demo.Models.Empleado;
import com.example.demo.Models.EstiloPrivado;
import com.example.demo.Models.EstiloPublico;
import com.example.demo.Models.JwtUser;
import com.example.demo.Models.Login;
import com.example.demo.Models.LoginMasDispositivos;
import com.example.demo.Models.Notification;
import com.example.demo.Models.ProductoModel;
import com.example.demo.Models.Servicio;
import com.example.demo.Models.TipoServicio;
import com.example.demo.Models.Venta;
import com.example.demo.security.JwtGenerator;
import com.example.demo.security.JwtValidator;
import com.example.demo.services.ICestaService;
import com.example.demo.services.ICierreCajaService;
import com.example.demo.services.IClienteService;
import com.example.demo.services.IDispositivoService;
import com.example.demo.services.IEmpleadoService;
import com.example.demo.services.IEstiloPrivadoService;
import com.example.demo.services.IEstiloPublicoService;
import com.example.demo.services.ILoginService;
import com.example.demo.services.INotificationService;
import com.example.demo.services.IProductoService;
import com.example.demo.services.IServicioService;
import com.example.demo.services.ITipoServicioService;
import com.example.demo.services.IVentaService;

@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	private ILoginService loginService;
	
	private JwtGenerator jwtGenerator;
	
	@Autowired IDispositivoService dispositivoService;
	
	@Autowired
	private IEstiloPublicoService estiloPublico;
	
	@Autowired
	private IEstiloPrivadoService estiloPrivadoService;
	
	@Autowired
	private ICestaService cestaService;
	
	@Autowired
	private ICierreCajaService cierreCajaService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IEmpleadoService empleadoService;
	
	@Autowired
	private INotificationService notificationService;
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IServicioService servicioService;
	
	@Autowired
	private ITipoServicioService tipoServicioService;
	
	@Autowired
	private IVentaService ventaService;
	
	@Autowired
	private JwtValidator validator;
	
	public LoginController() {
		this.jwtGenerator = new JwtGenerator();
	}

	@CrossOrigin
	@PostMapping("/register_comercio")
	public ResponseEntity<Login> registerComercio(@RequestBody Login login, @RequestHeader(Constants.authorizationHeaderKey) String token, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		
		if (loginService.findByComercioId(login.getComercioId()) != null) {
			return new ResponseEntity<>(HttpStatus.FOUND);
		} else {
			login.setActive(false);
			Login response = loginService.save(login);
			EstiloPublico estilo = new EstiloPublico(login.getAndroidBundleId(),login.getIosBundleId(), login.getNombre());
			estiloPublico.saveEstilo(estilo);
			EstiloPrivado estiloPrivado = new EstiloPrivado(login.getComercioId());
			estiloPrivadoService.saveEstilo(estiloPrivado);
			
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}
	}

	@PostMapping("/login_comercio")
	public ResponseEntity<LoginMasDispositivos> loginComercio(@RequestBody Login login) {
		Login comercioLogin = loginService.findByUsuario(login.getUsuario());
		
		if (comercioLogin == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if (comercioLogin.getNombre().compareTo(login.getNombre()) != 0) {
			return new ResponseEntity<>(HttpStatus.valueOf(411));//Revisamos que se esta logeando en el comercio adecuado
		}
		
		if (comercioLogin.getPassword().compareTo(login.getPassword()) != 0) {
			return new ResponseEntity<>(HttpStatus.valueOf(412));
		}
		
		boolean dispositivoRegistrado = false;
		ArrayList<Dispositivo> dispositivos = dispositivoService.findByComercioId(comercioLogin.getComercioId());
		for (Dispositivo dispositivo : dispositivos) {
			if (dispositivo.getUnique_deviceId().compareTo(login.getUnique_deviceId()) == 0) {
				dispositivoRegistrado = true;
			}
		}
		
		EstiloPrivado estiloPrivado = estiloPrivadoService.findByComercioId(comercioLogin.getComercioId());
		
		LoginMasDispositivos result = new LoginMasDispositivos(comercioLogin, dispositivos, estiloPrivado);
		
		if (dispositivos.size() == comercioLogin.getNumero_dispositivos() && !dispositivoRegistrado) {
			return new ResponseEntity<>(result, HttpStatus.valueOf(413));
		}
		
		if (!dispositivoRegistrado) {
			dispositivoService.save(new Dispositivo(comercioLogin.getComercioId(), new Date().getTime(), login.getNombre_dispositivo(), login.getUnique_deviceId()));
		}
		
		if (!comercioLogin.isActive()) {
			comercioLogin.setActive(true);
			comercioLogin.setToken(generarToken(comercioLogin));
			comercioLogin = loginService.updateLogin(comercioLogin);
		}
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/login_swap_devices")
	public ResponseEntity<LoginMasDispositivos> swapDevices(@RequestBody LoginMasDispositivos loginMasDispositivos) {
		Login comercioLogin = loginService.findByUsuario(loginMasDispositivos.getLogin().getUsuario());
		
		if (comercioLogin == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if (loginMasDispositivos.getDispositivos().size() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		String oldUniqueDeviceId = loginMasDispositivos.getDispositivos().get(0).getUnique_deviceId();
		Dispositivo dispositivoABorrar = null;
		ArrayList<Dispositivo> dispositivos = dispositivoService.findByComercioId(comercioLogin.getComercioId());
		for (Dispositivo dispositivo : dispositivos) {
			if (dispositivo.getUnique_deviceId().compareTo(oldUniqueDeviceId) == 0) {
				dispositivoABorrar = dispositivo;
			}
		}
		
		if (dispositivoABorrar == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		dispositivoService.deleteDispositivo(dispositivoABorrar.getDispositivoId());
		dispositivoService.save(new Dispositivo(comercioLogin.getComercioId(), new Date().getTime(), loginMasDispositivos.getLogin().getNombre_dispositivo(), loginMasDispositivos.getLogin().getUnique_deviceId()));

		EstiloPrivado estiloPrivado = estiloPrivadoService.findByComercioId(comercioLogin.getComercioId());
		
		LoginMasDispositivos result = new LoginMasDispositivos(comercioLogin, dispositivos, estiloPrivado);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	//Servicios web admin
	
	@CrossOrigin
	@PostMapping("/login_admin")
	public ResponseEntity<Login> loginWebAdmin(@RequestBody Login login) {
		Login loginAdmin = loginService.findByUsuario(login.getUsuario());
		
		if (loginAdmin == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if (!loginAdmin.isAdmin()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if (loginAdmin.getNombre().compareTo(login.getNombre()) != 0) {
			return new ResponseEntity<>(HttpStatus.valueOf(411));//Revisamos que se esta logeando en el admin
		}
		
		if (loginAdmin.getPassword().compareTo(login.getPassword()) != 0) {
			return new ResponseEntity<>(HttpStatus.valueOf(412));
		}
		
		boolean dispositivoRegistrado = false;
		Dispositivo dispositivoAdmin = new Dispositivo();
		ArrayList<Dispositivo> dispositivos = dispositivoService.findByComercioId(loginAdmin.getComercioId());
		for (Dispositivo dispositivo : dispositivos) {
			if (dispositivo.getUnique_deviceId().compareTo(login.getUnique_deviceId()) == 0) {
				dispositivoRegistrado = true;
				dispositivoAdmin = dispositivo;
			}
		}
		
		if (!dispositivoRegistrado) {
			dispositivoAdmin = dispositivoService.save(new Dispositivo(loginAdmin.getComercioId(), new Date().getTime(), login.getNombre_dispositivo(), login.getUnique_deviceId()));
		}
		
		if (!loginAdmin.isActive()) {
			loginAdmin.setActive(true);
			loginAdmin.setToken(generarToken(loginAdmin));
			loginAdmin = loginService.updateLogin(loginAdmin);
		}
		
		loginAdmin.setNombre_dispositivo(dispositivoAdmin.getNombre_dispositivo());
		loginAdmin.setUnique_deviceId(dispositivoAdmin.getUnique_deviceId());
		
		return new ResponseEntity<>(loginAdmin, HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("get_comercios")
	public ResponseEntity<ArrayList<Login>> getComerciosAdmin(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		ArrayList<Login> logins = loginService.findAllLogins();
		ArrayList <Login> result = new ArrayList<Login>();
		for(Login login : logins) {
			if (!login.isAdmin()) {
				result.add(login);
			}
		}
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("comercio_details")
	public ResponseEntity<ComercioModel> getComercioById(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody Login login) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		Login serverLogin = loginService.findByComercioId(login.getComercioId());
		
		if (serverLogin == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		ArrayList<Dispositivo> dispositivos = dispositivoService.findByComercioId(login.getComercioId());
		EstiloPrivado estiloPrivado = estiloPrivadoService.findByComercioId(login.getComercioId());
		EstiloPublico estilo = estiloPublico.findByNombreApp(login.getNombre());
		
		ComercioModel comercio = new ComercioModel(serverLogin, dispositivos, estiloPrivado, estilo);
		
		return new ResponseEntity<>(comercio, HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("delete_comercio")
	public ResponseEntity<?> deleteComercio(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody Login login) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		Login serverLogin = loginService.findByComercioId(login.getComercioId());
		if (serverLogin == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		loginService.deleteLogin(serverLogin);
		ArrayList<Cesta> cestas = cestaService.findByComercioId(login.getComercioId());
		ArrayList<CierreCaja> cierreCajas = cierreCajaService.findByComercioId(login.getComercioId());
		ArrayList<Cliente> clientes = clienteService.findByComercioId(login.getComercioId());
		ArrayList<Dispositivo> dispositivos = dispositivoService.findByComercioId(login.getComercioId());
		ArrayList<Empleado> empleados = empleadoService.findByComercioId(login.getComercioId());
		EstiloPrivado estiloPrivado = estiloPrivadoService.findByComercioId(login.getComercioId());
		EstiloPublico estiloPublicoObject = estiloPublico.findByNombreApp(login.getNombre());
		ArrayList<Notification> notifications = notificationService.findByComercioId(login.getComercioId());
		ArrayList<ProductoModel> productos = productoService.findByComercioId(login.getComercioId());
		ArrayList<TipoServicio> tipoServicios = tipoServicioService.findByComercioId(login.getComercioId());
		ArrayList<Servicio> servicios = servicioService.findByComercioId(login.getComercioId());
		ArrayList<Venta> ventas = ventaService.findByComercioId(login.getComercioId());
		
		cestaService.deleteCestas(cestas);
		cierreCajaService.deleteCierreCajas(cierreCajas);
		clienteService.deleteClientes(clientes);
		dispositivoService.deleteDispositivos(dispositivos);
		empleadoService.deleteEmpleados(empleados);
		estiloPrivadoService.deleteEstilo(estiloPrivado);
		estiloPublico.deleteEstilo(estiloPublicoObject);
		notificationService.deleteNotifications(notifications);
		productoService.deleteProductos(productos);
		tipoServicioService.deleteTipoServicios(tipoServicios);
		servicioService.deleteServicios(servicios);
		ventaService.deleteVentas(ventas);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("update_login")
	public ResponseEntity<Login> udpdateComercio(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody Login login) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		Login serverLogin = loginService.findByComercioId(login.getComercioId());
		
		if (serverLogin == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		ArrayList<Dispositivo> dispositivos = dispositivoService.findByComercioId(login.getComercioId());
		EstiloPrivado estiloPrivado = estiloPrivadoService.findByComercioId(login.getComercioId());
		EstiloPublico estiloPublicoObject = estiloPublico.findByNombreApp(serverLogin.getNombre());
		
		serverLogin.setNombre(login.getNombre());
		estiloPublicoObject.setNombreApp(login.getNombre());
		
		serverLogin.setUsuario(login.getUsuario());
		
		serverLogin.setPassword(login.getPassword());
		
		if (login.getNumero_dispositivos() < dispositivos.size()) {
			for (int i = 0; i < dispositivos.size(); i++) {
				if (i >= login.getNumero_dispositivos()) {
					dispositivoService.deleteDispositivo(dispositivos.get(i).getDispositivoId());
				}
			}
		}
		
		serverLogin.setNumero_dispositivos(login.getNumero_dispositivos());
		
		serverLogin.setAndroidBundleId(login.getAndroidBundleId());
		estiloPublicoObject.setAndroidBundleId(login.getAndroidBundleId());
		
		serverLogin.setIosBundleId(login.getIosBundleId());
		estiloPublicoObject.setIosBundleId(login.getIosBundleId());
		
		if (serverLogin.isActive() && !login.isActive()) {
			serverLogin.setActive(false);
			serverLogin.setToken("");
			dispositivoService.deleteDispositivos(dispositivos);
		}
		
		if (!serverLogin.isActive() && login.isActive()) {
			serverLogin.setActive(true);
			serverLogin.setToken(generarToken(serverLogin));
		}
		
		serverLogin.setAdmin(login.isAdmin());
		
		serverLogin = loginService.updateLogin(serverLogin);
		estiloPrivadoService.updateEstilo(estiloPrivado);
		estiloPublico.updateEstilo(estiloPublicoObject);
		
		return new ResponseEntity<>(serverLogin, HttpStatus.OK);
	}
	
	private String generarToken(Login login) {
		JwtUser user = new JwtUser();
		user.setUsername(login.getNombre());
		user.setId(login.getComercioId());
		user.setRole("Admin");
		return jwtGenerator.generate(user);
	}
}
