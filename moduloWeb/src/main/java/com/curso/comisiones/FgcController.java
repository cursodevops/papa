package com.curso.comisiones;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/fgc")
public class FgcController {
	

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView home() {
		
		ComisionesFgc comisiones= new ComisionesFgc();
		
		comisiones.setTiposVendedor(new HashMap<String, String>());
		comisiones.getTiposVendedor().put(ComisionesFgc.VENDEDOR,ComisionesFgc.VENDEDOR);
		comisiones.getTiposVendedor().put(ComisionesFgc.ENCARGADO,ComisionesFgc.ENCARGADO);
		comisiones.setVentasMes(0);
		comisiones.setHorasExtras(0);
		
		ModelAndView modelAndView= new ModelAndView(ComisionesFgc.HOME);
		modelAndView.addObject("comisiones", comisiones);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/calcular", method = RequestMethod.POST)
	public ModelAndView calcular(ComisionesFgc comisiones) {
		
		int salario=ComisionesFgc.SALARIO;
		if(comisiones.getTipoSeleccionado().equals(ComisionesFgc.ENCARGADO))
			salario=ComisionesFgc.SALARIO1;
		
		if(comisiones.getVentasMes()>=ComisionesFgc.IMPORTE_VENTAS_MES_TRAMO_1)
			salario+=100;
		if(comisiones.getVentasMes()>=ComisionesFgc.IMPORTE_VENTAS_MES_TRAMO_2)
			salario+=100;
		
		
		salario+=comisiones.getHorasExtras()*20;
		
		
		ModelAndView modelAndView=home();
		modelAndView.addObject("criterios", comisiones);
		modelAndView.addObject("salario", salario);
		return modelAndView;
	}
	
}
