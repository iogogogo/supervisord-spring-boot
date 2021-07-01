package com.iogogogo.supervisord.util;

import com.iogogogo.supervisord.core.parser.ResponseParser;
import com.iogogogo.supervisord.exception.SupervisordException;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by tao.zeng on 2021/6/30.
 */
public class XmlParse {

    @SuppressWarnings("unchecked")
    public static <T> T parse(String xml) throws SupervisordException {
        if (!StringUtils.hasLength(xml)) throw new NullPointerException("xml rpc response is null.");
        return (T) new ResponseParser().parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
    }
}
