package com.curso.comisiones;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/aap")
public class AapController {
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		
		ComisionesAap comisiones= new ComisionesAap();
		
		comisiones.setTiposVendedor(new HashMap<String, String>());
		comisiones.getTiposVendedor().put(ComisionesAap.VENDEDOR,ComisionesAap.VENDEDOR);
		comisiones.getTiposVendedor().put(ComisionesAap.ENCARGADO,ComisionesAap.ENCARGADO);
		comisiones.getTiposVendedor().put(ComisionesAap.BECARIO,ComisionesAap.BECARIO);
		comisiones.setVentasMes(0);
		comisiones.setHorasExtras(0);
		
		ModelAndView modelAndView= new ModelAndView(ComisionesAap.HOME);
		modelAndView.addObject("comisiones", comisiones);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/calcular", method = RequestMethod.POST)
	public ModelAndView calcular(ComisionesAap comisiones) {
		
		int salario=ComisionesAap.SALARIO;
		if(comisiones.getTipoSeleccionado().equals(ComisionesAap.ENCARGADO))
			salario=ComisionesAap.SALARIO1;
		
		if(comisiones.getVentasMes()>=ComisionesAap.IMPORTE_VENTAS_MES_TRAMO_1)
			salario+=100;
		if(comisiones.getVentasMes()>=ComisionesAap.IMPORTE_VENTAS_MES_TRAMO_2)
			salario+=100;
		
		
		salario+=comisiones.getHorasExtras()*20;
		
		
		ModelAndView modelAndView=home();
		modelAndView.addObject("criterios", comisiones);
		modelAndView.addObject("salario", salario);
		return modelAndView;
	}
	

}
