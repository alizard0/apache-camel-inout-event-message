package com.alizard0.poc.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ConsumerEventRoute extends RouteBuilder {

  @Override
  public void configure() {
    from("rabbitmq://localhost?queue=request")
            .log("Message received ${body} ${headers}")
            .transform().constant("Thats a reply!")
            .log("Message sent ${body}");
  }
}
