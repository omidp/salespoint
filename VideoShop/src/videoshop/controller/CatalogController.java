package videoshop.controller;

import java.util.Locale;

import org.salespointframework.core.inventory.InventoryItem;
import org.salespointframework.core.inventory.PersistentInventory;
import org.salespointframework.core.inventory.PersistentInventoryItem;
import org.salespointframework.core.product.ProductIdentifier;
import org.salespointframework.core.quantity.Quantity;
import org.salespointframework.core.quantity.Units;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import videoshop.model.Comment;
import videoshop.model.Disc;
import videoshop.model.VideoCatalog;



@Controller
public class CatalogController {
	
	private final VideoCatalog videoCatalog = new VideoCatalog();
	private final PersistentInventory inventory = new PersistentInventory();

	@Autowired
	private MessageSource messageSource;
	
	
	@RequestMapping("/dvdCatalog")
	public ModelAndView dvdCatalog(ModelAndView mav, Locale locale) {
		mav.addObject("catalog", videoCatalog.findDvds());
		String title = messageSource.getMessage("catalog.dvd.title", null , locale);
		mav.addObject("title", title);
		mav.setViewName("catalog");
		return mav;
	}

	@RequestMapping("/blurayCatalog")
	public ModelAndView blurayCatalog(ModelAndView mav, Locale locale) {
		mav.addObject("catalog", videoCatalog.findBluRays());
		String title = messageSource.getMessage("catalog.bluray.title", null , locale);
		mav.addObject("title", title);
		mav.setViewName("catalog");
		return mav;
	}
	
	@RequestMapping("/detail/{pid}")
	public ModelAndView detail(ModelAndView mav, @PathVariable("pid") ProductIdentifier pid) {
		Disc disc = videoCatalog.getDisc(pid);
		mav.addObject("disc", disc);
		
		InventoryItem item = inventory.getByProductIdentifier(PersistentInventoryItem.class, pid);
		Quantity quantity = item == null ? Units.ZERO : item.getQuantity();
		
		mav.addObject("quantity", quantity);
		
		mav.setViewName("detail");
		return mav;
	}


	@RequestMapping("/comment")
	public ModelAndView comment(ModelAndView mav,
			@RequestParam("pid") ProductIdentifier pid,
			@RequestParam("comment") String comment,
			@RequestParam("rating") int rating ) {

		Disc disc = videoCatalog.get(Disc.class, pid);

		disc.addComment(new Comment(comment, rating));

		videoCatalog.update(disc);
		
		mav.addObject("disc", disc);
		
		InventoryItem item = inventory.getByProductIdentifier(PersistentInventoryItem.class, pid);
		Quantity quantity = item == null ? Units.ZERO : item.getQuantity();
		
		mav.addObject("quantity", quantity);
		
		mav.setViewName("detail");
		return mav;
	}
}