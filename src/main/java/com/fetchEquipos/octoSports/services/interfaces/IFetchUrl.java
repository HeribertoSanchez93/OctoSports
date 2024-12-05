package com.fetchEquipos.octoSports.services.interfaces;

import java.lang.reflect.Type;

public interface IFetchUrl<T> {

    T fetch(String url, Type type);
}
