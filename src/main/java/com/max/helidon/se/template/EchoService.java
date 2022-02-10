
package com.max.helidon.se.template;

import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import java.util.Collections;


public class EchoService implements Service {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    @Override
    public void update(Routing.Rules rules) {
        rules.get("/", this::getDefaultHandler);
    }

    private void getDefaultHandler(ServerRequest request, ServerResponse response) {
        sendResponse(response);
    }

    private void sendResponse(ServerResponse response) {
        String msg = String.format("%s!!!", "mTLS is working");

        JsonObject returnObject = JSON.createObjectBuilder()
                .add("message", msg)
                .build();
        response.send(returnObject);
    }
}
