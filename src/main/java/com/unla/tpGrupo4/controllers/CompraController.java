package com.unla.tpGrupo4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.tpGrupo4.entities.Compra;
import com.unla.tpGrupo4.helpers.ViewRouteHelper;
import com.unla.tpGrupo4.services.implementation.ICompraService;
import com.unla.tpGrupo4.services.implementation.IProductoService;

@Controller
public class CompraController {
	
	@Autowired
    private ICompraService compraService;
	
	@Autowired
    private IProductoService productoService;
	
	@GetMapping("/compra-form/{id}")
	public ModelAndView compraForm(@PathVariable int id) {
		
		ModelAndView mv = new ModelAndView(ViewRouteHelper.COMPRA);
		mv.addObject("producto", productoService.buscarProducto(id));
		mv.addObject("compra", new Compra());
		
		return mv;
	}
	
	@GetMapping("/compras")
	public ModelAndView compras() {
		
		ModelAndView mv = new ModelAndView(ViewRouteHelper.VER_COMPRA);
		mv.addObject("compras", compraService.findCompras());

		return mv;
	}
	
	@PostMapping("/compra/{id}")
	public RedirectView compra(@PathVariable int id, @ModelAttribute Compra c) {
		
		compraService.crearCompra(c, id);
		
		return new RedirectView(ViewRouteHelper.ROUTE);
	}
	
	
}
