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
@RequestMapping("/JSV")
public class JSVController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		
		ComisionesJSV comisiones= new ComisionesJSV();
		
		comisiones.setTiposVendedor(new HashMap<String, String>());
		comisiones.getTiposVendedor().put(ComisionesJSV.VENDEDOR,ComisionesJSV.VENDEDOR);
		comisiones.getTiposVendedor().put(ComisionesJSV.ENCARGADO,ComisionesJSV.ENCARGADO);
		comisiones.setVentasMes(0);
		comisiones.setHorasExtras(0);
		
		ModelAndView modelAndView= new ModelAndView(ComisionesJSV.HOME);
		modelAndView.addObject("comisiones", comisiones);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "calcular", method = RequestMethod.POST)
	public ModelAndView calcular(ComisionesJSV comisiones) {
		
		int salario=ComisionesJSV.SALARIO;
		if(comisiones.getTipoSeleccionado().equals(ComisionesJSV.ENCARGADO))
			salario=ComisionesJSV.SALARIO1;
		
		if(comisiones.getVentasMes()>=ComisionesJSV.IMPORTE_VENTAS_MES_TRAMO_1)
			salario+=100;
		if(comisiones.getVentasMes()>=ComisionesJSV.IMPORTE_VENTAS_MES_TRAMO_2)
			salario+=100;
		
		
		salario+=comisiones.getHorasExtras()*20;
		
		
		ModelAndView modelAndView=home();
		modelAndView.addObject("criterios", comisiones);
		modelAndView.addObject("salario", salario);
		return modelAndView;
	}
	
}
