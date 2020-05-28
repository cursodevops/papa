package com.curso.comisiones;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ams")
public class AmsController {
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView home() {
		
		ComisionesAms comisiones= new ComisionesAms();
		
		comisiones.setTiposVendedor(new HashMap<String, String>());
		comisiones.getTiposVendedor().put(ComisionesAms.VENDEDOR,ComisionesAms.VENDEDOR);
		comisiones.getTiposVendedor().put(ComisionesAms.ENCARGADO,ComisionesAms.ENCARGADO);
		comisiones.getTiposVendedor().put(ComisionesAms.PRACTICAS,ComisionesAms.PRACTICAS);
		comisiones.setVentasMes(0);
		comisiones.setHorasExtras(0);
		
		ModelAndView modelAndView= new ModelAndView(ComisionesAms.HOME);
		modelAndView.addObject("comisiones", comisiones);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/calcular", method = RequestMethod.POST)
	public ModelAndView calcular(ComisionesAms comisiones) {
		
		int salario=ComisionesAms.SALARIO;
		if(comisiones.getTipoSeleccionado().equals(ComisionesAms.ENCARGADO))
			salario=ComisionesAms.SALARIO1;
		
		if(comisiones.getVentasMes()>=ComisionesAms.IMPORTE_VENTAS_MES_TRAMO_1)
			salario+=100;
		if(comisiones.getVentasMes()>=ComisionesAms.IMPORTE_VENTAS_MES_TRAMO_2)
			salario+=100;
		
		
		salario+=comisiones.getHorasExtras()*20;
		
		
		ModelAndView modelAndView=home();
		modelAndView.addObject("criterios", comisiones);
		modelAndView.addObject("salario", salario);
		return modelAndView;
	}
	

}
