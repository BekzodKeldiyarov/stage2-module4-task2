package com.mjc.stage2.impl;


import com.mjc.stage2.Connection;

public class ProxyConnection implements Connection {
    private RealConnection realConnection;

    public ProxyConnection(RealConnection realConnection) {
        this.realConnection = realConnection;
    }

    public void reallyClose() {
        this.realConnection.close();
        ConnectionPool.getInstance().releaseConnection(new ProxyConnection(this.realConnection));
    }

    @Override
    public void close() {
        ConnectionPool.getInstance().releaseConnection(new ProxyConnection(this.realConnection));
    }

    @Override
    public boolean isClosed() {
        return this.realConnection.isClosed();
    }

}
