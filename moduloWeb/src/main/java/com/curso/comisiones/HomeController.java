package com.curso.comisiones;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		
		Comisiones comisiones= new Comisiones();
		
		comisiones.setTiposVendedor(new HashMap<String, String>());
		comisiones.getTiposVendedor().put(Comisiones.VENDEDOR,Comisiones.VENDEDOR);
		comisiones.getTiposVendedor().put(Comisiones.ENCARGADO,Comisiones.ENCARGADO);
		comisiones.setVentasMes(0);
		comisiones.setHorasExtras(0);
		
		ModelAndView modelAndView= new ModelAndView(Comisiones.HOME);
		modelAndView.addObject("comisiones", comisiones);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "calcular", method = RequestMethod.POST)
	public ModelAndView calcular(Comisiones comisiones) {
		
		int salario=Comisiones.SALARIO;
		if(comisiones.getTipoSeleccionado().equals(Comisiones.ENCARGADO))
			salario=Comisiones.SALARIO1;
		
		if(comisiones.getVentasMes()>=Comisiones.IMPORTE_VENTAS_MES_TRAMO_1)
			salario+=100;
		if(comisiones.getVentasMes()>=Comisiones.IMPORTE_VENTAS_MES_TRAMO_2)
			salario+=100;
		
		
		salario+=comisiones.getHorasExtras()*20;
		
		
		ModelAndView modelAndView=home();
		modelAndView.addObject("criterios", comisiones);
		modelAndView.addObject("salario", salario);
		return modelAndView;
	}
	
}
