package xyz.wongs.drunkard.oauth2.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Description: Description
 * @ProjectName: spring-parent
 * @Package: com.yaomy.security.oauth2.api.GrantController
 * @Version: 1.0
 */
@Slf4j
@Controller
@SessionAttributes("authorizationRequest")
public class GrantController {
    @GetMapping("/test/login")
    public String index(Model model) {
        model.addAttribute("loginProcessUrl","/user/login");
        return "login";
    }
    @RequestMapping("/custom/confirm_access")
    public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
        ModelAndView view = new ModelAndView();
        view.setViewName("grant");
        view.addObject("clientId", authorizationRequest.getClientId());
        view.addObject("scope", authorizationRequest.getScope());
        log.error(authorizationRequest.getScope().toString());
        log.error(authorizationRequest.getClientId());
        return view;
    }

}
