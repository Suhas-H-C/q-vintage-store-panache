package org.shc.quarkus.panache.utils;

import org.shc.quarkus.jpa.entity.Customer;

public class CustomerUtils {

    public static Customer JohnDoe() {
        return new Customer("John", "Dao", "John.Dao@email.com");
    }
}
