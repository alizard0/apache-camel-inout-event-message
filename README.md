# camel-request-reply
proof of concept for camel-rabbitmq using camel-rest for exposing rabbitmq routes.

**run rabbitmq locally**
```bash
$ cd rabbitmq
$ docker build -t my-rabbit .
$ docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 my-rabbit:latest
```

**run application**
```bash
$ mvn clean package
$ java -jar target/camel-request-reply-0.0.1-SNAPSHOT.jar
```

**test with POST**
```bash
$ curl -X POST localhost:8080/api/event -d '{"key1":"value1", "key2":"value2"}' -H 'Content-type: application/json'
// this will be blocked until someone consume and reply to the queue's message
```


**expected logs**
```bash
[nio-8080-exec-1] route2                                   : Publishing event {key1=value1, key2=value2}
[tmq://localhost] route1                                   : Message received {key1=value1, key2=value2} {accept=*/*, CamelHttpCharacterEncoding=UTF-8, CamelHttpMethod=POST, CamelHttpPath=, CamelHttpUri=/api/event, CamelHttpUrl=http://localhost:8080/api/event, CamelServletContextPath=/event, content-length=34, Content-Type=application/json, host=localhost:8080, JMSCorrelationID=728069e6-50c8-4d6b-8555-4dbae4c2e1ce, JMSReplyTo=reply, rabbitmq.CORRELATIONID=Camel-ID-alizardo-fedora-1651752624226-0-2, rabbitmq.DELIVERY_TAG=1, rabbitmq.EXCHANGE_NAME=localhost, rabbitmq.REDELIVERY_TAG=false, rabbitmq.REPLY_TO=amq.gen-C4XCjQmsZ4BuZp8lkMHu0w, rabbitmq.ROUTING_KEY=, user-agent=curl/7.76.1}
[tmq://localhost] route1                                   : Message sent Thats a reply!
cker[localhost]] route2                                   : Received message: Thats a reply!
```
