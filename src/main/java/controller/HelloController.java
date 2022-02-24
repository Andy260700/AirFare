package controller;

import dao.DealDao;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;


public class HelloController implements IApplicationController {
    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) throws Exception {
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        DealDao dealDao = new DealDao();
//        Deal deal = new Deal(32.0);
//        try {
//            dealDao.saveDeal(deal);
//        } catch (Exception e) {
//            System.out.println("Error occured");
//        }
        System.out.println(dealDao.getDeals());
        ctx.setVariable("recipient", "Arkadeep De");
        templateEngine.process("home", ctx, writer);
    }
}
