package com.faceart.faceart.custom;

import com.faceart.faceart.entities.Product;

import java.util.List;

public class ContainsForJSP {
    public static boolean containsProductId(List<Product> list, Long id) {
        return list.stream().anyMatch(o -> o.getId() == id);
    }
}
