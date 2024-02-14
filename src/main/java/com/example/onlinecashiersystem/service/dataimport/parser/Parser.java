package com.example.onlinecashiersystem.service.dataimport.parser;

import java.util.Collection;

public interface Parser<T> {

    Collection<T> getParsedCollection(byte[] bytes);

}
