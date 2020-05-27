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
@RequestMapping("/mjg")
public class MjgController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		
		ComisionesMjg comisiones= new ComisionesMjg();
		
		comisiones.setTiposVendedor(new HashMap<String, String>());
		comisiones.getTiposVendedor().put(ComisionesMjg.VENDEDOR,ComisionesMjg.VENDEDOR);
		comisiones.getTiposVendedor().put(ComisionesMjg.ENCARGADO,ComisionesMjg.ENCARGADO);
		comisiones.getTiposVendedor().put(ComisionesMjg.PRACTICAS,ComisionesMjg.PRACTICAS);
		comisiones.setVentasMes(0);
		comisiones.setHorasExtras(0);
		
		ModelAndView modelAndView= new ModelAndView(ComisionesMjg.HOME);
		modelAndView.addObject("comisiones", comisiones);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "calcular", method = RequestMethod.POST)
	public ModelAndView calcular(ComisionesProfe comisiones) {
		
		int salario=Comisiones.SALARIO;
		if(comisiones.getTipoSeleccionado().equals(ComisionesMjg.ENCARGADO))
			salario=Comisiones.SALARIO1;
		if(comisiones.getTipoSeleccionado().equals(ComisionesMjg.PRACTICAS))
			salario=ComisionesMjg.SALARIO_0;
		
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
