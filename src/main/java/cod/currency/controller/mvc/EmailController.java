package cod.currency.controller.mvc;

import cod.currency.service.CurrencyService;
import cod.currency.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * JMA - 8/8/2020 17:33
 **/
@Controller
public class EmailController {

    private final EmailService emailService;
    private final CurrencyService currencyService;

    @Autowired
    public EmailController(EmailService emailService, CurrencyService currencyService) {
        this.emailService = emailService;
        this.currencyService = currencyService;
    }

    @GetMapping("/h1")
    public String hello(Model model) {
        model.addAllAttributes(currencyService.reportCoin());
        return "reportmail";
    }
}
