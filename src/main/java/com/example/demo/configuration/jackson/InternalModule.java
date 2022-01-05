package com.example.demo.configuration.jackson;

import com.example.demo.configuration.jackson.codecs.ProductIdParser;
import com.example.demo.configuration.jackson.codecs.ProductNameParser;
import com.example.demo.configuration.jackson.codecs.ProductQuantityCodecs;
import com.example.demo.domain.ProductId;
import com.example.demo.domain.ProductName;
import com.example.demo.domain.ProductQuantity;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class InternalModule extends SimpleModule {

    private static final String NAME = "InternalModule";

    public InternalModule() {
        super(NAME, Version.unknownVersion());

        addSerializer(ProductQuantity.class, new ProductQuantityCodecs.Serializer());
        addDeserializer(ProductQuantity.class, new ProductQuantityCodecs.Deserializer());


        addSerializer(ProductId.class, new ProductIdParser.Serializer());
        addDeserializer(ProductId.class, new ProductIdParser.Deserializer());

        addSerializer(ProductName.class, new ProductNameParser.Serializer());
        addDeserializer(ProductName.class, new ProductNameParser.Deserializer());
    }
}
