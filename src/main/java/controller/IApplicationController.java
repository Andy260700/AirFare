package controller;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;

public interface IApplicationController {
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) throws Exception;
}
