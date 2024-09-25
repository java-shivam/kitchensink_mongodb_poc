package com.kitchensink.demo.data.mongo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class LongToObjectIdConverter implements Converter<Long, String> {
    @Override
    public String convert(Long source) {
        return source.toString();
    }
}

