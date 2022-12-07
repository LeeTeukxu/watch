package com.zhide.govwatch.model;

import java.io.Serializable;

public class ClientAndClientLinkers implements Serializable {
    tbClient client;
    com.zhide.govwatch.model.tbClientLinkers clientLinkers;

    public tbClient getClient() {
        return client;
    }

    public void setClient(tbClient client) {
        this.client = client;
    }

    public com.zhide.govwatch.model.tbClientLinkers getClientLinkers() {
        return clientLinkers;
    }

    public void setClientLinkers(com.zhide.govwatch.model.tbClientLinkers clientLinkers) {
        this.clientLinkers = clientLinkers;
    }
}
