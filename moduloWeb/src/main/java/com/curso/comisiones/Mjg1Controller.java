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
@RequestMapping("/mjg1")
public class Mjg1Controller {
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView home() {
		
		ComisionesMjg comisiones= new ComisionesMjg();
		
		comisiones.setTiposVendedor(new HashMap<String, String>());
		comisiones.getTiposVendedor().put(ComisionesMjg1.VENDEDOR,ComisionesMjg1.VENDEDOR);
		comisiones.getTiposVendedor().put(ComisionesMjg1.ENCARGADO,ComisionesMjg1.ENCARGADO);
		comisiones.getTiposVendedor().put(ComisionesMjg1.PRACTICAS,ComisionesMjg1.PRACTICAS);
		comisiones.getTiposVendedor().put(ComisionesMjg1.SUPERVISOR,ComisionesMjg1.SUPERVISOR);
		comisiones.setVentasMes(0);
		comisiones.setHorasExtras(0);
		
		ModelAndView modelAndView= new ModelAndView(ComisionesMjg.HOME);
		modelAndView.addObject("comisiones", comisiones);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "calcular", method = RequestMethod.POST)
	public ModelAndView calcular(ComisionesProfe comisiones) {
		
		int salario=ComisionesMjg1.SALARIO;
		if(comisiones.getTipoSeleccionado().equals(ComisionesMjg1.ENCARGADO))
			salario=ComisionesMjg1.SALARIO1;
		if(comisiones.getTipoSeleccionado().equals(ComisionesMjg1.PRACTICAS))
			salario=ComisionesMjg1.SALARIO_0;
		if(comisiones.getTipoSeleccionado().equals(ComisionesMjg1.SUPERVISOR))
			salario=ComisionesMjg1.SALARIO2;
		
		if(comisiones.getVentasMes()>=ComisionesMjg.IMPORTE_VENTAS_MES_TRAMO_1)
			salario+=100;
		if(comisiones.getVentasMes()>=ComisionesMjg.IMPORTE_VENTAS_MES_TRAMO_2)
			salario+=100;
		
		
		salario+=comisiones.getHorasExtras()*20;
		
		
		ModelAndView modelAndView=home();
		modelAndView.addObject("criterios", comisiones);
		modelAndView.addObject("salario", salario);
		return modelAndView;
	}
	
}
