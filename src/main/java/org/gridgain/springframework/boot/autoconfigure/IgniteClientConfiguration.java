package org.gridgain.springframework.boot.autoconfigure;

import org.apache.ignite.client.IgniteClient;

public class IgniteClientConfiguration extends IgniteClient.Builder {
    public IgniteClientConfiguration setAddresses(String addresses) {
        addresses(addresses);
        return this;
    }
}
