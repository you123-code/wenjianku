package com.demo.gateway.server.filter.route;

import com.demo.gateway.server.model.dto.ClientConfig;
import com.demo.gateway.server.service.client.ClientService;
import com.demo.gateway.server.service.securityAPI.SecurityApiRpcService;
import com.demo.gateway.server.service.utils.WebUtil;
import com.demo.gateway.server.service.utils.cert.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/3/31 17:52
 */
@Slf4j
@Component
public class DecryptRequestBodyFilter extends AbstractGatewayFilterFactory implements Ordered {
    private final List<HttpMessageReader<?>> messageReaders;

    public DecryptRequestBodyFilter() {
        this.messageReaders = HandlerStrategies.withDefaults().messageReaders();
    }

    @Override
    @SuppressWarnings("unchecked")
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerRequest serverRequest = ServerRequest.create(exchange,
                    this.messageReaders);

            log.info("????????????: headers->[{}], body->[{}]", exchange.getRequest().getHeaders(), exchange.getRequest().getBody());

            Mono<String> modifiedBody = serverRequest.bodyToMono(String.class)
                    .flatMap(originalBody -> modifyBody().apply(exchange, Mono.just(originalBody)));

            BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody, String.class);
            HttpHeaders headers = new HttpHeaders();
            headers.putAll(exchange.getRequest().getHeaders());
            headers.remove(HttpHeaders.CONTENT_LENGTH);
            CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);

            return bodyInserter.insert(outputMessage, new BodyInserterContext())
                    .then(Mono.defer(() -> {
                        ServerHttpRequest decorator = decorate(exchange, headers, outputMessage);
                        return chain.filter(exchange.mutate().request(decorator).build());
                    }));
        };
    }

    private BiFunction<ServerWebExchange, Mono<String>, Mono<String>> modifyBody() {
        return (exchange, body) -> {
            try {
                AtomicReference<String> result = new AtomicReference<>();
                ServerHttpRequest request = exchange.getRequest();
                String sign = WebUtil.getRequiredHeader(request, SecurityApiRpcService.SIGNATURE_HEADER);
                String aesEnKey = WebUtil.getRequiredHeader(request, SecurityApiRpcService.AES_KEY_HEADER);
                String clientId = WebUtil.getRequiredHeader(request, SecurityApiRpcService.HEADER_CLIENT_ID);
                body.subscribe(value -> result.set(decrypt(clientId, aesEnKey, sign, value)),
                        e -> log.error(e.getMessage(), e)
                );

                return Mono.just(result.get());
            } catch (Exception e) {
                log.error("gateway parameter decryption exception", e);
                throw new RuntimeException("Parameter decryption exception");
            }
        };
    }

    private ServerHttpRequestDecorator decorate(ServerWebExchange exchange, HttpHeaders headers,
                                                CachedBodyOutputMessage outputMessage) {
        return new ServerHttpRequestDecorator(exchange.getRequest()) {
            @Override
            public HttpHeaders getHeaders() {
                long contentLength = headers.getContentLength();
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.putAll(super.getHeaders());
                if (contentLength > 0) {
                    httpHeaders.setContentLength(contentLength);
                } else {
                    httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                }
                return httpHeaders;
            }

            @Override
            public Flux<DataBuffer> getBody() {
                return outputMessage.getBody();
            }
        };
    }

    @Override
    public int getOrder() {
        return -2;
    }

    /**
     * ???????????????
     *
     * @param clientId  ??????appId
     * @param aesEnKey  ??????
     * @param signature ??????
     * @param body      ?????????
     * @return ??????????????????
     */
    private String decrypt(String clientId, String aesEnKey, String signature, String body) {

        log.info("???????????????===>");
        log.info("aesEnKey:{}", aesEnKey);
        log.info("signature:{}", signature);
        log.info("body:{}", body);

        // ??????????????????
        SecurityApiRpcService securityApiRpcService = SpringContextUtil.getBean(SecurityApiRpcService.class);
        // ??????????????????
        ClientService clientService = SpringContextUtil.getBean(ClientService.class);
        ClientConfig clientConfig = clientService.clientDetail(clientId, null);

        // ????????????body??????
        String param = securityApiRpcService.resolveData(body, aesEnKey, clientConfig);
        // ??????
        securityApiRpcService.verifySignature(param, signature, clientConfig);
        log.info("??????????????????????????????====>{}", param);
        return param;
    }
}

