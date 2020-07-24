package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Commons.CommonFunctions;
import com.example.demo.Commons.Constants;
import com.example.demo.Models.Cesta;
import com.example.demo.Models.CestaMasVentas;
import com.example.demo.Models.ProductoModel;
import com.example.demo.Models.Venta;
import com.example.demo.security.JwtValidator;
import com.example.demo.services.IDispositivoService;
import com.example.demo.services.ILoginService;
import com.example.demo.services.IProductoService;
import com.example.demo.services.IVentaService;
import com.example.demo.services.ICestaService;

@RestController
@RequestMapping("/api")
public class CestaController {
	
	@Autowired
	private ICestaService cestaService;
	
	@Autowired
	private IVentaService ventaService;
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private IDispositivoService dispositivoService;
	
	@Autowired
	private JwtValidator validator;
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/get_cestas/{comercioId}")
	public ResponseEntity<ArrayList<Cesta>> getCestas(@RequestHeader(Constants.authorizationHeaderKey) String token, @PathVariable(value = "comercioId")Long comercioId, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		ArrayList<Cesta> cestas = cestaService.findByComercioId(comercioId);
		return new ResponseEntity<>(cestas, HttpStatus.OK);
	}
	
	@GetMapping("/get_ventas/{comercioId}")
	public ResponseEntity<ArrayList<Venta>> getVentas(@RequestHeader(Constants.authorizationHeaderKey) String token, @PathVariable(value = "comercioId")Long comercioId, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		ArrayList<Venta> ventas = ventaService.findByComercioId(comercioId);
		return new ResponseEntity<>(ventas, HttpStatus.OK);
	}
	
	@PostMapping("/save_cesta")
	public ResponseEntity<CestaMasVentas> saveCesta(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody CestaMasVentas model) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		Cesta cesta = cestaService.saveCesta(model.getCesta());
		
		for (Venta venta : model.getVentas()) {
			venta.setCestaId(cesta.getCestaId());
			ProductoModel producto = productoService.findByProductoId(venta.getProductoId());
			producto.setNumProductos(producto.getNumProductos() - venta.getCantidad());
			productoService.updateProducto(producto);
		}
		
		ArrayList<Venta> ventas = ventaService.saveVentas(model.getVentas());
		
		return new ResponseEntity<>(new CestaMasVentas(cesta, ventas), HttpStatus.CREATED);
	}
	
	@PutMapping("/update_cesta")
	public ResponseEntity<CestaMasVentas> updateCesta(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody CestaMasVentas model) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		if (cestaService.findByCestaId(model.getCesta().getCestaId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			Cesta cesta = cestaService.saveCesta(model.getCesta());
			for (Venta venta: model.getVentas()) {
				if (venta.getVentaId() == 0) {
					ProductoModel producto = productoService.findByProductoId(venta.getProductoId());
					producto.setNumProductos(producto.getNumProductos() - venta.getCantidad());
					productoService.updateProducto(producto);
				}
			}
			
			ArrayList<Venta> ventas = ventaService.saveVentas(model.getVentas());
			return new ResponseEntity<>(new CestaMasVentas(cesta, ventas), HttpStatus.OK);
		}
	}
	
	@PostMapping("/delete_Cesta")
	public ResponseEntity<Void> deleteProducto(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody Cesta cesta) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		if (cestaService.findByCestaId(cesta.getCestaId()) != null) {
			cestaService.deleteCesta(cesta.getCestaId());
			ArrayList<Venta> ventas = ventaService.findByCestaId(cesta.getCestaId());
			ventaService.deleteVentas(ventas);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
