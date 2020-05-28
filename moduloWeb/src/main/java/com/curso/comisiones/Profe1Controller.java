package com.curso.comisiones;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.curso.moduloProfe1.ComisionesProfe1;

@Controller
@RequestMapping("profe1")
public class Profe1Controller {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView home() {
		
		ComisionesProfe1 comisiones= new ComisionesProfe1();
		
		comisiones.setTiposVendedor(new HashMap<String, String>());
		comisiones.getTiposVendedor().put(ComisionesProfe1.VENDEDOR,ComisionesProfe1.VENDEDOR);
		comisiones.getTiposVendedor().put(ComisionesProfe1.ENCARGADO,ComisionesProfe1.ENCARGADO);
		comisiones.setVentasMes(0);
		comisiones.setHorasExtras(0);
		
		ModelAndView modelAndView= new ModelAndView(ComisionesProfe1.HOME);
		modelAndView.addObject("comisiones", comisiones);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/calcular", method = RequestMethod.POST)
	public ModelAndView calcular(ComisionesProfe1 comisiones) {
		
		int salario=ComisionesProfe1.SALARIO;
		if(comisiones.getTipoSeleccionado().equals(ComisionesProfe1.ENCARGADO))
			salario=ComisionesProfe1.SALARIO1;
		
		if(comisiones.getVentasMes()>=ComisionesProfe1.IMPORTE_VENTAS_MES_TRAMO_1)
			salario+=100;
		if(comisiones.getVentasMes()>=ComisionesProfe1.IMPORTE_VENTAS_MES_TRAMO_2)
			salario+=100;
		
		
		salario+=comisiones.getHorasExtras()*20;
		
		
		ModelAndView modelAndView=home();
		modelAndView.addObject("criterios", comisiones);
		modelAndView.addObject("salario", salario);
		return modelAndView;
	}
	
	
}
