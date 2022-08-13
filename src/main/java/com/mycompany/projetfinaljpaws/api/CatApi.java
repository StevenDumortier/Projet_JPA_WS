/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetfinaljpaws.api;

import com.mycompany.projetfinaljpaws.dao.CategoryDAO;
import com.mycompany.projetfinaljpaws.entity.Category;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("cat")
public class CatApi {

    @POST()
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addCategory(Category category) {
        if (category.getName().length()<=255){
         CategoryDAO.create(category); 
         return Response.ok().build();
        }
        else{
           return Response.status(406).entity("Le nom doit aoir 255 caracteres max").build();
        }
    }

    @GET()
    @Produces({MediaType.APPLICATION_JSON})
    public List<Category> getCategory() {
        return CategoryDAO.findAll();
    }

    @Path("/{id}")
    @GET()
    @Produces({MediaType.APPLICATION_JSON})
    public Category getById(@PathParam("id") Long categoryId) {
        try{
          return CategoryDAO.findById(categoryId);  
        }
        catch(Exception e){
            System.out.println("probleme");
        }
        return null;
    }

    @Path("/{id}")
    @DELETE()
    public void delete(@PathParam("id") Long id) {
        try{
          Category category = CategoryDAO.findById(id); 
          CategoryDAO.delete(category);
        }
        catch(Exception e){
            System.out.println("probleme");
        }  
    }

    @Path("/{id}")
    @PUT()
    public void update(@PathParam("id") Long id, Category categoryToUpdate) {
        CategoryDAO.update(id, categoryToUpdate);
    }

}
