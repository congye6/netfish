package connector.processor;

import connector.request.HttpRequest;
import connector.response.HttpResponse;

/**
 * Created by cong on 2018-04-09.
 */
public class StaticResourceProcessor implements Processor{


    public void process(HttpRequest request, HttpResponse response) {
        response.sendStaticResource();
    }
}
