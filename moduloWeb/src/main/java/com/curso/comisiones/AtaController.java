package com.curso.comisiones;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ata")
public class AtaController {
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		
		ComisionesAta comisiones= new ComisionesAta();
		
		comisiones.setTiposVendedor(new HashMap<String, String>());
		comisiones.getTiposVendedor().put(ComisionesAta.VENDEDOR,ComisionesAta.VENDEDOR);
		comisiones.getTiposVendedor().put(ComisionesAta.ENCARGADO,ComisionesAta.ENCARGADO);
		comisiones.getTiposVendedor().put(ComisionesAta.COMERCIAL,ComisionesAta.COMERCIAL);
		comisiones.setVentasMes(0);
		comisiones.setHorasExtras(0);
		
		ModelAndView modelAndView= new ModelAndView(ComisionesAta.HOME);
		modelAndView.addObject("comisiones", comisiones);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/calcular", method = RequestMethod.POST)
	public ModelAndView calcular(ComisionesAta comisiones) {
		
		int salario=ComisionesAta.SALARIO_0;
		if(comisiones.getTipoSeleccionado().equals(ComisionesAta.ENCARGADO))
			salario=ComisionesAta.SALARIO;
		if(comisiones.getTipoSeleccionado().equals(ComisionesAta.COMERCIAL))
			salario=ComisionesAta.SALARIO1;
		
		if(comisiones.getVentasMes()>=ComisionesAta.IMPORTE_VENTAS_MES_TRAMO_1)
			salario+=100;
		if(comisiones.getVentasMes()>=ComisionesAta.IMPORTE_VENTAS_MES_TRAMO_2)
			salario+=100;
		
		
		salario+=comisiones.getHorasExtras()*20;
		
		
		ModelAndView modelAndView=home();
		modelAndView.addObject("criterios", comisiones);
		modelAndView.addObject("salario", salario);
		return modelAndView;
	}
	

}
