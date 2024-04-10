package poly.store.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.store.CustomerOAuth2User;
import poly.store.entity.Account;
import poly.store.entity.Order;
import poly.store.entity.OrderDetail;
import poly.store.service.AccountService;
import poly.store.service.OrderSevice;

@Controller
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	AccountService accountService;
	@Autowired
	OrderSevice orderSevice;
	
	
	@GetMapping("checkout")
	public String checkout(Model model) {
		if (userDetails() != null || customerOAuth2User() != null) {
		    Optional<Account> user;
		    if(userDetails() == null) {
		    	user = accountService.findByUsername(customerOAuth2User().getUsername());
		    }else {
		    	user = accountService.findByUsername(userDetails().getUsername());
		    }
		    if(user.isPresent()) {
		    	model.addAttribute("user", user.get());
		    }
		}
		return "/order/checkout";
	}
	
	@GetMapping("list/{index}")
	public String list(@PathVariable("index") Integer index, Model model) {
		Pageable pageable = PageRequest.of(index, 4);
		if (userDetails() != null || customerOAuth2User() != null) {
			Page<Order> list;
			if(userDetails() == null) {
				list = orderSevice.findByAccount(customerOAuth2User().getUsername(), pageable);
			}else {
				list = orderSevice.findByAccount(userDetails().getUsername(), pageable);
			}
		    
		    model.addAttribute("page", list);
		}
		return "/order/list";
	}
	
	@GetMapping("detail/{id}")
	public String detail(@PathVariable("id") Long id, Model model) {
		Order order = orderSevice.findById(id);
		model.addAttribute("order", order);
		Double sum = 0.0;
		for (OrderDetail detail: order.getOrderDetails()) {
			sum = sum + (detail.getPrice() * detail.getQuantity());
		}
		model.addAttribute("sum", sum);
		return "/order/detail";
	}
	
	public UserDetails userDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
		    userDetails = (UserDetails) authentication.getPrincipal();
		}
		return userDetails;
	}
	
	public CustomerOAuth2User customerOAuth2User() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomerOAuth2User customerOAuth2User = null;
		if (authentication != null && authentication.getPrincipal() instanceof CustomerOAuth2User) {
			customerOAuth2User = (CustomerOAuth2User) authentication.getPrincipal();
		}
		return customerOAuth2User;
	}
}
