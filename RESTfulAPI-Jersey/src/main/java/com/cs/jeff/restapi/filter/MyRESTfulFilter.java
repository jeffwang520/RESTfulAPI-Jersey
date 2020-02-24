package com.cs.jeff.restapi.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

public class MyRESTfulFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(final ContainerRequestContext requestContext) throws IOException {

        // TODO Auto-generated method stub
        System.out.println("In MyRESTfulFilter.filter request()");

    }

    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext) throws IOException {

        System.out.println("In MyRESTfulFilter.filter response()");

    }

}
