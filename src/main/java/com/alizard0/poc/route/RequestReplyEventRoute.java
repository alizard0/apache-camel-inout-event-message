package com.alizard0.poc.route;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RequestReplyEventRoute extends RouteBuilder {

  @Override
  public void configure() {
    from("direct:publishEvent")
            .id("publishEventDirectRoute")
            .log("Publishing event ${body}")
            .convertBodyTo(String.class)
            .to(ExchangePattern.InOut,"rabbitmq://localhost?queue=event")
            .log("Received message: ${body}")
            .end();
  }
}
