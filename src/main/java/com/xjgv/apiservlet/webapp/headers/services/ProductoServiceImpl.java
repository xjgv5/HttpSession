package com.xjgv.apiservlet.webapp.headers.services;

import com.xjgv.apiservlet.webapp.headers.models.Producto;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceImpl implements ProductoService {

    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "Notebook", "Computación", 175000),
                new Producto(2L, "Mesa escritorio", "Oficina", 10000),
                new Producto(3L, "Teclado mecánico", "Computación", 4000),
                new Producto(4L, "Mouse inalámbrico", "Computación", 1000),
                new Producto(5L, "Monitor curvo", "Computacion", 5000));
    }
}
