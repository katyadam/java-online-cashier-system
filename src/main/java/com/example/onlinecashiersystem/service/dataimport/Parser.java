package com.example.onlinecashiersystem.service.dataimport;

import java.util.Collection;

public interface Parser<T> {

    Collection<T> getParsedCollection(byte[] bytes);

}
