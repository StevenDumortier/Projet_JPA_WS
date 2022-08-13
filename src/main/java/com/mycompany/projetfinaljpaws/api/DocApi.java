/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetfinaljpaws.api;

import com.mycompany.projetfinaljpaws.dao.CategoryDAO;
import com.mycompany.projetfinaljpaws.dao.DocumentationDAO;
import com.mycompany.projetfinaljpaws.entity.Category;
import com.mycompany.projetfinaljpaws.entity.Documentation;
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

@Path("doc")
public class DocApi {

    @POST()
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addDocumentation(Documentation documentation) {
        if (documentation.getName().length() > 255) {
            return Response.status(400).entity("Le nom doit contenir 255 caracteres max").build();
        }
        if (documentation.getUrl().length() > 255) {
            return Response.status(400).entity("L'URL doit contenir 255 caracteres max").build();
        }
        if (documentation.getCategory().getId() != null) {
            try {
                CategoryDAO.findById(documentation.getCategory().getId());
                DocumentationDAO.create(documentation);
                return Response.ok().build();
            } catch (Exception e) {
                System.out.println("problème");
                return Response.status(400).entity("La categorie n'existe pas").build();
            }
        }
        return null;
    }

    @GET()
    @Produces({MediaType.APPLICATION_JSON})
    public List<Documentation> getDocumentation() {
        return DocumentationDAO.findAll();
    }

    @Path("/{id}")
    @GET()
    @Produces({MediaType.APPLICATION_JSON})
    public Category getById(@PathParam("id") Long categoryId) {
        try {
            return CategoryDAO.findById(categoryId);
        } catch (Exception e) {
            System.out.println("probleme");
        }
        return null;
    }

    @Path("/{id}")
    @DELETE()
    public void delete(@PathParam("id") Long id) {
        Documentation documentation = DocumentationDAO.findById(id);
        DocumentationDAO.delete(documentation);
    }

    @Path("/{id}")
    @PUT()
    public void update(@PathParam("id") Long id, Documentation documentationToUpdate) {
        DocumentationDAO.update(id, documentationToUpdate);
    }

}
