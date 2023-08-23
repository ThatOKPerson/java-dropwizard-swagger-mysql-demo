package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.ProductService;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.FailedToCreateProductException;
import org.kainos.ea.client.FailedToDeleteProductException;
import org.kainos.ea.client.FailedToGetProductsException;
import org.kainos.ea.client.FailedToUpdateProductException;
import org.kainos.ea.client.InvalidProductException;
import org.kainos.ea.client.ProductDoesNotExistException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Engineering Academy Dropwizard Product API")
@Path("/api")
public class ProductController {
    private final ProductService productService = new ProductService();

    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts(){
        try {
            return Response.ok(productService.getAllProducts()).build();
        } catch (FailedToGetProductsException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/products/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsById(@PathParam("id") int id){
        try {
            return Response.ok(productService.getProductByID(id)).build();
        } catch (FailedToGetProductsException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        } catch (ProductDoesNotExistException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(ProductRequest product){
            try {
                return Response.ok(productService.createProduct(product)).build();
            } catch (FailedToCreateProductException e) {
                System.err.println(e.getMessage());
                return Response.serverError().build();
            } catch (InvalidProductException e) {
                System.err.println(e.getMessage());
                return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
            }
    }

    @PUT
    @Path("/products/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") int id, ProductRequest product){
        try {
            return Response.ok(productService.updateProduct(id, product)).build();
        } catch (InvalidProductException | ProductDoesNotExistException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToUpdateProductException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/products/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("id") int id){
        try {
            productService.deleteProduct(id);
            return Response.ok().build();
        } catch (ProductDoesNotExistException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToDeleteProductException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }
}
