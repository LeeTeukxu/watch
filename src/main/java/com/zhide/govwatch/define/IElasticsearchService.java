package com.zhide.govwatch.define;

public interface IElasticsearchService {
    String RebuildIndex() throws Exception;
    String DeleteIndex() throws  Exception;
}
