package controller;

import org.thymeleaf.web.IWebRequest;

import java.util.HashMap;
import java.util.Map;

public class ControllerMappings {
    private static Map<String, IApplicationController> controllerByURL;

    static {
        controllerByURL = new HashMap<>();
        controllerByURL.put("/", new HelloController());
    }

    public static IApplicationController resolveControllerForRequest(IWebRequest webRequest) {
        String url = webRequest.getPathWithinApplication();
        return controllerByURL.get(url);
    }
}
