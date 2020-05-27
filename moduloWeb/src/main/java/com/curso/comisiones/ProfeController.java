package com.curso.comisiones;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/profe")
public class ProfeController {
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		
		ComisionesProfe comisiones= new ComisionesProfe();
		
		comisiones.setTiposVendedor(new HashMap<String, String>());
		comisiones.getTiposVendedor().put(ComisionesProfe.VENDEDOR,ComisionesProfe.VENDEDOR);
		comisiones.getTiposVendedor().put(ComisionesProfe.ENCARGADO,ComisionesProfe.ENCARGADO);
		comisiones.setVentasMes(0);
		comisiones.setHorasExtras(0);
		
		ModelAndView modelAndView= new ModelAndView(ComisionesProfe.HOME);
		modelAndView.addObject("comisiones", comisiones);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/calcular", method = RequestMethod.POST)
	public ModelAndView calcular(ComisionesProfe comisiones) {
		
		int salario=ComisionesProfe.SALARIO;
		if(comisiones.getTipoSeleccionado().equals(ComisionesProfe.ENCARGADO))
			salario=ComisionesProfe.SALARIO1;
		
		if(comisiones.getVentasMes()>=ComisionesProfe.IMPORTE_VENTAS_MES_TRAMO_1)
			salario+=100;
		if(comisiones.getVentasMes()>=ComisionesProfe.IMPORTE_VENTAS_MES_TRAMO_2)
			salario+=100;
		
		
		salario+=comisiones.getHorasExtras()*20;
		
		
		ModelAndView modelAndView=home();
		modelAndView.addObject("criterios", comisiones);
		modelAndView.addObject("salario", salario);
		return modelAndView;
	}
	

}
