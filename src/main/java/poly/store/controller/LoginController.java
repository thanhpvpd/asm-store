package poly.store.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.store.CustomerOAuth2User;
import poly.store.MyUserDetailService;
import poly.store.entity.Account;
import poly.store.entity.Authority;
import poly.store.entity.Role;
import poly.store.service.AccountService;
import poly.store.service.AuthorityService;
import poly.store.service.RoleService;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	AuthorityService authorityService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	MyUserDetailService my;
	
    @GetMapping("sign-in")
    public String signIn() {
        return "/login/sign-in";
    }
    
    
    @GetMapping("security/success")
    public String success(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Đăng nhập thành công");
        if(customerOAuth2User() != null) {
        	Optional<Account> acc = accountService.findByUsername(customerOAuth2User().getUsername());
            if(acc.isEmpty()){
            	Account user = new Account();
            	user.setUsername(customerOAuth2User().getUsername());
            	user.setPassword("123");
            	user.setFullname(customerOAuth2User().getName());
            	user.setEmail(customerOAuth2User().getEmail());
            	user.setPhoto("user.png");
            	accountService.create(user);
            	Authority auth = new Authority();
            	auth.setAccount(user);
            	auth.setRole(roleService.findById("CUST"));
            	authorityService.create(auth);
            }
        }
        return "redirect:/product/index";
    }
    
    @GetMapping("security/error")
    public String error(Model model) {
    	model.addAttribute("message", "Sai thông tin đăng nhập");
    	return "/login/sign-in";
    }
    
    
    @GetMapping("security/out/success")
    public String outSuccess(Model model) {
    	model.addAttribute("message", "Đăng xuất thành công");
    	return "/login/sign-in";
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
