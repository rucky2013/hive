package com.hive.http.converter.json;

import java.io.IOException;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hive.common.AESOperator;
/**
 * 重写json返回值
 * @author zhangyl
 *
 */
public class MyMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter{
	private final Logger logger=LoggerFactory.getLogger(getClass());
	@Override
	protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
		JsonGenerator generator = this.objectMapper.getFactory().createGenerator(outputMessage.getBody(), encoding);
		ObjectWriter objectWriter=this.objectMapper.writer();
		//使用Jackson的ObjectMapper将Java对象转换成Json String
	    String json =this.objectMapper.writeValueAsString(object);
	    //加密
	    String value = null;
		try {
			value = AESOperator.getInstance().encrypt(json);
		} catch (Exception e) {
			this.logger.error(object+"AES加密失败");
		}
	    //输出
		objectWriter.writeValue(generator, value);
	}
}
