package com.yandex.money.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public interface MethodRequest<T> {

    String URI_API = "https://money.yandex.ru/api/";

    URL requestURL() throws MalformedURLException;

    T parseResponse(InputStream inputStream);

    PostRequestBodyBuffer buildParameters() throws IOException;
}