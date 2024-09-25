package com.kitchensink.demo.data.mongo;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class ObjectIdToLongConverter implements Converter<String, Long> {
    @Override
    public Long convert(String source) {
        return Long.valueOf(source.toString());
    }
}
