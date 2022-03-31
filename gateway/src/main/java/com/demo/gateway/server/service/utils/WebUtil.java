package com.demo.gateway.server.service.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import com.demo.gateway.server.model.props.IgnorePathProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.zip.GZIPInputStream;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/3/31 17:48
 */
@Slf4j
public class WebUtil {
    public static final String HEADER_CLIENT_ID = "x-client-id";
    private static final String HEADER_CLIENT_TOKEN = "x-client-token";
    private static final String PARAM_CLIENT_TOKEN = "_auth";
    public static final String HEADER_CLIENT_SOURCE = "x-zqlian-source";

    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    /**
     * @param request 请求
     *                如果通过了多级反向代理的话，
     *                X-Forwarded-For的值并不止一个， 而是一串IP值， 取 X-Forwarded-For中第一个非unknown的有效IP字符串。
     * @return 值
     */
    public static String clientIp(ServerHttpRequest request) {
        String ip = getHeaderStr(request, "x-forwarded-for", 0);
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = getHeaderStr(request, "Proxy-Client-IP", 0);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = getHeaderStr(request, "WL-Proxy-Client-IP", 0);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = Objects.requireNonNull(request.getRemoteAddress()).getHostString();
        }
        return ip;
    }

    /**
     * 获取header的值
     * header ==> []
     *
     * @param request 请求
     * @param key     header--key
     * @param index   索引
     * @return header值
     */
    private static String getHeaderStr(ServerHttpRequest request, String key, int index) {
        List<String> rs = request.getHeaders().get(key);
        return (rs != null && rs.size() > index) ? rs.get(index) : null;
    }

    private static String getHeaderStr(ServerHttpResponse response, String key, int index) {
        List<String> rs = response.getHeaders().get(key);
        return (rs != null && rs.size() > index) ? rs.get(index) : null;
    }

    /**
     * 获取header值，为空则抛出异常
     *
     * @param request 请求
     * @param key     header-key
     * @return 值
     */
    public static String getRequiredHeader(ServerHttpRequest request, String key) {
        String value = getHeaderStr(request, key, 0);
        Assert.notNull(value, String.format("header %s value is null", key));
        return value;
    }

    public static String getRequiredHeader(ServerHttpResponse response, String key) {
        String value = getHeaderStr(response, key, 0);
        Assert.notNull(value, String.format("header %s value is null", key));
        return value;
    }

    /**
     * 获取header值
     *
     * @param request 请求体
     * @param key     header键值
     * @return header[0]值 第一个
     */
    public static String getHeader(ServerHttpRequest request, String key) {
        return getHeaderStr(request, key, 0);
    }

    public static String getHeader(ServerHttpResponse response, String key) {
        return getHeaderStr(response, key, 0);
    }


    public static String getToken(ServerHttpRequest request) {
        String token = getHeader(request, HEADER_CLIENT_TOKEN);
        if (StrUtil.isBlank(token)) {
            // 从参数获取
            token = request.getQueryParams().getFirst(PARAM_CLIENT_TOKEN);
        }
        if (StrUtil.isBlank(token)) {
            return null;
        }
        if (token.replaceAll("^\\s$", ":").contains(":")) {
            String[] rs = token.split(":");
            return rs[rs.length - 1];
        } else {
            return token;
        }
    }

    /**
     * 获取客户端ID
     *
     * @param request 请求
     * @return 值
     */
    public static String getClientId(ServerHttpRequest request) {
        return getHeader(request, HEADER_CLIENT_ID);
    }

    /**
     * 不需校验的路径或客户端
     *
     * @param request              请求
     * @param ignorePathProperties 忽略配置
     * @return true/false
     */
    public static boolean isIgnoreClientOrPath(ServerHttpRequest request, IgnorePathProperties ignorePathProperties) {
        String clientId = WebUtil.getClientId(request);
        String path = request.getURI().getPath();

        if (ObjectUtil.isNotNull(clientId) && CollUtil.isNotEmpty(ignorePathProperties.getClients())
                && ignorePathProperties.getClients().contains(clientId)) {
            return true;
        }
        if(CollUtil.isNotEmpty(ignorePathProperties.getUrls())
                && ignorePathProperties.getUrls().stream().anyMatch(ignorePath -> PATH_MATCHER.match(ignorePath, path))) {
            return true;
        }
        if(CollUtil.isNotEmpty(ignorePathProperties.getSwaggerProviders())
                && ignorePathProperties.getSwaggerProviders().stream().anyMatch(ignorePath -> PATH_MATCHER.match(ignorePath, path))) {
            return true;
        }
        return false;
    }

    /**
     * 返回错误信息
     *
     * @param restData     返回实体
     * @param objectMapper 对象转换
     * @param request      请求
     * @param response     响应
     * @return 无
     */
    public static Mono<Void> responseError(Object restData, ObjectMapper objectMapper,
                                           ServerHttpRequest request, ServerHttpResponse response) {
        return responseError(restData, HttpStatus.BAD_REQUEST, objectMapper, request, response);
    }

    /**
     * 返回错误信息
     *
     * @param restData     返回实体
     * @param status       httpStatus
     * @param objectMapper 对象转换
     * @param request      请求
     * @param response     响应
     * @return 无
     */
    public static Mono<Void> responseError(Object restData, HttpStatus status, ObjectMapper objectMapper,
                                           ServerHttpRequest request, ServerHttpResponse response) {
        try {
            response.setStatusCode(status);
            response.getHeaders().set(Header.CONTENT_TYPE.toString(), MediaType.APPLICATION_JSON_VALUE);
            return response.writeWith(
                    Mono.just(response.bufferFactory().wrap(objectMapper.writeValueAsBytes(restData)))
            );
        } catch (JsonProcessingException parseEx) {
            log.error("json cast error", parseEx);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            response.getHeaders().set(Header.CONTENT_TYPE.toString(), MediaType.APPLICATION_JSON_VALUE);

            return response.writeWith(
                    Mono.just(
                            response.bufferFactory()
                                    .wrap("filter error, filter name ==> ClientFilter".getBytes(StandardCharsets.UTF_8))
                    ));
        }
    }

    public static boolean isApplicationJson(MediaType other) {
        return MediaType.APPLICATION_JSON.equalsTypeAndSubtype(other);
    }


    /**
     * 是否为gzip压缩格式
     *
     * @param response 响应
     * @return true/false
     */
    public static boolean responseWithGzip(ServerHttpResponse response) {
        return "GZIP".equalsIgnoreCase(response.getHeaders().getFirst("Content-Encoding"));
    }


    /**
     * 返回内容获取
     *
     * @param rs       返回内容集合 内容为base64转化后的内容
     * @param byteSize 返回内容长度
     * @return
     */
    public static byte[] resultToByteArray(List<String> rs, int byteSize) {
        byte[] bytes = new byte[byteSize];
        byte[] tmp = null;
        int pos = 0;
        for (String str : rs) {
            tmp = Base64.getDecoder().decode(str);
            System.arraycopy(tmp, 0, bytes, pos, tmp.length);
            pos += tmp.length;
            tmp = null;
        }
        return bytes;
    }

    /**
     * gzip读取
     *
     * @param bytes gzip压缩内容
     * @return 解压后的原始内容
     */
    public static String zipResult2Str(byte[] bytes) {
        String rs = "";
        try (GZIPInputStream gzis = new GZIPInputStream(new ByteArrayInputStream(bytes))) {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int length;
            while ((length = gzis.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            rs = result.toString("UTF-8");
            result.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return rs;
    }
}
